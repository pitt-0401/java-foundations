/*
  Interactive patterns and multiple-choice answers. Both are opt-in through
  Markdown conventions (see docs/design-guidelines.md); on pages without that
  markup this script does nothing. Without the script, the same Markdown reads
  as a plain pattern, a list of explanations, and a list of answers.

  1. Interactive pattern: a pattern followed by <div class="pattern-parts"> with
     a definition list (one <var> term per placeholder). Each placeholder
     becomes a button; selecting one shows its explanation and highlights its
     part of the examples up to the next heading. A short label under a
     placeholder comes from <var data-label="…"> in the pattern. Which example
     text belongs to a placeholder comes from <var data-find="…"> in the list
     (explicit mapping); a one-line pattern without any data-find is matched
     against each example line automatically.

  2. Multiple choice: inside a numbered exercise, a bulleted list whose items
     each hold one answer (inline code, one code span per output line) and an
     explanation that starts with **Correct.** or **Not quite.**.

  3. Progress: a "Your turn" list with answer buttons gets an "N of M done" line
     under its heading.
*/
(function () {
  "use strict";

  var slice = function (list) { return Array.prototype.slice.call(list); };

  /* ---------- 1. Interactive patterns ---------- */

  // Placeholder colors: each placeholder name belongs to one kind, drawn in that
  // kind's color in the pattern and in the examples (extra.css, data-kind). The
  // same name gets the same color on every page. A name not listed here keeps
  // the accent color; add it here to give it a kind.
  var KINDS = {
    returnType: "return-type",
    methodName: "method-name",
    ClassName: "method-name",
    type: "type",
    name: "name",
    paramName: "name",
    statements: "statements",
    value: "value"
  };
  function setKind(el, name) {
    if (Object.prototype.hasOwnProperty.call(KINDS, name)) el.dataset.kind = KINDS[name];
  }

  // A regular expression for one line of example code, built from the pattern:
  // literal text must match (a run of spaces matches one or more spaces), and
  // each placeholder is a capture group. A placeholder followed by a space is
  // one word; otherwise it runs up to the next literal text, such as ";", that
  // leaves only a comment after it (so a ";" inside a string does not count).
  // Matching is per line, so it is used only for one-line patterns.
  function patternRegex(code) {
    var source = "^\\s*";
    var names = [];
    var nodes = slice(code.childNodes);
    nodes.forEach(function (node, i) {
      if (node.nodeName === "VAR") {
        var next = nodes[i + 1];
        names.push(node.textContent.trim());
        source += next && /^\s/.test(next.textContent) ? "(\\S+)" : "(.+?)";
      } else {
        source += node.textContent
          .replace(/[.*+?^${}()|[\]\\]/g, "\\$&")
          .replace(/\s+/g, "\\s+");
      }
    });
    // The pattern must reach the end of the line, apart from a comment.
    source += "(?=\\s*(//.*)?$)";
    return { regex: new RegExp(source, "d"), names: names };
  }

  // Wraps the text offsets [start, end) of a code element in spans marked with
  // a part name. A range can cross the syntax-highlighting spans, so each text
  // node it touches gets its own wrapper. Ranges may nest (value inside
  // statements): the text nodes are listed again for each range, which works
  // because wrapping never changes the text. Copying gives exactly the same Java.
  function wrapRanges(code, ranges) {
    ranges.forEach(function (range) {
      var walker = document.createTreeWalker(code, NodeFilter.SHOW_TEXT);
      var hits = [];
      var offset = 0;
      while (walker.nextNode()) {
        var node = walker.currentNode;
        var from = Math.max(range.start - offset, 0);
        var to = Math.min(range.end - offset, node.length);
        if (from < to) hits.push({ node: node, from: from, to: to });
        offset += node.length;
      }
      hits.forEach(function (h) {
        var target = h.node;
        if (h.to < target.length) target.splitText(h.to);
        if (h.from > 0) target = target.splitText(h.from);
        var span = document.createElement("span");
        span.className = "jf-part";
        span.dataset.part = range.name;
        setKind(span, range.name);
        target.parentNode.insertBefore(span, target);
        span.appendChild(target);
      });
    });
  }

  // Explicit mapping: data-find="int [[a]] | int [[b]]" lists literal example
  // text with the placeholder's part in [[ ]]; the text around it only pins
  // down which occurrence is meant. A run of spaces matches any whitespace, and
  // a word at either end must be a whole word, so "int [[a]]" never matches
  // inside "int ab".
  // The last "]]" closes the part, so a part can end in a bracket ("[[arr[i]]]").
  function findRegex(spec) {
    var open = spec.indexOf("[[");
    var close = spec.lastIndexOf("]]");
    if (open === -1 || close < open + 3) return null;   // no part, or an empty one
    var literal = function (t) {
      return t.replace(/[.*+?^${}()|[\]\\]/g, "\\$&").replace(/\s+/g, "\\s+");
    };
    var plain = spec.slice(0, open) + spec.slice(open + 2, close) + spec.slice(close + 2);
    // "(?:^|[^\w$])" rather than a lookbehind, which older Safari cannot parse.
    var source = (/^[\w$]/.test(plain) ? "(?:^|[^\\w$])" : "") +
      literal(spec.slice(0, open)) + "(" + literal(spec.slice(open + 2, close)) + ")" +
      literal(spec.slice(close + 2)) +
      (/[\w$]$/.test(plain) ? "(?![\\w$])" : "");
    return new RegExp(source, "dg");
  }

  function annotateFinds(code, finds) {
    var text = code.textContent;
    var ranges = [];
    finds.forEach(function (f) {
      var match;
      f.regex.lastIndex = 0;
      while ((match = f.regex.exec(text))) {
        ranges.push({ name: f.name, start: match.indices[1][0], end: match.indices[1][1] });
        f.used = true;
      }
    });
    if (ranges.length) wrapRanges(code, ranges);
    return ranges.length > 0;
  }

  function annotate(code, pattern) {
    var ranges = [];
    var lineStart = 0;
    code.textContent.split("\n").forEach(function (line) {
      var match = pattern.regex.exec(line);
      if (match) {
        pattern.names.forEach(function (name, i) {
          var at = match.indices[i + 1];
          if (at) ranges.push({ name: name, start: lineStart + at[0], end: lineStart + at[1] });
        });
      }
      lineStart += line.length + 1;
    });
    if (ranges.length) wrapRanges(code, ranges);
    return ranges.length > 0;
  }

  function setUpPattern(pre, partsBox) {
    var code = pre.querySelector("code");
    var list = partsBox.querySelector("dl");
    if (!code || !list) return;

    var explanations = {};
    var finds = [];
    slice(list.querySelectorAll("dt")).forEach(function (dt) {
      var name = dt.textContent.trim();
      var dd = dt.nextElementSibling;
      if (dd && dd.tagName === "DD") explanations[name] = dd.innerHTML;
      var mapped = dt.querySelector("var[data-find]");
      if (!mapped) return;
      mapped.dataset.find.split(" | ").forEach(function (spec) {
        var regex = findRegex(spec.trim());
        if (regex) finds.push({ name: name, spec: spec.trim(), regex: regex });
        else console.warn("lesson.js: data-find needs a non-empty part in [[ ]]:", spec);
      });
    });

    // Example code: every code block after the explanations, up to the next
    // heading. With data-find only the listed text is highlighted; otherwise a
    // one-line pattern is matched line by line, and a multi-line one not at all.
    var singleLine = code.textContent.indexOf("\n") === -1;
    var pattern = !finds.length && singleLine ? patternRegex(code) : null;
    var examples = [];
    for (var el = partsBox.nextElementSibling; el && !/^H[1-6]$/.test(el.tagName); el = el.nextElementSibling) {
      slice(el.querySelectorAll(".highlight code")).forEach(function (c) {
        var found = finds.length ? annotateFinds(c, finds) : pattern ? annotate(c, pattern) : false;
        if (found) examples.push(c);
      });
    }
    finds.forEach(function (f) {
      if (!f.used) console.warn("lesson.js: no example matches data-find", f.spec);
    });

    // A repeated placeholder shows its label once, but every occurrence gets it
    // in its accessible name.
    var labels = {};
    slice(code.querySelectorAll("var[data-label]")).forEach(function (v) {
      labels[v.textContent.trim()] = v.dataset.label;
    });
    var buttons = [];
    slice(code.querySelectorAll("var")).forEach(function (v) {
      var name = v.textContent.trim();
      if (!explanations[name]) return;
      var button = document.createElement("button");
      button.type = "button";
      button.className = "jf-ph";
      button.dataset.part = name;
      setKind(button, name);
      button.setAttribute("aria-pressed", "false");
      button.setAttribute("aria-label", name + ", placeholder" + (labels[name] ? ": " + labels[name] : ""));
      button.textContent = name;
      v.textContent = "";
      v.appendChild(button);
      buttons.push(button);
    });
    if (!buttons.length) return;

    // Labels under placeholders are drawn by CSS from data-label; a one-line
    // pattern is also shown larger.
    if (singleLine) pre.classList.add("is-row");
    pre.classList.add("is-interactive");
    partsBox.classList.add("is-interactive");

    // The note changes on hover and focus too; only a selection is announced,
    // through a separate hidden live region.
    var note = document.createElement("p");
    note.className = "jf-note";
    var status = document.createElement("span");
    status.className = "jf-sr-only";
    status.setAttribute("aria-live", "polite");
    // The middle sentence names what the placeholders stand for, so it comes from
    // the page: <div class="pattern-parts" data-replace-with="…">.
    var replaceWith = partsBox.dataset.replaceWith;
    var defaultNote = "Boxed words are placeholders. " +
      (replaceWith ? "Replace them with " + replaceWith + ". " : "") +
      (examples.length ? "Select a placeholder to highlight its role in the examples." : "Select a placeholder to see what it means.");
    note.textContent = defaultNote;
    partsBox.appendChild(note);
    partsBox.appendChild(status);

    var pinned = null;
    function show(name) {
      examples.forEach(function (c) {
        c.classList.toggle("jf-dim", !!name);
        slice(c.querySelectorAll(".jf-part")).forEach(function (s) {
          s.classList.toggle("is-on", s.dataset.part === name);
        });
      });
      buttons.forEach(function (b) { b.classList.toggle("is-preview", b.dataset.part === name); });
      note.classList.toggle("is-on", !!name);
      if (name) {
        var v = document.createElement("var");
        v.textContent = name;
        setKind(v, name);
        note.innerHTML = ": " + explanations[name];
        note.insertBefore(v, note.firstChild);
      } else {
        note.textContent = defaultNote;
      }
    }
    function pin(name) {
      pinned = name;
      buttons.forEach(function (b) { b.setAttribute("aria-pressed", String(b.dataset.part === name)); });
      show(name);
      status.textContent = name ? note.textContent : "Selection cleared.";
    }
    buttons.forEach(function (b) {
      var name = b.dataset.part;
      b.addEventListener("mouseenter", function () { show(name); });
      b.addEventListener("mouseleave", function () { show(pinned); });
      b.addEventListener("focus", function () { if (!pinned) show(name); });
      b.addEventListener("blur", function () { show(pinned); });
      // Selecting the selected placeholder again clears the selection.
      b.addEventListener("click", function () { pin(pinned === name ? null : name); });
    });
    // On the document, because Safari does not focus a button when it is clicked.
    document.addEventListener("keydown", function (e) {
      if (e.key === "Escape" && pinned) pin(null);
    });
  }

  slice(document.querySelectorAll(".md-typeset pre.pattern")).forEach(function (pre) {
    var box = pre.nextElementSibling;
    if (box && box.classList.contains("pattern-parts")) setUpPattern(pre, box);
  });

  /* ---------- 2. Multiple choice ---------- */

  // A bulleted list is a set of answers when every item is
  // <p>code…</p><p><strong>Correct.|Not quite.</strong> …</p> and exactly one is correct.
  function readChoices(ul) {
    var choices = [];
    var items = slice(ul.children);
    for (var i = 0; i < items.length; i++) {
      var parts = slice(items[i].children);
      var lead = parts[1] && parts[1].firstChild;
      var verdict = lead && lead.nodeName === "STRONG" ? lead.textContent.trim() : "";
      var looksLikeAnswer = verdict === "Correct." || verdict === "Not quite.";
      if (parts.length !== 2 || parts[0].tagName !== "P" || parts[1].tagName !== "P") {
        // Probably meant as an answer, but the explanation has more than one paragraph.
        if (looksLikeAnswer) console.warn("lesson.js: each answer needs exactly two paragraphs: the output, then one explanation.", items[i]);
        return null;
      }
      var codes = slice(parts[0].children);
      if (!codes.length || codes.some(function (c) { return c.tagName !== "CODE"; })) return null;
      if (!looksLikeAnswer) return null;
      choices.push({
        lines: codes.map(function (c) { return c.textContent; }),
        correct: verdict === "Correct.",
        explanation: parts[1]
      });
    }
    var correct = choices.filter(function (c) { return c.correct; }).length;
    return choices.length > 1 && correct === 1 ? choices : null;
  }

  function setUpChoices(ul, choices, onSolved) {
    var group = document.createElement("div");
    group.className = "jf-choices";
    group.setAttribute("role", "group");
    var prompt = ul.parentElement.querySelector(":scope > p");
    var number = slice(ul.parentElement.parentElement.children).indexOf(ul.parentElement) + 1;
    group.setAttribute("aria-label", "Answers to exercise " + number + (prompt ? ": " + prompt.textContent.trim() : ""));
    var feedback = document.createElement("div");
    feedback.className = "jf-feedback";
    feedback.setAttribute("aria-live", "polite");
    var buttons = [];

    // Answered buttons keep keyboard focus (aria-disabled, not disabled), and
    // their label says how they were answered, not only their color.
    function mark(button, state, tagText) {
      button.classList.add(state);
      button.setAttribute("aria-disabled", "true");
      if (!tagText) return;
      var tag = document.createElement("span");
      tag.className = "jf-tag";
      tag.textContent = tagText;
      button.appendChild(tag);
      button.setAttribute("aria-label", button.getAttribute("aria-label") + ". " + tagText.slice(2));
    }

    choices.forEach(function (choice) {
      var button = document.createElement("button");
      button.type = "button";
      button.className = "jf-opt";
      button.setAttribute("aria-label", choice.lines.join(", then "));
      var lines = document.createElement("span");
      lines.className = "jf-lines";
      lines.textContent = choice.lines.join("\n");
      button.appendChild(lines);
      button.addEventListener("click", function () {
        if (button.getAttribute("aria-disabled") === "true") return;
        var text = choice.explanation.cloneNode(true);
        if (choice.correct) {
          mark(button, "is-right", "✓ Correct");
          buttons.forEach(function (b) { if (b.getAttribute("aria-disabled") !== "true") mark(b, "is-dim"); });
          onSolved();
        } else {
          mark(button, "is-wrong", "✗ Incorrect");
          text.appendChild(document.createTextNode(" Try another answer."));
        }
        feedback.className = "jf-feedback " + (choice.correct ? "is-right" : "is-wrong");
        feedback.textContent = "";
        feedback.appendChild(text);
      });
      buttons.push(button);
      group.appendChild(button);
    });

    ul.replaceWith(group);
    group.after(feedback);
  }

  // Progress for a "Your turn" list that has answer buttons. A question is done
  // when its correct answer is found; questions without buttons are done once
  // the Answers box after the list (before the next heading) is opened.
  function setUpProgress(ol, questions) {
    var heading = ol.previousElementSibling;
    while (heading && !/^H[1-6]$/.test(heading.tagName)) heading = heading.previousElementSibling;
    // The Answers box is the first <details> after the list, before the next heading.
    var answers = null;
    for (var el = ol.nextElementSibling; el && !/^H[1-6]$/.test(el.tagName); el = el.nextElementSibling) {
      if (el.tagName === "DETAILS") { answers = el; break; }
    }

    var done = new Set();
    var progress = document.createElement("p");
    progress.className = "jf-progress";
    var ticks = document.createElement("span");
    ticks.className = "jf-ticks";
    ticks.setAttribute("aria-hidden", "true");
    questions.forEach(function () { ticks.appendChild(document.createElement("span")); });
    var text = document.createElement("span");
    progress.appendChild(ticks);
    progress.appendChild(text);
    function update() {
      slice(ticks.children).forEach(function (t, i) { t.classList.toggle("is-done", i < done.size); });
      text.textContent = done.size + " of " + questions.length + " done";
    }
    update();
    if (heading) heading.after(progress);

    questions.forEach(function (q) {
      if (q.choices) setUpChoices(q.ul, q.choices, function () { done.add(q.li); update(); });
    });
    if (answers) {
      answers.addEventListener("toggle", function () {
        if (!answers.open) return;
        questions.forEach(function (q) { if (!q.choices) done.add(q.li); });
        update();
      });
    }
  }

  slice(document.querySelectorAll(".md-typeset ol")).forEach(function (ol) {
    var questions = slice(ol.children).map(function (li) {
      var ul = li.querySelector(":scope > ul");
      return { li: li, ul: ul, choices: ul ? readChoices(ul) : null };
    });
    if (questions.some(function (q) { return q.choices; })) setUpProgress(ol, questions);
  });
})();

# Design guidelines

This page describes the design the course website actually uses. It is for
contributors and is not published as a student page (`mkdocs.yml` excludes it).
Read it before you change shared styling, and update it when a project-wide
design decision changes.

The site uses MkDocs with the Material theme. Everything below is either a theme
setting in `mkdocs.yml`, a rule in `docs/stylesheets/extra.css`, or one of the
two interactive components in `docs/javascripts/lesson.js`. Prefer the theme's
own components; add CSS or JavaScript only when the theme cannot do what is
needed.

## Principles

- One accent color, neutral everything else. Green and red appear only on
  checked answers, always with a text label. The one exception is the
  placeholders of interactive patterns, which have one soft color per kind of
  placeholder (see "Placeholder colors" below).
- Plain text, tables, and code blocks rather than cards, badges, or decorative
  boxes.
- Content first: no hero section, no animation, no decorative icons.
- Every rule must work in the light and dark schemes and at a 390 px width.

## Typography

| Element | Setting |
|---|---|
| Text font | IBM Plex Sans (`theme.font.text`) for headings, body text, navigation, tables, labels and the site name in the header |
| Code font | IBM Plex Mono (`theme.font.code`) for code, inline code, patterns and every placeholder (`<var>`), including one in running text. Placeholders are upright, never italic: in UML, which students also learn, italics marks abstract elements |
| Font weights | Material loads 300, 400 and 700; the `@import` at the top of `extra.css` adds Plex Sans 500 and 600 and Plex Mono 600 |
| Body text | `0.825rem` (16.5 px at the default size; Material raises the root font size on screens 1600 px and wider), line height `1.6` |
| H1 | `2.06em` (34 px), weight 600, slight negative letter spacing, default text color |
| H2 | `1.4em` (23 px), weight 600, slight negative letter spacing, `2.2em` space above, thin rule underneath |
| H3 | `1.12em` (18.5 px), weight 600, `1.8em` space above |
| Bold | weight 600 |
| Tables | `0.8em` (slightly larger than the theme default); code in tables `0.92em` of that (about 12 px); header weight 600 |
| Code | `0.82em` (13.5 px), inline and in code blocks; code blocks have line height `1.5` |
| Patterns | `1.09em` (18 px); interactive patterns use the sizes under "Interactive patterns" |

Each page has one H1 (the group name, such as "Basics"). Most H2s are
"How do I …?" questions; Help uses error-focused questions and Practice uses
numbered exercises. Every H2 has a short explicit id (`## How do I declare a
variable? { #variable }`). H3 is used for **Your turn**, for the parts described under "Syntax helps"
below, for the question-index
groups and "Using the terminal" on the home page, for "Find the line" on Help,
and for the worked-example steps on Practice. The question list in the left sidebar shows H2
only (`toc_depth: 2`).

The content column is at most `38rem` wide (760 px at the default size, 836 px
on screens 1600 px and wider), so text keeps a comfortable reading width now that
there is no right sidebar. Between 960 and 1219 px, where both sidebars are in
the drawer, the column is centered. Do not widen it.

## Color

Colors are set as tokens on `body[data-md-color-scheme]`:

| Token | Light (`default`) | Dark (`slate`) | Used for |
|---|---|---|---|
| `--jf-accent` | `#2b4fa3` | `#8db6ee` | Links, placeholders, primary button, current tab and question, focus outlines, Answers buttons, highlighted parts of placeholders without a kind |
| `--jf-accent-strong` | `#213f86` | `#b8d1f5` | Hover on the primary button, link hover, current question in the sidebar |
| `--jf-on-accent` | `#ffffff` | `#0f1b2d` | Text on the primary button, and on hovered or focused buttons (Material's `--md-accent-bg-color` is set to it) |
| `--jf-rule` | `#e2e5e3` | `#2f3542` | Thin rules: under H2 and patterns, under the header and tabs, footer border |
| `--jf-rule-strong` | `#858c89` | `#737c90` | Borders of answer buttons and Answers buttons, empty progress ticks |
| `--jf-note-bg` | `#f4f6fa` | `#222733` | Side notes (blockquotes) |
| `--jf-code-border` | `#dce1e6` | `#333a48` | The 1 px border around code blocks |
| `--jf-muted` | `#5b6360` | `#a5adbb` | Code that steps back while a pattern part is highlighted |
| `--jf-hl-soft`, `--jf-hl` | `#eef2fb`, `#dfe7f8` | `#222c42`, `#2b3956` | Tint of placeholders without a kind; answer button hover and press; Answers button hover |
| `--jf-var-line` | `#6f88c8` | `#6a80b8` | Outline of placeholders without a kind (static patterns, the home page, the explanation line, and slots without a kind): 3:1 or more on the page and on the tint |
| `--jf-ph-<kind>-bg`, `-fg`, `-line` | see "Placeholder colors" | see "Placeholder colors" | Interactive placeholders, their explanation line, and their highlighted example code |
| `--jf-ok`, `--jf-ok-bg` | `#1d6b38`, `#e8f3ec` | `#86d19f`, `#1b2e23` | A correct answer and its "✓ Correct" label |
| `--jf-bad`, `--jf-bad-bg` | `#a3282c`, `#f9eaea` | `#f2a1a1`, `#3a2326` | A wrong answer and its "✗ Incorrect" label |

In the dark scheme the header and footer use the page background instead of a
colored bar. The palette toggle in the header cycles through the system
setting, light, and dark.

Contrast: links 7.1:1 (light) and 7.7:1 (dark; 6.8:1 on code backgrounds).
Light code colors on the `#f1f3f5` code background: keywords `#8a3a0e` 7.0:1,
numbers `#0b6370` 6.2:1, strings `#2f6b1f` 5.8:1, comments `#5d6561` 5.4:1;
function names, operators and punctuation use the code text color. Placeholder
slots, answer states and highlighted pattern parts are all 4.7:1 or above in both
schemes. Borders of answer buttons and Answers buttons (`--jf-rule-strong`) are
3.4:1 (light) and 3.8:1 (dark), so the controls are recognizable. Dark code
colors follow the same scheme as light ones: keywords and constants `#e0a36b`
6.5:1, numbers `#5cc0cc` 6.7:1, Material's green strings 5.1:1, and function
names, operators and punctuation in the code text color. Highlighted lines
(`hl_lines`) use `--jf-hl` (light) and `--jf-hl-soft` (dark) with a 2 px
`--jf-accent` bar; the dark tint is the darker one because dark-scheme code
comments are 4.58:1 on code and 4.53:1 on highlighted lines, the closest to the
limit, so do not lighten either. Keep all text at 4.5:1 or above, on highlighted
lines too.

Do not use color alone to show meaning; pair it with text.

## Spacing

Spacing comes from the theme's defaults plus the heading margins above. Avoid adding margins to individual
pages; change the shared rule instead.

## Links, buttons, navigation, and focus

- Links use `--jf-accent` and change to `--jf-accent-strong` on hover.
- Buttons (`.md-button`) are outlined in the accent color; the primary button
  (`.md-button--primary`) is filled. Only the home page uses buttons.
- The six pages are tabs in a compact row under the header
  (`navigation.tabs`); the current page is bold with an accent underline.
- The left sidebar lists the current page's questions (`toc.integrate`, with
  `toc.follow`), on a thin rule; the question being read is bold accent with an
  accent bar. There is no right sidebar.
- Other navigation features: `navigation.sections`, `navigation.footer`
  (previous and next links), and `navigation.top`. On narrow screens the tabs
  and questions move into Material's drawer.
- Links, tabs, navigation, footer links, header buttons, buttons, **Answers**
  summaries, the copy button, **Back to top**, placeholders and answer buttons
  have a 2 px solid accent `:focus-visible` outline. The palette toggle and
  search field use the theme's own focus styles.

## Code

- Code blocks use Material's highlighting and copy button (`content.code.copy`),
  on a cool grey background with a 1 px `--jf-code-border` border and 6 px
  corners.
- A block title (`title="Car.java"`) is shown as a small grey label above the code.
- Line numbers (`linenums="1"`) use the code's size and line height, so each
  number stays beside its line, and sit inside the same bordered box.
- Compiler and runtime output goes in `text` blocks with the real output.
- Code blocks scroll sideways inside their own box when a line is too long; the
  page itself never scrolls. Do not make code or output blocks wrap: wrapping
  would move the caret (`^`) in compiler messages away from the character it
  points to.

### Sections: pattern, example, notes, Your turn

Every "How do I …?" section follows the same order. Sections without a pattern
(the Help sections and "Which kind of variable do I need?") skip step 1.

1. **Pattern:** raw HTML, `<pre class="pattern"><code>…</code></pre>`, with every
   placeholder wrapped in `<var>`. Placeholders are upright, in the accent color
   (interactive patterns use one color per kind; see "Placeholder colors"), on a
   tint with an outline, so they do not depend on color alone. In a pattern that
   is not interactive the outline is `--jf-var-line` and the slot has no
   padding, so aligned comments stay aligned. Escape `<` and `&` inside
   the block. Patterns have no syntax colors; that is intended, so the
   placeholders stand out. A pattern has no box: it sits between the H2 rule and
   a thin rule of its own. Material still adds its copy button. A pattern can be
   made interactive; see "Interactive patterns" below.
2. **Example:** a normal fenced `java` block with a one-line lead-in.
3. **Notes:** a short bullet list or one or two sentences.
4. **Lectures line:** `<p class="lectures">Lectures 02, 04</p>`, small and grey.
5. **Your turn** (H3): one to three numbered exercises (up to five in a section
   with syntax helps), each starting with a bold
   label such as **Predict the output.**, **Find the bug.**, **Fill in the
   blank.**, **Write it.**, or **What happens?** (Help also uses **Read this
   error.** and **Which exception will each line throw?**), followed by one `<details markdown>` box
   with `<summary>Answers</summary>`.

### Syntax helps

Sections whose main difficulty is syntax can add up to three H3 parts after the
notes and before the lectures line. "How do I declare a class?" uses all three;
"How do I write a constructor?" and "How do I write a method?" each have a
word-by-word table, so the explanation is in the section the student is
already reading:

- **Where each part goes:** a `<ul class="anatomy">` nested list drawn as nested
  boxes (accent border outside, grey borders inside, note background for the
  innermost boxes). Each box starts with a bold `.anatomy-label`, so the nesting
  is clear without the borders, and screen readers announce a real nested list.
- **What each word means:** the line as a `java` block, then a two-column
  `| Part | Meaning |` table with one row per word or symbol. Do not put the
  whole line in the table header; it wraps badly on phones. Keep every row
  even when the section's pattern is interactive: the pattern's explanations
  appear only after a placeholder is selected, so the table is the explanation
  students see without clicking.
- **Write it one step at a time:** numbered steps, each a complete `java` block that
  compiles (the last step may point to the finished example at the top of the
  section instead of repeating it), with the new lines marked by `hl_lines` (from
  `pymdownx.highlight`). The lead-in sentence names what was added, so the
  highlight is not the only cue.

Matching **Your turn** exercises: **Put the lines in order.** (lines without
indentation), **Label the parts.** (a block with `linenums="1"`), and **Fill in
the blank.**

## Home page components

- **Button:** local setup (primary), at the top. A Codespaces button and setup
  option are left out until Codespaces has been tested.
- **Question index:** `<div class="question-index">` holds one `<div markdown>`
  per column. Each group is a small uppercase grey H3 followed by a plain list of
  links (no bullets). The columns sit side by side on wide screens
  (`auto-fit`, at least `14rem` each) and stack on narrow ones.
- **Copyright:** set by `copyright` in `mkdocs.yml`; Material shows it in the
  footer of every page, in the secondary text grey.

## Tables

Use plain Markdown tables. Material wraps every table in a horizontally
scrollable container, so a wide table scrolls inside itself on a narrow screen.
Keep tables to two to four short columns where possible.

## Interactive patterns

A pattern becomes interactive when a `<div class="pattern-parts" markdown>`
with a definition list follows it directly, one term per placeholder:

```html
<pre class="pattern"><code><var data-label="kind of value">type</var> <var data-label="your choice">name</var> = <var data-label="fits the type">value</var>;</code></pre>

<div class="pattern-parts" data-replace-with="a suitable type, variable name, and value" markdown>

<var>type</var>
:   The kind of value the variable holds, such as `int`, `double` or `String`.

</div>
```

- Each placeholder with an explanation becomes a button (`aria-pressed`) in a
  tinted slot. Hover or keyboard focus previews it; selecting it outlines it,
  shows its explanation in the line under the pattern (announced with
  `aria-live`), and highlights the matching part of every example line up to the
  next heading. Selecting it again, or pressing Escape, clears the selection.
- Highlighted parts are bold, underlined text in the placeholder's color on its tint,
  while the rest of the code turns `--jf-muted`. A part inside the highlighted
  one (`value` inside `statements`) takes the outer part's colors.
- Which example code belongs to a placeholder is found in one of two ways:
  - **Explicit mapping (`data-find`)**, for multi-line patterns and anything
    the automatic matching cannot do. See below.
  - **Automatic**, for a one-line pattern with no `data-find` at all: each
    example line is matched against the pattern. The text between placeholders
    must appear as written (any run of spaces matches one or more), and lines
    that do not match are left alone.
- Code in **Your turn** is never highlighted (the search stops at the next
  heading). Only `<span>` wrappers are added, so the copied code is unchanged.
- A `data-label` on a `<var>` in the pattern adds a short label under that
  placeholder (drawn with CSS, so it is not copied). When a placeholder appears
  more than once, put the label on one occurrence only (every occurrence still
  gets it in its accessible name), and choose occurrences
  whose labels do not collide (in the method pattern, the first `type` and the
  second `paramName`). Keep each line of a multi-line pattern to about 36
  characters where Java allows, so its placeholders fit a 390 px phone; the method
  pattern puts each parameter on its own line for this reason. A one-line pattern is larger; a multi-line pattern keeps
  its indentation, is the body text size, and has taller lines to make room for
  the labels. It starts below the copy button at every width, so its first line
  can use the full column; a line still too long scrolls inside the pattern.
- Only selecting or clearing a placeholder is announced to screen readers;
  hover and focus change the explanation line visually only.
- The line under the pattern starts as "Boxed words are placeholders.
  Select a placeholder to highlight its role in the examples." (or "… to see
  what it means." when no example matches). The optional `data-replace-with`
  adds a middle sentence, "Replace them with …", naming what this pattern's
  placeholders stand for.
- Without JavaScript the explanations are a visible two-column list.

Three sections use interactive patterns: "How do I write a program that runs?"
and "How do I write a method?" (explicit mapping) and "How do I declare a
variable?" (automatic).

### Placeholder colors

Each placeholder name belongs to a kind, and each kind has its own soft color.
The same kind has the same color in every pattern, in the explanation line, and
in the highlighted example code, so students can match a placeholder to its part
of the code. Color only reinforces the labels, the explanation line and the
highlight; it never carries meaning on its own.

| Kind (`data-kind`) | Placeholder names | Color | Light: tint / text / outline | Dark: tint / text / outline |
|---|---|---|---|---|
| `return-type` | `returnType` | Amber | `#fbe9c7` / `#8a5a00` / `#ac7b1a` | `#42351f` / `#f2c875` / `#b58a3d` |
| `method-name` | `methodName`, `ClassName` | Blue | `#e6f0fa` / `#1e4f8a` / `#5e89be` | `#20364d` / `#a6cefa` / `#679ccf` |
| `type` | `type` | Green | `#e4f4ef` / `#0e6b57` / `#439680` | `#1d3d35` / `#89dec2` / `#55ac91` |
| `name` | `name`, `paramName` | Purple | `#f6e8f3` / `#8a4f79` / `#a8759a` | `#422b3f` / `#e8b5dc` / `#bb83ac` |
| `statements` | `statements` | Teal | `#e8f5fa` / `#2b6f86` / `#5292a7` | `#1c3c46` / `#a0dceb` / `#69aabd` |
| `value` | `value` | Vermillion | `#fbe7e2` / `#8e4737` / `#b47567` | `#49302c` / `#f4b6a7` / `#cc8879` |

- The hues follow the Okabe–Ito palette. The tokens are `--jf-ph-<kind>-bg`,
  `-fg` and `-line` in `extra.css`.
- The names are mapped to kinds in `KINDS` at the top of the interactive-pattern
  part of `lesson.js`. To color a new placeholder, add its name there with one of
  the six kinds; no CSS is needed. Choose the kind by what the placeholder
  stands for, not by its position. A name that is not listed keeps the accent
  colors (`--jf-accent` on `--jf-hl-soft`).
- States: at rest a tint with a thin outline; hover or keyboard preview turns the
  outline into the text color; selected makes it 2 px. Keyboard focus adds the
  usual 2 px accent outline outside the slot, so focus and selection look
  different. Placeholders are upright in every state.
- Contrast, measured on the rendered pages: placeholder text on its tint is
  4.96:1 or more in the light scheme (amber is the lowest) and 6.9:1 or more in
  the dark scheme. Highlighted example code has the same ratios. The hover and
  selected outlines (the text color) are 4.96:1 or more against both the tint
  and the page, so selection is visible in grayscale too. The resting outline
  is 3.1:1 or more against both the tint and the page in both schemes (light:
  3.10 vermillion to 3.14 amber on the tint; dark: 3.79 or more), so a slot is
  visible without color; it stays lighter (light scheme) or darker (dark scheme)
  than the hover outline. In the light scheme the
  tints are barely lighter or darker than the code background, and highlighted
  code is close in lightness to the dimmed code, so highlighted parts are also
  bold and underlined.
- Some kinds are hard to tell apart for some readers. Teal (`statements`) is
  close to blue in normal vision and, in simulations, close to purple
  (protanopia, deuteranopia) or green (tritanopia); amber and vermillion are
  close under tritanopia, and several tints become nearly identical under every
  simulated deficiency. This is why every
  placeholder keeps its name, many have a label underneath, and selecting one
  explains it in words. Do not remove those cues, and do not put two
  placeholders whose colors are close next to each other without a label.

### Explicit mapping with `data-find`

Put `data-find` on the `<var>` in the explanation list. It lists the example text
for that placeholder, separated by ` | ` (space, bar, space). Each entry is
literal text copied from the examples with the placeholder's part in `[[ ]]`;
the text around it only pins down which occurrence is meant:

```html
<var data-find="int [[a]] | int [[b]] | String [[name]]">paramName</var>
:   A parameter is a variable that receives a value when the method is called. …

<var data-find='[[return (a + b) / 2.0;]] | [[System.out.println("Hello, " + name);]]'>statements</var>
:   These instructions run when the method is called.
```

- Every occurrence of an entry in the examples is highlighted, so `[[int]] a`
  marks the `int` before `a` but no other `int`. Add context until the entry
  means exactly what you intend: `[[a]]` alone would also match the `a` in
  `(a + b)`.
- A word at either end of an entry must be a whole word (`int [[a]]` does not
  match inside `int ab`). Any run of spaces in an entry matches any whitespace,
  including a line break.
- One highlighted part may contain another: `statements` and `value` both
  highlight inside `return (a + b) / 2.0;`.
- A placeholder that does not apply to an example simply has no entry for it
  (`value` has none for the `void` method `greet`). Do not invent one.
- Use single quotes around the attribute when an entry contains `"`. Write `<`
  and `&` as `&lt;` and `&amp;`. An entry cannot contain ` | `. The part starts
  at the first `[[` and ends at the last `]]`, so `[[arr[i]]] = 0` marks
  `arr[i]`; a part that starts with `[` (`int[[[5]]]`) is ambiguous, so add
  context instead.
- An entry without `[[ ]]`, with an empty part, or one that matches nothing,
  logs a console warning and highlights nothing. Check the browser console after
  changing examples.

### Automatic matching (one-line patterns)

What the automatic matching handles (checked against `type name = value;` and
`object.method(arguments);`):

- Works: leading indentation; array and generic types without spaces
  (`int[]`, `List<String>`); values that contain spaces, brackets or operators
  (`new int[5]`, `(int) 9.99`, `(a + b) / 2.0`); a `;` or `//` inside a string
  (`"a;b"`, `"http://x"`); a trailing `//` comment, even one containing `;`.
- Left alone (no highlight, which is safe): lines with extra words such as
  `final int max = 10;`; types with a space (`Map<String, Integer>`); spacing
  that differs from the pattern (`int x=5;` against `name = value`); lines
  without the literal parts (`int x;`, `for (…)`, comment lines).
- Highlighted wrongly, so do not put such lines in the examples of an
  interactive pattern: several declarations on one line (`int a = 1, b = 2;`
  marks `1, b = 2` as the value); a chain of dots against one `.` in the pattern
  (`System.out.println(…)` against `object.method(…)` marks `System` and
  `out.println`); extra spaces inside the brackets or before a `.`, which become
  part of the highlighted placeholder.
- Matching is a regular expression built from the pattern, not a Java parser.
  Check every example line after adding or changing an interactive pattern.

## Multiple choice and progress

A **Predict the output.** exercise can have answer buttons. Under the
exercise's code, add a bulleted list with one item per answer: the output as
inline code (one code span per output line), then, indented by eight spaces, an
explanation that starts with **Correct.** or **Not quite.** Each explanation is
one paragraph. Exactly one answer is correct; its explanation is the answer text that would otherwise go in the
Answers box.

```markdown
    - `3` `3.0` `3.5`

        **Correct.** `3`, then `3.0`, then `3.5`. When both numbers are …
```

- The answers become equal-width buttons, one output line per row. A wrong
  answer is marked "✗ Incorrect", its explanation appears with "Try another
  answer.", and the other answers stay available. The correct answer is marked
  "✓ Correct" and ends the question; earlier wrong picks stay marked.
- Once an answer is picked, a small **Reset** button (drawn like the Answers
  button) appears under the feedback. It clears the marks and the feedback,
  moves focus to the first answer, and takes the question off the progress count.
- Answered buttons use `aria-disabled` rather than `disabled`, so keyboard focus
  stays where it was; their accessible name adds "Incorrect" or "Correct". The
  feedback is in an `aria-live` region.
- A Your turn list with answer buttons gets a progress line under its heading
  ("1 of 3 done"). Exercises without buttons count as done when the Answers box
  is opened: the first `<details>` after the list, before the next heading. If
  there is none, those exercises never count as done. Nothing is stored;
  reloading the page resets it.
- Put "Pick an answer to check it. If it's wrong, you can try again." under the
  Your turn heading.
- Without JavaScript the answers are a plain list with their explanations.
- If the list does not follow the format (two or no **Correct.** answers, a
  single answer, an answer that is not inline code, a verdict that is not the
  first word), it stays a plain list with the explanations visible. Only an
  explanation with more than one paragraph logs a console warning, so check the
  built page after writing a question.

Multiple choice is a learning aid, not the default exercise format: writing or
completing code is usually more valuable for beginners. Only "How do I declare a
variable?" uses it so far; review which other exercises would benefit before
adding more.

## Answers

Answers to the **Your turn** exercises are in a plain `<details>` element with
`<summary>Answers</summary>` (`Answer` when there is only one), closed by
default. It is drawn as a small outlined button in the accent color with a
chevron, and the answers open below it without a frame. Answer text uses the body
text size; the theme's smaller default for `<details>` is overridden. When the
first exercises in a list have answer buttons, number the remaining answers to
match with `<ol start="3" markdown>` and `<li markdown="block">`.

## Responsive behavior

- The site must not scroll sideways at 390 px or wider.
- On narrow screens the tabs and the page's questions move into Material's
  drawer.
- On phones, patterns keep the content margins (other code blocks run to the
  screen edge) and start below Material's copy button, so the button never
  covers pattern text; a one-line interactive pattern gets smaller (its labels
  stay at about 11.5 px, with wider word spacing to keep them apart), and answer buttons
  share the width in three equal columns.
- The question index stacks into one column on narrow screens.
- Code blocks and tables scroll inside their own containers.

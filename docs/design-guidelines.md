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
  checked answers, always with a text label.
- Plain text, tables, and code blocks rather than cards, badges, or decorative
  boxes.
- Content first: no hero section, no animation, no decorative icons.
- Every rule must work in the light and dark schemes and at a 390 px width.

## Typography

| Element | Setting |
|---|---|
| Heading font | Newsreader (serif), loaded with `@import` at the top of `extra.css`; also the site name in the header |
| Body font | Public Sans (`theme.font.text`) |
| Code font | JetBrains Mono (`theme.font.code`) |
| Body text | `0.85rem` (17 px at the default size; Material raises the root font size on screens 1600 px and wider), line height `1.65` |
| H1 | `2.3em`, weight 500, slight negative letter spacing, default text color |
| H2 | `1.5em`, weight 600, `2.2em` space above, thin rule underneath |
| H3 | `1.2em`, weight 600, `1.8em` space above |
| Bold | weight 600 |
| Tables | `0.8em` (slightly larger than the theme default) |
| Inline code | `0.84em` |

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
| `--jf-accent` | `#2b4fa3` | `#8db6ee` | Links, placeholders, primary button, current tab and question, focus outlines, Answers buttons, highlighted pattern parts |
| `--jf-accent-strong` | `#213f86` | `#b8d1f5` | Hover on the primary button, link hover, selected placeholder |
| `--jf-on-accent` | `#ffffff` | `#0f1b2d` | Text on the primary button, and on hovered or focused buttons (Material's `--md-accent-bg-color` is set to it) |
| `--jf-rule` | `#e2e5e3` | `#2f3542` | Thin rules: under H2 and patterns, under the header and tabs, footer border |
| `--jf-rule-strong` | `#858c89` | `#737c90` | Borders of answer buttons and Answers buttons, empty progress ticks |
| `--jf-note-bg` | `#f4f6fa` | `#222733` | Side notes (blockquotes) |
| `--jf-code-border` | `#dce1e6` | `#333a48` | The 1 px border around code blocks |
| `--jf-muted` | `#5b6360` | `#a5adbb` | Code that steps back while a pattern part is highlighted |
| `--jf-hl-soft`, `--jf-hl`, `--jf-hl-line`, `--jf-hl-line-strong` | `#eef2fb`, `#dfe7f8`, `#d3dcf0`, `#b9c7e8` | `#222c42`, `#2b3956`, `#3b4b6e`, `#52668f` | Placeholder slots (rest, hover or selected, outline, hover outline); answer button hover |
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
3.4:1 (light) and 3.8:1 (dark), so the controls are recognizable. Dark-scheme code comments are 4.58:1, the closest to the limit, so do
not lighten them further. In the dark scheme, Material's number and constant code
colors are lightened (`#ec7a6d`, `#a08fe8`) to reach 5.1:1 on the code
background. Keep all text at 4.5:1 or above.

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
- Compiler and runtime output goes in `text` blocks with the real output.
- Code blocks scroll sideways inside their own box when a line is too long; the
  page itself never scrolls. Do not make code or output blocks wrap: wrapping
  would move the caret (`^`) in compiler messages away from the character it
  points to.

### Sections: pattern, example, notes, Your turn

Every "How do I …?" section follows the same order. Sections without a pattern
(the Help sections and "Which kind of variable do I need?") skip step 1.

1. **Pattern:** raw HTML, `<pre class="pattern"><code>…</code></pre>`, with every
   placeholder wrapped in `<var>`. Placeholders are shown in the accent color and
   in italics, so they do not depend on color alone. Escape `<` and `&` inside
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
- **Read it word by word:** the line as a `java` block, then a two-column
  `| Part | Meaning |` table with one row per word or symbol. Do not put the
  whole line in the table header; it wraps badly on phones.
- **Build it step by step:** numbered steps, each a complete `java` block that
  compiles (the last step may point to the finished example at the top of the
  section instead of repeating it), with the new lines marked by `hl_lines` (from
  `pymdownx.highlight`). The lead-in sentence names what was added, so the
  highlight is not the only cue.

Matching **Your turn** exercises: **Put the lines in order.** (lines without
indentation), **Label the parts.** (a block with `linenums="1"`), and **Fill in
the blank.**

## Home page components

- **Buttons:** Codespaces (primary) and local setup, at the top.
- **Question index:** `<div class="question-index">` holds one `<div markdown>`
  per column. Each group is a small uppercase grey H3 followed by a plain list of
  links (no bullets). The columns sit side by side on wide screens
  (`auto-fit`, at least `14rem` each) and stack on narrow ones.
- **Footnote:** `<p class="page-footnote">` for the instructor link, small and grey.

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
- Highlighted parts are bold accent text while the rest of the code turns
  `--jf-muted`. There is no background, so the code does not look selected.
- The examples are matched line by line against the pattern: the text between
  placeholders must appear as written (any run of spaces matches one or more),
  and lines that do not match are left alone. Code in **Your turn** is never
  highlighted. Only `<span>` wrappers are added, so the copied code is unchanged.
- A one-line pattern is larger, and a `data-label` on a `<var>` adds a short label
  under that placeholder (drawn with CSS, so it is not copied). Matching is per
  line, so a multi-line pattern (a class or a method) can explain its
  placeholders but cannot highlight examples; it keeps its layout and has no
  labels.
- Only selecting or clearing a placeholder is announced to screen readers;
  hover and focus change the explanation line visually only.
- The line under the pattern starts as "Italicized words are placeholders.
  Select a placeholder to highlight its role in the examples." (or "… to see
  what it means." when no example matches). The optional `data-replace-with`
  adds a middle sentence, "Replace them with …", naming what this pattern's
  placeholders stand for.
- Without JavaScript the explanations are a visible two-column list.

Only "How do I declare a variable?" uses an interactive pattern so far. Add one
to another section only when the examples follow the pattern line by line.

What the matching handles (checked against `type name = value;` and
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
  screen edge), a one-line interactive pattern gets smaller, and answer buttons
  share the width in three equal columns.
- The question index stacks into one column on narrow screens.
- Code blocks and tables scroll inside their own containers.

# Design guidelines

This page describes the design the course website actually uses. It is for
contributors and is not published as a student page (`mkdocs.yml` excludes it).
Read it before you change shared styling, and update it when a project-wide
design decision changes.

The site uses MkDocs with the Material theme. Everything below is either a theme
setting in `mkdocs.yml` or a rule in `docs/stylesheets/extra.css`. Prefer the
theme's own components; add CSS only when the theme cannot do what is needed.

## Principles

- One accent color, neutral everything else.
- Plain text, tables, and code blocks rather than cards, badges, or decorative
  boxes.
- Content first: no hero section, no animation, no decorative icons.
- Every rule must work in the light and dark schemes and at a 390 px width.

## Typography

| Element | Setting |
|---|---|
| Body font | Source Sans 3 (`theme.font.text`) |
| Code font | JetBrains Mono (`theme.font.code`) |
| Body text | `0.85rem` (17 px at the default size; Material raises the root font size on screens 1600 px and wider), line height `1.65` |
| H1 | `1.9em`, weight 700, slight negative letter spacing, default text color |
| H2 | `1.35em`, weight 650, `2.2em` space above, thin rule underneath |
| H3 | `1.08em`, weight 650, `1.8em` space above |
| Bold | weight 650 |
| Tables | `0.8em` (slightly larger than the theme default) |
| Inline code | `0.84em` |

Each page has one H1 (the group name, such as "Basics"). Most H2s are
"How do I …?" questions; Help uses error-focused questions and Practice uses
numbered exercises. Every H2 has a short explicit id (`## How do I declare a
variable? { #variable }`). H3 is used for **Your turn**, for the parts described under "Syntax helps"
below, for the question-index
groups and "Using the terminal" on the home page, for "Find the line" on Help,
and for the worked-example steps on Practice. The page contents list shows H2
only (`toc_depth: 2`).

The reading width is Material's default content column. Do not widen it.

## Color

Colors are set as tokens on `body[data-md-color-scheme]`:

| Token | Light (`default`) | Dark (`slate`) | Used for |
|---|---|---|---|
| `--jf-accent` | `#1d4f91` | `#8db6ee` | Links, placeholders in patterns, primary button, active navigation item, focus outlines, answer boxes |
| `--jf-accent-strong` | `#133864` | `#b8d1f5` | Hover on the primary button and link hover |
| `--jf-on-accent` | `#ffffff` | `#0f1b2d` | Text on the primary button, and on hovered or focused buttons (Material's `--md-accent-bg-color` is set to it) |
| `--jf-rule` | `#e3e6ec` | `#2f3542` | Thin rules: under H2, header and footer borders |
| `--jf-note-bg` | `#f4f6fa` | `#222733` | Side notes (blockquotes) and answer summaries |

In the dark scheme the header and footer use the page background instead of a
colored bar. The palette toggle in the header cycles through the system
setting, light, and dark.

Contrast measured on the rendered site: links 8.1:1 (light) and 7.7:1 (dark; 6.8:1
on code backgrounds); secondary
grey text 6.25:1 (light) and 4.9:1 (dark); code comments 6.0:1 (light) and 4.58:1
(dark). Keep all text at 4.5:1 or above. Dark-scheme code comments are the closest
to the limit, so do not lighten them further. In the dark scheme, Material's
number and constant code colors are lightened (`#ec7a6d`, `#a08fe8`) to reach
5.1:1 on the code background.

Do not use color alone to show meaning; pair it with text.

## Spacing

Spacing comes from the theme's defaults plus the heading margins above. Avoid adding margins to individual
pages; change the shared rule instead.

## Links, buttons, navigation, and focus

- Links use `--jf-accent` and change to `--jf-accent-strong` on hover.
- Buttons (`.md-button`) are outlined in the accent color; the primary button
  (`.md-button--primary`) is filled. Only the home page uses buttons.
- The left navigation lists the six pages; the current page is in bold accent. Navigation features in use: `navigation.sections`,
  `navigation.footer` (previous and next links), `navigation.top`, and
  `toc.follow`.
- Links, navigation, footer links, header buttons, buttons, **Answers**
  summaries, the copy button, and **Back to top** have a 2 px solid accent
  `:focus-visible` outline with a 2 px offset. The palette toggle and search
  field use the theme's own focus styles.

## Code

- Code blocks use Material's highlighting and copy button (`content.code.copy`).
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
   placeholders stand out. Material still adds its copy button.
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

## Answers

Answers to the **Your turn** exercises are in a plain `<details>` element with
`<summary>Answers</summary>`, closed by default. The box uses the accent color
for its border and icons and `--jf-note-bg` for the summary row. Answer text
uses the body text size; the theme's smaller default for `<details>` is
overridden.

## Responsive behavior

- The site must not scroll sideways at 390 px or wider.
- On narrow screens the navigation moves into Material's drawer, which also holds
  the page's table of contents.
- The question index stacks into one column on narrow screens.
- Code blocks and tables scroll inside their own containers.

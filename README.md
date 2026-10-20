# Java Foundations

"Java: How do I …?" is a Java syntax cheat sheet for CMPINF 0401. Each section
answers one question with a pattern to copy, an example, and short exercises.
Nine practice exercises put the topics together in small programs, with tests
that check your code.

**Website:** <https://pitt-0401.github.io/java-foundations/>. Start there.

[![Open in GitHub Codespaces](https://github.com/codespaces/badge.svg)](https://codespaces.new/pitt-0401/java-foundations)

## Contents

| Page | Questions |
|---|---|
| [Basics](docs/basics.md) | Write a program, declare a variable, write and call a method, make a decision, repeat something, read input, print output |
| [Classes and objects](docs/classes-and-objects.md) | Classes, constructors, objects, getters and setters, `toString`, kinds of variables, `static`, `this`, comparing values, avoiding crashes |
| [Arrays](docs/arrays.md) | Create an array, loop through an array, 2D arrays |
| [Help](docs/help.md) | Compile errors, runtime errors, common mistakes |
| [Practice](docs/practice.md) | Nine practice exercises, how to approach a problem, and a worked example |

## Run the practice tests

Open the repository in **GitHub Codespaces** with the button above, or install
JDK 21, Maven, and VS Code (the [website](https://pitt-0401.github.io/java-foundations/#setup) explains how).
Then, from this folder (the one that contains `pom.xml`):

```bash
mvn test -Dtest=TipCalculatorTest   # the tests for one exercise
mvn test                            # every exercise
```

Tests for exercises you haven't done yet will fail. That's expected.

## Folders

```text
java-foundations/
├── docs/          <- the website pages
├── examples/      <- runnable examples (car/, playlist/)
├── exercises/     <- your work: one file per practice exercise
├── tests/         <- JUnit 5 tests for the exercises
├── solutions/     <- reference solutions
└── archive/       <- earlier course units, kept for later versions
```

## Teaching or contributing

The [instructor guide](INSTRUCTORS.md) covers checking the solutions and how the
website is built and published.

## License

[MIT](LICENSE)

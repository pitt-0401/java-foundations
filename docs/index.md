# Java: How do I …?

This page serves as a Java syntax cheat sheet. Each section includes a topic, explains the syntax, and short exercises to help you practice it.

If you are new to programming, start at the beginning. You can also use the list
below to find the topic you need.

[Set up on your own computer](#setup){ .md-button .md-button--primary }

## How do I …? { #questions }

<div class="question-index" markdown>

<div markdown>

### Basics

- [Write a program that runs](basics.md#program)
- [Declare a variable](basics.md#variable)
- [Write a method](basics.md#method)
- [Call a method](basics.md#call-method)
- [Make a decision](basics.md#decision)
- [Repeat something](basics.md#repeat)
- [Read input](basics.md#input)
- [Print output](basics.md#output)

</div>

<div markdown>

### Classes and objects

- [Declare a class](classes-and-objects.md#class)
- [Write a constructor](classes-and-objects.md#constructor)
- [Create and use an object](classes-and-objects.md#object)
- [Write getters and setters](classes-and-objects.md#getters-setters)
- [Print an object](classes-and-objects.md#print-object)
- [Pick the right variable](classes-and-objects.md#which-variable)
- [Share a value (`static`)](classes-and-objects.md#static)
- [Use `this`](classes-and-objects.md#this)
- [Compare values](classes-and-objects.md#compare)
- [Avoid crashes](classes-and-objects.md#avoid-crashes)

</div>

</div>

In a pattern, a boxed word such as <var>ClassName</var> is a placeholder:
replace it with your own name, type, or value. 
Type everything else exactly as it is shown.

## Java Setup { #setup }

You need >= Java 21 (the JDK: the compiler and the runtime that runs Java
programs), Maven (a tool that runs the automated tests), and a code editor.

### Install on your own computer

1. Download the course files: on the repository page, choose
   **Code > Download ZIP** and unzip it, or clone the repository with Git.
2. Install JDK 21 or newer, for example Eclipse Temurin from
   <https://adoptium.net/>. On Windows, select the installer option that sets
   `JAVA_HOME`.
3. Install Maven: <https://maven.apache.org/install.html>.
4. Install VS Code with the Extension Pack for Java:
   <https://code.visualstudio.com/docs/java/java-tutorial>.
5. In VS Code, choose **File > Open Folder** and open the course folder, the one
   that contains the file `pom.xml`.

To check that everything works, open a terminal (in VS Code,
**Terminal > New Terminal**) and run:

```bash
java -version
mvn -v
```

Both should show a Java version 21 or newer; in the `mvn -v` output, search for the
`Java version:` line. If a command is not found, close and reopen the terminal,
then check the installation steps again.

### Using the terminal

A terminal session works inside one folder; commands look for files in that
folder. The following commands are enough for this page:

| Command | Purpose |
|---|---|
| `pwd` | Shows which folder you are in |
| `ls` | Lists the files and folders in it |
| `cd examples` | Moves into the `examples` folder |
| `cd ..` | Moves up one folder |

Run `mvn` commands from the course folder (the one that contains `pom.xml`).

## How to practice

1. For the best learning experience, read a section and type its example yourself instead of copying it. Then do
   the **Your turn** exercises before you check the answers.
2. Compile and run after every few lines. One error at a time is easy to fix;
   twenty at once are typically not.

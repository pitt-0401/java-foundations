# Java: How do I …?

This page serves as a Java syntax cheat sheet. Each section includes a topic, explains the syntax, and short exercises to help you practice it.
[practice exercises](practice.md) are small
programs that combine several topics, with tests that check your code.

If you are new to programming, start at the beginning. You can also use the list
below to find the topic you need.

[Open in GitHub Codespaces](https://codespaces.new/pitt-0401/java-foundations){ .md-button .md-button--primary }
[Set up on your own computer](#setup){ .md-button }

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

<div markdown>

### Arrays

- [Create an array](arrays.md#create-array)
- [Loop through an array](arrays.md#loop-array)
- [Use a 2D array](arrays.md#2d-array)

### Help

- [Read a compile error](help.md#compile-errors)
- [Read a runtime error](help.md#runtime-errors)
- [Avoid common mistakes](help.md#common-mistakes)

### Practice

- [Practice exercises](practice.md)
- [Approach a problem](practice.md#approach)

</div>

</div>

In a pattern, a boxed word such as <var>ClassName</var> is a placeholder:
replace it with your own name, type, or value. 
Type everything else exactly as it is shown.

## Java Setup { #setup }

You need >= Java 21 (the JDK: the compiler and the runtime that runs Java
programs), Maven (a tool that runs the automated tests), and a code editor.
You can use them in the browser or install them yourself.

### OPTION 1: Browser
**GitHub Codespaces** runs everything in your browser, which means you do not need to
install anything. You do need a GitHub account that you are logged in in your browser session. Ideally, also sign up for the student pack via GitHub Education. Then select **Open in GitHub Codespaces** (button above) and wait 1-2 minutes until setup is complete. 
GitHub Codespaces includes a limited number of free hours each month; when you finish, click **Codespaces** in the bottom-left corner and choose **Stop Current Codespace**.

### OPTION 2: Your own computer

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
   the **Your turn** exercises before you check the the answers.
2. Compile and run after every few lines. One error at a time is easy to fix;
   twenty at once are typically not.
3. Once you feel comfortable with a few sections, try a
   [practice exercise](practice.md) that practices the topics.
4. When you see an error message, look it up on the [Help](help.md) page.

<p class="page-footnote">Teaching with this site? The
<a href="https://github.com/pitt-0401/java-foundations/blob/main/INSTRUCTORS.md">instructor
guide</a> explains how to check the solutions and publish the site.</p>

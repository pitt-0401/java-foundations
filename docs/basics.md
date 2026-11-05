# Basics

This page covers the syntax that most Java programs use: running a program, variables, methods, decisions, loops, input and output.

## How do I write a program that runs? { #program }

<pre class="pattern"><code>public class <var data-label="class name">ClassName</var> {
    public static void main(String[] args) {
        <var data-label="code to run">statements</var>
    }
}</code></pre>

<div class="pattern-parts" markdown>

<var data-find="class [[CarRace]]">ClassName</var>
:   The name of the class, written in PascalCase, e.g., `CarRace`. A public class must be saved in a file with the same name: `CarRace.java`.

<var data-find='[[System.out.println("Ready!");]]'>statements</var>
:   The instructions inside `main`. The `main` method is the entry point of a Java program: its statements are executed sequentially, top to bottom.

</div>

Example, in a file named `CarRace.java`:

```java
public class CarRace {
    public static void main(String[] args) {
        System.out.println("Ready!");
    }
}
```

Compile, then run it in the terminal:

```bash
javac CarRace.java
java CarRace
```

- The file name must match the class name: `CarRace.java`
- Simple statements (e.g., declarations, assignments, method calls) end with `;`; lines that end with `{` or `}` do not
- Every opening `{` must have a matching closing `}`
- Compile often, as you code

<p class="lectures">Lectures 02, 04</p>

### Your turn

1. **Find the bug.** This code is saved in a file named `Hello.java`. Why does `javac Hello.java` fail?

    ```java
    public class Greeting {
        public static void main(String[] args) {
            System.out.println("Hello");
        }
    }
    ```

2. **Find the bug.** What is missing?

    ```java
    System.out.println("Hello")
    ```

3. **Write it.** Write a program in `Countdown.java` that prints `3`, `2`, `1` and `Go` on four separate lines.

<details markdown>
<summary>Answers</summary>

1. The file must be named `Greeting.java`, because the class is named `Greeting`
    - The compiler says:

    ```text
    Hello.java:1: error: class Greeting is public, should be declared in a file named Greeting.java
    ```

2. The `;` at the end
    - The compiler reports `error: ';' expected`
3. One solution:

    ```java
    public class Countdown {
        public static void main(String[] args) {
            System.out.println(3);
            System.out.println(2);
            System.out.println(1);
            System.out.println("Go");
        }
    }
    ```

</details>

## How do I declare a variable? { #variable }

<pre class="pattern"><code><var data-label="kind of value">type</var> <var data-label="your choice">name</var> = <var data-label="fits the type">value</var>;</code></pre>

<div class="pattern-parts" data-replace-with="a suitable type, variable name, and value" markdown>

<var>type</var>
:   The data type of the variable, e.g., `int`, `double`, or `String`. It defines the kind of data the variable can store.

<var>name</var>
:   The variable name, written in camelCase, e.g., `isOpen`.

<var>value</var>
:   The initial value. It must be compatible with the variable's type: `9.99` can be assigned to a `double`, but assigning it to an `int` requires a cast, e.g., `(int) 9.99`, which results in `9`.

</div>

Examples:

```java
int seats = 40;         // whole number
double price = 9.99;    // decimal
boolean isOpen = true;  // true or false
char grade = 'A';       // one character: ' '
String name = "Yoda";   // text: " "
float g = 9.81f;        // float needs f
long big = 9000000000L; // long needs L
```

Convert between types:

```java
// int to double: automatic (widening)
double d = 42;
// double to int: cast, gives 9
int i = (int) 9.99;
```

- Write names in **camelCase**: `numberOfSeats`
- Declare a variable before you use it
- The type cannot change later
- A cast cuts off the decimal part; it does not round

<p class="lectures">Lectures 02, 03, 04</p>

### Your turn

1. **Code tracing.** What does this print?

    ```java
    int a = 7 / 2;
    double b = 7 / 2;
    double c = 7 / 2.0;
    System.out.println(a);
    System.out.println(b);
    System.out.println(c);
    ```

    - `3.5` `3.5` `3.5`

        **Not quite.** `7 / 2` divides two `int` values, so the result is `3` for both `a` and `b`. Storing it in a `double` does not bring the `.5` back.

    - `3` `3` `3.5`

        **Not quite.** `b` is a `double`, so `println` shows it with a decimal point: `3.0`, not `3`.

    - `3` `3.0` `3.5`

        **Correct.** `3`, then `3.0`, then `3.5`. When both numbers are `int`, `/` does integer division and drops the remainder, even if you store the result in a `double`. Making one number a `double` (`2.0`) keeps the decimal part.


2. **Code tracing.** What does this print?

    ```java
    System.out.println((int) 2.99);
    ```

    - `3`

        **Not quite.** Casting `2.99` to `int` truncates it: Java drops the fractional part `.99` and keeps `2`. Nothing is rounded up.

    - `2`

        **Correct.** `2`. The cast cuts off `.99`; it does not round up.

    - `2.99`

        **Not quite.** `(int)` turns `2.99` into an `int` before it is printed, so the decimal part is gone.


3. **Find the bug.** Why does this line not compile?

    ```java
    int seats = 40.5;
    ```

<details markdown>
<summary>Answer</summary>

<ol start="3" markdown>
<li markdown="block">

`40.5` is a `double`, and an `int` cannot hold the `.5`, so Java will not convert it automatically:

```text
error: incompatible types: possible lossy conversion from double to int
```

- Use `double seats = 40.5;`, or write `(int) 40.5` if you want `40`

</li>
</ol>

</details>

## How do I write a method? { #method }

<pre class="pattern"><code>public static <var data-label="return type">returnType</var> <var data-label="method name">methodName</var>(
        <var data-label="parameter type">type</var> <var>paramName</var>,
        <var>type</var> <var data-label="parameter name">paramName</var>) {
    <var data-label="method body">statements</var>
    return <var data-label="returned value">value</var>;  // omit for void methods
}</code></pre>

<div class="pattern-parts" markdown>

<var data-find="static [[double]] | static [[void]]">returnType</var>
:   The type of value the method returns, e.g., `int` or `double`. `void` means that the method does not return anything.

<var data-find="[[average]]( | [[greet]](">methodName</var>
:   A descriptive method name, written in camelCase, e.g., `average` or `greet`.

<var data-find="[[int]] a | [[int]] b | [[String]] name">type</var>
:   The data type of the parameter. It defines which values can be passed to the method.

<var data-find="int [[a]] | int [[b]] | String [[name]]">paramName</var>
:   A parameter acts as a placeholder for the value that is passed in when the method is called. Choose a meaningful name, e.g., `a`, `b`, or `name`.

<var data-find='[[return (a + b) / 2.0;]] | [[System.out.println("Hello, " + name);]]'>statements</var>
:   The method body: the statements that are executed when the method is called. It can include a `return` statement.

<var data-find="return [[(a + b) / 2.0]]">value</var>
:   The value the method returns. It must match the declared return type. `void` methods do not return a value.

</div>

The pattern is simplified: a method does not need other statements before `return` (in `average`, the return statement is the whole body). In a `void` method, leave out `return value;`.

Examples:

```java
public static double average(int a, int b) {
    return (a + b) / 2.0;
}

public static void greet(String name) {
    System.out.println("Hello, " + name);
}
```

- The returned value must match the return type
- Methods that work on an object's fields leave out `static`; see [Declare a class](classes-and-objects.md#class)

### What each word means { #method-words }

The first line is the method header:

```java
public static double average(int a, int b) {
```

| Part | Meaning |
|---|---|
| `public` | Access modifier: the method can be accessed from anywhere, e.g., from other classes. |
| `static` | The method belongs to the class (not to an object), so `main` can call it without creating an object first. Methods that work on an object's fields leave it out. |
| `double` | The return type: the type of value the method returns. `void` means it returns nothing. |
| `average` | The method name, written in camelCase. |
| `(int a, int b)` | The parameters, each with a type and a name. No parameters: `()`. |
| `{` | Starts the method body, which ends at the matching `}`. |

<p class="lectures">Lecture 03</p>

### Your turn

1. **Find the bug.** This method should give back the square of `x`. Why does it not compile?

    ```java
    public static int square(int x) {
        System.out.println(x * x);
    }
    ```

2. **Write it.** Write a method `isEven` that takes an `int` and returns `true` if the number is even. Hint: `n % 2` is the remainder after dividing by 2.

<details markdown>
<summary>Answers</summary>

1. The return type is `int`, so the method must `return` an `int`
    - Printing a value is not the same as returning it
    - The compiler reports `error: missing return statement`
    - Fix it with `return x * x;`
2. One solution:

    ```java
    public static boolean isEven(int n) {
        return n % 2 == 0;
    }
    ```

</details>

## How do I call a method? { #call-method }

| Situation | Pattern | Example |
|---|---|---|
| static, same class | <code><var>methodName</var>(<var>args</var>);</code> | `greet("Taylor");` |
| static, other class | <code><var>ClassName</var>.<var>methodName</var>(<var>args</var>);</code> | `Car.getTotalCars();` |
| on an object | <code><var>object</var>.<var>methodName</var>(<var>args</var>);</code> | `car1.drive();` |
| keep the result | <code><var>type</var> <var>var</var> = <var>methodName</var>(<var>args</var>);</code> | `double avg = average(4, 7);` |

Parameter vs. argument:

```java
// parameter: name
public static void greet(String name)
// argument: "Taylor"
greet("Taylor");
```

- The **parameter** is the placeholder in the method header; the **argument** is the actual value passed in when the method is called
- Arguments must match the parameters in number, type and order
- Calling a method is also known as invoking it
- For what happens to the argument inside the method, see [Use a parameter](#parameter)
- Non-static methods need an object first; see [Create and use an object](classes-and-objects.md#object)

<p class="lectures">Lectures 03, 07, 09</p>

### Your turn

These use `average` and `greet` from [How do I write a method?](#method).

1. **Code tracing.** What does this print?

    ```java
    double avg = average(4, 7);
    System.out.println(avg);
    ```

2. **Find the bug.**

    ```java
    double avg = average(4);
    ```

3. **What happens?**

    ```java
    String message = greet("Taylor");
    ```

<details markdown>
<summary>Answers</summary>

1. `5.5`
    - The method computes `(4 + 7) / 2.0`, which is `11 / 2.0`
2. `average` has two parameters, so you must pass two arguments
    - The compiler says `method average in class ... cannot be applied to given types` and adds `reason: actual and formal argument lists differ in length`
3. It does not compile
    - `greet` is `void`, so there is no result to store: `error: incompatible types: void cannot be converted to String`
    - Call it on its own line instead: `greet("Taylor");`

</details>

## How do I use a parameter inside a method? { #parameter }

When a method is called, the value of each argument is copied into its
parameter (pass-by-value). Inside the method, the parameter is a local
variable: it can only be accessed inside the scope of the method, between its
curly braces, `{` and `}`.

Example:

```java
public static int addBonus(int points) {
    // changes only the copy
    points = points + 10;
    return points;
}

public static void main(String[] args) {
    int score = 50;
    int total = addBonus(score);
    System.out.println(score);  // 50
    System.out.println(total);  // 60
}
```

- `addBonus(score)` copies the value of `score` (`50`) into the parameter `points`
- The method changes `points`, not `score`: changes are not reflected in the original
- To get a new value back, `return` it and store the result: `int total = addBonus(score);`
- The names do not need to match: the argument `score` and the parameter `points` are different variables
- `points` exists only inside `addBonus`, and `score` exists only inside `main`
    - Using `points` in `main` gives `error: cannot find symbol`
- For an array or an object, the copied value is a reference to the same object, so the method can change its elements or fields; see [Compare values](classes-and-objects.md#compare)

<p class="lectures">Lectures 02, 03, 09</p>

### Your turn

1. **Code tracing.** What does this print?

    ```java
    public static void reset(int x) {
        x = 0;
    }

    // in main:
    int n = 5;
    reset(n);
    System.out.println(n);
    ```

    - `0`

        **Not quite.** `reset(n)` copies the value of `n` into `x`. Setting `x` to `0` changes only the copy, so `n` is still `5`.

    - `5`

        **Correct.** `5`. `x` is a copy of `n`, so changing `x` inside `reset` does not change `n`. To change `n`, `reset` would have to return the new value, and `main` would have to store it: `n = reset(n);`.

2. **Find the bug.** Why does this not compile?

    ```java
    public static void main(String[] args) {
        int total = 0;
        addPoints(5);
        System.out.println(total);
    }

    public static void addPoints(int amount) {
        total = total + amount;
    }
    ```

<details markdown>
<summary>Answers</summary>

<ol start="2" markdown>
<li markdown="block">

`total` is a local variable of `main`, so it is not defined in the scope of `addPoints`

- The compiler reports `error: cannot find symbol`
- Pass the value in and return the result:

```java
public static int addPoints(
        int total, int amount) {
    return total + amount;
}
```

- In `main`: `total = addPoints(total, 5);`

</li>
</ol>

</details>

## How do I make a decision? { #decision }

<pre class="pattern"><code>if (<var data-label="true or false">condition</var>) {
    <var data-label="code block">statements</var>
} else if (<var>condition</var>) {
    <var>statements</var>
} else {
    <var>statements</var>
}</code></pre>

<div class="pattern-parts" data-replace-with="conditions and the statements to run" markdown>

<var data-find="if ([[score >= 90]]) | if ([[score >= 80]])">condition</var>
:   An expression that evaluates to a boolean (`true` or `false`), e.g., `score >= 90`. The parentheses are required.

<var data-find="[[grade = 'A';]] | [[grade = 'B';]] | [[grade = 'C';]]">statements</var>
:   The code block that is executed when its condition is `true`. The `else` block runs when no condition is `true`.

</div>

Example:

```java
if (score >= 90) {
    grade = 'A';
} else if (score >= 80) {
    grade = 'B';
} else {
    grade = 'C';
}
```

| Compare (relational operators) | Combine (logical operators) |
|---|---|
| `==` `!=` `<` `>` `<=` `>=` | `&&` and, <code>&#124;&#124;</code> or, `!` not |

- The condition must evaluate to a boolean (`true` or `false`)
- The first condition that evaluates to `true` triggers its code block; no further conditions are checked
- For Strings, use `.equals()`, not `==`; see [Compare values](classes-and-objects.md#compare)

<p class="lectures">Lecture 05</p>

### Your turn

1. **Code tracing.** What does this print?

    ```java
    int score = 95;
    if (score >= 80) {
        System.out.println("B");
    } else if (score >= 90) {
        System.out.println("A");
    }
    ```

2. **Find the bug.**

    ```java
    if (score = 90) {
        System.out.println("Perfect");
    }
    ```

3. **Fill in the blank.** Complete the condition so it is `true` when `answer` holds the text `yes`.

    ```java
    if (answer.______("yes")) {
    ```

<details markdown>
<summary>Answers</summary>

1. `B`
    - `95 >= 80` is true, so the first branch runs and Java never checks `score >= 90`
    - Put the strictest condition first
2. `=` assigns a value; `==` compares
    - Write `score == 90`
    - With `=`, the compiler reports `error: incompatible types: int cannot be converted to boolean`
3. `equals`
    - With `==`, Java checks whether both sides are the same String object, which can be `false` even when the text matches

</details>

## How do I repeat something? { #repeat }

When the number of iterations is known in advance, use `for`:

<pre class="pattern"><code>for (int i = 0; i &lt; <var data-label="how many times">count</var>; i++) {
    <var data-label="loop body">statements</var>
}</code></pre>

<div class="pattern-parts" data-replace-with="the number of iterations and the statements to repeat" markdown>

<var data-find="i < [[5]];">count</var>
:   The number of iterations, e.g., `5`. `i` starts at 0, and the loop stops when `i` reaches `count`, so `i` goes from 0 to `count - 1`.

<var data-find="[[System.out.println(i);]]">statements</var>
:   The loop body: the statements that are executed in each iteration. They can use `i`.

</div>

Example:

```java
// prints 0 1 2 3 4, one per line
for (int i = 0; i < 5; i++) {
    System.out.println(i);
}
```

To visit every element of an array, use for-each (see [Loop through an array](arrays.md#loop-array)):

<pre class="pattern"><code>for (<var>type</var> <var data-label="each value">element</var> : <var data-label="array name">array</var>) {
    <var>statements</var>
}</code></pre>

<div class="pattern-parts" data-replace-with="the element type, a variable name, and the array" markdown>

<var data-find="for ([[int]] score">type</var>
:   The type of the elements, e.g., `int` for an `int[]` array.

<var data-find="int [[score]] : | println([[score]])">element</var>
:   A variable that holds one element in each iteration, e.g., `score`. Choose a meaningful name.

<var data-find=": [[scores]])">array</var>
:   The array to loop through, e.g., `scores`.

<var data-find="[[System.out.println(score);]]">statements</var>
:   The loop body: the statements that are executed once for each element.

</div>

Example:

```java
// prints 90, 75, 82
int[] scores = {90, 75, 82};
for (int score : scores) {
    System.out.println(score);
}
```

To repeat as long as a condition is true, use `while`:

<pre class="pattern"><code>while (<var data-label="true or false">condition</var>) {
    <var data-label="loop body">statements</var>  // must change condition
}</code></pre>

<div class="pattern-parts" data-replace-with="a condition and the statements to repeat" markdown>

<var data-find="while ([[seconds > 0]])">condition</var>
:   An expression that evaluates to a boolean, e.g., `seconds > 0`. It is checked before each iteration; the loop stops when it is `false`.

<var data-find="[[System.out.println(seconds); seconds--;]]">statements</var>
:   The loop body. It must change something the condition depends on (here `seconds--`); otherwise you get an infinite loop.

</div>

Example:

```java
// prints 3 2 1
int seconds = 3;
while (seconds > 0) {
    System.out.println(seconds);
    seconds--;
}
```

To run the body at least once, use `do`-`while`:

<pre class="pattern"><code>do {
    <var data-label="loop body">statements</var>
} while (<var data-label="true or false">condition</var>);  // note the ;</code></pre>

<div class="pattern-parts" data-replace-with="the statements to repeat and a condition" markdown>

<var data-find="[[System.out.println(n); n++;]]">statements</var>
:   The loop body. It is executed once before the condition is checked, so it always runs at least once.

<var data-find="} while ([[n <= 3]])">condition</var>
:   An expression that evaluates to a boolean, e.g., `n <= 3`. It is checked after each iteration; the loop repeats while it is `true`.

</div>

Example:

```java
// prints 1 2 3
int n = 1;
do {
    System.out.println(n);
    n++;
} while (n <= 3);
```

- **`break`** exits the loop
- **`continue`** skips to the next iteration
- `i++` increments `i` by 1, `i--` decrements it by 1
- `total += x` is short for `total = total + x`

<p class="lectures">Lecture 06</p>

### Your turn

1. **Code tracing.** How many times does this print `Hi`?

    ```java
    for (int i = 0; i <= 5; i++) {
        System.out.println("Hi");
    }
    ```

2. **What happens?**

    ```java
    int n = 3;
    while (n > 0) {
        System.out.println(n);
    }
    ```

3. **Code tracing.** What does this print?

    ```java
    int x = 10;
    do {
        System.out.println(x);
        x++;
    } while (x < 5);
    ```

<details markdown>
<summary>Answers</summary>

1. 6 times, for `i` = 0, 1, 2, 3, 4 and 5
    - Starting at `0` with `<=` runs one extra time
    - Use `i < 5` for 5 times
2. It prints `3` forever: an infinite loop
    - Nothing in the loop changes `n`, so the condition stays true
    - Add `n--;` inside the loop
    - Stop a running program with `Ctrl+C`
3. `10`
    - A `do`-`while` loop runs its body once before it checks the condition, even if the condition is false from the start

</details>

## How do I read input from the keyboard? { #input }

<pre class="pattern"><code>import java.util.Scanner;  // top of file

Scanner scanner = new Scanner(System.in);
<var>type</var> <var data-label="your choice">var</var> = scanner.<var data-label="see the table">nextXxx</var>();
scanner.close();  // when done</code></pre>

<div class="pattern-parts" data-replace-with="a type, a variable name, and the matching method" markdown>

<var data-find="[[int]] age =">type</var>
:   The data type of the value you read, e.g., `int`. It must match the method: `nextInt()` returns an `int`.

<var data-find="int [[age]] =">var</var>
:   The variable name, written in camelCase, e.g., `age`. It stores the value the user typed.

<var data-find="= scanner.[[nextInt]]">nextXxx</var>
:   The method that reads the next input, e.g., `nextInt`. Pick it from the table below.

</div>

| I want to read … | Method |
|---|---|
| one word | `next()` |
| a whole line | `nextLine()` |
| an int | `nextInt()` |
| a double | `nextDouble()` |
| a boolean | `nextBoolean()` |

Re-prompt until the user types a number:

```java
while (!scanner.hasNextInt()) {
    scanner.next();  // discard the bad input
    System.out.print("Age: ");
}
int age = scanner.nextInt();
```

**Trap:** if you call `nextLine()` after `nextInt()`, you need to call one extra `nextLine()` first. `nextInt()` reads only the number and leaves the newline in the input; the next `nextLine()` consumes that leftover newline and returns an empty String.

<p class="lectures">Lecture 06</p>

### Your turn

1. **What happens?** The user types `20`, presses Enter, then types `Sam Lee`.

    ```java
    System.out.print("Age: ");
    int age = scanner.nextInt();
    System.out.print("Name: ");
    String name = scanner.nextLine();
    ```

2. **Fill in the blank.** The user types `Taylor Swift`. Which method stores both words in `fullName`?

    ```java
    String fullName = scanner.______();
    ```

<details markdown>
<summary>Answers</summary>

1. `name` is an empty String, and the program does not wait for the name
    - `nextLine()` reads the rest of the line after `20`, which is empty
    - Add `scanner.nextLine();` right after `nextInt()`
2. `nextLine`
    - `next()` reads one word, so it would store only `Taylor`

</details>

## How do I print output? { #output }

<pre class="pattern"><code>System.out.println(<var data-label="text or number">value</var>); // then new line
System.out.print(<var>value</var>);   // stay on line
System.out.printf("<var data-label="text with %">format</var>", <var data-label="in order">values</var>);</code></pre>

<div class="pattern-parts" data-replace-with="the value to print, or a format and its values" markdown>

<var data-find='println([["Age: " + age]])'>value</var>
:   The value to print, e.g., `"Age: " + age`. It can be a String, a number, or a combination joined with `+`.

<var data-find='printf("[[%s is %d%n]]" | printf("[[%.2f%n]]"'>format</var>
:   The text to print, with a `printf` placeholder (e.g., `%d`) where each value goes. See the table below.

<var data-find='", [[name, age]]) | ", [[3.14159]])'>values</var>
:   The values for the placeholders, in the same order, separated by commas. `%n` does not take a value.

</div>

Examples:

```java
System.out.println("Age: " + age);
System.out.printf("%s is %d%n", name, age);
System.out.printf("%.2f%n", 3.14159); // 3.14
```

| `printf` placeholder | Meaning |
|---|---|
| `%d` | int |
| `%f` | double |
| `%.2f` | 2 decimals |
| `%s` | String |
| `%n` | new line |

**Trap:** `"Sum: " + 5 + 4` prints `Sum: 54`. Java evaluates left to right: `"Sum: " + 5` becomes the String `"Sum: 5"`, then `4` is joined to it as text (String concatenation). Use parentheses, `"Sum: " + (5 + 4)`, to get `Sum: 9`.

<p class="lectures">Lectures 02, 05, 08</p>

### Your turn

1. **Code tracing.** What does this print?

    ```java
    System.out.println("Sum: " + 5 + 4);
    System.out.println(5 + 4 + " total");
    ```

2. **Code tracing.** What does this print?

    ```java
    System.out.print("A");
    System.out.print("B");
    System.out.println("C");
    System.out.print("D");
    ```

3. **Fill in the blank.** Print `Total: 7.50` and end the line.

    ```java
    System.out.printf("Total: ______", 7.5);
    ```

<details markdown>
<summary>Answers</summary>

1. `Sum: 54`, then `9 total`
    - In the second line, `5 + 4` comes first and both are numbers, so Java adds them before it reaches the String
2. `ABC` on the first line and `D` on the second
    - Only `println` ends the line
3. `%.2f%n`
    - `%.2f` shows the number with 2 decimals, and `%n` ends the line

</details>

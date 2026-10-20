# Basics

The syntax almost every program uses: running a program, variables, methods, decisions, loops, input and output.

## How do I write a program that runs? { #program }

<pre class="pattern"><code>public class <var>ClassName</var> {
    public static void main(String[] args) {
        <var>statements</var>
    }
}</code></pre>

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

- The file name must match the class name: `CarRace.java`.
- Simple statements, such as declarations, assignments and method calls, end with `;`. Lines that end with `{` or `}` don't.
- Every `{` needs a matching `}`. Compile often.

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

1. The class is named `Greeting`, so the file must be named `Greeting.java`. The compiler says:

    ```text
    Hello.java:1: error: class Greeting is public, should be declared in a file named Greeting.java
    ```

2. The `;` at the end. The compiler reports `error: ';' expected`.
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

<pre class="pattern"><code><var>type</var> <var>name</var> = <var>value</var>;</code></pre>

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
double d = 42;        // int to double: automatic
int i = (int) 9.99;   // double to int: cast, gives 9
```

- Write names in **camelCase**: `numberOfSeats`.
- Declare a variable before you use it.
- The type cannot change later.
- A cast cuts off the decimal part. It doesn't round.

<p class="lectures">Lectures 02, 03, 04</p>

### Your turn

1. **Predict the output.**

    ```java
    int a = 7 / 2;
    double b = 7 / 2;
    double c = 7 / 2.0;
    System.out.println(a);
    System.out.println(b);
    System.out.println(c);
    ```

2. **Predict the output.**

    ```java
    System.out.println((int) 2.99);
    ```

3. **Find the bug.** Why doesn't this line compile?

    ```java
    int seats = 40.5;
    ```

<details markdown>
<summary>Answers</summary>

1. `3`, then `3.0`, then `3.5`. When both numbers are `int`, `/` does integer division and drops the remainder, even if you store the result in a `double`. Making one number a `double` (`2.0`) keeps the decimal part.
2. `2`. The cast cuts off `.99`; it doesn't round up.
3. `40.5` is a `double`, and an `int` can't hold the `.5`, so Java won't convert it automatically:

    ```text
    error: incompatible types: possible lossy conversion from double to int
    ```

    Use `double seats = 40.5;`, or write `(int) 40.5` if you want `40`.

</details>

## How do I write a method? { #method }

<pre class="pattern"><code>public static <var>returnType</var> <var>methodName</var>(<var>type</var> <var>paramName</var>, <var>type</var> <var>paramName</var>) {
    <var>statements</var>
    return <var>value</var>;  // leave out if returnType is void
}</code></pre>

Examples:

```java
public static double average(int a, int b) {
    return (a + b) / 2.0;
}

public static void greet(String name) {
    System.out.println("Hello, " + name);
}
```

- The returned value must match the return type.
- A method that works on an object's fields leaves out `static`. See [Declare a class](classes-and-objects.md#class).

### Read it word by word { #method-words }

```java
public static double average(int a, int b) {
```

| Part | Meaning |
|---|---|
| `public` | Code in other classes can call it. |
| `static` | `main` can call the method directly, without creating an object first. Methods that work on an object's fields leave it out. |
| `double` | The return type: the kind of value the method gives back. `void` means it gives nothing back. |
| `average` | The name you choose, in camelCase. |
| `(int a, int b)` | The parameters: each has a type and a name. With no parameters, write `()`. |
| `{` | Starts the body. The body ends at the matching `}`. |

<p class="lectures">Lecture 03</p>

### Your turn

1. **Find the bug.** This method should give back the square of `x`. Why doesn't it compile?

    ```java
    public static int square(int x) {
        System.out.println(x * x);
    }
    ```

2. **Write it.** Write a method `isEven` that takes an `int` and returns `true` if the number is even. Hint: `n % 2` is the remainder after dividing by 2.

<details markdown>
<summary>Answers</summary>

1. The return type is `int`, so the method must `return` an `int`. Printing a value isn't the same as returning it. The compiler reports `error: missing return statement`. Fix it with `return x * x;`.
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
| static, same class | `methodName(args);` | `greet("Taylor");` |
| static, other class | `ClassName.methodName(args);` | `Car.getTotalCars();` |
| on an object | `object.methodName(args);` | `car1.drive();` |
| keep the result | `type var = methodName(args);` | `double avg = average(4, 7);` |

Parameter vs argument:

```java
public static void greet(String name)   // parameter
greet("Taylor");                         // argument
```

- The **parameter** is the variable in the method header. The **argument** is the value you pass in when you call the method.
- Arguments must match the parameters in number, type and order.
- Non-static methods need an object first. See [Create and use an object](classes-and-objects.md#object).

<p class="lectures">Lectures 03, 07, 09</p>

### Your turn

These use `average` and `greet` from [How do I write a method?](#method).

1. **Predict the output.**

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

1. `5.5`. The method computes `(4 + 7) / 2.0`, which is `11 / 2.0`.
2. `average` has two parameters, so you must pass two arguments. The compiler says `method average in class ... cannot be applied to given types` and adds `reason: actual and formal argument lists differ in length`.
3. It doesn't compile. `greet` is `void`, so there's no result to store: `error: incompatible types: void cannot be converted to String`. Call it on its own line instead: `greet("Taylor");`.

</details>

## How do I make a decision? { #decision }

<pre class="pattern"><code>if (<var>condition</var>) {
    <var>statements</var>
} else if (<var>condition</var>) {
    <var>statements</var>
} else {
    <var>statements</var>
}</code></pre>

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

| Compare | Combine |
|---|---|
| `==` `!=` `<` `>` `<=` `>=` | `&&` and, <code>&#124;&#124;</code> or, `!` not |

- Only the first true branch runs. Java skips the rest.
- For Strings, use `.equals()`, not `==`. See [Compare values](classes-and-objects.md#compare).

<p class="lectures">Lecture 05</p>

### Your turn

1. **Predict the output.**

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

1. `B`. `95 >= 80` is true, so the first branch runs and Java never checks `score >= 90`. Put the strictest condition first.
2. `=` assigns a value; `==` compares. Write `score == 90`. With `=`, the compiler reports `error: incompatible types: int cannot be converted to boolean`.
3. `equals`. With `==`, Java checks whether both sides are the same String object, which can be `false` even when the text matches.

</details>

## How do I repeat something? { #repeat }

To repeat a fixed number of times, use `for`:

<pre class="pattern"><code>for (int i = 0; i &lt; <var>count</var>; i++) {
    <var>statements</var>
}</code></pre>

To visit every element of an array, use for-each (see [Loop through an array](arrays.md#loop-array)):

<pre class="pattern"><code>for (<var>type</var> <var>item</var> : <var>array</var>) {
    <var>statements</var>
}</code></pre>

To repeat until something changes, use `while`:

<pre class="pattern"><code>while (<var>condition</var>) {
    <var>statements</var>  // must change condition
}</code></pre>

To run the body at least once, use `do`-`while`:

<pre class="pattern"><code>do {
    <var>statements</var>
} while (<var>condition</var>);  // note the ;</code></pre>

Example:

```java
for (int i = 0; i < 5; i++) { System.out.println(i); }  // prints 0 1 2 3 4
```

- **`break`** leaves the loop.
- **`continue`** skips to the next round of the loop.
- `i++` adds 1 to `i`, and `i--` subtracts 1. `total += x` is short for `total = total + x`.

<p class="lectures">Lecture 06</p>

### Your turn

1. **Predict the output.** How many times does this print `Hi`?

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

3. **Predict the output.**

    ```java
    int x = 10;
    do {
        System.out.println(x);
        x++;
    } while (x < 5);
    ```

<details markdown>
<summary>Answers</summary>

1. 6 times, for `i` = 0, 1, 2, 3, 4 and 5. Starting at `0` with `<=` runs one extra time. Use `i < 5` for 5 times.
2. It prints `3` forever. Nothing in the loop changes `n`, so the condition stays true. Add `n--;` inside the loop. Stop a running program with `Ctrl+C`.
3. `10`. A `do`-`while` loop runs its body once before it checks the condition, even if the condition is false from the start.

</details>

## How do I read input from the keyboard? { #input }

<pre class="pattern"><code>import java.util.Scanner;  // top of file

Scanner scanner = new Scanner(System.in);
<var>type</var> <var>var</var> = scanner.<var>nextXxx</var>();
scanner.close();  // when done</code></pre>

| I want to read … | Method |
|---|---|
| one word | `next()` |
| a whole line | `nextLine()` |
| an int | `nextInt()` |
| a double | `nextDouble()` |
| a boolean | `nextBoolean()` |

Ask again until the user types a number:

```java
while (!scanner.hasNextInt()) {
    scanner.next();  // throw away bad input
    System.out.print("Age: ");
}
int age = scanner.nextInt();
```

**Trap:** after `nextInt()`, call `nextLine()` once before reading a line. `nextInt()` reads only the number and leaves the end of the line unread, so the next `nextLine()` returns an empty String.

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

1. `name` is an empty String, and the program doesn't wait for the name. `nextLine()` reads the rest of the line after `20`, which is empty. Add `scanner.nextLine();` right after `nextInt()`.
2. `nextLine`. `next()` reads one word, so it would store only `Taylor`.

</details>

## How do I print output? { #output }

<pre class="pattern"><code>System.out.println(<var>value</var>);  // then new line
System.out.print(<var>value</var>);    // stay on line
System.out.printf("<var>format</var>", <var>values</var>);</code></pre>

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

**Trap:** `"Sum: " + 5 + 4` prints `Sum: 54`. Java works left to right, so `"Sum: " + 5` becomes the String `"Sum: 5"`, and then `4` is added as text. Write `"Sum: " + (5 + 4)` to get `Sum: 9`.

<p class="lectures">Lectures 02, 05, 08</p>

### Your turn

1. **Predict the output.**

    ```java
    System.out.println("Sum: " + 5 + 4);
    System.out.println(5 + 4 + " total");
    ```

2. **Predict the output.**

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

1. `Sum: 54`, then `9 total`. In the second line, `5 + 4` comes first and both are numbers, so Java adds them before it reaches the String.
2. `ABC` on the first line and `D` on the second. Only `println` ends the line.
3. `%.2f%n`. `%.2f` shows the number with 2 decimals, and `%n` ends the line.

</details>

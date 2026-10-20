# Help

What common error messages mean, and the mistakes that usually cause them.

## What does this compile error mean? { #compile-errors }

| javac says | Usually means | Fix |
|---|---|---|
| `';' expected` | A semicolon is missing | Add `;` at the end of the line javac names |
| `cannot find symbol` | Typo, not declared, or used outside its scope | Check spelling, capitals, and where it was declared ([Declare a variable](basics.md#variable)) |
| `incompatible types: possible lossy conversion from double to int` | A decimal goes into an `int` | Change the type, or cast with `(int)` |
| `missing return statement` | Some path ends without `return` | Return a value on every path |
| `variable x might not have been initialized` | A local variable is used before it has a value | Give it a value when you declare it |
| `non-static variable speed cannot be referenced from a static context` | A field is used inside `main` | Create an object, then use its getter ([Share a value (`static`)](classes-and-objects.md#static)) |
| `reached end of file while parsing` | A `}` is missing | Count your `{` and `}` |

Fix the **first** error first. One mistake can cause several messages.

Each message starts with the file name and line number. The caret `^` marks where javac noticed the problem. For example, this is what javac prints when line 3 of `CarRace.java` is `int laps = 3` with no semicolon:

```text
CarRace.java:3: error: ';' expected
        int laps = 3
                    ^
1 error
```

<p class="lectures">Lectures 04, 10</p>

### Your turn

1. **Read this error.** Which file, which line, and what is wrong?

    ```text
    Garage.java:4: error: cannot find symbol
            System.out.println(car.getSpeeed());
                                  ^
      symbol:   method getSpeeed()
      location: variable car of type Car
    1 error
    ```

2. **What happens?** Which message from the table will javac print?

    ```java
    public static void main(String[] args) {
        int laps;
        System.out.println(laps);
    }
    ```

<details markdown>
<summary>Answers</summary>

1. File `Garage.java`, line 4. The method name is misspelled: `getSpeeed` has an extra `e`. The class `Car` has no method with that name, so javac can't find it. Change it to `getSpeed()`.
2. `variable laps might not have been initialized`. The local variable `laps` is printed before it has a value. Declare it as `int laps = 0;`.

</details>

## My program crashed. What does it mean? { #runtime-errors }

| Exception | Means | Fix |
|---|---|---|
| `NullPointerException` | A variable is `null` and holds no object | Create it with `new`, or check `!= null` ([Keep your program from crashing](classes-and-objects.md#avoid-crashes)) |
| `ArrayIndexOutOfBoundsException` | Index below 0 or `>= length` | Loop with `i < arr.length` ([Loop through an array](arrays.md#loop-array)) |
| `InputMismatchException` | The user typed the wrong kind of value | Check with `hasNextInt()` first ([Read input](basics.md#input)) |
| `ArithmeticException: / by zero` | An `int` divided by 0 | Check the divisor first |

### Find the line

When a program crashes, Java prints the exception and a list of the method calls that led to it. Here, `Race.java` calls `driver.greet()` on line 4, and line 5 of `Driver.java` uses the field `name`, which was never given a value:

```text
Exception in thread "main" java.lang.NullPointerException: Cannot invoke "String.toUpperCase()" because "this.name" is null
	at Driver.greet(Driver.java:5)
	at Race.main(Race.java:4)
```

The first line of the output tells you what went wrong. To find where it went wrong, look for the first `at` line that names one of your own files: here, `Driver.java`, line 5. Some traces start inside Java's own classes, like this one from a user typing `ten` when `nextInt()` expects a number. Skip the `java.base/` lines until you reach your own file, here `AgeCheck.java`, line 6:

```text
Exception in thread "main" java.util.InputMismatchException
	at java.base/java.util.Scanner.throwFor(Scanner.java:947)
	at java.base/java.util.Scanner.next(Scanner.java:1602)
	at java.base/java.util.Scanner.nextInt(Scanner.java:2267)
	at java.base/java.util.Scanner.nextInt(Scanner.java:2221)
	at AgeCheck.main(AgeCheck.java:6)
```

**Never ends?** A loop condition never becomes false. Make sure the loop changes the variable that the condition checks. To stop the running program, press Ctrl+C in the terminal.

<p class="lectures">Lectures 06, 09, 10, 11</p>

### Your turn

1. **Which exception will each line throw?**

    ```java
    int[] laps = new int[3];
    laps[3] = 1;                // a
    Car[] cars = new Car[2];
    cars[0].drive();            // b
    int avg = 10 / (5 - 5);     // c
    ```

2. **What happens?** `System.out.println(10.0 / 0);`

3. **Find the bug.** This loop never ends.

    ```java
    int i = 0;
    while (i < 5) {
        System.out.println(i);
    }
    ```

<details markdown>
<summary>Answers</summary>

1. (a) `ArrayIndexOutOfBoundsException`: an array of 3 has indices 0 to 2. (b) `NullPointerException`: `new Car[2]` creates two empty slots that hold `null`, not two cars. (c) `ArithmeticException: / by zero`: `5 - 5` is 0, and both numbers are `int`.
2. It prints `Infinity` and doesn't crash. Only `int` division by 0 throws an exception; `10.0` is a `double`.
3. `i` never changes, so `i < 5` stays true and the program prints `0` forever. Add `i++;` inside the loop.

</details>

## What are the most common mistakes? { #common-mistakes }

| Incorrect | Correct | Reason |
|---|---|---|
| `s1 == s2` | `s1.equals(s2)` | `==` checks for the same object ([Compare values](classes-and-objects.md#compare)) |
| `if (x = 5)` | `if (x == 5)` | `=` assigns, `==` compares ([Make a decision](basics.md#decision)) |
| `7 / 2` is `3` | `7 / 2.0` is `3.5` | `int / int` drops decimals |
| `float g = 9.81;` | `float g = 9.81f;` | `9.81` is a `double` |
| `model = model;` | `this.model = model;` | Parameter shadows field ([Use this](classes-and-objects.md#this)) |
| `public void Car()` | `public Car()` | Constructors have no return type ([Write a constructor](classes-and-objects.md#constructor)) |
| `arr[arr.length]` | `arr[arr.length - 1]` | Last index is `length - 1` |
| `nextInt(); nextLine();` | Call `nextLine()` once more to skip the rest of the line | `nextInt()` leaves the newline behind ([Read input](basics.md#input)) |

### Your turn

1. **Find the bug.** The user types `yes`, but nothing is printed.

    ```java
    String answer = scanner.nextLine();
    if (answer == "yes") {
        System.out.println("Starting the race");
    }
    ```

2. **Predict the output.**

    ```java
    double avg = 7 / 2;
    System.out.println(avg);
    ```

3. **Find the bug.** After `new Car("Roadster", 2024)`, the field `model` is still `null`.

    ```java
    public Car(String model, int year) {
        model = model;
        this.year = year;
    }
    ```

<details markdown>
<summary>Answers</summary>

1. `==` checks whether both sides are the same object, not whether they hold the same text. Use `answer.equals("yes")`.
2. `3.0`. `7 / 2` is `int` division, so it gives `3`, which is then stored as the `double` `3.0`. Write `7 / 2.0` to get `3.5`.
3. The parameter `model` shadows the field, so `model = model;` assigns the parameter to itself. Write `this.model = model;`.

</details>

# Classes and objects

A class describes the structure of an object. You can think of it as a data type
you define yourself. It sets out the data each object keeps (its fields, also
called instance variables) and what each object can do (its methods, also
called instance methods). You write the class once and then create as many
objects from it as you need.

## How do I declare a class? { #class }

Pattern, in a file named <code><var>ClassName</var>.java</code>:

<pre class="pattern"><code>public class <var>ClassName</var> {
    // 1. fields (data)
    private <var>type</var> <var>fieldName</var>;

    // 2. constructor (setup)
    public <var>ClassName</var>(<var>type</var> <var>param</var>) {
        this.<var>fieldName</var> = <var>param</var>;
    }

    // 3. methods (actions)
    public <var>returnType</var> <var>methodName</var>() {
        <var>statements;</var>
    }
}</code></pre>

Example, in a file named `Car.java`:

```java
public class Car {
    // 1. fields
    private String model;
    private int year;
    private int speed;

    // 2. constructor
    public Car(String model, int year) {
        this.model = model;
        this.year = year;
    }

    // 3. methods
    public void drive() {
        System.out.println("Vroom");
    }
}
```

The same class as a UML class diagram:

| Car |
|---|
| `- model: String`<br>`- year: int`<br>`- speed: int` |
| `+ drive()` |

In a UML class diagram, `-` means `private` and `+` means `public`.

The full `Car` class used on this page, with a `CarRace` program that uses it, is in
`examples/car/`. To run it, go to the repository folder and enter:

```bash
cd examples/car
javac Car.java CarRace.java
java CarRace
```

- Write the class name in PascalCase: `Car`, `CarRace`
- The file name must match the class name: `public class Car` in `Cars.java`
  gives `error: class Car is public, should be declared in a file named Car.java`
- `Car` has no `main` method, so `java Car` gives
  `Error: Main method not found in class Car`
- `Car` only describes the objects; a separate class with `main`, here
  `CarRace`, creates and uses them
- Don't put `static` on fields or methods that belong to each object
- Methods work as described in [Write a method](basics.md#method)

### Where each part goes

A class is a set of nested blocks. Each `{` opens a block and its matching `}`
closes it:

<ul class="anatomy">
  <li><span class="anatomy-label">Class: <code>public class Car { … }</code></span>
    <ul>
      <li><span class="anatomy-label">Fields: <code>private String model;</code></span>
        Declarations only, directly in the class body.</li>
      <li><span class="anatomy-label">Constructor: <code>public Car(String model, int year) { … }</code></span>
        <ul>
          <li><span class="anatomy-label">Statements</span> <code>this.model = model;</code><br><code>this.year = year;</code></li>
        </ul>
      </li>
      <li><span class="anatomy-label">Method: <code>public void drive() { … }</code></span>
        <ul>
          <li><span class="anatomy-label">Statements</span> <code>System.out.println("Vroom");</code></li>
        </ul>
      </li>
    </ul>
  </li>
</ul>

- Fields, constructors, and methods are declared directly in the class body,
  the outermost block.
- Statements such as `speed = 10;` and `System.out.println(...);` belong in the
  body of a constructor or method. A field declaration can include a starting
  value, as in `private int speed = 0;`, but a separate statement directly in
  the class body gives `error: <identifier> expected`.

### What each word means

```java
private String model;
```

| Part | Meaning |
|---|---|
| `private` | Access modifier: only code in the `Car` class can use this field. |
| `String` | The data type: the kind of value `model` holds. A `String` is text. |
| `model` | The name you choose, in camelCase. |
| `;` | Ends the declaration. |

The same breakdown for a constructor is in
[Write a constructor](#constructor-words), and for a method in
[Write a method](basics.md#method-words).

### Write it one step at a time

Build a class a few lines at a time and compile after each step, so any error
is easy to find. Each version below compiles on its own; the new lines are
highlighted.

Step 1: the empty class.

```java
public class Car {
}
```

Step 2: add the fields inside the class body.

```java hl_lines="2 3 4"
public class Car {
    private String model;
    private int year;
    private int speed;
}
```

Step 3: add the constructor below the fields.

```java hl_lines="6 7 8 9"
public class Car {
    private String model;
    private int year;
    private int speed;

    public Car(String model, int year) {
        this.model = model;
        this.year = year;
    }
}
```

Step 4: add the `drive` method below the constructor, still inside the class body.
The result is the `Car` class at the top of this section.

<p class="lectures">Lectures 07, 08</p>

### Your turn

1. **Put the lines in order.** Arrange these lines into a class that compiles.

    ```java
    this.title = title;
    }
    private String title;
    public class Book {
    public Book(String title) {
    }
    ```

2. **Label the parts.** Which lines are the field, the constructor, and the
   method? Which lines are statements?

    ```java linenums="1"
    public class Lamp {
        private boolean on;

        public Lamp() {
            on = false;
        }

        public void turnOn() {
            on = true;
        }
    }
    ```

3. **Fill in the blank.** Complete the constructor so that it stores the name.

    ```java
    public class Student {
        private String name;

        public ____(String name) {
            ____ = name;
        }
    }
    ```

4. **Find the bug.** Every bus ends up with the model of the last bus you created. Why?

    ```java
    public class Bus {
        private static String model;
        public Bus(String model) {
            this.model = model;
        }
    }
    ```

5. **Write it.** Turn this UML box into a Java class. Leave the method body empty.

    | Dog |
    |---|
    | `- name: String` |
    | `+ bark()` |

<details markdown>
<summary>Answers</summary>

1. The class line comes first and its `}` comes last. The first `}` closes the
   constructor:

    ```java
    public class Book {
        private String title;
        public Book(String title) {
            this.title = title;
        }
    }
    ```

    Fields usually come first, but that's only a convention; the class still
    compiles with the field after the constructor. What matters is that both are inside the class
    braces and the statement is inside the constructor.

2. Line 2 is the field. Lines 4 to 6 are the constructor: its name is the class
   name and it has no return type. Lines 8 to 10 are the method `turnOn`. The
   statements are line 5 (`on = false;`) and line 9 (`on = true;`), each inside
   a body.
3. `Student` and `this.name`. A constructor's name is exactly the class name, and
   it has no return type. `this.name` is the field; plain `name` is the parameter.
   `name = name;` compiles, but it assigns the parameter to itself, so the
   field stays `null`.
4. `model` is `static`, so all buses share one `model` field and each new bus overwrites it. Remove `static`.
5. One possible answer:

    ```java
    public class Dog {
        private String name;

        public void bark() {
        }
    }
    ```

</details>

## How do I write a constructor? { #constructor }

Pattern:

<pre class="pattern"><code>// no-arg
public <var>ClassName</var>() {
    <var>fieldName</var> = <var>startValue</var>;
}

// parameterized
public <var>ClassName</var>(<var>type</var> <var>param</var>) {
    this.<var>fieldName</var> = <var>param</var>;
}</code></pre>

Example, inside class `Car`:

```java
public Car() {
    model = "Unknown";
}

public Car(String model, int year) {
    this.model = model;
    this.year = year;
}
```

- A constructor has the **same name as the class**.
- It has **no return type**, not even `void`.
- It runs when you write `new`.
- If you write no constructor at all, Java adds an empty no-arg constructor for you.

### What each word means { #constructor-words }

```java
public Car(String model, int year) {
```

| Part | Meaning |
|---|---|
| `public` | Code in other classes can use it with `new`. |
| `Car` | Exactly the class name, with no return type, not even `void`. That's what makes it a constructor. |
| `(String model, int year)` | Parameters: the values you pass in `new Car("Roadster", 2024)`. |
| `{` | Starts the body. The body ends at the matching `}`. |

<p class="lectures">Lecture 07</p>

### Your turn

1. **What happens?** `Car` has a field `private String model;`, a getter `getModel()`, and this "constructor":

    ```java
    public void Car() {
        model = "Unknown";
    }
    ```

    What does `System.out.println(new Car().getModel());` print?

2. **What happens?** `Car` has only the constructor `Car(String model, int year)`. You write `Car car = new Car();`.

<details markdown>
<summary>Answers</summary>

1. It prints `null`. Because of `void`, `Car()` is an ordinary method, not a constructor. Java adds an empty constructor, so `model` keeps its default value `null`.
2. It doesn't compile. Java adds the empty no-arg constructor only when you write no constructor at all:

    ```text
    error: constructor Car in class Car cannot be applied to given types;
      required: String,int
      found:    no arguments
      reason: actual and formal argument lists differ in length
    ```

</details>

## How do I create and use an object? { #object }

Pattern:

<pre class="pattern"><code><var>ClassName</var> <var>varName</var> = new <var>ClassName</var>(<var>arguments</var>);
<var>varName</var>.<var>methodName</var>(<var>arguments</var>);</code></pre>

Example, in `main`:

```java
Car car1 = new Car();
Car car2 = new Car("Roadster", 2024);
car2.drive();
int speed = car2.getSpeed();
```

- The arguments decide which constructor runs.
- Each object has its own field values.
- `Car car3;` on its own doesn't create an object. Only `new` creates one.

<p class="lectures">Lectures 07, 09</p>

### Your turn

1. **Predict the output.** Both cars start with speed 0.

    ```java
    Car car1 = new Car("Roadster", 2024);
    Car car2 = new Car("Beetle", 1970);
    car1.accelerate(20);
    car2.accelerate(50);
    car1.accelerate(5);
    System.out.println(car1.getSpeed() + " " + car2.getSpeed());
    ```

2. **Find the bug.** This code inside `main` doesn't compile.

    ```java
    Car car3;
    car3.drive();
    ```

<details markdown>
<summary>Answers</summary>

1. `25 50`. Each car has its own `speed` field, so `car2.accelerate(50)` doesn't change `car1`.
2. `car3` refers to no object yet. The compiler reports `error: variable car3 might not have been initialized`. Write `Car car3 = new Car();` instead.

</details>

## How do I write getters and setters? { #getters-setters }

Pattern:

<pre class="pattern"><code>private <var>type</var> <var>fieldName</var>;

public <var>type</var> get<var>FieldName</var>() {
    return <var>fieldName</var>;
}

public void set<var>FieldName</var>(<var>type</var> <var>fieldName</var>) {
    this.<var>fieldName</var> = <var>fieldName</var>;
}</code></pre>

Example of a setter that checks the new value:

```java
private int speed;

public int getSpeed() {
    return speed;
}

public void setSpeed(int speed) {
    if (speed >= 0) {
        this.speed = speed;
    }
}
```

- `private` means only code in this class can use the field or method. `public` means code in any class can.
- Not every field needs a setter.

<p class="lectures">Lecture 08</p>

### Your turn

1. **Predict the output.**

    ```java
    Car car = new Car();
    car.setSpeed(30);
    car.setSpeed(-5);
    System.out.println(car.getSpeed());
    ```

2. **What happens?** `speed` is `private` in `Car`. You write this in `main` in another class:

    ```java
    Car car = new Car();
    car.speed = 100;
    ```

<details markdown>
<summary>Answers</summary>

1. `30`. The setter ignores `-5` because it's negative.
2. It doesn't compile: `error: speed has private access in Car`. Use `car.setSpeed(100);` instead.

</details>

## How do I print an object? { #print-object }

Pattern, added to your class:

<pre class="pattern"><code>@Override
public String toString() {
    return "<var>ClassName</var> [<var>field</var>: " + <var>field</var> + "]";
}</code></pre>

Example:

```java
@Override
public String toString() {
    return "Car [model: " + model + "]";
}
```

Then in `main`:

```java
System.out.println(car2);  // Car [model: Roadster]
// without toString() you get something like Car@7ad041f3
```

- `println` calls `toString()` for you.
- Write it exactly like this: `public`, named `toString`, returns a `String`, takes no parameters.

<p class="lectures">Lecture 08</p>

### Your turn

1. **Find the bug.** This method is in class `Car`, but `System.out.println(car2);` still prints something like `Car@7ad041f3`.

    ```java
    public String tostring() {
        return "Car [model: " + model + "]";
    }
    ```

2. **What happens?** You add `@Override` on the line above the method from question 1 and compile again.

<details markdown>
<summary>Answers</summary>

1. The name must be `toString` with a capital S. `tostring` is a new method that `println` never calls.
2. It doesn't compile: `error: method does not override or implement a method from a supertype`. `@Override` makes the compiler catch the misspelling for you.

</details>

## Which kind of variable do I need? { #which-variable }

| I want … | Use | Where |
|---|---|---|
| data each object keeps | instance | in the class, outside methods |
| one value shared by all objects | class (`static`) | in the class, with `static` |
| a value passed into a method | parameter | in the method's `( )` |
| a temporary value | local | inside a method or `{ }` |

Example:

```java
public class Car {
    private static int totalCars = 0;  // class (static)
    private int speed;                 // instance
    public void accelerate(int amount) {  // amount: parameter
        int newSpeed = speed + amount;    // local
        speed = newSpeed;
    }
}
```

Fields get default values (`0`, `false`, `null`). Local variables don't, so set them before you use them.

<p class="lectures">Lectures 02, 07, 09</p>

### Your turn

1. **Fill in the blank.** In `Car` above, which kind of variable is each of these: `amount`, `speed`, `totalCars`, `newSpeed`?
2. **What happens?** This code is inside a method:

    ```java
    int total;
    total = total + 5;
    ```

<details markdown>
<summary>Answers</summary>

1. `amount` is a parameter, `speed` is an instance variable, `totalCars` is a class (`static`) variable, and `newSpeed` is a local variable.
2. It doesn't compile: `error: variable total might not have been initialized`. Local variables get no default value. Write `int total = 0;`.

</details>

## How do I share one value across all objects? { #static }

Pattern:

<pre class="pattern"><code>private static <var>type</var> <var>name</var> = <var>value</var>;

public static <var>type</var> get<var>Name</var>() {
    return <var>name</var>;
}

// call on the class:
<var>ClassName</var>.get<var>Name</var>();</code></pre>

Example that counts all cars, inside class `Car`:

```java
private static int totalCars = 0;

public Car() {
    totalCars++;
}
public static int getTotalCars() {
    return totalCars;
}
```

In `main`:

```java
int n = Car.getTotalCars();
```

A `static` method (like `main`) can't use instance fields directly. Create an object first.

<p class="lectures">Lecture 09</p>

### Your turn

1. **Predict the output.** Every `Car` constructor runs `totalCars++`.

    ```java
    Car car1 = new Car();
    Car car2 = new Car("Roadster", 2024);
    Car car3 = new Car();
    System.out.println(Car.getTotalCars());
    ```

2. **What happens?** This class doesn't compile. Why?

    ```java
    public class Counter {
        private int count;
        public static void main(String[] args) {
            count++;
        }
    }
    ```

<details markdown>
<summary>Answers</summary>

1. `3`. All cars share one `totalCars`, and each constructor call adds 1 to it.
2. `count` is an instance field, and `main` is `static`: `error: non-static variable count cannot be referenced from a static context`. Create an object first, or make `count` `static` if all objects should share it.

</details>

## How do I use `this`? { #this }

A common problem: your constructor doesn't set the fields.

Wrong:

```java
public Student(String name, int age) {
    name = name;
    age = age;
}
// prints: null is 0 years old
```

Right:

```java
public Student(String name, int age) {
    this.name = name;
    this.age = age;
}
// prints: Yoda is 864 years old
```

- The parameter `name` hides the field `name`. This is called **shadowing**. `name = name` assigns the parameter to itself, and the field keeps its default value.
- `this.name` means "the field of this object".

One constructor can also call another with `this(...)`:

```java
public Student() { this("Unknown", 0); }
```

Put the `this(...)` call first in the constructor. Java 21, which this course
uses, requires that; Java 25 and later relax the rule.

<p class="lectures">Lecture 09</p>

### Your turn

1. **Predict the output.** `Car` has `toString()` from [Print an object](#print-object) and this constructor:

    ```java
    public Car(String model, int year) {
        model = model;
        year = year;
    }
    ```

    What does `System.out.println(new Car("Roadster", 2024));` print?

2. **Fill in the blank.** Make the no-arg constructor give the car the model `"Unknown"` and the year `0` by calling the other constructor.

    ```java
    public Car() {
        ____________________;
    }
    ```

<details markdown>
<summary>Answers</summary>

1. `Car [model: null]`. The parameters shadow the fields, so the fields are never set. Write `this.model = model;` and `this.year = year;`.
2. `this("Unknown", 0);` calls `Car(String model, int year)`.

</details>

## How do I compare values? { #compare }

| Comparing | Use | Example |
|---|---|---|
| `int`, `double`, `char`, `boolean` | `==` `!=` `<` `>` `<=` `>=` | `age >= 18` |
| Strings | `.equals()` `.equalsIgnoreCase()` | `name.equals("Yoda")` |
| "Is it the same object?" | `==` | `winner == driver1` |
| "Is there an object?" | `!= null` | `car != null` |

Why not `==` for Strings?

```java
if (answer == "yes")       // may be false
if (answer.equals("yes"))  // compares the text
```

Objects are stored as references. `==` asks whether two references point to the same object, not whether the content matches. A String read with a `Scanner` (see [Read input](basics.md#input)) is a new object, so `==` usually gives `false` even when the text matches.

<p class="lectures">Lectures 05, 09</p>

### Your turn

1. **Predict the output.**

    ```java
    String a = "yes";
    String b = new String("yes");
    System.out.println(a == b);
    System.out.println(a.equals(b));
    System.out.println("YES".equalsIgnoreCase(a));
    ```

2. **Predict the output.**

    ```java
    Car car1 = new Car("Roadster", 2024);
    Car winner = car1;
    Car twin = new Car("Roadster", 2024);
    System.out.println(winner == car1);
    System.out.println(twin == car1);
    ```

<details markdown>
<summary>Answers</summary>

1. `false`, `true`, `true`. `new String` makes a second object, so `==` is `false`, but the text is the same.
2. `true`, `false`. `winner` and `car1` refer to the same object. `twin` is a different object with the same field values.

</details>

## How do I keep my program from crashing? { #avoid-crashes }

Option 1: check first. Use an `if` when you can test for the problem before the
operation runs.

```java
if (car != null) {
    car.drive();
}
if (i < prices.length) {
    System.out.println(prices[i]);
}
if (scanner.hasNextInt()) {
    age = scanner.nextInt();
}
if (count != 0) {
    int average = sum / count;
} else {
    System.out.println("count is 0");
}
```

Option 2: `try` / `catch`. Use this when an operation can throw an exception
that you want to handle instead of letting the program crash. If a statement in
the `try` block throws the type of exception you catch, Java skips the rest of
the `try` block and runs the `catch` block.

<pre class="pattern"><code>try {
    <var>risky statements;</var>
} catch (<var>ExceptionType</var> e) {
    <var>what to do instead;</var>
} finally {
    // optional: always runs
}</code></pre>

Example:

```java
try {
    int age = Integer.parseInt("abc");
    System.out.println(age);
} catch (NumberFormatException e) {
    System.out.println("Invalid number");
}
```

`Integer.parseInt` converts text such as `"42"` into an `int`. `"abc"` isn't a
number, so `parseInt` throws a `NumberFormatException`. Java skips
`println(age)`, runs the `catch` block, and prints `Invalid number`.

- `prices.length` is the number of elements in an array.
- `scanner.hasNextInt()` checks that the next input is a whole number. See [Read input](basics.md#input).

<p class="lectures">Lectures 06, 10</p>

### Your turn

1. **Find the bug.** `car` is `null`. This line crashes with a `NullPointerException`. Fix it without adding a new `if`.

    ```java
    if (car.getSpeed() > 0 && car != null) {
    ```

2. **Predict the output.** `sum` is `10` and `count` is `0`.

    ```java
    try {
        System.out.println(sum / count);
        System.out.println("average printed");
    } catch (ArithmeticException e) {
        System.out.println("count is 0");
    }
    ```

<details markdown>
<summary>Answers</summary>

1. Swap the two conditions: `if (car != null && car.getSpeed() > 0) {`. Java checks `&&` from left to right and stops at the first `false`, so `getSpeed()` is never called on `null`.
2. Only `count is 0`. Dividing an `int` by 0 throws an `ArithmeticException`, so Java jumps straight to `catch` and skips the rest of the `try` block.

</details>

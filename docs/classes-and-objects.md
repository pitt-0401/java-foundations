# Classes and objects

A class describes the structure of an object, like a blueprint. Classes allow you
to define your own data types. A class defines the data each object stores (its
attributes, also known as fields or instance variables) and what each object can
do (its methods, also known as instance methods). You write the class once; then
you can create as many objects (instances) from it as you need.

## How do I declare a class? { #class }

Pattern, in a file named <code><var>ClassName</var>.java</code>:

<pre class="pattern"><code>public class <var data-label="class name">ClassName</var> {
    // 1. fields (data)
    private <var>type</var> <var data-label="field name">fieldName</var>;

    // 2. constructor (setup)
    public <var>ClassName</var>(<var>type</var> <var data-label="parameter">param</var>) {
        this.<var>fieldName</var> = <var>param</var>;
    }

    // 3. methods (actions)
    public <var data-label="return type">returnType</var> <var data-label="method name">methodName</var>() {
        <var data-label="method body">statements</var>
    }
}</code></pre>

<div class="pattern-parts" data-replace-with="a class name, fields, a constructor, and methods" markdown>

<var data-find="class [[Car]] | public [[Car]](">ClassName</var>
:   The name of the class, written in PascalCase, e.g., `Car`. The constructor has the same name as the class.

<var data-find="private [[String]] | private [[int]] | ([[String]] model | , [[int]] year">type</var>
:   The data type of a field or parameter, e.g., `String` or `int`.

<var data-find="private String [[model]] | private int [[year]] | private int [[speed]] | this.[[model]] | this.[[year]]">fieldName</var>
:   The name of a field (attribute), written in camelCase, e.g., `model`. Each object stores its own value for each field.

<var data-find="(String [[model]], | int [[year]]) | = [[model]]; | = [[year]];">param</var>
:   A constructor parameter: the starting value that is passed in when an object is created. The constructor stores it in the field.

<var data-find="public [[void]] drive">returnType</var>
:   The type of value the method returns, e.g., `int`. `void` means that the method does not return anything.

<var data-find="void [[drive]](">methodName</var>
:   A descriptive method name, written in camelCase, e.g., `drive`.

<var data-find='[[System.out.println("Vroom");]]'>statements</var>
:   The method body: the statements that are executed when the method is invoked.

</div>

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

A UML class diagram has three parts: class name, attributes (fields), and methods. `-` indicates `private` members, `+` indicates `public` members.

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
- Do not put `static` on fields or methods that belong to each object
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

- Fields, constructors, and methods are declared directly in the class body
  (the outermost block)
- Statements, e.g., `speed = 10;` and `System.out.println(...);`, belong in the
  body of a constructor or method
    - A field declaration can include a starting value, e.g., `private int speed = 0;`
    - A separate statement directly in the class body leads to
      `error: <identifier> expected`

### What each word means

```java
private String model;
```

| Part | Meaning |
|---|---|
| `private` | Access modifier: the field is only accessible from within the `Car` class. |
| `String` | The data type: the kind of value `model` holds. A `String` is text. |
| `model` | The field name, written in camelCase. |
| `;` | Ends the declaration. |

The same breakdown for a constructor is in
[Write a constructor](#constructor-words), and for a method in
[Write a method](basics.md#method-words).

### Write it one step at a time

Start with the structure, then add the fields, the constructor, and the methods.
Compile after each step: errors are easier to find and fix. Each version below compiles on its own; the new lines are
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

1. The class line comes first and its `}` comes last; the first `}` closes the
   constructor:

    ```java
    public class Book {
        private String title;
        public Book(String title) {
            this.title = title;
        }
    }
    ```

    - Fields usually come first, but that is only a convention; the class still
      compiles with the field after the constructor
    - What matters is that both are inside the class braces and the statement is
      inside the constructor

2. The parts:
    - Field: line 2
    - Constructor: lines 4 to 6; its name is the class name and it has no return type
    - Method `turnOn`: lines 8 to 10
    - Statements: line 5 (`on = false;`) and line 9 (`on = true;`), each inside a body
3. `Student` and `this.name`
    - A constructor's name is exactly the class name, and it has no return type
    - `this.name` is the field; plain `name` is the parameter
    - `name = name;` compiles, but it assigns the parameter to itself, so the
      field stays `null`
4. `model` is `static`, so all buses share one `model` field and each new bus overwrites it
    - Remove `static`
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
public <var data-label="class name">ClassName</var>() {
    <var data-label="field name">fieldName</var> = <var data-label="starting value">startValue</var>;
}

// parameterized
public <var>ClassName</var>(<var>type</var> <var data-label="parameter">param</var>) {
    this.<var>fieldName</var> = <var>param</var>;
}</code></pre>

<div class="pattern-parts" data-replace-with="the class name, field names, and starting values or parameters" markdown>

<var data-find="public [[Car]](">ClassName</var>
:   The name of the class, e.g., `Car`. A constructor must have the same name as the class.

<var data-find='[[model]] = "Unknown" | this.[[model]] | this.[[year]]'>fieldName</var>
:   A field of the class, e.g., `model`. The constructor gives it its starting value.

<var data-find='= [["Unknown"]]'>startValue</var>
:   A fixed starting value for the field, e.g., `"Unknown"`. It must match the field's type.

<var data-find="([[String]] model | , [[int]] year">type</var>
:   The data type of the parameter, e.g., `String` or `int`. It must match the field's type.

<var data-find="(String [[model]], | int [[year]]) | = [[model]]; | = [[year]];">param</var>
:   A parameter acts as a placeholder for the value that is passed in with `new`, e.g., `new Car("Roadster", 2024)`.

</div>

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

- A constructor has the **same name as the class**
- It has **no return type**, not even `void`
- It is invoked when you create an object with `new`
- If a class has no constructor, Java adds an empty no-arg constructor (the default constructor)

### What each word means { #constructor-words }

```java
public Car(String model, int year) {
```

| Part | Meaning |
|---|---|
| `public` | Access modifier: code in other classes can call it with `new`. |
| `Car` | Exactly the class name, with no return type, not even `void`. That is what makes it a constructor. |
| `(String model, int year)` | The parameters: they receive the arguments in `new Car("Roadster", 2024)`. |
| `{` | Starts the constructor body, which ends at the matching `}`. |

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

1. It prints `null`
    - Because of `void`, `Car()` is an ordinary method, not a constructor
    - Java adds an empty constructor, so `model` keeps its default value `null`
2. It does not compile
    - Java adds the empty no-arg constructor only when you write no constructor at all:

    ```text
    error: constructor Car in class Car cannot be applied to given types;
      required: String,int
      found:    no arguments
      reason: actual and formal argument lists differ in length
    ```

</details>

## How do I create and use an object? { #object }

Pattern:

<pre class="pattern"><code><var data-label="class name">ClassName</var> <var data-label="object name">varName</var> =
        new <var>ClassName</var>(<var data-label="may be empty">arguments</var>);
<var>varName</var>.<var data-label="method name">methodName</var>(<var>arguments</var>);</code></pre>

<div class="pattern-parts" data-replace-with="the class name, a variable name, a method name, and arguments" markdown>

<var data-find="[[Car]] car1 | [[Car]] car2 | new [[Car]](">ClassName</var>
:   The name of the class, e.g., `Car`. It is the data type of the variable, and `new ClassName(...)` invokes its constructor.

<var data-find="Car [[car1]] | Car [[car2]] | [[car2]].drive | [[car2]].getSpeed">varName</var>
:   The variable name, written in camelCase, e.g., `car2`. The variable refers to the new object.

<var data-find='([["Roadster", 2024]])'>arguments</var>
:   The values that are passed to the constructor or method, separated by commas. The parentheses can be empty, e.g., `new Car()` and `car2.drive()`.

<var data-find=".[[drive]]( | .[[getSpeed]](">methodName</var>
:   The method to invoke on the object, e.g., `drive`.

</div>

Example, in `main`:

```java
Car car1 = new Car();
Car car2 = new Car("Roadster", 2024);
car2.drive();
int speed = car2.getSpeed();
```

- The arguments determine which constructor is invoked
- The dot notation (`.`) is used to access an object's fields and invoke its methods, e.g., `car2.drive()`
- Each object has its own field values (its state)
- `Car car3;` on its own does not create an object; only `new` creates one

### Your turn

1. **Code tracing.** What does this print? Both cars start with speed 0.

    ```java
    Car car1 = new Car("Roadster", 2024);
    Car car2 = new Car("Beetle", 1970);
    car1.accelerate(20);
    car2.accelerate(50);
    car1.accelerate(5);
    System.out.println(car1.getSpeed() + " "
            + car2.getSpeed());
    ```

2. **Find the bug.** This code inside `main` does not compile.

    ```java
    Car car3;
    car3.drive();
    ```

<details markdown>
<summary>Answers</summary>

1. `25 50`
    - Each car has its own `speed` field, so `car2.accelerate(50)` does not change `car1`
2. `car3` refers to no object yet
    - The compiler reports `error: variable car3 might not have been initialized`
    - Write `Car car3 = new Car();` instead

</details>

## How do I write getters and setters? { #getters-setters }

Pattern:

<pre class="pattern"><code>private <var>type</var> <var data-label="field name">fieldName</var>;

public <var>type</var> get<var data-label="capitalized">FieldName</var>() {
    return <var>fieldName</var>;
}

public void set<var>FieldName</var>(<var>type</var> <var>fieldName</var>) {
    this.<var>fieldName</var> = <var>fieldName</var>;
}</code></pre>

<div class="pattern-parts" data-replace-with="the field's type and name" markdown>

<var data-find="private [[int]] | [[int]] getSpeed | ([[int]] speed">type</var>
:   The data type of the field, e.g., `int`. The getter returns this type, and the setter's parameter has this type.

<var data-find="private int [[speed]] | return [[speed]] | int [[speed]]) | this.[[speed]] = | = [[speed]];">fieldName</var>
:   The name of the field, e.g., `speed`. The setter's parameter usually has the same name; `this.speed` is the field, `speed` is the parameter.

<var data-find="get[[Speed]] | set[[Speed]]">FieldName</var>
:   The field name with its first letter capitalized, e.g., `getSpeed` and `setSpeed`.

</div>

Example of a setter that rejects invalid values:

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

- Getters read a value; setters change it and can validate the new value
- `private`: only accessible from within the class; `public`: accessible from anywhere
- Not every field needs a setter
- Encapsulation (also known as information hiding): fields are `private`, and access is controlled through `public` getters and setters

### Your turn

1. **Code tracing.** What does this print?

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

1. `30`
    - The setter ignores `-5` because it is negative
2. It does not compile: `error: speed has private access in Car`
    - Use `car.setSpeed(100);` instead

</details>

## How do I print an object? { #print-object }

Pattern, added to your class:

<pre class="pattern"><code>@Override
public String toString() {
    return "<var data-label="class name">ClassName</var> [<var>field</var>: "
            + <var data-label="field value">field</var> + "]";
}</code></pre>

<div class="pattern-parts" data-replace-with="the class name and its fields" markdown>

<var data-find='"[[Car]] ['>ClassName</var>
:   The name of the class, e.g., `Car`. Inside the quotes it is text, so it appears in the output as written.

<var data-find='[[[model]]: " | + [[model]] +'>field</var>
:   A field, e.g., `model`. Inside the quotes it labels the value; outside the quotes it is the field's value. Add more fields the same way.

</div>

Example:

```java
@Override
public String toString() {
    return "Car [model: " + model + "]";
}
```

Then in `main`:

```java
System.out.println(car2);
// prints Car [model: Roadster]
// without toString(): e.g., Car@7ad041f3
```

- `println` calls `toString()` for you
- Every class already has a `toString()` (declared in `Object`) that returns something like `Car@7ad041f3`; `@Override` means that you provide a custom implementation
- The method header must be exactly `public String toString()`: `public`, named `toString`, returns a `String`, no parameters

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

1. The name must be `toString` with a capital S
    - `tostring` is a new method that `println` never calls
2. It does not compile: `error: method does not override or implement a method from a supertype`
    - `@Override` makes the compiler catch the misspelling for you

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
    // class (static)
    private static int totalCars = 0;
    // instance
    private int speed;
    // amount: parameter
    public void accelerate(int amount) {
        // local
        int newSpeed = speed + amount;
        speed = newSpeed;
    }
}
```

Fields get default values (`0`, `false`, `null`); local variables do not, so initialize them before you use them.

### Your turn

1. **Fill in the blank.** In `Car` above, which kind of variable is each of these: `amount`, `speed`, `totalCars`, `newSpeed`?
2. **What happens?** This code is inside a method:

    ```java
    int total;
    total = total + 5;
    ```

<details markdown>
<summary>Answers</summary>

1. Each one is a different kind:
    - `amount`: parameter
    - `speed`: instance variable
    - `totalCars`: class (`static`) variable
    - `newSpeed`: local variable
2. It does not compile: `error: variable total might not have been initialized`
    - Local variables get no default value
    - Write `int total = 0;`

</details>

## How do I share one value across all objects? { #static }

Pattern:

<pre class="pattern"><code>private static <var>type</var> <var data-label="field name">name</var> = <var data-label="starting value">value</var>;

public static <var>type</var> get<var data-label="capitalized">Name</var>() {
    return <var>name</var>;
}

// call on the class:
<var data-label="class name">ClassName</var>.get<var>Name</var>();</code></pre>

<div class="pattern-parts" data-replace-with="a type, a field name, a starting value, and the class name" markdown>

<var data-find="static [[int]]">type</var>
:   The data type of the `static` field, e.g., `int`.

<var data-find="int [[totalCars]] = | [[totalCars]]++ | return [[totalCars]]">name</var>
:   The name of the `static` field (class variable), written in camelCase, e.g., `totalCars`.

<var data-find="= [[0]];">value</var>
:   The starting value. It is set once for the class, not once per object.

<var data-find="get[[TotalCars]]">Name</var>
:   The field name with its first letter capitalized, e.g., `getTotalCars`.

<var data-find="[[Car]].getTotalCars">ClassName</var>
:   The name of the class, e.g., `Car`. A `static` method is invoked on the class, not on an object.

</div>

A `static` field (also known as a class variable) belongs to the class, not to individual objects: all objects share the same field. A `static` method (also known as a class method) is called using the class name, without creating an object.

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

A `static` method (e.g., `main`) cannot access instance fields directly; create an object first.

### Your turn

1. **Code tracing.** What does this print? Every `Car` constructor runs `totalCars++`.

    ```java
    Car car1 = new Car();
    Car car2 = new Car("Roadster", 2024);
    Car car3 = new Car();
    System.out.println(Car.getTotalCars());
    ```

2. **What happens?** This class does not compile. Why?

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

1. `3`
    - All cars share one `totalCars`, and each constructor call adds 1 to it
2. `count` is an instance field, and `main` is `static`: `error: non-static variable count cannot be referenced from a static context`
    - Create an object first, or make `count` `static` if all objects should share it

</details>

## How do I use `this`? { #this }

Problem: the constructor does not set the fields.

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

- **Shadowing**: the parameter `name` shadows the field `name`, so inside the constructor, `name` on its own refers to the parameter
- `name = name` assigns the parameter to itself; the field keeps its default value
- `this` refers to the current object, so `this.name` is the field

Constructor chaining: one constructor can call another with `this(...)`:

```java
public Student() { this("Unknown", 0); }
```

Important: the `this(...)` call must come first in the constructor. Java 21
(used in this course) requires that; Java 25 and later relax the rule.

### Your turn

1. **Code tracing.** `Car` has `toString()` from [Print an object](#print-object) and this constructor:

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

1. `Car [model: null]`
    - The parameters shadow the fields, so the fields are never set
    - Write `this.model = model;` and `this.year = year;`
2. `this("Unknown", 0);` calls `Car(String model, int year)`

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
if (answer.equals("yes"))  // compares text
```

A reference variable stores a reference to an object, not the object itself. `==` checks whether two references point to the same object (identity), not whether the content is equal (equality). A String read with a `Scanner` (see [Read input](basics.md#input)) is a new object, so `==` usually gives `false`, even when the text matches.

### Your turn

1. **Code tracing.** What does this print?

    ```java
    String a = "yes";
    String b = new String("yes");
    System.out.println(a == b);
    System.out.println(a.equals(b));
    System.out.println(
            "YES".equalsIgnoreCase(a));
    ```

2. **Code tracing.** What does this print?

    ```java
    Car car1 = new Car("Roadster", 2024);
    Car winner = car1;
    Car twin = new Car("Roadster", 2024);
    System.out.println(winner == car1);
    System.out.println(twin == car1);
    ```

<details markdown>
<summary>Answers</summary>

1. `false`, `true`, `true`
    - `new String` makes a second object, so `==` is `false`, but the text is the same
2. `true`, `false`
    - `winner` and `car1` refer to the same object
    - `twin` is a different object with the same field values

</details>

## How do I keep my program from crashing? { #avoid-crashes }

Predictable problem → check it; surprise problem → catch it.

Option 1: check first. Use an `if` when bad input is normal and easy to test
for before the operation runs.

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

Option 2: `try` / `catch`. Use this when the error is rare or you cannot check
for it ahead of time. The `try` block contains code that might throw an
exception; the `catch` block handles it, so the program does not crash. If an
exception of the specified type is thrown in the `try` block, Java skips the
rest of the `try` block and jumps to the `catch` block.

<pre class="pattern"><code>try {
    <var>risky statements</var>
} catch (<var data-label="exception class">ExceptionType</var> e) {
    <var>what to do instead</var>
} finally {
    // optional: always runs
}</code></pre>

<div class="pattern-parts" data-replace-with="the code that might fail, an exception type, and what to do instead" markdown>

<var data-find='[[int age = Integer.parseInt("abc"); System.out.println(age);]]'>risky statements</var>
:   The statements that might throw an exception. If one of them throws, the rest of the `try` block is skipped.

<var data-find="catch ([[NumberFormatException]]">ExceptionType</var>
:   The type of exception to catch, e.g., `NumberFormatException`. Other exceptions still crash the program.

<var data-find='[[System.out.println("Invalid number");]]'>what to do instead</var>
:   The statements that handle the exception. They run only if the exception is thrown.

</div>

Example:

```java
try {
    int age = Integer.parseInt("abc");
    System.out.println(age);
} catch (NumberFormatException e) {
    System.out.println("Invalid number");
}
```

`Integer.parseInt` converts text such as `"42"` into an `int`. `"abc"` is not a
number, so `parseInt` throws a `NumberFormatException`. Java skips
`println(age)`, runs the `catch` block, and prints `Invalid number`.

- `prices.length` is the number of elements in an array; see [Create an array](arrays.md#create-array)
- `scanner.hasNextInt()` checks whether the next input is a whole number; see [Read input](basics.md#input)

### Your turn

1. **Find the bug.** `car` is `null`. This line crashes with a `NullPointerException`. Fix it without adding a new `if`.

    ```java
    if (car.getSpeed() > 0 && car != null) {
    ```

2. **Code tracing.** What does this print? `sum` is `10` and `count` is `0`.

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

1. Swap the two conditions: `if (car != null && car.getSpeed() > 0) {`
    - Java checks `&&` from left to right and stops at the first `false`, so `getSpeed()` is never called on `null`
2. Only `count is 0`
    - Dividing an `int` by 0 throws an `ArithmeticException`, so Java jumps straight to `catch` and skips the rest of the `try` block

</details>

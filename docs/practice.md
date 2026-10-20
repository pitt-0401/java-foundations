# Practice

Nine small programs that combine several ideas from the cheat sheet. Each one has tests that check your code.

| Exercise | Practices | File |
|---|---|---|
| [1. Tip calculator](#tip-calculator) | Methods, parameters, return, printf | `TipCalculator.java` |
| [2. Leap year checker](#leap-year) | Operators, boolean, if / else | `LeapYear.java` |
| [3. Score collector](#score-collector) | Loops, Scanner, input validation | `ScoreCollector.java` |
| [4. Pet shelter, part 1](#pet-shelter-1) | Class, constructors, getters, setters | `Pet.java`, `PetShelter.java` |
| [5. Pet shelter, part 2](#pet-shelter-2) | static, this, toString() | `Pet.java`, `PetShelter.java` |
| [6. Safe average](#safe-average) | Exceptions, null checks | `SafeAverage.java` |
| [7. Score statistics](#score-statistics) | Arrays, loops, sum, max, search | `ScoreStatistics.java` |
| [8. Seating chart](#seating-chart) | 2D arrays, nested loops | `SeatingChart.java` |
| [9. Bubble sort](#bubble-sort) | Nested loops, swapping | `BubbleSort.java` |

Try each exercise on your own before you open its Review links. Compile after every few lines.

<p class="lectures">Based on in-class practice tasks, Lectures 02 to 14</p>

## How to work on an exercise { #how }

1. Open the exercise file in the `exercises` folder. It already compiles: the methods
   exist but return placeholder values, and `// TODO` comments mark what to write.
2. Run your program from the course folder (the one that contains `pom.xml`):

    ```bash
    java exercises/TipCalculator.java
    ```

    The pet shelter uses two classes, so compile both inside the `exercises` folder:

    ```bash
    cd exercises
    javac Pet.java PetShelter.java
    java PetShelter
    ```

3. Save the file, then run its tests from the course folder:

    ```bash
    mvn test -Dtest=TipCalculatorTest
    ```

    A failing test prints a message that says what it expected and what your code
    produced. When every test passes, Maven prints `BUILD SUCCESS`.

Maven compiles every exercise before it runs any test, so a compile error in any
exercise file stops all tests: fix the file named in the error first.

If your computer is set to a language that writes decimals with a comma,
`printf` prints `42,50` instead of `42.50` when you run a program yourself. The
tests always use a decimal point, so this doesn't affect them.

`mvn test` without `-Dtest` runs the tests for all nine exercises. Tests for
exercises you haven't finished yet will fail; that's expected.

Each exercise has a reference solution in the `solutions` folder. Look at it after
you've made a real attempt, and compare it with yours.

## 1. Tip calculator { #tip-calculator }

Practices: methods, parameters, return values, printf. Review:
[Write a method](basics.md#method), [Call a method](basics.md#call-method),
[Print output](basics.md#output).

1. Open `exercises/TipCalculator.java`. The class and its `main` method are already there.
2. Write `calculateTip(double bill, int percent)`. It returns the tip as a `double`.
3. Write `printReceipt(double bill, int percent)`. It calls `calculateTip` and prints
   bill, tip and total with 2 decimals.
4. In `main`, call `printReceipt(42.50, 18)` and `printReceipt(100.0, 20)`.

Expected output:

```text
Bill: 42.50
Tip (18%): 7.65
Total: 50.15
Bill: 100.00
Tip (20%): 20.00
Total: 120.00
```

**Hints:** Use `bill * percent / 100.0` to avoid int division. In `printf`,
a `%` sign is written `%%`.

File: `exercises/TipCalculator.java`. Test: `mvn test -Dtest=TipCalculatorTest`

<p class="lectures">Based on Lecture 03</p>

## 2. Leap year checker { #leap-year }

Practices: `%` and comparison operators, `&&` and `||`, if / else, boolean methods.
Review: [Write a method](basics.md#method), [Make a decision](basics.md#decision).

The rule: a year is a leap year if it is divisible by 4, except years divisible by
100, except years divisible by 400 (those are leap years again).

1. Write `isLeapYear(int year)` that returns a `boolean`. Use if / else if / else.
2. Write a second version, `isLeapYearOneLine(int year)`, with one return statement
   that uses `&&` and `||`.
3. In `main`, test 2024, 2023, 1900 and 2000 with `isLeapYear`.

Expected output:

```text
2024: true
2023: false
1900: false
2000: true
```

**Hints:** "Divisible by 4" is `year % 4 == 0`. Check the most specific rule (400)
first. Use `( )` to make the order clear.

File: `exercises/LeapYear.java`. Test: `mvn test -Dtest=LeapYearTest` (it checks
both versions)

<p class="lectures">Based on Lecture 05 (operator practice tasks)</p>

## 3. Score collector { #score-collector }

Practices: loops, Scanner, input validation. Review:
[Repeat something](basics.md#repeat), [Read input](basics.md#input).

1. Read quiz scores from the keyboard until the user enters -1.
2. A score counts only if it is a whole number from 0 to 10.
3. Anything else: print a message and ask again.
4. The program must never crash.
5. At the end, print how many scores were entered and their average with 1 decimal.
   If no scores were entered, print only the count (`You entered 0 scores.`) and
   no average.

**Before you code:** which loop fits best, and why?

Expected output, when you type 8, ten, 12, 7 and -1:

```text
Score (0-10, -1 to finish): 8
Score (0-10, -1 to finish): ten
That is not a whole number.
Score (0-10, -1 to finish): 12
Scores must be between 0 and 10.
Score (0-10, -1 to finish): 7
Score (0-10, -1 to finish): -1
You entered 2 scores.
Average: 7.5
```

**Hints:** `hasNextInt()` before `nextInt()`. Throw bad input away with `next()`. Don't
divide when the count is 0.

File: `exercises/ScoreCollector.java`. Test: `mvn test -Dtest=ScoreCollectorTest`.
The tests type the input for you.

<p class="lectures">Based on Lecture 06 (rating collector)</p>

## 4. Pet shelter, part 1 { #pet-shelter-1 }

Practices: declaring a class, constructors, getters and setters, creating objects.
Review: [Declare a class](classes-and-objects.md#class),
[Write a constructor](classes-and-objects.md#constructor),
[Create and use an object](classes-and-objects.md#object),
[Write getters and setters](classes-and-objects.md#getters-setters).

1. Draw the UML class diagram for `Pet` first.
2. Open `exercises/Pet.java` and `exercises/PetShelter.java`. `PetShelter` has the `main` method.
3. `Pet` has private fields `name`, `age` and `hunger` (0 = full, 10 = starving).
4. No-arg constructor: name `"Unknown"`, age 0, hunger 5. Second constructor
   `Pet(String name, int age)`: hunger 5.
5. Getters for all fields. `setAge` ignores negative ages.
6. `feed()`: hunger goes down by 3, never below 0. `play()`: hunger goes up by 2,
   never above 10.

Test in `main`: create Rex (age 3) and Mia (age 1). Feed Rex twice. Let Mia play once.

Expected output:

```text
Rex: 5
Rex after feed(): 2
Rex after feed(): 0
Mia after play(): 7
```

**Hint:** use `getHunger()` in `main`. `hunger` is private.

Files: `exercises/Pet.java` and `exercises/PetShelter.java`. Test:
`mvn test -Dtest=PetShelterPart1Test`. This test checks your `Pet` class directly,
not the output of `main`, so it keeps passing after you change `main` in part 2.

<p class="lectures">Based on Lectures 07, 08 (CarRace)</p>

## 5. Pet shelter, part 2 { #pet-shelter-2 }

Practices: static, this, constructor chaining, toString(). Review:
[Print an object](classes-and-objects.md#print-object),
[Share a value (`static`)](classes-and-objects.md#static),
[Use `this`](classes-and-objects.md#this).

Continue from part 1, in the same two files.

1. Add `private static int totalPets` that counts every `Pet` created.
2. Add a static method `getTotalPets()`.
3. In `Pet(String name, int age)`, use `this.name = name` and `this.age = age`.
   Count the pet here.
4. Let the no-arg constructor call `this("Unknown", 0)`.
5. Override `toString()` to match the output.
6. In `main`, create 3 pets, print each, then print the total. This replaces the
   part 1 test in `main`.

Expected output:

```text
Pet [name: Rex, age: 3, hunger: 5]
Pet [name: Mia, age: 1, hunger: 5]
Pet [name: Unknown, age: 0, hunger: 5]
Total pets: 3
```

**Think about it:** Why must `totalPets` be static? What would print if it were an
instance variable?

**Hint:** call it as `Pet.getTotalPets()`.

Files: `exercises/Pet.java` and `exercises/PetShelter.java`. Test:
`mvn test -Dtest=PetShelterPart2Test`

<p class="lectures">Based on Lectures 08, 09 (CarRace)</p>

## 6. Safe average { #safe-average }

Practices: try / catch, null checks, check first vs catch. Review:
[Avoid crashes](classes-and-objects.md#avoid-crashes),
[Read a runtime error](help.md#runtime-errors).

1. Write `printAverage(int[] values)`. It prints the int average:
   `sum / values.length`.
2. If `values` is `null`, print a message and return. Check this with `if`, before
   you loop.
3. Put the division in try / catch. Catch the `ArithmeticException` for an empty
   array.
4. In `main`, call it with `{4, 8, 6}`, with `new int[0]`, and with `null`.

Expected output:

```text
Average: 6
No values to average.
No array given.
```

**Think about it:** Remove the null check and run it. Which exception do you get,
and on which line? Could you avoid the try / catch by checking `values.length`
first? Which is better here?

File: `exercises/SafeAverage.java`. Test: `mvn test -Dtest=SafeAverageTest`

<p class="lectures">Based on Lecture 10</p>

## 7. Score statistics { #score-statistics }

Practices: arrays, for and for-each loops, sum, maximum, search. Review:
[Create an array](arrays.md#create-array), [Loop through an array](arrays.md#loop-array).

```java
int[] scores = {72, 95, 88, 64, 100, 79, 88};
```

Write one method for each task:

1. Print the whole array with `Arrays.toString`.
2. `sum(int[] a)` and `average(int[] a)`, average as a `double`.
3. `indexOfMax(int[] a)`: the position of the largest value.
4. `countAtLeast(int[] a, int limit)`: how many are `>= limit`.
5. `indexOf(int[] a, int target)`: first position, or -1 if not found.

Expected output:

```text
[72, 95, 88, 64, 100, 79, 88]
Sum: 586
Average: 83.71
Max: 100 at index 4
Scores >= 80: 4
First 88 at index: 2
```

**Hints:** `import java.util.Arrays;`. Average: `(double) sum / a.length`; print it with `%.2f`.

File: `exercises/ScoreStatistics.java`. Test: `mvn test -Dtest=ScoreStatisticsTest`

<p class="lectures">Based on Lectures 11, 12</p>

## 8. Seating chart { #seating-chart }

Practices: 2D arrays, nested loops, methods that return boolean. Review:
[Make a decision](basics.md#decision), [Use a 2D array](arrays.md#2d-array).

1. The stub already has the field `char[][] seats = new char[3][4];`.
2. In the constructor, fill every seat with `'-'` using nested loops.
3. Write `book(int row, int col)`. It returns `false` if the seat is outside the
   chart or already taken (`'X'`). Otherwise it sets `'X'` and returns `true`.
4. Write `printChart()` that prints the chart row by row.
5. Write `countFree()` that counts the `'-'` seats.
6. In `main`, create a `SeatingChart`, then `book(1, 2)` twice and `book(5, 0)` once.
   Print each result, the chart and the free seats.

Expected output:

```text
true
false
false
- - - -
- - X -
- - - -
Free seats: 11
```

**Hint:** check the range before you touch `seats[row][col]`, or you get an
`ArrayIndexOutOfBoundsException`.

File: `exercises/SeatingChart.java`. Test: `mvn test -Dtest=SeatingChartTest`

<p class="lectures">Based on Lecture 12</p>

## 9. Bubble sort { #bubble-sort }

Practices: nested loops, array indices, swapping. Review:
[Loop through an array](arrays.md#loop-array).

1. The stub's `main` already has `int[] numbers = {9, 3, 6, 2, 7, 1, 4};` and calls `sort(numbers)`.
2. Write `sort(int[] numbers)`. Compare each pair of neighbors. If the left one is
   larger, swap them.
3. Repeat passes until a pass makes no swaps.
4. Print the array after every pass that swapped.

How to swap:

```java
int temp = numbers[j];
numbers[j] = numbers[j + 1];
numbers[j + 1] = temp;
```

Expected output:

```text
Pass 1: [3, 6, 2, 7, 1, 4, 9]
Pass 2: [3, 2, 6, 1, 4, 7, 9]
Pass 3: [2, 3, 1, 4, 6, 7, 9]
Pass 4: [2, 1, 3, 4, 6, 7, 9]
Pass 5: [1, 2, 3, 4, 6, 7, 9]
```

**Hints:** after each pass the largest unsorted value is at the end. Why does the
inner loop stop at `j < n - i - 1`?

If you work with a partner, one of you types and the other gives instructions. Switch every 5 minutes.

File: `exercises/BubbleSort.java`. Test: `mvn test -Dtest=BubbleSortTest`

<p class="lectures">Based on Lectures 13, 14</p>

## How should I approach a problem? { #approach }

1. **Understand.** Say the goal in one sentence. What goes in, what comes out? Work
   one example by hand.
2. **Model.** Nouns become classes and fields. Verbs become methods. Sketch the UML.
3. **Skeleton.** Write the classes, fields and empty method stubs. Make it compile.
4. **Fill in.** One method at a time. Compile after each one.
5. **Test.** Call each method from `main` and print the result. Try edge cases: 0,
   empty, wrong input.
6. **Tidy.** Clear names, short comments, no unused code.

**Avoid the "big bang":** writing everything, then compiling once. The errors pile
up and are hard to find.

**Stuck?** Write the steps in plain English as comments first, then turn each
comment into code.

The worked example below follows these steps on one problem.

<p class="lectures">Lectures 04, 10, 13</p>

## Worked example: a playlist { #playlist }

**Problem:** A playlist holds up to 10 songs. Each song has a title and a length in
seconds. You can add songs, get the total length as minutes:seconds, and find the
longest song.

### Step 1: Understand

- **In:** songs, each with a title and seconds.
- **Out:** total time like 7:45, and the longest title.
- **By hand:** 210 + 185 + 70 = 465 seconds. 465 / 60 = 7 and 465 % 60 = 45, so
  7:45. Longest: Song A (210 seconds).

### Step 2: Model

Nouns: playlist, song, title, seconds. Verbs: add, total, find longest.

```text
+---------------------+               +------------------+
| Playlist            |   has 0..10   | Song             |
+---------------------+ ------------> +------------------+
| - songs: Song[]     |               | - title: String  |
| - count: int        |               | - seconds: int   |
+---------------------+               +------------------+
| + addSong(Song)     |               | + getTitle()     |
| + getTotalSeconds() |               | + getSeconds()   |
| + getLongest()      |               +------------------+
+---------------------+
```

`-` marks a private field, `+` a public method. A playlist has 0 to 10 songs.

### Step 3: Skeleton that compiles

`Song.java`:

```java
public class Song {
    private String title;
    private int seconds;

    public Song(String title, int seconds) {
    }
    public String getTitle() {
        return null;  // placeholder
    }
    public int getSeconds() {
        return 0;     // placeholder
    }
}
```

`Playlist.java`:

```java
public class Playlist {
    private Song[] songs = new Song[10];
    private int count = 0;

    public boolean addSong(Song song) {
        return false;
    }
    public int getTotalSeconds() {
        return 0;
    }
    public Song getLongest() {
        return null;
    }
}
```

It compiles but doesn't do anything yet, which is fine for now. The placeholder
returns (`null`, `0`, `false`) are only there so it compiles.

### Step 4: Fill in one method at a time

`Song`'s constructor and getters come first. They're short: `this.title = title;`
and `return title;`.

1. `addSong`, then compile:

    ```java
    public boolean addSong(Song song) {
        if (count == songs.length) {
            return false;  // full
        }
        songs[count] = song;
        count++;
        return true;
    }
    ```

2. `getTotalSeconds`, then compile:

    ```java
    public int getTotalSeconds() {
        int total = 0;
        for (int i = 0; i < count; i++) {
            total += songs[i].getSeconds();
        }
        return total;
    }
    ```

3. `getLongest`, then compile:

    ```java
    public Song getLongest() {
        if (count == 0) {
            return null;  // no songs yet
        }
        Song longest = songs[0];
        for (int i = 1; i < count; i++) {
            Song s = songs[i];
            if (s.getSeconds() > longest.getSeconds()) {
                longest = s;
            }
        }
        return longest;
    }
    ```

Loop to `count`, not `songs.length`: the empty slots are `null`.

### Step 5: Test in main, including edge cases

In `main`, in `PlaylistApp.java`:

```java
Playlist list = new Playlist();
System.out.println(list.getLongest());  // edge case: empty playlist
list.addSong(new Song("Song A", 210));
list.addSong(new Song("Song B", 185));
list.addSong(new Song("Song C", 70));
int total = list.getTotalSeconds();
System.out.printf("Total: %d:%02d%n", total / 60, total % 60);  // %02d: 7:05, not 7:5
System.out.println("Longest: " + list.getLongest().getTitle());
```

Expected output:

```text
null
Total: 7:45
Longest: Song A
```

If this matches what you worked out by hand in step 1, the logic works.

Also try: add an 11th song (`addSong` should return `false`). Call
`getLongest().getTitle()` on an empty playlist: what happens, and how would you
prevent it?

The finished code is in the `examples/playlist` folder. To run it:

```bash
cd examples/playlist
javac *.java
java PlaylistApp
```

<p class="lectures">Lectures 04, 07, 08, 09, 10, 11, 12</p>

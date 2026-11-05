# Arrays

An array stores multiple values of the same type in a single variable, instead
of separate variables for each value. Each value is called an element; elements
are accessed by their position (index), starting at 0. The length of an array is
fixed when it is created.

## How do I create an array? { #create-array }

<pre class="pattern"><code>// you know the values
<var>type</var>[] <var data-label="your choice">name</var> = {<var data-label="the elements">values</var>};

// you know only the length
<var data-label="element type">type</var>[] <var>name</var> = new <var>type</var>[<var data-label="how many">length</var>];</code></pre>

<div class="pattern-parts" data-replace-with="an element type, an array name, and the values or the length" markdown>

<var data-find="[[double]][] prices | [[int]][] scores | new [[int]][5] | [[Car]][] cars | new [[Car]][3]">type</var>
:   The type of the elements, e.g., `int`, `double`, or `Car`. All elements have the same type. The square brackets `[]` after the type declare an array. With `new`, repeat the type before the length.

<var data-find="double[] [[prices]] = | int[] [[scores]] = | Car[] [[cars]] =">name</var>
:   The array name, written in camelCase, e.g., `prices`. A plural name indicates that the array holds multiple values.

<var data-find="{[[0.99, 2.45, 7.32]]}">values</var>
:   The elements, separated by commas. The length is determined by the number of values between the curly braces: `{0.99, 2.45, 7.32}` has length 3.

<var data-find="new int[[[5]]] | new Car[[[3]]]">length</var>
:   The number of elements the array can hold, e.g., `5`.

</div>

Example:

```java
double[] prices = {0.99, 2.45, 7.32};
int[] scores = new int[5];  // 0 0 0 0 0
Car[] cars = new Car[3];    // null null null

double first = prices[0];   // 0.99
scores[4] = 100;            // last element
int count = prices.length;  // 3
cars[0] = new Car("Roadster", 2024);
```

Read, change, or count the elements:

| To … | Write | Example |
|---|---|---|
| read an element | <code><var>name</var>[<var>index</var>]</code> | `prices[0]` |
| change an element | <code><var>name</var>[<var>index</var>] = <var>value</var>;</code> | `scores[4] = 100;` |
| get the number of elements | <code><var>name</var>.length</code>, without `( )` | `prices.length` |

- Indices go from **0** to **length - 1**: an array of length 5 has indices 0 to 4
- The length is fixed and cannot be changed later!
- Static initialization: values in curly braces, e.g., `{0.99, 2.45, 7.32}`
- Dynamic initialization: `new` with a length, e.g., `new int[5]`
- `new` fills the array with default values: `0` for numbers, `false` for `boolean`, `null` for reference types (e.g., `String`, `Car`)
    - `new Car[3]` creates three empty slots (`null`), not three cars; store a car with `cars[0] = new Car(...)` before you use it
- Curly braces `{ }` only work in the declaration; to assign a new array later, write `prices = new double[] {1.25, 3.10};`
- Use curly braces, not square brackets as in a Python list
- `Car` is the class from [Declare a class](classes-and-objects.md#class)

### What each word means { #create-array-words }

```java
int[] scores = new int[5];
```

| Part | Meaning |
|---|---|
| `int[]` | The type: an array of `int` values. The `[]` makes it an array. |
| `scores` | The array name, written in camelCase. |
| `=` | Assigns the new array to `scores`. |
| `new int[5]` | Creates an array of length 5, filled with default values (`0`). |
| `;` | Ends the statement. |

<p class="lectures">Lecture 11</p>

### Your turn

1. **Code tracing.** What does this print?

    ```java
    int[] laps = new int[4];
    String[] names = new String[2];
    System.out.println(laps[1]);
    System.out.println(names[0]);
    System.out.println(laps.length);
    ```

    - `null` `null` `4`

        **Not quite.** Only arrays of objects, such as `String`, start out holding `null`. A new `int` array holds `0` in every element.

    - `0` `null` `4`

        **Correct.** `0`, then `null`, then `4`. A new `int` array holds zeros, and a new `String` array holds `null`, which `println` shows as the word `null`. `length` is the length you gave.

    - `0` `null` `3`

        **Not quite.** `length` is the number of elements, `4`. The last index is `3`, one less than the length.

2. **Fill in the blank.** Print the last element of the array `scores`, whatever its size: `System.out.println(scores[____]);`

3. **What happens?**

    ```java
    int[] a = {3, 6, 9};
    a[3] = 12;
    ```

<details markdown>
<summary>Answers</summary>

<ol start="2" markdown>
<li markdown="block">

`scores.length - 1`

- The last index is one less than the length, because counting starts at 0

</li>
<li markdown="block">

The program crashes with `ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3`

- The array has 3 elements, so the valid indices are 0, 1 and 2

</li>
</ol>

</details>

## How do I loop through an array? { #loop-array }

Iterating over (traversing) an array means accessing each element one by one.

When you need the index, use a standard `for` loop:

<pre class="pattern"><code>for (int i = 0; i &lt; <var data-label="array name">array</var>.length; i++) {
    // use <var>array</var>[i]
}</code></pre>

<div class="pattern-parts" data-replace-with="the name of your array" markdown>

<var data-find="i < [[nums]].length | [[nums]][i]">array</var>
:   The array to loop through, e.g., `nums`. `i` goes from 0 to `array.length - 1`, and `array[i]` is the element at index `i`.

</div>

When you only need the values, use a for-each loop (also known as enhanced
`for` loop):

<pre class="pattern"><code>for (<var>type</var> <var data-label="each value">element</var> : <var data-label="array name">array</var>) {
    // use <var>element</var>
}</code></pre>

<div class="pattern-parts" data-replace-with="the element type, a variable name, and the array" markdown>

<var data-find="for ([[int]] n">type</var>
:   The type of the elements, e.g., `int` for an `int[]` array.

<var data-find="int [[n]] : | += [[n]]; | if ([[n]] > | = [[n]];">element</var>
:   A variable that holds one element in each iteration, e.g., `n`. It is a copy: changing it does not change the array.

<var data-find="n : [[nums]])">array</var>
:   The array to loop through, e.g., `nums`.

</div>

Three common tasks, each using `int[] nums = {4, 9, 2};`.

Add up the values:

```java
int sum = 0;
for (int n : nums) {
    sum += n;
}
```

Find the largest value (starts with `nums[0]`, so the array must not be empty):

```java
int max = nums[0];
for (int n : nums) {
    if (n > max) {
        max = n;
    }
}
```

Find the index of `target` (`-1` means it is not in the array):

```java
int target = 9;
int pos = -1;
for (int i = 0; i < nums.length; i++) {
    if (nums[i] == target) {
        pos = i;
        break;
    }
}
```

- Use `i < array.length`, not `<=`: the index starts at 0, so the last index is `length - 1`
- Print the whole array with `Arrays.toString(array)` (requires `import java.util.Arrays;`)
- The loops work as described in [Repeat something](basics.md#repeat)

<p class="lectures">Lectures 11, 12</p>

### Your turn

1. **Find the bug.**

    ```java
    int[] a = {3, 6, 9};
    for (int i = 0; i <= a.length; i++) {
        System.out.println(a[i]);
    }
    ```

2. **Code tracing.** What does this print? Assume `import java.util.Arrays;` is at the top of the file.

    ```java
    int[] nums = {1, 2, 3};
    for (int n : nums) {
        n = n * 10;
    }
    System.out.println(Arrays.toString(nums));
    ```

3. **What happens?** `int[] nums = {1, 2, 3};` followed by `System.out.println(nums);`

<details markdown>
<summary>Answers</summary>

1. It prints `3`, `6`, `9` and then crashes with `ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3`
    - Change `<=` to `<` so the loop stops at the last index, 2
2. `[1, 2, 3]`
    - In a for-each loop, `n` is a copy of each value, so changing `n` does not change the array
    - To change the elements, use an index loop: `nums[i] = nums[i] * 10;`
3. It prints something like `[I@33909752`, not the values
    - That is the array's type (`[I` means an `int` array) followed by a hash code, not its contents
    - Use `Arrays.toString(nums)` to print `[1, 2, 3]`

</details>

## How do I use a 2D array? { #2d-array }

<pre class="pattern"><code><var>type</var>[][] <var data-label="array name">name</var> = new <var>type</var>[<var>rows</var>][<var>cols</var>];
<var>name</var>[<var>row</var>][<var>col</var>] = <var>value</var>;

for (int i = 0; i &lt; <var>name</var>.length; i++) {
    for (int j = 0;
            j &lt; <var>name</var>[i].length; j++) {
        // use <var>name</var>[i][j]
    }
}</code></pre>

<div class="pattern-parts" data-replace-with="a type, a name, the numbers of rows and columns, and indices" markdown>

<var data-find="[[int]][][] grid | [[int]][][] board | new [[int]][">type</var>
:   The type of the elements, e.g., `int`.

<var data-find="[[grid]] | [[board]]">name</var>
:   The array name, written in camelCase, e.g., `grid`.

<var data-find="new int[[[3]]][4]">rows</var>
:   The number of rows, e.g., `3`. Later, `name.length` returns it.

<var data-find="new int[3][[[4]]]">cols</var>
:   The number of columns in each row, e.g., `4`. Later, `name[0].length` returns it.

<var data-find="grid[[[1]]][2] | board[[[2]]][1]">row</var>
:   The row index, starting at 0.

<var data-find="grid[1][[[2]]] | board[2][[[1]]]">col</var>
:   The column index, starting at 0.

<var data-find="= [[7]];">value</var>
:   The value to store, e.g., `7`. It must match the type.

</div>

Example:

```java
int[][] grid = {{1, 2, 3},
                {4, 5, 6}};
int value = grid[1][2];     // 6
int rows = grid.length;     // 2
int cols = grid[0].length;  // 3

int[][] board = new int[3][4];  // all 0
board[2][1] = 7;
```

- The order is always **[row][column]**
- A 2D array is an "array of arrays": each row is its own array
    - `grid.length` is the number of rows; `grid[0].length` is the number of columns in row 0
- The nested loops traverse the array row by row: the outer loop picks a row, the inner loop goes through its columns

<p class="lectures">Lecture 12</p>

### Your turn

1. **Code tracing.** What does this print? Use the `grid` from the example above.

    ```java
    System.out.println(grid[0][2]);
    System.out.println(grid[1][0]);
    ```

    - `2` `4`

        **Not quite.** Indices start at 0, so column 2 is the third column. `grid[0][2]` is the third value in row 0, which is `3`.

    - `3` `2`

        **Not quite.** The first index picks the row. `grid[1][0]` is row 1, `{4, 5, 6}`, and column 0 of that row is `4`.

    - `3` `4`

        **Correct.** `3`, then `4`. The first index picks the row and the second picks the column: `grid[0][2]` is row 0, column 2, and `grid[1][0]` is row 1, column 0.

2. **Fill in the blank.** After `int[][] seats = new int[3][5];`, `seats.length` is ____ and `seats[0].length` is ____.

3. **Find the bug.** This should print every number in `grid`, but it prints only `1 2 4 5`.

    ```java
    for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid.length; j++) {
            System.out.print(grid[i][j] + " ");
        }
    }
    ```

<details markdown>
<summary>Answers</summary>

<ol start="2" markdown>
<li markdown="block">

`3` and `5`

- The first length is the number of rows; each row holds 5 columns

</li>
<li markdown="block">

The inner loop uses `grid.length`, the number of rows (2), so it never reaches column 2

- Use `j < grid[i].length`

</li>
</ol>

</details>

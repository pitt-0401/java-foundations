# Arrays

In Java, an array holds a fixed number of elements (values) of the same type in one variable.

## How do I create an array? { #create-array }

<pre class="pattern"><code>// know the values
<var>type</var>[] <var>name</var> = {<var>v1</var>, <var>v2</var>, <var>v3</var>};

// know only the size
<var>type</var>[] <var>name</var> = new <var>type</var>[<var>size</var>];

<var>name</var>[<var>index</var>]          // read
<var>name</var>[<var>index</var>] = <var>value</var>;  // write
<var>name</var>.length           // size, no ( )</code></pre>

Example:

```java
double[] prices = {0.99, 2.45, 7.32};
int[] scores = new int[5];  // 0 0 0 0 0
Car[] cars = new Car[2];    // null null

prices[0]        // 0.99
scores[4] = 100; // last slot
prices.length    // 3
cars[0] = new Car("Roadster", 2024);
```

- Indices go from **0** to **length - 1**. An array of 5 has indices 0 to 4.
- The size can't change after you create the array.
- `new` fills a new array with default values: `0` for numbers, `null` for objects such as `String` or `Car`.

<p class="lectures">Lecture 11</p>

### Your turn

1. **Predict the output.**

    ```java
    int[] laps = new int[4];
    String[] names = new String[2];
    System.out.println(laps[1]);
    System.out.println(names[0]);
    System.out.println(laps.length);
    ```

2. **Fill in the blank.** Print the last element of the array `scores`, whatever its size: `System.out.println(scores[____]);`

3. **What happens?**

    ```java
    int[] a = {3, 6, 9};
    a[3] = 12;
    ```

<details markdown>
<summary>Answers</summary>

1. `0`, then `null`, then `4`. A new `int` array holds zeros, a new `String` array holds `null`, and `length` is the size you gave.
2. `scores.length - 1`. The last index is one less than the length because counting starts at 0.
3. The program crashes with `ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3`. The array has 3 elements, so the valid indices are 0, 1 and 2.

</details>

## How do I loop through an array? { #loop-array }

When you need the index, use a `for` loop:

<pre class="pattern"><code>for (int i = 0; i &lt; <var>arr</var>.length; i++) {
    // use <var>arr</var>[i]
}</code></pre>

When you only need the values, use a for-each loop:

<pre class="pattern"><code>for (<var>type</var> <var>item</var> : <var>arr</var>) {
    // use <var>item</var>
}</code></pre>

Three common tasks:

**Sum**

```java
int sum = 0;
for (int n : nums) {
    sum += n;
}
```

**Maximum** (start from `nums[0]`, so the array must not be empty)

```java
int max = nums[0];
for (int n : nums) {
    if (n > max) {
        max = n;
    }
}
```

**Find a position** (`-1` means not found)

```java
int pos = -1;
int n = nums.length;
for (int i = 0; i < n; i++) {
    if (nums[i] == target) {
        pos = i;
        break;
    }
}
```

- Use `i < arr.length`, never `<=`. With `<=`, the last pass uses index `length`, which doesn't exist.
- Print the whole array with `Arrays.toString(arr)`. It needs `import java.util.Arrays;` at the top of the file.

<p class="lectures">Lectures 11, 12</p>

### Your turn

1. **Find the bug.**

    ```java
    int[] a = {3, 6, 9};
    for (int i = 0; i <= a.length; i++) {
        System.out.println(a[i]);
    }
    ```

2. **Predict the output.** Assume `import java.util.Arrays;` is at the top of the file.

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

1. It prints `3`, `6`, `9` and then crashes with `ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3`. Change `<=` to `<` so the loop stops at the last index, 2.
2. `[1, 2, 3]`. In a for-each loop, `n` is a copy of each value, so changing `n` doesn't change the array. To change the elements, use an index loop: `nums[i] = nums[i] * 10;`.
3. It prints something like `[I@33909752`, not the values. That is the array's type and an identifier. Use `Arrays.toString(nums)` to print `[1, 2, 3]`.

</details>

## How do I use a 2D array? { #2d-array }

<pre class="pattern"><code><var>type</var>[][] <var>name</var> = new <var>type</var>[<var>rows</var>][<var>cols</var>];
<var>name</var>[<var>row</var>][<var>col</var>] = <var>value</var>;

for (int i = 0; i &lt; <var>name</var>.length; i++) {
    for (int j = 0; j &lt; <var>name</var>[i].length; j++) {
        // use <var>name</var>[i][j]
    }
}</code></pre>

Example:

```java
int[][] grid = {{1, 2, 3},
                {4, 5, 6}};
grid[1][2]     // 6
grid.length    // 2 rows
grid[0].length // 3 columns
```

- The order is always **[row][column]**.
- A 2D array is an array of arrays: each row is its own array. That's why `grid.length` counts the rows and `grid[0].length` counts the columns in row 0.

<p class="lectures">Lecture 12</p>

### Your turn

1. **Predict the output.** Use the `grid` from the example above.

    ```java
    System.out.println(grid[0][2]);
    System.out.println(grid[1][0]);
    ```

2. **Fill in the blank.** After `int[][] seats = new int[3][5];`, what are `seats.length` and `seats[0].length`?

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

1. `3`, then `4`. The first index picks the row and the second picks the column.
2. `3` and `5`. The first size is the number of rows; each row holds 5 columns.
3. The inner loop uses `grid.length`, the number of rows (2), so it never reaches column 2. Use `j < grid[i].length`.

</details>

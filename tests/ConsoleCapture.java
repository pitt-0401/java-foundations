import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/*
 * Helper for the practice tests: runs some code and returns everything it
 * printed with System.out, so a test can compare it with the expected output.
 *
 * The returned text uses \n line endings, has trailing spaces removed from
 * every line, and has no newline at the very end.
 */
public class ConsoleCapture {

    /** Runs the code and returns what it printed. */
    public static String run(Runnable code) {
        return run("", code);
    }

    /** Runs the code with the given text as keyboard input and returns what it printed. */
    public static String run(String input, Runnable code) {
        PrintStream originalOut = System.out;
        InputStream originalIn = System.in;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try {
            System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
            System.setOut(new PrintStream(buffer, true, StandardCharsets.UTF_8));
            code.run();
        } finally {
            System.out.flush();
            System.setOut(originalOut);
            System.setIn(originalIn);
        }
        return normalize(buffer.toString(StandardCharsets.UTF_8));
    }

    /** Uses \n line endings, removes trailing spaces on each line and trailing newlines. */
    public static String normalize(String text) {
        String[] lines = text.replace("\r\n", "\n").replace('\r', '\n').split("\n", -1);
        StringBuilder result = new StringBuilder();
        for (String line : lines) {
            result.append(line.stripTrailing()).append('\n');
        }
        return result.toString().stripTrailing();
    }

    /** Joins lines with \n, to write the expected output one line at a time. */
    public static String lines(String... lines) {
        return String.join("\n", lines);
    }
}

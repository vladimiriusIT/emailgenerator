package people.spheres.emailgenerator.expression;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class ExpressionEvaluatorTests {

    @Test
    void testFirstAndLastChars() {
        Map<String, String> inputs = Map.of(
                "input1", "Jean",
                "input2", "Mignard"
        );

        String expr = "input1.firstChars(1).toLowerCase() ~ \".\" ~ input2.lastChars(3).toLowerCase()";
        String result = ExpressionEvaluator.evaluate(expr, inputs);
        assertEquals("j.ard", result);
    }

    @Test
    void testAllCharsAndToLower() {
        Map<String, String> inputs = Map.of(
                "input1", "Jean",
                "input2", "Mignard"
        );

        String expr = "input1.toLowerCase() ~ \"@\" ~ input2.toUpperCase()";
        String result = ExpressionEvaluator.evaluate(expr, inputs);
        assertEquals("jean@MIGNARD", result);
    }

    @Test
    void testStringLiterals() {
        Map<String, String> inputs = Map.of(
                "input1", "John"
        );

        String expr = "\"Email:\" ~ input1.allChars()";
        String result = ExpressionEvaluator.evaluate(expr, inputs);
        assertEquals("Email:John", result);
    }

    @Test
    void testConcatFunction() {
        Map<String, String> inputs = Map.of(
                "input1", "Jean",
                "input2", "Mignard",
                "input3", "peoplespheres",
                "input4", "io"
        );

        String expr = "concat(input1.firstChars(1).toLowerCase(), \".\", input2.lastChars(3).toLowerCase(), \"@\", input3.allChars().toLowerCase(), \".\", input4.allChars().toLowerCase())";
        String result = ExpressionEvaluator.evaluate(expr, inputs);
        assertEquals("j.ard@peoplespheres.io", result);
    }

    @Test
    void testNestedConcatAndWhitespace() {
        Map<String, String> inputs = Map.of(
                "input1", "Han",
                "input2", "Solo",
                "input3", "millennium",
                "input4", "falcon",
                "input5", "com"
        );

        String expr = "concat(input1.firstChars(1).toLowerCase(), \".\", input2.lastChars(2).toLowerCase(), \"@\", concat(input3.allChars().toLowerCase(), \"-\", input4.allChars().toLowerCase()), \".\", input5.allChars().toLowerCase())";
        String result = ExpressionEvaluator.evaluate(expr, inputs);
        assertEquals("h.lo@millennium-falcon.com", result);
    }

    @Test
    void testInvalidTokenThrowsException() {
        Map<String, String> inputs = Map.of("input1", "test");
        String expr = "input1.unknownFunc()";

        assertThrows(IllegalArgumentException.class, () -> {
            ExpressionEvaluator.evaluate(expr, inputs);
        });
    }

    @Test
    void testMissingInputDefaultsToEmpty() {
        Map<String, String> inputs = new HashMap<>();
        String expr = "input1.firstChars(2) ~ \"@domain.com\"";
        String result = ExpressionEvaluator.evaluate(expr, inputs);
        assertEquals("@domain.com", result);
    }

    @Test
    void testEmptyConcat() {
        Map<String, String> inputs = new HashMap<>();
        String expr = "concat()";
        String result = ExpressionEvaluator.evaluate(expr, inputs);
        assertEquals("", result);
    }
}

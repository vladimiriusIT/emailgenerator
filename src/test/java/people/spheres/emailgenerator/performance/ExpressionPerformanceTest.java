package people.spheres.emailgenerator.performance;

import org.junit.jupiter.api.Test;
import people.spheres.emailgenerator.expression.MultiExpressionEvaluator;


import java.util.*;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExpressionPerformanceTest {

    @Test
    void performanceTest_largeCombinationSet_shouldCompleteWithinReasonableTime() {
        Map<String, List<String>> multiInputs = new LinkedHashMap<>();
        IntStream.rangeClosed(1, 4).forEach(i -> {
            List<String> values = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                values.add("val" + i + "_" + j);
            }
            multiInputs.put("input" + i, values);
        });

        String expression = "concat(input1.allChars(), \"_\", input2.allChars(), \"@\", input3.allChars(), \".\", input4.allChars())";

        long start = System.currentTimeMillis();
        List<String> results = MultiExpressionEvaluator.evaluate(expression, multiInputs);
        long duration = System.currentTimeMillis() - start;

        System.out.println("Generated " + results.size() + " combinations in " + duration + "ms");

        assertTrue(duration < 5000, "Performance test took too long!");
    }
}


package people.spheres.emailgenerator.expression;

import java.util.*;

public class MultiExpressionEvaluator {
    public static List<String> evaluate(String expression, Map<String, List<String>> inputMap) {
        List<Map<String, String>> combinations = computeCombinations(inputMap);

        List<String> results = new ArrayList<>();
        for (Map<String, String> combo : combinations) {
            results.add(ExpressionEvaluator.evaluate(expression, combo));
        }

        return results;
    }

    private static List<Map<String, String>> computeCombinations(Map<String, List<String>> inputMap) {
        List<Map<String, String>> result = new ArrayList<>();
        generateCombinationsRecursive(inputMap, new ArrayList<>(inputMap.keySet()), 0, new HashMap<>(), result);
        return result;
    }

    private static void generateCombinationsRecursive(Map<String, List<String>> inputMap, List<String> keys, int index,
                                                      Map<String, String> current, List<Map<String, String>> result) {
        if (index == keys.size()) {
            result.add(new HashMap<>(current));
            return;
        }

        String key = keys.get(index);
        for (String value : inputMap.getOrDefault(key, List.of(""))) {
            current.put(key, value);
            generateCombinationsRecursive(inputMap, keys, index + 1, current, result);
        }
    }
}

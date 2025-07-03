package people.spheres.emailgenerator.expression;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionEvaluator {

    private static final Pattern STRING_LITERAL = Pattern.compile("^\"([^\"]*)\"$");
    private static final Pattern INPUT_PATTERN = Pattern.compile("^(input\\d+)((\\.[a-zA-Z]+\\([^)]*\\))*)$");

    public static String evaluate(String expression, Map<String, String> inputs) {
        String[] tokens = expression.split("~");
        StringBuilder result = new StringBuilder();

        for (String token : tokens) {
            token = token.trim();
            result.append(resolveToken(token, inputs));
        }

        return result.toString();
    }

    private static String resolveToken(String token, Map<String, String> inputs) {
        if (STRING_LITERAL.matcher(token).matches()) {
            return token.substring(1, token.length() - 1); // remove quotes
        }

        if (token.startsWith("concat(") && token.endsWith(")")) {
            return handleConcat(token, inputs);
        }

        Matcher matcher = INPUT_PATTERN.matcher(token);
        if (matcher.matches()) {
            String inputKey = matcher.group(1);
            String chainedFunctions = matcher.group(2);

            String value = inputs.getOrDefault(inputKey, "");

            return applyChainedFunctions(value, chainedFunctions);
        }

        throw new IllegalArgumentException("Invalid expression token: " + token);
    }

    private static String applyChainedFunctions(String value, String chained) {
        Pattern funcPattern = Pattern.compile("\\.([a-zA-Z]+)\\(([^)]*)\\)");
        Matcher matcher = funcPattern.matcher(chained);

        while (matcher.find()) {
            String function = matcher.group(1);
            String param = matcher.group(2).trim();

            switch (function) {
                case "firstChars" -> {
                    int len = Integer.parseInt(param);
                    value = value.length() >= len ? value.substring(0, len) : value;
                }
                case "lastChars" -> {
                    int len = Integer.parseInt(param);
                    value = value.length() >= len ? value.substring(value.length() - len) : value;
                }
                case "allChars" -> {
                    // no-op
                }
                case "toLowerCase" -> value = value.toLowerCase();
                case "toUpperCase" -> value = value.toUpperCase();
                default -> throw new IllegalArgumentException("Unknown function: " + function);
            }
        }

        return value;
    }

    private static String handleConcat(String token, Map<String, String> inputs) {
        String inner = token.substring("concat(".length(), token.length() - 1);
        String[] args = splitArgs(inner);

        StringBuilder sb = new StringBuilder();
        for (String arg : args) {
            sb.append(resolveToken(arg.trim(), inputs));
        }

        return sb.toString();
    }
    private static String[] splitArgs(String inner) {
        List<String> result = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        int depth = 0;
        boolean inString = false;

        for (char c : inner.toCharArray()) {
            if (c == '"' && (current.length() == 0 || current.charAt(current.length() - 1) != '\\')) {
                inString = !inString;
            }
            if (c == ',' && depth == 0 && !inString) {
                result.add(current.toString());
                current.setLength(0);
            } else {
                if (c == '(' && !inString) depth++;
                if (c == ')' && !inString) depth--;
                current.append(c);
            }
        }
        if (current.length() > 0) result.add(current.toString());

        return result.toArray(new String[0]);
    }
}



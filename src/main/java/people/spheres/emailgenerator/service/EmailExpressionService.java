package people.spheres.emailgenerator.service;

import org.springframework.stereotype.Service;
import people.spheres.emailgenerator.expression.ExpressionEvaluator;
import people.spheres.emailgenerator.model.EmailResponse;

import java.util.List;
import java.util.Map;

@Service
public class EmailExpressionService {

    public List<EmailResponse.EmailEntry> evaluateExpression(Map<String, String> inputs, String expression) {
        String result = ExpressionEvaluator.evaluate(expression, inputs);
        return List.of(new EmailResponse.EmailEntry(result, result));
    }
}

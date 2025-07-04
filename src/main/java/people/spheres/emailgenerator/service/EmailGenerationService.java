package people.spheres.emailgenerator.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import people.spheres.emailgenerator.entity.EmailTemplate;
import people.spheres.emailgenerator.entity.GeneratedEmail;
import people.spheres.emailgenerator.model.EmailResponse;
import people.spheres.emailgenerator.repository.GeneratedEmailRepository;

import java.util.*;

@Service
public class EmailGenerationService {

    private final GeneratedEmailRepository generatedEmailRepository;
    private final EmailExpressionService expressionService;

    private final GeneratedEmailService generatedEmailService;

    @Autowired
    public EmailGenerationService(GeneratedEmailRepository generatedEmailRepository,
                                  EmailExpressionService expressionService, GeneratedEmailService generatedEmailService) {
        this.generatedEmailRepository = generatedEmailRepository;
        this.expressionService = expressionService;
        this.generatedEmailService =generatedEmailService;
    }

    public EmailResponse generate(String expression, Map<String, List<String>> multiInputs) {
        List<Map<String, String>> allCombinations = computeCombinations(multiInputs);

        List<EmailResponse.EmailEntry> emails = new ArrayList<>();
        for (Map<String, String> combo : allCombinations) {
            String result = expressionService.evaluateExpression(combo, expression).get(0).getValue();
            emails.add(new EmailResponse.EmailEntry(result, result));
        }

        return new EmailResponse(emails);
    }

    @Transactional
    public EmailResponse generateAndStore(String expression, Map<String, List<String>> multiInputs, EmailTemplate template) {
        List<Map<String, String>> allCombinations = computeCombinations(multiInputs);

        List<EmailResponse.EmailEntry> emails = new ArrayList<>();
        for (Map<String, String> combo : allCombinations) {
            String result = expressionService.evaluateExpression(combo, expression).get(0).getValue();
            emails.add(new EmailResponse.EmailEntry(result, result));

            GeneratedEmail email = new GeneratedEmail();
            email.setEmail(result);
            email.setTemplate(template);
            generatedEmailService.saveIfNotExists(result, template);
        }

        return new EmailResponse(emails);
    }

    private List<Map<String, String>> computeCombinations(Map<String, List<String>> inputMap) {
        List<Map<String, String>> result = new ArrayList<>();
        generateCombinationsRecursive(inputMap, new ArrayList<>(inputMap.keySet()), 0, new HashMap<>(), result);
        return result;
    }

    private void generateCombinationsRecursive(Map<String, List<String>> inputMap, List<String> keys, int index,
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

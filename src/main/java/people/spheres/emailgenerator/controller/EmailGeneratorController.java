package people.spheres.emailgenerator.controller;

import org.springframework.web.bind.annotation.*;
import people.spheres.emailgenerator.model.EmailResponse;
import people.spheres.emailgenerator.service.EmailExpressionService;

import java.util.*;

@RestController
@RequestMapping("/generate-email")
public class EmailGeneratorController {

    private final EmailExpressionService emailExpressionService;

    public EmailGeneratorController(EmailExpressionService emailExpressionService) {
        this.emailExpressionService = emailExpressionService;
    }

    @GetMapping("/")
    public String home() {
        return "Email Generator API is running. Go to /swagger-ui.html";
    }

    @GetMapping
    public Map<String, List<EmailResponse>> generateEmail(
            @RequestParam Map<String, String> params,
            @RequestParam(name = "expression") String expression
    ) {
        // Filter out all inputN parameters
        Map<String, String> inputs = new TreeMap<>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (entry.getKey().startsWith("input")) {
                inputs.put(entry.getKey(), entry.getValue());
            }
        }

        List<EmailResponse> responses = emailExpressionService.evaluateExpression(inputs, expression);
        return Map.of("data", responses);
    }
}

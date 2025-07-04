package people.spheres.emailgenerator.controller;

import org.springframework.web.bind.annotation.*;
import people.spheres.emailgenerator.entity.EmailTemplate;
import people.spheres.emailgenerator.model.EmailResponse;
import people.spheres.emailgenerator.repository.EmailTemplateRepository;
import people.spheres.emailgenerator.service.EmailGenerationService;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/email")
public class EmailGeneratorController {

    private final EmailGenerationService emailGenerationService;
    private final EmailTemplateRepository emailTemplateRepository;

    public EmailGeneratorController(EmailGenerationService emailGenerationService,
                                    EmailTemplateRepository emailTemplateRepository) {
        this.emailGenerationService = emailGenerationService;
        this.emailTemplateRepository = emailTemplateRepository;
    }

    @GetMapping("/")
    public String home() {
        return "Email Generator API is running. Go to /swagger-ui.html";
    }

    @GetMapping
    public EmailResponse generateEmails(@RequestParam Map<String, String> flatParams) {
        String expression = flatParams.get("expression");
        if (expression == null) {
            throw new IllegalArgumentException("Missing required 'expression' parameter");
        }

        Map<String, List<String>> inputMap = flatParams.entrySet().stream()
                .filter(e -> e.getKey().startsWith("input"))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> Arrays.asList(e.getValue().split(","))
                ));

        EmailTemplate template = new EmailTemplate();
        template.setName("Ad-hoc Template");
        template.setExpression(expression);
        EmailTemplate savedTemplate = emailTemplateRepository.save(template);

        return emailGenerationService.generateAndStore(expression, inputMap, savedTemplate);
    }
}

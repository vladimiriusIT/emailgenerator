package people.spheres.emailgenerator.controller;

import org.springframework.web.bind.annotation.*;
import people.spheres.emailgenerator.entity.EmailTemplate;
import people.spheres.emailgenerator.entity.GeneratedEmail;
import people.spheres.emailgenerator.model.EmailGenerationRequest;
import people.spheres.emailgenerator.model.EmailResponse;
import people.spheres.emailgenerator.repository.EmailTemplateRepository;
import people.spheres.emailgenerator.service.EmailGenerationService;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
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

    @GetMapping("/email")
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

    @PostMapping("/email")
    public EmailResponse generateEmail(@RequestBody EmailGenerationRequest request) {
        Optional<EmailTemplate> templateOpt = emailTemplateRepository.findById(request.getTemplateId());
        if (templateOpt.isEmpty()) {
            throw new IllegalArgumentException("Template not found for ID: " + request.getTemplateId());
        }

        EmailTemplate template = templateOpt.get();
        Map<String, List<String>> multiInputs = new HashMap<>();

        request.getInputs().forEach((k, v) -> multiInputs.put(k, List.of(v)));

        return emailGenerationService.generateAndStore(template.getExpression(), multiInputs, template);
    }

    @GetMapping("/emails")
    public List<GeneratedEmail> getAllGeneratedEmails() {
        return emailGenerationService.getAllGenerated();
    }
}

package people.spheres.emailgenerator.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import people.spheres.emailgenerator.entity.EmailTemplate;
import people.spheres.emailgenerator.model.EmailResponse;
import people.spheres.emailgenerator.repository.EmailTemplateRepository;
import people.spheres.emailgenerator.service.EmailGenerationService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class EmailGenerationIntegrationTest {

    @Autowired
    private EmailGenerationService service;

    @Autowired
    private EmailTemplateRepository templateRepo;

    @Test
    void shouldGenerateAndStoreUniqueEmails() {
        EmailTemplate template = new EmailTemplate();
        template.setName("IntGen");
        template.setExpression("input1.firstChars(1)~\".\"~input2~\"@ex.com\"");

        template = templateRepo.save(template);

        Map<String, List<String>> inputs = new HashMap<>();
        inputs.put("input1", List.of("Anna"));
        inputs.put("input2", List.of("Smith"));

        EmailResponse response = service.generateAndStore(template.getExpression(), inputs, template);

        assertEquals(1, response.getData().size());
        assertEquals("A.Smith@ex.com", response.getData().get(0).getValue());
    }
}


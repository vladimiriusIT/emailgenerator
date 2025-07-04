package people.spheres.emailgenerator.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import people.spheres.emailgenerator.entity.EmailTemplate;
import people.spheres.emailgenerator.entity.GeneratedEmail;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class RepositoryIntegrationTest {

    @Autowired
    private EmailTemplateRepository emailTemplateRepository;

    @Autowired
    private GeneratedEmailRepository generatedEmailRepository;

    @Test
    @DisplayName("Test save and retrieve EmailTemplate and related GeneratedEmails")
    void testSaveAndRetrieve() {
        EmailTemplate template = new EmailTemplate();
        template.setName("Test Template");
        template.setExpression("input1.firstChars(1) ~ '@' ~ input2.allChars()");
        EmailTemplate savedTemplate = emailTemplateRepository.save(template);

        GeneratedEmail email = new GeneratedEmail();
        email.setTemplate(savedTemplate);
        email.setEmail("j@doe.com");
        generatedEmailRepository.save(email);

        List<GeneratedEmail> results = generatedEmailRepository.findByTemplateId(savedTemplate.getId());

        assertThat(results).hasSize(1);
        assertThat(results.get(0).getEmail()).isEqualTo("j@doe.com");
    }
}

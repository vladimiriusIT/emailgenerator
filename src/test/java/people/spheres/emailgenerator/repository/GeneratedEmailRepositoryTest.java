package people.spheres.emailgenerator.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import people.spheres.emailgenerator.entity.EmailTemplate;
import people.spheres.emailgenerator.entity.GeneratedEmail;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
class GeneratedEmailRepositoryTest {

    @Autowired
    private GeneratedEmailRepository repository;

    @Autowired
    private EmailTemplateRepository templateRepo;

    @Test
    void shouldSaveGeneratedEmailAndLinkToTemplate() {
        EmailTemplate template = new EmailTemplate();
        template.setName("Gen");
        template.setExpression("x~y");
        template = templateRepo.save(template);

        GeneratedEmail email = new GeneratedEmail();
        email.setEmail("test@example.com");
        email.setTemplate(template);

        repository.save(email);

        List<GeneratedEmail> emails = repository.findByTemplateId(template.getId());
        assertEquals(1, emails.size());
        assertEquals("test@example.com", emails.get(0).getEmail());
    }
}


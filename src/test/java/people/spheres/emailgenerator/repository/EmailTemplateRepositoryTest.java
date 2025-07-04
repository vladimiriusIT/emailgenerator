package people.spheres.emailgenerator.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import people.spheres.emailgenerator.entity.EmailTemplate;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class EmailTemplateRepositoryTest {

    @Autowired
    private EmailTemplateRepository repository;

    @Test
    void shouldSaveAndFindTemplate() {
        EmailTemplate template = new EmailTemplate();
        template.setName("Test Template");
        template.setExpression("input1.allChars()~\"@test.com\"");

        EmailTemplate saved = repository.save(template);

        UUID id = template.getId();
        Optional<EmailTemplate> found = repository.findById(id);
        assertTrue(found.isPresent());
        assertEquals("Test Template", found.get().getName());
    }

    @Test
    void shouldFindByName() {
        EmailTemplate template = new EmailTemplate();
        template.setName("UniqueName");
        template.setExpression("input1~input2");

        repository.save(template);

        Optional<EmailTemplate> found = repository.findByName("UniqueName");
        assertTrue(found.isPresent());
    }
}


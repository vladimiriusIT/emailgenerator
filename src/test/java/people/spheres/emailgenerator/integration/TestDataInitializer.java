package people.spheres.emailgenerator.integration;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Profile;
import people.spheres.emailgenerator.entity.AppUser;
import people.spheres.emailgenerator.entity.EmailTemplate;
import people.spheres.emailgenerator.model.UserRole;
import people.spheres.emailgenerator.repository.AppUserRepository;
import people.spheres.emailgenerator.repository.EmailTemplateRepository;

@TestConfiguration
@Profile("test")
public class TestDataInitializer {

    private final AppUserRepository userRepository;
    private final EmailTemplateRepository templateRepository;

    public TestDataInitializer(AppUserRepository userRepository, EmailTemplateRepository templateRepository) {
        this.userRepository = userRepository;
        this.templateRepository = templateRepository;
    }

    @PostConstruct
    @Transactional
    public void init() {
        System.out.println("INIT!");
        userRepository.save(new AppUser("admin", "{noop}admin123", UserRole.ADMIN));
        userRepository.save(new AppUser("user", "{noop}user123", UserRole.USER));

        templateRepository.save(new EmailTemplate("Test1", "input1~input2"));
        templateRepository.save(new EmailTemplate("Test2", "input1.firstChars(1)~input2.lastChars(2)"));
    }
}

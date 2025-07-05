package people.spheres.emailgenerator.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import people.spheres.emailgenerator.entity.AppUser;
import people.spheres.emailgenerator.entity.EmailTemplate;
import people.spheres.emailgenerator.entity.GeneratedEmail;
import people.spheres.emailgenerator.model.UserRole;
import people.spheres.emailgenerator.repository.AppUserRepository;
import people.spheres.emailgenerator.repository.EmailTemplateRepository;
import people.spheres.emailgenerator.repository.GeneratedEmailRepository;

import java.util.List;

@Component
@Profile({"dev", "docker"})
public class DataInitialize implements CommandLineRunner {

    private final AppUserRepository userRepo;
    private final EmailTemplateRepository templateRepo;
    private final GeneratedEmailRepository emailRepo;
    private final PasswordEncoder passwordEncoder;

    public DataInitialize(AppUserRepository userRepo,
                          EmailTemplateRepository templateRepo,
                          GeneratedEmailRepository emailRepo,
                          PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.templateRepo = templateRepo;
        this.emailRepo = emailRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        System.out.println("DataInitialize running...");

        if (userRepo.count() == 0) {
            userRepo.saveAll(List.of(
                    new AppUser("admin", passwordEncoder.encode("admin123"), UserRole.ADMIN),
                    new AppUser("user", passwordEncoder.encode("user123"), UserRole.USER)
            ));
            System.out.println("Default users created");
        }

        if (templateRepo.count() == 0 && emailRepo.count() == 0) {
            List<EmailTemplate> templates = List.of(
                    new EmailTemplate("FirstLast", "input1.firstChars(1)~\".\"~input2.allChars()~\"@test.com\""),
                    new EmailTemplate("LastOnly", "input2.allChars()~\"@domain.com\""),
                    new EmailTemplate("ShortForm", "input1.firstChars(2)~input2.lastChars(2)~\"@company.io\""),
                    new EmailTemplate("UpperCase", "input1.allChars().toUpperCase()~\"_\"~input2.toLowerCase()~\"@test.org\""),
                    new EmailTemplate("ComplexExpr", "input1.firstChars(1)~input2.lastChars(3)~\"@my.org\"")
            );
            templateRepo.saveAll(templates);

            List<GeneratedEmail> emails = List.of(
                    new GeneratedEmail("J.Doe@test.com", templates.get(0)),
                    new GeneratedEmail("M.Smith@test.com", templates.get(0)),
                    new GeneratedEmail("Smith@domain.com", templates.get(1)),
                    new GeneratedEmail("Brown@domain.com", templates.get(1)),
                    new GeneratedEmail("Jooe@company.io", templates.get(2)),
                    new GeneratedEmail("Alen@company.io", templates.get(2)),
                    new GeneratedEmail("JANE_smith@test.org", templates.get(3)),
                    new GeneratedEmail("BOB_jones@test.org", templates.get(3)),
                    new GeneratedEmail("Jith@my.org", templates.get(4)),
                    new GeneratedEmail("Knie@my.org", templates.get(4))
            );
            emailRepo.saveAll(emails);

            System.out.println("Sample templates and emails created");
        }
    }
}

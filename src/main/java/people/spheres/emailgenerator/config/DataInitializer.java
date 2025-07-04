package people.spheres.emailgenerator.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import people.spheres.emailgenerator.entity.EmailTemplate;
import people.spheres.emailgenerator.entity.GeneratedEmail;
import people.spheres.emailgenerator.repository.EmailTemplateRepository;
import people.spheres.emailgenerator.repository.GeneratedEmailRepository;

import java.util.List;

@Component
@Profile({"dev", "docker"})
public class DataInitializer implements CommandLineRunner {

    private final EmailTemplateRepository templateRepo;
    private final GeneratedEmailRepository emailRepo;

    public DataInitializer(EmailTemplateRepository templateRepo, GeneratedEmailRepository emailRepo) {
        this.templateRepo = templateRepo;
        this.emailRepo = emailRepo;
    }

    @Override
    public void run(String... args) {
        if (templateRepo.count() == 0 && emailRepo.count() == 0) {
            System.out.println("Inserting sample data -->");

            List<EmailTemplate> templates = List.of(
                    new EmailTemplate("Standard", "input1.firstChars(1)~\".\"~input2.allChars()~\"@example.com\""),
                    new EmailTemplate("Reverse", "input2.firstChars(1)~\".\"~input1.allChars()~\"@corp.com\""),
                    new EmailTemplate("LowerCase", "input1.firstChars(1).toLowerCase()~\"_\"~input2.toLowerCase()~\"@domain.org\""),
                    new EmailTemplate("Initials", "input1.firstChars(1)~input2.firstChars(1)~\"@init.com\""),
                    new EmailTemplate("Full", "input1.allChars()~\".\"~input2.allChars()~\"@full.net\"")
            );
            templateRepo.saveAll(templates);

            List<GeneratedEmail> generated = List.of(
                    new GeneratedEmail("j.doe@example.com", templates.get(0)),
                    new GeneratedEmail("d.johnson@example.com", templates.get(0)),
                    new GeneratedEmail("s.brown@corp.com", templates.get(1)),
                    new GeneratedEmail("b.smith@corp.com", templates.get(1)),
                    new GeneratedEmail("j_doe@domain.org", templates.get(2)),
                    new GeneratedEmail("a_smith@domain.org", templates.get(2)),
                    new GeneratedEmail("jd@init.com", templates.get(3)),
                    new GeneratedEmail("as@init.com", templates.get(3)),
                    new GeneratedEmail("john.doe@full.net", templates.get(4)),
                    new GeneratedEmail("anna.smith@full.net", templates.get(4))
            );
            emailRepo.saveAll(generated);

            System.out.println("Sample data inserted.");
        }
    }
}

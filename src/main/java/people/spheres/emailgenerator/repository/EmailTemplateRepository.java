package people.spheres.emailgenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import people.spheres.emailgenerator.entity.EmailTemplate;

import java.util.Optional;
import java.util.UUID;

public interface EmailTemplateRepository extends JpaRepository<EmailTemplate, UUID> {
    Optional<EmailTemplate> findByName(String name);
}

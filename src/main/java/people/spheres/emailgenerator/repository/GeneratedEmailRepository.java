package people.spheres.emailgenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import people.spheres.emailgenerator.entity.GeneratedEmail;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GeneratedEmailRepository extends JpaRepository<GeneratedEmail, UUID> {
    List<GeneratedEmail> findByTemplateId(UUID templateId);
    Optional<GeneratedEmail> findByEmail(String email);
}

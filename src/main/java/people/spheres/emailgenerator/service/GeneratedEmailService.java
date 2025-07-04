package people.spheres.emailgenerator.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import people.spheres.emailgenerator.entity.EmailTemplate;
import people.spheres.emailgenerator.entity.GeneratedEmail;
import people.spheres.emailgenerator.repository.GeneratedEmailRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GeneratedEmailService {

    private final GeneratedEmailRepository generatedEmailRepository;

    public GeneratedEmailService(GeneratedEmailRepository generatedEmailRepository) {
        this.generatedEmailRepository = generatedEmailRepository;
    }

    public List<GeneratedEmail> getAll() {
        return generatedEmailRepository.findAll();
    }

    public List<GeneratedEmail> getByTemplateId(UUID templateId) {
        return generatedEmailRepository.findByTemplateId(templateId);
    }

    @Transactional
    public GeneratedEmail saveIfNotExists(String email, EmailTemplate template) {
        return generatedEmailRepository.findByEmail(email)
                .orElseGet(() -> {
                    GeneratedEmail newEmail = new GeneratedEmail(email, template);
                    return generatedEmailRepository.save(newEmail);
                });
    }

    public List<GeneratedEmail> saveMultipleIfNotExists(List<String> emails, EmailTemplate template) {
        List<GeneratedEmail> saved = new ArrayList<>();
        for (String email : emails) {
            saved.add(saveIfNotExists(email, template));
        }
        return saved;
    }

    @Transactional
    public void deleteById(UUID id) {
        generatedEmailRepository.deleteById(id);
    }

    public GeneratedEmail getById(UUID id) {
        return generatedEmailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("GeneratedEmail not found with id: " + id));
    }
}


package people.spheres.emailgenerator.service;

import org.springframework.stereotype.Service;
import people.spheres.emailgenerator.entity.EmailTemplate;
import people.spheres.emailgenerator.repository.EmailTemplateRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmailTemplateService {

    private final EmailTemplateRepository repository;

    public EmailTemplateService(EmailTemplateRepository repository) {
        this.repository = repository;
    }

    public List<EmailTemplate> findAll() {
        return repository.findAll();
    }

    public Optional<EmailTemplate> findById(UUID id) {
        return repository.findById(id);
    }

    public Optional<EmailTemplate> findByName(String name) {
        return repository.findByName(name);
    }

    public EmailTemplate save(EmailTemplate template) {
        return repository.save(template);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
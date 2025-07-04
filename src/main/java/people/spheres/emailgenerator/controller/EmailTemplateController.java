package people.spheres.emailgenerator.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import people.spheres.emailgenerator.entity.EmailTemplate;
import people.spheres.emailgenerator.service.EmailTemplateService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/templates")
public class EmailTemplateController {

    private final EmailTemplateService service;

    public EmailTemplateController(EmailTemplateService service) {
        this.service = service;
    }

    @GetMapping
    public List<EmailTemplate> getAllTemplates() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmailTemplate> getTemplateById(@PathVariable UUID id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{name}")
    public ResponseEntity<EmailTemplate> getTemplateByName(@PathVariable String name) {
        return service.findByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public EmailTemplate createTemplate(@RequestBody EmailTemplate template) {
        return service.save(template);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmailTemplate> updateTemplate(@PathVariable UUID id, @RequestBody EmailTemplate template) {
        return service.findById(id)
                .map(existing -> {
                    existing.setName(template.getName());
                    existing.setExpression(template.getExpression());
                    return ResponseEntity.ok(service.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTemplate(@PathVariable UUID id) {
        if (service.findById(id).isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}


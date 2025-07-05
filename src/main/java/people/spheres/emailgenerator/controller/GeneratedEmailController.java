package people.spheres.emailgenerator.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import people.spheres.emailgenerator.entity.GeneratedEmail;
import people.spheres.emailgenerator.service.GeneratedEmailService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/generated-emails")
public class GeneratedEmailController {

    private final GeneratedEmailService generatedEmailService;

    public GeneratedEmailController(GeneratedEmailService generatedEmailService) {
        this.generatedEmailService = generatedEmailService;
    }

    @GetMapping
    public List<GeneratedEmail> getAll() {
        return generatedEmailService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneratedEmail> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(generatedEmailService.getById(id));
    }

    @GetMapping("/template/{templateId}")
    public List<GeneratedEmail> getByTemplateId(@PathVariable UUID templateId) {
        return generatedEmailService.getByTemplateId(templateId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        generatedEmailService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

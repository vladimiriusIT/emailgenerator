package people.spheres.emailgenerator.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class GeneratedEmail {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "generated_at")
    private LocalDateTime generatedAt;

    @ManyToOne
    @JoinColumn(name = "template_id")
    @JsonIgnore
    private EmailTemplate template;


    public GeneratedEmail() {}

    public GeneratedEmail(String email, EmailTemplate template) {
        this.email = email;
        this.template = template;
        this.generatedAt = LocalDateTime.now();
    }


    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public EmailTemplate getTemplate() {
        return template;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }

    public void setTemplate(EmailTemplate template) {
        this.template = template;
    }

    @PrePersist
    public void prePersist() {
        if (this.generatedAt == null) {
            this.generatedAt = LocalDateTime.now();
        }
    }

}

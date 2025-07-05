package people.spheres.emailgenerator.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class EmailTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @Lob
    private String expression;

    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GeneratedEmail> generatedEmails = new ArrayList<>();

    public EmailTemplate() {}

    public EmailTemplate(String name, String expression) {
        this.name = name;
        this.expression = expression;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public List<GeneratedEmail> getGeneratedEmails() {
        return generatedEmails;
    }

    public void setGeneratedEmails(List<GeneratedEmail> generatedEmails) {
        this.generatedEmails = generatedEmails;
    }
}

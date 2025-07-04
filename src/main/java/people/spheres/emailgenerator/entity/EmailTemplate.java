package people.spheres.emailgenerator.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class EmailTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @Lob
    private String expression;
    public EmailTemplate() {}

    public EmailTemplate(String name, String expression) {
        this.name = name;
        this.expression = expression;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getExpression() {
        return expression;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}

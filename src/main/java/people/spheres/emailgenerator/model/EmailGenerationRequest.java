package people.spheres.emailgenerator.model;

import java.util.Map;
import java.util.UUID;

public class EmailGenerationRequest {
    private UUID templateId;
    private Map<String, String> inputs;

    public UUID getTemplateId() {
        return templateId;
    }

    public void setTemplateId(UUID templateId) {
        this.templateId = templateId;
    }

    public Map<String, String> getInputs() {
        return inputs;
    }

    public void setInputs(Map<String, String> inputs) {
        this.inputs = inputs;
    }
}

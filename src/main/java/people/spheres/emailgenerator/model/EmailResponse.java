package people.spheres.emailgenerator.model;

public class EmailResponse {
    private String id;
    private String value;

    public EmailResponse() {}

    public EmailResponse(String value) {
        this.id = value;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setValue(String value) {
        this.value = value;
    }
}


package people.spheres.emailgenerator.model;

import java.util.List;

public class EmailResponse {
    private List<EmailEntry> data;

    public EmailResponse(List<EmailEntry> data) {
        this.data = data;
    }

    public List<EmailEntry> getData() {
        return data;
    }

    public void setData(List<EmailEntry> data) {
        this.data = data;
    }

    public static class EmailEntry {
        private String id;
        private String value;

        public EmailEntry() {
        }

        public EmailEntry(String id, String value) {
            this.id = id;
            this.value = value;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}

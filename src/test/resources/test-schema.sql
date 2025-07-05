CREATE TABLE app_user (
    id UUID PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

CREATE TABLE email_template (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    expression TEXT NOT NULL
);

CREATE TABLE generated_email (
    id UUID PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    generated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    template_id UUID NOT NULL,
    CONSTRAINT fk_template FOREIGN KEY (template_id) REFERENCES email_template(id)
);

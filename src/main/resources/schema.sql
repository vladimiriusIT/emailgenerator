DROP TABLE IF EXISTS generated_email CASCADE;
DROP TABLE IF EXISTS email_template CASCADE;
DROP TABLE IF EXISTS app_user CASCADE;

CREATE TABLE IF NOT EXISTS app_user (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  username VARCHAR(50) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  role VARCHAR(20) NOT NULL
);

CREATE TABLE email_template (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(100) NOT NULL,
    expression TEXT NOT NULL
);

CREATE TABLE generated_email (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    email VARCHAR(255) NOT NULL,
    generated_at TIMESTAMP DEFAULT now(),
    template_id UUID NOT NULL REFERENCES email_template(id)
);

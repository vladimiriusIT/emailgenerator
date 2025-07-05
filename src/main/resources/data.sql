-- USERS (passwords plaintext - {noop})
INSERT INTO app_user (id, username, password, role) VALUES
  (gen_random_uuid(), 'admin', '{noop}admin123', 'ROLE_ADMIN'),
  (gen_random_uuid(), 'user', '{noop}user123', 'ROLE_USER');

-- EMAIL TEMPLATES
INSERT INTO email_template (id, name, expression) VALUES
  (gen_random_uuid(), 'FirstLast', 'input1.firstChars(1)~"."~input2.allChars()~"@test.com"'),
  (gen_random_uuid(), 'LastOnly', 'input2.allChars()~"@domain.com"'),
  (gen_random_uuid(), 'ShortForm', 'input1.firstChars(2)~input2.lastChars(2)~"@company.io"'),
  (gen_random_uuid(), 'UpperCase', 'input1.allChars().toUpperCase()~"_"~input2.toLowerCase()~"@test.org"'),
  (gen_random_uuid(), 'ComplexExpr', 'input1.firstChars(1)~input2.lastChars(3)~"@my.org"');

-- GENERATED EMAILS
INSERT INTO generated_email (id, email, generated_at, template_id) VALUES
  (gen_random_uuid(), 'J.Doe@test.com', now(), (SELECT id FROM email_template WHERE name = 'FirstLast')),
  (gen_random_uuid(), 'M.Smith@test.com', now(), (SELECT id FROM email_template WHERE name = 'FirstLast')),
  (gen_random_uuid(), 'Smith@domain.com', now(), (SELECT id FROM email_template WHERE name = 'LastOnly')),
  (gen_random_uuid(), 'Brown@domain.com', now(), (SELECT id FROM email_template WHERE name = 'LastOnly')),
  (gen_random_uuid(), 'Jooe@company.io', now(), (SELECT id FROM email_template WHERE name = 'ShortForm')),
  (gen_random_uuid(), 'Alen@company.io', now(), (SELECT id FROM email_template WHERE name = 'ShortForm')),
  (gen_random_uuid(), 'JANE_smith@test.org', now(), (SELECT id FROM email_template WHERE name = 'UpperCase')),
  (gen_random_uuid(), 'BOB_jones@test.org', now(), (SELECT id FROM email_template WHERE name = 'UpperCase')),
  (gen_random_uuid(), 'Jith@my.org', now(), (SELECT id FROM email_template WHERE name = 'ComplexExpr')),
  (gen_random_uuid(), 'Knie@my.org', now(), (SELECT id FROM email_template WHERE name = 'ComplexExpr'));

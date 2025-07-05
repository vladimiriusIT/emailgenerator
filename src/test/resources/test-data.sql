INSERT INTO app_user (id, username, password, role)
VALUES
    (RANDOM_UUID(), 'admin', '{noop}admin123', 'ADMIN'),
    (RANDOM_UUID(), 'user', '{noop}user123', 'USER');

INSERT INTO email_template (id, name, expression)
VALUES
    (RANDOM_UUID(), 'FirstLast', 'input1.firstChars(1).toUpperCase() ~ input2'),
    (RANDOM_UUID(), 'LastOnly', 'input2.toLowerCase()');

INSERT INTO generated_email (id, email, template_id) VALUES
    (RANDOM_UUID(), 'J.Doe@test.com', (SELECT id FROM email_template WHERE name = 'FirstLast')),
    (RANDOM_UUID(), 'smith@domain.com', (SELECT id FROM email_template WHERE name = 'LastOnly'));

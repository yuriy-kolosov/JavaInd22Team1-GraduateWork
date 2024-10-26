-- changeset distrog:4

ALTER TABLE users DROP COLUMN role CASCADE;
DROP TABLE roles;
ALTER TABLE users ADD COLUMN role VARCHAR(255)

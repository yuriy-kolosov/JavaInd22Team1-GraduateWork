-- liquibase formatted sql

-- changeset yuriy-kolosov:9
ALTER TABLE users DROP COLUMN s_name;
ALTER TABLE users ADD COLUMN s_name VARCHAR(255);
-- liquibase formatted sql

-- changeset yuriy-kolosov:14
ALTER TABLE users ADD UNIQUE (username);

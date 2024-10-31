-- liquibase formatted sql

-- changeset yuriy-kolosov:11
ALTER TABLE ads DROP COLUMN author;
ALTER TABLE ads ADD COLUMN author INT8 REFERENCES users (id) ON DELETE CASCADE;
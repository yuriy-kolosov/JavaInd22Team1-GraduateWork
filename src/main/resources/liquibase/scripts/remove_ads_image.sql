-- liquibase formatted sql

-- changeset yuriy-kolosov:12
ALTER TABLE ads DROP COLUMN image;
ALTER TABLE ads DROP COLUMN image_length;
ALTER TABLE ads DROP COLUMN image_type;
-- liquibase formatted sql

-- changeset yuriy-kolosov:7
ALTER TABLE ads DROP COLUMN image;
ALTER TABLE ads ADD COLUMN image BYTEA;
ALTER TABLE ads ADD COLUMN image_length INT8;
ALTER TABLE ads ADD COLUMN image_type VARCHAR(255);
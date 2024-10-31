-- liquibase formatted sql

-- changeset distrog:13

ALTER TABLE users ADD COLUMN image_id bigint;
ALTER TABLE users ADD CONSTRAINT fk_user_images FOREIGN KEY (image_id) REFERENCES user_images(id);
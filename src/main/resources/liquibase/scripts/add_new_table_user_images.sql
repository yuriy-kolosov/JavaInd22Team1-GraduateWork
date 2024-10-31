-- liquibase formatted sql

-- changeset distrog:12

CREATE TABLE IF NOT EXISTS user_images (
  id bigserial PRIMARY KEY,
  file_size BIGINT NOT NULL,
  media_type VARCHAR(255) NOT NULL,
  data oid NOT NULL,
  url VARCHAR(255)
);
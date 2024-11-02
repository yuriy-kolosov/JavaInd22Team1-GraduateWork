-- liquibase formatted sql

-- changeset yuriy-kolosov:11
CREATE TABLE IF NOT EXISTS ad_image (
  id bigserial PRIMARY KEY,
  ads_id INT8 NOT NULL REFERENCES ads (id) ON DELETE CASCADE,
  file_length INT8 NOT NULL,
  media_type VARCHAR(255) NOT NULL,
  data BYTEA
);
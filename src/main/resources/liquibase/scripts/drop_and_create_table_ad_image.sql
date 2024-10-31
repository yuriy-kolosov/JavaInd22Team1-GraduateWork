-- liquibase formatted sql

-- changeset yuriy-kolosov:13
DROP TABLE ad_image;

CREATE TABLE ad_image (
  id bigserial PRIMARY KEY,
  file_length INT8 NOT NULL,
  media_type VARCHAR(255) NOT NULL,
  data BYTEA
);
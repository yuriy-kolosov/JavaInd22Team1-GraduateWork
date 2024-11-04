-- liquibase formatted sql

-- changeset mariiam:1
CREATE TABLE IF NOT EXISTS user_images (
  id bigserial PRIMARY KEY,
  file_size BIGINT NOT NULL,
  media_type VARCHAR(255) NOT NULL,
  data bytea,
  url VARCHAR(255)
);
CREATE TABLE IF NOT EXISTS users (
  id bigserial PRIMARY KEY,
  f_name VARCHAR(255) NOT NULL,
  s_name VARCHAR(255),
  l_name VARCHAR(255),
  username VARCHAR(255) NOT NULL UNIQUE,
  phone VARCHAR(255),
  reg_date timestamp,
  email VARCHAR(255),
  "role" VARCHAR(255),
  enabled BOOLEAN,
  "password" VARCHAR(255) NOT NULL,
  image_id bigint,
  CONSTRAINT fk_user_images FOREIGN KEY (image_id) REFERENCES user_images(id)
);

CREATE TABLE IF NOT EXISTS ads (
  id bigserial PRIMARY KEY,
  author INT8 REFERENCES users (id) ON DELETE CASCADE,
  price DECIMAL(8,2) NOT NULL,
  title VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS ad_image (
  id bigserial PRIMARY KEY,
  file_length INT8 NOT NULL,
  media_type VARCHAR(255) NOT NULL,
  data BYTEA
);

CREATE TABLE IF NOT EXISTS ads_ad_image (
  ad_image_id int8 REFERENCES ad_image(id),
  ads_id int8 REFERENCES ads(id)
);

CREATE TABLE IF NOT EXISTS "comments" (
  id bigserial PRIMARY KEY,
  author INT8 NOT NULL REFERENCES users (id) ON DELETE CASCADE,
  created_at  TIMESTAMP NOT NULL,
  "text" VARCHAR(255) NOT NULL
);

-- liquibase formatted sql

-- changeset yuriy-kolosov:12
CREATE TABLE IF NOT EXISTS ads_ad_image (
  ad_image_id int8 REFERENCES ad_image(id),
  ads_id int8 REFERENCES ads(id)
);
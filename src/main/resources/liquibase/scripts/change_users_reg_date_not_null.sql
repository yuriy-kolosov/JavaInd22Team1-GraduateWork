-- liquibase formatted sql

-- changeset yuriy-kolosov:10
ALTER TABLE users DROP COLUMN reg_date;
ALTER TABLE users ADD COLUMN reg_date timestamp;
-- changeset distrog:8

alter table users drop column if exists image;
alter table users add column if not exists image_id bigserial;
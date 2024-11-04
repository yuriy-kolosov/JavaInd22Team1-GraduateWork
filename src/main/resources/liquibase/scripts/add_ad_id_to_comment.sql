-- liquibase formatted sql

-- changeset distrog:2
alter table comments add column ad_id bigint;
alter table comments add constraint fk_ads foreign key (ad_id) references ads(id);
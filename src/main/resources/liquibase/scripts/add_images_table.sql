-- changeset distrog:7
CREATE TABLE IF NOT EXISTS images(
  id bigserial PRIMARY KEY,
  url varchar(255),
  file_size varchar(255),
  media_type varchar(255),
  data bytea
)
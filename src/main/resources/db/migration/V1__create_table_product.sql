CREATE TABLE product(
id BIGINT PRIMARY KEY NOT NULL,
name VARCHAR(150) NOT NULL,
description VARCHAR(1000),
value NUMERIC(10, 2) NOT NULL,
status CHAR(1) NOT NULL
);

CREATE SEQUENCE sq_product
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
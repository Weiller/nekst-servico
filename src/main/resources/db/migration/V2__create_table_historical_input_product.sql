CREATE TABLE historical_input_product(
id  BIGINT PRIMARY KEY NOT NULL,
id_product BIGINT,
quantity INTEGER NOT NULL,
reference_date DATE NOT NULL,
registered_by BIGINT,
FOREIGN KEY(id_product) references product(id)
);

CREATE SEQUENCE sq_historical_input_product
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
CREATE SCHEMA form;

CREATE TABLE form.t_field (
	id SERIAL,
	code VARCHAR(128) NOT NULL,
	name VARCHAR(128) NOT NULL,
	description VARCHAR(512),
  	creation_time TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW(),

  	CONSTRAINT pk_t_field PRIMARY KEY (id),
  	CONSTRAINT min_t_field_code CHECK (char_length(code) >= 3),
  	CONSTRAINT ne_t_field_code CHECK (TRIM(BOTH FROM code) <> ''),
  	CONSTRAINT max_t_field_code CHECK (char_length(code) <= 128),
  	CONSTRAINT uk_t_field_code UNIQUE (code),
  	CONSTRAINT min_t_field_name CHECK (char_length(name) >= 3),
  	CONSTRAINT ne_t_field_name CHECK (TRIM(BOTH FROM name) <> ''),
  	CONSTRAINT max_t_field_name CHECK (char_length(name) <= 128)
);

CREATE TABLE form.t_characteristic (
	id SERIAL,
	attribute VARCHAR(64) NOT NULL,
	"value" VARCHAR(128) NOT NULL,
	description VARCHAR(512),
  	creation_time TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW(),

  	CONSTRAINT pk_t_characteristic PRIMARY KEY (id),
  	CONSTRAINT min_t_characteristic_attribute CHECK (char_length(attribute) >= 3),
  	CONSTRAINT ne_t_characteristic_attribute CHECK (TRIM(BOTH FROM attribute) <> ''),
  	CONSTRAINT max_t_characteristic_attribute CHECK (char_length(attribute) <= 64),
  	CONSTRAINT min_t_characteristic_value CHECK (char_length("value") >= 1),
  	CONSTRAINT ne_t_characteristic_value CHECK (TRIM(BOTH FROM "value") <> ''),
  	CONSTRAINT max_t_characteristic_value CHECK (char_length("value") <= 128)
);

CREATE TABLE form.t_field_characteristic (
	id_field BIGINT,
	id_characteristic BIGINT,
	
  	CONSTRAINT pk_t_field_characteristic PRIMARY KEY (id_field, id_characteristic),
  	CONSTRAINT fk_t_field_characteristic_id_field FOREIGN KEY (id_field) REFERENCES form.t_field (id),
  	CONSTRAINT fk_t_field_characteristic_id_characteristic FOREIGN KEY (id_characteristic) REFERENCES form.t_characteristic (id)
);



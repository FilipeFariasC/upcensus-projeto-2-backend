CREATE SCHEMA module;

CREATE TABLE module.t_input_template (
	id SERIAL,
	file_type VARCHAR(10) NOT NULL,
  	creation_time TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW(),
	
  	CONSTRAINT pk_t_input_template PRIMARY KEY (id)
);

CREATE TABLE module.t_input_template_mapping (
	id_input_template BIGINT,
	id_field BIGINT,
	config VARCHAR(128),
	
  	CONSTRAINT fk_t_template_mapping_id_input_template FOREIGN KEY (id_input_template) REFERENCES module.t_input_template (id),
  	CONSTRAINT fk_t_template_mapping_id_field FOREIGN KEY (id_field) REFERENCES form.t_field (id)
);
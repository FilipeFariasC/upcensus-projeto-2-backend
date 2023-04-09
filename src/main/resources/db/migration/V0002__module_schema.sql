CREATE SCHEMA module;

CREATE TABLE module.t_template (
	id SERIAL,
	file_type VARCHAR(10) NOT NULL,
  	creation_time TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW(),
	
  	CONSTRAINT pk_t_template PRIMARY KEY (id)
);

CREATE TABLE module.t_template_mapping (
	id_template BIGINT,
	id_field BIGINT,
	config VARCHAR(128),
	
  	CONSTRAINT fk_t_template_mapping_id_template FOREIGN KEY (id_template) REFERENCES module.t_template (id),
  	CONSTRAINT fk_t_template_mapping_id_field FOREIGN KEY (id_field) REFERENCES form.t_field (id)
);
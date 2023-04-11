CREATE SCHEMA module;

CREATE TABLE module.t_template (
	id SERIAL,
	code VARCHAR(128) NOT NULL,
	name VARCHAR(128) NOT NULL,
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

CREATE TABLE module.t_module (
	id SERIAL,
	code VARCHAR(128) NOT NULL,
	name VARCHAR(128) NOT NULL,
	id_configuration BIGINT NOT NULL,
  	creation_time TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW(),
	
	CONSTRAINT pk_t_module PRIMARY KEY (id),
	CONSTRAINT fk_t_module_id_configuration FOREIGN KEY (id_configuration) REFERENCES form.t_configuration (id)
);

CREATE TABLE module.t_module_tags (
	id_module BIGINT,
	tag VARCHAR(128),
	
	CONSTRAINT fk_t_module_tags_id_module FOREIGN KEY (id_module) REFERENCES module.t_module (id)
);

CREATE TABLE module.t_module_template(
	id_module BIGINT,
	id_template BIGINT,
	
	CONSTRAINT fk_t_module_template_id_module FOREIGN KEY (id_module) REFERENCES module.t_module (id),
	CONSTRAINT fk_t_module_template_id_template FOREIGN KEY (id_template) REFERENCES module.t_template (id)
);
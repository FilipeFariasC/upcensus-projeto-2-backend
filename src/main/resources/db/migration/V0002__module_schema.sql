CREATE SCHEMA module;

CREATE TABLE module.t_template (
	id SERIAL,
	code VARCHAR(128) NOT NULL,
	name VARCHAR(128) NOT NULL,
	file_type VARCHAR(10) NOT NULL,
    creation_time TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW(),
    id_field_identifier BIGINT NOT NULL,
	
  	CONSTRAINT pk_t_template PRIMARY KEY (id),
  	CONSTRAINT uk_t_template_code UNIQUE (code),
    CONSTRAINT fk_t_template_id_field FOREIGN KEY (id_field_identifier) REFERENCES form.t_field (id)
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
	CONSTRAINT fk_t_module_id_configuration FOREIGN KEY (id_configuration) REFERENCES form.t_configuration (id),
  	CONSTRAINT uk_t_module_code UNIQUE (code)
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

CREATE TABLE module.t_answer (
    id SERIAL,
    id_module BIGINT NOT NULL,
    id_template BIGINT NOT NULL,
    id_field BIGINT NOT NULL,
    creation_time TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW(),
    identifier VARCHAR(2048) NOT NULL,
    value VARCHAR(2048) NOT NULL,
    
    CONSTRAINT pk_t_answer PRIMARY KEY (id),
    CONSTRAINT fk_t_answer_id_module FOREIGN KEY (id_module) REFERENCES module.t_module (id),
    CONSTRAINT fk_t_answer_id_template FOREIGN KEY (id_template) REFERENCES module.t_template (id),
    CONSTRAINT fk_t_answer_id_field FOREIGN KEY (id_field) REFERENCES form.t_field (id),
    CONSTRAINT uk_t_answer_id_module_id_template_id_field_identifier_value UNIQUE (id_module, id_template, id_field, identifier, value),
    CONSTRAINT ck_t_answer_identifier_not_empty CHECK (TRIM(BOTH FROM identifier) <> '')
);

CREATE TABLE module.t_error (
    id SERIAL,
    id_answer BIGINT NOT NULL,
	motive VARCHAR(64) NOT NULL,
    description VARCHAR(2048),
    creation_time TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW(),
    
    CONSTRAINT pk_t_error PRIMARY KEY (id),
    CONSTRAINT fk_t_error_id_answer FOREIGN KEY (id_answer) REFERENCES module.t_answer (id)
);


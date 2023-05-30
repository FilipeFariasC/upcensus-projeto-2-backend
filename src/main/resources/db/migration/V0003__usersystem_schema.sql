CREATE SCHEMA IF NOT EXISTS usersystem;



CREATE TABLE IF NOT EXISTS usersystem.t_usersys (
    id SERIAL,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    provider_id VARCHAR(255),
    provider VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    enabled BOOLEAN,
    creation_time TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW(),
    CONSTRAINT pk_t_usersys PRIMARY KEY (id),
    CONSTRAINT nn_t_usersys_name CHECK (name IS NOT NULL),
    CONSTRAINT min_t_usersys_name CHECK (char_length(name) >= 3),
    CONSTRAINT ne_t_usersys_name CHECK (TRIM(name) <> ''),
    CONSTRAINT max_t_usersys_name CHECK (char_length(name) <= 255)
);


CREATE TABLE IF NOT EXISTS usersystem.t_role (
    id SERIAL,
    name VARCHAR(128) NOT NULL,
    creation_time TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW(),
    CONSTRAINT pk_t_role PRIMARY KEY (id),
    CONSTRAINT nn_t_role_name CHECK (name IS NOT NULL),
    CONSTRAINT min_t_role_name CHECK (char_length(name) >= 3),
    CONSTRAINT ne_t_role_name CHECK (TRIM(name) <> ''),
    CONSTRAINT max_t_role_name CHECK (char_length(name) <= 128)
);

CREATE TABLE IF NOT EXISTS usersystem.t_usersys_role (
    id_usersys BIGINT,
    id_role BIGINT,
    CONSTRAINT pk_t_usersys_role PRIMARY KEY (id_usersys, id_role),
    CONSTRAINT fk_t_usersys_role_id_usersys FOREIGN KEY (id_usersys) REFERENCES usersystem.t_usersys (id),
    CONSTRAINT fk_t_usersys_role_id_role FOREIGN KEY (id_role) REFERENCES usersystem.t_role (id)
);

INSERT INTO usersystem.t_role (name) SELECT 'ROLE_USER' WHERE NOT EXISTS (SELECT id FROM usersystem.t_role WHERE name = 'ROLE_USER');
INSERT INTO usersystem.t_role (name) SELECT 'ADMIN' WHERE NOT EXISTS (SELECT id FROM usersystem.t_role WHERE name = 'ADMIN');
INSERT INTO usersystem.t_role (name) SELECT 'MODERATOR' WHERE NOT EXISTS (SELECT id FROM usersystem.t_role WHERE name = 'MODERATOR');










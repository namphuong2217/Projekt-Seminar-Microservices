--DROP DATABASE IF EXISTS docestatedb;
--CREATE DATABASE docestatedb;
CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START 100;
CREATE SEQUENCE IF NOT EXISTS deparment_seq;

CREATE TABLE IF NOT EXISTS department (

id BIGINT NOT NULL DEFAULT nextval('deparment_seq') PRIMARY KEY,
department_name VARCHAR(100) NOT NULL UNIQUE
);
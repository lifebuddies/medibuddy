DROP OWNED BY medibuddy_usr CASCADE;
DROP ROLE IF EXISTS medibuddy_usr;
CREATE ROLE medibuddy_usr WITH LOGIN PASSWORD 'medibuddy_pwd';
CREATE DATABASE medibuddy_dev_db;
GRANT ALL PRIVILEGES ON DATABASE medibuddy_dev_db TO medibuddy_usr;
CREATE DATABASE medibuddy_prod_db;
GRANT ALL PRIVILEGES ON DATABASE medibuddy_prod_db TO medibuddy_usr;
CREATE SCHEMA medibuddy AUTHORIZATION medibuddy_usr;

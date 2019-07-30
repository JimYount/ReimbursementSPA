-- User creation script for the bookApp database

/*
    I like to separate my scripts by use case. So I'll have a DCL, a DDL, and
    a DML script. This lets me run each script separately. 
    
    create User -> define the schema on that user -> add/manipulate data on the user
*/

-- Fresh start. Drop the user
drop user bookapp cascade;

/* Drop can remove the user from the system entirely.
    We dont' want to build our schema on the admin user, so we'll create a new one
    If we want to reset, we can drop and then recreate.
*/
-- create the user
create user bookapp
identified by p4ssw0rd
default tablespace users
temporary tablespace temp
quota 10m on users;


-- DCL: Data Control Language
-- Controls the access to data
-- Consists of two keywords: grant and revoke

-- ability to connect to another user from bookapp
grant connect to bookapp;
-- ability to create types
grant resource to bookapp;
-- We do NOT want the ability to alter and destroy types
--grant dba to bookapp;

-- ability to create a session for a transaction
grant create session to bookapp;

grant create table to bookapp;
grant create view to bookapp;


-- olders versions of Oracle SQL
-- grant select, insert, update, delete to bookapp;








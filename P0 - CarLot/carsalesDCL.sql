-- Fresh start. Drop the user
drop user jayount cascade;

-- create the user
create user jayount
identified by MY5al35Pa55
default tablespace users
temporary tablespace temp
quota 10m on users;

-- ability to connect to another user from bookapp
grant connect to jayount;
-- ability to create types
grant resource to jayount;
-- We do NOT want the ability to alter and destroy types
--grant dba to jayount;

-- ability to create a session for a transaction
grant create session to jayount;

grant create table to jayount;
grant create view to jayount;


-- olders versions of Oracle SQL
-- grant select, insert, update, delete to jayount;








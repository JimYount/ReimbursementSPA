-- Fresh start. Drop the user
drop user jayountR cascade;

-- create the user
create user jayountR
identified by R31MBUR53M3NT
default tablespace users
temporary tablespace temp
quota 10m on users;

-- ability to connect to another user from bookapp
grant connect to jayountR;
-- ability to create types
grant resource to jayountR;
-- We do NOT want the ability to alter and destroy types
--grant dba to jayount;

-- ability to create a session for a transaction
grant create session to jayountR;

grant create table to jayountR;
grant create view to jayountR;


-- olders versions of Oracle SQL
-- grant select, insert, update, delete to jayount;
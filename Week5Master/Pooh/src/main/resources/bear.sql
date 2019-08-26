drop user bear cascade;

create user bear
identified by p4ssw0rd
default tablespace users
temporary tablespace temp
quota 10m on users;

grant connect to bear;
grant resource to bear;
grant create session to bear;
grant create table to bear;
grant create view to bear;

create table bear.bear (
    bear_id number primary key,
    cave_id number,
    honeypot_id number unique,
    bear_color varchar2(25),
    breed varchar2(25),
    weight number(6,2),
    height number(6,2),
    bear_name varchar2(25)
);

create table bear.cave_id (
    cave_id_id number primary key,
    sq_footage number(6,2),
    cavetype varchar2(25),
    cave_name varchar2(25)
);

create table bear.honey_pot (
    honeypot_id number primary key,
    volume number(10,2),
    honeyamount number(10,2)
);

create table bear.parent_cub (
    parent_id number,
    cub_id number,
    constraint pk_parent_cub primary key (parent_id, cub_id),
    constraint fk_parentcub_parent foreign key (parent_id)
        references bear.bear(bear_id),
    constraint fk_parentcub_cub foreign key (cub_id)
        references bear.bear(bear_id)
);

alter table bear.bear add constraint fk_cave
    foreign key (cave_id) references bear.cave_id(cave_id_id);
alter table bear.bear add constraint fk_honey
    foreign key (honeypot_id) references bear.honey_pot(honeypot_id);
    
create sequence bear.honeypotid_seq;
create sequence bear.bearid_seq;
create sequence bear.caveid_seq;

insert into bear.cave_id (cave_id_id, sq_footage, cavetype, cave_name)
    select bear.caveid_seq.nextVal, 200, 'rock', 'JellyStone Cave' from dual;

insert into bear.honey_pot (honeypot_id, volume, honeyamount)
    select bear.honeypotid_seq.nextVal, 706, 607 from dual;
    
insert into bear.honey_pot (honeypot_id, volume, honeyamount)
    select bear.honeypotid_seq.nextVal, 300, 150 from dual;

insert into bear.bear(bear_id, cave_id, honeypot_id, bear_color,
    breed, height, weight, bear_name) select bear.bearid_seq.nextVal, 
    1, 1, 'brown', 'grizzly', 6, 200, 'Yogi' from dual;

insert into bear.bear(bear_id, cave_id, honeypot_id, bear_color,
    breed, height, weight, bear_name) select bear.bearid_seq.nextVal, 
    1, 2, 'brown', 'grizzly', 3, 70, 'Booboo' from dual;

insert into bear.parent_cub (parent_id, cub_id) values (1, 2);

commit;
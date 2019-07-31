/*
I have three options for my DDL script
1. I can connect to the user directly and run it
2. Connect to admin, use the connect command to connect to my
    user, and then run it.
3. Connect to admin, and explicitly create the table on the user
*/

--connect command
/*connect bookapp/p4ssw0rd;
create table book (id number primary key);
exit;*/
-- Explicit
--create table bookapp.book(id number primary key);

-- drop all tables to start with a clean slate
--select 'drop table '|| table_name || ' cascade constraints;' from user_tables;
drop table BOOK cascade constraints;
drop table GENRE cascade constraints;
drop table AUTHOR cascade constraints;
drop table LOGIN cascade constraints;
drop table CUSTOMER cascade constraints;
drop table ADDRESS cascade constraints;
drop table PURCHASE cascade constraints;
drop table BOOK_GENRE cascade constraints;
drop table BOOK_AUTHOR cascade constraints;
drop table EMP cascade constraints;
-- drop all my sequences to start with a clean slate
--select 'drop sequence ' || sequence_name || ';' from user_sequences;
drop sequence ADDRESS_SEQ;
drop sequence AUTHOR_SEQ;
drop sequence BOOK_SEQ;
drop sequence GENRE_SEQ;
drop sequence LOGIN_SEQ;
drop sequence PURCHASE_SEQ;
-- build my schema
create table book (
    id number(20) primary key,
    isbn10 varchar2(10) unique,
    isbn13 varchar2(14) unique,
    title varchar2(256) not null,
    price number(5,2) not null check (price>=0),
    stock number(5) not null,
    cover varchar2(2000) -- intended to hold a url to a picture
);

create table genre (
    id number(10) primary key,
    genre varchar2(256) unique not null
);

create table book_genre (
    book_id number(20),
    genre_id number(10),
    constraint pk_bookgenre primary key (book_id, genre_id),
    constraint fk_bookgenre_book foreign key (book_id) references book(id),
    constraint fk_bookgenre_genre foreign key (genre_id) references genre(id)
);

create table author (
    id number(20) primary key,
    firstname varchar2(50) not null,
    lastname varchar2(50) not null,
    aboutblurb varchar2(1000)
);

create table book_author (
    book_id number(20),
    author_id number(20),
    constraint pk_bookauthor primary key (book_id, author_id),
    constraint fk_bookauthor_book foreign key (book_id) references book(id),
    constraint fk_bookauthor_author foreign key (author_id) references author(id)
);

create table login (
    id number(20) primary key,
    username varchar2(25) unique not null,
    pswd varchar2(25) not null,
    first_name varchar2(50) not null,
    last_name varchar2(50) not null
);

create table emp (
    id number(20) primary key,
    sup_id number(20),
    title varchar2(256),
    constraint fk_sup_emp foreign key (sup_id) references emp(id),
    constraint fk_emp_login foreign key (id) references login(id)
);

create table customer (
    id number(20) primary key,
    address_id number(10) not null,
    fav_color varchar2(10) not null,
    constraint fk_customer_login foreign key(id) references login(id)
);

create table address (
    id number(10) primary key,
    lineone varchar2(100) not null,
    linetwo varchar2(100),
    city varchar2(100) not null,
    state varchar2(3) not null,
    zip varchar2(10) not null
);

create table taxrate (
    state varchar2(3) primary key,
    rate number(5,5) not null
);

create table purchase (
    id number(20) primary key,
    customer_id number(20),
    total number(7,2) default 0 check (total>=0),
    status varchar2(10),
    constraint fk_purchase_customer foreign key (customer_id) references customer(id)
);

create table purchase_book (
    purchase_id number(20),
    book_id number(20),
    quantity number(10) check (quantity >=0),
    constraints pk_purchase_book primary key(book_id, purchase_id),
    constraints fk_purchasebook_purchase foreign key (purchase_id) references purchase(id),
    constraints fk_purchasebook_book foreign key (book_id) references book(id)
);

create table reading_list (
    book_id number(20),
    cust_id number(20),
    constraint pk_readinglist primary key(book_id, cust_id),
    constraint fk_readinglist_cust foreign key (cust_id) references customer(id),
    constraint fk_readinglist_book foreign key (book_id) references book(id)
);

-- add constraints with the alter statement
alter table customer add constraint fk_addressid
    foreign key (address_id) references address(id);

-- create sequences to generate our primary keys from
create sequence book_seq;
create sequence author_seq;
create sequence genre_seq;
create sequence login_seq;
create sequence purchase_seq;
create sequence address_seq;

-- Trigger
-- A PL/SQL block that executes under certain conditions
-- Triggers can execute before or after
--      any DML statement (update, delete, insert)
create or replace trigger author_pk_trig
before insert or update on author
for each row
begin
    if INSERTING then
        select author_seq.nextVal into :new.id from dual;
    elsif UPDATING then
        select :old.id into :new.id from dual;
    end if;
end;
/
create or replace trigger book_pk_trig
before insert or update on book
for each row
begin
    if INSERTING then
        select book_seq.nextVal into :new.id from dual;
    elsif UPDATING then
        select :old.id into :new.id from dual;
    end if;
end;
/
create or replace trigger genre_pk_trig
before insert or update on genre
for each row
begin
    if INSERTING then
        select genre_seq.nextVal into :new.id from dual;
    elsif UPDATING then
        select :old.id into :new.id from dual;
    end if;
end;
/
create or replace trigger purchase_pk_trig
before insert or update on purchase
for each row
begin
    if INSERTING then
        select purchase_seq.nextVal into :new.id from dual;
    elsif UPDATING then
        select :old.id into :new.id from dual;
    end if;
end;
/
create or replace trigger login_pk_trig
before insert or update on login
for each row
begin
    if INSERTING then
        select login_seq.nextVal into :new.id from dual;
    elsif UPDATING then
        select :old.id into :new.id from dual;
    end if;
end;
/
create or replace trigger address_pk_trig
before insert or update on address
for each row
begin
    if INSERTING then
        select address_seq.nextVal into :new.id from dual;
    elsif UPDATING then
        select :old.id into :new.id from dual;
    end if;
end;
/
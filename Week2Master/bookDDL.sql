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
-- Functions
/* Functions are reusable PL/SQL blocks
    They take in parameters (in, out, or inout) (using out  
        or inout in a function is bad practice)
    They have a return value and can therefore be used
        inside of a statement.
            (select, insert, update, or delete)
    functions can call other functions
    Functions can only use DQL statements
*/
create or replace function calculateTax
    (book_id in number, cust_id in number)
return number
is -- as also works here
-- after the is/as, we have a chance to declare variables we
-- wish to use inside of the function
book_price number(10, 2);
home_state varchar2(3);
tax_rate number(5,5);
begin
    -- functions allow me to run DQL statements and return a value
    select state into home_state from customer join address
        on customer.address_id = address.id
        where customer.id = cust_id;
    select rate into tax_rate from taxrate where state=home_state;
    select price into book_price from book where id=book_id;
    return round(book_price * (1+tax_rate),2);
end;
/

create or replace function calculateTax2
    (total in number, cust_id in number)
return number
is
home_state varchar2(3);
tax_rate number(5,5);
begin
    select state into home_state from customer join address
        on customer.address_id = address.id where customer.id=cust_id;
    select rate into tax_rate from taxrate where state = home_state;
    return total * (1+ tax_rate);
end;
/

-- Stored Procedures
/*
    A PL/SQL block with a name that can be reused
    It has in, out, and inout parameters
    It has NO return value
    We can use DML, TCL, and DQL statements
    We can call other stored procedures and functions inside them.
*/
create or replace procedure add_book_to_cart
(purchase_in in number, book_in in number,
    total_out out number, updated_row out sys_refcursor)
as
-- declare variables
cust_id number(20);
book_stock number(5);
new_stock number(5);
num_in_cart number(5);
sum_purchase number(10, 2);
begin
    set transaction name 'add_book';
    select stock into book_stock from book where book.id=book_in;
    if book_stock > 0 then
        savepoint add_book;
        --time to go to work
        --1. update book stock
        update book set stock = (stock-1) where book.id=book_in;
        --2. Check to see if we already have a book.
        select count(*) into num_in_cart from purchase_book p
            where p.book_id = book_in and p.purchase_id = purchase_in;
        if num_in_cart = 0 then
            -- INSERT
            insert into purchase_book(purchase_id, book_id, quantity)
                values(purchase_in, book_in, 1);
        else
            -- UPDATE
            update purchase_book set quantity = quantity+1
                where book_id = book_in and purchase_id = purchase_in;
        end if;
        --3. Update the total
        select sum(purchase_book.quantity * book.price) into sum_purchase
            from purchase_book join book on book.id = purchase_book.book_id
            where purchase_book.purchase_id = purchase_in;
        select customer_id into cust_id from purchase where id = purchase_in;
        select calculatetax2(sum_purchase, cust_id) into total_out from dual;
        -- 4. Finally update the purchase total
        update purchase set total = total_out where id = purchase_in;
        commit; --make those changes stick
        --rollback to add_book;
    end if;
    -- sysrefcursor stuff
    -- A Cursor is a pointer to a stored result set
    open updated_row for select * from purchase_book where
        purchase_id = purchase_in;    
end;
/
create or replace procedure remove_book_from_cart
(purchase_in in number, book_in in number, total_out out number, updated_row out sys_refcursor)
as
--declare variables
cust_id number(20);
book_stock number(5);
num_in_cart number(5);
sum_purchase number(10,2);
begin
  --create a spot to save our current state.
  savepoint remove_book;
  --update book stock
  update book set stock= ((select stock from book where book.id=book_in)+1)
      where book.id=book_in;
  --remove the book from the shopping cart
  select count(*) into num_in_cart from purchase_book p
    where p.book_id = book_in and p.purchase_id = purchase_in;
    if num_in_cart > 0 then
      --update
      update purchase_book set quantity = quantity-1
        where book_id = book_in and purchase_id = purchase_in;
      select quantity into book_stock from purchase_book p
        where p.book_id = book_in and p.purchase_id = purchase_in;
      if book_stock < 1 then
        delete from purchase_book where book_id=book_in and purchase_id = purchase_in;
      end if;
      -- update total
      select SUM(purchase_book.quantity*book.PRICE) into sum_purchase from purchase_book join book on book.ID=purchase_book.BOOK_ID where purchase_book.purchase_id = purchase_in;
      select customer_id into cust_id from purchase where id=purchase_in;
      select calculatetax2(sum_purchase,cust_id) into total_out from dual;
      update purchase set total=total_out where id=purchase_in;
    else
      -- failure
      rollback to remove_book;
    end if;
  open updated_row for select * from purchase_book where purchase_id=purchase_in;
end remove_book_from_cart;
/

create or replace procedure empty_cart
(purch_id IN number)
as
cursor purchases
    is
        select book_id, quantity from purchase_book where purchase_id = purch_id;
begin
    for res in purchases
    loop
        update book set stock = stock + res.quantity where id = res.book_id;
        delete from purchase_book where book_id = res.book_id and purchase_id = purch_id;
    end loop;
    delete from purchase where id = purch_id;
    commit;
end empty_cart;
/
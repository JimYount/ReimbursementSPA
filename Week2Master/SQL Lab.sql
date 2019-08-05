-- 1. Done

-- 2.1
SELECT * FROM EMPLOYEE;
SELECT * FROM EMPLOYEE WHERE LASTNAME = 'King';
SELECT * FROM EMPLOYEE WHERE LASTNAME = 'King' AND REPORTSTO IS NULL;

-- 2.2
SELECT * FROM ALBUM ORDER BY TITLE DESC;
SELECT FIRSTNAME FROM CUSTOMER ORDER BY CITY;

-- 2.3
INSERT INTO GENRE (GENREID, NAME) VALUES(26, 'Bluegrass');
INSERT INTO GENRE (GENREID, NAME) VALUES(27, 'Folk');

-- 2.4
UPDATE CUSTOMER SET FIRSTNAME = 'Robert', LASTNAME = 'Walter' WHERE FIRSTNAME = 'Aaron' AND LASTNAME = 'Mitchell';
UPDATE ARTIST SET NAME = 'CCR' WHERE NAME = 'Creedence Clearwater Revival';

-- 2.5
SELECT * FROM INVOICE WHERE BILLINGADDRESS LIKE 'T%';

-- 2.6
SELECT * FROM INVOICE WHERE TOTAL BETWEEN '15' AND '50'; 
SELECT * FROM EMPLOYEE WHERE HIREDATE BETWEEN '01-JUN-03' AND '1-MAR-04';

-- 2.7
-- I did this lab question this way despite the way most people talked about doing it because 
-- you should never delete financial records from a business in case there is an audit.
-- That is bad bookkeeping. It does not sit right with me.
INSERT INTO CUSTOMER (CUSTOMERID, FIRSTNAME, LASTNAME, EMAIL) VALUES(0, 'PreviousCustomer', 'PreviousCustomer', '@gmail.com');
UPDATE INVOICE SET CUSTOMERID = 0 WHERE CUSTOMERID = (SELECT CUSTOMERID FROM CUSTOMER WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter');
DELETE FROM CUSTOMER WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter';

-- 3.1
SELECT CURRENT_TIMESTAMP FROM DUAL;
SELECT LENGTH(MEDIATYPEID), LENGTH(NAME) FROM MEDIATYPE;

-- 3.2
SELECT AVG(TOTAL) FROM INVOICE;
SELECT * FROM TRACK WHERE UNITPRICE = (SELECT MAX(UNITPRICE) FROM TRACK);

-- 3.3
SELECT AVG(UNITPRICE) FROM INVOICELINE;

-- 3.4
SELECT * FROM EMPLOYEE WHERE BIRTHDATE >= '01-JAN-69';

-- 4.1
create or replace procedure out_name
as 
begin
    for fn in (select FIRSTNAME, LASTNAME FROM EMPLOYEE)
    loop
        dbms_output.put(fn.FIRSTNAME || ' ');
        dbms_output.put_line(fn.LASTNAME);
    end loop;
end;
/

exec out_name;

-- 4.2
create or replace procedure update_employee
(employee_id in number, repl_title in varchar2, repl_reportsto in number, repl_birthdate in varchar2, repl_hiredate in date, repl_address in varchar2, 
repl_city in varchar2, repl_state in varchar2, repl_country in varchar2, repl_postalcode in varchar2, repl_phone in varchar2, repl_fax in varchar2)
as
begin
    update EMPLOYEE set TITLE = repl_title, REPORTSTO = new_reportsto, BIRTHDATE = new_birthdate, HIREDATE = repl_hiredate, ADDRESS = repl_address, 
    CITY = repl_city, STATE = repl_state, COUNTRY = repl_country, POSTALCODE = repl_postalcode, PHONE = repl_phone, FAX = repl_fax;
end;
/

exec update_employee;


create or replace procedure name_manager
    (employee_id in number)
as 
    manager_id number;
begin
    select reportsto into manager_id from employee where employee_id = employeeid;
end;
/

exec name_manager;

-- 4.3
create or replace procedure name_company
as 
begin
    for fn in (select FIRSTNAME, LASTNAME, COMPANY FROM CUSTOMER)
    loop
        dbms_output.put(fn.FIRSTNAME || ' ');
        dbms_output.put(fn.LASTNAME || ', Company:');
        dbms_output.put_line(fn.COMPANY);
    end loop;
end;
/

exec name_company;

-- 5.0


-- 6.1
create or replace trigger employee_pk_trig
after insert on EMPLOYEE
for each row
begin
    dbms_output.put_line('An employee was inserted.');
end;
/

create or replace trigger album_pk_trig
after update on ALBUM
for each row
begin
    dbms_output.put_line('An album was updated.');
end;
/

create or replace trigger customer_pk_trig
after delete on CUSTOMER
for each row
begin
    dbms_output.put_line('A customer was deleted.');
end;
/

-- 7.1
SELECT FIRSTNAME, LASTNAME, INVOICEID FROM CUSTOMER INNER JOIN INVOICE ON CUSTOMER.CUSTOMERID = INVOICE.CUSTOMERID;

-- 7.2
SELECT CUSTOMER.CUSTOMERID, FIRSTNAME, LASTNAME, INVOICEID, TOTAL FROM CUSTOMER LEFT OUTER JOIN INVOICE ON CUSTOMER.CUSTOMERID = INVOICE.CUSTOMERID;

-- 7.3
SELECT NAME, TITLE FROM ALBUM RIGHT OUTER JOIN ARTIST ON ALBUM.ARTISTID = ARTIST.ARTISTID;

-- 7.4
SELECT * FROM ALBUM, ARTIST ORDER BY NAME ASC;

-- 7.5
SELECT * FROM EMPLOYEE a, EMPLOYEE b WHERE a.REPORTSTO = b.REPORTSTO;
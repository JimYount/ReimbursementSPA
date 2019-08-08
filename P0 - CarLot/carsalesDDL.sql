DROP TABLE Offers;
DROP TABLE Cars;
DROP TABLE Payments;
DROP TABLE CarUsers;

CREATE TABLE CarUsers
(
    CarUserID NUMBER NOT NULL,
    FirstName VARCHAR2(120),
    LastName VARCHAR2(120),
    UserName VARCHAR2(120),
    Password VARCHAR2(120),
    AccountType NUMBER NOT NULL,
    CONSTRAINT PK_CarUserID PRIMARY KEY  (CarUserID)
);

CREATE TABLE Cars
(
    CarID NUMBER NOT NULL,
    Price NUMBER(9,2) NOT NULL,
    PayMonths NUMBER,
    Make VARCHAR2(120),
    Model VARCHAR2(120),
    Color VARCHAR2(120),
    Year NUMBER NOT NULL,
    OwnerID NUMBER,
    BoughtYear NUMBER,
    BoughtMonth NUMBER,
    PicURL VARCHAR2(120),
    CONSTRAINT PK_CarID PRIMARY KEY  (CarID),
    CONSTRAINT FK_OwnerID FOREIGN KEY (OwnerID) REFERENCES CarUsers(CarUserID) ON DELETE CASCADE
);

CREATE TABLE Offers
(
    OfferID NUMBER NOT NULL,
    OfferCarID NUMBER NOT NULL,
    OfferCarUserID NUMBER NOT NULL,
    Offer NUMBER(9,2) NOT NULL,
    CONSTRAINT PK_OfferID PRIMARY KEY  (OfferID),
    CONSTRAINT FK_OfferCarID FOREIGN KEY (OfferCarID) REFERENCES Cars(CarID) ON DELETE CASCADE,
    CONSTRAINT FK_OfferCarUserID FOREIGN KEY (OfferCarUserID) REFERENCES CarUsers(CarUserID) ON DELETE CASCADE
);

CREATE TABLE Payments
(
    PaymentID  NUMBER NOT NULL,
    PayerID NUMBER NOT NULL,
    PaidAmount NUMBER(9,2),
    CONSTRAINT PK_PaymentID PRIMARY KEY  (PaymentID),
    CONSTRAINT FK_PayerID FOREIGN KEY (PayerID) REFERENCES CarUsers(CarUserID) ON DELETE CASCADE
);

drop sequence carUser_seq;
drop sequence cars_seq;
drop sequence offers_seq;
drop sequence payments_seq;

create sequence carUser_seq;
create sequence cars_seq;
create sequence offers_seq;
create sequence payments_seq;

create or replace trigger carUser_pk_trig
before insert or update on CarUsers
for each row
begin
    if INSERTING then
        select carUser_seq.nextVal into :new.CarUserID from dual;
    elsif UPDATING then
        select :old.CarUserID into :new.CarUserID from dual;
    end if;
end;
/

create or replace trigger cars_pk_trig
before insert or update on Cars
for each row
begin
    if INSERTING then
        select cars_seq.nextVal into :new.CarID from dual;
    elsif UPDATING then
        select :old.CarID into :new.CarID from dual;
    end if;
end;
/

create or replace trigger offers_pk_trig
before insert or update on Offers
for each row
begin
    if INSERTING then
        select offers_seq.nextVal into :new.OfferID from dual;
    elsif UPDATING then
        select :old.OfferID into :new.OfferID from dual;
    end if;
end;
/

create or replace trigger payments_pk_trig
before insert or update on Payments
for each row
begin
    if INSERTING then
        select payments_seq.nextVal into :new.PaymentID from dual;
    elsif UPDATING then
        select :old.PaymentID into :new.PaymentID from dual;
    end if;
end;
/

create or replace procedure out_name
as 
begin
    for fn in (select FirstName, LastName FROM CarUsers)
    loop
        dbms_output.put(fn.FirstName || ' ');
        dbms_output.put_line(fn.LastName);
    end loop;
end;
/

commit;
exit;
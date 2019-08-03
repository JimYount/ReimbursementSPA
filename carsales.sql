
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
    Price NUMBER NOT NULL,
    PayMonths NUMBER,
    Make VARCHAR2(120),
    Model VARCHAR2(120),
    Color VARCHAR2(120),
    Year NUMBER NOT NULL,
    Quantity NUMBER,
    OwnerID NUMBER,
    BoughtYear NUMBER,
    BoughtMonth NUMBER,
    PicURL VARCHAR2(120),
    CONSTRAINT PK_CarID PRIMARY KEY  (CarID),
    CONSTRAINT FK_OwnerID FOREIGN KEY (OwnerID) REFERENCES CarUsers(CarUserID)
);

CREATE TABLE Offers
(
    OfferID NUMBER NOT NULL,
    OfferCarID NUMBER NOT NULL,
    OfferCarUserID NUMBER NOT NULL,
    Offer NUMBER NOT NULL,
    CONSTRAINT PK_OfferID PRIMARY KEY  (OfferID),
    CONSTRAINT FK_OfferCarID FOREIGN KEY (OfferCarID) REFERENCES Cars(CarID),
    CONSTRAINT FK_OfferCarUserID FOREIGN KEY (OfferCarUserID) REFERENCES CarUsers(CarUserID)
);

CREATE TABLE Payments
(
    PaymentID  NUMBER NOT NULL,
    PayerID NUMBER NOT NULL,
    PaidAmount NUMBER,
    CONSTRAINT PK_PaymentID PRIMARY KEY  (PaymentID),
    CONSTRAINT FK_PayerID FOREIGN KEY (PayerID) REFERENCES CarUsers(CarUserID)
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

INSERT INTO CarUsers (CarUserID, FirstName, LastName, UserName, Password, AccountType) VALUES (1, 'Jim', 'Yount', 'jay', 'MyPa55', 3);
INSERT INTO CarUsers (CarUserID, FirstName, LastName, UserName, Password, AccountType) VALUES (2, 'Buddy', 'Guy', 'bud', 'MyPa55', 2);
INSERT INTO CarUsers (CarUserID, FirstName, LastName, UserName, Password, AccountType) VALUES (3, 'Hey', 'Jude', 'jude', 'MyPa55', 1);

commit;
exit;
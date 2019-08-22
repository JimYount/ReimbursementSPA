DROP TABLE EventTypes CASCADE CONSTRAINTS;
DROP TABLE GradeFormats CASCADE CONSTRAINTS;
DROP TABLE Submission CASCADE CONSTRAINTS;
DROP TABLE Employees CASCADE CONSTRAINTS;
DROP TABLE Department CASCADE CONSTRAINTS;
DROP TABLE Comments CASCADE CONSTRAINTS;

CREATE TABLE Department
(
    DepartmentID NUMBER NOT NULL,
    DeptName VARCHAR2 (120),
    DeptHeadID NUMBER,
    CONSTRAINT PK_DepartmentID PRIMARY KEY  (DepartmentID)
);

CREATE TABLE Employees
(
    EmployeeID NUMBER NOT NULL,
    Email VARCHAR2 (120) NOT NULL,
    UserName VARCHAR2 (120) NOT NULL,
    UserPass VARCHAR2 (120) NOT NULL,
    FirstName VARCHAR2 (120) NOT NULL,
    LastName VARCHAR2 (120) NOT NULL,
    Supervisor NUMBER,
    DepartmentID NUMBER NOT NULL,
    CONSTRAINT PK_EmployeeID PRIMARY KEY  (EmployeeID),
    CONSTRAINT FK_DepartmentID FOREIGN KEY (DepartmentID) REFERENCES Department(DepartmentID) ON DELETE CASCADE
);

CREATE TABLE GradeFormats
(
    FormatID NUMBER NOT NULL,
    GradeCutoff NUMBER,
    Presentation NUMBER NOT NULL,
    CONSTRAINT PK_FormatID PRIMARY KEY  (FormatID)
);

CREATE TABLE EventTypes
(
    EventTypeID NUMBER NOT NULL,
    EventName VARCHAR2 (120) NOT NULL,
    EventPercent NUMBER NOT NULL,
    CONSTRAINT PK_EventTypeID PRIMARY KEY  (EventTypeID)
);

CREATE TABLE Submission
(
    SubmissionID NUMBER NOT NULL, 
    EmployeeID NUMBER NOT NULL,
    EventTypeID NUMBER NOT NULL,
    SubmitDate DATE NOT NULL, 
    Locale VARCHAR2 (300) NOT NULL, 
    Descpription VARCHAR2 (300) NOT NULL, 
    ReimAmount NUMBER NOT NULL, 
    GradeFormatID NUMBER NOT NULL, 
    Justification VARCHAR2 (300) NOT NULL, 
    OptEvtAttach VARCHAR2 (120), 
    OptDaysMissed NUMBER,
    Status NUMBER NOT NULL,
    Passed NUMBER,
    ApprSubDate DATE,
    CONSTRAINT PK_SubmissionID PRIMARY KEY  (SubmissionID),
    CONSTRAINT FK_GradeFormatID FOREIGN KEY (GradeFormatID) REFERENCES GradeFormats(FormatID) ON DELETE CASCADE,
    CONSTRAINT FK_EmployeeID FOREIGN KEY (EmployeeID) REFERENCES Employees(EmployeeID) ON DELETE CASCADE,
    CONSTRAINT FK_EventTypeID FOREIGN KEY (EventTypeID) REFERENCES EventTypes(EventTypeID) ON DELETE CASCADE
);

CREATE TABLE Comments
(
    CommentID NUMBER NOT NULL,
    Comments VARCHAR2 (300),
    SubmissionID NUMBER NOT NULL,
    CommenterID NUMBER NOT NULL,
    CONSTRAINT PK_CommentID PRIMARY KEY  (CommentID),
    CONSTRAINT FK_SubmissionID FOREIGN KEY (SubmissionID) REFERENCES Submission(SubmissionID) ON DELETE CASCADE,
    CONSTRAINT FK_CommenterID FOREIGN KEY (CommenterID) REFERENCES Employees(EmployeeID) ON DELETE CASCADE
);

ALTER TABLE Department ADD CONSTRAINT FK_DeptHeadID FOREIGN KEY (DeptHeadID) REFERENCES Employees(EmployeeID) ON DELETE CASCADE;


drop sequence eventtypes_seq;
drop sequence department_seq;
drop sequence submission_seq;
drop sequence gradeformats_seq;
drop sequence employees_seq;
drop sequence comments_seq;

create sequence eventtypes_seq;
create sequence department_seq;
create sequence submission_seq;
create sequence gradeformats_seq;
create sequence employees_seq;
create sequence comments_seq;


create or replace trigger comments_pk_trig
before insert or update on Comments
for each row
begin
    if INSERTING then
        select comments_seq.nextVal into :new.CommentID from dual;
    elsif UPDATING then
        select :old.CommentID into :new.CommentID from dual;
    end if;
end;
/

create or replace trigger eventtypes_pk_trig
before insert or update on EventTypes
for each row
begin
    if INSERTING then
        select eventtypes_seq.nextVal into :new.EventTypeID from dual;
    elsif UPDATING then
        select :old.EventTypeID into :new.EventTypeID from dual;
    end if;
end;
/


create or replace trigger department_pk_trig
before insert or update on Department
for each row
begin
    if INSERTING then
        select department_seq.nextVal into :new.DepartmentID from dual;
    elsif UPDATING then
        select :old.DepartmentID into :new.DepartmentID from dual;
    end if;
end;
/

create or replace trigger submission_pk_trig
before insert or update on Submission
for each row
begin
    if INSERTING then
        select submission_seq.nextVal into :new.SubmissionID from dual;
    elsif UPDATING then
        select :old.SubmissionID into :new.SubmissionID from dual;
    end if;
end;
/

create or replace trigger gradeformats_pk_trig
before insert or update on GradeFormats
for each row
begin
    if INSERTING then
        select gradeformats_seq.nextVal into :new.FormatID from dual;
    elsif UPDATING then
        select :old.FormatID into :new.FormatID from dual;
    end if;
end;
/

create or replace trigger employees_pk_trig
before insert or update on Employees
for each row
begin
    if INSERTING then
        select employees_seq.nextVal into :new.EmployeeID from dual;
    elsif UPDATING then
        select :old.EmployeeID into :new.EmployeeID from dual;
    end if;
end;
/

commit;
exit;
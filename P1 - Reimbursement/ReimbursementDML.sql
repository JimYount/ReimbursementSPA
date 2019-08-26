INSERT INTO EventTypes (EventTypeID, EventName, EventPercent) VALUES('1', 'University Courses', '80');
INSERT INTO EventTypes (EventTypeID, EventName, EventPercent) VALUES('2', 'Seminars', '60');
INSERT INTO EventTypes (EventTypeID, EventName, EventPercent) VALUES('3', 'Certification Preparation Classes', '75');
INSERT INTO EventTypes (EventTypeID, EventName, EventPercent) VALUES('4', 'Certification', '100');
INSERT INTO EventTypes (EventTypeID, EventName, EventPercent) VALUES('5', 'Technical Training', '90');
INSERT INTO EventTypes (EventTypeID, EventName, EventPercent) VALUES('6', 'Other', '30');

INSERT INTO Department (DepartmentID, DeptName) VALUES('1', 'Management');
INSERT INTO Department (DepartmentID, DeptName) VALUES('2', 'Human Resources');
INSERT INTO Department (DepartmentID, DeptName) VALUES('3', 'Advertising');
INSERT INTO Department (DepartmentID, DeptName) VALUES('4', 'Accounting');

INSERT INTO Employees (EmployeeID, Email, UserName, UserPass, FirstName, LastName, Supervisor, DepartmentID) VALUES(
    '1', 'jimayount@gmail.com', 'jayount', 'MyPa55', 'Jim', 'Yount', '-1', '1');
INSERT INTO Employees (EmployeeID, Email, UserName, UserPass, FirstName, LastName, Supervisor, DepartmentID) VALUES(
    '2', 'ownit@gmail.com', 'manager', 'no1elsehair', 'Jack', 'Donaghy', '1', '1');
INSERT INTO Employees (EmployeeID, Email, UserName, UserPass, FirstName, LastName, Supervisor, DepartmentID, HRAssignedNum) VALUES(
    '3', 'h8urebels@gmail.com', 'dvader', 'sith4ever', 'Darth', 'Vader', '4', '2', '0');
INSERT INTO Employees (EmployeeID, Email, UserName, UserPass, FirstName, LastName, Supervisor, DepartmentID, HRAssignedNum) VALUES(
    '4', 'dropabombonu@gmail.com', 'witty', 'angry', 'Malcolm', 'Tucker', '2', '2', '0');
INSERT INTO Employees (EmployeeID, Email, UserName, UserPass, FirstName, LastName, Supervisor, DepartmentID) VALUES(
    '5', 'itakeurmoney@gmail.com', 'trade', 'nobuysellhigh', 'Khajiit', 'Trader', '2', '3');
INSERT INTO Employees (EmployeeID, Email, UserName, UserPass, FirstName, LastName, Supervisor, DepartmentID) VALUES(
    '6', 'hatsunemei@gmail.com', 'research', 'advertise', 'Mei', 'Hatsune', '5', '3');
INSERT INTO Employees (EmployeeID, Email, UserName, UserPass, FirstName, LastName, Supervisor, DepartmentID) VALUES(
    '7', 'jeopardy@gmail.com', 'jeopardy', 'rainman', 'Raymond', 'Babbitt', '2', '4');
INSERT INTO Employees (EmployeeID, Email, UserName, UserPass, FirstName, LastName, Supervisor, DepartmentID) VALUES(
    '8', 'themusicalpython@gmail.com', 'eidle', 'wideaccountancy', 'Eric', 'Idle', '6', '4');
    
UPDATE Department SET DeptHeadID = 1 WHERE DepartmentID = '1';
UPDATE Department SET DeptHeadID = 3 WHERE DepartmentID = '2';
UPDATE Department SET DeptHeadID = 5 WHERE DepartmentID = '3';
UPDATE Department SET DeptHeadID = 7 WHERE DepartmentID = '4';

Insert into SUBMISSION (SUBMISSIONID,EMPLOYEEID,EVENTTYPEID,EVENTDATE,EVENTCOUNTRY,EVENTSTATE,EVENTCITY,DESCPRIPTION,REIMAMOUNT,GRADEPRESNUM,JUSTIFICATION,OPTEVTATTACH,OPTDAYSMISSED,STATUS,PASSED,APPRSUBDATE,ASSIGNEDHR) values 
(3,4,3,to_date('14-SEP-19','DD-MON-RR'),'United Kingdom',null,'London','Retort Classes',600,0,'This will make me better at comebacks.',' ',-1,2,null,to_date('25-AUG-19','DD-MON-RR'),3);

Insert into SUBMISSION (SUBMISSIONID,EMPLOYEEID,EVENTTYPEID,EVENTDATE,EVENTCOUNTRY,EVENTSTATE,EVENTCITY,DESCPRIPTION,REIMAMOUNT,GRADEPRESNUM,JUSTIFICATION,OPTEVTATTACH,OPTDAYSMISSED,STATUS,PASSED,APPRSUBDATE,ASSIGNEDHR) values 
(4,4,1,to_date('14-SEP-19','DD-MON-RR'),'United Kingdom',null,'London','Management Classes',500,0,'This will make me rule the room better.',' ',-1,2,null,to_date('25-AUG-19','DD-MON-RR'),3);

Insert into SUBMISSION (SUBMISSIONID,EMPLOYEEID,EVENTTYPEID,EVENTDATE,EVENTCOUNTRY,EVENTSTATE,EVENTCITY,DESCPRIPTION,REIMAMOUNT,GRADEPRESNUM,JUSTIFICATION,OPTEVTATTACH,OPTDAYSMISSED,STATUS,PASSED,APPRSUBDATE,ASSIGNEDHR) values 
(1,1,4,to_date('15-SEP-19','DD-MON-RR'),'United States','WV','Morgantown','J8 Certification',250,0,'This will make me more valuable to employers, Revature.',' ',-1,7,null,to_date('25-AUG-19','DD-MON-RR'),4);

Insert into SUBMISSION (SUBMISSIONID,EMPLOYEEID,EVENTTYPEID,EVENTDATE,EVENTCOUNTRY,EVENTSTATE,EVENTCITY,DESCPRIPTION,REIMAMOUNT,GRADEPRESNUM,JUSTIFICATION,OPTEVTATTACH,OPTDAYSMISSED,STATUS,PASSED,APPRSUBDATE,ASSIGNEDHR) values 
(2,3,2,to_date('15-SEP-19','DD-MON-RR'),'Death Star',null,'Management Floor','Fathering Classes',400,0,'I need this.',' ',-1,0,null,to_date('25-AUG-19','DD-MON-RR'),4);

COMMIT;
EXIT;
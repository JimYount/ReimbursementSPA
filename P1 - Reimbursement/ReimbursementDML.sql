INSERT INTO EventTypes (EventName, EventPercent) VALUES('University Courses', '80');
INSERT INTO EventTypes (EventName, EventPercent) VALUES('Seminars', '60');
INSERT INTO EventTypes (EventName, EventPercent) VALUES('Certification Preparation Classes', '75');
INSERT INTO EventTypes (EventName, EventPercent) VALUES('Certification', '100');
INSERT INTO EventTypes (EventName, EventPercent) VALUES('Technical Training', '90');
INSERT INTO EventTypes (EventName, EventPercent) VALUES('Other', '30');

INSERT INTO Department (DeptName) VALUES('Management');
INSERT INTO Department (DeptName) VALUES('Human Resources');
INSERT INTO Department (DeptName) VALUES('Advertising');
INSERT INTO Department (DeptName) VALUES('Accounting');

INSERT INTO Employees (Email, UserName, UserPass, FirstName, LastName, Supervisor, DepartmentID) VALUES(
    'jimayount@gmail.com', 'jayount', 'MyPa55', 'Jim', 'Yount', '-1', '1');
INSERT INTO Employees (Email, UserName, UserPass, FirstName, LastName, Supervisor, DepartmentID) VALUES(
    'ownit@gmail.com', 'manager', 'no1elsehair', 'Jack', 'Donaghy', '1', '1');
INSERT INTO Employees (Email, UserName, UserPass, FirstName, LastName, Supervisor, DepartmentID) VALUES(
    'dropabombonu@gmail.com', 'witty', 'angry', 'Malcolm', 'Tucker', '1', '2');
INSERT INTO Employees (Email, UserName, UserPass, FirstName, LastName, Supervisor, DepartmentID) VALUES(
    'h8urebels@gmail.com', 'dvader', 'sith4ever', 'Darth', 'Vader', '3', '2');
INSERT INTO Employees (Email, UserName, UserPass, FirstName, LastName, Supervisor, DepartmentID) VALUES(
    'itakeurmoney@gmail.com', 'trade', 'nobuysellhigh', 'Khajiit', 'Trader', '1', '3');
INSERT INTO Employees (Email, UserName, UserPass, FirstName, LastName, Supervisor, DepartmentID) VALUES(
    'hatsunemei@gmail.com', 'research', 'advertise', 'Mei', 'Hatsune', '5', '3');
INSERT INTO Employees (Email, UserName, UserPass, FirstName, LastName, Supervisor, DepartmentID) VALUES(
    'jeopardy@gmail.com', 'jeopardy', 'rainman', 'Raymond', 'Babbitt', '1', '4');
INSERT INTO Employees (Email, UserName, UserPass, FirstName, LastName, Supervisor, DepartmentID) VALUES(
    'themusicalpython@gmail.com', 'eidle', 'wideaccountancy', 'Eric', 'Idle', '6', '4');
    
UPDATE Department SET DeptHeadID = 1 WHERE DepartmentID = '1';
UPDATE Department SET DeptHeadID = 3 WHERE DepartmentID = '2';
UPDATE Department SET DeptHeadID = 5 WHERE DepartmentID = '3';
UPDATE Department SET DeptHeadID = 7 WHERE DepartmentID = '4';

COMMIT;
EXIT;
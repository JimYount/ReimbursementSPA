-- DQL - Data Query Language - SELECT
select * from album;
SELECT * FROM ALBUM;
SeLeCt * FrOm AlBuM;

-- Oracle SQL isn't case sensitive?
select title from album;
select 'title' from album; -- Single quotes represent a string value
select 1 from album;
select "title" from album; -- double quotes represent an identifier
select "TITLE" from album;


-- DO NOT DO THIS
-- SERIOUSLY DO NOT DO THIS
--create table "album" (
--"albumId" number primary key,
--"title" varchar2(16) not null,
--"artistId" number not null);
--
--select * from album; -- ANYTHING outside of an identifier quote is getting uppercased
--select * from "album";
--drop table "album";

select title, artistid from album;
select * from album;
select albumid, title, artistid from album;
select artistid, title, albumid from album;

-- select statements return result sets

select * from album where artistid = 1;
select artistid from artist where name = 'Foo Fighters';
select * from album where artistid=84;
select * from album where artistid = (select artistid from artist where name = 'Foo Fighters');
select * from 
    (select * from album where artistid = (select artistid from artist where name = 'Foo Fighters'))
where title like 'In Your%';

select * from album
    where artistid=(select artistid from artist where name = 'Foo Fighters') and title like 'In Your%';

-- Where Clauses
select * from album where title like 'In You%';
select * from album where title like 'B%';
select * from album where title like '%One%';
select * from album where title = 'Audioslave';
select * from track where unitprice > .99;
select * from track where unitprice <= 1.99 and unitprice >.99;
select * from track where unitprice between .99 and 1.99;

select * from artist join album on artist.artistid = album.artistid; --equijoin
select name, title from artist join album on artist.artistid = album.artistid;
select * from artist join track on artist.artistid > track.unitprice; --thetajoin
select name, title from artist natural join album;
select name, title from artist join album using(artistid);
select * from employee natural join customer;
select * from employee join customer using (firstname);
select * from employee join customer using (firstname, lastname);
select * from employee join customer on employeeid = customerid;

-- let us get unnecessarily complex
select * from (select artist.name, album.title from artist join album using (artistid))
    where title = 'Let There Be Rock';
select artist.name, album.title from artist join album using(artistid) where title = 'Let There Be Rock';

-- Views: Saving a query as a queryable object
create view artist_album as (select artist.name, album.title from artist join album using (artistid));

select * from artist_album;
-- VIEWS ARE NOT TABLES
select * from artist_album where title like '%et%';
select * from (select artist.name, album.title from artist join album using (artistid)) where title like '%et%';


-- Functions
-- Function: A PL/SQL block (in Oracle SQL) that executes when you call it
/*
    There are two types of functions in SQL
    Scalar: A function that operates on a single input
        length(string)
        upper(string)
        lower(string)
        substr(string, start, end)
        concat(string, string)
    Aggregate: A function that operates on multiple rows of input, returning a single value
        max()
        min()
        count()
        avg()
*/

select length(name), name from artist;
-- scalar
select substr(name, 1, 5) from artist;
select concat(name, artistid) from artist;
select concat(concat(name, ': '), artistid) from artist;
select systimestamp from dual;
select systimestamp from artist;
select * from dual;
-- aggregate
select count(*) from track where unitprice > .99;
select count(*) from track;
select count(*) from track where unitprice < 1.99;
select count(artistid) from album;
select avg(unitprice) from track;
select count(artistid) as "Number of Albums" from album;

-- quick aside on alieases
select t.title as "bub" from album t;

-- back to aggregates
select artistid, count(artistid) as "# Albums" from album group by artistid;

select name, "# Albums" from artist join 
    (select artistid, count(artistid) as "# Albums" from album group by artistid)
using (artistid);

select name, "# Albums" from artist a join 
    (select artistid, count(artistid) as "# Albums" from album group by artistid) b
on a.artistid = b.artistid;

select name, "# Albums" from artist a join 
    (select artistid, count(artistid) as "# Albums" from album group by artistid) b
on a.artistid = b.artistid order by "# Albums" desc;


-- Select all rock tracks and the artist's name
select * from track;
-- narrow to relevant fields
select name, genreid, albumid from track;
-- join that to the Genre table
select track.name, track.albumid, genre.name as "GENRE" 
    from track join genre using (genreid);
-- restrict results to just rock
select track.name, track.albumid, genre.name as "GENRE" from track join genre
    using (genreid) where genre.name like 'Rock%';
    
-- Join with album
select "Track Name", album.artistid, genre from 
    (select track.name as "Track Name", track.albumid, genre.name as "GENRE" from track join genre
    using (genreid) where genre.name like 'Rock%') join album using (albumid);

-- join with artist
select "Track Name", artist.name, genre from 
    (select "Track Name", album.artistid, genre from 
    (select track.name as "Track Name", track.albumid, genre.name as "GENRE" from track join genre
    using (genreid) where genre.name like 'Rock%') join album using (albumid)) join artist using (artistid);
    
/*
    Natural Join - The db attempts to perform an inner join on like columns
    Inner Join - Only matches will show in resulting result set
    Outer Join - Every row will show even if there is no match
    Left outer join - all results from the left table with only
            matches from the right
    Right outer join - all results fromt he right table with only
            matches from the left
    Cross Join - Cartesian product of two tables. (every row with every row)
    Self Join - Joining a table with itself.
*/
-- self inner join
select * from employee;
select bob.firstname as "Employee", man.firstname as "Manager"
    from employee bob join employee man on bob.reportsto= man.employeeid;
-- self outer join
select bob.firstname as "Employee", man.firstname as "Manager"
    from employee bob full outer join employee man on bob.reportsto= man.employeeid;
-- self left join
select bob.firstname as "Employee", man.firstname as "Manager"
    from employee bob left outer join employee man on bob.reportsto= man.employeeid;
-- self right join
select bob.firstname as "Employee", man.firstname as "Manager"
    from employee bob right outer join employee man on bob.reportsto= man.employeeid;

-- cross join
select count(*) from album; --347
select count(*) from artist; --275
select count(*) from album, artist; -- 95425
select count(*) from album cross join artist;
select * from album, artist;

-- group by and having
select count(firstname), company from customer group by company;
-- where clause
select count(firstname), company from customer where company = 'Google Inc.';
select count(firstname), company from customer where company = 'Google Inc.' group by company;
select count(firstname), company from customer where count(firstname)=1 group by company;
select count(firstname), company from customer group by company having count(firstname)=1;

select count(albumid) as "# of Albums", name from
    (select album.albumid, artist.artistid, artist.name from
        album join artist on album.artistid = artist.artistid)
    where artistid > 5
    group by name
    having count(albumid)>1
    order by "# of Albums" asc;

-- cannot use having without group by
select count(albumid) from album where albumid>5;
select count(albumid) from album having albumid>5;

-- Set Operations
/*
    Combine results with like columns
    Union - All unique rows in both sets
        (a, b, c) U (b, c) = (a, b, c)
    Union All - All rows in both sets
        (a, b, c) U All (b, c)  = (a, b, b, c, c)
    Intersect - Only rows in both sets
        (a, b, c) Intersect (b, c) = (b, c)
    Minus - Only rows in the first set
        (a, b, c) minus (b, c) = (a)
*/

select * from customer where state = 'CA';
select * from customer where country = 'Brazil';
select * from customer where state = 'CA' union 
    select * from customer where country = 'Brazil';

select * from invoiceline where unitprice > .99; --111
select * from invoiceline where trackid < 3000; --1961
select * from invoiceline where unitprice > .99
    minus select * from invoiceline where trackid < 3000; --58
select * from invoiceline where unitprice > .99
    intersect select * from invoiceline where trackid < 3000; --53
    
select firstname from employee;
select firstname from customer;
select firstname from employee intersect select firstname from customer;

select firstname, lastname from employee
    intersect select firstname, lastname from customer;
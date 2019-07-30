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
select track.name, track.albumid, genre.name as "GENRE" from track join genre using (genreid);
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
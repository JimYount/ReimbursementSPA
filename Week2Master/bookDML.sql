-- Authors
insert into author(id, firstname, lastname, aboutblurb)
    values( 1, 'J.K.', 'Rowling', 
    'One of the richest people in the world.');
insert into author(id, firstname, lastname, aboutblurb)
    values(56, 'Robert', 'Jordan', 'Lived in Charleston, SC');
insert into author(id, firstname, lastname, aboutblurb)
    values(56, 'George', 'Martin', 'Sadist');
insert into author(id, firstname, lastname) values (2, 'Steven', 'Brust');
insert into author(firstname, lastname) values('Jim', 'Butcher');

-- updates
update author set id=55 where id=3;

-- Books
insert into book(id, title, isbn10, isbn13, price, stock)
    values(1, 'A Game of Thrones', '0553593714', '978-0553593716', 8.79, 60);
insert into book(title, isbn10, isbn13, price, stock)
    values('The Eye of the World', '0812511816', '978-0812511819', 8.69, 500);
insert into book (id, isbn10, isbn13, title, price, stock)
  values(1,'0590353403','978-0590353403','Harry Potter and the Sorcerer''s Stone',19.82,50);
insert into book (isbn10, isbn13, title, price, stock)
  values('0439064872', '978-0439064873', 'Harry Potter and the Chamber of Secrets', 8.33, 30);
insert into book (isbn10, isbn13, title, price, stock)
  values('0451457811', '978-0451457813', 'Storm Front', 8.99, 50);
  
select id from author where firstname='J.K.';
insert into book_author (author_id, book_id) values (
    (select id from author where firstname='J.K.'),
    (select id from book where title like '%Sorcerer%'));
insert into book_author (author_id, book_id) values (
    (select id from author where firstname='J.K.'),
    (select id from book where title like '%Chamber%'));
insert into book_author (author_id, book_id)
    values((select id from author where firstname='George' and lastname='Martin'),
    (select id from book where title='A Game of Thrones'));
insert into book_author (author_id, book_id)
    values((select id from author where firstname ='Robert' and lastname='Jordan'),
    (select id from book where title='The Eye of the World'));
insert into book_author(author_id, book_id)
    values((select id from author where firstname = 'Jim' and lastname = 'Butcher'),
    (select id from book where title = 'Storm Front'));
commit;

select b.title, author.firstname, author.lastname from
    (select * from book join book_author on book.id = book_author.book_id) b
    join author on b.author_id = author.id;

-- breaking it down
select * from book;
select title from book;
select title, book_author.author_id from book
    join book_author on book.id = book_author.book_id;
    
update book set stock = 498, price = 8.68 where id = 2;
update book set id = 5;

-- Genre
insert into genre (genre) values ('Fantasy');
insert into genre (genre) values('Non-Fiction');
insert into genre (genre) values('Fiction');
insert into genre (genre) values('Sci-Fi');
insert into genre (genre) values('Historical Romance');
insert into genre (genre) values('Mystery');

insert into book_genre (book_id, genre_id)
  values((select id from book where book.TITLE='A Game of Thrones'),
  (select id from genre where genre='Fantasy'));
insert into book_genre (book_id, genre_id)
  values((select id from book where book.TITLE='The Eye of the World'),
  (select id from genre where genre='Fantasy'));
insert into book_genre (book_id, genre_id)
  values((select id from book where book.TITLE='Harry Potter and the Sorcerer''s Stone'),
  (select id from genre where genre='Fantasy'));
insert into book_genre (book_id, genre_id)
  values((select id from book where book.TITLE='Harry Potter and the Chamber of Secrets'),
  (select id from genre where genre='Fantasy'));
  insert into book_genre (book_id, genre_id)
values((select id from book where book.TITLE='Storm Front'),
  (select id from genre where genre='Fantasy'));
insert into book_genre (book_id, genre_id)
  values((select id from book where book.TITLE='Storm Front'),
  (select id from genre where genre='Mystery'));
  
select b.title, genre.genre from 
    (select book.title, book_genre.genre_id from book 
        join book_genre on book.id = book_genre.book_id) b
    join genre on b.genre_id = genre.id;
    
insert into taxrate(state,rate) values('AL',.04);
insert into taxrate(state,rate) values('AK',.00);
insert into taxrate(state,rate) values('AZ',.056);
insert into taxrate(state,rate) values('AR',.065);
insert into taxrate(state,rate) values('CA',.0725);
insert into taxrate(state,rate) values('CO',.029);
insert into taxrate(state,rate) values('CT',.0635);
insert into taxrate(state,rate) values('DE',.00);
insert into taxrate(state,rate) values('FL',.06);
insert into taxrate(state,rate) values('GA',.04);

insert into taxrate(state,rate) values('HI',.04);
insert into taxrate(state,rate) values('ID',.06);
insert into taxrate(state,rate) values('IL',.0625);
insert into taxrate(state,rate) values('IN',.07);
insert into taxrate(state,rate) values('IA',.06);
insert into taxrate(state,rate) values('KS',.065);
insert into taxrate(state,rate) values('KY',.06);
insert into taxrate(state,rate) values('LA',.05);
insert into taxrate(state,rate) values('ME',.055);
insert into taxrate(state,rate) values('MD',.06);

insert into taxrate(state,rate) values('MA',.0625);
insert into taxrate(state,rate) values('MI',.06);
insert into taxrate(state,rate) values('MN',.06875);
insert into taxrate(state,rate) values('MS',.07);
insert into taxrate(state,rate) values('MO',.04225);
insert into taxrate(state,rate) values('MT',.00);
insert into taxrate(state,rate) values('NE',.055);
insert into taxrate(state,rate) values('NV',.0685);
insert into taxrate(state,rate) values('NH',.00);
insert into taxrate(state,rate) values('NJ',.06875);

insert into taxrate(state,rate) values('NM',.05125);
insert into taxrate(state,rate) values('NY',.04);
insert into taxrate(state,rate) values('NC',.0475);
insert into taxrate(state,rate) values('ND',.05);
insert into taxrate(state,rate) values('OH',.0575);
insert into taxrate(state,rate) values('OK',.045);
insert into taxrate(state,rate) values('OR',.00);
insert into taxrate(state,rate) values('PA',.06);
insert into taxrate(state,rate) values('RI',.07);
insert into taxrate(state,rate) values('SC',.06);
insert into taxrate(state,rate) values('TN',.045);
insert into taxrate(state,rate) values('TX',.07);

insert into taxrate(state,rate) values('UT',.0595);
insert into taxrate(state,rate) values('VT',.06);
insert into taxrate(state,rate) values('VA',.053);
insert into taxrate(state,rate) values('WA',.065);
insert into taxrate(state,rate) values('WV',.076);
insert into taxrate(state,rate) values('WI',.05);
insert into taxrate(state,rate) values('WY',.04);
insert into taxrate(state,rate) values('GU',.04);
insert into taxrate(state,rate) values('PR',.115);
insert into taxrate(state,rate) values('VI',.00);
insert into taxrate(state,rate) values('DC',.0575);

insert into address(id, lineone,linetwo,city,state,zip)
  values(1,'11730 Plaza America Dr','Suite 205','Reston','VA',20170);
insert into address(id, lineone,linetwo,city,state,zip)
  values(1,'1 Fantasy Lane',null,'Detroit','MI',48127);
insert into address(lineone,linetwo,city,state,zip)
  values('42 Cardinal Dr',null,'St Louis','MO',63101);
  
insert into login(first_name,last_name,username, pswd)
  values('Paul','Maksimovich','paulm','pass');
insert into login(first_name,last_name,username, pswd)
  values('Richard','Orr','rorr','pass1');
insert into login(first_name,last_name,username, pswd)
  values('Matt','Pierzynski','pski','pwd');
  
insert into customer(id, address_id, fav_color)
  values((select id from login where username='paulm'), 1, 'red');
insert into customer(id, address_id, fav_color)
  values((select id from login where username='rorr'), 2, 'purple');

insert into emp(id, sup_id, title)
  values((select id from login where username='pski'), null, 'CEO');
insert into emp(id, sup_id, title)
  values((select id from login where username='rorr'),
  (select id from login where username='pski'), 'Cashier');
commit;

-- calculate how much a book costs in a particular state
select title, price from book;
select * from taxrate where state = 'WV';
select title, price, round((1+rate)*price, 2) as "total" 
    from book, taxrate where taxrate.state ='WV';


-- Calculate the price of Harry Potter and the Chamber of Secrets
-- Specifically for the user Paul. His username is paulm.
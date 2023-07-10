CREATE DATABASE if not exists matchLearners;
USE matchLearners;

CREATE TABLE if not exists users (
	id_user INT PRIMARY KEY auto_increment,
    username VARCHAR(50) NOT NULL,
	email VARCHAR (50) NOT NULL,
    password VARCHAR (50) NOT NULL
);
CREATE TABLE if not exists admin (
	id_user INT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
	email VARCHAR (50) NOT NULL,
    password VARCHAR (50) NOT NULL,
    privileges INT NOT NULL
);
CREATE TABLE if not exists post (
	id_post INT PRIMARY KEY auto_increment,
    title VARCHAR(50) NOT NULL,
	description VARCHAR (500) NOT NULL,
    id_user INT NOT NULL,
	username VARCHAR(50) NOT NULL,
    is_liked int NOT NULL
);

INSERT INTO users VALUES (1,"Jean","jean@gmail.com","1234");
INSERT INTO users VALUES (2,"Jeanne","jeanne@gmail.com","1234");
INSERT INTO users VALUES (3,"Luc","luc@gmail.com","XXXX");

INSERT INTO post VALUES (1,"Help","Need a lot of help",2,"Jeanne",0);
INSERT INTO post VALUES (2,"Gardening","I want to learn some gardening skills",1,"Jean",0);
INSERT INTO post VALUES (3,"Housekeeping","Need a lot of help",3,"Luc",0);
INSERT INTO post VALUES (4,"Homework","Need help for maths skills",2,"Jeanne",0);

select * from post;
select * from users;
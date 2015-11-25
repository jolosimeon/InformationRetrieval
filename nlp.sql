CREATE DATABASE NLP;
USE NLP;

CREATE TABLE existence
(
word			varchar(50)			    NOT NULL,
files    		varchar(20000)		    NOT NULL,

PRIMARY KEY(word)
);

INSERT into existence values("word1","file1,2");
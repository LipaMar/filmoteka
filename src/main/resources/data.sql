CREATE TABLE  IF NOT EXISTS Users(id INTEGER PRIMARY KEY ,username VARCHAR(255), password VARCHAR (255));
INSERT INTO Users(id, username, password) VALUES (1,'jdoe','password');
INSERT INTO Users(id, username, password) VALUES (2,'test','test');

CREATE TABLE  IF NOT EXISTS Reviewers(id INTEGER PRIMARY KEY ,displayName VARCHAR(255), sortName VARCHAR (255), status VARCHAR (255), bio VARCHAR (255), seoName VARCHAR (255));
INSERT INTO Reviewers VALUES (1,'reviewer1','','','','');
INSERT INTO Reviewers VALUES (2,'reviewer2','','','','');

CREATE TABLE  IF NOT EXISTS Reviews(id INTEGER PRIMARY KEY ,content VARCHAR(255), author_id INTEGER, FOREIGN KEY (author_id) references Reviewers(id));
INSERT INTO Reviews VALUES (1,'good',2);
INSERT INTO Reviews VALUES (2,'bad',1);
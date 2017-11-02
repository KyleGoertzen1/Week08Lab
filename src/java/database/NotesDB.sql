DROP DATABASE if exists NotesDB;
CREATE DATABASE NotesDB;
USE NotesDB;

DROP TABLE Note;

CREATE TABLE Note(
    noteId INT(11) NOT NULL AUTO_INCREMENT,
    dateCreated DATE NOT NULL,
    contents VARCHAR(10000) NOT NULL,
    PRIMARY KEY (noteId)
);

INSERT INTO Note values(default, "1988/05/01", 'Hello World!');
CREATE database matrixdb;
Use matrixdb;
DROP TABLE IF EXISTS person;
DROP TABLE IF EXISTS profile_person;
DROP TABLE IF EXISTS skill;
DROP TABLE IF EXISTS type_skills;
DROP TABLE IF EXISTS person_skill;


CREATE TABLE profile_person (
  profile_id INT AUTO_INCREMENT  PRIMARY KEY,
  profile_title VARCHAR(250) NOT NULL,
  profile_description VARCHAR(250) NOT NULL
);

INSERT INTO profile_person (profile_title, profile_description) VALUES
  ('Developpeur Junior', 'Developpeur Junior 3ans'),
  ('Developpeur Sénior', 'Developpeur Sénior 5ans'),
  ('Business Analyste Junior', 'Business Analyste Junior 1an(s)'),
  ('Business Analyste Sénior', 'Business Analyste Sénior 5ans'),
  ('PO Junior', 'PO Junior 3ans'),
  ('PO Confirmer', 'PO Junior 5ans'),
  ('Manager Junior', 'Manager Junior 13ans');

  CREATE TABLE person (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  mail_adresses VARCHAR(250) DEFAULT NULL,
  birth_date DATE DEFAULT NULL,
  profile_id INT NOT NULL
);

INSERT INTO person (first_name, last_name, mail_adresses, birth_date,profile_id) VALUES
  ('Aliko', 'Dangote', 'adangote@sqli.com','1973-11-17',1),
  ('Amira', 'El ouardi', 'aelouardi@sqli.com','2015-09-26',1),
  ('Ibrahim', 'El ouardi', 'ielouardi@sqli.com','2016-10-23',2),
  ('Yassine', 'El ouardi', 'yelouardi@sqli.com','1986-09-14',7),
  ('User1', 'Dangote', '1adangote@sqli.com','1973-11-17',5),
  ('User11', 'El ouardi', '2aelouardi@sqli.com','2015-09-26',4),
  ('User12', 'El ouardi', '3ielouardi@sqli.com','2016-10-23',3),
  ('User13', 'El ouardi', '4yelouardi@sqli.com','1986-09-14',4),
  ('User14', 'Dangote', '5adangote@sqli.com','1973-11-17',5),
  ('User15', 'El ouardi', '6aelouardi@sqli.com','2015-09-26',6),
  ('User16', 'El ouardi', '7ielouardi@sqli.com','2016-10-23',7),
  ('User17', 'El ouardi', '8yelouardi@sqli.com','1986-09-14',2);

  CREATE TABLE type_skills (
  type_id INT AUTO_INCREMENT  PRIMARY KEY,
  title_skill VARCHAR(250) NOT NULL
);

INSERT INTO type_skills (title_skill) VALUES
  ('audace'),
  ('java'),
  ('scrum'),
  ('agile'),
  ('react');

CREATE TABLE skill (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  libelle VARCHAR(250) NOT NULL,
  description VARCHAR(250) NOT NULL,
  type_id INT NOT NULL
);

INSERT INTO skill (libelle, description, type_id) VALUES
  ('Java', 'Java 8',1),
  ('C#', 'C# description',1),
  ('C++', 'C++ description',2),
  ('coach-agile', 'coach agile',4),
  ('scrum-master', 'scrum master',3),
  ('craft-man', 'craft man software',4),
  ('webpack', 'Web pack build',5),
  ('reactjs', 'Developpment React JS',5);

CREATE TABLE person_skill (
  person_id INT NOT NULL,
  skill_id INT NOT NULL
);
INSERT INTO person_skill (person_id, skill_id) VALUES
  (4, 1),
  (4, 2),
  (4, 3),
  (3, 1),
  (3, 2),
  (12, 2),
  (2, 2),
  (1, 1),
  (10, 2),
  (5, 3),
  (6, 1),
  (7, 2),
  (8, 2),
  (9, 2),
  (10, 2),
  (1, 3),
    (4, 3),
    (4, 4),
    (4, 5),
    (3, 6),
    (3, 5),
    (12, 1),
    (2, 3),
    (1, 4),
    (10, 1),
    (5, 6),
    (6, 2),
    (7, 3),
    (8, 4),
    (9, 1),
    (10, 3),
    (1, 5),
      (4, 6),
      (3, 3),
      (3, 4),
      (12, 4),
      (2, 4),
      (1, 4),
      (10, 4),
      (5, 5),
      (6, 3),
      (7, 1),
      (8, 1),
      (9, 1),
      (10, 1),
      (1, 6);
use human_friends;


-- truncate all_animals;
-- truncate cat;
-- truncate dog;
-- truncate horse_and_donkey;
-- truncate humster;
-- truncate young_animal;


create table if not exists cat (
	id int primary key auto_increment,
	animal_name varchar(20),
    command varchar(20),
    date_of_birth date
    );
insert into cat (animal_name,command, date_of_birth) VALUES 
	('Cat_1', 'run', '2021-04-29'),
	('Cat_2', 'meow', '2022-02-14'),
    ('Cat_3', 'run', '2023-03-23'),
    ('Cat_4', 'meow', '2024-05-11');
   

create table if not exists dog (
	id int primary key auto_increment,
	animal_name varchar(20),
    command varchar(20),
    date_of_birth date
);
insert into dog (animal_name,command, date_of_birth) VALUES 
('Dog_1', 'run', '2021-04-29'),
	('Dog_2', 'meow', '2022-02-14'),
    ('Dog_3', 'run', '2023-03-23'),
    ('Dog_4', 'meow', '2024-05-11');



create table if not exists humster (
	id int primary key auto_increment,
	animal_name varchar(20),
    command varchar(20),
    date_of_birth date
);
insert into humster (animal_name,command, date_of_birth) VALUES 
('Humster_1', 'run', '2021-04-29'),
	('Humster_2', 'meow', '2022-02-14'),
    ('Humster_3', 'run', '2023-03-23'),
    ('Humster_4', 'meow', '2024-05-11');
    
    

create table if not exists horse (
	id int primary key auto_increment,
	animal_name varchar(20),
    command varchar(20),
    date_of_birth date
);


insert into horse (animal_name,command, date_of_birth) VALUES 
('Horse1_', 'run', '2021-04-29'),
	('Horse_2', 'meow', '2022-02-14'),
    ('Horse_3', 'run', '2023-03-23'),
    ('Horse_4', 'meow', '2024-05-11');



create table if not exists camel (
	id int primary key auto_increment,
	animal_name varchar(20),
    command varchar(20),
    date_of_birth date
);
insert into camel (animal_name,command, date_of_birth) VALUES 
('Camel_1', 'run', '2021-04-29'),
	('Camel_2', 'meow', '2022-02-14'),
    ('Camel_3', 'run', '2023-03-23'),
    ('Camel_4', 'meow', '2024-05-11');
    
    

create table if not exists donkey (
	id int primary key auto_increment,
	animal_name varchar(20),
    command varchar(20),
    date_of_birth date
);
insert into donkey (animal_name,command, date_of_birth) VALUES 
('Donkey_1', 'run', '2021-04-29'),
	('Donkey_2', 'meow', '2022-02-14'),
    ('Donkey_3', 'run', '2023-03-23'),
    ('Donkey_4', 'meow', '2024-05-11');
    
    
drop table camel;


create table if not exists horse_and_donkey(
	id int primary key auto_increment,
	animal_name varchar(20),
    command varchar(20),
    date_of_birth date
);

insert into  horse_and_donkey (animal_name, command, date_of_birth)
select animal_name, command, date_of_birth
from horse
union all 
select animal_name, command, date_of_birth 
from donkey ;

drop table horse;
drop table donkey;

create table if not exists young_animal 
(	id int primary key auto_increment,
	animal_name varchar(20),
    command varchar(20),
    date_of_birth date, 
    age_by_month int 
    );
 
 truncate young_animal;
insert into  young_animal (animal_name, command, date_of_birth,age_by_month)
select animal_name, command, date_of_birth, timestampdiff(MONTH, date_of_birth, curdate()) as age_by_month
from cat 
where date_of_birth between date_sub(curdate(), interval 3 year) and date_sub(curdate(), interval 1 year);

insert into  young_animal (animal_name, command, date_of_birth,age_by_month)
select animal_name, command, date_of_birth, timestampdiff(MONTH, date_of_birth, curdate()) as age_by_month
from dog 
where date_of_birth between date_sub(curdate(), interval 3 year) and date_sub(curdate(), interval 1 year);

insert into  young_animal (animal_name, command, date_of_birth,age_by_month)
select animal_name, command, date_of_birth, timestampdiff(MONTH, date_of_birth, curdate()) as age_by_month
from horse_and_donkey 
where date_of_birth between date_sub(curdate(), interval 3 year) and date_sub(curdate(), interval 1 year);

insert into  young_animal (animal_name, command, date_of_birth,age_by_month)
select animal_name, command, date_of_birth, timestampdiff(MONTH, date_of_birth, curdate()) as age_by_month
from humster 
where date_of_birth between date_sub(curdate(), interval 3 year) and date_sub(curdate(), interval 1 year);


create table if not exists all_animals (
    id int primary key auto_increment,
    animal_name varchar(20),
    command varchar(20),
    date_of_birth date,
    original_table varchar(20)
);

insert into  all_animals (animal_name, command, date_of_birth, original_table)
select animal_name, command, date_of_birth, 'cat' as original_table
from cat;

insert into  all_animals (animal_name, command, date_of_birth, original_table)
select animal_name, command, date_of_birth, 'dog' as original_table
from dog;

insert into  all_animals (animal_name, command, date_of_birth, original_table)
select animal_name, command, date_of_birth, 'horse_and_donkey' as original_table
from horse_and_donkey;

INSERT INTO all_animals (animal_name, command, date_of_birth, original_table)
SELECT animal_name, command, date_of_birth, 'humster' AS original_table
FROM humster;

insert into  all_animals (animal_name, command, date_of_birth, original_table)
select animal_name, command, date_of_birth, 'humster' as original_table
from humster;

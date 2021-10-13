create database if not exists restaurants;

use restaurants;

drop table if exists restaurant;

create table restaurant (
name varchar(50) not null,
id int(10) not null,
primary key(name)
);

insert into restaurant (name, id) values("Il Vicino", 10813);

select * from restaurant;

	




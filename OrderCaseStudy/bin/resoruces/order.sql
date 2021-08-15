show databases;
drop database case_study;
create database case_study;

use case_study;

create table user(user_id varchar(20) not null
				  , user_password varchar(20) not null
                  , name varchar(25) not null
                  , email_id varchar(50) not null
                  , dob date not null
                  , contact_number varchar(15) not null
                  , address varchar(100) not null
                  , pincode int not null
                  , role varchar(10) not null
                  , primary key (user_id));


create table category(category_id int, 
					   name varchar(50) not null, primary key (category_id));
                       


create table product(product_id int
                     , product_name varchar(50)
                     , product_price int
                     , category_id int
                     , primary key (product_id)
                     , foreign key (category_id) references category(category_id));
                     
create table orders(order_id int auto_increment
                    , user_id varchar(20)
                    , order_price int not null
                    , order_date date not null
                    , primary key (order_id)
                    , foreign key (user_id) references user(user_id));
                    
create table line_items(lineitems_id int auto_increment
                        , product_id int not null
                        , quantity int not null
                        , order_id int not null
                        , primary key (lineitems_id) 
                        , foreign key (product_id) references product(product_id)
                        , foreign key (order_id) references orders(order_id));
                        
                        
  insert into product values(1, "Sanitizer", 20, 1);

insert into product values(2, "Soap", 25, 1);

insert into product values(3, "Lotion", 50, 1);

insert into product values(4, "Granola Bar", 10, 2);

insert into product values(5, "Horlicks", 70, 2);

insert into product values(6, "Boost", 85, 2);

insert into product values(7, "Paracetmol", 5, 3);

insert into product values(8, "Vicks", 10, 3);

insert into product values(9, "Citrogen", 15, 3);

insert into product values(10, "Syringe", 20, 3);
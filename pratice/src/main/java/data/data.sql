create database session10;
use session10;
create table category
(
    id     int auto_increment primary key not null,
    name   varchar(255)                   not null,
    status bit(1) default 1               not null
);

create table product
(
    id          int auto_increment primary key not null,
    name        varchar(255)                   not null,
    price       int                            not null,
    category_id int                            not null,
    foreign key (category_id) references category (id)
);

delimiter //
create procedure proc_create_new_category(in name_cat varchar(255), status_cat bit(1))
begin
    insert into category(name, status) values (name_cat, status_cat);
end //
//

delimiter //
create procedure proc_update_category(in catId int, catName varchar(255), catStatus bit(1))
begin
    update category set name=catName, status=catStatus where id = catId;
end //

delimiter //
create procedure proc_delete_category(in catId int)
begin
    delete from category where id = catId;
end //

delimiter //
create procedure proc_create_new_product(in pName varchar(255), pPrice float, catId int)
begin
    insert into product(name, price, category_id) values (pName, pPrice, catId);
end //
//

delimiter //
create procedure proc_update_product(in pId int, pName varchar(255), pPrice float, catId int)
begin
    update product set name = pName, price = pPrice, category_id = catId where id = pId;
end //

delimiter //
create procedure proc_delete_product(in pId int)
begin
    delete from product where id = pId;
end //


delimiter //
create procedure proc_show_category()
begin
    select * from category;
end //
//

delimiter //
create procedure proc_show_product()
begin
    select * from product;
end //
//

delimiter //
create procedure proc_find_cat_by_id(in catId int)
begin
    select * from category where id = catId;
end //
//

delimiter //
create procedure proc_find_pro_by_id(in proId int)
begin
    select * from category where id = proId;
end //
//


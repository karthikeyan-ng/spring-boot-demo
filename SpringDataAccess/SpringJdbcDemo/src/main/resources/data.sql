insert into customers (name, email) values ('karthi', 'karthi@xyz.com');
insert into customers (name, email) values ('pascal', 'pascal@xyz.com');
insert into customers (name, email) values ('thomas', 'thomas@xyz.com');

insert into orders (sku, customer_fk) values ('karthi mytest123', (select id from customers where name = 'karthi'));
insert into orders (sku, customer_fk) values ('karthi mytest123', (select id from customers where name = 'karthi'));
insert into orders (sku, customer_fk) values ('karthi mytest123', (select id from customers where name = 'karthi'));
insert into orders (sku, customer_fk) values ('pascal mytest123', (select id from customers where name = 'pascal'));
insert into orders (sku, customer_fk) values ('pascal mytest123', (select id from customers where name = 'pascal'));
insert into orders (sku, customer_fk) values ('thomas mytest123', (select id from customers where name = 'thomas'));
insert into orders (sku, customer_fk) values ('thomas mytest123', (select id from customers where name = 'thomas'));
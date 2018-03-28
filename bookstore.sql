CREATE DATABASE bookstore;
USE bookstore;
/*用户表*/
CREATE TABLE tb_user(
 uid CHAR(32) PRIMARY KEY,/*主键*/
 username VARCHAR(50) NOT NULL,/*用户名*/
`password` VARCHAR(50) NOT NULL,/*密码*/
 email VARCHAR(50) NOT NULL,/*邮箱*/
 `code` CHAR(64) NOT NULL,/*激活码*/
 state BOOLEAN/*用户状态，有两种是否激活*/
 );
 
 SELECT * FROM tb_user;
 
 /*分类*/
 CREATE TABLE category(
 cid CHAR(32) PRIMARY KEY,/*主键*/
 cname VARCHAR(100) NOT NULL/*分类名称*/
 );
 
INSERT INTO category(cid,cname) VALUES('1','JavaSE');
INSERT INTO category(cid,cname) VALUES('2','JavaEE');
INSERT INTO category(cid,cname) VALUES('3','JavaScript');
SELECT * FROM book;
 
/*图书表*/
CREATE TABLE book(
 bid CHAR(32) PRIMARY KEY,/*主键*/
 bname VARCHAR(100),/*图数名*/
 price DECIMAL(5,1),/*单价*/
 author VARCHAR(20),/*作者*/
 image VARCHAR(200),/*图片*/
 cid CHAR(32),/*所属分类*/
 FOREIGN KEY (cid) REFERENCES category(cid)/*建立主外键关系*/
 );
 
INSERT INTO book VALUES('1','javase1','12','a','book_img/1_1.jpg','1');
INSERT INTO book VALUES('2','javase2','15','a','book_img/1_2.jpg','1');
INSERT INTO book VALUES('3','javase3','17','b','book_img/1_3.jpg','1');
INSERT INTO book VALUES('4','javaee1','20','张三','book_img/2_1.jpg','2');
INSERT INTO book VALUES('5','javaee2','32','zs','book_img/2_2.jpg','2');
INSERT INTO book VALUES('6','javaee3','22','ls','book_img/2_3.jpg','2');
INSERT INTO book VALUES('7','javasc1','213','ls','book_img/3_1.jpg','3');
INSERT INTO book VALUES('8','javasc2','33','我的天','book_img/3_2.jpg','3');
INSERT INTO book VALUES('9','javasc3','65','啦啦啦','book_img/3_3.jpg','3');

/*订单表*/
CREATE TABLE orders(
 oid CHAR(32) PRIMARY KEY,/*主键*/
 ordertime DATETIME,/*订单生成时间*/
 total DECIMAL(10,0),/*订单合计*/
 state SMALLINT(1),/*订单状态：未付款、已付款但未发货、已发货但未确认收获、收货已结束*/
 uid CHAR(32),/*订单的所有者*/
 address VARCHAR(200),/*订单的收货地址*/
 FOREIGN KEY (uid) REFERENCES tb_user (uid)/*建立主外键关系*/
);
SELECT * FROM orders;

/*订单项表*/
CREATE TABLE orderitem(
 iid CHAR(32) PRIMARY KEY,/*主键*/
 COUNT INT,/*数量*/
 subtotal DECIMAL(10,0),/*小计*/
 oid CHAR(32),/*所属订单*/
 bid CHAR(32),/*订单项所指的商品*/
 FOREIGN KEY (oid) REFERENCES orders(oid),/*建立主外键关系*/
 FOREIGN KEY (bid) REFERENCES book(bid)/*建立主外键关系*/
 );
 
 SELECT * FROM orderitem;
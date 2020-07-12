DROP DATABASE IF EXISTS bookmanager;
CREATE DATABASE bookmanager DEFAULT CHARACTER SET utf8;
USE bookmanager;
    
drop table if exists user;
drop table if exists role;
drop table if exists permission;
drop table if exists user_role;
drop table if exists role_permission;
drop table if exists book;
    
create table user (
  `id` bigint auto_increment,
  `name` varchar(100),
  `email` varchar(100),
  `password` varchar(100),
  `salt` varchar(100),
  constraint pk_users primary key(id)
) charset=utf8 ENGINE=InnoDB;
    
create table role (
  `id` bigint auto_increment,
  `name` varchar(100),
  `desc_` varchar(100),
  constraint pk_roles primary key(id)
) charset=utf8 ENGINE=InnoDB;
    
create table permission (
  `id` bigint auto_increment,
  `name` varchar(100),
  `desc_` varchar(100),
  `url` varchar(100),
  constraint pk_permissions primary key(id)
) charset=utf8 ENGINE=InnoDB;
    
create table user_role (
  `id` bigint auto_increment,
  `uid` bigint,
  `rid` bigint,
  constraint pk_users_roles primary key(id)
) charset=utf8 ENGINE=InnoDB;
    
create table role_permission (
  `id` bigint auto_increment,
  `rid` bigint,
  `pid` bigint,
  constraint pk_roles_permissions primary key(id)
) charset=utf8 ENGINE=InnoDB;

CREATE TABLE book (
  `id` bigint auto_increment,
  `name` varchar(256),
  `author` varchar(256),
  `price` varchar(256),
  `status` int(11) DEFAULT '0',
  constraint pk_books primary key(id),
  unique key `name` (`name`)
) charset=utf8 ENGINE=InnoDB;
 
INSERT INTO `permission` VALUES (1,'addBook','增加书目','/addBook');
INSERT INTO `permission` VALUES (2,'deleteBook','删除书目','/deleteBook');
INSERT INTO `permission` VALUES (3,'updateBook','修改书目','/updateBook');
INSERT INTO `permission` VALUES (4,'borrowBook','借阅图书','/borrowBook');
INSERT INTO `permission` VALUES (5,'returnBook','归还图书','/returnBook');

INSERT INTO `role` VALUES (1,'admin','超级管理员');
INSERT INTO `role` VALUES (2,'user','普通用户');

INSERT INTO `role_permission` VALUES (1,1,1);
INSERT INTO `role_permission` VALUES (2,1,2);
INSERT INTO `role_permission` VALUES (3,1,3);
INSERT INTO `role_permission` VALUES (4,1,4);
INSERT INTO `role_permission` VALUES (5,1,5);
INSERT INTO `role_permission` VALUES (6,2,4);
INSERT INTO `role_permission` VALUES (7,2,5);

INSERT INTO `user` VALUES (1,'zhang3','zhang@163.com','a7d59dfc5332749cb801f86a24f5f590','e5ykFiNwShfCXvBRPr3wXg==');
INSERT INTO `user` VALUES (2,'li4','li@qq.com','43e28304197b9216e45ab1ce8dac831b','jPz19y7arvYIGhuUjsb6sQ==');

INSERT INTO `user_role` VALUES (1,2,2);
INSERT INTO `user_role` VALUES (2,1,1);

INSERT INTO `book` VALUES (1,'论一个演员的自我修养','斯坦尼斯拉夫斯基','20.40￥',0);
INSERT INTO `book` VALUES (2,'深入理解Java虚拟机','周志华','69.00￥',0);
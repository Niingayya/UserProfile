create database modestackdb;

create table user_profile (
	user_id int(11) NOT NULL AUTO_INCREMENT,
	user_name varchar(35) NOT NULL,
	password varchar(25) NOT NULL,
	email varchar(50) NOT NULL,
	address varchar(250) NOT NULL,
	access_token varchar(150),
	created_time datetime NOT NULL DEFAULT NOW(),
	updated_time datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (user_id)
)ENGINE=innodb DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


create table articles (
	article_id int(11) NOT NULL AUTO_INCREMENT,
	user_id int(11) NOT NULL,
	title varchar(35) NOT NULL,
	body mediumtext NOT NULL,
	author varchar(35) NOT NULL,
	access_token varchar(150),
	created_time datetime NOT NULL DEFAULT NOW(),
	updated_time datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (article_id),
	FOREIGN KEY (user_id) REFERENCES user_profile(user_id)
)ENGINE=innodb DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
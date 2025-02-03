DROP TABLE IF EXISTS `user`;
create table user
(
    id          bigint(20) unsigned auto_increment,
    username    varchar(60)  default '' not null COMMENT 'username',
    email       varchar(100) default '' not null COMMENT 'email',
    password    varchar(256) default '' not null COMMENT 'password；encode',
    phone       varchar(50)  default '' not null COMMENT 'phone',
    avatar      varchar(255) default '' not null COMMENT 'avatar',
    gender      tinyint(1) default '0' not null COMMENT 'gender；0：secret，1：male；2: female',
    birthday    date                    not null COMMENT 'birthday',
    is_active   tinyint(1) default '1' not null COMMENT 'default: true',
    bio         text                            COMMENT 'personal bio',
    create_time datetime                not null COMMENT 'create time',
    update_time datetime                not null COMMENT 'update time',
    deleted     tinyint(1) default 0 not null COMMENT 'logic-delete 1: delete 0: normal',
    primary key (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT 'user table';

DROP TABLE IF EXISTS `role`;
create table role
(
    id          bigint(20) unsigned auto_increment,
    name        varchar(255) not null COMMENT 'role name',
    create_time datetime     not null COMMENT 'create time',
    update_time datetime     not null COMMENT 'update time',
    deleted     tinyint(1) default 0 not null COMMENT 'logic-delete 1: delete 0: normal',
    primary key (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT 'role table';



DROP TABLE IF EXISTS `user_role`;
create table user_role
(
    id          bigint(20) unsigned auto_increment,
    user_id     bigint(20) not null COMMENT 'user id',
    role_id     bigint(20) not null COMMENT 'user id',
    create_time datetime not null COMMENT 'create time',
    update_time datetime not null COMMENT 'update time',
    deleted     tinyint(1) default 0 not null COMMENT 'logic-delete 1: delete 0: normal',
    primary key (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT 'user role table';



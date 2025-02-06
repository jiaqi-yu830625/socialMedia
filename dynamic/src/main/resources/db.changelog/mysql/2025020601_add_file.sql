DROP TABLE IF EXISTS `dyn_file`;
create table dyn_file
(
    id          bigint(20) unsigned auto_increment,
    file_name   varchar(255) COMMENT 'file_name',
    file_data   LONGBLOB COMMENT 'file data',
    create_time datetime not null COMMENT 'create time',
    update_time datetime not null COMMENT 'update time',
    deleted     tinyint(1) default 0 not null COMMENT 'logic-delete 1: delete 0: normal',
    primary key (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT 'file table';

DROP TABLE IF EXISTS `dyn_post`;
create table dyn_post
(
    id          bigint(20) unsigned auto_increment,
    user_id     bigint(20) not null COMMENT 'user id',
    content     text COMMENT 'post content',
    visibility  varchar(20) COMMENT 'public,friends,private',
    create_time datetime not null COMMENT 'create time',
    update_time datetime not null COMMENT 'update time',
    deleted     tinyint(1) default 0 not null COMMENT 'logic-delete 1: delete 0: normal',
    primary key (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT 'post table';

DROP TABLE IF EXISTS `dyn_post_img`;
create table dyn_post_img
(
    id          bigint(20) unsigned auto_increment,
    post_id     bigint(20) not null COMMENT 'post id',
    url         varchar(255) not null COMMENT 'image url',
    create_time datetime     not null COMMENT 'create time',
    update_time datetime     not null COMMENT 'update time',
    deleted     tinyint(1) default 0 not null COMMENT 'logic-delete 1: delete 0: normal',
    primary key (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT 'post image table';

DROP TABLE IF EXISTS `dyn_post_likes`;
create table dyn_post_likes
(
    id          bigint(20) unsigned auto_increment,
    user_id     bigint(20) not null COMMENT 'user id',
    post_id     bigint(20) not null COMMENT 'post id',
    create_time datetime not null COMMENT 'create time',
    update_time datetime not null COMMENT 'update time',
    deleted     tinyint(1) default 0 not null COMMENT 'logic-delete 1: delete 0: normal',
    primary key (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT 'post likes table';

DROP TABLE IF EXISTS `dyn_post_comment`;
create table dyn_post_comment
(
    id                bigint(20) unsigned auto_increment,
    user_id           bigint(20) not null COMMENT 'user id',
    post_id           bigint(20) not null COMMENT 'post id',
    comment           text     not null COMMENT 'comment',
    parent_comment_id bigint(20) not null default '0' COMMENT 'parent comment id(use to comment the comment)',
    create_time       datetime not null COMMENT 'create time',
    update_time       datetime not null COMMENT 'update time',
    deleted           tinyint(1) default 0 not null COMMENT 'logic-delete 1: delete 0: normal',
    primary key (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT 'post comment table';

DROP TABLE IF EXISTS `dyn_user_follows`;
create table dyn_user_follows
(
    id          bigint(20) unsigned auto_increment,
    user_id     bigint(20) not null COMMENT 'user id',
    follower_id bigint(20) not null COMMENT 'follower id',
    followee_id bigint(20) not null COMMENT 'followee id',
    create_time datetime not null COMMENT 'create time',
    update_time datetime not null COMMENT 'update time',
    deleted     tinyint(1) default 0 not null COMMENT 'logic-delete 1: delete 0: normal',
    primary key (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT 'user follows table';

DROP TABLE IF EXISTS `notifications`;
create table dyn_notifications
(
    id          bigint(20) unsigned auto_increment,
    user_id     bigint(20) not null COMMENT 'user id',
    source_id   bigint(20) not null COMMENT 'from user or dynamic',
    type        varchar(20) not null COMMENT 'type(like,follow,comment)',
    is_read     tinyint(1) not null COMMENT 'is read',
    create_time datetime    not null COMMENT 'create time',
    update_time datetime    not null COMMENT 'update time',
    deleted     tinyint(1) default 0 not null COMMENT 'logic-delete 1: delete 0: normal',
    primary key (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT 'notifications table';


CREATE DATABASE if not exists bigdata charset utf8;
use bigdata;
create table if not exists interface_info
(
    id                varchar(32)      not null comment 'id'
        primary key,
    server_id         int              null,
    server_url        varchar(128)     null comment '服务器id',
    interface_code    varchar(64)      null comment '编码code'

);

CREATE DATABASE if not exists plat charset utf8;
use plat;
create table if not exists t_sys_user
(
    ID              varchar(64)  not null comment 'ID'
        primary key,
    DEPT_ID         varchar(64)  null comment 'ID',
    LOGIN_NAME      varchar(64)  not null comment '登录帐号',
    PASSWD          varchar(64)  not null comment '登录密码',
    USER_NAME       varchar(64)  not null comment '用户名称'
)
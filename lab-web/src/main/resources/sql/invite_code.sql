drop table if exists T_INVITE_CODE;

/*==============================================================*/
/* Table: T_INVITE_CODE                                         */
/*==============================================================*/
create table T_INVITE_CODE
(
   id                   int not null auto_increment comment '主键',
   number               varchar(50) comment '邀请码',
   isUsed               int comment '是否使用',
   primary key (id)
);

alter table T_INVITE_CODE comment '邀请码';

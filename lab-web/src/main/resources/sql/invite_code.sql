drop table if exists T_INVITE_CODE;

/*==============================================================*/
/* Table: T_INVITE_CODE                                         */
/*==============================================================*/
create table T_INVITE_CODE
(
   id                   int not null auto_increment comment '主键',
   number               varchar(50) comment '邀请码',
   user_id              int comment '创建者',
   primary key (id)
);

alter table T_INVITE_CODE comment '邀请码';

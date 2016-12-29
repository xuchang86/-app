drop table if exists T_CHAT_RECORD;

/*==============================================================*/
/* Table: T_CHAT_RECORD                                         */
/*==============================================================*/
create table T_CHAT_RECORD
(
   id                   int not null auto_increment comment '主键',
   user_id              int comment '用户id',
   from_id              varchar(50) comment '发起人id',
   to_id                varchar(50) comment '接收人id',
   content              varchar(2000) comment '内容',
   create_time          timestamp comment '创建时间',
   primary key (id)
);

alter table T_CHAT_RECORD comment '聊天记录';

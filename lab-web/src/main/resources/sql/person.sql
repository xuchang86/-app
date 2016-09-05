drop table if exists T_BASE_PERSON;

/*==============================================================*/
/* Table: T_BASE_PERSON                                         */
/*==============================================================*/
create table T_BASE_PERSON
(
   id                   int not null auto_increment comment '主键',
   user_id              int comment '用户id',
   level                int comment '等级',
   bill                 decimal(10,2) comment '逍遥币',
   parent_id            int comment '师傅id',
   create_date          timestamp comment '创建时间',
   name                 varchar(20) comment '昵称',
   primary key (id)
);

alter table T_BASE_PERSON comment '个人信息表';

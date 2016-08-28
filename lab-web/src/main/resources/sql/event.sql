drop table if exists T_MP_EVENT;

/*==============================================================*/
/* Table: T_MP_EVENT                                            */
/*==============================================================*/
create table T_MP_EVENT
(
   id                   int not null auto_increment comment '主键',
   content              varchar(800) comment '公告内容',
   person_id            int comment '公告发起人',
   start_date           timestamp comment '公告开始时间',
   end_date             timestamp comment '公告截止时间',
   primary key (id)
);

alter table T_MP_EVENT comment '门派事件';

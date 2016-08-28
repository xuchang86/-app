drop table if exists T_PUBLISH_ACTIVITY;

/*==============================================================*/
/* Table: T_PUBLISH_ACTIVITY                                    */
/*==============================================================*/
create table T_PUBLISH_ACTIVITY
(
   id                   int not null auto_increment comment '主键',
   type                 varchar(20) comment '活动类别',
   address              varchar(50) comment '活动地址',
   content              varchar(500) comment '活动内容',
   date                 timestamp comment '活动时间',
   person_id            int comment '活动发起人',
   cost                 decimal(10,2) comment '费用',
   primary key (id)
);

alter table T_PUBLISH_ACTIVITY comment '发布的活动';

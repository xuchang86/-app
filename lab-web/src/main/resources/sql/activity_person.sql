drop table if exists T_ACTIVITY_PERSON;

/*==============================================================*/
/* Table: T_ACTIVITY_PERSON                                     */
/*==============================================================*/
create table T_ACTIVITY_PERSON
(
   id                   int not null auto_increment comment '主键',
   person_id            int comment '参与人id',
   activity_id          int comment '活动id',
   primary key (id)
);

alter table T_ACTIVITY_PERSON comment '活动参与人';

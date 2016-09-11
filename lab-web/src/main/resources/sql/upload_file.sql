drop table if exists T_UPLOAD_FILE;

/*==============================================================*/
/* Table: T_UPLOAD_FILE                                         */
/*==============================================================*/
create table T_UPLOAD_FILE
(
   id                   int not null auto_increment comment '主键',
   name                 varchar(100) comment '图片名称',
   user_id              int comment '用户ID',
   activity_id          int comment '活动ID',
   type                 varchar(15) comment '图片类型',
   create_date          date comment '上传时间',
   primary key (id)
);

alter table T_UPLOAD_FILE comment '上传图片';

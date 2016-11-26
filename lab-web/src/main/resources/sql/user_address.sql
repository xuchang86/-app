drop table if exists T_USER_ADDRESS;

/*==============================================================*/
/* Table: T_USER_ADDRESS                                        */
/*==============================================================*/
create table T_USER_ADDRESS
(
   id                   int not null auto_increment comment '主键',
   user_id              int comment '用户ID',
   contracts            varchar(50) comment '联系人',
   phone                varchar(50) comment '电话',
   city                 varchar(50) comment '城市',
   address              varchar(100) comment '详细地址',
   primary key (id)
);

alter table T_USER_ADDRESS comment '个人收货地址';

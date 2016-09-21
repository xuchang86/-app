drop table if exists T_GOODS_SELLER;

/*==============================================================*/
/* Table: T_GOODS_SELLER                                        */
/*==============================================================*/
create table T_GOODS_SELLER
(
   id                   int not null auto_increment comment '主键',
   nick_name            varchar(20) comment '昵称',
   real_name            varchar(20) comment '真实姓名',
   city                 varchar(50) comment '城市',
   phone                varchar(20) comment '联系电话',
   email                varchar(20) comment '邮箱',
   account              varchar(30) comment '账户',
   primary key (id)
);

alter table T_GOODS_SELLER comment '卖家信息';

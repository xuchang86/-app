drop table if exists T_GOODS_TYPE;

/*==============================================================*/
/* Table: T_GOODS_TYPE                                          */
/*==============================================================*/
create table T_GOODS_TYPE
(
   id                   int not null auto_increment comment '主键',
   name                 varchar(20) comment '类型名称',
   number               varchar(20) comment '类型编码',
   level                int comment '类型级别',
   parent_id            int comment '上级id',
   url                  varchar(100) comment '类型图片',
   primary key (id)
);

alter table T_GOODS_TYPE comment '商品类别';

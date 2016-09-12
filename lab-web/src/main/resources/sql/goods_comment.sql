drop table if exists T_GOODS_COMMENT;

/*==============================================================*/
/* Table: T_GOODS_COMMENT                                       */
/*==============================================================*/
create table T_GOODS_COMMENT
(
   id                   int not null auto_increment comment '主键',
   user_id              int comment '评论人',
   create_date          timestamp comment '评论时间',
   content              varchar(500) comment '评论内容',
   goods_id             int comment '商品id',
   primary key (id)
);

alter table T_GOODS_COMMENT comment '物品评论';

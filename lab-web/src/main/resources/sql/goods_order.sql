drop table if exists T_GOODS_ORDER;

/*==============================================================*/
/* Table: T_GOODS_ORDER                                         */
/*==============================================================*/
create table T_GOODS_ORDER
(
   id                   int not null auto_increment comment '主键',
   number               varchar(20) comment '订单号',
   create_date          timestamp comment '创建时间',
   pay_date             timestamp comment '付款时间',
   user_id              int comment '创建用户id',
   goods_id             varchar(20) comment '商品id(多个以逗号隔开)',
   address              varchar(50) comment '收货地址',
   contacts             varchar(10) comment '联系人',
   phone                varchar(20) comment '联系电话',
   state                varchar(20) comment '订单状态',
   goods_model          varchar(20) comment '商品型号',
   address_id           int comment '收货地址ID',
   amount               decimal(20,2) comment '付款金额',
   primary key (id)
);

alter table T_GOODS_ORDER comment '商品订单';

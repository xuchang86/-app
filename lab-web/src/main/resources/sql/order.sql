drop table if exists T_PAY_ORDER;

/*==============================================================*/
/* Table: T_PAY_ORDER                                           */
/*==============================================================*/
create table T_PAY_ORDER
(
   id                   int not null auto_increment comment '主键',
   order_code           varchar(50) comment '订单号',
   pay_date             timestamp comment '付款时间',
   user_id              int comment '付款人',
   pay_amount           decimal(10,2) comment '付款金额',
   primary key (id)
);

alter table T_PAY_ORDER comment '付款订单信息';

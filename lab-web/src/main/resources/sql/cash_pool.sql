drop table if exists T_CASH_POOL;

/*==============================================================*/
/* Table: T_CASH_POOL                                           */
/*==============================================================*/
create table T_CASH_POOL
(
   id                   int not null auto_increment comment '主键',
   money                decimal(10,2) comment '资金',
   user_id              int comment '创建人',
   createDate           date comment '创建时间',
   platform             decimal(10,2) comment '平台收入',
   primary key (id)
);

alter table T_CASH_POOL comment '资金池';

INSERT INTO `T_CASH_POOL` (`id`, `money`, `user_id`, `createDate`, `platform`) VALUES (1, 0.00, 1, '2016-9-11', 0.00);


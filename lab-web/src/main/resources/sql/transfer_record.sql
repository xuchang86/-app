drop table if exists T_TRANSFER_RECORD;

/*==============================================================*/
/* Table: T_TRANSFER_RECORD                                     */
/*==============================================================*/
create table T_TRANSFER_RECORD
(
   id                   int not null auto_increment comment '主键',
   date                 timestamp comment '提现日期',
   operator             varchar(50) comment '提现人',
   account_id           int comment '提现账户',
   amount               decimal(10,2) comment '提现金额',
   state                varchar(20) comment '提现状态',
   user_id              int comment '用户id',
   primary key (id)
);

alter table T_TRANSFER_RECORD comment '提现记录';

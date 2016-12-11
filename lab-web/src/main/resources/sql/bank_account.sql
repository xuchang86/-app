drop table if exists T_BANK_ACCOUNT;

/*==============================================================*/
/* Table: T_BANK_ACCOUNT                                        */
/*==============================================================*/
create table T_BANK_ACCOUNT
(
   id                   int not null auto_increment comment '主键',
   name                 varchar(50) comment '银行名称',
   account              varchar(50) comment '银行账户',
   receiver             varchar(50) comment '收款人',
   user_id              int comment '用户id',
   primary key (id)
);

alter table T_BANK_ACCOUNT comment '银行账户信息';

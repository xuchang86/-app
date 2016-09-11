drop table if exists T_MEMBERS_RULE;

/*==============================================================*/
/* Table: T_MEMBERS_RULE                                        */
/*==============================================================*/
create table T_MEMBERS_RULE
(
   id                   int not null auto_increment comment '主键',
   level                int(5) comment '等级',
   level_count          int(5) comment '升级人数',
   money_pool           decimal(10,2) comment '资金池',
   packet               decimal(10,2) comment '徒弟红包',
   packet_count         int(5) comment '徒弟红包总数',
   child_packet         decimal(10,2) comment '徒孙给师傅的红包',
   child_packet_count   int(5) comment '徒孙给师傅的红包总数',
   upgrade_awards       decimal(10,2) comment '升级奖励',
   member_income        decimal(10,2) comment '会员收入',
   platform_income      decimal(10,2) comment '平台收入',
   remark               varchar(100) comment '备注',
   total_child          int(5) comment '弟子总数',
   primary key (id)
);

alter table T_MEMBERS_RULE comment '会员成长规则';

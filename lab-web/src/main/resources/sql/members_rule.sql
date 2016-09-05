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
   primary key (id)
);

alter table T_MEMBERS_RULE comment '会员成长规则';

INSERT INTO `T_MEMBERS_RULE` (`id`, `level`, `level_count`, `money_pool`, `packet`, `packet_count`, `child_packet`, `child_packet_count`, `upgrade_awards`, `member_income`, `platform_income`, `remark`) VALUES (1, 1, 1, 400.00, 60.00, 60, 30.00, 30, 100.00, 190.00, 110.00, '见习弟子');
INSERT INTO `T_MEMBERS_RULE` (`id`, `level`, `level_count`, `money_pool`, `packet`, `packet_count`, `child_packet`, `child_packet_count`, `upgrade_awards`, `member_income`, `platform_income`, `remark`) VALUES (2, 2, 2, 800.00, 70.00, 140, 35.00, 70, 200.00, 410.00, 390.00, '精英弟子');
INSERT INTO `T_MEMBERS_RULE` (`id`, `level`, `level_count`, `money_pool`, `packet`, `packet_count`, `child_packet`, `child_packet_count`, `upgrade_awards`, `member_income`, `platform_income`, `remark`) VALUES (3, 3, 3, 1200.00, 80.00, 240, 40.00, 120, 300.00, 660.00, 540.00, '副组长');
INSERT INTO `T_MEMBERS_RULE` (`id`, `level`, `level_count`, `money_pool`, `packet`, `packet_count`, `child_packet`, `child_packet_count`, `upgrade_awards`, `member_income`, `platform_income`, `remark`) VALUES (4, 4, 4, 1600.00, 90.00, 360, 45.00, 180, 400.00, 940.00, 660.00, '组长');
INSERT INTO `T_MEMBERS_RULE` (`id`, `level`, `level_count`, `money_pool`, `packet`, `packet_count`, `child_packet`, `child_packet_count`, `upgrade_awards`, `member_income`, `platform_income`, `remark`) VALUES (5, 5, 5, 2000.00, 100.00, 500, 50.00, 250, 500.00, 1250.00, 750.00, '副队长');
INSERT INTO `T_MEMBERS_RULE` (`id`, `level`, `level_count`, `money_pool`, `packet`, `packet_count`, `child_packet`, `child_packet_count`, `upgrade_awards`, `member_income`, `platform_income`, `remark`) VALUES (6, 6, 6, 2400.00, 110.00, 660, 55.00, 330, 600.00, 1590.00, 810.00, '队长');
INSERT INTO `T_MEMBERS_RULE` (`id`, `level`, `level_count`, `money_pool`, `packet`, `packet_count`, `child_packet`, `child_packet_count`, `upgrade_awards`, `member_income`, `platform_income`, `remark`) VALUES (7, 7, 7, 2800.00, 120.00, 840, 60.00, 420, 700.00, 1960.00, 840.00, '副堂主');
INSERT INTO `T_MEMBERS_RULE` (`id`, `level`, `level_count`, `money_pool`, `packet`, `packet_count`, `child_packet`, `child_packet_count`, `upgrade_awards`, `member_income`, `platform_income`, `remark`) VALUES (8, 8, 8, 3200.00, 130.00, 1040, 65.00, 520, 800.00, 2360.00, 840.00, '堂主');
INSERT INTO `T_MEMBERS_RULE` (`id`, `level`, `level_count`, `money_pool`, `packet`, `packet_count`, `child_packet`, `child_packet_count`, `upgrade_awards`, `member_income`, `platform_income`, `remark`) VALUES (9, 9, 9, 3600.00, 140.00, 1260, 70.00, 630, 900.00, 2790.00, 810.00, '副舵主');
INSERT INTO `T_MEMBERS_RULE` (`id`, `level`, `level_count`, `money_pool`, `packet`, `packet_count`, `child_packet`, `child_packet_count`, `upgrade_awards`, `member_income`, `platform_income`, `remark`) VALUES (10, 10, 10, 4000.00, 150.00, 1500, 75.00, 750, 1000.00, 3250.00, 750.00, '舵主');
INSERT INTO `T_MEMBERS_RULE` (`id`, `level`, `level_count`, `money_pool`, `packet`, `packet_count`, `child_packet`, `child_packet_count`, `upgrade_awards`, `member_income`, `platform_income`, `remark`) VALUES (11, 11, 15, 6000.00, 160.00, 2400, 80.00, 1200, 1100.00, 4700.00, 1300.00, '青龙护法');
INSERT INTO `T_MEMBERS_RULE` (`id`, `level`, `level_count`, `money_pool`, `packet`, `packet_count`, `child_packet`, `child_packet_count`, `upgrade_awards`, `member_income`, `platform_income`, `remark`) VALUES (12, 12, 15, 6000.00, 170.00, 2550, 85.00, 1275, 1200.00, 5025.00, 975.00, '白虎护法');
INSERT INTO `T_MEMBERS_RULE` (`id`, `level`, `level_count`, `money_pool`, `packet`, `packet_count`, `child_packet`, `child_packet_count`, `upgrade_awards`, `member_income`, `platform_income`, `remark`) VALUES (13, 13, 20, 8000.00, 180.00, 3600, 90.00, 1800, 1300.00, 6700.00, 1300.00, '朱雀护法');
INSERT INTO `T_MEMBERS_RULE` (`id`, `level`, `level_count`, `money_pool`, `packet`, `packet_count`, `child_packet`, `child_packet_count`, `upgrade_awards`, `member_income`, `platform_income`, `remark`) VALUES (14, 14, 20, 8000.00, 190.00, 3800, 95.00, 1900, 1400.00, 7100.00, 900.00, '玄武护法');
INSERT INTO `T_MEMBERS_RULE` (`id`, `level`, `level_count`, `money_pool`, `packet`, `packet_count`, `child_packet`, `child_packet_count`, `upgrade_awards`, `member_income`, `platform_income`, `remark`) VALUES (15, 15, 25, 10000.00, 200.00, 5000, 100.00, 2500, 1500.00, 9000.00, 1000.00, '逍遥左使');
INSERT INTO `T_MEMBERS_RULE` (`id`, `level`, `level_count`, `money_pool`, `packet`, `packet_count`, `child_packet`, `child_packet_count`, `upgrade_awards`, `member_income`, `platform_income`, `remark`) VALUES (16, 16, 30, 12000.00, 210.00, 6300, 105.00, 3150, 1800.00, 11250.00, 750.00, '逍遥右使');
INSERT INTO `T_MEMBERS_RULE` (`id`, `level`, `level_count`, `money_pool`, `packet`, `packet_count`, `child_packet`, `child_packet_count`, `upgrade_awards`, `member_income`, `platform_income`, `remark`) VALUES (17, 17, 40, 16000.00, 220.00, 8800, 110.00, 4400, 2000.00, 15200.00, 800.00, '大长老');
INSERT INTO `T_MEMBERS_RULE` (`id`, `level`, `level_count`, `money_pool`, `packet`, `packet_count`, `child_packet`, `child_packet_count`, `upgrade_awards`, `member_income`, `platform_income`, `remark`) VALUES (18, 18, NULL, NULL, 230.00, NULL, 115.00, NULL, NULL, NULL, NULL, '副掌门');


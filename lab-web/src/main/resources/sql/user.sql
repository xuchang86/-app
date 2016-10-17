drop table if exists T_LOGIN_USER;

/*==============================================================*/
/* Table: T_LOGIN_USER                                          */
/*==============================================================*/
create table T_LOGIN_USER
(
   id                   int not null auto_increment comment '主键',
   phone                varchar(20) comment '手机号',
   password             varchar(50) comment '密码',
   name                 varchar(20) comment '姓名',
   birthday             date comment '生日',
   address              varchar(50) comment '地址',
   providerId           varchar(20) comment '能提供的资源',
   requiredId           varchar(20) comment '需要的资源',
   city                 varchar(20) comment '城市',
   sex                  int comment '性别',
   userName             varchar(15) comment '用户名',
   isPay                int comment '是否支付',
   url                  varchar(20) comment '个人头像',
   primary key (id)
);

alter table T_LOGIN_USER comment '登陆用户';

20161022 本次更新内容:
1.修改登录时返回的用户信息中URL没有绝对路径问题
2.增加获取当前环信用户好友接口(返回的结果集直接用的环信返回的结果集没有包装)
URL: https://ip:端口/xyp/xiaoyao/getFriends.do?userId=用户id不能为空
3.增加通过群id获取聊天群用户接口(结果集为环信返回结果集没有二次包装)
URL: https://ip:端口/xyp/xiaoyao/getChatGroupUsers.do?groupId=环信聊天群id不能为空


20161023 本次更新内容:
1.增加活动信息,我的徒弟,逍遥币排行榜,弟子排行榜各个接口中User对象,user对象中URL为个人头像


20161024 本次更新内容:
1.修改查询门派事件接口名称为:querySchoolEvent(URL: https://ip:端口/xyp/event/querySchoolEvent.do),
以前的queryAllEvent接口已废弃
2.增加系统事件接口,URL: https://ip:端口/xyp/event/querySystemEvent.do


20161027 本次更新内容:
1.修改获取当前环信用户好友接口,返回值封装为User集合
URL: https://ip:端口/xyp/xiaoyao/getFriends.do?userId=用户id不能为空
2.修改通过群id获取聊天群用户接口,返回值封装为User集合
URL: https://ip:端口/xyp/xiaoyao/getChatGroupUsers.do?groupId=环信聊天群id不能为空


20161102 本次更新内容
1.增加支付宝支付签名接口:
URL: https://ip:端口/xyp/pay/alipaySign.do?userId=用户id不能为空&inviteCode=邀请码不能为空
备注:增加该接口后获取支付宝的通知回调接口getAliaPayURL,就不需要调用了,因为在alipaySign接口中已经传递.
2.暂时先注释掉注册时验证码的校验,该验证交由安卓端验证


20161103 本次更新内容
1.恢复注册时的验证码校验
2.修改确认提交的报错缺陷
3.恢复支付宝签名私钥为旧私钥,避免调用报错(最新申请的私钥还需要修正后验证)


20161108  本次更新内容
1、新增商城获取分类接口:  https://ip:port/xyp/mall/queryGoodsType.do
   新增通过分类获取商品接口: https://ip:port/xyp/mall/queryGoodsByTypeId.do?typeId=类型Id不能为空
   
2、商品价格为原价price,增加会员价为vipPrice

3、新增查询精选商品接口:  https://ip:port/xyp/mall/queryNiceGoods.do

4. 增加商品等级level字段,0 正常,1 精品,2 完美,3 差

5、订单中退货状态已有

6、门派活动、悬赏活动、出售活动中
新增:活动方式(way)字段,活动方式包括:打听(ask_about),兼职(part_time),其他(other)
新增:付款方式(payway)字段,付款方式包括:AA(aa),男A女免费(man_a_woman_free),女A男免费(woman_a_man_free),全部免费(all_free)
修改接口:queryAllActivity(查询所有的门派活动),queryAllTask(查询所有悬赏任务),queryAllService(查询所有的出售服务)这三个接口增加参数 城市(city),如果没有传则查全部,传了会查该城市下的活动。

7、新增充值签名接口: https://ip:port/xyp/pay/rechargeSign.do?userId=用户id不能为空&amount=充值金额不能为空 

8、转账需要逍遥派客户人员自己线下转账,无法提供

9.新增通过用户id集合获取用户组信息接口 : https://ip:port/xyp/xiaoyao/queryUserByIds?userId=用户id逗号隔开
新增通过手机号集合获取用户组信息接口: https://ip:port/xyp/xiaoyao/queryUserByIds?phones=手机号逗号隔开
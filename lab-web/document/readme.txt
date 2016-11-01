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

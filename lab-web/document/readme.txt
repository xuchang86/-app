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


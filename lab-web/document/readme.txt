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


20161109  本次更新内容
1.修改alipaySign支付宝注册接口和rechargeSign支付宝充值接口,去除notify_url中的参数传递
2.去除getAliaPayURL接口和getRechargeURL接口中的参数传递


20161118  本次更新内容
1.增加获取售后订单接口:
https://ip:port/xyp/mall/querySellAfterOrders.do?userId=用户id不能为空
2.增加获取退货订单接口
https://ip:port/xyp/mall/queryReturnOrders.do?userId=用户id不能为空
3.增加获取物品评论信息接口
https://ip:port/xyp/mall/getComments.do?goodsId=物品id不能为空



20161126  本次更新内容
1.新增收货地址
https://ip:port/xyp/mall/addAddress.do?userId=用户id不能为空&contracts=联系人不能为空&phone=联系电话不能为空&city=城市不能为空&address详细地址不能为空
返回值:收货地址id
2.修改收货地址
https://ip:port/xyp/mall/modifyAddress.do?id=地址id不能为空
返回值:收货地址id
3.删除收货地址
https://ip:port/xyp/mall/deleteAddress.do?addressId=地址id不能为空
4.我的收货地址
https://ip:port/xyp/mall/queryAddress.do?userId=用户id不能为空
地址集合
5.确认订单
https://ip:port/xyp/mall/confirmOrder.do?userId=用户id不能为空&goodsId=商品id不能为空&amount=付款金额不能为空&goodsModel=商品型号不能为空&addressId=联系地址不能为空
返回值:订单id
6.确认支付
https://ip:port/xyp/mall/confirmPayment.do?orderId=订单id不能为空&userId=用户id&amount=付款金额不能为空
返回值:订单id



20161127  本次更新内容
1.新增批量确认订单
https://ip:port/xyp/mall/batchConfirmOrder.do?orders=JSON数组格式
JSON数组格式为:
[{
	goodsId: 2,//物品id
	userId: 1,//用户id
	amount: 100,//订单金额
	goodsModel: X,//型号
	addressId: 2 //地址id
}]
返回值:订单id数组
2.新增批量确认支付
https://ip:port/xyp/mall/batchConfirmPayment.do?orders=JSON数组格式
JSON数组格式为:
[{
	orderId: 11,//订单id
	userId: 1,//用户id
	amount: 1//订单金额
}];	
返回值:订单id数组
3.新增删除订单接口
https://ip:port/xyp/mall/deleteOrder.do?orderId=订单id
4.新增通过订单状态查询订单接口
https://ip:port/xyp/mall/queryOrderByState.do?state=订单状态(如果是多个状态以逗号隔开,单个状态直接传递)&
状态值如下
待付款:todo
已付款:paying
已发货:to
已收货:get
售后:after_sale
退货:sales_return
5.修改通过订单状态查询订单接口中增加参数userId
,订单返回值增加商品信息
https://ip:port/xyp/mall/queryOrderByState.do?state=订单状态&userId=不能为空
6.修改评价接口,增加score评分参数;
https://ip:port/xyp/mall/comments.do?userId=用户id&content=评论内容&orderId=订单id&score=评分
7.修改退货接口,增加参数状态参数(退货和售后合并)
https://ip:port/xyp/mall/returnSale.do?userId=用户id&orderId=订单id&state=订单状态
state订单状态值必须是sales_return(退货),after_sale(售后)
8.增加商品查询通用查询
https://ip:port/xyp/mall/queryGoods.do
参数说明:
(1).不传参数为查询所有
(2).sortField排序字段可以是id, name(名称), description(描述), number(编码), type_id, price(价格), user_id, create_date(创建日期), url, area, is_sale, seller_id, vip_price(会员价格), level(级别), model, sales(销量)
(3).sortType排序类型:desc,asc
(4).pageSize 分页参数,显示的页数
(5).pageNo 页码
(6).字段属性可以是如下:
/** 商品主键 */
private Integer id;
/** 商品名称 */
private String name;
/** 商品描述 */
private String description;
/** 商品编码 */
private String number;
/** 商品类别id */
private Integer typeId;
/** 商品价格 */
private BigDecimal price;
/** 用户id */
private Integer userId;
/** 创建时间 */
private Date createDate;(日期格式以2016-11-27类似即可)
/** 商品图片url */
private String url;
/** 商品所在地 */
private String area;
/** 是否出售 */
private Boolean isSale;
/** 卖家id */
private Integer sellerId;
/** 会员价格 */
private BigDecimal vipPrice;
/** 商品等级 {@link com.xiaoyao.mall.model.GoodsLevel} */
private Integer level;
/** 销量 */
private Integer sales;
/** 商品型号 */
private String model;
9.修改获取评论信息,增加分页参数pageSize,pageNo,
评论信息返回值增加用户信息



20161204  本次更新内容
1.增加人物之间的转账功能
URL:https://ip:port/xyp/person/exchangeBill.do?srcPersonId=来源人物id不能为空&targetPersonId=目标人物id不能为空&amount=转账金额不能为空
参数:注传的都是人物(person)id不是用户(User)id,可通过queryPerson接口获取其他人物信息
2.增加人物通用查询功能
URL:https://ip:port/xyp/person/queryPerson.do?pageSize=20&sortField=bill&sortType=desc&name=人物名称
支持分页,排序，以及各条件查询,不传参数为全部查询
排序参数:sortField(排序字段),sortType(排序类型)
排序字段值:id, user_id, level, bill, parent_id, create_date, name
排序类型值: desc,asc 
分页参数:pageSize,pageNo(默认不传参显示10个每页)
查询参数:id,userId(用户id),level(级别),bill(逍遥币),parentId(师傅id),createDate(创建时间),name(人物名称)



20161207  本次更新内容
1.增加发布活动时的个人权限控制(需在后台管理,用户信息中维护个人发布权限)
2.修改注册时生成的群名称



20161210  本次更新内容
1.修改editUser接口将用户付款状态清空问题
2.增加发布活动，悬赏，出售服务排序功能
参数:
sortType 排序类型 值为desc(降序) asc(升序)
sortField  排序字段 值可以传 id, type, address, content, date (创建时间), person_id, cost (费用), city, way, payWay


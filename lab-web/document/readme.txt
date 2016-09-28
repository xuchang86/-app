本次更新内容:

1.需要分页的传参数pageSize,pageNo
商城相关接口:
2.获取商城所有物品:ip:端口/xyp/mall/queryAllGoods.do?pageSize=一页显示多少数&pageNo=页码
3.确认支付:ip:端口/xyp/mall/confirmPayment.do?goodsId=商品id&address=联系地址&userId=用户id&contacts=联系人&phone=联系电话&amount=付款金额
4.待收货的订单信息:ip:端口/xyp/mall/todoGetGoods.do?userId=用户id
5.待发货:ip:端口/xyp/mall/todoSendGoods.do?userId=用户id
6.待评价:ip:端口/xyp/mall/todoComments.do?userId=用户id
7.评价:ip:端口/xyp/mall/comments.do?userId=用户id&content=评论内容&orderId=订单id
8.退货:ip:端口/xyp/mall/returnSale.do?userId=用户id&orderId=订单id


9.逍遥币排行榜: ip:端口/xyp/person/queryTopBill.do?pageSize=页数&pageNo=页码&sortField=排序字段&sortType=排序类型
10.弟子数量排行榜 ip:端口/xyp/person/queryTopChild.do?pageSize=页数&pageNo=页码&sortType=排序类型



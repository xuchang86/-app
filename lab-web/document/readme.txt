本次更新内容:

1.增加上传个人头像接口,请求地址: https://ip:端口/xyp/xiaoyao/uploadHead.do?userId=用户id
请求enctype需要为multipart/form-data ,参数从url中传即可。
需要注意的是contentType需要设置成image类型。

2.获取本人信息接口中同样增加头像图片字段,请求地址: https://ip:端口/xyp/xiaoyao/getCurrentUser.do?userId=用户id

3.增加手动调用已付款接口(在支付宝或者微信支付没调通时,可以调用该接口,将用户信息注册为已付款);
请求地址:   https://ip:端口/xyp/xiaoyao/payment.do?userId=用户id&inviteCode=邀请码

4.增加获取微信支付回调URL接口:
请求地址:   https://ip:端口/xyp/pay/getWechatPayURL.do?userId=用户id&inviteCode=邀请码

5.支付宝付款接口需要提供申请的:
1.申请的合作身份者ID
2.商户私钥
3.支付宝的公钥

package com.alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {
	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String APPID = "2016101702205247";
	// 商户的私钥
	public static String RSA_PRIVATE = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKqwfFLyJh+326GK8VpbJXF5Lj4StK3SQ9tUIXJhyZRqhNLsmZBOfUka1iVYSH+frqDoRMS8D4P3caTePccaeIFVprMOVuFRQd4oFB2zAHTVTfibYdcop8QfpmEF1BWWdNkNNvm4TUXu9rWNGELdJZpA7J4/HriwAP8U620uKeNDAgMBAAECgYBvH1qw/WiIecAI2VXrhy9HQqcyTidGin8WyRzFRJhhgT9buiD3a8Hw2AHWajU4sRKVOuGaWvyW438e4HjDqJ4ONO/sN3oiAVT+dIyaFWsaqxZ3Df7rRhkp4CVRLp879+X75tWOCoUQ6cShEjnwR3wNVtQHTT83NLjpHUGUS2mIQJBANpB28e06VDsy2Ti5mpDa8XNs49rS2BRgNuNLyApsqufZ/twLQjLDjdncB5a1uelXzbvIaJVF+hb8X0Kw3rfJqsCQQDINNI9S2Dl7Qhu3E+SiE5ZNh6ks50FTqqzJBhZXm8EylrN3uooWApW3WlR6+Im/Nj+ASc13g1MdtyQflQmWZXJAkAxxWz6GK5QDv++7ONAfkl4XRVCcmRI/a0V5BdIDVo6Ouq6cvVCGRQP3F27/lkdkB+rZlLUMvV1vmnOawLy5SprAkEAj3AYuMWKtPDwO80oTYnO6Ge36LSu6SCFeDeCInVgAQShriHpjeIV9kpHrOPdcaVyKfnVh+iBiqkWOYfo7lj80QJAb26Lb0891yCXxMR+otkPqOmX8+nRZh6+vrdGCmm4Zf6eufkHAV3S4x6QF5tf25jwaNZnQrTdAHFDZcn2TMd1wg==";
	
	// 支付宝的公钥，无需修改该值
	public static String ali_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	

	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "D:\\";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
	
	// 签名方式 不需修改
	public static String sign_type = "RSA";

}

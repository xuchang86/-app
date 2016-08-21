/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.base.util;

import java.security.MessageDigest;

/**
 * MD5加密解密工具类
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年8月20日 许畅 新建
 */
public final class MD5Util {

	/**
	 * 私有化构造方法
	 */
	private MD5Util() {
		super();
	}

	/**
	 * 利用JDK MD5单向加密
	 * 
	 * @param s
	 *            输入字符
	 * @return 加密为MD5类型
	 */
	public static String encodeToMD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 测试主函数
	public static void main(String args[]) {
		String s = "xuchang111";
		System.out.println("原始：" + s);
		System.out.println("MD5后：" + encodeToMD5(s));
		System.out.println("MD5后长度:" + encodeToMD5(s).length());
		System.out.println("是否相同:"
				+ encodeToMD5(s).equals("0b5d75b7581a59c2bdf9c64c67d784fd"));
	}

}

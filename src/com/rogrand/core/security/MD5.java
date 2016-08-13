package com.rogrand.core.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：字符串MD5加密,用于用户注册时候，用户密码加密处理。MD5无法解密
 */
public class MD5 {
    private static Log logger = LogFactory.getLog(MD5.class);
    public static String getEncrypt(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            byte[] bb = md.digest();
            String hs = "", tmp = "";
            for (byte e : bb) {
                tmp = (Integer.toHexString(e & 0xFF));
                hs = tmp.length() == 1 ? hs + "0" + tmp : hs + tmp;
            }
            return hs;
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage());
            return "";
        }
    }

    public static void main(String[] args) {
//        System.out.println(MD5.getEncrypt("888888"));
    }

}
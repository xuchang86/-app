package com.rogrand.core.security;

import java.security.Key;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：字符串加密，解密类
 */
public class AuthCode {

    private static String defaultKey = "com.rogrand";

    /**
     * 加密字符串
     *
     * @param strIn 需加密的字符串
     * @return 加密后的字符串
     */
    /*public static String encrypt(String strIn) {
        return encrypt(strIn, defaultKey);
    }*/


    /**
     * 加密字符串
     *
     * @param strIn  需加密的字符串
     * @param strKey 密钥
     * @return 加密后的字符串
     */
    /*public static String encrypt(String strIn, String strKey) {
        try {
            Security.addProvider(new SunJCE());
            Key key = getKey(strKey.getBytes());
            Cipher encryptCipher = Cipher.getInstance("DES");
            encryptCipher.init(Cipher.ENCRYPT_MODE, key);
            return new String(Base64.encodeBase64(encryptCipher.doFinal(strIn.getBytes())));
        }
        catch (Exception e) {
            return null;
        }
    }*/

    /**
     * 解密字符串
     *
     * @param strIn 需解密的字符串
     * @return 解密后的字符串
     */
    /*public static String decrypt(String strIn) {
        return decrypt(strIn, defaultKey);
    }*/

    /**
     * 解密字符串
     *
     * @param strIn  需解密的字符串
     * @param strKey 密钥
     * @return 解密后的字符串
     */
    /*public static String decrypt(String strIn, String strKey) {
        try {
            Security.addProvider(new SunJCE());
            Key key = getKey(strKey.getBytes());
            Cipher decryptCipher = Cipher.getInstance("DES");
            decryptCipher.init(Cipher.DECRYPT_MODE, key);
            return new String(decryptCipher.doFinal(Base64.decodeBase64(strIn.getBytes())));
        }
        catch (Exception e) {
            return null;
        }
    }*/

    /**
     * 从指定字符串生成密钥，密钥所需的字节数组长度为8位 不足8位时后面补0，超出8位只取前8位
     *
     * @param arrBTmp 构成该字符串的字节数组
     * @return 生成的密钥
     * @throws Exception 异常
     */
    private static Key getKey(byte[] arrBTmp) throws Exception {
        // 创建一个空的8位字节数组（默认值为0）
        byte[] arrB = new byte[8];
        // 将原始字节数组转换为8位
        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++)
            arrB[i] = arrBTmp[i];
        // 生成密钥
        return new javax.crypto.spec.SecretKeySpec(arrB, "DES");
    }


    
}


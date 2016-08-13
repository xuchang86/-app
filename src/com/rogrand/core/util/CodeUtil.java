package com.rogrand.core.util;

import java.io.ByteArrayOutputStream;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：字符工具类
 */
public class CodeUtil {

    private static String hexString = "0123456789abcdef";
    
    /**
     * 将字符串编码成十六进制数字,适用于所有字符（包括中文）
     * @param str
     * @return
     */
    public static String stringToHex(String str) {
        // 根据默认编码获取字节数组
        byte[] bytes = str.getBytes();
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        // 将字节数组中每个字节拆解成2位16进制整数
        for (int i = 0; i < bytes.length; i++) {
            sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
            sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
        }
        return sb.toString();
    }

    /**
     * 将十六进制数字解码成字符串,适用于所有字符（包括中文）
     * @param hex
     * @return
     */
    public static String hexToString(String hex) {
        String s = "";
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream(hex.length() / 2);
            // 将每2位16进制整数组装成一个字节
            for (int i = 0; i < hex.length(); i += 2) {
                baos.write((hexString.indexOf(hex.charAt(i)) << 4 | hexString.indexOf(hex.charAt(i + 1))));
            }
            s = new String(baos.toByteArray());
            baos.close();
        }
        catch (Exception e) {
            e.fillInStackTrace();
        }

        return s;
    }


    /**
     * 十六进制数字转换为二进制字节
     * @param hex
     * @return
     */
    public static byte[] hexToByte(String hex) {
        byte[] bts = new byte[hex.length() / 2];
        for (int i = 0; i < bts.length; i++) {
            bts[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2),
                    16);
        }
        return bts;
    }

    /**
     * 二进制字节转换为十六进制数字
     *
     * @param b
     * @return
     */
    public static String byteToHex(byte[] b) {
        String s = "";
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            s += hex;
        }
        return s;
    }

    /**
     * 二进制字节转换为字符串
     * @param b
     * @return
     */
    public static String byteToString(byte[] b) {
        String hex = byteToHex(b);
        return hexToString(hex);
    }

    /**
     * 字符串转换为二进制字节
     * @param s
     * @return
     */
    public static byte[] stringToByte(String s) {
        String hex = stringToHex(s);
        return hexToByte(hex);
    }

    /**
     * 字符串编译成unicode字符
     * @param str 字符串
     * @return unicode格式字符串
     */
    public static String encodeUnicode(String str) {
        StringBuffer out = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            out.append("\\u");
            String hex = Integer.toHexString(str.charAt(i) & 0xFFFF);
            for (int j = 0; j < 4 - hex.length(); j++) {
                out.append("0");
            }
            out.append(hex.toLowerCase());
        }
        return (new String(out));
    }

    /**
     * unicodo字符反编码
     * @param string unicode格式字符串
     * @return 还原的字符串
     */
    public static String decodeUnicode(String string) {
        int start = 0;
        int end = 0;
        final StringBuffer buffer = new StringBuffer();
        while (start > -1) {
            end = string.indexOf("\\u", start + 2);
            String charStr = "";
            if (end == -1) {
                charStr = string.substring(start + 2, string.length());
            } else {
                charStr = string.substring(start + 2, end);
            }
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
            buffer.append(Character.toString(letter));
            start = end;
        }
        return buffer.toString();
    }

}

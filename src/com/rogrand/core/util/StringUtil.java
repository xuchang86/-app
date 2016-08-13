package com.rogrand.core.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：字符串工具类
 */
public class StringUtil {
    
    private static SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddHHmmss");
    private final static char[] chr = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
    
    private final static String[] imgSuffix = { ".jpg", ".bmp", ".gif", ".png", ".jpeg", ".ico" };// jpg,gif,bmp,png
    
    public static String byte32(String value) {
        if (isEmpty(value))
            return value;
        byte[] bytes = value.getBytes();
        boolean err = false;
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] == 63) {
                bytes[i] = 32;
                err = true;
            }
        }
        if (err) {
            return new String(bytes);
        } else {
            return value;
        }
    }
    
    public static String null2string(Object v) {
        if (v == null) {
            return "";
        }
        return v.toString();
    }
    
    /**
     * 获得0-9的随机数
     * 
     * @param length
     * @return String
     */
    public static String getRandomNumber(int length) {
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        
        for (int i = 0; i < length; i++) {
            buffer.append(random.nextInt(10));
        }
        return buffer.toString();
    }
    
    /**
     * 忽略字符串两边的空白后,再判断字符是否为空
     * 
     * @param str
     *            某字符串
     * @return 为null或为空串则返回true，否则返回false
     */
    public static boolean isEmptyAfterTrim(String str) {
        return str == null || str.trim().length() == 0;
    }
    
    /**
     * 获得0-9的随机数 长度默认为10
     * 
     * @return String
     */
    public static String getRandomNumber() {
        return getRandomNumber(10);
    }
    
    /**
     * 获得0-9,a-z,A-Z范围的随机数
     * 
     * @param length
     *            随机数长度
     * @return String
     */
    
    public static String getRandomChar(int length) {
        
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            buffer.append(chr[random.nextInt(36)]);
        }
        return buffer.toString();
    }
    
    /**
     * 获得10位随机字符
     * 
     * @return String
     */
    public static String getRandomChar() {
        return getRandomChar(10);
    }
    
    /**
     * 获得主键
     * 
     * @return String
     */
    public static String getPK() {
        return dateformat.format(new Date()) + getRandomChar(18);
    }
    
    /**
     * 获得主键
     * 
     * @param len
     *            随机数长度
     * @return string
     */
    public static String getPK(int len) {
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateformat.format(new Date()) + getRandomChar(len);
    }
    
    /**
     * 判断字符是否为空
     * 
     * @param input
     *            某字符串
     * @return 包含则返回true，否则返回false
     */
    public static boolean isEmpty(String input) {
        return input == null || input.trim().length() == 0;
    }
    
    private static final String PATTERN_LINE_START = "^";
    
    private static final String PATTERN_LINE_END = "$";
    
    private static final char[] META_CHARACTERS = { '$', '^', '[', ']', '(', ')', '{', '}', '|', '+', '.', '\\' };
    
    /**
     * 正则表达式匹配 The function is based on regex.
     * 
     * @param pattern
     * @param str
     * @return
     */
    public static boolean regexMatch(String pattern, String str) {
        String result = PATTERN_LINE_START;
        char[] chars = pattern.toCharArray();
        for (char ch : chars) {
            if (Arrays.binarySearch(META_CHARACTERS, ch) >= 0) {
                result += "\\" + ch;
                continue;
            }
            switch (ch) {
                case '*':
                    result += ".*";
                    break;
                case '?':
                    result += ".{0,1}";
                    break;
                default:
                    result += ch;
            }
        }
        result += PATTERN_LINE_END;
        return Pattern.matches(result, str);
    }
    
    /**
     * 字符串首字母大写
     * 
     * @param str
     *            字符串
     * @return String
     */
    public static String upperFirstChar(String str) {
        if (str != null && str.length() > 0) {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        } else {
            return str;
        }
    }
    
    public static String lowerFirstChar(String str) {
        if (str != null && str.length() > 0) {
            return str.substring(0, 1).toLowerCase() + str.substring(1);
        } else {
            return str;
        }
    }
    
    public static boolean isIP(String ip) {
        Pattern pattern = Pattern.compile("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");
        Matcher matcher = pattern.matcher(ip);
        return matcher.matches();
    }
    
    public static boolean isIn(String[] strArray, String str) {
        if (strArray == null || strArray.length == 0) {
            return false;
        }
        for (String s : strArray) {
            if (s.equals(str)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 根据文件名判断该文件是不是图片，是图片返回true,否则返回false
     * 
     * @param name
     * @return
     * @author ZRH
     */
    public static Boolean isImg(Object name) {
        if (name == null) {
            return false;
        }
        if (name instanceof java.lang.String) {
            return (isImg((String) name));
        }
        return false;
    }
    
    /**
     * 根据文件名判断该文件是不是图片，是图片返回true,否则返回false
     * 
     * @param name
     * @return
     * @author ZRH
     */
    public static Boolean isImg(String name) {
        if (isEmpty(name)) {
            return false;
        }
        name = name.toLowerCase();
        for (String suffix : imgSuffix) {
            if (name.endsWith(suffix)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * 根据文件名判断一个map中是否有图片文件，有图片并且图片大小不为0返回true
     * 
     * @param name
     * @return
     * @author ZRH
     */
    public static boolean hasImg(Map<String, Object> rowData) {
        if (rowData == null || rowData.size() <= 0)
            return false;
        for (String key : rowData.keySet()) {
            if (StringUtil.isImg(rowData.get(key))) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 去掉头尾指定字符
     * 
     * @param value
     * @param c
     * @return
     */
    public static String trimChar(String value, char c) {
        return StringUtils.trimTrailingCharacter(StringUtils.trimLeadingCharacter(value, c), c);
    }
    
    /**
     * 字符串数组转换成Set集合
     * 
     * @param String
     *            [] args
     * @return Set<String>
     */
    public static Set<String> strArrayToSet(String[] args) {
        if (args == null || args.length == 0) {
            return new HashSet<String>();
        }
        Set<String> set = new HashSet<String>();
        for (String str : args) {
            if (!StringUtil.isEmpty(str)) {
                set.add(str);
            }
        }
        return set;
    }
    
    /**
     * Set集合转换成String
     * 
     * @param Set
     *            <String> args,String split_char 分隔符
     * @return String
     */
    public static String setToString(Set<String> args, String split_char) {
        if (args == null || args.isEmpty()) {
            return "";
        }
        return org.apache.commons.lang.StringUtils.join(args.toArray(new String[args.size()]), split_char);
    }
    
    /**
     * 去除字符串前的逗号，和结尾的逗号
     * 
     * @param contents
     * @return
     */
    public static String delFirstAndLastComma(String contents) {
        if (contents.startsWith(",") || contents.startsWith("，")) {
            contents = contents.substring(1);
        }
        if (contents.endsWith(",") || contents.endsWith("，")) {
            contents = contents.substring(0, contents.length() - 1);
        }
        if ((!contents.startsWith(",") && !contents.startsWith("，")) && (!contents.endsWith(",") && !contents.endsWith("，"))) {
            return contents;
        }
        return delFirstAndLastComma(contents);
    }
    
    /**
     * 查找字符串个数
     * 
     * @param str
     *            字符串
     * @param sub
     *            子串
     * @return int
     */
    public static int findCharCount(String str, String sub) {
        if (str == null || sub == null || str.length() == 0 || sub.length() == 0) {
            return 0;
        }
        int count = 0, pos = 0, idx = 0;
        while ((idx = str.indexOf(sub, pos)) != -1) {
            ++count;
            pos = idx + sub.length();
        }
        return count;
    }
    
    public static int count(String s) {
        try {
            byte[] bytes = s.getBytes("Unicode");
            int n = 0; // 表示当前的字节数
            int i = 2; // 要截取的字节数，从第3个字节开始
            for (; i < bytes.length; i++) {
                if (i % 2 == 1) { // 奇数位置，如3、5、7等，为UCS2编码中两个字节的第二个字节
                    if (bytes[i] != 0) {
                        n++;
                    }
                } else { // 当UCS2编码的第二个字节不等于0时，该UCS2字符为汉字，一个汉字算两个字节
                    n++;
                }
            }
            return n;
            
        } catch (UnsupportedEncodingException e) {
            return 0;
        }
    }
    
    public static String substring(String s, int byteLength) {
        if (s == null)
            return null;
        if (s.equals(""))
            return "";
        try {
            byte[] bytes = s.getBytes("Unicode");
            int n = 0; // 表示当前的字节数
            int i = 2; // 要截取的字节数，从第3个字节开始
            for (i = 2; i < bytes.length && n < byteLength; i++) {
                if (i % 2 == 1) { // 奇数位置，如3、5、7等，为UCS2编码中两个字节的第二个字节
                    if (bytes[i] != 0) {
                        n++;
                    }
                } else { // 当UCS2编码的第二个字节不等于0时，该UCS2字符为汉字，一个汉字算两个字节
                    n++;
                }
            }
            if (i % 2 == 1) { // 如果i为奇数时，处理成偶数
                if (bytes[i] != 0)
                    i = i - 1; // 该UCS2字符是汉字时，去掉这个截一半的汉字
                else
                    i = i + 1; // 该UCS2字符是字母或数字，则保留该字符
            }
            return new String(bytes, 0, i, "Unicode");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
    
    public static String substringHtml(String s, int byteLength) {
        if (s == null)
            return null;
        if (s.equals(""))
            return "";
        s = s.replaceAll("<.*?>|</.*?>", "");
        try {
            byte[] bytes = s.getBytes("Unicode");
            int n = 0; // 表示当前的字节数
            int i = 2; // 要截取的字节数，从第3个字节开始
            for (i = 2; i < bytes.length && n < byteLength; i++) {
                if (i % 2 == 1) { // 奇数位置，如3、5、7等，为UCS2编码中两个字节的第二个字节
                    if (bytes[i] != 0) {
                        n++;
                    }
                } else { // 当UCS2编码的第二个字节不等于0时，该UCS2字符为汉字，一个汉字算两个字节
                    n++;
                }
            }
            if (i % 2 == 1) { // 如果i为奇数时，处理成偶数
                if (bytes[i] != 0)
                    i = i - 1; // 该UCS2字符是汉字时，去掉这个截一半的汉字
                else
                    i = i + 1; // 该UCS2字符是字母或数字，则保留该字符
            }
            return new String(bytes, 0, i, "Unicode");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
    
    /**
     * 过滤特殊字符
     * 
     * @param value
     * @return
     */
    public static String escapeDir(String value) {
        String[] codes = { "/", "\\", ":", "*", "?", "\"", "<", ">", "|" };
        for (int i = 0; i < codes.length; i++) {
            value = value.replace(codes[i], "");
        }
        return value;
    }
    
    /**
     * 关键字分隔符
     * 
     * @param value
     * @return
     */
    public static String gjzDir(String value) {
        String[] codes = { ",", "，", ";", "；", " ", "?", "|", "&" };
        for (int i = 0; i < codes.length; i++) {
            value = value.replace(codes[i], ",");
        }
        return value;
    }
    
    /**
     * 去除字符串前，后，中的空格
     * 
     * @param value
     * @return
     */
    public static String bankDir(String value) {
        String[] codes = { " " };
        for (int i = 0; i < codes.length; i++) {
            value = value.replace(codes[i], "");
        }
        return value;
    }
    
    /**
     * 字符串分割并转成数组
     * 
     * @param input
     *            输入字符串
     * @param delim
     *            分隔符
     * @return 分隔后数组
     */
    public static String[] splitString(String input, String delim) {
        if (isEmpty(input)) {
            return new String[0];
        }
        ArrayList<String> a1 = new ArrayList<String>();
        for (StringTokenizer stringTokenizer = new StringTokenizer(input, delim); stringTokenizer.hasMoreTokens(); a1.add(stringTokenizer.nextToken())) {
        }
        String result[] = new String[a1.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = a1.get(i);
        }
        return result;
    }
    
    /**
     * 分隔符字符串成数组
     * 
     * @param input
     *            输入字符串
     * @param delim
     *            分隔符
     * @return 分隔后数组
     */
    public static String[] split(String input, String delim) {
        return org.apache.commons.lang.StringUtils.split(input, delim);
    }
    
    /**
     * 字符串分割并转成List<String>集合
     * 
     * @param input
     *            输入字符串
     * @param delim
     *            分隔符
     * @return List<String>
     */
    public static List<String> splitList(String input, String delim) {
        if (isEmpty(input)) {
            return null;
        }
        ArrayList<String> a1 = new ArrayList<String>();
        for (StringTokenizer stringTokenizer = new StringTokenizer(input, delim); stringTokenizer.hasMoreTokens(); a1.add(stringTokenizer.nextToken())) {
        }
        return a1;
    }
    
    /**
     * 数组转成字符串
     * 
     * @param String
     *            [] strs
     * @return String
     */
    public static String joinArray(String[] strs) {
        if (strs == null)
            return null;
        if (strs.length == 0)
            return "";
        String ss = "";
        for (String s : strs) {
            ss += "," + s;
        }
        return ss.substring(1);
    }
    
    /**
     * 数组转成字符串
     * 
     * @param String
     *            [] args
     * @return String
     */
    public static String joinArray(String[] args, String split_char) {
        if (args == null || args.length == 0) {
            return "";
        }
        return org.apache.commons.lang.StringUtils.join(args, split_char);
    }
    
    public static List<String> stringToList(String args) {
        String[] strs = args.split(",");
        List<String> list = new ArrayList<String>();
        for (String str : strs) {
            list.add(str);
        }
        return list;
    }
    
    public static String stringListToString(List<String> args) {
        String name = "";
        for (String str : args) {
            name += "," + str;
        }
        if (!StringUtil.isEmpty(name)) {
            name = name.substring(1);
        }
        return name;
    }
    
    /**
     * 字符串是否存在字符串数组中
     * 
     * @param stringArray
     * @param str
     * @return
     */
    public static boolean isInStringArray(String[] stringArray, String str) {
        if (stringArray == null || stringArray.length == 0) {
            return false;
        }
        for (String s : stringArray) {
            if (s.equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 全角逗号，转成半角逗号
     * 
     * @param param
     * @return
     */
    public String adjustComma(String param) {
        if (param == null)
            return "";
        String tmpStr = param.replaceAll("，", ",");
        String[] ss = tmpStr.split(",");
        String str = "";
        for (String s : ss) {
            if (!StringUtil.isEmpty(s.trim())) {
                str += "," + s.trim();
            }
        }
        return str.length() > 0 ? str.substring(1) : "";
    }
    
    public static void main(String[] args) {
        
    }
    
    /**
     * 得到一个二进制流的BASE64码
     * 
     * @param data
     * @return
     */
    /*public static String getImageStr(byte[] data) {
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }*/
    
    public static String replaceAllChar(String str, String colType) {
        if (StringUtil.isEmpty(str)) {
            return "";
        }
        if ("clob".equalsIgnoreCase(colType)) {
            return str.replaceAll("<p>", "").replaceAll("</p>", "").replaceAll("<P>", "").replaceAll("</P>", "").replaceAll("&", "＆").replaceAll("<", "＜");
        } else if ("varchar".equalsIgnoreCase(colType)) {
            return str.replaceAll("&", "＆").replaceAll("<", "＜");
        } else if (StringUtil.isEmpty(colType)) {
            return str.replaceAll("&", "＆").replaceAll("<", "＜");
        }
        return str;
    }
    
    public static String replaceAllChar(Object o, String colType) {
        if (o == null) {
            return "";
        }
        String str = o.toString();
        return replaceAllChar(str, colType);
    }
    
    public static String encodeDownloadFileName(String fileName, HttpServletRequest request) throws UnsupportedEncodingException {
        String agent = request.getHeader("User-Agent");
        
        if (agent != null) {
            if (agent.toUpperCase().indexOf("MSIE") != -1) {
                return URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
            }
        }
        
        return new String(fileName.getBytes("UTF-8"), "ISO8859-1");
    }
}

package com.rogrand.core.util;

import java.util.Random;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：根据输入的汉字得到对应的拼音首字母
 * 调用方法：	GB2Alpha alpha = new GB2Alpha();
 *              alpha.String2Alpha("中华人民共和国")
 * 结果：             zhrmghg
 */
public class GB2Alpha {

    /*
      * 字母Z使用了两个标签，这里有２７个值
      * i, u, v都不做声母, 跟随前面的字母
      */
    private char[] chartable = {
            '啊', '芭', '擦', '搭', '蛾', '发', '噶', '哈', '哈', '击',
            '喀', '垃', '妈', '拿', '哦', '啪', '期', '然', '撒', '塌',
            '塌', '塌', '挖', '昔', '压', '匝', '座'};

    private char[] alphatable = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z'};

    private int[] table = new int[27];

    public GB2Alpha() {
        for (int i = 0; i < 27; ++i) {
            table[i] = getBG2312Unicode(chartable[i]);
        }
    }

    private boolean match(int i, int gb) {
        if (gb < table[i]) {
            return false;
        }

        int j = i + 1;

        // 字母Z使用了两个标签
        while (j < 26 && (table[j] == table[i])) {
            ++j;
        }

        if (j == 26) {
            return gb <= table[j];
        } else {
            return gb < table[j];
        }
    }

    /**
     * 获取汉字的unicode编码
     *
     * @param ch 汉字
     * @return 返回汉字的unicode编码
     */
    private int getBG2312Unicode(char ch) {
        String str = String.valueOf(ch);
        try {
            byte[] bytes = str.getBytes("GB2312");
            if (bytes.length < 2) {
                return 0;
            }
            return (bytes[0] << 8 & 0xff00) + (bytes[1] & 0xff);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 输入汉字，
     *
     * @param ch 字符
     * @return 如果是汉字，返回他的声母；如果是英文字母返回对应的大写字母；其他字符返回'0'。
     */
    public char char2Alpha(char ch) {
        // 如果是英文字母返回对应的大写字母
        if (ch >= 'a' && ch <= 'z') {
            return (char) (ch - 'a' + 'A');
        }
        // 如果是英文大写字母返回大写字母
        if (ch >= 'A' && ch <= 'Z') {
            return ch;
        }

        int gb = getBG2312Unicode(ch);
        if (gb < table[0]) {
            return '0';
        }

        int i;
        for (i = 0; i < 26; ++i) {
            if (match(i, gb)) {
                break;
            }
        }

        if (i >= 26) {
            return '0';
        } else {
            return alphatable[i];
        }
    }

    /**
     * 根据一个包含汉字的字符串返回一个汉字拼音首字母的字符串
     *
     * @param str 包含汉字的字符串
     * @return 汉字拼音首字母字符串
     */
    public String String2Alpha(String str) {
        String result = "";
        try {
            for (int i = 0; i < str.length(); i++) {
                result += char2Alpha(str.charAt(i));
            }
        } catch (Exception e) {
            result = "";
        }

        return result;
    }

    /**
     * 随机产生指定数量的中文简体汉字字符串
     *
     * @param word_num 中文简体汉字个数
     * @return 中文简体汉字字符串
     */
    public static String randomChinese(int word_num) {
        String chinese = "";
        Random ran = new Random();
        // int delta = 0x9fa5 - 0x4e00 + 1;
        if (word_num < 0) {
            word_num = 0;
        }
        for (int i = 0; i < word_num; i++) {
            // char word = (char)(0x4e00 + ran.nextInt(delta));
            char word = (char) (0x4f20 + ran.nextInt(200));
            chinese = chinese + word;
        }

        return chinese;
    }

//	public static void main(String[] args) {
//		GB2Alpha alpha = new GB2Alpha();
//		System.out.println(alpha.String2Alpha("测试：中华人民共和国！"));
//		System.out.println(alpha.char2Alpha('国'));
//		System.out.println(alpha.char2Alpha('a'));
//		System.out.println(alpha.char2Alpha('國'));
//	}
}
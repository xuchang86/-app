package com.rogrand.core.util;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：SQL操作工具类
 */
public class SQLUtil {
	
	/**
     * 防止sql注入
     *
     * @param value 参数值
     * @return
     */
    public static String escapeSql(String value) {
        return StringEscapeUtils.escapeSql(value);
    }
}

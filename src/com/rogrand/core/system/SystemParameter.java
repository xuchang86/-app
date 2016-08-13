package com.rogrand.core.system;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：系统参数类，保存系统初始化参数（system.xml），和静态常量
 */
public class SystemParameter {

    protected static Map<String, String> parameter = new HashMap<String, String>();

    public static void set(String key, String value) {
        parameter.put(key, value);
    }

    public static String get(String key) {
        return parameter.get(key);
    }
    
    public static void remove(String key) {
        parameter.remove(key);
    }


}

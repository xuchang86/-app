package com.rogrand.core.util;

import com.rogrand.core.enums.TerminalType;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xuan.zhou@rogrand.com <br/>
 * 生成日期：2013-12-9 <br/>
 * 描述：〈字典值工具〉
 */
public class DictUtil {
    
    public static String getTerminalType(String terminalType) {
        if (terminalType != null) {
            if (terminalType.startsWith("0")) {
                return TerminalType.ANDROID.getDesc();
            } else if (terminalType.startsWith("1")) {
                return TerminalType.IOS.getDesc();
            } else if (terminalType.startsWith("9") || terminalType.startsWith("3")) { // 会有“9”的情况吗？待确定！！！
                return "PC-WEB";
            }
        }
        return null;
    }
    
    public static String getTerminalType(Long terminalType) {
        if (terminalType != null) {
            return getTerminalType(String.valueOf(terminalType));
        }
        return null;
    }
}

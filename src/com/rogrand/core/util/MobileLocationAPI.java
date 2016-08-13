package com.rogrand.core.util;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xuan.zhou@rogrand.com <br/>
 * 生成日期：2013-12-23 <br/>
 * 描述：〈号码归属地 API〉
 */
public class MobileLocationAPI {
    
    public static final String url = "http://api.showji.com/Locating/www.showji.c.om.aspx?m={0}&output=json";
    public static final String URL_IP138 = "http://www.ip138.com:8080/search.asp?mobile={0}&action=mobile";
    
    
    /**
     * 〈获取手机号码归属地〉 <br/>
     * 
     * @param mobile 手机号码
     * @return String 归属地，如：湖北 武汉 中国移动
     */
    @Deprecated
    public static String getLocation(String mobile) {
        String data = HttpClientUtil.sendGetRequest(MessageFormat.format(url, mobile), "UTF-8");
        
        StringBuffer sb = new StringBuffer();
        sb.append(getValue(data, "Province")).append(" ");
        sb.append(getValue(data, "City")).append(" ");
        sb.append(getValue(data, "Corp"));
        
        return sb.toString();
    }
    
    public static String getLocationFromIP138(String mobile) {
        String data = HttpClientUtil.sendGetRequest(MessageFormat.format(URL_IP138, mobile), "GBK");
        
        StringBuffer sb = new StringBuffer();
        sb.append(getValue(data, "<TD class=\"tdc2\" align=\"center\">(.*?)&nbsp;(.*?)</TD>", 1)).append(" ");
        sb.append(getValue(data, "<TD class=\"tdc2\" align=\"center\">(.*?)&nbsp;(.*?)</TD>", 2)).append(" ");
        sb.append(getValue(data, "<td align=\"center\" class='tdc2'>(.*?)</TD>", 1));
        
        return sb.toString();
    }
    
    /**
     * 〈根据参数名从接口返回的内容中获取对应的值〉 <br/>
     * 
     * @param data 接口返回的数据
     * @param name 参数名称
     * @return String 参数值
     */
    private static String getValue(String data, String name) {
        String regex = "\"" + name + "\":\"(.*?)\"";
        Pattern p = Pattern.compile(regex);
        Matcher matcher = p.matcher(data);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
    
    private static String getValue(String data, String regex, int group) {
        Pattern p = Pattern.compile(regex);
        Matcher matcher = p.matcher(data);
        if (matcher.find()) {
            return matcher.group(group);
        }
        return null;
    }
    
    public static void main(String[] args) {
        System.out.println(getLocation("15827010119"));
        System.out.println("IP138:" + getLocationFromIP138("15827010119"));
    }
}

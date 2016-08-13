package com.rogrand.core.util;

import java.text.DateFormatSymbols;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：日期格式化对象
 */
public class DateFormat extends SimpleDateFormat{

    private Log logger = LogFactory.getLog(getClass());

    public DateFormat() {
    }

    public DateFormat(String pattern) {
        super(pattern);
    }

    public DateFormat(String pattern, Locale locale) {
        super(pattern, locale);
    }

    public DateFormat(String pattern, DateFormatSymbols formatSymbols) {
        super(pattern, formatSymbols);
    }

    @Override
    public Date parse(String source)  {

        if(source==null||source.length()<8) return null;//字符串为null或长度小于8,返回null
        String dateTime = source.replaceAll("/","-");//替换日期间隔

        if(dateTime.indexOf('-')==-1){//判断是否是时间的long值
            try{
                long time = Long.parseLong(dateTime);
                return new Date(time);
            }
            catch (NumberFormatException e){
                logger.error(e.getMessage());
                return null;
            }
        }
        else{
            String pattern="yyyy-MM-dd";
            int count = dateTime.split(":").length;  //将字符串以:号分割成数组的长度
            if(count==2) pattern+=" HH:mm"; //有一个:
            else if(count==3) pattern+=" HH:mm:ss";//有两个:
            this.applyPattern(pattern);
            try{
                return super.parse(dateTime);
            }
            catch (ParseException e){
                logger.error(e.getMessage());
                return null;
            }
        }
    }

    @Override
    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition pos) {
        StringBuffer stringBuffer = super.format(date, toAppendTo, pos);
        String str = stringBuffer.toString();
        if(str.endsWith(" 00:00:00")||str.endsWith(" 00:00")||str.endsWith(" 00")){
            return new StringBuffer(str.substring(0,10));
        }
        else{
            return new StringBuffer(str);
        }
    }
}

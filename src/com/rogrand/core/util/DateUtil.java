package com.rogrand.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：日期工具类
 */
public class DateUtil {
    private static final Log logger = LogFactory.getLog(DateUtil.class);
    
    final static String[] datePatters = new String[]{"yyyy-MM-dd", "yyyyMMdd", "dd/MM/yyyy", "MM/dd/yyyy", "yyyy年MM月dd日"};
    final static String[] datetimePatters = new String[]{"yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss", "yyyyMMdd HHmmss", "dd/MM/yyyy HH:mm:ss", "MM/dd/yyyy HH:mm:ss", "yyyy年MM月dd日 HH时mm分ss秒"};

    public static Date parseDateTryCustomPatters(String date){
    	if (date == null || date.trim().length()<=0) return null;
    	date = date.trim();
    	
    	if(date.indexOf("-")!=-1){
    		if(date.split("-").length!=3){
    			return null;
    		}
    	}else if(date.indexOf("/")!=-1){
    		if(date.split("/").length!=3){
    			return null;
    		}
    	}
    	
    	SimpleDateFormat formatter = new SimpleDateFormat();
        Date d = null;
        for (String patter : datePatters) {
			try {
				formatter.applyPattern(patter);
				d = formatter.parse(date);
				break;
			}catch(ParseException pe){
			}catch (Exception e) {
				System.err.println("=================");
				System.err.println("p:" + patter + ",d:" + date);
				System.err.println("=================");
				e.printStackTrace();
			}
		}
        return d;
    }
    
    public static Date parseDatetimeTryCustomPatters(String datetime){
    	if (datetime == null || datetime.trim().length()<=0) return null;
    	datetime = datetime.trim();
    	
    	if(datetime.indexOf("-")!=-1){
    		if(datetime.split("-").length!=3){
    			return null;
    		}
    	}else if(datetime.indexOf("/")!=-1){
    		if(datetime.split("/").length!=3){
    			return null;
    		}
    	}
    	
    	SimpleDateFormat formatter = new SimpleDateFormat();
        Date d = null;
        for (String patter : datetimePatters) {
			try {
				formatter.applyPattern(patter);
				d = formatter.parse(datetime);
				break;
			}catch(ParseException pe){
			}catch (Exception e) {
				System.err.println("=================");
				System.err.println("p:" + patter + ",d:" + datetime);
				System.err.println("=================");
				e.printStackTrace();
			}
		}
        return d;
    }
    
    /**
     * 对以毫秒为单位的时间间隔进行转换,加上单位.
     * @param millisecond 以毫秒为单位的时间
     * @return 如millisecond值为63000,则返回"1分钟3秒"字符串
     * @author zrh
     */
	public static String time2CHNStr(long millisecond) {
    	
    	if( millisecond <= 0 ) {
    		return "0秒";
    	}
    	long t = millisecond/1000;

    	StringBuilder timeStr = new StringBuilder();
    	long d=0, h=0, m=0, s=0, ms=0;;//天,小时,分钟,秒
    	
    	
    	if( t >= (24*60*60) ) {
    		d = t/(24*60*60);
    	}
    	if( (t - d*24*60*60) >= (60*60) ) {
    		h = (t - d*24*60*60)/(60*60);
    	}
    	if( (t - d*24*60*60 - h*60*60) >= 60 ) {
    		m = (t - d*24*60*60 - h*60*60)/60;
    	}
    	
    	s = t - d*24*60*60 - h*60*60 - m*60;	//秒
    	
    	if( d > 0 ) {
    		timeStr.append(d).append("天");
    	}
    	if( d > 0 || h > 0 ) {
    		timeStr.append(h).append("小时");
    	}
    	if( d > 0 || h > 0 || m > 0 ) {
    		timeStr.append(m).append("分");
    	}
    	if( d > 0 || h > 0 || m > 0 || s > 0) {
    		timeStr.append(s).append("秒");
    	}

    	ms = millisecond%1000;
    	if( ms > 0 ) {
    		timeStr.append(ms).append("毫秒");
    	}
    	
    	return timeStr.toString();
    }
	
	public static String format(Date date){
		if( date == null ) return "";
		SimpleDateFormat formatter = new SimpleDateFormat();
		formatter.applyPattern("yyyy-MM-dd HH:mm:ss");
		return formatter.format(date);
	}
    
    /**
     * 取得两个日期段的日期间隔
     *
     * @param d1 日期1
     * @param d2 日期2
     * @return t2 与t1的间隔天数
     */
    public static int getBetweenDays1(Date d1, Date d2) {
        if (d1 == null || d2 == null) {
            return -1;
        }
        int betweenDays;
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        // 保证第二个时间一定大于第一个时间
        if (c1.after(c2)) {
            c2.setTime(d1);
            c1.setTime(d2);
        }
        int betweenYears = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
        betweenDays = c2.get(Calendar.DAY_OF_YEAR) - c1.get(Calendar.DAY_OF_YEAR);
        for (int i = 0; i < betweenYears
                ;
             i++) {
            c1.set(Calendar.YEAR, (c1.get(Calendar.YEAR) + 1));
            betweenDays += c1.getMaximum(Calendar.DAY_OF_YEAR);
        }
        return betweenDays;
    }

    public static Date parseDate(String date) {
        if (date == null) return null;
        DateFormat formatter = new DateFormat();
        formatter.setLenient(false);
        return formatter.parse(date);
    }

    public static Date parseDate(String date, String patter) {
        if (date == null) return null;
        SimpleDateFormat formatter = new SimpleDateFormat();
        formatter.setTimeZone(TimeZone.getDefault());
        formatter.applyPattern(patter);

        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String formatLocal(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH) + 1;
        int dd = calendar.get(Calendar.DATE);
        int ww = calendar.get(Calendar.DAY_OF_WEEK);
        int hh = calendar.get(Calendar.HOUR_OF_DAY);
        int mi = calendar.get(Calendar.MINUTE);
        int se = calendar.get(Calendar.SECOND);
        String s = "";
        s += Integer.toString(yy) + "年";
        s += Integer.toString(mm) + "月";
        s += Integer.toString(dd) + "日";
        s += " ";
        switch (ww) {
            case 1:
                s += "星期日";
                break;
            case 2:
                s += "星期一";
                break;
            case 3:
                s += "星期二";
                break;
            case 4:
                s += "星期三";
                break;
            case 5:
                s += "星期四";
                break;
            case 6:
                s += "星期五";
                break;
            case 7:
                s += "星期六";
                break;
        }
        s += " ";
        s += hh < 10 ? "0" + Integer.toString(hh) : Integer.toString(hh);
        s += ":";
        s += mi < 10 ? "0" + Integer.toString(mi) : Integer.toString(mi);
        s += ":";
        s += se < 10 ? "0" + Integer.toString(se) : Integer.toString(se);
        return s;
    }

    public static String formatLocal() {
        return formatLocal(new Date());
    }

    private static String adjust(String date) {
    	if((date.endsWith(" 00:00:00") || date.endsWith(" 00:00") || date.endsWith(" 00"))){
    		if(date.length()>10){
    			return date.substring(0, 10);
    		}
    	}
        return date;
    }

    public static String formatDate(Date date) {
    	SimpleDateFormat formatter = new SimpleDateFormat();
        formatter.setTimeZone(TimeZone.getDefault());
        formatter.applyPattern("yyyy-MM-dd HH:mm:ss");
        formatter.setLenient(false);
        return adjust(formatter.format(date));
    }

    public static String formatDate(Date date, String patter) {
    	SimpleDateFormat formatter = new SimpleDateFormat();
        formatter.setTimeZone(TimeZone.getDefault());
        formatter.applyPattern(patter);
        formatter.setLenient(false);
        return adjust(formatter.format(date));
    }

    public static String formatGMTDate(Date date) {
    	SimpleDateFormat formatter = new SimpleDateFormat();
        formatter.applyPattern("yyyy-MM-dd HH:mm:ss");
        formatter.setLenient(false);
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        return adjust(formatter.format(date));
    }

    public static String formatGMTDate(Date date, String patter) {
    	SimpleDateFormat formatter = new SimpleDateFormat();
        formatter.applyPattern(patter);
        formatter.setLenient(false);
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        return adjust(formatter.format(date));
    }

    public static String formatDate(String date) {
        Date d = parseDate(date);
        return d != null ? formatDate(d) : "";
    }

    public static String formatDate(String date, String patter) {
        Date d = parseDate(date);
        return d != null ? formatDate(d, patter) : "";
    }

    /**
     * 得到当前时间,不带时分秒毫秒
     *
     * @return Date
     */
    public static Date getDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 得到当前时间,不带时分秒毫秒
     *
     * @return String
     */
    public static Date getDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 将当前日期转换成指定格式的字符串
     *
     * @param pattern 日期格式
     * @return 返回格式化的日期字符串
     */
    public static String getNow(String pattern) {
        return formatDate(new Date(), pattern);
    }

    /**
     * 获得当前时间
     *
     * @return Date
     */
    public static Date getNow() {
        return new Date();
    }

    /**
     * 取得年份，格式"yyyy"
     *
     * @return 返回当前年份
     */
    public static int getYear() {
        Date now = new Date();
        return getYear(now);
    }

    /**
     * 取得日期的年份，格式"yyyy"
     *
     * @param date 日期
     * @return 日期的年份
     */
    public static int getYear(Date date) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        return cale.get(Calendar.YEAR);
    }

    /**
     * 取得月份
     *
     * @return 返回当前月份
     */
    public static int getMonth() {
        Date now = new Date();
        return getMonth(now);
    }

    /**
     * 取得日期的月份
     *
     * @param date 日期
     * @return 日期的月份
     */
    public static int getMonth(Date date) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        return cale.get(Calendar.MONTH) + 1;
    }

    /**
     * 取得日期所在季度
     *
     * @return
     */
    public static int getQuarter(Date date) {
        int month = getMonth(date);
        if (month >= 1 && month <= 3) return 1;
        else if (month >= 4 && month <= 6) return 2;
        else if (month >= 7 && month <= 9) return 3;
        else return 4;
    }

    /**
     * 取得当前日期所在季度
     *
     * @return
     */
    public static int getQuarter() {
        return getQuarter(new Date());
    }

    /**
     * 取得今天的日期数
     *
     * @return 返回今天的日期数
     */
    public static int getDay() {
        Date now = new Date();
        return getDay(now);
    }

    /**
     * 取得日期的天数
     *
     * @param date 日期
     * @return 日期的天数
     */
    public static int getDay(Date date) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        return cale.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获得与某日期相隔几年的日期
     *
     * @param date     指定的日期
     * @param addCount 相隔的年数,可以是负数，表示日期前几年
     * @return 返回的日期
     */
    public static Date addYear(Date date, int addCount) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.add(Calendar.YEAR, addCount);
        return cale.getTime();
    }
    
    /**
     * 获得与某日期相隔几月的日期
     *
     * @param date     指定的日期
     * @param addCount 相隔的月数,可以是负数，表示日期前几月
     * @return 返回的日期
     */
    public static Date addMonth(Date date, int addCount) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.add(Calendar.MONTH, addCount);
        return cale.getTime();
    }
    
    /**
     * 获得与某日期相隔几天的日期
     *
     * @param date     指定的日期
     * @param addCount 相隔的天数,可以是负数，表示日期前几天
     * @return 返回的日期
     */
    public static Date addDay(Date date, int addCount) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.add(Calendar.DAY_OF_MONTH, addCount);

        return cale.getTime();
    }
    
    public static Date addHour(Date date, int addCount) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.add(Calendar.HOUR_OF_DAY, addCount);
        return cale.getTime();
    }
    
    public static Date addMinutes(Date date, int minutes) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.add(Calendar.MINUTE, minutes);
        return cale.getTime();
    }

    /**
     * 得到某天是周几
     *
     * @param strDay 2010-01-02
     * @return 周几
     */
    public static int getWeekDay(String strDay) {

        Date day = parseDate(strDay);
        Calendar cale = Calendar.getInstance();
        cale.setTime(day);
        return cale.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 得到某天是周几
     *
     * @param date 日期类型
     * @return 周几
     */
    public static int getWeekDay(Date date) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        return cale.get(Calendar.DAY_OF_WEEK) - 1;
    }
    
    /**
     * 获取当前日期是星期几<br>
     * 
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 取得两个日期段的日期间隔
     *
     * @param t1 时间1
     * @param t2 时间2
     * @return t2 与t1的间隔天数
     */
    public static int getBetweenDays(String t1, String t2) {

        Date d1 = parseDate(t1);
        Date d2 = parseDate(t2);
        return getBetweenDays(d1, d2);
    }

    /**
     * 取得两个日期段的日期间隔
     *
     * @param d1 日期1
     * @param d2 日期2
     * @return t2 与t1的间隔天数
     */
    private static int getBetweenDays(Date d1, Date d2) {
        if (d1 == null || d2 == null) {
            return -1;
        }
        int betweenDays;
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        // 保证第二个时间一定大于第一个时间
        if (c1.after(c2)) {
            c2.setTime(d1);
            c1.setTime(d2);
        }
        int betweenYears = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
        betweenDays = c2.get(Calendar.DAY_OF_YEAR) - c1.get(Calendar.DAY_OF_YEAR);
        for (int i = 0; i < betweenYears
                ;
             i++) {
            c1.set(Calendar.YEAR, (c1.get(Calendar.YEAR) + 1));
            betweenDays += c1.getMaximum(Calendar.DAY_OF_YEAR);
        }
        return betweenDays;
    }
    
    public static int getBetweenDay(Date d1, Date d2) {
        if (d1 == null || d2 == null) {
            return -1;
        }
        int betweenDays;
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        
        int betweenYears = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
        betweenDays = c2.get(Calendar.DAY_OF_YEAR) - c1.get(Calendar.DAY_OF_YEAR);
        for (int i = 0; i < betweenYears
                ;
             i++) {
            c1.set(Calendar.YEAR, (c1.get(Calendar.YEAR) + 1));
            betweenDays += c1.getMaximum(Calendar.DAY_OF_YEAR);
        }
        return betweenDays;
    }


    /**
     * 转换字符串为日期，格式"yyyy-MM-dd"
     *
     * @param date 日期字符串
     * @return 返回格式化的日期
     * @throws ParseException 分析时意外地出现了错误异常
     */
    public static Date strToDate(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setLenient(false);
        return formatter.parse(date);
    }

    /**
     * 转换字符串为日期，格式"yyyy-MM-dd HH:mm:ss"
     *
     * @param date 日期字符串
     * @return 返回格式化的日期
     * @throws ParseException 分析时意外地出现了错误异常
     */
    public static Date strToTime(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formatter.setLenient(false);
        return formatter.parse(date);
    }

    /**
     * 转换日期为字符串，格式"yyyy-MM-dd"
     *
     * @param date 日期
     * @return 返回格式化的日期字符串
     */
    public static String dateToStr(Date date) {
        if (date == null) return null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }

    /**
     * 返回 yyyy年MM月dd日
     *
     * @param date 日期对象
     * @return 返回格式化的日期字符串
     */
    public static String dateToStrCN(Date date) {
        if (date == null) return null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年M月d日");
        return formatter.format(date);
    }

    /**
     * 转换日期为字符串，格式"yyyy-MM-dd HH:mm:ss"
     *
     * @param date 日期
     * @return 返回格式化的日期字符串
     */
    public static String timeToStr(Date date) {
        if (date == null) return null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }
    
    /**
     * 转换日期为字符串，格式"yyyy-MM-dd HH:mm:ss"
     *
     * @param millisecond 日期毫秒
     * @return 返回格式化的日期字符串
     */
    public static String timeToStr(long millisecond) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d =  new Date(millisecond);
        return formatter.format(d);
    }

    public static Date getWeekDate(Date indate, int weeknum) {
        Calendar c = Calendar.getInstance();
        c.setTime(indate);
        c.set(Calendar.DAY_OF_WEEK, weeknum);
        return c.getTime();
    }

    /**
     * 获得指定日期所在月份的第一天
     *
     * @param date 日期对象
     * @return Date
     * @throws ParseException 分析时意外地出现了错误异常
     */
    public static Date getFirstDate(Date date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-01");
        String firstdate = formatter.format(date);
        return strToDate(firstdate);
    }

    /**
     * 获得今天所在月份的第一天
     *
     * @return Date
     * @throws ParseException 分析时意外地出现了错误异常
     */
    public static Date getFirstDate() throws ParseException {
        Date now = new Date();
        return getFirstDate(now);
    }

    /**
     * 获得指定日期所在月份的最后一天
     *
     * @param date 日期对象
     * @return Date
     * @throws ParseException 分析时意外地出现了错误异常
     */
    public static Date getLastDate(Date date) throws ParseException {
        Date nextMonthfirstDate = addMonth(getFirstDate(date), 1);
        return addDay(nextMonthfirstDate, -1);
    }

    /**
     * 得到每年第几周的开始日期,默认星期日是每周的第一天
     *
     * @param theyear
     * @param weekIndex
     * @param addDays
     * @return
     */
    public static String getFirstYearOfWeek(int theyear, int weekIndex, int addDays) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, theyear);
        c.set(Calendar.WEEK_OF_YEAR, weekIndex);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + addDays); //设置星期几为每周第一天
        String strdate = sdf.format(c.getTime());
        return strdate;
    }


    /**
     * 得到每年第几周的结束日期
     *
     * @param theyear
     * @param weekIndex
     * @param addDays
     * @return
     */
    public static String getLastYearOfWeek(int theyear, int weekIndex, int addDays) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, theyear);
        c.set(Calendar.WEEK_OF_YEAR, weekIndex);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + addDays);
        String strdate = sdf.format(c.getTime());
        return strdate;
    }

    /**
     * 得到当前日期是多少周
     *
     * @return int
     * @throws ParseException 分析时意外地出现了错误异常
     */
    public static int getCurrentWeeks() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 得到某一年周的总数
     *
     * @param year
     * @return
     */
    public static int getMaxWeekNumOfYear(int year) {
        Calendar c = Calendar.getInstance();
        c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
        c.setFirstDayOfWeek(Calendar.SUNDAY);
        c.setMinimalDaysInFirstWeek(7);
        c.setTime(c.getTime());
        return c.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获得今天所在月份的最后一天
     *
     * @return Date
     * @throws ParseException 分析时意外地出现了错误异常
     */
    public static Date getLastDate() throws ParseException {
        Date now = new Date();
        return getLastDate(now);
    }

    /**
     * 通过两个日期，获得间隔月份
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int getMonthBetweenDate(String date1, String date2) {
        Map<Integer, Integer> map = new HashMap();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = null;
        try {
            d1 = sd.parse(date1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Date d2 = null;
        try {
            d2 = sd.parse(date2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int months = 0;// 相差月份
        int y1 = d1.getYear();
        int y2 = d2.getYear();
        int dm1 = d2.getMonth();// 起始日期月份
        int dm2 = d2.getMonth();// 结束日期月份
        int dd1 = d1.getDate(); // 起始日期天
        int dd2 = d2.getDate(); // 结束日期天
        if (d1.getTime() < d2.getTime()) {
            months = d2.getMonth() - d1.getMonth() + (y2 - y1) * 12;
            if (dd2 < dd1) {
                months = months - 1;
            }
        }
        return months;
    }

    /**
     * 得到当前时间,不带时分秒毫秒
     *
     * @return String
     */
    public static String getDateString() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyyMMdd");
        String s = dataFormat.format(calendar.getTime());
        return s;
    }

    /**
     * @param mss 要转换的毫秒数
     * @return 该毫秒数转换为 * days * hours * minutes * seconds 后的格式
     */
    public static String formatDuring(long mss) {
        if (mss <= 0) return "小于1毫秒";
        if (mss < 1000) return mss + "毫秒";
        String result = "";
        long days = mss / (1000 * 60 * 60 * 24);
        long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
        long seconds = (mss % (1000 * 60)) / 1000;
        if (days > 0) {
            result += days + "天";
        }
        if (hours > 0) {
            result += hours + "小时";
        }
        if (minutes > 0) {
            result += minutes + "分";
        }
        if (seconds >= 0) {
            result += seconds + "秒";
        }
        return result;
    }

    /**
     * @param begin 时间段的开始
     * @param end   时间段的结束
     * @return 输入的两个Date类型数据之间的时间间格用* days * hours * minutes * seconds的格式展示
     */
    public static String formatDuring(Date begin, Date end) {
        if (begin != null && end != null) {
            return formatDuring(end.getTime() - begin.getTime());
        } else {
            return "";
        }
    }


    public static Date getRandomDate(Date start, Date end) {
        Random random = new Random();
        long s = start.getTime();
        long e = end.getTime();
        long rtn = s + (long) (random.nextDouble() * (e - s));
        return new Date(rtn);
    }

    public static Date getRandomDate(String start, String end) {
        return getRandomDate(parseDate(start), parseDate(end));
    }

    public static void main(String[] args) {
        System.out.println(getFirstYearOfWeek(2012, 50, 1));
        System.out.println(getLastYearOfWeek(2012, 50, 5));
//        System.out.println(getDate());
        Date date = new Date();
        System.out.println(getCurrentWeeks());
        
        
        Date t1=new Date(115,3,10);
        System.out.println(t1);
        System.out.println(new Date());
        System.out.println(getBetweenDay(t1, new Date()));

    }
}

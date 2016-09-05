/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.base.util;

import java.text.DateFormatSymbols;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 将String日期格式转换为日期格式
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年8月28日 许畅 新建
 */
public class DateFormat extends SimpleDateFormat {

	/** 默认序列 */
	private static final long serialVersionUID = 1L;

	/** LOGGER日志 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(DateFormat.class);

	/**
	 * 无参构造方法
	 */
	public DateFormat() {
		super();
	}

	/**
	 * 构造方法
	 * 
	 * @param pattern
	 */
	public DateFormat(String pattern) {
		super(pattern);
	}

	/**
	 * 构造方法
	 * 
	 * @param pattern
	 * @param locale
	 */
	public DateFormat(String pattern, Locale locale) {
		super(pattern, locale);
	}

	/**
	 * 构造方法
	 * 
	 * @param pattern
	 * @param formatSymbols
	 */
	public DateFormat(String pattern, DateFormatSymbols formatSymbols) {
		super(pattern, formatSymbols);
	}

	/**
	 * 重写String字符串日期格式转日期类型方法
	 *
	 * @param source
	 * @return
	 *
	 * @see java.text.DateFormat#parse(java.lang.String)
	 */
	@Override
	public Date parse(String source) {
		if (source == null || source.length() < 8)
			return null;// 字符串为null或长度小于8,返回null
		String dateTime = source.replaceAll("/", "-");// 替换日期间隔

		if (dateTime.indexOf('-') == -1) {// 判断是否是时间的long值
			try {
				long time = Long.parseLong(dateTime);
				return new Date(time);
			} catch (NumberFormatException e) {
				LOGGER.error(e.getMessage());
			}
		} else {
			String pattern = "yyyy-MM-dd";
			int count = dateTime.split(":").length; // 将字符串以:号分割成数组的长度
			if (count == 2)
				pattern += " HH:mm"; // 有一个:
			else if (count == 3)
				pattern += " HH:mm:ss";// 有两个:
			this.applyPattern(pattern);
			try {
				return super.parse(dateTime);
			} catch (ParseException e) {
				LOGGER.error(e.getMessage());
			}
		}
		return null;
	}

	/**
	 * 重写format方法
	 *
	 * @param date
	 * @param toAppendTo
	 * @param pos
	 * @return
	 *
	 * @see java.text.SimpleDateFormat#format(java.util.Date,
	 *      java.lang.StringBuffer, java.text.FieldPosition)
	 */
	@Override
	public StringBuffer format(Date date, StringBuffer toAppendTo,
			FieldPosition pos) {
		StringBuffer stringBuffer = super.format(date, toAppendTo, pos);
		String str = stringBuffer.toString();
		if (str.endsWith(" 00:00:00") || str.endsWith(" 00:00")
				|| str.endsWith(" 00")) {
			return new StringBuffer(str.substring(0, 10));
		} else {
			return new StringBuffer(str);
		}
	}
}

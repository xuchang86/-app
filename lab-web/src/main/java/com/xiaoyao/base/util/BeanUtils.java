/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.base.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JavaBean工具类
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年8月20日 许畅 新建
 */
public final class BeanUtils {

	/**
	 * 构造方法
	 */
	private BeanUtils() {

	}

	/** LOGGER日志 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(BeanUtils.class);

	/** 日期格式化 */
	private static final DateFormat _date_format = new DateFormat();

	/**
	 * 将请求参数map封装成Bean对象
	 *
	 * @param request
	 *            请求对象
	 * @param cls
	 *            类class
	 * @return Object
	 */
	public static <T> T mapConvertToBean(Class<T> cls,
			HttpServletRequest request) {
		return mapConvertToBean(cls, request.getParameterMap());
	}

	/**
	 * map转换为Bean对象
	 * 
	 * @param cls
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static <T> T mapConvertToBean(Class<T> cls, Map map) {
		try {
			T obj = cls.newInstance();
			if (obj instanceof Map) {
				org.apache.commons.beanutils.BeanUtils.populate(obj, map);
			} else {
				setBeanProperty(obj, map);
			}
			return obj;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * java bean转换为map
	 * 
	 * @param obj
	 *            bean对象
	 * @return map<String, Object>
	 */
	public static Map<String, Object> beanConverToMap(Object obj) {
		if (obj == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo
					.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);
					map.put(key, value);
				}

			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return map;
	}

	/**
	 * 通过java.beans.Introspector将map转为bean
	 * 
	 * @param map
	 * @param cls
	 * @return
	 */
	public static <T> T mapConvert2ToBean(Class<T> cls, Map<String, Object> map) {
		T obj = null;
		try {
			obj = cls.newInstance();
			BeanInfo beanInfo = Introspector.getBeanInfo(cls);
			PropertyDescriptor[] propertyDescriptors = beanInfo
					.getPropertyDescriptors();

			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				if (map.containsKey(key)) {
					Object value = map.get(key);
					Method setter = property.getWriteMethod();
					// 对应的值类型
					Class<?> type = cls.getDeclaredField(key).getType();
					if (value instanceof String[]) {
						String str = ((String[]) value)[0];
						setter.invoke(obj, stringConverToObj(type, str));
					} else if (value instanceof String) {
						String str = String.valueOf(value);
						setter.invoke(obj, stringConverToObj(type, str));
					} else {
						setter.invoke(obj, value);
					}
				}
			}
		} catch (InstantiationException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (IntrospectionException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (IllegalArgumentException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (NoSuchFieldException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (SecurityException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return obj;
	}

	@SuppressWarnings("unchecked")
	public static <T> T mapConvert2ToBean(Class<T> cls,
			HttpServletRequest request) {
		return (T) mapConvert2ToBean(cls, request.getParameterMap());
	}

	/**
	 * 将String转换为相应的对象类型
	 * 
	 * @param type
	 *            Class类型 包括基本类型,BigDecimal,Date等类型
	 * @param value
	 *            String字符串类型
	 * @return Object
	 */
	protected static Object stringConverToObj(Class<?> type, String value) {
		if (type == Date.class || type == Timestamp.class) {
			return toDate(value);
		} else if (type == BigDecimal.class) {
			return toBigDecimal(value);
		} else if (type == BigInteger.class) {
			return toBigInteger(value);
		} else if (type == Boolean.class) {
			return toBoolean(value);
		} else if (type == Double.class) {
			return toDouble(value);
		} else if (type == Float.class) {
			return toFloat(value);
		} else if (type == Integer.class) {
			return toInteger(value);
		} else if (type == Long.class) {
			return toLong(value);
		} else if (type == Short.class) {
			return toShort(value);
		} else if (type == Byte.class) {
			return toByte(value);
		} else {
			return value;
		}
	}

	/**
	 * 将请求参数的parameterMap设置到bean对象的属性中
	 *
	 * @param obj
	 *            bean对象
	 * @param map
	 *            request.getParameterMap()
	 */
	@SuppressWarnings("rawtypes")
	private static void setBeanProperty(Object obj, Map map) {
		if (map == null)
			return;
		for (Object o : map.entrySet()) {
			Map.Entry entry = (Map.Entry) o;
			String key = (String) entry.getKey();

			String[] values = null;
			String value = null;
			Object object = entry.getValue();
			if (object != null) {
				if (object.getClass() == String[].class) {
					values = (String[]) object;
					value = values[0];
				} else if (object.getClass() == String.class) {
					values = new String[] { (String) object };
					value = (String) object;
				}
			}
			if (!PropertyUtils.isWriteable(obj, key))
				continue;
			try {
				Class cls = PropertyUtils.getPropertyType(obj, key);
				if (cls == null)
					continue;
				if (cls == String.class)
					PropertyUtils.setProperty(obj, key, value);
				else if (cls == String[].class)
					PropertyUtils.setProperty(obj, key, values);
				else if (cls == BigDecimal.class)
					PropertyUtils.setProperty(obj, key, toBigDecimal(value));
				else if (cls == BigDecimal[].class)
					PropertyUtils.setProperty(obj, key, toBigDecimal(values));
				else if (cls == BigInteger.class)
					PropertyUtils.setProperty(obj, key, toBigInteger(value));
				else if (cls == BigInteger[].class)
					PropertyUtils.setProperty(obj, key, toBigInteger(values));
				else if (cls == Boolean.class)
					PropertyUtils.setProperty(obj, key, toBoolean(value));
				else if (cls == Boolean[].class)
					PropertyUtils.setProperty(obj, key, toBoolean(values));
				else if (cls == Double.class)
					PropertyUtils.setProperty(obj, key, toDouble(value));
				else if (cls == Double[].class)
					PropertyUtils.setProperty(obj, key, toDouble(values));
				else if (cls == Float.class)
					PropertyUtils.setProperty(obj, key, toFloat(value));
				else if (cls == Float[].class)
					PropertyUtils.setProperty(obj, key, toFloat(values));
				else if (cls == Integer.class)
					PropertyUtils.setProperty(obj, key, toInteger(value));
				else if (cls == Integer[].class)
					PropertyUtils.setProperty(obj, key, toInteger(values));
				else if (cls == Long.class)
					PropertyUtils.setProperty(obj, key, toLong(value));
				else if (cls == Long[].class)
					PropertyUtils.setProperty(obj, key, toLong(values));
				else if (cls == Short.class)
					PropertyUtils.setProperty(obj, key, toShort(value));
				else if (cls == Short[].class)
					PropertyUtils.setProperty(obj, key, toShort(values));
				else if (cls == Byte.class)
					PropertyUtils.setProperty(obj, key, toByte(value));
				else if (cls == Byte[].class)
					PropertyUtils.setProperty(obj, key, toByte(values));
				else if (cls == Date.class)
					PropertyUtils.setProperty(obj, key, toDate(value));
				else if (cls == Date[].class)
					PropertyUtils.setProperty(obj, key, toDate(values));
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
	}

	/**
	 * 字符串转换 BigDecimal
	 *
	 * @param value
	 *            字符串
	 * @return BigDecimal
	 */
	private static BigDecimal toBigDecimal(String value) {
		if (value == null || value.equals("") || value.equals("null"))
			return null;
		try {
			return new BigDecimal(value);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 字符串数组转换 BigDecimal[]
	 *
	 * @param values
	 *            字符串数组
	 * @return BigDecimal[]
	 */
	private static BigDecimal[] toBigDecimal(String[] values) {
		BigDecimal[] result = new BigDecimal[values.length];
		for (int i = 0; i < values.length; i++)
			result[i] = toBigDecimal(values[i]);
		return result;
	}

	/**
	 * 字符串转换 BigInteger
	 *
	 * @param value
	 *            字符串
	 * @return BigInteger
	 */
	private static BigInteger toBigInteger(String value) {
		if (value == null || value.equals("") || value.equals("null"))
			return null;
		try {
			return new BigInteger(value);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 字符串数组转换 BigInteger[]
	 *
	 * @param values
	 *            字符串数组
	 * @return BigInteger[]
	 */
	private static BigInteger[] toBigInteger(String[] values) {
		BigInteger[] result = new BigInteger[values.length];
		for (int i = 0; i < values.length; i++)
			result[i] = toBigInteger(values[i]);
		return result;
	}

	/**
	 * 字符串转换 Boolean
	 *
	 * @param value
	 *            字符串
	 * @return Boolean
	 */
	private static Boolean toBoolean(String value) {
		if (value == null || value.equals("") || value.equals("null"))
			return null;
		try {
			return Boolean.parseBoolean(value);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 字符串数组转换 Boolean[]
	 *
	 * @param values
	 *            字符串数组
	 * @return Boolean[]
	 */
	private static Boolean[] toBoolean(String[] values) {
		Boolean[] result = new Boolean[values.length];
		for (int i = 0; i < values.length; i++)
			result[i] = toBoolean(values[i]);
		return result;
	}

	/**
	 * 字符串转换 Double
	 *
	 * @param value
	 *            字符串
	 * @return Double
	 */
	private static Double toDouble(String value) {
		if (value == null || value.equals("") || value.equals("null"))
			return null;
		try {
			return Double.parseDouble(value);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 字符串数组转换 Double[]
	 *
	 * @param values
	 *            字符串数组
	 * @return Double[]
	 */
	private static Double[] toDouble(String[] values) {
		Double[] result = new Double[values.length];
		for (int i = 0; i < values.length; i++)
			result[i] = toDouble(values[i]);
		return result;
	}

	/**
	 * 字符串转换 Float
	 *
	 * @param value
	 *            字符串
	 * @return Float
	 */
	private static Float toFloat(String value) {
		if (value == null || value.equals("") || value.equals("null"))
			return null;
		try {
			return Float.parseFloat(value);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 字符串数组转换 Float[]
	 *
	 * @param values
	 *            字符串数组
	 * @return Float[]
	 */
	private static Float[] toFloat(String[] values) {
		Float[] result = new Float[values.length];
		for (int i = 0; i < values.length; i++)
			result[i] = toFloat(values[i]);
		return result;
	}

	/**
	 * 字符串转换 Integer
	 *
	 * @param value
	 *            字符串
	 * @return Integer
	 */
	private static Integer toInteger(String value) {
		if (value == null || value.equals("") || value.equals("null"))
			return null;
		try {
			return Integer.parseInt(value);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 字符串数组转换 Integer[]
	 *
	 * @param values
	 *            字符串
	 * @return Integer[]
	 */
	private static Integer[] toInteger(String[] values) {
		Integer[] result = new Integer[values.length];
		for (int i = 0; i < values.length; i++)
			result[i] = toInteger(values[i]);
		return result;
	}

	/**
	 * 字符串转换 Long
	 *
	 * @param value
	 *            字符串
	 * @return Long
	 */
	private static Long toLong(String value) {
		if (value == null || value.equals("") || value.equals("null"))
			return null;
		try {
			return Long.parseLong(value);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 字符串数组转换 Long[]
	 *
	 * @param values
	 *            字符串
	 * @return Long[]
	 */
	private static Long[] toLong(String[] values) {
		Long[] result = new Long[values.length];
		for (int i = 0; i < values.length; i++)
			result[i] = toLong(values[i]);
		return result;
	}

	/**
	 * 字符串转换 Short
	 *
	 * @param value
	 *            字符串
	 * @return Short
	 */
	private static Short toShort(String value) {
		if (value == null || value.equals("") || value.equals("null"))
			return null;
		try {
			return Short.parseShort(value);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 字符串数组转换 Short[]
	 *
	 * @param values
	 *            字符串
	 * @return Short[]
	 */
	private static Short[] toShort(String[] values) {
		Short[] result = new Short[values.length];
		for (int i = 0; i < values.length; i++)
			result[i] = toShort(values[i]);
		return result;
	}

	private static Byte toByte(String value) {
		if (value == null || value.equals("") || value.equals("null"))
			return null;
		try {
			return Byte.parseByte(value);
		} catch (Exception e) {
			return null;
		}
	}

	private static Byte[] toByte(String[] values) {
		Byte[] result = new Byte[values.length];
		for (int i = 0; i < values.length; i++)
			result[i] = toByte(values[i]);
		return result;
	}

	/**
	 * 字符串转换 Date
	 *
	 * @param value
	 *            字符串
	 * @return Date
	 */
	public static Date toDate(String value) {
		if (value == null || value.equals("") || value.equals("null"))
			return null;
		try {
			_date_format.setLenient(false);
			return _date_format.parse(value);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 字符串数组转换 Date[]
	 *
	 * @param values
	 *            字符串
	 * @return Date[]
	 */
	public static Date[] toDate(String[] values) {
		Date[] result = new Date[values.length];
		for (int i = 0; i < values.length; i++)
			result[i] = toDate(values[i]);
		return result;
	}

}

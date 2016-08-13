package com.rogrand.core.util;


import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.converters.DateTimeConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.JavaType;
import org.springframework.web.util.UrlPathHelper;

import com.rogrand.core.domain.PageParam;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：参数封装的工具类
 */
public class BeanUtil {

    private static final Log logger = LogFactory.getLog(BeanUtil.class);
    private static UrlPathHelper urlPathHelper = new UrlPathHelper();
    private static DateFormat dateFormat = new DateFormat();


    private static String getExceptionMessage(Exception e) {
        String msg = e.getMessage();
        if (msg == null) msg = e.getClass().getName();
        return msg;
    }

    static {
        DateConverter dc = new DateConverter();
        String[] datePattern = {"yyyy-MM-dd", "yyyy-MM-dd HH:mm", "yyyy-MM-dd HH:mm:ss"};
        dc.setPatterns(datePattern);
        ConvertUtils.register(dc, Date.class);
    }

    /**
     * 将对象转换成Map
     *
     * @param bean
     * @return
     */
    public static Map toMap(Object bean) {
        return bean != null ? new BeanMap(bean) : null;
    }

    /**
     * 创建分页查询条件(从session取出保存的条件)
     *
     * @param request 请求对象
     *                //     * @param receive 重新得到session中保存的查询条件
     * @return PageParam
     */
    public static PageParam wrapPageBean(HttpServletRequest request) throws Exception {
        try {
            PageParam param = new PageParam();
            String url = urlPathHelper.getPathWithinApplication(request);
            HttpSession session = request.getSession(true);
            String refresh = request.getParameter("refresh");//翻页刷新参数 1刷新所有，2刷新记录数
            boolean isRefresh = (refresh != null) && refresh.equals("1");
            if (!isRefresh) { //从会话中获得保存的查询条件(不刷新条件)
                Object saveParam = session.getAttribute(url);
                if (saveParam != null && saveParam instanceof PageParam) {
                    param = (PageParam) saveParam;
                }
            }
            BeanUtils.populate(param, request.getParameterMap());
            session.setAttribute(url, param);
            return param;
        } catch (Exception e) {
            logger.error(getExceptionMessage(e));
            return null;
        }
    }



    /**
     * 将请求参数map封装成Bean对象
     *
     * @param request 请求对象
     * @param cls     类class
     * @return Object
     */
    public static <T> T wrapBean(Class<T> cls, HttpServletRequest request) {
        try {
            T obj = cls.newInstance();
            if (obj instanceof Map) {
                BeanUtils.populate(obj, request.getParameterMap());
            }
            else {
                setBeanProperty(obj, request.getParameterMap());
            }
            return obj;
        } catch (Exception e) {
            logger.error(getExceptionMessage(e));
            return null;
        }
    }

    /**
     * 将map对象封装成bean对象
     *
     * @param cls 返回bean对象的class
     * @param map 参数map对象
     * @param <T> 泛型
     * @return 对象实例
     */
    public static <T> T wrapBean(Class<T> cls, Map map) {
        try {
            T obj = cls.newInstance();
            if (obj instanceof Map) {
                BeanUtils.populate(obj, map);
            }
            else {
                setBeanProperty(obj, map);
            }
            return obj;
        } catch (Exception e) {
            logger.error(getExceptionMessage(e));
            return null;
        }
    }

    /**
     * 将请求参数封装成Bean集合对象
     *
     * @param request 请求对象
     * @param cls     类class
     * @return Object
     */
    public static <T> List<T> wrapBeanList(Class<T> cls, HttpServletRequest request) {
        List<T> list = new ArrayList<T>();
        Enumeration enumeration = request.getParameterNames();
        List<String> keys = new ArrayList<String>();
        int maxLen = 0;
        String[] values;
        while (enumeration.hasMoreElements()) {
            String key = (String) enumeration.nextElement();
            keys.add(key);
            values = request.getParameterValues(key);
            if (maxLen < values.length) maxLen = values.length;
        }

        Map<String, String> map;
        T obj;
        for (int i = 0; i < maxLen; i++) {
            map = new HashMap<String, String>();
            for (String s : keys) {
                if (i < request.getParameterValues(s).length) {
                    map.put(s, request.getParameterValues(s)[i]);
                }
            }
            try {
                obj = cls.newInstance();
                if (obj instanceof Map) {
                    BeanUtils.populate(obj, map);
                }
                else {
                    setBeanProperty(obj, map);
                }
                list.add(obj);
            } catch (Exception e) {
                logger.error(getExceptionMessage(e));
            }
        }
        obj = null;
        map = null;
        values = null;
        keys = null;
        enumeration = null;
        return list;
    }

    /**
     * 将请求参数的parameterMap设置到bean对象的属性中
     *
     * @param obj bean对象
     * @param map request.getParameterMap()
     */
    private static void setBeanProperty(Object obj, Map map) {
        if (map == null) return;
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
                }
                else if (object.getClass() == String.class) {
                    values = new String[]{(String) object};
                    value = (String) object;
                }
            }
            if (!PropertyUtils.isWriteable(obj, key)) continue;
            try {
                Class cls = PropertyUtils.getPropertyType(obj, key);
                if (cls == null) continue;
                if (cls == String.class) PropertyUtils.setProperty(obj, key, value);
                else if (cls == String[].class) PropertyUtils.setProperty(obj, key, values);
                else if (cls == BigDecimal.class) PropertyUtils.setProperty(obj, key, toBigDecimal(value));
                else if (cls == BigDecimal[].class) PropertyUtils.setProperty(obj, key, toBigDecimal(values));
                else if (cls == BigInteger.class) PropertyUtils.setProperty(obj, key, toBigInteger(value));
                else if (cls == BigInteger[].class) PropertyUtils.setProperty(obj, key, toBigInteger(values));
                else if (cls == Boolean.class) PropertyUtils.setProperty(obj, key, toBoolean(value));
                else if (cls == Boolean[].class) PropertyUtils.setProperty(obj, key, toBoolean(values));
                else if (cls == Double.class) PropertyUtils.setProperty(obj, key, toDouble(value));
                else if (cls == Double[].class) PropertyUtils.setProperty(obj, key, toDouble(values));
                else if (cls == Float.class) PropertyUtils.setProperty(obj, key, toFloat(value));
                else if (cls == Float[].class) PropertyUtils.setProperty(obj, key, toFloat(values));
                else if (cls == Integer.class) PropertyUtils.setProperty(obj, key, toInteger(value));
                else if (cls == Integer[].class) PropertyUtils.setProperty(obj, key, toInteger(values));
                else if (cls == Long.class) PropertyUtils.setProperty(obj, key, toLong(value));
                else if (cls == Long[].class) PropertyUtils.setProperty(obj, key, toLong(values));
                else if (cls == Short.class) PropertyUtils.setProperty(obj, key, toShort(value));
                else if (cls == Short[].class) PropertyUtils.setProperty(obj, key, toShort(values));
                else if (cls == Byte.class) PropertyUtils.setProperty(obj, key, toByte(value));
                else if (cls == Byte[].class) PropertyUtils.setProperty(obj, key, toByte(values));
                else if (cls == Date.class) PropertyUtils.setProperty(obj, key, toDate(value));
                else if (cls == Date[].class) PropertyUtils.setProperty(obj, key, toDate(values));
            } catch (Exception e) {
                logger.error(getExceptionMessage(e));
            }
        }
    }


    /**
     * 将以json方式提交的字符串封装成Bean对象
     *
     * @param json json对象
     * @param cls  bean的class
     * @return Object
     */
    public static <T> T wrapBean(Class<T> cls, String json) {
        if (json == null) return null;
        ObjectMapper mapper = new ObjectMapper();
        mapper.getDeserializationConfig().disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.getDeserializationConfig().setDateFormat(new DateFormat());
        try {
            return mapper.readValue(json, cls);
        } catch (Exception e) {
            logger.error(getExceptionMessage(e));
            return null;
        }
    }

    /**
     * 将以json方式提交的字符串封装成Bean对象
     *
     * @param json     json对象
     * @param javaType bean的class
     * @return Object
     */
    @SuppressWarnings({"unchecked"})
    public static <T> T wrapBean(JavaType javaType, String json) {
        if (json == null) return null;
        ObjectMapper mapper = new ObjectMapper();
        mapper.getDeserializationConfig().disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.getDeserializationConfig().setDateFormat(new DateFormat());
        try {
            return (T) mapper.readValue(json, javaType);
        } catch (Exception e) {
            logger.error(getExceptionMessage(e));
            return null;
        }
    }

    /**
     * 将json 数组封装成 arrays
     *
     * @param json json数组
     * @param cls  类名
     * @return T[]
     */
    @SuppressWarnings({"unchecked"})
    public static <T> T wrapArray(Class cls, String json) {
        if (json == null) return null;
        return (T) wrapBean(TypeFactory.arrayType(cls), json);
    }


    /**
     * 将以json方式提交的字符串封装成Bean集合
     *
     * @param jsonArray json数组
     * @param cls       bean的class
     * @return List
     */
    public static <T> List<T> wrapBeanList(Class<T> cls, String jsonArray) {
        if (jsonArray == null) return null;
        ObjectMapper mapper = new ObjectMapper();
        mapper.getDeserializationConfig().disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.getDeserializationConfig().setDateFormat(new DateFormat());
        try {
            return mapper.readValue(jsonArray, TypeFactory.collectionType(List.class, cls));
        } catch (Exception e) {
            logger.error(getExceptionMessage(e));
            return null;
        }
    }


    /**
     * 对象转换json字符串
     *
     * @param obj 对象
     * @return String
     */
    public static String toJsonString(Object obj) {
        if (obj == null) return null;
        ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
        mapper.getSerializationConfig().setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
        mapper.getSerializationConfig().disable(SerializationConfig.Feature.WRITE_NULL_MAP_VALUES);
        mapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.getSerializationConfig().setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        try {
            StringWriter sb = new StringWriter();
            mapper.writeValue(sb, obj);
            return sb.toString();
        } catch (Exception e) {
            logger.error(getExceptionMessage(e));
            return null;
        }
    }

    /**
     * 字符串转换 BigDecimal
     *
     * @param value 字符串
     * @return BigDecimal
     */
    public static BigDecimal toBigDecimal(String value) {
        if (value == null || value.equals("") || value.equals("null")) return null;
        try {
            return new BigDecimal(value);
        } catch (Exception e) {
            logger.error(getExceptionMessage(e));
            return null;
        }
    }


    /**
     * 字符串数组转换 BigDecimal[]
     *
     * @param values 字符串数组
     * @return BigDecimal[]
     */
    public static BigDecimal[] toBigDecimal(String[] values) {
        BigDecimal[] result = new BigDecimal[values.length];
        for (int i = 0; i < values.length; i++) result[i] = toBigDecimal(values[i]);
        return result;
    }


    /**
     * 字符串转换 BigInteger
     *
     * @param value 字符串
     * @return BigInteger
     */
    public static BigInteger toBigInteger(String value) {
        if (value == null || value.equals("") || value.equals("null")) return null;
        try {
            return new BigInteger(value);
        } catch (Exception e) {
            logger.error(getExceptionMessage(e));
            return null;
        }
    }


    /**
     * 字符串数组转换 BigInteger[]
     *
     * @param values 字符串数组
     * @return BigInteger[]
     */
    public static BigInteger[] toBigInteger(String[] values) {
        BigInteger[] result = new BigInteger[values.length];
        for (int i = 0; i < values.length; i++) result[i] = toBigInteger(values[i]);
        return result;
    }


    /**
     * 字符串转换 Boolean
     *
     * @param value 字符串
     * @return Boolean
     */
    public static Boolean toBoolean(String value) {
        if (value == null || value.equals("") || value.equals("null")) return null;
        try {
            return Boolean.parseBoolean(value);
        } catch (Exception e) {
            logger.error(getExceptionMessage(e));
            return null;
        }
    }


    /**
     * 字符串数组转换 Boolean[]
     *
     * @param values 字符串数组
     * @return Boolean[]
     */
    public static Boolean[] toBoolean(String[] values) {
        Boolean[] result = new Boolean[values.length];
        for (int i = 0; i < values.length; i++) result[i] = toBoolean(values[i]);
        return result;
    }

    /**
     * 字符串转换 Double
     *
     * @param value 字符串
     * @return Double
     */
    public static Double toDouble(String value) {
        if (value == null || value.equals("") || value.equals("null")) return null;
        try {
            return Double.parseDouble(value);
        } catch (Exception e) {
            logger.error(getExceptionMessage(e));
            return null;
        }
    }

    /**
     * 字符串数组转换 Double[]
     *
     * @param values 字符串数组
     * @return Double[]
     */
    public static Double[] toDouble(String[] values) {
        Double[] result = new Double[values.length];
        for (int i = 0; i < values.length; i++) result[i] = toDouble(values[i]);
        return result;
    }

    /**
     * 字符串转换 Float
     *
     * @param value 字符串
     * @return Float
     */
    public static Float toFloat(String value) {
        if (value == null || value.equals("") || value.equals("null")) return null;
        try {
            return Float.parseFloat(value);
        } catch (Exception e) {
            logger.error(getExceptionMessage(e));
            return null;
        }
    }

    /**
     * 字符串数组转换 Float[]
     *
     * @param values 字符串数组
     * @return Float[]
     */
    public static Float[] toFloat(String[] values) {
        Float[] result = new Float[values.length];
        for (int i = 0; i < values.length; i++) result[i] = toFloat(values[i]);
        return result;
    }


    /**
     * 字符串转换 Integer
     *
     * @param value 字符串
     * @return Integer
     */
    public static Integer toInteger(String value) {
        if (value == null || value.equals("") || value.equals("null")) return null;
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            logger.error(getExceptionMessage(e));
            return null;
        }
    }

    /**
     * 字符串数组转换 Integer[]
     *
     * @param values 字符串
     * @return Integer[]
     */
    public static Integer[] toInteger(String[] values) {
        Integer[] result = new Integer[values.length];
        for (int i = 0; i < values.length; i++) result[i] = toInteger(values[i]);
        return result;
    }

    /**
     * 字符串转换 Long
     *
     * @param value 字符串
     * @return Long
     */
    public static Long toLong(String value) {
        if (value == null || value.equals("") || value.equals("null")) return null;
        try {
            return Long.parseLong(value);
        } catch (Exception e) {
            logger.error(getExceptionMessage(e));
            return null;
        }
    }

    /**
     * 字符串数组转换 Long[]
     *
     * @param values 字符串
     * @return Long[]
     */
    public static Long[] toLong(String[] values) {
        Long[] result = new Long[values.length];
        for (int i = 0; i < values.length; i++) result[i] = toLong(values[i]);
        return result;
    }


    /**
     * 字符串转换 Short
     *
     * @param value 字符串
     * @return Short
     */
    public static Short toShort(String value) {
        if (value == null || value.equals("") || value.equals("null")) return null;
        try {
            return Short.parseShort(value);
        } catch (Exception e) {
            logger.error(getExceptionMessage(e));
            return null;
        }
    }

    /**
     * 字符串数组转换 Short[]
     *
     * @param values 字符串
     * @return Short[]
     */
    public static Short[] toShort(String[] values) {
        Short[] result = new Short[values.length];
        for (int i = 0; i < values.length; i++) result[i] = toShort(values[i]);
        return result;
    }


    public static Byte toByte(String value) {
        if (value == null || value.equals("") || value.equals("null")) return null;
        try {
            return Byte.parseByte(value);
        } catch (Exception e) {
            logger.error(getExceptionMessage(e));
            return null;
        }
    }

    public static Byte[] toByte(String[] values) {
        Byte[] result = new Byte[values.length];
        for (int i = 0; i < values.length; i++) result[i] = toByte(values[i]);
        return result;
    }


    /**
     * 字符串转换 Date
     *
     * @param value 字符串
     * @return Date
     */
    public static Date toDate(String value) {
        if (value == null || value.equals("") || value.equals("null")) return null;
        try {
            //DateFormat dateFormat = new DateFormat();
            dateFormat.setLenient(false);
            return dateFormat.parse(value);
        } catch (Exception e) {
            logger.error(getExceptionMessage(e));
            return null;
        }
    }

    /**
     * 字符串数组转换 Date[]
     *
     * @param values 字符串
     * @return Date[]
     */
    public static Date[] toDate(String[] values) {
        Date[] result = new Date[values.length];
        for (int i = 0; i < values.length; i++) result[i] = toDate(values[i]);
        return result;
    }

    /**
     * 获得基本类型字符串
     *
     * @param obj 对象
     * @return string
     */
    public static String toString(Object obj) {
        return toString(obj, true);
    }

    /**
     * 获得基本类型字符串
     *
     * @param obj                对象
     * @param emptyStringReplace 替换null时的字符串
     * @return string
     */
    public static String toString(Object obj, boolean emptyStringReplace) {

        if (obj == null) return emptyStringReplace ? "" : null;
        Class cls = obj.getClass();
        String str = "";
        String temp = "";
        if (cls == String.class) return (String) obj;
        else if (cls == String[].class) {
            for (String s : (String[]) obj) {
                temp = toString(s, emptyStringReplace);
                if (!StringUtil.isEmpty(temp)) str += "," + temp;
            }
            return StringUtil.isEmpty(str) ? "" : str.substring(1);
        }
        else if (cls == BigDecimal.class) return ((BigDecimal) obj).toPlainString();
        else if (cls == BigDecimal[].class) {
            for (BigDecimal s : (BigDecimal[]) obj) {
                temp = toString(s, emptyStringReplace);
                if (!StringUtil.isEmpty(temp)) str += "," + temp;
            }
            return StringUtil.isEmpty(str) ? "" : str.substring(1);
        }
        else if (cls == BigInteger.class) return obj.toString();
        else if (cls == BigInteger[].class) {
            for (BigInteger s : (BigInteger[]) obj) {
                temp = toString(s, emptyStringReplace);
                if (!StringUtil.isEmpty(temp)) str += "," + temp;
            }
            return StringUtil.isEmpty(str) ? "" : str.substring(1);
        }
        else if (cls == Boolean.class) return obj.toString();
        else if (cls == Boolean[].class) {
            for (Boolean s : (Boolean[]) obj) {
                temp = toString(s, emptyStringReplace);
                if (!StringUtil.isEmpty(temp)) str += "," + temp;
            }
            return StringUtil.isEmpty(str) ? "" : str.substring(1);
        }
        else if (cls == Double.class) return (new BigDecimal((Double) obj)).toPlainString();
        else if (cls == Double[].class) {
            for (Double s : (Double[]) obj) {
                temp = toString(s, emptyStringReplace);
                if (!StringUtil.isEmpty(temp)) str += "," + temp;
            }
            return StringUtil.isEmpty(str) ? "" : str.substring(1);
        }
        else if (cls == Float.class) return (new BigDecimal((Float) obj)).toPlainString();
        else if (cls == Float[].class) {
            for (Float s : (Float[]) obj) {
                temp = toString(s, emptyStringReplace);
                if (!StringUtil.isEmpty(temp)) str += "," + temp;
            }
            return StringUtil.isEmpty(str) ? "" : str.substring(1);
        }
        else if (cls == Integer.class) return obj.toString();
        else if (cls == Integer[].class) {
            for (Integer s : (Integer[]) obj) {
                temp = toString(s, emptyStringReplace);
                if (!StringUtil.isEmpty(temp)) str += "," + temp;
            }
            return StringUtil.isEmpty(str) ? "" : str.substring(1);
        }
        else if (cls == Long.class) return obj.toString();
        else if (cls == Long[].class) {
            for (Long s : (Long[]) obj) {
                temp = toString(s, emptyStringReplace);
                if (!StringUtil.isEmpty(temp)) str += "," + temp;
            }
            return StringUtil.isEmpty(str) ? "" : str.substring(1);
        }
        else if (cls == Short.class) return obj.toString();
        else if (cls == Short[].class) {
            for (Short s : (Short[]) obj) {
                temp = toString(s, emptyStringReplace);
                if (!StringUtil.isEmpty(temp)) str += "," + temp;
            }
            return StringUtil.isEmpty(str) ? "" : str.substring(1);
        }
        else if (cls == Date.class) return DateUtil.formatDate((Date) obj);
        else if (cls == Date[].class) {
            for (Date s : (Date[]) obj) {
                temp = toString(s, emptyStringReplace);
                if (!StringUtil.isEmpty(temp)) str += "," + temp;
            }
            return StringUtil.isEmpty(str) ? "" : str.substring(1);
        }
        return emptyStringReplace ? "" : null;
    }

    public static Object createInstance(String className) {
        try {
            Class cls = Class.forName(className);
            return cls.newInstance();
        } catch (Exception e) {
            logger.error(getExceptionMessage(e));
            return null;
        }
    }
    
    /**
     * 当前表单是否没有输入查询条件
     * @param params
     * @return 
     */
    public static boolean isBlank(Map<String, Object> params) {
    	
    	if( CollectionUtils.isEmpty(params) ) {
    		return true;
    	}
    	
    	boolean blank = true;
		for(String key: params.keySet()) {
			if( key.equalsIgnoreCase("sort") || key.equalsIgnoreCase("rows") ||
				key.equalsIgnoreCase("order") || key.equalsIgnoreCase("page") ||
				key.equalsIgnoreCase("refresh") || key.equalsIgnoreCase("recordCount") ||
				key.equalsIgnoreCase("countSql") || key.equalsIgnoreCase("recordSql") ||
				key.equalsIgnoreCase("queryTime") || key.equalsIgnoreCase("begin") ||
				key.equalsIgnoreCase("end") || key.equalsIgnoreCase("t_name") ||
				key.equalsIgnoreCase("totalPage") || key.equalsIgnoreCase("defaultSort") ||
				key.equalsIgnoreCase("head") || key.equalsIgnoreCase("foot") ||
				key.equalsIgnoreCase("sortOrder") || key.equalsIgnoreCase("tableCounts")
			) {
				continue;
			}
				
			Object o = params.get(key);
			if( o != null ) {
				if( o instanceof java.util.Collection ) {
					Collection c = (Collection)o;
					// 集合Collection
					if( !CollectionUtils.isEmpty(c) ) {
						blank = false;
						break;
					}
				}else if(o instanceof java.util.Set) {
					// 集合Set
					Set s = (Set)o;
					if( !CollectionUtils.isEmpty(s) ) {
						blank = false;
						break;
					}
				}else {
					// 非集合
					if( !o.toString().trim().equals("") ) {
						blank = false;
						break;
					}
				}
			}
			
		}
		
		return blank;
    }

}


class DateConverter extends DateTimeConverter {

    DateConverter() {
    }

    DateConverter(Object o) {
        super(o);
    }

    @Override
    protected Object convertToType(Class aClass, Object o) {
        try {
            return super.convertToType(aClass, o);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
	protected Class getDefaultType() {
        return Date.class;
    }



    
}


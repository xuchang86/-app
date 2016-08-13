package com.rogrand.core.domain;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.codehaus.jackson.annotate.JsonIgnore;

import com.rogrand.core.util.BeanUtil;
import com.rogrand.core.util.StringUtil;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：基础映射类
 */
public class Base extends SerializeCloneable {

	private static final long serialVersionUID = -7467183350240052770L;
	
	private Map<String, Boolean> fields; //需要更新的字段
    private Map<String, String> comment;//映射对象的注释（包括表名称，字段名称等）
    private String mode;//查询方式
    private String order;//排序方式
    private String status;//状态

    public Base() {
        fields = new HashMap<String, Boolean>();
        comment = new HashMap<String, String>();
    }

    /**
     * 得到要更新的字段map,用于ibatis中动态更新的判断
     *
     * @return map
     */
    @JsonIgnore(value = true)
    public final Map<String, Boolean> getFields() {
        return fields;
    }

    /**
     * 清空动态更新
     */
    public final void clearFields() {
        fields.clear();
    }

    /**
     * 设置单个需要更新的字段（子类的set方法中调用）
     *
     * @param field 字段
     */
    protected final void putField(String field) {
        fields.put(field, true);
    }

    /**
     * 设置多个需要更新的字段，字段以 逗号 分割
     *
     * @param fields 字段字符串
     */
    public final void putFields(String fields) {
        putFields(fields.split(","));
    }

    /**
     * 设置多个需要更新的字段
     *
     * @param fields 字段数组
     */
    public final void putFields(String[] fields) {
        clearFields();
        for (String field : fields) {
            String value = field.trim();
            if (!StringUtil.isEmpty(value)) putField(value);
        }
    }


    /**
     * 设置注释
     *
     * @param key     字段名或表名
     * @param content 注释内容
     */
    protected final void putComment(String key, String content) {
        comment.put(key, content);
    }

    /**
     * 得到注释
     *
     * @param key 字段名或表名
     * @return String 注释内容
     */
    public final String getComment(String key) {
        return comment.get(key);
    }

    /**
     * 得到查询方式
     *
     * @return String
     */
    public final String getMode() {
        return mode;
    }

    /**
     * 设置查询方式
     *
     * @param mode 方式
     */
    public final void setMode(String mode) {
        this.mode = mode;
    }


    /**
     * 得到排序方式
     *
     * @return String
     */
    public String getOrder() {
        return order;
    }

    /**
     * 设置排序方式
     *
     * @param order 方式
     */
    public void setOrder(String order) {
        this.order = order;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 设置查询方式和查询字段参数，构造函数中调用(快捷查询)
     *
     * @param property 属性名
     * @param value 属性值
     */
    @SuppressWarnings("rawtypes")
    protected final void init(String property , Object value) {
        try {
            setMode(property);
            if (!PropertyUtils.isWriteable(this, property)) return;
            Class cls = PropertyUtils.getPropertyType(this, property);
            if (cls == null) return;
            if (value == null) return;
            if (value.getClass() == String.class) {
                if (cls == String.class) PropertyUtils.setProperty(this, property, value);
                else if (cls == String[].class) PropertyUtils.setProperty(this, property, new String[]{(String) value});
                else if (cls == BigDecimal.class) PropertyUtils.setProperty(this, property, BeanUtil.toBigDecimal((String) value));
                else if (cls == BigDecimal[].class) PropertyUtils.setProperty(this, property, BeanUtil.toBigDecimal(new String[]{(String) value}));
                else if (cls == BigInteger.class) PropertyUtils.setProperty(this, property, BeanUtil.toBigInteger((String) value));
                else if (cls == BigInteger[].class) PropertyUtils.setProperty(this, property, BeanUtil.toBigInteger(new String[]{(String) value}));
                else if (cls == Boolean.class) PropertyUtils.setProperty(this, property, BeanUtil.toBoolean((String) value));
                else if (cls == Boolean[].class) PropertyUtils.setProperty(this, property, BeanUtil.toBoolean(new String[]{(String) value}));
                else if (cls == Double.class) PropertyUtils.setProperty(this, property, BeanUtil.toDouble((String) value));
                else if (cls == Double[].class) PropertyUtils.setProperty(this, property, BeanUtil.toDouble(new String[]{(String) value}));
                else if (cls == Float.class) PropertyUtils.setProperty(this, property, BeanUtil.toFloat((String) value));
                else if (cls == Float[].class) PropertyUtils.setProperty(this, property, BeanUtil.toFloat(new String[]{(String) value}));
                else if (cls == Integer.class) PropertyUtils.setProperty(this, property, BeanUtil.toInteger((String) value));
                else if (cls == Integer[].class) PropertyUtils.setProperty(this, property, BeanUtil.toInteger(new String[]{(String) value}));
                else if (cls == Long.class) PropertyUtils.setProperty(this, property, BeanUtil.toLong((String) value));
                else if (cls == Long[].class) PropertyUtils.setProperty(this, property, BeanUtil.toLong(new String[]{(String) value}));
                else if (cls == Short.class) PropertyUtils.setProperty(this, property, BeanUtil.toShort((String) value));
                else if (cls == Short[].class) PropertyUtils.setProperty(this, property, BeanUtil.toShort(new String[]{(String) value}));
                else if (cls == Date.class) PropertyUtils.setProperty(this, property, BeanUtil.toDate((String) value));
                else if (cls == Date[].class) PropertyUtils.setProperty(this, property, BeanUtil.toDate(new String[]{(String) value}));
            }
            else if (value.getClass() == String[].class) {
                String[] values = (String[]) value;
                String str = (values != null && values.length > 0) ? values[0] : null;
                if (cls == String.class) PropertyUtils.setProperty(this, property, str);
                else if (cls == String[].class) PropertyUtils.setProperty(this, property, values);
                else if (cls == BigDecimal.class) PropertyUtils.setProperty(this, property, BeanUtil.toBigDecimal(str));
                else if (cls == BigDecimal[].class) PropertyUtils.setProperty(this, property, BeanUtil.toBigDecimal(values));
                else if (cls == BigInteger.class) PropertyUtils.setProperty(this, property, BeanUtil.toBigInteger(str));
                else if (cls == BigInteger[].class) PropertyUtils.setProperty(this, property, BeanUtil.toBigInteger(values));
                else if (cls == Boolean.class) PropertyUtils.setProperty(this, property, BeanUtil.toBoolean(str));
                else if (cls == Boolean[].class) PropertyUtils.setProperty(this, property, BeanUtil.toBoolean(values));
                else if (cls == Double.class) PropertyUtils.setProperty(this, property, BeanUtil.toDouble(str));
                else if (cls == Double[].class) PropertyUtils.setProperty(this, property, BeanUtil.toDouble(values));
                else if (cls == Float.class) PropertyUtils.setProperty(this, property, BeanUtil.toFloat(str));
                else if (cls == Float[].class) PropertyUtils.setProperty(this, property, BeanUtil.toFloat(values));
                else if (cls == Integer.class) PropertyUtils.setProperty(this, property, BeanUtil.toInteger(str));
                else if (cls == Integer[].class) PropertyUtils.setProperty(this, property, BeanUtil.toInteger(values));
                else if (cls == Long.class) PropertyUtils.setProperty(this, property, BeanUtil.toLong(str));
                else if (cls == Long[].class) PropertyUtils.setProperty(this, property, BeanUtil.toLong(values));
                else if (cls == Short.class) PropertyUtils.setProperty(this, property, BeanUtil.toShort(str));
                else if (cls == Short[].class) PropertyUtils.setProperty(this, property, BeanUtil.toShort(values));
                else if (cls == Date.class) PropertyUtils.setProperty(this, property, BeanUtil.toDate(str));
                else if (cls == Date[].class) PropertyUtils.setProperty(this, property, BeanUtil.toDate(values));
            }
            else if (value.getClass() == cls) {
                PropertyUtils.setProperty(this, property, value);
            }
        } catch (Exception e) {
            logger.error(getExceptionMessage(e));
        }
    }

    /**
     * 得到异常消息，
     * @param e 异常
     * @return string
     */
    private String getExceptionMessage(Exception e) {
        String msg = e.getMessage();
        if (msg == null) msg = e.getClass().getName();
        return msg;
    }

    /**
     * 获得映射对象中所有属性列表
     *
     * @return String[]
     */
    @SuppressWarnings("rawtypes")
    @JsonIgnore(value = true)
    public final String[] getAllFields() {
        Class cls = this.getClass();
        List<String> fieldList = new ArrayList<String>();
        while (true) {
            if (cls == Base.class) break;
            Field[] fields = cls.getDeclaredFields();
            for (Field f : fields) {
                String name = f.getName();
                if (PropertyUtils.isReadable(this, name)) {
                    fieldList.add(name);
                }
            }
            cls = cls.getSuperclass();
        }
        return fieldList.toArray(new String[fieldList.size()]);
    }

}

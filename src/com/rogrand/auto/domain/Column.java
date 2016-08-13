package com.rogrand.auto.domain;

import com.rogrand.core.domain.SerializeCloneable;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：从数据库得到要自动生成的table的字段信息
 */
public class Column extends SerializeCloneable {

	private static final long serialVersionUID = 4761199922482795070L;
	
	private String name;//字段英文名称
    private String annotation;//字段注释（中文名）
    private String jdbcType;//jdbc数据类型
    private String nullAble;//允许为空
    private String pk;//主键字段
    private Integer length;//字段长度
    private Integer scale;//字段精度


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    public String getJavaType() {
        String ibatisType = getIbatisType();
        if (ibatisType == null) return "String";
        else if (ibatisType.equals("VARCHAR")) return "String";
        else if (ibatisType.equals("TEXT")) return "String";
        else if (ibatisType.equals("CLOB")) return "String";
        else if (ibatisType.equals("NUMERIC")) return (scale == null || scale == 0) ? (length < 10 ? "Integer" : "Long") : "Double";
        else if (ibatisType.equals("TIMESTAMP")) return "Date";
        else if (ibatisType.equals("BLOB")) return "byte[]";
        else return "String";
    }

    public String getIbatisType(){
        if (jdbcType == null) return null;
        else if (jdbcType.equalsIgnoreCase("CHAR")) return "VARCHAR";
        else if (jdbcType.equalsIgnoreCase("NCHAR")) return "VARCHAR";
        else if (jdbcType.equalsIgnoreCase("VARCHAR")) return "VARCHAR";
        else if (jdbcType.equalsIgnoreCase("VARCHAR2")) return "VARCHAR";
        else if (jdbcType.equalsIgnoreCase("NVARCHAR2")) return "VARCHAR";

        else if (jdbcType.equalsIgnoreCase("NUMBER")) return "NUMERIC";
        else if (jdbcType.equalsIgnoreCase("NUMERIC")) return "NUMERIC";
        else if (jdbcType.equalsIgnoreCase("DECIMAL")) return "NUMERIC";
        else if (jdbcType.equalsIgnoreCase("DEC")) return "NUMERIC";
        else if (jdbcType.equalsIgnoreCase("MONEY")) return "NUMERIC";
        else if (jdbcType.equalsIgnoreCase("FLOAT")) return "NUMERIC";
        else if (jdbcType.equalsIgnoreCase("DOUBLE")) return "NUMERIC";
        else if (jdbcType.equalsIgnoreCase("REAL")) return "NUMERIC";
        else if (jdbcType.equalsIgnoreCase("INT")) return "NUMERIC";
        else if (jdbcType.equalsIgnoreCase("INTEGER")) return "NUMERIC";
        else if (jdbcType.equalsIgnoreCase("BIGINT")) return "NUMERIC";
        else if (jdbcType.equalsIgnoreCase("TINYINT")) return "NUMERIC";
        else if (jdbcType.equalsIgnoreCase("SMALLINT")) return "NUMERIC";

        else if (jdbcType.equalsIgnoreCase("DATE")) return "TIMESTAMP";
        else if (jdbcType.equalsIgnoreCase("DATETIME")) return "TIMESTAMP";
        else if (jdbcType.equalsIgnoreCase("TIME")) return "TIMESTAMP";
        else if (jdbcType.equalsIgnoreCase("TIMESTAMP")) return "TIMESTAMP";
        else if (jdbcType.equalsIgnoreCase("TIMESTAMP(6)")) return "TIMESTAMP";

        else if (jdbcType.equalsIgnoreCase("TEXT")) return "TEXT";
        else if (jdbcType.equalsIgnoreCase("LONGVARCHAR")) return "TEXT";
        else if (jdbcType.equalsIgnoreCase("CLOB")) return "CLOB";

        else if (jdbcType.equalsIgnoreCase("LONGVARBINARY")) return "BLOB";
        else if (jdbcType.equalsIgnoreCase("BLOB")) return "BLOB";
        else if (jdbcType.equalsIgnoreCase("IMAGE")) return "BLOB";

        else return "VARCHAR";
    }

//    public String getPropertyName() {
//        String[] arrStr = name.split("_");
//        String propertyName = "";
//        for (String s : arrStr) {
//            propertyName += StringUtil.upperFirstChar(s.toLowerCase());
//        }
//        return StringUtil.lowerFirstChar(propertyName);
//    }

    public String getNullAble() {
        return nullAble;
    }

    public void setNullAble(String nullAble) {
        this.nullAble = nullAble;
    }

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    public String getMax(){
        String n = "";
        if (getJavaType().equals("Integer") || getJavaType().equals("Long")) {
            for (int i = 0; i < length; i++) n += "9";
        }
        else if (getJavaType().equals("Double")) {
            for (int i = 0; i < length - scale; i++) n += "9";
            n += ".";
            for (int i = 0; i < scale; i++) n += "9";
        }
        return n;
    }

}

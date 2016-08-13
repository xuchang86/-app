package com.rogrand.auto.domain;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.rogrand.core.domain.SerializeCloneable;
import com.rogrand.core.util.StringUtil;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：要自动生成table的信息
 */
public class Table extends SerializeCloneable {
	
	private static final long serialVersionUID = -7703109005640698128L;
	
	private Integer id; //表id
    private String name;//表名称
    private String annotation;//表注释
    private List<Column> columnList;//字段集合
    private Boolean buildCURD;//生成增删改查维护
    private String subjectModuleName;   //子模块名

    public String getClassDomain(){
        int index = name.indexOf("_");
        String className = index != -1 ? name.substring(1 + index) : name;
        String[] arrStr = className.split("_");
        className = "";
        for (String s : arrStr) {
            className += StringUtil.upperFirstChar(s.toLowerCase());
        }
        return className;
    }

    public String getClassController(){
        return getClassDomain()+"Controller";
    }

    public String getClassService(){
        return getClassDomain()+"Service";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public List<Column> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<Column> columnList) {
        this.columnList = columnList;
    }

    public Boolean getBuildCURD() {
        return buildCURD;
    }

    public void setBuildCURD(Boolean buildCURD) {
        this.buildCURD = buildCURD;
    }

    public String getSubjectModuleName() {
        return subjectModuleName;
    }

    public void setSubjectModuleName(String subjectModuleName) {
        this.subjectModuleName = subjectModuleName;
    }

    public String getModuleName() {
    	/*
        int index= name.indexOf("_");
        if(index!=-1) return name.substring(0,index).toLowerCase();
        else return "";
        */
    	return "";
    }

    @JsonIgnore(value = true)
    public String getPackageDomain(){
        String packageName="com.rogrand";
        if (!StringUtil.isEmpty(this.getModuleName())) packageName+="."+this.getModuleName();
        if (!StringUtil.isEmpty(this.getSubjectModuleName())) packageName+="."+this.getSubjectModuleName();
        packageName+=".domain";
        return packageName;
    }

    @JsonIgnore(value = true)
    public String getPackageController(){
        String packageName="com.rogrand";
        if (!StringUtil.isEmpty(this.getModuleName())) packageName+="."+this.getModuleName();
        if (!StringUtil.isEmpty(this.getSubjectModuleName())) packageName+="."+this.getSubjectModuleName();
        packageName+=".controller";
        return packageName;
    }

    @JsonIgnore(value = true)
    public String getPackageService(){
        String packageName="com.rogrand";
        if (!StringUtil.isEmpty(this.getModuleName())) packageName+="."+this.getModuleName();
        if (!StringUtil.isEmpty(this.getSubjectModuleName())) packageName+="."+this.getSubjectModuleName();
        packageName+=".service";
        return packageName;
    }

    @JsonIgnore(value = true)
    public String getMappings(){
        String mappings = "";
        if (!StringUtil.isEmpty(this.getModuleName())) mappings += "/" + this.getModuleName();
        if (!StringUtil.isEmpty(this.getSubjectModuleName())) mappings+="/"+this.getSubjectModuleName();
        mappings += "/" + StringUtil.lowerFirstChar(getClassDomain()) + "_*.do";
        return mappings;
    }

    public String getPk(){
        if(this.columnList!=null&&this.columnList.size()>0){
            for(Column column:columnList){
                if("Y".equals(column.getPk())) return column.getName();
            }
            return columnList.get(0).getName();
        }
        else{
            return "";
        }
    }
}

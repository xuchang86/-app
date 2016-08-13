package com.rogrand.sys.domain;

import java.util.Date;

import com.rogrand.core.domain.Base;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：系统日志
 */
public class Log extends Base {

    private String sl_id;  //日志ID
    private Date sl_date;  //日志时间
    private String sl_user_code;  //登录账号
    private String sl_user_name;  //用户名称
    private String sl_org_name;  //机构名称
    private String sl_ip;  //IP地址
    private String sl_class;  //类名称
    private String sl_method;  //方法名称
    private String sl_description;  //方法描述
    private String sl_content;  //日志内容

    /**
     * 系统日志表对象构造函数
     */
    public Log() {
        super();
        this.putComment("tableComment", "系统日志表");
        this.putComment("sl_id", "日志ID");
        this.putComment("sl_date", "日志时间");
        this.putComment("sl_user_code", "登录账号");
        this.putComment("sl_user_name", "用户名称");
        this.putComment("sl_org_name", "机构名称");
        this.putComment("sl_ip", "IP地址");
        this.putComment("sl_class", "类名称");
        this.putComment("sl_method", "方法名称");
        this.putComment("sl_description", "方法描述");
        this.putComment("sl_content", "日志内容");
    }

    public Log(Long sl_id) {
        this("sl_id",sl_id);
    }

    public Log(String sl_id) {
        this("sl_id",sl_id);
    }

    public Log(String property, Object value) {
        this();
        init(property, value);
    }

    /**
    * 设置日志ID
    */
    public void setSl_id(String sl_id){
        putField("sl_id");
        this.sl_id = sl_id;
    }

    /**
    * 获得日志ID
    */
    public String getSl_id(){
        return this.sl_id;
    }
    
    /**
    * 设置日志时间
    */
    public void setSl_date(Date sl_date){
        putField("sl_date");
        this.sl_date = sl_date;
    }

    /**
    * 获得日志时间
    */
    public Date getSl_date(){
        return this.sl_date;
    }
    
    /**
    * 设置登录账号
    */
    public void setSl_user_code(String sl_user_code){
        putField("sl_user_code");
        this.sl_user_code = sl_user_code;
    }

    /**
    * 获得登录账号
    */
    public String getSl_user_code(){
        return this.sl_user_code;
    }
    
    /**
    * 设置用户名称
    */
    public void setSl_user_name(String sl_user_name){
        putField("sl_user_name");
        this.sl_user_name = sl_user_name;
    }

    /**
    * 获得用户名称
    */
    public String getSl_user_name(){
        return this.sl_user_name;
    }
    
    /**
    * 设置机构名称
    */
    public void setSl_org_name(String sl_org_name){
        putField("sl_org_name");
        this.sl_org_name = sl_org_name;
    }

    /**
    * 获得机构名称
    */
    public String getSl_org_name(){
        return this.sl_org_name;
    }
    
    /**
    * 设置IP地址
    */
    public void setSl_ip(String sl_ip){
        putField("sl_ip");
        this.sl_ip = sl_ip;
    }

    /**
    * 获得IP地址
    */
    public String getSl_ip(){
        return this.sl_ip;
    }
    
    /**
    * 设置类名称
    */
    public void setSl_class(String sl_class){
        putField("sl_class");
        this.sl_class = sl_class;
    }

    /**
    * 获得类名称
    */
    public String getSl_class(){
        return this.sl_class;
    }
    
    /**
    * 设置方法名称
    */
    public void setSl_method(String sl_method){
        putField("sl_method");
        this.sl_method = sl_method;
    }

    /**
    * 获得方法名称
    */
    public String getSl_method(){
        return this.sl_method;
    }
    
    /**
    * 设置方法描述
    */
    public void setSl_description(String sl_description){
        putField("sl_description");
        this.sl_description = sl_description;
    }

    /**
    * 获得方法描述
    */
    public String getSl_description(){
        return this.sl_description;
    }
    
    /**
    * 设置日志内容
    */
    public void setSl_content(String sl_content){
        putField("sl_content");
        this.sl_content = sl_content;
    }

    /**
    * 获得日志内容
    */
    public String getSl_content(){
        return this.sl_content;
    }
    

}




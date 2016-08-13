package com.rogrand.sys.domain;

import com.rogrand.core.domain.Base;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：机构
 */
public class Org extends Base {
    
    private String so_id;
    private String so_name;
    private String so_parentid;
    private Integer so_order;
    private String so_code;
    private String so_contact;
    private String so_email;
    private String so_post;
    private String so_purview;

    private String so_parentname;
    private String so_child;
    private String so_used;


    public Org() {
        super();
        this.putComment("tableComment", "机构表");        
        this.putComment("so_id", "机构ID");
        this.putComment("so_name", "机构名称机构部门表");
        this.putComment("so_parentid", "上级机构ID");
        this.putComment("so_order", "机构排序");
        this.putComment("so_code", "机构编码");
        this.putComment("so_contact", "联系电话");
        this.putComment("so_email", "电子邮箱");
        this.putComment("so_post", "邮政编码");
        this.putComment("so_purview", "机构权限");

    }

    public Org(Long so_id){
        this("so_id",so_id);
    }

    public Org(String so_id){
        this("so_id",so_id);
    }

    public Org(String property, Object value) {
        this();
        init(property, value);
    }

    public void setSo_id(String so_id) {
        putField("so_id");
        this.so_id = so_id;
    }

    public void setSo_name(String so_name) {
        putField("so_name");
        this.so_name = so_name;
    }

    public void setSo_parentid(String so_parentid) {
        putField("so_parentid");
        this.so_parentid = so_parentid;
    }

    public void setSo_order(Integer so_order) {
        putField("so_order");
        this.so_order = so_order;
    }

    public void setSo_code(String so_code) {
        putField("so_code");
        this.so_code = so_code;
    }

    public void setSo_contact(String so_contact) {
        putField("so_contact");
        this.so_contact = so_contact;
    }

    public void setSo_email(String so_email) {
        putField("so_email");
        this.so_email = so_email;
    }

    public void setSo_post(String so_post) {
        putField("so_post");
        this.so_post = so_post;
    }

    public void setSo_purview(String so_purview) {
        putField("so_purview");
        this.so_purview = so_purview;
    }

    public void setSo_child(String so_child) {
        putField("so_child");
        this.so_child = so_child;
    }

    public void setSo_used(String so_used) {
        putField("so_used");
        this.so_used = so_used;
    }

    public String getSo_used() {
        return so_used;
    }

    public String getSo_id() {
        return so_id;
    }

    public String getSo_name() {
        return so_name;
    }

    public String getSo_parentid() {
        return so_parentid;
    }

    public Integer getSo_order() {
        return so_order;
    }

    public String getSo_code() {
        return so_code;
    }

    public String getSo_contact() {
        return so_contact;
    }

    public String getSo_email() {
        return so_email;
    }

    public String getSo_post() {
        return so_post;
    }

    public String getSo_purview() {
        return so_purview;
    }

    public String getSo_child() {
        return so_child;
    }

    public String getSo_parentname() {
        return so_parentname;
    }

    public void setSo_parentname(String so_parentname) {
        putField("so_parentname");
        this.so_parentname = so_parentname;
    }
}

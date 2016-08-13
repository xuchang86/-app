package com.rogrand.sys.domain;

import com.rogrand.core.domain.Base;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：菜单
 */
public class Menu extends Base {

    private String sm_id;  //菜单ID

    private String sap_id;  //应用系统ID

    private String sm_name;  //菜单名称

    private String sm_parentid;  //父级菜单ID

    private String sm_icon;  //菜单图标样式

    private String sm_type;  //菜单类型

    private String sm_action;  //菜单动作

    private Long sm_order;  //菜单顺序

    private String sm_content;  //备注

    private String sm_admin;  /*管理员菜单*/

    private String sm_child;  //是否有子菜单

    private String sm_parentname;  //父菜单名称

    private String sap_name;    //系统名称


    /**
     * 菜单表对象构造函数
     */
    public Menu() {
        super();
    }

    public Menu(String sm_id) {
        this("sm_id", sm_id);
    }

    public Menu(String property, Object value) {
        this();
        init(property, value);
    }

    /**
     * 设置菜单ID
     */
    public void setSm_id(String sm_id) {
        putField("sm_id");
        this.sm_id = sm_id;
    }

    /**
     * 获得菜单ID
     */
    public String getSm_id() {
        return this.sm_id;
    }

    /**
     * 设置菜单名称
     */
    public void setSm_name(String sm_name) {
        putField("sm_name");
        this.sm_name = sm_name;
    }

    /**
     * 获得菜单名称
     */
    public String getSm_name() {
        return this.sm_name;
    }

    /**
     * 设置父级菜单ID
     */
    public void setSm_parentid(String sm_parentid) {
        putField("sm_parentid");
        this.sm_parentid = sm_parentid;
    }

    /**
     * 获得父级菜单ID
     */
    public String getSm_parentid() {
        return this.sm_parentid;
    }

    /**
     * 设置菜单图标样式
     */
    public void setSm_icon(String sm_icon) {
        putField("sm_icon");
        this.sm_icon = sm_icon;
    }

    /**
     * 获得菜单图标样式
     */
    public String getSm_icon() {
        return this.sm_icon;
    }

    /**
     * 设置菜单类型
     */
    public void setSm_type(String sm_type) {
        putField("sm_type");
        this.sm_type = sm_type;
    }

    /**
     * 获得菜单类型
     */
    public String getSm_type() {
        return this.sm_type;
    }

    /**
     * 设置菜单动作
     */
    public void setSm_action(String sm_action) {
        putField("sm_action");
        this.sm_action = sm_action;
    }

    /**
     * 获得菜单动作
     */
    public String getSm_action() {
        return this.sm_action;
    }

    /**
     * 设置菜单顺序
     */
    public void setSm_order(Long sm_order) {
        putField("sm_order");
        this.sm_order = sm_order;
    }

    /**
     * 获得菜单顺序
     */
    public Long getSm_order() {
        return this.sm_order;
    }

    /**
     * 设置备注
     */
    public void setSm_content(String sm_content) {
        putField("sm_content");
        this.sm_content = sm_content;
    }

    /**
     * 获得备注
     */
    public String getSm_content() {
        return this.sm_content;
    }

    /**
     * 设置管理员菜单
     */
    public void setSm_admin(String sm_admin) {
        putField("sm_admin");
        this.sm_admin = sm_admin;
    }

    /**
     * 获得管理员菜单
     */
    public String getSm_admin() {
        return this.sm_admin;
    }

    /**
     * 设置应用系统ID
     */
    public void setSap_id(String sap_id) {
        putField("sap_id");
        this.sap_id = sap_id;
    }

    /**
     * 获得应用系统ID
     */
    public String getSap_id() {
        return this.sap_id;
    }


    public String getSm_child() {
        return sm_child;
    }

    public void setSm_child(String sm_child) {
        putField("sm_child");
        this.sm_child = sm_child;
    }

    public String getSm_parentname() {
        return sm_parentname;
    }

    public void setSm_parentname(String sm_parentname) {
        putField("sm_parentname");
        this.sm_parentname = sm_parentname;
    }

    public String getSap_name() {
        return sap_name;
    }

    public void setSap_name(String sap_name) {
        putField("sap_name");
        this.sap_name = sap_name;
    }

}


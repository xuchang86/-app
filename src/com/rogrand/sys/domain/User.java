package com.rogrand.sys.domain;

import java.util.Date;

import com.rogrand.core.domain.Base;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：系统用户
 */
public class User extends Base {
    
	private static final long serialVersionUID = -1587966574883862186L;
	
	private String su_id;
    private String su_code;
    private String su_name;
    private String su_sex;
    private String su_password;
    private String su_status;
    private String su_contact;
    private String su_email;
    private String su_admin;
    private String su_content;
    private String su_random;
    private String so_id;
    private String su_last_ip;//用户上次登录ip
    private Date su_add_time;//用户创建时间
    private Date su_last_time;//用户上次登录时间
    private int su_login_count;//用户登录次数
    
    //    附加字段
    private String so_code;
    private String so_name;
    private String so_parentid;
    private String so_purview;

    private String sr_id;
    private String sr_code;
    private String sr_name;
    private String sap_id;

    public User() {
        super();
        this.putComment("tableComment", "系统用户表");        
        this.putComment("su_id", "用户ID");
        this.putComment("su_code", "用户帐号");
        this.putComment("su_name", "用户姓名");
        this.putComment("su_sex", "性别");
        this.putComment("su_password", "用户密码");
        this.putComment("su_status", "用户状态");
        this.putComment("su_contact", "联系方式");
        this.putComment("su_email", "邮箱");
        this.putComment("su_admin", "超级管理员权限");
        this.putComment("su_content", "备注");
        this.putComment("su_random", "登录随机数");
        this.putComment("so_id", "机构ID");
        this.putComment("su_last_ip", "用户上次登录IP");
        this.putComment("su_add_time", "用户创建时间");
        this.putComment("su_last_time", "用户上次登录时间");
        this.putComment("su_login_count", "用户登录次数");
        this.putComment("su_password_old", "修改密码时校验旧密码");
        this.putComment("so_code", "机构编码");
        this.putComment("so_name", "机构名称");
        this.putComment("so_parentid", "父机构ID");
        this.putComment("so_purview", "机构权限");
        this.putComment("sr_id", "角色id，多角色以逗号连接");
        this.putComment("sr_code", "角色编码，多角色以逗号连接");
        this.putComment("sr_name", "角色名称，多角色以逗号连接");
        this.putComment("sap_id", "登录系统ID");
    }

    public User(Long su_id) {
        this("su_id",su_id);
    }

    public User(String su_id) {
        this("su_id",su_id);
    }

    public User(String property, Object value) {
        this();
        init(property, value);
    }

    public void setSu_id(String su_id) {
        putField("su_id");
        this.su_id = su_id;
    }

    public Date getSu_last_time() {
		return su_last_time;
	}

	public void setSu_last_time(Date su_last_time) {
		putField("su_last_time");
		this.su_last_time = su_last_time;
	}

	public int getSu_login_count() {
		return su_login_count;
	}

	public void setSu_login_count(int su_login_count) {
		putField("su_login_count");
		this.su_login_count = su_login_count;
	}

	public void setSu_code(String su_code) {
        putField("su_code");
        this.su_code = su_code;
    }

    public void setSu_name(String su_name) {
        putField("su_name");
        this.su_name = su_name;
    }

    public void setSu_sex(String su_sex) {
        putField("su_sex");
        this.su_sex = su_sex;
    }

    public void setSu_password(String su_password) {
        putField("su_password");
        this.su_password = su_password;
    }

    public void setSu_status(String su_status) {
        putField("su_status");
        this.su_status = su_status;
    }

    public void setSu_contact(String su_contact) {
        putField("su_contact");
        this.su_contact = su_contact;
    }

    public void setSu_email(String su_email) {
        putField("su_email");
        this.su_email = su_email;
    }

    public void setSu_admin(String su_admin) {
        putField("su_admin");
        this.su_admin = su_admin;
    }

    public void setSu_content(String su_content) {
        putField("su_content");
        this.su_content = su_content;
    }

    public void setSu_random(String su_random) {
        putField("su_random");
        this.su_random = su_random;
    }

    public void setSo_id(String so_id) {
        putField("so_id");
        this.so_id = so_id;
    }

    public void setSo_code(String so_code) {
        putField("so_code");
        this.so_code = so_code;
    }

    public void setSo_name(String so_name) {
        putField("so_name");
        this.so_name = so_name;
    }

    public void setSo_parentid(String so_parentid) {
        putField("so_parentid");
        this.so_parentid = so_parentid;
    }

    public void setSo_purview(String so_purview) {
        putField("so_purview");
        this.so_purview = so_purview;
    }

    public void setSr_id(String sr_id) {
        putField("sr_id");
        this.sr_id = sr_id;
    }

    public String getSu_last_ip() {
		return su_last_ip;
	}

	public void setSu_last_ip(String su_last_ip) {
		putField("su_last_ip");
		this.su_last_ip = su_last_ip;
	}

	public void setSr_code(String sr_code) {
        putField("sr_code");
        this.sr_code = sr_code;
    }

    public void setSr_name(String sr_name) {
        putField("sr_name");
        this.sr_name = sr_name;
    }

    public void setSap_id(String sap_id) {
        putField("sap_id");
        this.sap_id = sap_id;
    }

    public String getSu_id() {
        return su_id;
    }

    public String getSu_code() {
        return su_code;
    }

    public String getSu_name() {
        return su_name;
    }

    public String getSu_sex() {
        return su_sex;
    }

    public String getSu_password() {
        return su_password;
    }

    public String getSu_status() {
        return su_status;
    }

    public String getSu_contact() {
        return su_contact;
    }

    public String getSu_email() {
        return su_email;
    }

    public String getSu_admin() {
        return su_admin;
    }

    public String getSu_content() {
        return su_content;
    }

    public String getSu_random() {
        return su_random;
    }

    public String getSo_id() {
        return so_id;
    }

    public Date getSu_add_time() {
		return su_add_time;
	}

	public void setSu_add_time(Date su_add_time) {
		putField("su_add_time");
		this.su_add_time = su_add_time;
	}

	public String getSo_code() {
        return so_code;
    }

    public String getSo_name() {
        return so_name;
    }

    public String getSo_parentid() {
        return so_parentid;
    }

    public String getSo_purview() {
        return so_purview;
    }

    public String getSr_id() {
        return sr_id;
    }

    public String getSr_code() {
        return sr_code;
    }

    public String getSr_name() {
        return sr_name;
    }

    public String getSap_id() {
        return sap_id;
    }
}

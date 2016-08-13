package com.rogrand.sys.domain;

import com.rogrand.core.domain.Base;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：用户角色
 */
public class UserRole extends Base {
    private String sur_id;
    private String su_id;
    private String sr_id;

    public UserRole() {
        super();
        this.putComment("tableComment", "用户角色表");        
        this.putComment("sur_id", "用户角色ID");
        this.putComment("su_id", "用户ID");
        this.putComment("sr_id", "角色ID");
    }

    public UserRole(Long sur_id) {
        this("sur_id",sur_id);
    }

    public UserRole(String sur_id) {
        this("sur_id",sur_id);
    }

    public UserRole(String property, Object value) {
        this();
        init(property, value);
    }

    public void setSur_id(String sur_id) {
        putField("sur_id");
        this.sur_id = sur_id;
    }

    public void setSu_id(String su_id) {
        putField("su_id");
        this.su_id = su_id;
    }

    public void setSr_id(String sr_id) {
        putField("sr_id");
        this.sr_id = sr_id;
    }

    public String getSur_id() {
        return sur_id;
    }

    public String getSu_id() {
        return su_id;
    }

    public String getSr_id() {
        return sr_id;
    }
}

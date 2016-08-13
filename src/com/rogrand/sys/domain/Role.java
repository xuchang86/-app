package com.rogrand.sys.domain;

import com.rogrand.core.domain.Base;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：角色
 */
public class Role extends Base {
    private String sr_id;
    private String sr_code;
    private String sr_name;
    private String sr_status;

    private String sap_id;


    public Role() {
        super();
        this.putComment("tableComment", "角色表");
        this.putComment("sr_id", "角色ID");
        this.putComment("sr_code", "角色编码");
        this.putComment("sr_name", "角色名称");
        this.putComment("sr_status", "审核状态");
        this.putComment("sap_id", "系统ID");
    }

    public Role(Long sr_id) {
        this("sr_id",sr_id);
    }

    public Role(String sr_id){
        this("sr_id",sr_id);
    }

    public Role(String property, Object value) {
        this();
        init(property, value);
    }

    public String getSr_id() {
        return sr_id;
    }

    public void setSr_id(String sr_id) {
        putField("sr_id");
        this.sr_id = sr_id;
    }

    public String getSr_code() {
        return sr_code;
    }

    public void setSr_code(String sr_code) {
        putField("sr_code");
        this.sr_code = sr_code;
    }

    public String getSr_name() {
        return sr_name;
    }

    public void setSr_name(String sr_name) {
        putField("sr_name");
        this.sr_name = sr_name;
    }

    public String getSr_status() {
        return sr_status;
    }

    public void setSr_status(String sr_status) {
        putField("sr_status");
        this.sr_status = sr_status;
    }

    public String getSap_id() {
        return sap_id;
    }

    public void setSap_id(String sap_id) {
        putField("sap_id");
        this.sap_id = sap_id;
    }
}

package com.rogrand.sys.domain;

import com.rogrand.core.domain.Base;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：角色菜单
 */
public class RoleMenu extends Base {
    private String srm_id;
    private String sr_id;
    private String sm_id;

    public RoleMenu() {
        super();
        this.putComment("tableComment", "角色菜单表");
        this.putComment("srm_id", "角色菜单主键");
        this.putComment("sr_id", "角色ID");
        this.putComment("sm_id", "菜单ID");
        this.putComment("sap_id", "系统ID");
    }

    public RoleMenu(Long srm_id) {
        this("srm_id",srm_id);
    }

    public RoleMenu(String srm_id) {
        this("srm_id",srm_id);
    }

    public RoleMenu(String mode, Object value) {
        this();
        init(mode, value);
    }

    public void setSrm_id(String srm_id) {
        putField("srm_id");
        this.srm_id = srm_id;
    }

    public void setSr_id(String sr_id) {
        putField("sr_id");
        this.sr_id = sr_id;
    }

    public void setSm_id(String sm_id) {
        putField("sm_id");
        this.sm_id = sm_id;
    }

    public String getSrm_id() {
        return srm_id;
    }

    public String getSr_id() {
        return sr_id;
    }

    public String getSm_id() {
        return sm_id;
    }

}

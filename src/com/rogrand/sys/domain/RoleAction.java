package com.rogrand.sys.domain;

import com.rogrand.core.domain.Base;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：角色动作
 */
public class RoleAction extends Base {
    private String sra_id;
    private String sr_id;
    private String sm_id;
    private String sa_id;

    public RoleAction() {
        super();
        this.putComment("tableComment", "角色动作表");
        this.putComment("sra_id", "主键");
        this.putComment("sr_id", "角色ID");
        this.putComment("sm_id", "菜单ID");
        this.putComment("sa_id", "功能ID");
    }

    public RoleAction(Long sra_id) {
        this("sra_id",sra_id);
    }

    public RoleAction(String sra_id) {
        this("sra_id",sra_id);
    }

    public RoleAction(String property, Object value) {
        this();
        init(property, value);
    }

    public void setSra_id(String sra_id) {
        putField("sra_id");
        this.sra_id = sra_id;
    }

    public void setSr_id(String sr_id) {
        putField("sr_id");
        this.sr_id = sr_id;
    }

    public void setSm_id(String sm_id) {
        putField("sm_id");
        this.sm_id = sm_id;
    }

    public void setSa_id(String sa_id) {
        putField("sa_id");
        this.sa_id = sa_id;
    }

    public String getSra_id() {
        return sra_id;
    }

    public String getSr_id() {
        return sr_id;
    }

    public String getSm_id() {
        return sm_id;
    }

    public String getSa_id() {
        return sa_id;
    }
}

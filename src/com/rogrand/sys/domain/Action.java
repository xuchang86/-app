package com.rogrand.sys.domain;

import com.rogrand.core.domain.Base;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：菜单
 */
public class Action extends Base {
    private String sa_id;
    private String sm_id;
    private String sa_class;
    private String sa_group;

    public Action() {
        super();
        this.putComment("tableComment", "菜单功能表");
        this.putComment("sa_id", "菜单功能ID");
        this.putComment("sm_id", "菜单ID");
        this.putComment("sa_class", "菜单类");
        this.putComment("sa_group", "功能分组");
    }

    public Action(Long sa_id) {
        this("sa_id",sa_id);
    }

    public Action(String sa_id) {
        this("sa_id",sa_id);
    }

    public Action(String property, Object value) {
        this();
        init(property, value);
    }

    public void setSa_id(String sa_id) {
        putField("sa_id");
        this.sa_id = sa_id;
    }

    public void setSm_id(String sm_id) {
        putField("sm_id");
        this.sm_id = sm_id;
    }

    public void setSa_class(String sa_class) {
        putField("sa_class");
        this.sa_class = sa_class;
    }

    public void setSa_group(String sa_group) {
        putField("sa_group");
        this.sa_group = sa_group;
    }

    public String getSa_id() {
        return sa_id;
    }

    public String getSm_id() {
        return sm_id;
    }

    public String getSa_class() {
        return sa_class;
    }

    public String getSa_group() {
        return sa_group;
    }



}

package com.rogrand.sys.domain;

import com.rogrand.core.annotation.FieldAnnotation;
import com.rogrand.core.domain.Base;
import java.util.Date;

/**
 * 版权：融贯资讯 <br/>
 * 作者：@rogrand.com <br/>
 * 生成日期：2013-12-17 <br/>
 * 描述：系统配置类
 */
public class Sysconfig extends Base {
    
    private static final long serialVersionUID = -9190650837389559744L;

    @FieldAnnotation(comment = "配置ID", exp = false, imp = false, empty = true, len = 10, pk = true)
    private Long configid;
    
    @FieldAnnotation(comment = "配置KEY", exp = true, imp = true, empty = true, len = 30)
    private String configkey;
    
    @FieldAnnotation(comment = "配置值", exp = true, imp = true, empty = true, len = 10)
    private String configvalue;
    
    @FieldAnnotation(comment = "配置描述", exp = true, imp = true, empty = true, len = 200)
    private String configdesc;
    
    @FieldAnnotation(comment = "配置时间", exp = true, imp = true, empty = true)
    private Date configaddtime;
    
    @FieldAnnotation(comment = "配置类型 0-用户配置 1-系统配置", exp = true, imp = true, empty = true)
    private Long configtype;
    
    /**
     * 系统配置对象构造函数
     */
    public Sysconfig() {
        super();
    }
    
    public Sysconfig(String configid) {
        this("configid", configid);
    }
    
    public Sysconfig(String property, Object value) {
        this();
        init(property, value);
    }
    
    /**
     * 获得配置ID
     * @return Long
     */
    public Long getConfigid() {
        return this.configid;
    }
    
    /**
     * 设置配置ID
     * @param configid  配置ID
     */
    public void setConfigid(Long configid) {
        putField("configid");
        this.configid = configid;
    }
    
    /**
     * 获得配置KEY
     * @return String
     */
    public String getConfigkey() {
        return this.configkey;
    }
    
    /**
     * 设置配置KEY
     * @param configkey  配置KEY
     */
    public void setConfigkey(String configkey) {
        putField("configkey");
        this.configkey = configkey;
    }
    
    /**
     * 获得配置值
     * @return String
     */
    public String getConfigvalue() {
        return this.configvalue;
    }
    
    /**
     * 设置配置值
     * @param configvalue  配置值
     */
    public void setConfigvalue(String configvalue) {
        putField("configvalue");
        this.configvalue = configvalue;
    }
    
    /**
     * 获得配置描述
     * @return String
     */
    public String getConfigdesc() {
        return this.configdesc;
    }
    
    /**
     * 设置配置描述
     * @param configdesc  配置描述
     */
    public void setConfigdesc(String configdesc) {
        putField("configdesc");
        this.configdesc = configdesc;
    }
    
    /**
     * 获得配置时间
     * @return Date
     */
    public Date getConfigaddtime() {
        return this.configaddtime;
    }
    
    /**
     * 设置配置时间
     * @param configaddtime  配置时间
     */
    public void setConfigaddtime(Date configaddtime) {
        putField("configaddtime");
        this.configaddtime = configaddtime;
    }
    
    /**
     * 获取配置类型
     */
    public Long getConfigtype() {
        return configtype;
    }
    
    /**
     * 设置配置类型
     * @param configtype  配置类型
     */
    public void setConfigtype(Long configtype) {
        putField("configtype");
        this.configtype = configtype;
    }
}

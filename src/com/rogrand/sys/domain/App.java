package com.rogrand.sys.domain;

import com.rogrand.core.domain.Base;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：应用系统
 */
public class App extends Base {

    private String sap_id;  //应用系统ID
    private String sap_code;  //应用系统编号
    private String sap_name;  //应用系统名称

    /**
     * 应用系统表对象构造函数
     */
    public App() {
        super();
        this.putComment("tableComment", "应用系统表");
        this.putComment("sap_id", "应用系统ID");
        this.putComment("sap_code", "应用系统编号");
        this.putComment("sap_name", "应用系统名称");
    }

    public App(Long sap_id) {
        this("sap_id",sap_id);
    }

    public App(String sap_id) {
        this("sap_id",sap_id);
    }

    public App(String property, Object value) {
        this();
        init(property, value);
    }

    /**
    * 设置应用系统ID
    */
    public void setSap_id(String sap_id){
        putField("sap_id");
        this.sap_id = sap_id;
    }

    /**
    * 获得应用系统ID
    */
    public String getSap_id(){
        return this.sap_id;
    }
    
    /**
    * 设置应用系统编号
    */
    public void setSap_code(String sap_code){
        putField("sap_code");
        this.sap_code = sap_code;
    }

    /**
    * 获得应用系统编号
    */
    public String getSap_code(){
        return this.sap_code;
    }
    
    /**
    * 设置应用系统名称
    */
    public void setSap_name(String sap_name){
        putField("sap_name");
        this.sap_name = sap_name;
    }

    /**
    * 获得应用系统名称
    */
    public String getSap_name(){
        return this.sap_name;
    }
    

}




package com.rogrand.core.json;

/**
 * 版权：融贯资讯 <br/>
 * 作者：kai.gao@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：前后端JSON对象公用Head部分
 */
public class Head {

    /**
     * serialNumber : <交易序列号>
     */
    private String serialNumber;

    /**
     * method : <交易方法>
     */
    private String method;

    /**
     * version : <交易方法版本号>
     */
    private String version;

    /**
     * terminalstate : <终端类型>
     * 
     * 0-安卓 10-用户版appstore(个人) 11-用户版inhouse(企业) 12-商户版appstore(个人)
     * 13-商户版inhouse(企业)
     */

    private int terminalstate;

    /**
     * sysVersion : <终端系统版本号>
     */
    private String sysVersion;

    /**
     * imei : <设备IME码>
     */
    private String imei;

    /**
     * appType : <APP类型>
     * 
     * 1-用户版 2-商户版
     */
    private int appType;

    /**
     * appVersion : <APP版本号>
     */
    private String appVersion;

    public Head() {
    }

    public Head(String method, String serialNumber, String version) {
        this.method = method;
        this.serialNumber = serialNumber;
        this.version = version;
    }

    public Head(String method, String serialNumber, String version, int terminalstate, String sysVersion, String imei,
            int appType) {
        this.method = method;
        this.serialNumber = serialNumber;
        this.version = version;
        this.terminalstate = terminalstate;
        this.sysVersion = sysVersion;
        this.imei = imei;
        this.appType = appType;
    }

    public Head(String method, String serialNumber, String version, int terminalstate, String sysVersion, String imei,
            int appType, String appVersion) {
        this.method = method;
        this.serialNumber = serialNumber;
        this.version = version;
        this.terminalstate = terminalstate;
        this.sysVersion = sysVersion;
        this.imei = imei;
        this.appType = appType;
        this.appVersion = appVersion;
    }

    public String toString() {
        return "serialNumber:" + serialNumber + ",method:" + method + ",version:" + version + ",terminalstate:"
                + terminalstate + ",sysVersion:" + sysVersion + ",imei:" + imei + ",appType:" + appType
                + ",appVersion:" + appVersion;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getTerminalstate() {
        return terminalstate;
    }

    public void setTerminalstate(int terminalstate) {
        this.terminalstate = terminalstate;
    }

    public String getSysVersion() {
        return sysVersion;
    }

    public void setSysVersion(String sysVersion) {
        this.sysVersion = sysVersion;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public int getAppType() {
        return appType;
    }

    public void setAppType(int appType) {
        this.appType = appType;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

}

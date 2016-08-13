package com.rogrand.core.jms.simplejms;

import java.io.Serializable;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xuan.zhou@rogrand.com <br/>
 * 生成日期：2014-3-27 <br/>
 * 描述：〈推送消息到集群的Web节点〉
 */
public class WebNode implements Serializable {

    private static final long serialVersionUID = -3290325399607255390L;

    private int id;

    private String protocol;
    private String host;
    private int port;
    private String app;

    public String toString() {
        return protocol + "://" + host + ":" + port + "/" + app;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}

package com.rogrand.core.json;

import java.io.Serializable;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：kai.gao@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：用户后台向前台返回的JSON对象
 */
public class ResponseJSON implements Serializable {
	
	private static final long serialVersionUID = -1173634853444152081L;
	
	private String mac;
	private Head head;
	private Body body;
	
	public String getMac() {
		return mac;
	}
	
	public void setMac(String mac) {
		this.mac = mac;
	}
	
	public Head getHead() {
		return head;
	}
	
	public void setHead(Head head) {
		this.head = head;
	}
	
	public Body getBody() {
		return body;
	}
	
	public void setBody(Body body) {
		this.body = body;
	}
	
	public String toString() {
		return "mac:" + mac + ",head:" + head + ",body:" + body;
	}
	
}

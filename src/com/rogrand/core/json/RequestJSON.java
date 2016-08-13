package com.rogrand.core.json;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：kai.gao@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：用户前台向后台传递的JSON对象
 */
public class RequestJSON implements Serializable {
	
	private static final long serialVersionUID = -1173634853444152081L;
	
	private String mac;
	private Head head;
	private JSONObject body;
	
	public String toString() {
		return "{mac:" + mac + ",head:" + head + ",body:" + body + "}";
	}
	
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
	
	public JSONObject getBody() {
		return body;
	}
	
	public void setBody(JSONObject body) {
		this.body = body;
	}
	
	public <T> T getYourObject(Class<T> clazz) {
		return JSON.parseObject(getBody().toJSONString(), clazz);
	}
	
}

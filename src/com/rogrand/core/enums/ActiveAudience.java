package com.rogrand.core.enums;


/**
 * 版权：融贯资讯 <br/>
 * 作者：yong.chen@rogrand.com <br/>
 * 生成日期：2014-3-4 <br/>
 * 描述：〈描述〉
 */

public enum ActiveAudience implements BaseEnum<Integer> {
	USER(1, "用户"), CHAIN(3, "连锁药房分店"),SHOP(4,"单体药房");
	private Integer code;
	private String desc;
	
	private ActiveAudience(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	
	public Integer getCode() {
		return code;
	}

	
	public void setCode(Integer code) {
		this.code = code;
	}

	
	public String getDesc() {
		return desc;
	}

	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}

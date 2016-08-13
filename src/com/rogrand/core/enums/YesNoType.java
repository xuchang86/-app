package com.rogrand.core.enums;


/**
 * 版权：融贯资讯 <br/>
 * 作者：yong.chen@rogrand.com <br/>
 * 生成日期：2014-2-26 <br/>
 * 描述：〈描述〉
 */

public enum YesNoType implements BaseEnum<Integer> {
	YES(1,"是"),NO(0,"否");
	
	private Integer code;
	private String desc;
	
	private YesNoType(Integer code,String desc){
		this.code = code;
		this.desc = desc;
	}
	@Override
	public Integer getCode() {
		return this.code;
	}
	@Override
	public String getDesc() {
		return this.desc;
	}
	
}

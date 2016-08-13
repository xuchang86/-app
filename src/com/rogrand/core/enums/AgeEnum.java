package com.rogrand.core.enums;


/**
 * 版权：融贯资讯 <br/>
 * 作者：yong.chen@rogrand.com <br/>
 * 生成日期：2014-3-13 <br/>
 * 描述：〈描述〉
 */

public enum AgeEnum implements BaseEnum<String> {
	AGE0("0","16岁以下"),
	AGE1("1","16-24岁"),
	AGE2("2","25-30岁"),
	AGE3("3","31-40岁"),
	AGE4("4","41-50岁"),
	AGE5("5","51-60岁"),
	AGE6("6","61-70岁"),
	AGE7("7","71-80岁");

	private String code;
	private String desc;
	
	private AgeEnum(String code,String desc){
		this.code = code;
		this.desc = desc;
	}
	@Override
	public String getCode() {
		return code;
	}

	
	@Override
	public String getDesc() {
		return desc;
	}
	
}

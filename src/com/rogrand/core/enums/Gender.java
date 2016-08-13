package com.rogrand.core.enums;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xuan.zhou@rogrand.com <br/>
 * 生成日期：2013-11-21 <br/>
 * 描述：性别枚举
 */
public enum Gender implements BaseEnum<String> {

	MALE("B", "男"), FEMALE("A", "女");

	private String code;
	private String desc;

	private Gender(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	@Override
	public String getCode() {
		return this.code;
	}

	@Override
	public String getDesc() {
		return this.desc;
	}

}

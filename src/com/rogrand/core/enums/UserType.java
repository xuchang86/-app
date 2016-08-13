package com.rogrand.core.enums;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xuan.zhou@rogrand.com <br/>
 * 生成日期：2013-11-26 <br/>
 * 描述：用户类型枚举
 */
public enum UserType implements BaseEnum<Long> {

	USER(0l, "用户"), PHARMACY(1l, "药店"), COMMUNITY_CLINIC(2l, "社区诊所");

	private Long code;
	private String desc;

	private UserType(Long code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	@Override
	public Long getCode() {
		return this.code;
	}

	@Override
	public String getDesc() {
		return this.desc;
	}

}

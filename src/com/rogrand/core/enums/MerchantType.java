package com.rogrand.core.enums;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xuan.zhou@rogrand.com <br/>
 * 生成日期：2013-11-22 <br/>
 * 描述：商户类型枚举
 */
public enum MerchantType implements BaseEnum<Long> {

	PHARMACY(1l, "药店"), COMMUNITY_CLINIC(2l, "社区诊所");

	private Long code;
	private String desc;

	private MerchantType(Long code, String desc) {
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

package com.rogrand.core.enums;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xuan.zhou@rogrand.com <br/>
 * 生成日期：2013-11-22 <br/>
 * 描述：商户状态枚举
 */
public enum MerchantStatus implements BaseEnum<Long> {

	INIT(1l, "待审核"), PASSED(2l, "已认证"), REJECT(3l, "已驳回");

	private Long code;
	private String desc;

	private MerchantStatus(Long code, String desc) {
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

package com.rogrand.core.enums;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xuan.zhou@rogrand.com <br/>
 * 生成日期：2013-11-25 <br/>
 * 描述：服务类型枚举
 */
public enum ServiceCator implements BaseEnum<Long> {
	
	IMMEDIATE(1l, "买药服务"), BOOKING(2l, "问诊服务");
	
	private Long code;
	private String desc;

	private ServiceCator(Long code, String desc) {
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

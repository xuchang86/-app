package com.rogrand.core.enums;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xuan.zhou@rogrand.com <br/>
 * 生成日期：2013-11-21 <br/>
 * 描述：用户终端类型枚举
 */
public enum TerminalType implements BaseEnum<Long> {

	ANDROID(0l, "Android"), IOS(1l, "IOS"), PCWEB(3l, "PC-WEB");

	private Long code;
	private String desc;

	private TerminalType(Long code, String desc) {
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

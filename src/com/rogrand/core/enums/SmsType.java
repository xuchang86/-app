package com.rogrand.core.enums;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xuan.zhou@rogrand.com <br/>
 * 生成日期：2013-12-12 <br/>
 * 描述：短信类型枚举
 */
public enum SmsType implements BaseEnum<Long> {

	VALID(1l, "验证码短信"), AUDIT(2l, "审核提示短信"), CUSTOM(3l, "用户自定义短信"), NOTICE(4l, "群发通知");

	private Long code;
	private String desc;

	private SmsType(Long code, String desc) {
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

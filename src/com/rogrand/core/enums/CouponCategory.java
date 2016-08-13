package com.rogrand.core.enums;

public class CouponCategory implements BaseEnum<String>{
	
	
	private Long code;
	private String desc;
	
	private CouponCategory(Long code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getDesc() {
		// TODO Auto-generated method stub
		return null;
	}
}

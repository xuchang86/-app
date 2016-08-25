/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.xiaoyao.base.model;

/**
 * 逍遥派等级
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年8月25日 许畅 新建
 */
public enum Level {

	/** 见习弟子 */
	JIAN_XI_DIZI(1),

	/** 精英弟子 */
	JING_YING_DIZI(2),

	/** 副组长 */
	FU_ZU_ZHANG(3),

	/** 组长 */
	ZU_ZHANG(4),

	/** 副队长 */
	FU_DUI_ZHANG(5),

	/** 队长 */
	DUI_ZHANG(6),

	/** 副堂主 */
	FU_TANG_ZHU(7),

	/** 堂主 */
	TANG_ZHU(8),

	/** 副舵主 */
	FU_DUO_ZHU(9),

	/** 舵主 */
	DUO_ZHU(10),

	/** 青龙护法 */
	QING_LONG_HU_FA(11),

	/** 白虎护法 */
	BAI_HU_HU_FA(12),

	/** 朱雀护法 */
	ZHU_QUE_HU_FA(13),

	/** 玄武护法 */
	XUAN_WU_HU_FA(14),

	/** 逍遥左使 */
	XIAO_YAO_ZUO_SHI(15),

	/** 逍遥右使 */
	XIAO_YAO_YOU_SHI(16),

	/** 大长老 */
	DA_ZHANG_LAO(17),

	/** 副掌门 */
	FU_ZHANG_MENG(18);

	private Integer value;

	private Level(Integer value) {
		this.setValue(value);
	}

	/**
	 * @return the value
	 */
	public Integer getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(Integer value) {
		this.value = value;
	}

}

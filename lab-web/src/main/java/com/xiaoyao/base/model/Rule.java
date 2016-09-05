package com.xiaoyao.base.model;

import java.math.BigDecimal;

/**
 * 会员成长规则
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年8月21日 许畅 新建
 */
public class Rule {
	private Integer id;

	/** 级别{@com.xiaoyao.base.model.Level} */
	private Integer level = Level.JIAN_XI_DIZI.getValue();

	private Integer levelCount;

	private BigDecimal moneyPool;

	private BigDecimal packet;

	private Integer packetCount;

	private BigDecimal childPacket;

	private Integer childPacketCount;

	private BigDecimal upgradeAwards;

	private BigDecimal memberIncome;

	private BigDecimal platformIncome;

	private String remark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getLevelCount() {
		return levelCount;
	}

	public void setLevelCount(Integer levelCount) {
		this.levelCount = levelCount;
	}

	public BigDecimal getMoneyPool() {
		return moneyPool;
	}

	public void setMoneyPool(BigDecimal moneyPool) {
		this.moneyPool = moneyPool;
	}

	public BigDecimal getPacket() {
		return packet;
	}

	public void setPacket(BigDecimal packet) {
		this.packet = packet;
	}

	public Integer getPacketCount() {
		return packetCount;
	}

	public void setPacketCount(Integer packetCount) {
		this.packetCount = packetCount;
	}

	public BigDecimal getChildPacket() {
		return childPacket;
	}

	public void setChildPacket(BigDecimal childPacket) {
		this.childPacket = childPacket;
	}

	public Integer getChildPacketCount() {
		return childPacketCount;
	}

	public void setChildPacketCount(Integer childPacketCount) {
		this.childPacketCount = childPacketCount;
	}

	public BigDecimal getUpgradeAwards() {
		return upgradeAwards;
	}

	public void setUpgradeAwards(BigDecimal upgradeAwards) {
		this.upgradeAwards = upgradeAwards;
	}

	public BigDecimal getMemberIncome() {
		return memberIncome;
	}

	public void setMemberIncome(BigDecimal memberIncome) {
		this.memberIncome = memberIncome;
	}

	public BigDecimal getPlatformIncome() {
		return platformIncome;
	}

	public void setPlatformIncome(BigDecimal platformIncome) {
		this.platformIncome = platformIncome;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}
}
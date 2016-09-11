package com.xiaoyao.base.model;

import java.math.BigDecimal;

/**
 * 逍遥派会员升级规则
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年9月11日 许畅 新建
 */
public class Rule {

	/** 主键 */
	private Integer id;

	/** 级别{@com.xiaoyao.base.model.Level} */
	private Integer level = Level.JIAN_XI_DIZI.getValue();

	/** 收徒升级人数 */
	private Integer levelCount;

	/** 资金池收入 */
	private BigDecimal moneyPool;

	/** 徒弟给师傅红包数 */
	private BigDecimal packet;

	private Integer packetCount;

	/** 徒孙给师傅红包数 */
	private BigDecimal childPacket;

	private Integer childPacketCount;

	/** 升级奖金 */
	private BigDecimal upgradeAwards;

	/** 会员收入 */
	private BigDecimal memberIncome;

	/** 平台收入 */
	private BigDecimal platformIncome;

	/** 弟子总数 */
	private Integer totalChild;

	/** 备注 */
	private String remark;

	/**
	 * 主键
	 * 
	 * @return
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 主键
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 等级
	 * 
	 * @return
	 */
	public Integer getLevel() {
		return level;
	}

	/**
	 * 等级
	 * 
	 * @param level
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}

	/**
	 * 收徒升级人数
	 * 
	 * @return
	 */
	public Integer getLevelCount() {
		return levelCount;
	}

	/**
	 * 收徒升级人数
	 * 
	 * @param levelCount
	 */
	public void setLevelCount(Integer levelCount) {
		this.levelCount = levelCount;
	}

	/**
	 * 资金池资金增长
	 * 
	 * @return
	 */
	public BigDecimal getMoneyPool() {
		return moneyPool;
	}

	/**
	 * 资金池资金增长
	 * 
	 * @param moneyPool
	 */
	public void setMoneyPool(BigDecimal moneyPool) {
		this.moneyPool = moneyPool;
	}

	/**
	 * 徒弟给师傅红包数
	 * 
	 * @return
	 */
	public BigDecimal getPacket() {
		return packet;
	}

	/**
	 * 徒弟给师傅红包数
	 * 
	 * @param packet
	 */
	public void setPacket(BigDecimal packet) {
		this.packet = packet;
	}

	public Integer getPacketCount() {
		return packetCount;
	}

	public void setPacketCount(Integer packetCount) {
		this.packetCount = packetCount;
	}

	/**
	 * 徒孙红包
	 * 
	 * @return
	 */
	public BigDecimal getChildPacket() {
		return childPacket;
	}

	/**
	 * 徒孙红包
	 * 
	 * @param childPacket
	 */
	public void setChildPacket(BigDecimal childPacket) {
		this.childPacket = childPacket;
	}

	public Integer getChildPacketCount() {
		return childPacketCount;
	}

	public void setChildPacketCount(Integer childPacketCount) {
		this.childPacketCount = childPacketCount;
	}

	/**
	 * 升级奖励
	 * 
	 * @return
	 */
	public BigDecimal getUpgradeAwards() {
		return upgradeAwards;
	}

	/**
	 * 升级奖励
	 * 
	 * @param upgradeAwards
	 */
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

	/**
	 * 弟子总数
	 * 
	 * @return
	 */
	public Integer getTotalChild() {
		return totalChild;
	}

	/**
	 * 弟子总数
	 * 
	 * @param totalChild
	 */
	public void setTotalChild(Integer totalChild) {
		this.totalChild = totalChild;
	}

	/**
	 * 备注
	 * 
	 * @return
	 */
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}
}
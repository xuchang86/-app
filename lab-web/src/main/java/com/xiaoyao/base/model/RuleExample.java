package com.xiaoyao.base.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RuleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RuleExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andLevelIsNull() {
            addCriterion("level is null");
            return (Criteria) this;
        }

        public Criteria andLevelIsNotNull() {
            addCriterion("level is not null");
            return (Criteria) this;
        }

        public Criteria andLevelEqualTo(Integer value) {
            addCriterion("level =", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotEqualTo(Integer value) {
            addCriterion("level <>", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThan(Integer value) {
            addCriterion("level >", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("level >=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThan(Integer value) {
            addCriterion("level <", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThanOrEqualTo(Integer value) {
            addCriterion("level <=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelIn(List<Integer> values) {
            addCriterion("level in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotIn(List<Integer> values) {
            addCriterion("level not in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelBetween(Integer value1, Integer value2) {
            addCriterion("level between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("level not between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelCountIsNull() {
            addCriterion("level_count is null");
            return (Criteria) this;
        }

        public Criteria andLevelCountIsNotNull() {
            addCriterion("level_count is not null");
            return (Criteria) this;
        }

        public Criteria andLevelCountEqualTo(Integer value) {
            addCriterion("level_count =", value, "levelCount");
            return (Criteria) this;
        }

        public Criteria andLevelCountNotEqualTo(Integer value) {
            addCriterion("level_count <>", value, "levelCount");
            return (Criteria) this;
        }

        public Criteria andLevelCountGreaterThan(Integer value) {
            addCriterion("level_count >", value, "levelCount");
            return (Criteria) this;
        }

        public Criteria andLevelCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("level_count >=", value, "levelCount");
            return (Criteria) this;
        }

        public Criteria andLevelCountLessThan(Integer value) {
            addCriterion("level_count <", value, "levelCount");
            return (Criteria) this;
        }

        public Criteria andLevelCountLessThanOrEqualTo(Integer value) {
            addCriterion("level_count <=", value, "levelCount");
            return (Criteria) this;
        }

        public Criteria andLevelCountIn(List<Integer> values) {
            addCriterion("level_count in", values, "levelCount");
            return (Criteria) this;
        }

        public Criteria andLevelCountNotIn(List<Integer> values) {
            addCriterion("level_count not in", values, "levelCount");
            return (Criteria) this;
        }

        public Criteria andLevelCountBetween(Integer value1, Integer value2) {
            addCriterion("level_count between", value1, value2, "levelCount");
            return (Criteria) this;
        }

        public Criteria andLevelCountNotBetween(Integer value1, Integer value2) {
            addCriterion("level_count not between", value1, value2, "levelCount");
            return (Criteria) this;
        }

        public Criteria andMoneyPoolIsNull() {
            addCriterion("money_pool is null");
            return (Criteria) this;
        }

        public Criteria andMoneyPoolIsNotNull() {
            addCriterion("money_pool is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyPoolEqualTo(BigDecimal value) {
            addCriterion("money_pool =", value, "moneyPool");
            return (Criteria) this;
        }

        public Criteria andMoneyPoolNotEqualTo(BigDecimal value) {
            addCriterion("money_pool <>", value, "moneyPool");
            return (Criteria) this;
        }

        public Criteria andMoneyPoolGreaterThan(BigDecimal value) {
            addCriterion("money_pool >", value, "moneyPool");
            return (Criteria) this;
        }

        public Criteria andMoneyPoolGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("money_pool >=", value, "moneyPool");
            return (Criteria) this;
        }

        public Criteria andMoneyPoolLessThan(BigDecimal value) {
            addCriterion("money_pool <", value, "moneyPool");
            return (Criteria) this;
        }

        public Criteria andMoneyPoolLessThanOrEqualTo(BigDecimal value) {
            addCriterion("money_pool <=", value, "moneyPool");
            return (Criteria) this;
        }

        public Criteria andMoneyPoolIn(List<BigDecimal> values) {
            addCriterion("money_pool in", values, "moneyPool");
            return (Criteria) this;
        }

        public Criteria andMoneyPoolNotIn(List<BigDecimal> values) {
            addCriterion("money_pool not in", values, "moneyPool");
            return (Criteria) this;
        }

        public Criteria andMoneyPoolBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("money_pool between", value1, value2, "moneyPool");
            return (Criteria) this;
        }

        public Criteria andMoneyPoolNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("money_pool not between", value1, value2, "moneyPool");
            return (Criteria) this;
        }

        public Criteria andPacketIsNull() {
            addCriterion("packet is null");
            return (Criteria) this;
        }

        public Criteria andPacketIsNotNull() {
            addCriterion("packet is not null");
            return (Criteria) this;
        }

        public Criteria andPacketEqualTo(BigDecimal value) {
            addCriterion("packet =", value, "packet");
            return (Criteria) this;
        }

        public Criteria andPacketNotEqualTo(BigDecimal value) {
            addCriterion("packet <>", value, "packet");
            return (Criteria) this;
        }

        public Criteria andPacketGreaterThan(BigDecimal value) {
            addCriterion("packet >", value, "packet");
            return (Criteria) this;
        }

        public Criteria andPacketGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("packet >=", value, "packet");
            return (Criteria) this;
        }

        public Criteria andPacketLessThan(BigDecimal value) {
            addCriterion("packet <", value, "packet");
            return (Criteria) this;
        }

        public Criteria andPacketLessThanOrEqualTo(BigDecimal value) {
            addCriterion("packet <=", value, "packet");
            return (Criteria) this;
        }

        public Criteria andPacketIn(List<BigDecimal> values) {
            addCriterion("packet in", values, "packet");
            return (Criteria) this;
        }

        public Criteria andPacketNotIn(List<BigDecimal> values) {
            addCriterion("packet not in", values, "packet");
            return (Criteria) this;
        }

        public Criteria andPacketBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("packet between", value1, value2, "packet");
            return (Criteria) this;
        }

        public Criteria andPacketNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("packet not between", value1, value2, "packet");
            return (Criteria) this;
        }

        public Criteria andPacketCountIsNull() {
            addCriterion("packet_count is null");
            return (Criteria) this;
        }

        public Criteria andPacketCountIsNotNull() {
            addCriterion("packet_count is not null");
            return (Criteria) this;
        }

        public Criteria andPacketCountEqualTo(Integer value) {
            addCriterion("packet_count =", value, "packetCount");
            return (Criteria) this;
        }

        public Criteria andPacketCountNotEqualTo(Integer value) {
            addCriterion("packet_count <>", value, "packetCount");
            return (Criteria) this;
        }

        public Criteria andPacketCountGreaterThan(Integer value) {
            addCriterion("packet_count >", value, "packetCount");
            return (Criteria) this;
        }

        public Criteria andPacketCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("packet_count >=", value, "packetCount");
            return (Criteria) this;
        }

        public Criteria andPacketCountLessThan(Integer value) {
            addCriterion("packet_count <", value, "packetCount");
            return (Criteria) this;
        }

        public Criteria andPacketCountLessThanOrEqualTo(Integer value) {
            addCriterion("packet_count <=", value, "packetCount");
            return (Criteria) this;
        }

        public Criteria andPacketCountIn(List<Integer> values) {
            addCriterion("packet_count in", values, "packetCount");
            return (Criteria) this;
        }

        public Criteria andPacketCountNotIn(List<Integer> values) {
            addCriterion("packet_count not in", values, "packetCount");
            return (Criteria) this;
        }

        public Criteria andPacketCountBetween(Integer value1, Integer value2) {
            addCriterion("packet_count between", value1, value2, "packetCount");
            return (Criteria) this;
        }

        public Criteria andPacketCountNotBetween(Integer value1, Integer value2) {
            addCriterion("packet_count not between", value1, value2, "packetCount");
            return (Criteria) this;
        }

        public Criteria andChildPacketIsNull() {
            addCriterion("child_packet is null");
            return (Criteria) this;
        }

        public Criteria andChildPacketIsNotNull() {
            addCriterion("child_packet is not null");
            return (Criteria) this;
        }

        public Criteria andChildPacketEqualTo(BigDecimal value) {
            addCriterion("child_packet =", value, "childPacket");
            return (Criteria) this;
        }

        public Criteria andChildPacketNotEqualTo(BigDecimal value) {
            addCriterion("child_packet <>", value, "childPacket");
            return (Criteria) this;
        }

        public Criteria andChildPacketGreaterThan(BigDecimal value) {
            addCriterion("child_packet >", value, "childPacket");
            return (Criteria) this;
        }

        public Criteria andChildPacketGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("child_packet >=", value, "childPacket");
            return (Criteria) this;
        }

        public Criteria andChildPacketLessThan(BigDecimal value) {
            addCriterion("child_packet <", value, "childPacket");
            return (Criteria) this;
        }

        public Criteria andChildPacketLessThanOrEqualTo(BigDecimal value) {
            addCriterion("child_packet <=", value, "childPacket");
            return (Criteria) this;
        }

        public Criteria andChildPacketIn(List<BigDecimal> values) {
            addCriterion("child_packet in", values, "childPacket");
            return (Criteria) this;
        }

        public Criteria andChildPacketNotIn(List<BigDecimal> values) {
            addCriterion("child_packet not in", values, "childPacket");
            return (Criteria) this;
        }

        public Criteria andChildPacketBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("child_packet between", value1, value2, "childPacket");
            return (Criteria) this;
        }

        public Criteria andChildPacketNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("child_packet not between", value1, value2, "childPacket");
            return (Criteria) this;
        }

        public Criteria andChildPacketCountIsNull() {
            addCriterion("child_packet_count is null");
            return (Criteria) this;
        }

        public Criteria andChildPacketCountIsNotNull() {
            addCriterion("child_packet_count is not null");
            return (Criteria) this;
        }

        public Criteria andChildPacketCountEqualTo(Integer value) {
            addCriterion("child_packet_count =", value, "childPacketCount");
            return (Criteria) this;
        }

        public Criteria andChildPacketCountNotEqualTo(Integer value) {
            addCriterion("child_packet_count <>", value, "childPacketCount");
            return (Criteria) this;
        }

        public Criteria andChildPacketCountGreaterThan(Integer value) {
            addCriterion("child_packet_count >", value, "childPacketCount");
            return (Criteria) this;
        }

        public Criteria andChildPacketCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("child_packet_count >=", value, "childPacketCount");
            return (Criteria) this;
        }

        public Criteria andChildPacketCountLessThan(Integer value) {
            addCriterion("child_packet_count <", value, "childPacketCount");
            return (Criteria) this;
        }

        public Criteria andChildPacketCountLessThanOrEqualTo(Integer value) {
            addCriterion("child_packet_count <=", value, "childPacketCount");
            return (Criteria) this;
        }

        public Criteria andChildPacketCountIn(List<Integer> values) {
            addCriterion("child_packet_count in", values, "childPacketCount");
            return (Criteria) this;
        }

        public Criteria andChildPacketCountNotIn(List<Integer> values) {
            addCriterion("child_packet_count not in", values, "childPacketCount");
            return (Criteria) this;
        }

        public Criteria andChildPacketCountBetween(Integer value1, Integer value2) {
            addCriterion("child_packet_count between", value1, value2, "childPacketCount");
            return (Criteria) this;
        }

        public Criteria andChildPacketCountNotBetween(Integer value1, Integer value2) {
            addCriterion("child_packet_count not between", value1, value2, "childPacketCount");
            return (Criteria) this;
        }

        public Criteria andUpgradeAwardsIsNull() {
            addCriterion("upgrade_awards is null");
            return (Criteria) this;
        }

        public Criteria andUpgradeAwardsIsNotNull() {
            addCriterion("upgrade_awards is not null");
            return (Criteria) this;
        }

        public Criteria andUpgradeAwardsEqualTo(BigDecimal value) {
            addCriterion("upgrade_awards =", value, "upgradeAwards");
            return (Criteria) this;
        }

        public Criteria andUpgradeAwardsNotEqualTo(BigDecimal value) {
            addCriterion("upgrade_awards <>", value, "upgradeAwards");
            return (Criteria) this;
        }

        public Criteria andUpgradeAwardsGreaterThan(BigDecimal value) {
            addCriterion("upgrade_awards >", value, "upgradeAwards");
            return (Criteria) this;
        }

        public Criteria andUpgradeAwardsGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("upgrade_awards >=", value, "upgradeAwards");
            return (Criteria) this;
        }

        public Criteria andUpgradeAwardsLessThan(BigDecimal value) {
            addCriterion("upgrade_awards <", value, "upgradeAwards");
            return (Criteria) this;
        }

        public Criteria andUpgradeAwardsLessThanOrEqualTo(BigDecimal value) {
            addCriterion("upgrade_awards <=", value, "upgradeAwards");
            return (Criteria) this;
        }

        public Criteria andUpgradeAwardsIn(List<BigDecimal> values) {
            addCriterion("upgrade_awards in", values, "upgradeAwards");
            return (Criteria) this;
        }

        public Criteria andUpgradeAwardsNotIn(List<BigDecimal> values) {
            addCriterion("upgrade_awards not in", values, "upgradeAwards");
            return (Criteria) this;
        }

        public Criteria andUpgradeAwardsBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("upgrade_awards between", value1, value2, "upgradeAwards");
            return (Criteria) this;
        }

        public Criteria andUpgradeAwardsNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("upgrade_awards not between", value1, value2, "upgradeAwards");
            return (Criteria) this;
        }

        public Criteria andMemberIncomeIsNull() {
            addCriterion("member_income is null");
            return (Criteria) this;
        }

        public Criteria andMemberIncomeIsNotNull() {
            addCriterion("member_income is not null");
            return (Criteria) this;
        }

        public Criteria andMemberIncomeEqualTo(BigDecimal value) {
            addCriterion("member_income =", value, "memberIncome");
            return (Criteria) this;
        }

        public Criteria andMemberIncomeNotEqualTo(BigDecimal value) {
            addCriterion("member_income <>", value, "memberIncome");
            return (Criteria) this;
        }

        public Criteria andMemberIncomeGreaterThan(BigDecimal value) {
            addCriterion("member_income >", value, "memberIncome");
            return (Criteria) this;
        }

        public Criteria andMemberIncomeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("member_income >=", value, "memberIncome");
            return (Criteria) this;
        }

        public Criteria andMemberIncomeLessThan(BigDecimal value) {
            addCriterion("member_income <", value, "memberIncome");
            return (Criteria) this;
        }

        public Criteria andMemberIncomeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("member_income <=", value, "memberIncome");
            return (Criteria) this;
        }

        public Criteria andMemberIncomeIn(List<BigDecimal> values) {
            addCriterion("member_income in", values, "memberIncome");
            return (Criteria) this;
        }

        public Criteria andMemberIncomeNotIn(List<BigDecimal> values) {
            addCriterion("member_income not in", values, "memberIncome");
            return (Criteria) this;
        }

        public Criteria andMemberIncomeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("member_income between", value1, value2, "memberIncome");
            return (Criteria) this;
        }

        public Criteria andMemberIncomeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("member_income not between", value1, value2, "memberIncome");
            return (Criteria) this;
        }

        public Criteria andPlatformIncomeIsNull() {
            addCriterion("platform_income is null");
            return (Criteria) this;
        }

        public Criteria andPlatformIncomeIsNotNull() {
            addCriterion("platform_income is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformIncomeEqualTo(BigDecimal value) {
            addCriterion("platform_income =", value, "platformIncome");
            return (Criteria) this;
        }

        public Criteria andPlatformIncomeNotEqualTo(BigDecimal value) {
            addCriterion("platform_income <>", value, "platformIncome");
            return (Criteria) this;
        }

        public Criteria andPlatformIncomeGreaterThan(BigDecimal value) {
            addCriterion("platform_income >", value, "platformIncome");
            return (Criteria) this;
        }

        public Criteria andPlatformIncomeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("platform_income >=", value, "platformIncome");
            return (Criteria) this;
        }

        public Criteria andPlatformIncomeLessThan(BigDecimal value) {
            addCriterion("platform_income <", value, "platformIncome");
            return (Criteria) this;
        }

        public Criteria andPlatformIncomeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("platform_income <=", value, "platformIncome");
            return (Criteria) this;
        }

        public Criteria andPlatformIncomeIn(List<BigDecimal> values) {
            addCriterion("platform_income in", values, "platformIncome");
            return (Criteria) this;
        }

        public Criteria andPlatformIncomeNotIn(List<BigDecimal> values) {
            addCriterion("platform_income not in", values, "platformIncome");
            return (Criteria) this;
        }

        public Criteria andPlatformIncomeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("platform_income between", value1, value2, "platformIncome");
            return (Criteria) this;
        }

        public Criteria andPlatformIncomeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("platform_income not between", value1, value2, "platformIncome");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
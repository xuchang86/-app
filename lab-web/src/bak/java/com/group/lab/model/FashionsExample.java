package com.group.lab.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FashionsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FashionsExample() {
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

        public Criteria andNumIidIsNull() {
            addCriterion("num_iid is null");
            return (Criteria) this;
        }

        public Criteria andNumIidIsNotNull() {
            addCriterion("num_iid is not null");
            return (Criteria) this;
        }

        public Criteria andNumIidEqualTo(String value) {
            addCriterion("num_iid =", value, "numIid");
            return (Criteria) this;
        }

        public Criteria andNumIidNotEqualTo(String value) {
            addCriterion("num_iid <>", value, "numIid");
            return (Criteria) this;
        }

        public Criteria andNumIidGreaterThan(String value) {
            addCriterion("num_iid >", value, "numIid");
            return (Criteria) this;
        }

        public Criteria andNumIidGreaterThanOrEqualTo(String value) {
            addCriterion("num_iid >=", value, "numIid");
            return (Criteria) this;
        }

        public Criteria andNumIidLessThan(String value) {
            addCriterion("num_iid <", value, "numIid");
            return (Criteria) this;
        }

        public Criteria andNumIidLessThanOrEqualTo(String value) {
            addCriterion("num_iid <=", value, "numIid");
            return (Criteria) this;
        }

        public Criteria andNumIidLike(String value) {
            addCriterion("num_iid like", value, "numIid");
            return (Criteria) this;
        }

        public Criteria andNumIidNotLike(String value) {
            addCriterion("num_iid not like", value, "numIid");
            return (Criteria) this;
        }

        public Criteria andNumIidIn(List<String> values) {
            addCriterion("num_iid in", values, "numIid");
            return (Criteria) this;
        }

        public Criteria andNumIidNotIn(List<String> values) {
            addCriterion("num_iid not in", values, "numIid");
            return (Criteria) this;
        }

        public Criteria andNumIidBetween(String value1, String value2) {
            addCriterion("num_iid between", value1, value2, "numIid");
            return (Criteria) this;
        }

        public Criteria andNumIidNotBetween(String value1, String value2) {
            addCriterion("num_iid not between", value1, value2, "numIid");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andMainImageIsNull() {
            addCriterion("main_image is null");
            return (Criteria) this;
        }

        public Criteria andMainImageIsNotNull() {
            addCriterion("main_image is not null");
            return (Criteria) this;
        }

        public Criteria andMainImageEqualTo(String value) {
            addCriterion("main_image =", value, "mainImage");
            return (Criteria) this;
        }

        public Criteria andMainImageNotEqualTo(String value) {
            addCriterion("main_image <>", value, "mainImage");
            return (Criteria) this;
        }

        public Criteria andMainImageGreaterThan(String value) {
            addCriterion("main_image >", value, "mainImage");
            return (Criteria) this;
        }

        public Criteria andMainImageGreaterThanOrEqualTo(String value) {
            addCriterion("main_image >=", value, "mainImage");
            return (Criteria) this;
        }

        public Criteria andMainImageLessThan(String value) {
            addCriterion("main_image <", value, "mainImage");
            return (Criteria) this;
        }

        public Criteria andMainImageLessThanOrEqualTo(String value) {
            addCriterion("main_image <=", value, "mainImage");
            return (Criteria) this;
        }

        public Criteria andMainImageLike(String value) {
            addCriterion("main_image like", value, "mainImage");
            return (Criteria) this;
        }

        public Criteria andMainImageNotLike(String value) {
            addCriterion("main_image not like", value, "mainImage");
            return (Criteria) this;
        }

        public Criteria andMainImageIn(List<String> values) {
            addCriterion("main_image in", values, "mainImage");
            return (Criteria) this;
        }

        public Criteria andMainImageNotIn(List<String> values) {
            addCriterion("main_image not in", values, "mainImage");
            return (Criteria) this;
        }

        public Criteria andMainImageBetween(String value1, String value2) {
            addCriterion("main_image between", value1, value2, "mainImage");
            return (Criteria) this;
        }

        public Criteria andMainImageNotBetween(String value1, String value2) {
            addCriterion("main_image not between", value1, value2, "mainImage");
            return (Criteria) this;
        }

        public Criteria andDetailUrlIsNull() {
            addCriterion("detail_url is null");
            return (Criteria) this;
        }

        public Criteria andDetailUrlIsNotNull() {
            addCriterion("detail_url is not null");
            return (Criteria) this;
        }

        public Criteria andDetailUrlEqualTo(String value) {
            addCriterion("detail_url =", value, "detailUrl");
            return (Criteria) this;
        }

        public Criteria andDetailUrlNotEqualTo(String value) {
            addCriterion("detail_url <>", value, "detailUrl");
            return (Criteria) this;
        }

        public Criteria andDetailUrlGreaterThan(String value) {
            addCriterion("detail_url >", value, "detailUrl");
            return (Criteria) this;
        }

        public Criteria andDetailUrlGreaterThanOrEqualTo(String value) {
            addCriterion("detail_url >=", value, "detailUrl");
            return (Criteria) this;
        }

        public Criteria andDetailUrlLessThan(String value) {
            addCriterion("detail_url <", value, "detailUrl");
            return (Criteria) this;
        }

        public Criteria andDetailUrlLessThanOrEqualTo(String value) {
            addCriterion("detail_url <=", value, "detailUrl");
            return (Criteria) this;
        }

        public Criteria andDetailUrlLike(String value) {
            addCriterion("detail_url like", value, "detailUrl");
            return (Criteria) this;
        }

        public Criteria andDetailUrlNotLike(String value) {
            addCriterion("detail_url not like", value, "detailUrl");
            return (Criteria) this;
        }

        public Criteria andDetailUrlIn(List<String> values) {
            addCriterion("detail_url in", values, "detailUrl");
            return (Criteria) this;
        }

        public Criteria andDetailUrlNotIn(List<String> values) {
            addCriterion("detail_url not in", values, "detailUrl");
            return (Criteria) this;
        }

        public Criteria andDetailUrlBetween(String value1, String value2) {
            addCriterion("detail_url between", value1, value2, "detailUrl");
            return (Criteria) this;
        }

        public Criteria andDetailUrlNotBetween(String value1, String value2) {
            addCriterion("detail_url not between", value1, value2, "detailUrl");
            return (Criteria) this;
        }

        public Criteria andShopNameIsNull() {
            addCriterion("shop_name is null");
            return (Criteria) this;
        }

        public Criteria andShopNameIsNotNull() {
            addCriterion("shop_name is not null");
            return (Criteria) this;
        }

        public Criteria andShopNameEqualTo(String value) {
            addCriterion("shop_name =", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotEqualTo(String value) {
            addCriterion("shop_name <>", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameGreaterThan(String value) {
            addCriterion("shop_name >", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameGreaterThanOrEqualTo(String value) {
            addCriterion("shop_name >=", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLessThan(String value) {
            addCriterion("shop_name <", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLessThanOrEqualTo(String value) {
            addCriterion("shop_name <=", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLike(String value) {
            addCriterion("shop_name like", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotLike(String value) {
            addCriterion("shop_name not like", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameIn(List<String> values) {
            addCriterion("shop_name in", values, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotIn(List<String> values) {
            addCriterion("shop_name not in", values, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameBetween(String value1, String value2) {
            addCriterion("shop_name between", value1, value2, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotBetween(String value1, String value2) {
            addCriterion("shop_name not between", value1, value2, "shopName");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(BigDecimal value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(BigDecimal value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(BigDecimal value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(BigDecimal value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<BigDecimal> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<BigDecimal> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andMonthCountIsNull() {
            addCriterion("month_count is null");
            return (Criteria) this;
        }

        public Criteria andMonthCountIsNotNull() {
            addCriterion("month_count is not null");
            return (Criteria) this;
        }

        public Criteria andMonthCountEqualTo(Integer value) {
            addCriterion("month_count =", value, "monthCount");
            return (Criteria) this;
        }

        public Criteria andMonthCountNotEqualTo(Integer value) {
            addCriterion("month_count <>", value, "monthCount");
            return (Criteria) this;
        }

        public Criteria andMonthCountGreaterThan(Integer value) {
            addCriterion("month_count >", value, "monthCount");
            return (Criteria) this;
        }

        public Criteria andMonthCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("month_count >=", value, "monthCount");
            return (Criteria) this;
        }

        public Criteria andMonthCountLessThan(Integer value) {
            addCriterion("month_count <", value, "monthCount");
            return (Criteria) this;
        }

        public Criteria andMonthCountLessThanOrEqualTo(Integer value) {
            addCriterion("month_count <=", value, "monthCount");
            return (Criteria) this;
        }

        public Criteria andMonthCountIn(List<Integer> values) {
            addCriterion("month_count in", values, "monthCount");
            return (Criteria) this;
        }

        public Criteria andMonthCountNotIn(List<Integer> values) {
            addCriterion("month_count not in", values, "monthCount");
            return (Criteria) this;
        }

        public Criteria andMonthCountBetween(Integer value1, Integer value2) {
            addCriterion("month_count between", value1, value2, "monthCount");
            return (Criteria) this;
        }

        public Criteria andMonthCountNotBetween(Integer value1, Integer value2) {
            addCriterion("month_count not between", value1, value2, "monthCount");
            return (Criteria) this;
        }

        public Criteria andRateIsNull() {
            addCriterion("rate is null");
            return (Criteria) this;
        }

        public Criteria andRateIsNotNull() {
            addCriterion("rate is not null");
            return (Criteria) this;
        }

        public Criteria andRateEqualTo(BigDecimal value) {
            addCriterion("rate =", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotEqualTo(BigDecimal value) {
            addCriterion("rate <>", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateGreaterThan(BigDecimal value) {
            addCriterion("rate >", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("rate >=", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateLessThan(BigDecimal value) {
            addCriterion("rate <", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("rate <=", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateIn(List<BigDecimal> values) {
            addCriterion("rate in", values, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotIn(List<BigDecimal> values) {
            addCriterion("rate not in", values, "rate");
            return (Criteria) this;
        }

        public Criteria andRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rate between", value1, value2, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rate not between", value1, value2, "rate");
            return (Criteria) this;
        }

        public Criteria andCommisionIsNull() {
            addCriterion("commision is null");
            return (Criteria) this;
        }

        public Criteria andCommisionIsNotNull() {
            addCriterion("commision is not null");
            return (Criteria) this;
        }

        public Criteria andCommisionEqualTo(BigDecimal value) {
            addCriterion("commision =", value, "commision");
            return (Criteria) this;
        }

        public Criteria andCommisionNotEqualTo(BigDecimal value) {
            addCriterion("commision <>", value, "commision");
            return (Criteria) this;
        }

        public Criteria andCommisionGreaterThan(BigDecimal value) {
            addCriterion("commision >", value, "commision");
            return (Criteria) this;
        }

        public Criteria andCommisionGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("commision >=", value, "commision");
            return (Criteria) this;
        }

        public Criteria andCommisionLessThan(BigDecimal value) {
            addCriterion("commision <", value, "commision");
            return (Criteria) this;
        }

        public Criteria andCommisionLessThanOrEqualTo(BigDecimal value) {
            addCriterion("commision <=", value, "commision");
            return (Criteria) this;
        }

        public Criteria andCommisionIn(List<BigDecimal> values) {
            addCriterion("commision in", values, "commision");
            return (Criteria) this;
        }

        public Criteria andCommisionNotIn(List<BigDecimal> values) {
            addCriterion("commision not in", values, "commision");
            return (Criteria) this;
        }

        public Criteria andCommisionBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("commision between", value1, value2, "commision");
            return (Criteria) this;
        }

        public Criteria andCommisionNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("commision not between", value1, value2, "commision");
            return (Criteria) this;
        }

        public Criteria andNickIsNull() {
            addCriterion("nick is null");
            return (Criteria) this;
        }

        public Criteria andNickIsNotNull() {
            addCriterion("nick is not null");
            return (Criteria) this;
        }

        public Criteria andNickEqualTo(String value) {
            addCriterion("nick =", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickNotEqualTo(String value) {
            addCriterion("nick <>", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickGreaterThan(String value) {
            addCriterion("nick >", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickGreaterThanOrEqualTo(String value) {
            addCriterion("nick >=", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickLessThan(String value) {
            addCriterion("nick <", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickLessThanOrEqualTo(String value) {
            addCriterion("nick <=", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickLike(String value) {
            addCriterion("nick like", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickNotLike(String value) {
            addCriterion("nick not like", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickIn(List<String> values) {
            addCriterion("nick in", values, "nick");
            return (Criteria) this;
        }

        public Criteria andNickNotIn(List<String> values) {
            addCriterion("nick not in", values, "nick");
            return (Criteria) this;
        }

        public Criteria andNickBetween(String value1, String value2) {
            addCriterion("nick between", value1, value2, "nick");
            return (Criteria) this;
        }

        public Criteria andNickNotBetween(String value1, String value2) {
            addCriterion("nick not between", value1, value2, "nick");
            return (Criteria) this;
        }

        public Criteria andTbkShotUrlIsNull() {
            addCriterion("tbk_shot_url is null");
            return (Criteria) this;
        }

        public Criteria andTbkShotUrlIsNotNull() {
            addCriterion("tbk_shot_url is not null");
            return (Criteria) this;
        }

        public Criteria andTbkShotUrlEqualTo(String value) {
            addCriterion("tbk_shot_url =", value, "tbkShotUrl");
            return (Criteria) this;
        }

        public Criteria andTbkShotUrlNotEqualTo(String value) {
            addCriterion("tbk_shot_url <>", value, "tbkShotUrl");
            return (Criteria) this;
        }

        public Criteria andTbkShotUrlGreaterThan(String value) {
            addCriterion("tbk_shot_url >", value, "tbkShotUrl");
            return (Criteria) this;
        }

        public Criteria andTbkShotUrlGreaterThanOrEqualTo(String value) {
            addCriterion("tbk_shot_url >=", value, "tbkShotUrl");
            return (Criteria) this;
        }

        public Criteria andTbkShotUrlLessThan(String value) {
            addCriterion("tbk_shot_url <", value, "tbkShotUrl");
            return (Criteria) this;
        }

        public Criteria andTbkShotUrlLessThanOrEqualTo(String value) {
            addCriterion("tbk_shot_url <=", value, "tbkShotUrl");
            return (Criteria) this;
        }

        public Criteria andTbkShotUrlLike(String value) {
            addCriterion("tbk_shot_url like", value, "tbkShotUrl");
            return (Criteria) this;
        }

        public Criteria andTbkShotUrlNotLike(String value) {
            addCriterion("tbk_shot_url not like", value, "tbkShotUrl");
            return (Criteria) this;
        }

        public Criteria andTbkShotUrlIn(List<String> values) {
            addCriterion("tbk_shot_url in", values, "tbkShotUrl");
            return (Criteria) this;
        }

        public Criteria andTbkShotUrlNotIn(List<String> values) {
            addCriterion("tbk_shot_url not in", values, "tbkShotUrl");
            return (Criteria) this;
        }

        public Criteria andTbkShotUrlBetween(String value1, String value2) {
            addCriterion("tbk_shot_url between", value1, value2, "tbkShotUrl");
            return (Criteria) this;
        }

        public Criteria andTbkShotUrlNotBetween(String value1, String value2) {
            addCriterion("tbk_shot_url not between", value1, value2, "tbkShotUrl");
            return (Criteria) this;
        }

        public Criteria andTbkUrlIsNull() {
            addCriterion("tbk_url is null");
            return (Criteria) this;
        }

        public Criteria andTbkUrlIsNotNull() {
            addCriterion("tbk_url is not null");
            return (Criteria) this;
        }

        public Criteria andTbkUrlEqualTo(String value) {
            addCriterion("tbk_url =", value, "tbkUrl");
            return (Criteria) this;
        }

        public Criteria andTbkUrlNotEqualTo(String value) {
            addCriterion("tbk_url <>", value, "tbkUrl");
            return (Criteria) this;
        }

        public Criteria andTbkUrlGreaterThan(String value) {
            addCriterion("tbk_url >", value, "tbkUrl");
            return (Criteria) this;
        }

        public Criteria andTbkUrlGreaterThanOrEqualTo(String value) {
            addCriterion("tbk_url >=", value, "tbkUrl");
            return (Criteria) this;
        }

        public Criteria andTbkUrlLessThan(String value) {
            addCriterion("tbk_url <", value, "tbkUrl");
            return (Criteria) this;
        }

        public Criteria andTbkUrlLessThanOrEqualTo(String value) {
            addCriterion("tbk_url <=", value, "tbkUrl");
            return (Criteria) this;
        }

        public Criteria andTbkUrlLike(String value) {
            addCriterion("tbk_url like", value, "tbkUrl");
            return (Criteria) this;
        }

        public Criteria andTbkUrlNotLike(String value) {
            addCriterion("tbk_url not like", value, "tbkUrl");
            return (Criteria) this;
        }

        public Criteria andTbkUrlIn(List<String> values) {
            addCriterion("tbk_url in", values, "tbkUrl");
            return (Criteria) this;
        }

        public Criteria andTbkUrlNotIn(List<String> values) {
            addCriterion("tbk_url not in", values, "tbkUrl");
            return (Criteria) this;
        }

        public Criteria andTbkUrlBetween(String value1, String value2) {
            addCriterion("tbk_url between", value1, value2, "tbkUrl");
            return (Criteria) this;
        }

        public Criteria andTbkUrlNotBetween(String value1, String value2) {
            addCriterion("tbk_url not between", value1, value2, "tbkUrl");
            return (Criteria) this;
        }

        public Criteria andTklIsNull() {
            addCriterion("tkl is null");
            return (Criteria) this;
        }

        public Criteria andTklIsNotNull() {
            addCriterion("tkl is not null");
            return (Criteria) this;
        }

        public Criteria andTklEqualTo(String value) {
            addCriterion("tkl =", value, "tkl");
            return (Criteria) this;
        }

        public Criteria andTklNotEqualTo(String value) {
            addCriterion("tkl <>", value, "tkl");
            return (Criteria) this;
        }

        public Criteria andTklGreaterThan(String value) {
            addCriterion("tkl >", value, "tkl");
            return (Criteria) this;
        }

        public Criteria andTklGreaterThanOrEqualTo(String value) {
            addCriterion("tkl >=", value, "tkl");
            return (Criteria) this;
        }

        public Criteria andTklLessThan(String value) {
            addCriterion("tkl <", value, "tkl");
            return (Criteria) this;
        }

        public Criteria andTklLessThanOrEqualTo(String value) {
            addCriterion("tkl <=", value, "tkl");
            return (Criteria) this;
        }

        public Criteria andTklLike(String value) {
            addCriterion("tkl like", value, "tkl");
            return (Criteria) this;
        }

        public Criteria andTklNotLike(String value) {
            addCriterion("tkl not like", value, "tkl");
            return (Criteria) this;
        }

        public Criteria andTklIn(List<String> values) {
            addCriterion("tkl in", values, "tkl");
            return (Criteria) this;
        }

        public Criteria andTklNotIn(List<String> values) {
            addCriterion("tkl not in", values, "tkl");
            return (Criteria) this;
        }

        public Criteria andTklBetween(String value1, String value2) {
            addCriterion("tkl between", value1, value2, "tkl");
            return (Criteria) this;
        }

        public Criteria andTklNotBetween(String value1, String value2) {
            addCriterion("tkl not between", value1, value2, "tkl");
            return (Criteria) this;
        }

        public Criteria andLabPriceIsNull() {
            addCriterion("lab_price is null");
            return (Criteria) this;
        }

        public Criteria andLabPriceIsNotNull() {
            addCriterion("lab_price is not null");
            return (Criteria) this;
        }

        public Criteria andLabPriceEqualTo(BigDecimal value) {
            addCriterion("lab_price =", value, "labPrice");
            return (Criteria) this;
        }

        public Criteria andLabPriceNotEqualTo(BigDecimal value) {
            addCriterion("lab_price <>", value, "labPrice");
            return (Criteria) this;
        }

        public Criteria andLabPriceGreaterThan(BigDecimal value) {
            addCriterion("lab_price >", value, "labPrice");
            return (Criteria) this;
        }

        public Criteria andLabPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("lab_price >=", value, "labPrice");
            return (Criteria) this;
        }

        public Criteria andLabPriceLessThan(BigDecimal value) {
            addCriterion("lab_price <", value, "labPrice");
            return (Criteria) this;
        }

        public Criteria andLabPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("lab_price <=", value, "labPrice");
            return (Criteria) this;
        }

        public Criteria andLabPriceIn(List<BigDecimal> values) {
            addCriterion("lab_price in", values, "labPrice");
            return (Criteria) this;
        }

        public Criteria andLabPriceNotIn(List<BigDecimal> values) {
            addCriterion("lab_price not in", values, "labPrice");
            return (Criteria) this;
        }

        public Criteria andLabPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("lab_price between", value1, value2, "labPrice");
            return (Criteria) this;
        }

        public Criteria andLabPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("lab_price not between", value1, value2, "labPrice");
            return (Criteria) this;
        }

        public Criteria andCueIsNull() {
            addCriterion("cue is null");
            return (Criteria) this;
        }

        public Criteria andCueIsNotNull() {
            addCriterion("cue is not null");
            return (Criteria) this;
        }

        public Criteria andCueEqualTo(String value) {
            addCriterion("cue =", value, "cue");
            return (Criteria) this;
        }

        public Criteria andCueNotEqualTo(String value) {
            addCriterion("cue <>", value, "cue");
            return (Criteria) this;
        }

        public Criteria andCueGreaterThan(String value) {
            addCriterion("cue >", value, "cue");
            return (Criteria) this;
        }

        public Criteria andCueGreaterThanOrEqualTo(String value) {
            addCriterion("cue >=", value, "cue");
            return (Criteria) this;
        }

        public Criteria andCueLessThan(String value) {
            addCriterion("cue <", value, "cue");
            return (Criteria) this;
        }

        public Criteria andCueLessThanOrEqualTo(String value) {
            addCriterion("cue <=", value, "cue");
            return (Criteria) this;
        }

        public Criteria andCueLike(String value) {
            addCriterion("cue like", value, "cue");
            return (Criteria) this;
        }

        public Criteria andCueNotLike(String value) {
            addCriterion("cue not like", value, "cue");
            return (Criteria) this;
        }

        public Criteria andCueIn(List<String> values) {
            addCriterion("cue in", values, "cue");
            return (Criteria) this;
        }

        public Criteria andCueNotIn(List<String> values) {
            addCriterion("cue not in", values, "cue");
            return (Criteria) this;
        }

        public Criteria andCueBetween(String value1, String value2) {
            addCriterion("cue between", value1, value2, "cue");
            return (Criteria) this;
        }

        public Criteria andCueNotBetween(String value1, String value2) {
            addCriterion("cue not between", value1, value2, "cue");
            return (Criteria) this;
        }

        public Criteria andClassifyIdIsNull() {
            addCriterion("classify_id is null");
            return (Criteria) this;
        }

        public Criteria andClassifyIdIsNotNull() {
            addCriterion("classify_id is not null");
            return (Criteria) this;
        }

        public Criteria andClassifyIdEqualTo(Integer value) {
            addCriterion("classify_id =", value, "classifyId");
            return (Criteria) this;
        }

        public Criteria andClassifyIdNotEqualTo(Integer value) {
            addCriterion("classify_id <>", value, "classifyId");
            return (Criteria) this;
        }

        public Criteria andClassifyIdGreaterThan(Integer value) {
            addCriterion("classify_id >", value, "classifyId");
            return (Criteria) this;
        }

        public Criteria andClassifyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("classify_id >=", value, "classifyId");
            return (Criteria) this;
        }

        public Criteria andClassifyIdLessThan(Integer value) {
            addCriterion("classify_id <", value, "classifyId");
            return (Criteria) this;
        }

        public Criteria andClassifyIdLessThanOrEqualTo(Integer value) {
            addCriterion("classify_id <=", value, "classifyId");
            return (Criteria) this;
        }

        public Criteria andClassifyIdIn(List<Integer> values) {
            addCriterion("classify_id in", values, "classifyId");
            return (Criteria) this;
        }

        public Criteria andClassifyIdNotIn(List<Integer> values) {
            addCriterion("classify_id not in", values, "classifyId");
            return (Criteria) this;
        }

        public Criteria andClassifyIdBetween(Integer value1, Integer value2) {
            addCriterion("classify_id between", value1, value2, "classifyId");
            return (Criteria) this;
        }

        public Criteria andClassifyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("classify_id not between", value1, value2, "classifyId");
            return (Criteria) this;
        }

        public Criteria andIsSaleIsNull() {
            addCriterion("is_sale is null");
            return (Criteria) this;
        }

        public Criteria andIsSaleIsNotNull() {
            addCriterion("is_sale is not null");
            return (Criteria) this;
        }

        public Criteria andIsSaleEqualTo(String value) {
            addCriterion("is_sale =", value, "isSale");
            return (Criteria) this;
        }

        public Criteria andIsSaleNotEqualTo(String value) {
            addCriterion("is_sale <>", value, "isSale");
            return (Criteria) this;
        }

        public Criteria andIsSaleGreaterThan(String value) {
            addCriterion("is_sale >", value, "isSale");
            return (Criteria) this;
        }

        public Criteria andIsSaleGreaterThanOrEqualTo(String value) {
            addCriterion("is_sale >=", value, "isSale");
            return (Criteria) this;
        }

        public Criteria andIsSaleLessThan(String value) {
            addCriterion("is_sale <", value, "isSale");
            return (Criteria) this;
        }

        public Criteria andIsSaleLessThanOrEqualTo(String value) {
            addCriterion("is_sale <=", value, "isSale");
            return (Criteria) this;
        }

        public Criteria andIsSaleLike(String value) {
            addCriterion("is_sale like", value, "isSale");
            return (Criteria) this;
        }

        public Criteria andIsSaleNotLike(String value) {
            addCriterion("is_sale not like", value, "isSale");
            return (Criteria) this;
        }

        public Criteria andIsSaleIn(List<String> values) {
            addCriterion("is_sale in", values, "isSale");
            return (Criteria) this;
        }

        public Criteria andIsSaleNotIn(List<String> values) {
            addCriterion("is_sale not in", values, "isSale");
            return (Criteria) this;
        }

        public Criteria andIsSaleBetween(String value1, String value2) {
            addCriterion("is_sale between", value1, value2, "isSale");
            return (Criteria) this;
        }

        public Criteria andIsSaleNotBetween(String value1, String value2) {
            addCriterion("is_sale not between", value1, value2, "isSale");
            return (Criteria) this;
        }

        public Criteria andSaleTimeIsNull() {
            addCriterion("sale_time is null");
            return (Criteria) this;
        }

        public Criteria andSaleTimeIsNotNull() {
            addCriterion("sale_time is not null");
            return (Criteria) this;
        }

        public Criteria andSaleTimeEqualTo(Date value) {
            addCriterion("sale_time =", value, "saleTime");
            return (Criteria) this;
        }

        public Criteria andSaleTimeNotEqualTo(Date value) {
            addCriterion("sale_time <>", value, "saleTime");
            return (Criteria) this;
        }

        public Criteria andSaleTimeGreaterThan(Date value) {
            addCriterion("sale_time >", value, "saleTime");
            return (Criteria) this;
        }

        public Criteria andSaleTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("sale_time >=", value, "saleTime");
            return (Criteria) this;
        }

        public Criteria andSaleTimeLessThan(Date value) {
            addCriterion("sale_time <", value, "saleTime");
            return (Criteria) this;
        }

        public Criteria andSaleTimeLessThanOrEqualTo(Date value) {
            addCriterion("sale_time <=", value, "saleTime");
            return (Criteria) this;
        }

        public Criteria andSaleTimeIn(List<Date> values) {
            addCriterion("sale_time in", values, "saleTime");
            return (Criteria) this;
        }

        public Criteria andSaleTimeNotIn(List<Date> values) {
            addCriterion("sale_time not in", values, "saleTime");
            return (Criteria) this;
        }

        public Criteria andSaleTimeBetween(Date value1, Date value2) {
            addCriterion("sale_time between", value1, value2, "saleTime");
            return (Criteria) this;
        }

        public Criteria andSaleTimeNotBetween(Date value1, Date value2) {
            addCriterion("sale_time not between", value1, value2, "saleTime");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNull() {
            addCriterion("create_user is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNotNull() {
            addCriterion("create_user is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserEqualTo(String value) {
            addCriterion("create_user =", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotEqualTo(String value) {
            addCriterion("create_user <>", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThan(String value) {
            addCriterion("create_user >", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThanOrEqualTo(String value) {
            addCriterion("create_user >=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThan(String value) {
            addCriterion("create_user <", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThanOrEqualTo(String value) {
            addCriterion("create_user <=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLike(String value) {
            addCriterion("create_user like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotLike(String value) {
            addCriterion("create_user not like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserIn(List<String> values) {
            addCriterion("create_user in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotIn(List<String> values) {
            addCriterion("create_user not in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserBetween(String value1, String value2) {
            addCriterion("create_user between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotBetween(String value1, String value2) {
            addCriterion("create_user not between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNull() {
            addCriterion("update_user is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNotNull() {
            addCriterion("update_user is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserEqualTo(String value) {
            addCriterion("update_user =", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotEqualTo(String value) {
            addCriterion("update_user <>", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThan(String value) {
            addCriterion("update_user >", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThanOrEqualTo(String value) {
            addCriterion("update_user >=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThan(String value) {
            addCriterion("update_user <", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThanOrEqualTo(String value) {
            addCriterion("update_user <=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLike(String value) {
            addCriterion("update_user like", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotLike(String value) {
            addCriterion("update_user not like", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIn(List<String> values) {
            addCriterion("update_user in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotIn(List<String> values) {
            addCriterion("update_user not in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserBetween(String value1, String value2) {
            addCriterion("update_user between", value1, value2, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotBetween(String value1, String value2) {
            addCriterion("update_user not between", value1, value2, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
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
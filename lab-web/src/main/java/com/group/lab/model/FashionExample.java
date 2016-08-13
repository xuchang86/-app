package com.group.lab.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.group.core.model.BaseExample;

public class FashionExample extends BaseExample{
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FashionExample() {
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

        public Criteria andFashionIdIsNull() {
            addCriterion("fashion_id is null");
            return (Criteria) this;
        }

        public Criteria andFashionIdIsNotNull() {
            addCriterion("fashion_id is not null");
            return (Criteria) this;
        }

        public Criteria andFashionIdEqualTo(Integer value) {
            addCriterion("fashion_id =", value, "fashionId");
            return (Criteria) this;
        }

        public Criteria andFashionIdNotEqualTo(Integer value) {
            addCriterion("fashion_id <>", value, "fashionId");
            return (Criteria) this;
        }

        public Criteria andFashionIdGreaterThan(Integer value) {
            addCriterion("fashion_id >", value, "fashionId");
            return (Criteria) this;
        }

        public Criteria andFashionIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("fashion_id >=", value, "fashionId");
            return (Criteria) this;
        }

        public Criteria andFashionIdLessThan(Integer value) {
            addCriterion("fashion_id <", value, "fashionId");
            return (Criteria) this;
        }

        public Criteria andFashionIdLessThanOrEqualTo(Integer value) {
            addCriterion("fashion_id <=", value, "fashionId");
            return (Criteria) this;
        }

        public Criteria andFashionIdIn(List<Integer> values) {
            addCriterion("fashion_id in", values, "fashionId");
            return (Criteria) this;
        }

        public Criteria andFashionIdNotIn(List<Integer> values) {
            addCriterion("fashion_id not in", values, "fashionId");
            return (Criteria) this;
        }

        public Criteria andFashionIdBetween(Integer value1, Integer value2) {
            addCriterion("fashion_id between", value1, value2, "fashionId");
            return (Criteria) this;
        }

        public Criteria andFashionIdNotBetween(Integer value1, Integer value2) {
            addCriterion("fashion_id not between", value1, value2, "fashionId");
            return (Criteria) this;
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

        public Criteria andOpenIidIsNull() {
            addCriterion("open_iid is null");
            return (Criteria) this;
        }

        public Criteria andOpenIidIsNotNull() {
            addCriterion("open_iid is not null");
            return (Criteria) this;
        }

        public Criteria andOpenIidEqualTo(String value) {
            addCriterion("open_iid =", value, "openIid");
            return (Criteria) this;
        }

        public Criteria andOpenIidNotEqualTo(String value) {
            addCriterion("open_iid <>", value, "openIid");
            return (Criteria) this;
        }

        public Criteria andOpenIidGreaterThan(String value) {
            addCriterion("open_iid >", value, "openIid");
            return (Criteria) this;
        }

        public Criteria andOpenIidGreaterThanOrEqualTo(String value) {
            addCriterion("open_iid >=", value, "openIid");
            return (Criteria) this;
        }

        public Criteria andOpenIidLessThan(String value) {
            addCriterion("open_iid <", value, "openIid");
            return (Criteria) this;
        }

        public Criteria andOpenIidLessThanOrEqualTo(String value) {
            addCriterion("open_iid <=", value, "openIid");
            return (Criteria) this;
        }

        public Criteria andOpenIidLike(String value) {
            addCriterion("open_iid like", value, "openIid");
            return (Criteria) this;
        }

        public Criteria andOpenIidNotLike(String value) {
            addCriterion("open_iid not like", value, "openIid");
            return (Criteria) this;
        }

        public Criteria andOpenIidIn(List<String> values) {
            addCriterion("open_iid in", values, "openIid");
            return (Criteria) this;
        }

        public Criteria andOpenIidNotIn(List<String> values) {
            addCriterion("open_iid not in", values, "openIid");
            return (Criteria) this;
        }

        public Criteria andOpenIidBetween(String value1, String value2) {
            addCriterion("open_iid between", value1, value2, "openIid");
            return (Criteria) this;
        }

        public Criteria andOpenIidNotBetween(String value1, String value2) {
            addCriterion("open_iid not between", value1, value2, "openIid");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
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

        public Criteria andBrandIsNull() {
            addCriterion("brand is null");
            return (Criteria) this;
        }

        public Criteria andBrandIsNotNull() {
            addCriterion("brand is not null");
            return (Criteria) this;
        }

        public Criteria andBrandEqualTo(String value) {
            addCriterion("brand =", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotEqualTo(String value) {
            addCriterion("brand <>", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandGreaterThan(String value) {
            addCriterion("brand >", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandGreaterThanOrEqualTo(String value) {
            addCriterion("brand >=", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandLessThan(String value) {
            addCriterion("brand <", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandLessThanOrEqualTo(String value) {
            addCriterion("brand <=", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandLike(String value) {
            addCriterion("brand like", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotLike(String value) {
            addCriterion("brand not like", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandIn(List<String> values) {
            addCriterion("brand in", values, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotIn(List<String> values) {
            addCriterion("brand not in", values, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandBetween(String value1, String value2) {
            addCriterion("brand between", value1, value2, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotBetween(String value1, String value2) {
            addCriterion("brand not between", value1, value2, "brand");
            return (Criteria) this;
        }

        public Criteria andSaleTypeIsNull() {
            addCriterion("sale_type is null");
            return (Criteria) this;
        }

        public Criteria andSaleTypeIsNotNull() {
            addCriterion("sale_type is not null");
            return (Criteria) this;
        }

        public Criteria andSaleTypeEqualTo(String value) {
            addCriterion("sale_type =", value, "saleType");
            return (Criteria) this;
        }

        public Criteria andSaleTypeNotEqualTo(String value) {
            addCriterion("sale_type <>", value, "saleType");
            return (Criteria) this;
        }

        public Criteria andSaleTypeGreaterThan(String value) {
            addCriterion("sale_type >", value, "saleType");
            return (Criteria) this;
        }

        public Criteria andSaleTypeGreaterThanOrEqualTo(String value) {
            addCriterion("sale_type >=", value, "saleType");
            return (Criteria) this;
        }

        public Criteria andSaleTypeLessThan(String value) {
            addCriterion("sale_type <", value, "saleType");
            return (Criteria) this;
        }

        public Criteria andSaleTypeLessThanOrEqualTo(String value) {
            addCriterion("sale_type <=", value, "saleType");
            return (Criteria) this;
        }

        public Criteria andSaleTypeLike(String value) {
            addCriterion("sale_type like", value, "saleType");
            return (Criteria) this;
        }

        public Criteria andSaleTypeNotLike(String value) {
            addCriterion("sale_type not like", value, "saleType");
            return (Criteria) this;
        }

        public Criteria andSaleTypeIn(List<String> values) {
            addCriterion("sale_type in", values, "saleType");
            return (Criteria) this;
        }

        public Criteria andSaleTypeNotIn(List<String> values) {
            addCriterion("sale_type not in", values, "saleType");
            return (Criteria) this;
        }

        public Criteria andSaleTypeBetween(String value1, String value2) {
            addCriterion("sale_type between", value1, value2, "saleType");
            return (Criteria) this;
        }

        public Criteria andSaleTypeNotBetween(String value1, String value2) {
            addCriterion("sale_type not between", value1, value2, "saleType");
            return (Criteria) this;
        }

        public Criteria andQtyIsNull() {
            addCriterion("qty is null");
            return (Criteria) this;
        }

        public Criteria andQtyIsNotNull() {
            addCriterion("qty is not null");
            return (Criteria) this;
        }

        public Criteria andQtyEqualTo(Integer value) {
            addCriterion("qty =", value, "qty");
            return (Criteria) this;
        }

        public Criteria andQtyNotEqualTo(Integer value) {
            addCriterion("qty <>", value, "qty");
            return (Criteria) this;
        }

        public Criteria andQtyGreaterThan(Integer value) {
            addCriterion("qty >", value, "qty");
            return (Criteria) this;
        }

        public Criteria andQtyGreaterThanOrEqualTo(Integer value) {
            addCriterion("qty >=", value, "qty");
            return (Criteria) this;
        }

        public Criteria andQtyLessThan(Integer value) {
            addCriterion("qty <", value, "qty");
            return (Criteria) this;
        }

        public Criteria andQtyLessThanOrEqualTo(Integer value) {
            addCriterion("qty <=", value, "qty");
            return (Criteria) this;
        }

        public Criteria andQtyIn(List<Integer> values) {
            addCriterion("qty in", values, "qty");
            return (Criteria) this;
        }

        public Criteria andQtyNotIn(List<Integer> values) {
            addCriterion("qty not in", values, "qty");
            return (Criteria) this;
        }

        public Criteria andQtyBetween(Integer value1, Integer value2) {
            addCriterion("qty between", value1, value2, "qty");
            return (Criteria) this;
        }

        public Criteria andQtyNotBetween(Integer value1, Integer value2) {
            addCriterion("qty not between", value1, value2, "qty");
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
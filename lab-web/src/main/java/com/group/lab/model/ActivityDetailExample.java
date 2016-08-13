package com.group.lab.model;

import java.util.ArrayList;
import java.util.List;

public class ActivityDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ActivityDetailExample() {
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

        public Criteria andActivityIdIsNull() {
            addCriterion("activity_id is null");
            return (Criteria) this;
        }

        public Criteria andActivityIdIsNotNull() {
            addCriterion("activity_id is not null");
            return (Criteria) this;
        }

        public Criteria andActivityIdEqualTo(Integer value) {
            addCriterion("activity_id =", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotEqualTo(Integer value) {
            addCriterion("activity_id <>", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdGreaterThan(Integer value) {
            addCriterion("activity_id >", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("activity_id >=", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdLessThan(Integer value) {
            addCriterion("activity_id <", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdLessThanOrEqualTo(Integer value) {
            addCriterion("activity_id <=", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdIn(List<Integer> values) {
            addCriterion("activity_id in", values, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotIn(List<Integer> values) {
            addCriterion("activity_id not in", values, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdBetween(Integer value1, Integer value2) {
            addCriterion("activity_id between", value1, value2, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotBetween(Integer value1, Integer value2) {
            addCriterion("activity_id not between", value1, value2, "activityId");
            return (Criteria) this;
        }

        public Criteria andImagesIsNull() {
            addCriterion("images is null");
            return (Criteria) this;
        }

        public Criteria andImagesIsNotNull() {
            addCriterion("images is not null");
            return (Criteria) this;
        }

        public Criteria andImagesEqualTo(String value) {
            addCriterion("images =", value, "images");
            return (Criteria) this;
        }

        public Criteria andImagesNotEqualTo(String value) {
            addCriterion("images <>", value, "images");
            return (Criteria) this;
        }

        public Criteria andImagesGreaterThan(String value) {
            addCriterion("images >", value, "images");
            return (Criteria) this;
        }

        public Criteria andImagesGreaterThanOrEqualTo(String value) {
            addCriterion("images >=", value, "images");
            return (Criteria) this;
        }

        public Criteria andImagesLessThan(String value) {
            addCriterion("images <", value, "images");
            return (Criteria) this;
        }

        public Criteria andImagesLessThanOrEqualTo(String value) {
            addCriterion("images <=", value, "images");
            return (Criteria) this;
        }

        public Criteria andImagesLike(String value) {
            addCriterion("images like", value, "images");
            return (Criteria) this;
        }

        public Criteria andImagesNotLike(String value) {
            addCriterion("images not like", value, "images");
            return (Criteria) this;
        }

        public Criteria andImagesIn(List<String> values) {
            addCriterion("images in", values, "images");
            return (Criteria) this;
        }

        public Criteria andImagesNotIn(List<String> values) {
            addCriterion("images not in", values, "images");
            return (Criteria) this;
        }

        public Criteria andImagesBetween(String value1, String value2) {
            addCriterion("images between", value1, value2, "images");
            return (Criteria) this;
        }

        public Criteria andImagesNotBetween(String value1, String value2) {
            addCriterion("images not between", value1, value2, "images");
            return (Criteria) this;
        }

        public Criteria andButtonIsNull() {
            addCriterion("button is null");
            return (Criteria) this;
        }

        public Criteria andButtonIsNotNull() {
            addCriterion("button is not null");
            return (Criteria) this;
        }

        public Criteria andButtonEqualTo(String value) {
            addCriterion("button =", value, "button");
            return (Criteria) this;
        }

        public Criteria andButtonNotEqualTo(String value) {
            addCriterion("button <>", value, "button");
            return (Criteria) this;
        }

        public Criteria andButtonGreaterThan(String value) {
            addCriterion("button >", value, "button");
            return (Criteria) this;
        }

        public Criteria andButtonGreaterThanOrEqualTo(String value) {
            addCriterion("button >=", value, "button");
            return (Criteria) this;
        }

        public Criteria andButtonLessThan(String value) {
            addCriterion("button <", value, "button");
            return (Criteria) this;
        }

        public Criteria andButtonLessThanOrEqualTo(String value) {
            addCriterion("button <=", value, "button");
            return (Criteria) this;
        }

        public Criteria andButtonLike(String value) {
            addCriterion("button like", value, "button");
            return (Criteria) this;
        }

        public Criteria andButtonNotLike(String value) {
            addCriterion("button not like", value, "button");
            return (Criteria) this;
        }

        public Criteria andButtonIn(List<String> values) {
            addCriterion("button in", values, "button");
            return (Criteria) this;
        }

        public Criteria andButtonNotIn(List<String> values) {
            addCriterion("button not in", values, "button");
            return (Criteria) this;
        }

        public Criteria andButtonBetween(String value1, String value2) {
            addCriterion("button between", value1, value2, "button");
            return (Criteria) this;
        }

        public Criteria andButtonNotBetween(String value1, String value2) {
            addCriterion("button not between", value1, value2, "button");
            return (Criteria) this;
        }

        public Criteria andFashionIdsIsNull() {
            addCriterion("fashion_ids is null");
            return (Criteria) this;
        }

        public Criteria andFashionIdsIsNotNull() {
            addCriterion("fashion_ids is not null");
            return (Criteria) this;
        }

        public Criteria andFashionIdsEqualTo(String value) {
            addCriterion("fashion_ids =", value, "fashionIds");
            return (Criteria) this;
        }

        public Criteria andFashionIdsNotEqualTo(String value) {
            addCriterion("fashion_ids <>", value, "fashionIds");
            return (Criteria) this;
        }

        public Criteria andFashionIdsGreaterThan(String value) {
            addCriterion("fashion_ids >", value, "fashionIds");
            return (Criteria) this;
        }

        public Criteria andFashionIdsGreaterThanOrEqualTo(String value) {
            addCriterion("fashion_ids >=", value, "fashionIds");
            return (Criteria) this;
        }

        public Criteria andFashionIdsLessThan(String value) {
            addCriterion("fashion_ids <", value, "fashionIds");
            return (Criteria) this;
        }

        public Criteria andFashionIdsLessThanOrEqualTo(String value) {
            addCriterion("fashion_ids <=", value, "fashionIds");
            return (Criteria) this;
        }

        public Criteria andFashionIdsLike(String value) {
            addCriterion("fashion_ids like", value, "fashionIds");
            return (Criteria) this;
        }

        public Criteria andFashionIdsNotLike(String value) {
            addCriterion("fashion_ids not like", value, "fashionIds");
            return (Criteria) this;
        }

        public Criteria andFashionIdsIn(List<String> values) {
            addCriterion("fashion_ids in", values, "fashionIds");
            return (Criteria) this;
        }

        public Criteria andFashionIdsNotIn(List<String> values) {
            addCriterion("fashion_ids not in", values, "fashionIds");
            return (Criteria) this;
        }

        public Criteria andFashionIdsBetween(String value1, String value2) {
            addCriterion("fashion_ids between", value1, value2, "fashionIds");
            return (Criteria) this;
        }

        public Criteria andFashionIdsNotBetween(String value1, String value2) {
            addCriterion("fashion_ids not between", value1, value2, "fashionIds");
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
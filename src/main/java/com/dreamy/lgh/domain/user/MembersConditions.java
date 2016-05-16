package com.dreamy.lgh.domain.user;

import com.dreamy.beans.Page;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MembersConditions {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Page page;

    public MembersConditions() {
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

    public void setPage(Page page) {
        this.page = page;
    }

    public Page getPage() {
        return this.page;
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

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andWxOrderIdIsNull() {
            addCriterion("wx_order_id is null");
            return (Criteria) this;
        }

        public Criteria andWxOrderIdIsNotNull() {
            addCriterion("wx_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andWxOrderIdEqualTo(String value) {
            addCriterion("wx_order_id =", value, "wxOrderId");
            return (Criteria) this;
        }

        public Criteria andWxOrderIdNotEqualTo(String value) {
            addCriterion("wx_order_id <>", value, "wxOrderId");
            return (Criteria) this;
        }

        public Criteria andWxOrderIdGreaterThan(String value) {
            addCriterion("wx_order_id >", value, "wxOrderId");
            return (Criteria) this;
        }

        public Criteria andWxOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("wx_order_id >=", value, "wxOrderId");
            return (Criteria) this;
        }

        public Criteria andWxOrderIdLessThan(String value) {
            addCriterion("wx_order_id <", value, "wxOrderId");
            return (Criteria) this;
        }

        public Criteria andWxOrderIdLessThanOrEqualTo(String value) {
            addCriterion("wx_order_id <=", value, "wxOrderId");
            return (Criteria) this;
        }

        public Criteria andWxOrderIdLike(String value) {
            addCriterion("wx_order_id like", value, "wxOrderId");
            return (Criteria) this;
        }

        public Criteria andWxOrderIdNotLike(String value) {
            addCriterion("wx_order_id not like", value, "wxOrderId");
            return (Criteria) this;
        }

        public Criteria andWxOrderIdIn(List<String> values) {
            addCriterion("wx_order_id in", values, "wxOrderId");
            return (Criteria) this;
        }

        public Criteria andWxOrderIdNotIn(List<String> values) {
            addCriterion("wx_order_id not in", values, "wxOrderId");
            return (Criteria) this;
        }

        public Criteria andWxOrderIdBetween(String value1, String value2) {
            addCriterion("wx_order_id between", value1, value2, "wxOrderId");
            return (Criteria) this;
        }

        public Criteria andWxOrderIdNotBetween(String value1, String value2) {
            addCriterion("wx_order_id not between", value1, value2, "wxOrderId");
            return (Criteria) this;
        }

        public Criteria andWxIdIsNull() {
            addCriterion("wx_id is null");
            return (Criteria) this;
        }

        public Criteria andWxIdIsNotNull() {
            addCriterion("wx_id is not null");
            return (Criteria) this;
        }

        public Criteria andWxIdEqualTo(String value) {
            addCriterion("wx_id =", value, "wxId");
            return (Criteria) this;
        }

        public Criteria andWxIdNotEqualTo(String value) {
            addCriterion("wx_id <>", value, "wxId");
            return (Criteria) this;
        }

        public Criteria andWxIdGreaterThan(String value) {
            addCriterion("wx_id >", value, "wxId");
            return (Criteria) this;
        }

        public Criteria andWxIdGreaterThanOrEqualTo(String value) {
            addCriterion("wx_id >=", value, "wxId");
            return (Criteria) this;
        }

        public Criteria andWxIdLessThan(String value) {
            addCriterion("wx_id <", value, "wxId");
            return (Criteria) this;
        }

        public Criteria andWxIdLessThanOrEqualTo(String value) {
            addCriterion("wx_id <=", value, "wxId");
            return (Criteria) this;
        }

        public Criteria andWxIdLike(String value) {
            addCriterion("wx_id like", value, "wxId");
            return (Criteria) this;
        }

        public Criteria andWxIdNotLike(String value) {
            addCriterion("wx_id not like", value, "wxId");
            return (Criteria) this;
        }

        public Criteria andWxIdIn(List<String> values) {
            addCriterion("wx_id in", values, "wxId");
            return (Criteria) this;
        }

        public Criteria andWxIdNotIn(List<String> values) {
            addCriterion("wx_id not in", values, "wxId");
            return (Criteria) this;
        }

        public Criteria andWxIdBetween(String value1, String value2) {
            addCriterion("wx_id between", value1, value2, "wxId");
            return (Criteria) this;
        }

        public Criteria andWxIdNotBetween(String value1, String value2) {
            addCriterion("wx_id not between", value1, value2, "wxId");
            return (Criteria) this;
        }

        public Criteria andWxUserNameIsNull() {
            addCriterion("wx_user_name is null");
            return (Criteria) this;
        }

        public Criteria andWxUserNameIsNotNull() {
            addCriterion("wx_user_name is not null");
            return (Criteria) this;
        }

        public Criteria andWxUserNameEqualTo(String value) {
            addCriterion("wx_user_name =", value, "wxUserName");
            return (Criteria) this;
        }

        public Criteria andWxUserNameNotEqualTo(String value) {
            addCriterion("wx_user_name <>", value, "wxUserName");
            return (Criteria) this;
        }

        public Criteria andWxUserNameGreaterThan(String value) {
            addCriterion("wx_user_name >", value, "wxUserName");
            return (Criteria) this;
        }

        public Criteria andWxUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("wx_user_name >=", value, "wxUserName");
            return (Criteria) this;
        }

        public Criteria andWxUserNameLessThan(String value) {
            addCriterion("wx_user_name <", value, "wxUserName");
            return (Criteria) this;
        }

        public Criteria andWxUserNameLessThanOrEqualTo(String value) {
            addCriterion("wx_user_name <=", value, "wxUserName");
            return (Criteria) this;
        }

        public Criteria andWxUserNameLike(String value) {
            addCriterion("wx_user_name like", value, "wxUserName");
            return (Criteria) this;
        }

        public Criteria andWxUserNameNotLike(String value) {
            addCriterion("wx_user_name not like", value, "wxUserName");
            return (Criteria) this;
        }

        public Criteria andWxUserNameIn(List<String> values) {
            addCriterion("wx_user_name in", values, "wxUserName");
            return (Criteria) this;
        }

        public Criteria andWxUserNameNotIn(List<String> values) {
            addCriterion("wx_user_name not in", values, "wxUserName");
            return (Criteria) this;
        }

        public Criteria andWxUserNameBetween(String value1, String value2) {
            addCriterion("wx_user_name between", value1, value2, "wxUserName");
            return (Criteria) this;
        }

        public Criteria andWxUserNameNotBetween(String value1, String value2) {
            addCriterion("wx_user_name not between", value1, value2, "wxUserName");
            return (Criteria) this;
        }

        public Criteria andWxImageUrlIsNull() {
            addCriterion("wx_image_url is null");
            return (Criteria) this;
        }

        public Criteria andWxImageUrlIsNotNull() {
            addCriterion("wx_image_url is not null");
            return (Criteria) this;
        }

        public Criteria andWxImageUrlEqualTo(String value) {
            addCriterion("wx_image_url =", value, "wxImageUrl");
            return (Criteria) this;
        }

        public Criteria andWxImageUrlNotEqualTo(String value) {
            addCriterion("wx_image_url <>", value, "wxImageUrl");
            return (Criteria) this;
        }

        public Criteria andWxImageUrlGreaterThan(String value) {
            addCriterion("wx_image_url >", value, "wxImageUrl");
            return (Criteria) this;
        }

        public Criteria andWxImageUrlGreaterThanOrEqualTo(String value) {
            addCriterion("wx_image_url >=", value, "wxImageUrl");
            return (Criteria) this;
        }

        public Criteria andWxImageUrlLessThan(String value) {
            addCriterion("wx_image_url <", value, "wxImageUrl");
            return (Criteria) this;
        }

        public Criteria andWxImageUrlLessThanOrEqualTo(String value) {
            addCriterion("wx_image_url <=", value, "wxImageUrl");
            return (Criteria) this;
        }

        public Criteria andWxImageUrlLike(String value) {
            addCriterion("wx_image_url like", value, "wxImageUrl");
            return (Criteria) this;
        }

        public Criteria andWxImageUrlNotLike(String value) {
            addCriterion("wx_image_url not like", value, "wxImageUrl");
            return (Criteria) this;
        }

        public Criteria andWxImageUrlIn(List<String> values) {
            addCriterion("wx_image_url in", values, "wxImageUrl");
            return (Criteria) this;
        }

        public Criteria andWxImageUrlNotIn(List<String> values) {
            addCriterion("wx_image_url not in", values, "wxImageUrl");
            return (Criteria) this;
        }

        public Criteria andWxImageUrlBetween(String value1, String value2) {
            addCriterion("wx_image_url between", value1, value2, "wxImageUrl");
            return (Criteria) this;
        }

        public Criteria andWxImageUrlNotBetween(String value1, String value2) {
            addCriterion("wx_image_url not between", value1, value2, "wxImageUrl");
            return (Criteria) this;
        }

        public Criteria andStartedAtIsNull() {
            addCriterion("started_at is null");
            return (Criteria) this;
        }

        public Criteria andStartedAtIsNotNull() {
            addCriterion("started_at is not null");
            return (Criteria) this;
        }

        public Criteria andStartedAtEqualTo(Date value) {
            addCriterion("started_at =", value, "startedAt");
            return (Criteria) this;
        }

        public Criteria andStartedAtNotEqualTo(Date value) {
            addCriterion("started_at <>", value, "startedAt");
            return (Criteria) this;
        }

        public Criteria andStartedAtGreaterThan(Date value) {
            addCriterion("started_at >", value, "startedAt");
            return (Criteria) this;
        }

        public Criteria andStartedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("started_at >=", value, "startedAt");
            return (Criteria) this;
        }

        public Criteria andStartedAtLessThan(Date value) {
            addCriterion("started_at <", value, "startedAt");
            return (Criteria) this;
        }

        public Criteria andStartedAtLessThanOrEqualTo(Date value) {
            addCriterion("started_at <=", value, "startedAt");
            return (Criteria) this;
        }

        public Criteria andStartedAtIn(List<Date> values) {
            addCriterion("started_at in", values, "startedAt");
            return (Criteria) this;
        }

        public Criteria andStartedAtNotIn(List<Date> values) {
            addCriterion("started_at not in", values, "startedAt");
            return (Criteria) this;
        }

        public Criteria andStartedAtBetween(Date value1, Date value2) {
            addCriterion("started_at between", value1, value2, "startedAt");
            return (Criteria) this;
        }

        public Criteria andStartedAtNotBetween(Date value1, Date value2) {
            addCriterion("started_at not between", value1, value2, "startedAt");
            return (Criteria) this;
        }

        public Criteria andEndedAtIsNull() {
            addCriterion("ended_at is null");
            return (Criteria) this;
        }

        public Criteria andEndedAtIsNotNull() {
            addCriterion("ended_at is not null");
            return (Criteria) this;
        }

        public Criteria andEndedAtEqualTo(Date value) {
            addCriterion("ended_at =", value, "endedAt");
            return (Criteria) this;
        }

        public Criteria andEndedAtNotEqualTo(Date value) {
            addCriterion("ended_at <>", value, "endedAt");
            return (Criteria) this;
        }

        public Criteria andEndedAtGreaterThan(Date value) {
            addCriterion("ended_at >", value, "endedAt");
            return (Criteria) this;
        }

        public Criteria andEndedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("ended_at >=", value, "endedAt");
            return (Criteria) this;
        }

        public Criteria andEndedAtLessThan(Date value) {
            addCriterion("ended_at <", value, "endedAt");
            return (Criteria) this;
        }

        public Criteria andEndedAtLessThanOrEqualTo(Date value) {
            addCriterion("ended_at <=", value, "endedAt");
            return (Criteria) this;
        }

        public Criteria andEndedAtIn(List<Date> values) {
            addCriterion("ended_at in", values, "endedAt");
            return (Criteria) this;
        }

        public Criteria andEndedAtNotIn(List<Date> values) {
            addCriterion("ended_at not in", values, "endedAt");
            return (Criteria) this;
        }

        public Criteria andEndedAtBetween(Date value1, Date value2) {
            addCriterion("ended_at between", value1, value2, "endedAt");
            return (Criteria) this;
        }

        public Criteria andEndedAtNotBetween(Date value1, Date value2) {
            addCriterion("ended_at not between", value1, value2, "endedAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNull() {
            addCriterion("created_at is null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNotNull() {
            addCriterion("created_at is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtEqualTo(Date value) {
            addCriterion("created_at =", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualTo(Date value) {
            addCriterion("created_at <>", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThan(Date value) {
            addCriterion("created_at >", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("created_at >=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThan(Date value) {
            addCriterion("created_at <", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualTo(Date value) {
            addCriterion("created_at <=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIn(List<Date> values) {
            addCriterion("created_at in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotIn(List<Date> values) {
            addCriterion("created_at not in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtBetween(Date value1, Date value2) {
            addCriterion("created_at between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotBetween(Date value1, Date value2) {
            addCriterion("created_at not between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIsNull() {
            addCriterion("updated_at is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIsNotNull() {
            addCriterion("updated_at is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtEqualTo(Date value) {
            addCriterion("updated_at =", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotEqualTo(Date value) {
            addCriterion("updated_at <>", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThan(Date value) {
            addCriterion("updated_at >", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("updated_at >=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThan(Date value) {
            addCriterion("updated_at <", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanOrEqualTo(Date value) {
            addCriterion("updated_at <=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIn(List<Date> values) {
            addCriterion("updated_at in", values, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotIn(List<Date> values) {
            addCriterion("updated_at not in", values, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtBetween(Date value1, Date value2) {
            addCriterion("updated_at between", value1, value2, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotBetween(Date value1, Date value2) {
            addCriterion("updated_at not between", value1, value2, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andWxOrderIdLikeInsensitive(String value) {
            addCriterion("upper(wx_order_id) like", value.toUpperCase(), "wxOrderId");
            return (Criteria) this;
        }

        public Criteria andWxIdLikeInsensitive(String value) {
            addCriterion("upper(wx_id) like", value.toUpperCase(), "wxId");
            return (Criteria) this;
        }

        public Criteria andWxUserNameLikeInsensitive(String value) {
            addCriterion("upper(wx_user_name) like", value.toUpperCase(), "wxUserName");
            return (Criteria) this;
        }

        public Criteria andWxImageUrlLikeInsensitive(String value) {
            addCriterion("upper(wx_image_url) like", value.toUpperCase(), "wxImageUrl");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }

        public Criteria addByMap(Map<String, Object> map) {
             for (Map.Entry<String, Object> entry : map.entrySet()) {
                if(entry.getValue().toString().startsWith("%")){
                    addCriterion(entry.getKey()+" like",entry.getValue(),null);
                }else{
                    addCriterion(entry.getKey()+" =",entry.getValue(),null);
                }
            }
            return this;
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
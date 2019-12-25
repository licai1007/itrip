package org.java.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class OrderCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer pageNo = 1;

    protected Integer startRow;

    protected Integer pageSize = 10;

    protected String fields;

    public OrderCriteria() {
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

    public void setPageNo(Integer pageNo) {
        this.pageNo=pageNo;
        this.startRow = (pageNo-1)*this.pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setStartRow(Integer startRow) {
        this.startRow=startRow;
    }

    public Integer getStartRow() {
        return startRow;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize=pageSize;
        this.startRow = (pageNo-1)*this.pageSize;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setFields(String fields) {
        this.fields=fields;
    }

    public String getFields() {
        return fields;
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andOrderidIsNull() {
            addCriterion("orderId is null");
            return (Criteria) this;
        }

        public Criteria andOrderidIsNotNull() {
            addCriterion("orderId is not null");
            return (Criteria) this;
        }

        public Criteria andOrderidEqualTo(String value) {
            addCriterion("orderId =", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotEqualTo(String value) {
            addCriterion("orderId <>", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidGreaterThan(String value) {
            addCriterion("orderId >", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidGreaterThanOrEqualTo(String value) {
            addCriterion("orderId >=", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLessThan(String value) {
            addCriterion("orderId <", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLessThanOrEqualTo(String value) {
            addCriterion("orderId <=", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLike(String value) {
            addCriterion("orderId like", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotLike(String value) {
            addCriterion("orderId not like", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidIn(List<String> values) {
            addCriterion("orderId in", values, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotIn(List<String> values) {
            addCriterion("orderId not in", values, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidBetween(String value1, String value2) {
            addCriterion("orderId between", value1, value2, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotBetween(String value1, String value2) {
            addCriterion("orderId not between", value1, value2, "orderid");
            return (Criteria) this;
        }

        public Criteria andPaymentIsNull() {
            addCriterion("payment is null");
            return (Criteria) this;
        }

        public Criteria andPaymentIsNotNull() {
            addCriterion("payment is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentEqualTo(String value) {
            addCriterion("payment =", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentNotEqualTo(String value) {
            addCriterion("payment <>", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentGreaterThan(String value) {
            addCriterion("payment >", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentGreaterThanOrEqualTo(String value) {
            addCriterion("payment >=", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentLessThan(String value) {
            addCriterion("payment <", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentLessThanOrEqualTo(String value) {
            addCriterion("payment <=", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentLike(String value) {
            addCriterion("payment like", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentNotLike(String value) {
            addCriterion("payment not like", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentIn(List<String> values) {
            addCriterion("payment in", values, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentNotIn(List<String> values) {
            addCriterion("payment not in", values, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentBetween(String value1, String value2) {
            addCriterion("payment between", value1, value2, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentNotBetween(String value1, String value2) {
            addCriterion("payment not between", value1, value2, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymenttypeIsNull() {
            addCriterion("paymentType is null");
            return (Criteria) this;
        }

        public Criteria andPaymenttypeIsNotNull() {
            addCriterion("paymentType is not null");
            return (Criteria) this;
        }

        public Criteria andPaymenttypeEqualTo(Integer value) {
            addCriterion("paymentType =", value, "paymenttype");
            return (Criteria) this;
        }

        public Criteria andPaymenttypeNotEqualTo(Integer value) {
            addCriterion("paymentType <>", value, "paymenttype");
            return (Criteria) this;
        }

        public Criteria andPaymenttypeGreaterThan(Integer value) {
            addCriterion("paymentType >", value, "paymenttype");
            return (Criteria) this;
        }

        public Criteria andPaymenttypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("paymentType >=", value, "paymenttype");
            return (Criteria) this;
        }

        public Criteria andPaymenttypeLessThan(Integer value) {
            addCriterion("paymentType <", value, "paymenttype");
            return (Criteria) this;
        }

        public Criteria andPaymenttypeLessThanOrEqualTo(Integer value) {
            addCriterion("paymentType <=", value, "paymenttype");
            return (Criteria) this;
        }

        public Criteria andPaymenttypeIn(List<Integer> values) {
            addCriterion("paymentType in", values, "paymenttype");
            return (Criteria) this;
        }

        public Criteria andPaymenttypeNotIn(List<Integer> values) {
            addCriterion("paymentType not in", values, "paymenttype");
            return (Criteria) this;
        }

        public Criteria andPaymenttypeBetween(Integer value1, Integer value2) {
            addCriterion("paymentType between", value1, value2, "paymenttype");
            return (Criteria) this;
        }

        public Criteria andPaymenttypeNotBetween(Integer value1, Integer value2) {
            addCriterion("paymentType not between", value1, value2, "paymenttype");
            return (Criteria) this;
        }

        public Criteria andPostfeeIsNull() {
            addCriterion("postFee is null");
            return (Criteria) this;
        }

        public Criteria andPostfeeIsNotNull() {
            addCriterion("postFee is not null");
            return (Criteria) this;
        }

        public Criteria andPostfeeEqualTo(String value) {
            addCriterion("postFee =", value, "postfee");
            return (Criteria) this;
        }

        public Criteria andPostfeeNotEqualTo(String value) {
            addCriterion("postFee <>", value, "postfee");
            return (Criteria) this;
        }

        public Criteria andPostfeeGreaterThan(String value) {
            addCriterion("postFee >", value, "postfee");
            return (Criteria) this;
        }

        public Criteria andPostfeeGreaterThanOrEqualTo(String value) {
            addCriterion("postFee >=", value, "postfee");
            return (Criteria) this;
        }

        public Criteria andPostfeeLessThan(String value) {
            addCriterion("postFee <", value, "postfee");
            return (Criteria) this;
        }

        public Criteria andPostfeeLessThanOrEqualTo(String value) {
            addCriterion("postFee <=", value, "postfee");
            return (Criteria) this;
        }

        public Criteria andPostfeeLike(String value) {
            addCriterion("postFee like", value, "postfee");
            return (Criteria) this;
        }

        public Criteria andPostfeeNotLike(String value) {
            addCriterion("postFee not like", value, "postfee");
            return (Criteria) this;
        }

        public Criteria andPostfeeIn(List<String> values) {
            addCriterion("postFee in", values, "postfee");
            return (Criteria) this;
        }

        public Criteria andPostfeeNotIn(List<String> values) {
            addCriterion("postFee not in", values, "postfee");
            return (Criteria) this;
        }

        public Criteria andPostfeeBetween(String value1, String value2) {
            addCriterion("postFee between", value1, value2, "postfee");
            return (Criteria) this;
        }

        public Criteria andPostfeeNotBetween(String value1, String value2) {
            addCriterion("postFee not between", value1, value2, "postfee");
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

        public Criteria andCreatetimeIsNull() {
            addCriterion("createTime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterionForJDBCDate("createTime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("createTime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterionForJDBCDate("createTime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("createTime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterionForJDBCDate("createTime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("createTime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterionForJDBCDate("createTime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("createTime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("createTime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("createTime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNull() {
            addCriterion("updateTime is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNotNull() {
            addCriterion("updateTime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeEqualTo(Date value) {
            addCriterionForJDBCDate("updateTime =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("updateTime <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(Date value) {
            addCriterionForJDBCDate("updateTime >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("updateTime >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(Date value) {
            addCriterionForJDBCDate("updateTime <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("updateTime <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<Date> values) {
            addCriterionForJDBCDate("updateTime in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("updateTime not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("updateTime between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("updateTime not between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andPaymenttimeIsNull() {
            addCriterion("paymentTime is null");
            return (Criteria) this;
        }

        public Criteria andPaymenttimeIsNotNull() {
            addCriterion("paymentTime is not null");
            return (Criteria) this;
        }

        public Criteria andPaymenttimeEqualTo(Date value) {
            addCriterionForJDBCDate("paymentTime =", value, "paymenttime");
            return (Criteria) this;
        }

        public Criteria andPaymenttimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("paymentTime <>", value, "paymenttime");
            return (Criteria) this;
        }

        public Criteria andPaymenttimeGreaterThan(Date value) {
            addCriterionForJDBCDate("paymentTime >", value, "paymenttime");
            return (Criteria) this;
        }

        public Criteria andPaymenttimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("paymentTime >=", value, "paymenttime");
            return (Criteria) this;
        }

        public Criteria andPaymenttimeLessThan(Date value) {
            addCriterionForJDBCDate("paymentTime <", value, "paymenttime");
            return (Criteria) this;
        }

        public Criteria andPaymenttimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("paymentTime <=", value, "paymenttime");
            return (Criteria) this;
        }

        public Criteria andPaymenttimeIn(List<Date> values) {
            addCriterionForJDBCDate("paymentTime in", values, "paymenttime");
            return (Criteria) this;
        }

        public Criteria andPaymenttimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("paymentTime not in", values, "paymenttime");
            return (Criteria) this;
        }

        public Criteria andPaymenttimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("paymentTime between", value1, value2, "paymenttime");
            return (Criteria) this;
        }

        public Criteria andPaymenttimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("paymentTime not between", value1, value2, "paymenttime");
            return (Criteria) this;
        }

        public Criteria andConsigntimeIsNull() {
            addCriterion("consignTime is null");
            return (Criteria) this;
        }

        public Criteria andConsigntimeIsNotNull() {
            addCriterion("consignTime is not null");
            return (Criteria) this;
        }

        public Criteria andConsigntimeEqualTo(Date value) {
            addCriterionForJDBCDate("consignTime =", value, "consigntime");
            return (Criteria) this;
        }

        public Criteria andConsigntimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("consignTime <>", value, "consigntime");
            return (Criteria) this;
        }

        public Criteria andConsigntimeGreaterThan(Date value) {
            addCriterionForJDBCDate("consignTime >", value, "consigntime");
            return (Criteria) this;
        }

        public Criteria andConsigntimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("consignTime >=", value, "consigntime");
            return (Criteria) this;
        }

        public Criteria andConsigntimeLessThan(Date value) {
            addCriterionForJDBCDate("consignTime <", value, "consigntime");
            return (Criteria) this;
        }

        public Criteria andConsigntimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("consignTime <=", value, "consigntime");
            return (Criteria) this;
        }

        public Criteria andConsigntimeIn(List<Date> values) {
            addCriterionForJDBCDate("consignTime in", values, "consigntime");
            return (Criteria) this;
        }

        public Criteria andConsigntimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("consignTime not in", values, "consigntime");
            return (Criteria) this;
        }

        public Criteria andConsigntimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("consignTime between", value1, value2, "consigntime");
            return (Criteria) this;
        }

        public Criteria andConsigntimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("consignTime not between", value1, value2, "consigntime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNull() {
            addCriterion("endTime is null");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNotNull() {
            addCriterion("endTime is not null");
            return (Criteria) this;
        }

        public Criteria andEndtimeEqualTo(Date value) {
            addCriterionForJDBCDate("endTime =", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("endTime <>", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThan(Date value) {
            addCriterionForJDBCDate("endTime >", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("endTime >=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThan(Date value) {
            addCriterionForJDBCDate("endTime <", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("endTime <=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIn(List<Date> values) {
            addCriterionForJDBCDate("endTime in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("endTime not in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("endTime between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("endTime not between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andClosetimeIsNull() {
            addCriterion("closeTime is null");
            return (Criteria) this;
        }

        public Criteria andClosetimeIsNotNull() {
            addCriterion("closeTime is not null");
            return (Criteria) this;
        }

        public Criteria andClosetimeEqualTo(Date value) {
            addCriterionForJDBCDate("closeTime =", value, "closetime");
            return (Criteria) this;
        }

        public Criteria andClosetimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("closeTime <>", value, "closetime");
            return (Criteria) this;
        }

        public Criteria andClosetimeGreaterThan(Date value) {
            addCriterionForJDBCDate("closeTime >", value, "closetime");
            return (Criteria) this;
        }

        public Criteria andClosetimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("closeTime >=", value, "closetime");
            return (Criteria) this;
        }

        public Criteria andClosetimeLessThan(Date value) {
            addCriterionForJDBCDate("closeTime <", value, "closetime");
            return (Criteria) this;
        }

        public Criteria andClosetimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("closeTime <=", value, "closetime");
            return (Criteria) this;
        }

        public Criteria andClosetimeIn(List<Date> values) {
            addCriterionForJDBCDate("closeTime in", values, "closetime");
            return (Criteria) this;
        }

        public Criteria andClosetimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("closeTime not in", values, "closetime");
            return (Criteria) this;
        }

        public Criteria andClosetimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("closeTime between", value1, value2, "closetime");
            return (Criteria) this;
        }

        public Criteria andClosetimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("closeTime not between", value1, value2, "closetime");
            return (Criteria) this;
        }

        public Criteria andShippingnameIsNull() {
            addCriterion("shippingName is null");
            return (Criteria) this;
        }

        public Criteria andShippingnameIsNotNull() {
            addCriterion("shippingName is not null");
            return (Criteria) this;
        }

        public Criteria andShippingnameEqualTo(String value) {
            addCriterion("shippingName =", value, "shippingname");
            return (Criteria) this;
        }

        public Criteria andShippingnameNotEqualTo(String value) {
            addCriterion("shippingName <>", value, "shippingname");
            return (Criteria) this;
        }

        public Criteria andShippingnameGreaterThan(String value) {
            addCriterion("shippingName >", value, "shippingname");
            return (Criteria) this;
        }

        public Criteria andShippingnameGreaterThanOrEqualTo(String value) {
            addCriterion("shippingName >=", value, "shippingname");
            return (Criteria) this;
        }

        public Criteria andShippingnameLessThan(String value) {
            addCriterion("shippingName <", value, "shippingname");
            return (Criteria) this;
        }

        public Criteria andShippingnameLessThanOrEqualTo(String value) {
            addCriterion("shippingName <=", value, "shippingname");
            return (Criteria) this;
        }

        public Criteria andShippingnameLike(String value) {
            addCriterion("shippingName like", value, "shippingname");
            return (Criteria) this;
        }

        public Criteria andShippingnameNotLike(String value) {
            addCriterion("shippingName not like", value, "shippingname");
            return (Criteria) this;
        }

        public Criteria andShippingnameIn(List<String> values) {
            addCriterion("shippingName in", values, "shippingname");
            return (Criteria) this;
        }

        public Criteria andShippingnameNotIn(List<String> values) {
            addCriterion("shippingName not in", values, "shippingname");
            return (Criteria) this;
        }

        public Criteria andShippingnameBetween(String value1, String value2) {
            addCriterion("shippingName between", value1, value2, "shippingname");
            return (Criteria) this;
        }

        public Criteria andShippingnameNotBetween(String value1, String value2) {
            addCriterion("shippingName not between", value1, value2, "shippingname");
            return (Criteria) this;
        }

        public Criteria andShippingcodeIsNull() {
            addCriterion("shippingCode is null");
            return (Criteria) this;
        }

        public Criteria andShippingcodeIsNotNull() {
            addCriterion("shippingCode is not null");
            return (Criteria) this;
        }

        public Criteria andShippingcodeEqualTo(String value) {
            addCriterion("shippingCode =", value, "shippingcode");
            return (Criteria) this;
        }

        public Criteria andShippingcodeNotEqualTo(String value) {
            addCriterion("shippingCode <>", value, "shippingcode");
            return (Criteria) this;
        }

        public Criteria andShippingcodeGreaterThan(String value) {
            addCriterion("shippingCode >", value, "shippingcode");
            return (Criteria) this;
        }

        public Criteria andShippingcodeGreaterThanOrEqualTo(String value) {
            addCriterion("shippingCode >=", value, "shippingcode");
            return (Criteria) this;
        }

        public Criteria andShippingcodeLessThan(String value) {
            addCriterion("shippingCode <", value, "shippingcode");
            return (Criteria) this;
        }

        public Criteria andShippingcodeLessThanOrEqualTo(String value) {
            addCriterion("shippingCode <=", value, "shippingcode");
            return (Criteria) this;
        }

        public Criteria andShippingcodeLike(String value) {
            addCriterion("shippingCode like", value, "shippingcode");
            return (Criteria) this;
        }

        public Criteria andShippingcodeNotLike(String value) {
            addCriterion("shippingCode not like", value, "shippingcode");
            return (Criteria) this;
        }

        public Criteria andShippingcodeIn(List<String> values) {
            addCriterion("shippingCode in", values, "shippingcode");
            return (Criteria) this;
        }

        public Criteria andShippingcodeNotIn(List<String> values) {
            addCriterion("shippingCode not in", values, "shippingcode");
            return (Criteria) this;
        }

        public Criteria andShippingcodeBetween(String value1, String value2) {
            addCriterion("shippingCode between", value1, value2, "shippingcode");
            return (Criteria) this;
        }

        public Criteria andShippingcodeNotBetween(String value1, String value2) {
            addCriterion("shippingCode not between", value1, value2, "shippingcode");
            return (Criteria) this;
        }

        public Criteria andUseridIsNull() {
            addCriterion("userId is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("userId is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(Long value) {
            addCriterion("userId =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(Long value) {
            addCriterion("userId <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(Long value) {
            addCriterion("userId >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(Long value) {
            addCriterion("userId >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(Long value) {
            addCriterion("userId <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(Long value) {
            addCriterion("userId <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<Long> values) {
            addCriterion("userId in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<Long> values) {
            addCriterion("userId not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(Long value1, Long value2) {
            addCriterion("userId between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(Long value1, Long value2) {
            addCriterion("userId not between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andBuyermessageIsNull() {
            addCriterion("buyerMessage is null");
            return (Criteria) this;
        }

        public Criteria andBuyermessageIsNotNull() {
            addCriterion("buyerMessage is not null");
            return (Criteria) this;
        }

        public Criteria andBuyermessageEqualTo(String value) {
            addCriterion("buyerMessage =", value, "buyermessage");
            return (Criteria) this;
        }

        public Criteria andBuyermessageNotEqualTo(String value) {
            addCriterion("buyerMessage <>", value, "buyermessage");
            return (Criteria) this;
        }

        public Criteria andBuyermessageGreaterThan(String value) {
            addCriterion("buyerMessage >", value, "buyermessage");
            return (Criteria) this;
        }

        public Criteria andBuyermessageGreaterThanOrEqualTo(String value) {
            addCriterion("buyerMessage >=", value, "buyermessage");
            return (Criteria) this;
        }

        public Criteria andBuyermessageLessThan(String value) {
            addCriterion("buyerMessage <", value, "buyermessage");
            return (Criteria) this;
        }

        public Criteria andBuyermessageLessThanOrEqualTo(String value) {
            addCriterion("buyerMessage <=", value, "buyermessage");
            return (Criteria) this;
        }

        public Criteria andBuyermessageLike(String value) {
            addCriterion("buyerMessage like", value, "buyermessage");
            return (Criteria) this;
        }

        public Criteria andBuyermessageNotLike(String value) {
            addCriterion("buyerMessage not like", value, "buyermessage");
            return (Criteria) this;
        }

        public Criteria andBuyermessageIn(List<String> values) {
            addCriterion("buyerMessage in", values, "buyermessage");
            return (Criteria) this;
        }

        public Criteria andBuyermessageNotIn(List<String> values) {
            addCriterion("buyerMessage not in", values, "buyermessage");
            return (Criteria) this;
        }

        public Criteria andBuyermessageBetween(String value1, String value2) {
            addCriterion("buyerMessage between", value1, value2, "buyermessage");
            return (Criteria) this;
        }

        public Criteria andBuyermessageNotBetween(String value1, String value2) {
            addCriterion("buyerMessage not between", value1, value2, "buyermessage");
            return (Criteria) this;
        }

        public Criteria andBuyernickIsNull() {
            addCriterion("buyerNick is null");
            return (Criteria) this;
        }

        public Criteria andBuyernickIsNotNull() {
            addCriterion("buyerNick is not null");
            return (Criteria) this;
        }

        public Criteria andBuyernickEqualTo(String value) {
            addCriterion("buyerNick =", value, "buyernick");
            return (Criteria) this;
        }

        public Criteria andBuyernickNotEqualTo(String value) {
            addCriterion("buyerNick <>", value, "buyernick");
            return (Criteria) this;
        }

        public Criteria andBuyernickGreaterThan(String value) {
            addCriterion("buyerNick >", value, "buyernick");
            return (Criteria) this;
        }

        public Criteria andBuyernickGreaterThanOrEqualTo(String value) {
            addCriterion("buyerNick >=", value, "buyernick");
            return (Criteria) this;
        }

        public Criteria andBuyernickLessThan(String value) {
            addCriterion("buyerNick <", value, "buyernick");
            return (Criteria) this;
        }

        public Criteria andBuyernickLessThanOrEqualTo(String value) {
            addCriterion("buyerNick <=", value, "buyernick");
            return (Criteria) this;
        }

        public Criteria andBuyernickLike(String value) {
            addCriterion("buyerNick like", value, "buyernick");
            return (Criteria) this;
        }

        public Criteria andBuyernickNotLike(String value) {
            addCriterion("buyerNick not like", value, "buyernick");
            return (Criteria) this;
        }

        public Criteria andBuyernickIn(List<String> values) {
            addCriterion("buyerNick in", values, "buyernick");
            return (Criteria) this;
        }

        public Criteria andBuyernickNotIn(List<String> values) {
            addCriterion("buyerNick not in", values, "buyernick");
            return (Criteria) this;
        }

        public Criteria andBuyernickBetween(String value1, String value2) {
            addCriterion("buyerNick between", value1, value2, "buyernick");
            return (Criteria) this;
        }

        public Criteria andBuyernickNotBetween(String value1, String value2) {
            addCriterion("buyerNick not between", value1, value2, "buyernick");
            return (Criteria) this;
        }

        public Criteria andBuyerrateIsNull() {
            addCriterion("buyerRate is null");
            return (Criteria) this;
        }

        public Criteria andBuyerrateIsNotNull() {
            addCriterion("buyerRate is not null");
            return (Criteria) this;
        }

        public Criteria andBuyerrateEqualTo(Integer value) {
            addCriterion("buyerRate =", value, "buyerrate");
            return (Criteria) this;
        }

        public Criteria andBuyerrateNotEqualTo(Integer value) {
            addCriterion("buyerRate <>", value, "buyerrate");
            return (Criteria) this;
        }

        public Criteria andBuyerrateGreaterThan(Integer value) {
            addCriterion("buyerRate >", value, "buyerrate");
            return (Criteria) this;
        }

        public Criteria andBuyerrateGreaterThanOrEqualTo(Integer value) {
            addCriterion("buyerRate >=", value, "buyerrate");
            return (Criteria) this;
        }

        public Criteria andBuyerrateLessThan(Integer value) {
            addCriterion("buyerRate <", value, "buyerrate");
            return (Criteria) this;
        }

        public Criteria andBuyerrateLessThanOrEqualTo(Integer value) {
            addCriterion("buyerRate <=", value, "buyerrate");
            return (Criteria) this;
        }

        public Criteria andBuyerrateIn(List<Integer> values) {
            addCriterion("buyerRate in", values, "buyerrate");
            return (Criteria) this;
        }

        public Criteria andBuyerrateNotIn(List<Integer> values) {
            addCriterion("buyerRate not in", values, "buyerrate");
            return (Criteria) this;
        }

        public Criteria andBuyerrateBetween(Integer value1, Integer value2) {
            addCriterion("buyerRate between", value1, value2, "buyerrate");
            return (Criteria) this;
        }

        public Criteria andBuyerrateNotBetween(Integer value1, Integer value2) {
            addCriterion("buyerRate not between", value1, value2, "buyerrate");
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
package com.xiaoyao.pay.model;

import java.math.BigDecimal;
import java.util.Date;

import com.xiaoyao.base.model.BaseVO;

public class CashPool extends BaseVO{
    private Integer id;

    private BigDecimal money;

    private Integer userId;

    private Date createdate;

    private BigDecimal platform;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public BigDecimal getPlatform() {
        return platform;
    }

    public void setPlatform(BigDecimal platform) {
        this.platform = platform;
    }
}
package com.group.lab.model;

import java.math.BigDecimal;
import java.util.Date;

public class Fashions {
    private String numIid;

    private String name;

    private String mainImage;

    private String detailUrl;

    private String shopName;

    private BigDecimal price;

    private Integer monthCount;

    private BigDecimal rate;

    private BigDecimal commision;

    private String nick;

    private String tbkShotUrl;

    private String tbkUrl;

    private String tkl;

    private BigDecimal labPrice;

    private String cue;

    private Integer classifyId;

    private String isSale;

    private Date saleTime;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    public String getNumIid() {
        return numIid;
    }

    public void setNumIid(String numIid) {
        this.numIid = numIid == null ? null : numIid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage == null ? null : mainImage.trim();
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl == null ? null : detailUrl.trim();
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getMonthCount() {
        return monthCount;
    }

    public void setMonthCount(Integer monthCount) {
        this.monthCount = monthCount;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getCommision() {
        return commision;
    }

    public void setCommision(BigDecimal commision) {
        this.commision = commision;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick == null ? null : nick.trim();
    }

    public String getTbkShotUrl() {
        return tbkShotUrl;
    }

    public void setTbkShotUrl(String tbkShotUrl) {
        this.tbkShotUrl = tbkShotUrl == null ? null : tbkShotUrl.trim();
    }

    public String getTbkUrl() {
        return tbkUrl;
    }

    public void setTbkUrl(String tbkUrl) {
        this.tbkUrl = tbkUrl == null ? null : tbkUrl.trim();
    }

    public String getTkl() {
        return tkl;
    }

    public void setTkl(String tkl) {
        this.tkl = tkl == null ? null : tkl.trim();
    }

    public BigDecimal getLabPrice() {
        return labPrice;
    }

    public void setLabPrice(BigDecimal labPrice) {
        this.labPrice = labPrice;
    }

    public String getCue() {
        return cue;
    }

    public void setCue(String cue) {
        this.cue = cue == null ? null : cue.trim();
    }

    public Integer getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Integer classifyId) {
        this.classifyId = classifyId;
    }

    public String getIsSale() {
        return isSale;
    }

    public void setIsSale(String isSale) {
        this.isSale = isSale == null ? null : isSale.trim();
    }

    public Date getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(Date saleTime) {
        this.saleTime = saleTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
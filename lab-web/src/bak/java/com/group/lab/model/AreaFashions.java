package com.group.lab.model;

import java.util.Date;

public class AreaFashions {
    private Integer id;

    private Integer areaId;

    private String areaType;

    private String numIid;

    private String isSale;

    private Date saleTime;

    private String isStick;

    private Date stickTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType == null ? null : areaType.trim();
    }

    public String getNumIid() {
        return numIid;
    }

    public void setNumIid(String numIid) {
        this.numIid = numIid == null ? null : numIid.trim();
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

    public String getIsStick() {
        return isStick;
    }

    public void setIsStick(String isStick) {
        this.isStick = isStick == null ? null : isStick.trim();
    }

    public Date getStickTime() {
        return stickTime;
    }

    public void setStickTime(Date stickTime) {
        this.stickTime = stickTime;
    }
}
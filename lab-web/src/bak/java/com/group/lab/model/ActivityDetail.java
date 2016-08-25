package com.group.lab.model;

public class ActivityDetail {
    private Integer id;

    private Integer activityId;

    private String images;

    private String button;

    private String fashionIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images == null ? null : images.trim();
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button == null ? null : button.trim();
    }

    public String getFashionIds() {
        return fashionIds;
    }

    public void setFashionIds(String fashionIds) {
        this.fashionIds = fashionIds == null ? null : fashionIds.trim();
    }
}
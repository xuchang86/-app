package com.group.lab.model;

import java.util.List;

public class ActivityDetailTwo {
    private Integer id;

    private Integer activityId;

    private String images;

    private List<FashionTaobao> fashions;

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

	public List<FashionTaobao> getFashions() {
		return fashions;
	}

	public void setFashions(List<FashionTaobao> fashions) {
		this.fashions = fashions;
	}
}
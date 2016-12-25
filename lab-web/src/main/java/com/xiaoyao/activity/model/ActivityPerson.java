package com.xiaoyao.activity.model;

import com.xiaoyao.base.model.Person;
import com.xiaoyao.login.model.User;

public class ActivityPerson {
	private Integer id;

	private Integer personId;

	private Integer activityId;

	/** 任务状态 {@link com.xiaoyao.activity.model.TaskState} */
	private String state;

	/** 人员信息 */
	private Person person;

	/** 用户信息 */
	private User user;

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public String getState() {
		return state;
	}

	/**
	 * @return the person
	 */
	public Person getPerson() {
		return person;
	}

	/**
	 * @param person
	 *            the person to set
	 */
	public void setPerson(Person person) {
		this.person = person;
	}

	public void setState(String state) {
		this.state = state == null ? null : state.trim();
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
}
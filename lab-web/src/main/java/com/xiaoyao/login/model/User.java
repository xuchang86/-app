package com.xiaoyao.login.model;

import java.util.Date;

import com.xiaoyao.base.annotation.Id;
import com.xiaoyao.base.model.BaseVO;

public class User extends BaseVO {
	@Id
	private Integer id;

	private String phone;

	private String password;

	private String name;

	private Date birthday;

	private String address;

	private String providerid;

	private String requiredid;

	private String city;

	/** 性别 {@link com.xiaoyao.login.model.Sex} */
	private Integer sex;

	private String username;

	/** 是否已付款 {@link com.xiaoyao.login.model.IsPay} */
	private Integer ispay = IsPay.UN_PAY.getValue();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public String getProviderid() {
		return providerid;
	}

	public void setProviderid(String providerid) {
		this.providerid = providerid == null ? null : providerid.trim();
	}

	public String getRequiredid() {
		return requiredid;
	}

	public void setRequiredid(String requiredid) {
		this.requiredid = requiredid == null ? null : requiredid.trim();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city == null ? null : city.trim();
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	public Integer getIspay() {
		return ispay;
	}

	public void setIspay(Integer ispay) {
		this.ispay = ispay;
	}
}
package com.kg.myapp.vo;

import java.util.Arrays;

public class UsersVO {
	
	private String userid;
	private String password;
	private String name;
	private String tel;
	private byte[] picture;
	private long pictureSize;
	
	@Override
	public String toString() {
		return "UserVO [userid=" + userid + ", password=" + password + ", name=" + name + ", tel=" + tel +
				", picture=" + Arrays.toString(picture) + ", pictureSize=" + pictureSize + "]";
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public long getPictureSize() {
		return pictureSize;
	}

	public void setPictureSize(long pictureSize) {
		this.pictureSize = pictureSize;
	}
	
	
}

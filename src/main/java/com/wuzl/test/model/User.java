package com.wuzl.test.model;

import java.util.Date;

public class User {

	private static final long serialVersionUID = 1671065665836227052L;

	public User(){};
	
	public String userid;
	public String username;
	public String password;
	public String user_groupid;
	public String real_name;
	public String user_phone;
	public String user_email;
	public Date gen_time;
	public Date login_time;
	public Date last_login_time;
	public int login_count;
	public String status;
	public Date brithday;
	public String remark;
	public int login_error;

	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUser_groupid() {
		return user_groupid;
	}
	public void setUser_groupid(String user_groupid) {
		this.user_groupid = user_groupid;
	}
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public Date getGen_time() {
		return gen_time;
	}
	public void setGen_time(Date gen_time) {
		this.gen_time = gen_time;
	}
	public Date getLogin_time() {
		return login_time;
	}
	public void setLogin_time(Date login_time) {
		this.login_time = login_time;
	}
	public Date getLast_login_time() {
		return last_login_time;
	}
	public void setLast_login_time(Date last_login_time) {
		this.last_login_time = last_login_time;
	}
	public int getLogin_count() {
		return login_count;
	}
	public void setLogin_count(int login_count) {
		this.login_count = login_count;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getBrithday() {
		return brithday;
	}
	public void setBrithday(Date brithday) {
		this.brithday = brithday;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getLogin_error() {
		return login_error;
	}
	public void setLogin_error(int login_error) {
		this.login_error = login_error;
	}

}

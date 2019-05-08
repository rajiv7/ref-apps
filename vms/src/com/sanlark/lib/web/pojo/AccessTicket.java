/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 25-Jun-2015 
 */
package com.sanlark.lib.web.pojo;

import java.io.Serializable;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class AccessTicket implements Serializable, HttpSessionBindingListener{
	private static final long serialVersionUID = -757356717142943211L;
	private Long recordId;
	private UserRole userRole;
	
	private String loginId;
	private String name;
	private String email;

	private boolean active;
	
	@Override
	public String toString(){
		return name;
	}

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		System.out.println("Value Bound : " + event.getValue());
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		System.out.println("Value UnBound : " + event.getValue());
	}

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}

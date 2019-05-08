/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 25-Jun-2015 
 */

package com.sanlark.lib.web.pojo;

import java.io.Serializable;

public class ComboOption implements Serializable{
	private static final long serialVersionUID = -4805634096248205531L;
	private long id;
	private String title;
	private boolean active;
	
	public ComboOption(){}
	
	public ComboOption(int id, String title){
		this.id = id;
		this.title = title;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public String toString(){
		return "{" + id + ", " + title + "}";
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}

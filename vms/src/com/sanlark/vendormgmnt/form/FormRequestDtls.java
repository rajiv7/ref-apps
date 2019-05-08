/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 30-Jun-2015 
 */
package com.sanlark.vendormgmnt.form;


/**
 * Use ModelProjectRequest
 */
@Deprecated 
public class FormRequestDtls{

	private String title;
	private String desc;
	private int[] skill;
	
	private int duration;
	private String startDate;
	
	private int location;
	private int category;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int[] getSkill() {
		return skill;
	}

	public void setSkill(int[] skill) {
		this.skill = skill;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
}

/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 25-Jun-2015 
 */

package com.sanlark.vendormgmnt.pojo.dtls;

import com.sanlark.lib.web.pojo.BasePojo;

public class FeedbackDtls extends BasePojo{
	private static final long serialVersionUID = 3992196616511455485L;
	private String title;
	private String desc;
	private int rating;
	private long requestId;
	
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
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public long getRequestId() {
		return requestId;
	}
	public void setRequestId(long requestId) {
		this.requestId = requestId;
	}
	
	@Override
	public String toString(){
		return "{" + title + ", " + rating + "}";
	}
}

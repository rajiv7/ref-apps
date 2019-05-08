package com.sanlark.vendormgmnt.vo.common;

@SuppressWarnings("serial")
public class VoVendorSkillCount extends BaseVo{
	private String name;
	private int matchedCount;
	private double avgFeedback;
	private boolean mailSent;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMatchedCount() {
		return matchedCount;
	}
	public void setMatchedCount(int matchedCount) {
		this.matchedCount = matchedCount;
	}
	public double getAvgFeedback() {
		return avgFeedback;
	}
	public void setAvgFeedback(double avgFeedback) {
		this.avgFeedback = avgFeedback;
	}
	public boolean isMailSent() {
		return mailSent;
	}
	public void setMailSent(boolean mailSent) {
		this.mailSent = mailSent;
	}
}

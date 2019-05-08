/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 25-Jun-2015 
 */

package com.sanlark.vendormgmnt.pojo.common;

import com.sanlark.lib.web.pojo.IEnumComboLoader;

public enum ProjectDuration implements IEnumComboLoader{
	QUARTER (1, "0-3 months"),
	HALF_YEAR(2, "3-6 months"),
	YEAR(3, "6-12 months"),
	MORE_THAN_YEAR(4, "> 12 months")
	;
	
	private ProjectDuration(int id, String title){
		this.id = id;
		this.title = title;
	}
	
	public final int id;
	public final String title;
	
	public int getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
}

/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 25-Jun-2015 
 */

package com.sanlark.lib.web.pojo;

public enum AccessLevel {
	NONE(0),
	VIEW(1),
	CREATE(2),
	UPDATE(3),
	DELETE(4)
	;
	
	private AccessLevel(int id){
		this.id = id;
	}
	
	public final int id;

	public int getId() {
		return id;
	}
	
	@Override
	public String toString(){
		return "" + id;
	}
}

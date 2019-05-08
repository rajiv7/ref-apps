/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 08-Jul-2015 
 */
package com.sanlark.lib.web.pojo;

import java.io.Serializable;

public class KeyValPair<K, V> implements Serializable{
	private static final long serialVersionUID = 3618810600315542452L;
	private K key;
	private V value;
	private Long userRefId;
	
	public K getKey() {
		return key;
	}
	public void setKey(K key) {
		this.key = key;
	}
	public V getValue() {
		return value;
	}
	public void setValue(V value) {
		this.value = value;
	}
	
	@Override
	public String toString(){
		return "{" + key + ", " + value + "}";
	}
	
	public Long getUserRefId() {
		return userRefId;
	}
	public void setUserRefId(Long userRefId) {
		this.userRefId = userRefId;
	}
}

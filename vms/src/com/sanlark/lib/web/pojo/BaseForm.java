/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 25-Jun-2015 
 */

package com.sanlark.lib.web.pojo;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class BaseForm implements Serializable, IValidator{
	protected final PageMsg pageMsg;
	
	public BaseForm(){
		pageMsg = new PageMsg();
	}
	
	@Override
	public final PageMsg validate() {
		doValidate();
		return pageMsg;
	}
	
	protected abstract void doValidate();
}

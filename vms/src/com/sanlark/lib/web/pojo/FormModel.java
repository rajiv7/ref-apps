/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 01-Jul-2015 
 */
package com.sanlark.lib.web.pojo;

@SuppressWarnings("serial")
public abstract class FormModel extends BasePojo implements IValidator{
	protected final PageMsg pageMsg;
	
	public FormModel(){
		pageMsg = new PageMsg();
	}
	
	@Override
	public final PageMsg validate() {
		doValidate();
		return pageMsg;
	}

	protected void doValidate(){
		
	}
}

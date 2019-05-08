/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 01-Jul-2015 
 */
package com.sanlark.lib.core.datalayer;

public class TransactionException extends Exception{
	private static final long serialVersionUID = -5974007561652625531L;
	
	public TransactionException(){
		super();
	}
	
	public TransactionException(String reason){
		super(reason);
	}
	
	public TransactionException(Throwable ex){
		super(ex);
	}
}

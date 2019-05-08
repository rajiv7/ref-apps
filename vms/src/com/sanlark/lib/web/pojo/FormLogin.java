/**
\ * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 25-Jun-2015 
 */

package com.sanlark.lib.web.pojo;

import com.sanlark.lib.core.util.CommonUtil;

@SuppressWarnings("serial")
public class FormLogin extends BaseForm{
	private String loginId;
	private String passwd;
	private boolean remMe;
	private String redirectPath;
	
	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	@Override
	public void doValidate() {
		if(CommonUtil.isEmpty(loginId)){
			pageMsg.setError("validation.invalid.login", "loginId");
		}else if(CommonUtil.isEmpty(passwd)){
			pageMsg.setError("validation.invalid.passwd", "passwd");
		}else{
			loginId = loginId.toLowerCase();
		}
	}
	
	@Override
	public String toString(){
		return loginId;
	}

	public boolean isRemMe() {
		return remMe;
	}

	public void setRemMe(boolean remMe) {
		this.remMe = remMe;
	}

	public String getRedirectPath() {
		return redirectPath;
	}

	public void setRedirectPath(String redirectPath) {
		this.redirectPath = redirectPath;
	}
}

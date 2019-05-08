/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 25-Jun-2015 
 */

package com.sanlark.lib.web.util;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sanlark.lib.core.util.CommonUtil;
import com.sanlark.lib.core.util.SecurityUtil;
import com.sanlark.lib.web.pojo.ComboOption;
import com.sanlark.lib.web.pojo.KeyValPair;
import com.sanlark.vendormgmnt.datalayer.BatchTableMapping;
import com.sanlark.vendormgmnt.logicLayer.IComboOptionService;
import com.sanlark.vendormgmnt.vo.common.BaseVo;

public class WebUtil {
	/*private static String contextPath;
	
	public static synchronized void init(ServletContext context){
		if(contextPath == null){
			contextPath = context.getContextPath();
		}
	}
	
	public static String getContextPath(){
		return contextPath + "/";
	}
	*/
	public static String getContextPath(HttpServletRequest request){
		return request.getContextPath() + "/";
	}
	
	public static String buildUrlPath(HttpServletRequest request, String url){
		return request.getContextPath() + "/" + url + AppConfigConst.ACTION_SUFFIX;
	}
	
	public static String isChecked(int[] arr, int val){
		if(arr == null){
			return "";
		}
		String out = "";
		for(int t : arr){
			if(t == val){
				out = " checked='checked' ";
			}
		}
		return out;
	}
	
	public static String toJson(Object obj){
		return new Gson().toJson(obj);
	}
	
	public static String mergeRecordId(Long recordId, Long userRefId) {
		KeyValPair<Long, String> obj = new KeyValPair<Long, String>();
		obj.setKey(recordId);
		obj.setUserRefId(userRefId);
		obj.setValue("dummy-field");
		String jsonString = toJson(obj);
		return SecurityUtil.encrypt(jsonString);
	}
	
	public static KeyValPair<Long, String> getRecordInfo(String key){
		if(CommonUtil.isEmpty(key)){
			return null;
		}
		try{
			String jsonString = SecurityUtil.decrypt(key);
			
			Gson gson = new Gson();
	        Type type = new TypeToken<KeyValPair<Long, String>>(){}.getType();
			KeyValPair<Long, String> obj = gson.fromJson(jsonString, type);
			return obj;
		}catch(Exception ex){
			// TODO Log -- Invalid Access
			ex.printStackTrace();
		}
		return null;
	}
	
	public static void populate(IComboOptionService comboService, HttpServletRequest request, String attrKey,
			BatchTableMapping mappingTable) {
		List<ComboOption> col = comboService.load(mappingTable);
		request.setAttribute(attrKey, col);
	}
	
	public static <T extends BaseVo> void buildAccessId(List<T> col) {
		if(col == null){
			return;
		}
		for(T obj : col){
			String accessId = mergeRecordId(obj.getRecordId(), null);
			obj.setAccessId(accessId);
		}
	}

	public static List<Long> parseAccessId(List<String> accessIdCol) {
		if(accessIdCol == null){
			return null;
		}
		
		List<Long> recordIdCol = new ArrayList<Long>(accessIdCol.size());
		
		for(String accessId : accessIdCol){
			KeyValPair<Long, String> valPair = getRecordInfo(accessId);
			recordIdCol.add(valPair.getKey());
		}
		return recordIdCol;
	}
}

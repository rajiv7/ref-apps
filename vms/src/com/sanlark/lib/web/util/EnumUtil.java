/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 25-Jun-2015 
 */

package com.sanlark.lib.web.util;

import java.util.ArrayList;
import java.util.List;

import com.sanlark.lib.web.pojo.ComboOption;
import com.sanlark.lib.web.pojo.IEnumComboLoader;

public class EnumUtil {
	public static <T extends IEnumComboLoader> List<ComboOption> load(Class<T> className) {
		List<ComboOption> col = new ArrayList<ComboOption>();
		for (T obj : className.getEnumConstants()) {
			col.add(new ComboOption(obj.getId(), obj.getTitle()));
		}
		return col;
	}
	
	public static <T extends IEnumComboLoader> T get(Class<T> className, int id){
		T enumObj = null;
		for (T obj : className.getEnumConstants()) {
			if(obj.getId() == id){
				enumObj = obj;
				break;
			}
		}
		return enumObj;
	}
	
	public static <T extends IEnumComboLoader> String getTitle(Class<T> className, int id){
		T enumObj = get(className, id);
		if(enumObj == null){
			return "";
		}
		return enumObj.getTitle();
	}
}

package com.perpule.api.utils;

import java.util.HashMap;
import org.json.JSONObject;

import com.perpule.ultraPos.common.UltraPOSCommonUtil;

public class loginUtils extends UltraPOSCommonUtil{
	
	public static HashMap<String, String> map() {
		HashMap<String, String> hmap = new HashMap<String, String>();
		hmap.put("emailId", getProp("adminEmail","common"));
		hmap.put("password", getProp("adminPassword","common"));	
		return hmap;
	}
	
	public static HashMap<String, String> createStaffMap() {
		HashMap<String, String> hmap = new HashMap<String, String>();
		hmap.put("emailId", "sometest@perpule.com");
		hmap.put("mobileNumber", "8887776665");
		hmap.put("password", "sometest");
		hmap.put("posPassword", "sometest");
		hmap.put("posUsername", "sometest");
		hmap.put("shopId", "802");
		hmap.put("staffName", "sometest");
		return hmap;
	}
	
	
	public static org.json.JSONObject getJson() {
		JSONObject jObject = new JSONObject();
	    jObject.accumulate("mobileNumber", getProp("mobile","common"));
	    jObject.accumulate("emailId", getProp("email","common"));
	    return jObject;

	}

}

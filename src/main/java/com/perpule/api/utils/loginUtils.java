package com.perpule.api.utils;

import java.util.HashMap;
import org.json.JSONObject;

import com.perpule.ultraPos.common.UltraPOSCommonUtil;

public class loginUtils extends UltraPOSCommonUtil{
	
	public static HashMap<String, String> map() {
		HashMap<String, String> hmap = new HashMap<String, String>();
		String env = getProp("env","common");
		if(env.contentEquals("QA")) {
			hmap.put("emailId", getProp("adminEmail","common"));
			hmap.put("password", getProp("adminPassword","common"));
		}else if(env.contentEquals("PROD")) {
			hmap.put("emailId", getProp("adminId","common"));
			hmap.put("password", getProp("adminPass","common"));
		}else {
			System.out.println("Check Environment");
		}
			
		return hmap;
	}
	
	public static HashMap<String, String> createStaffMap() {
		HashMap<String, String> hmap = new HashMap<String, String>();
		hmap.put("emailId", getProp("staffEmail","common"));
		hmap.put("mobileNumber", getProp("staffMobile","common"));
		hmap.put("password", "sometest");
		hmap.put("posPassword", "sometest");
		hmap.put("posUsername", "sometest");
		hmap.put("shopId", getProp("shopId","common"));
		hmap.put("staffName", "sometest");
		return hmap;
	}
	
	
	public static org.json.JSONObject getJson() {
		JSONObject jObject = new JSONObject();
	    jObject.accumulate("mobileNumber", getProp("mobile","common"));
	    jObject.accumulate("emailId", getProp("email","common"));
	    return jObject;
	}
	
	
	public static org.json.JSONObject addItemJson() {
		JSONObject jObject = new JSONObject();
	    jObject.accumulate("scanned_barcode", "100000375");
	    jObject.accumulate("void", false);
	    jObject.accumulate("quantity", 1);
	    return jObject;
	}
	
	
	public static org.json.JSONObject quantity() {
		JSONObject jObject = new JSONObject();
	    jObject.accumulate("scanned_barcode", "100000375");
	    jObject.accumulate("void", false);
	    jObject.accumulate("quantity", 8);
	    return jObject;
	}
	
	
	public static org.json.JSONObject clearCart() {
		JSONObject jObject = new JSONObject();
	    jObject.accumulate("voidonly", false);
	    return jObject;
	}
	
	
	public static org.json.JSONObject voidLine() {
		JSONObject jObject = new JSONObject();
		jObject.accumulate("username", null);
	    jObject.accumulate("passcode", null);
	    jObject.accumulate("void", true);
	    jObject.accumulate("quantity", 0);
	    return jObject;
	}

}

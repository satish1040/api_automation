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
	
	
	public static org.json.JSONObject createCartJson() {
		JSONObject jObject = new JSONObject();
		jObject.accumulate("customer_mobile", getProp("mobile","common"));
	    jObject.accumulate("address", "");
	    jObject.accumulate("gstin", "");
	    jObject.accumulate("name", "");
	    jObject.accumulate("state_code", "");
	    return jObject;
	}
	
	
	public static org.json.JSONObject checkout() {
		JSONObject jObject = new JSONObject();
		jObject.accumulate("cart", getProp("cartId","token"));
		jObject.accumulate("customer_mobile", getProp("mobile","common"));
	    jObject.accumulate("address", "");
	    jObject.accumulate("gstin", "");
	    jObject.accumulate("name", "");
	    jObject.accumulate("state_code", "");
	    jObject.accumulate("city", "");
	    return jObject;
	}
	
	
	public static org.json.JSONObject payment() {
		JSONObject jObject = new JSONObject();
		jObject.accumulate("type", "INPUT");
		jObject.accumulate("payment_method", "CASH");
	    jObject.accumulate("enabled", true);
	    jObject.accumulate("config_list", JSONObject.NULL);
	    jObject.accumulate("name", "Cash");
	    jObject.accumulate("amount", Integer.valueOf(getProp("amount","token")));
	    jObject.accumulate("img", "../../assets/images/payment-tenders/cashOption.svg");
	    jObject.accumulate("checked", true);
	    jObject.accumulate("request_metadata", new JSONObject());
	    jObject.accumulate("id", JSONObject.NULL);
	    return jObject;
	}
	
	
	public static org.json.JSONObject loginJson() {
		JSONObject jObject = new JSONObject();
	    jObject.accumulate("username", getProp("dposId","common"));
	    jObject.accumulate("password", getProp("dposPass","common"));
	    jObject.accumulate("delete_token", true);
	    jObject.accumulate("do_not_check_token", false);
	    jObject.accumulate("mac_id", "98:5a:eb:8e:90:62");
	    return jObject;
	}
	
	
	public static org.json.JSONObject addItemJson() {
		JSONObject jObject = new JSONObject();
	    jObject.accumulate("scanned_barcode", "scanit");
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

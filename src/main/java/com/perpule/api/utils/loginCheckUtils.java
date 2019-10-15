package com.perpule.api.utils;

import java.util.HashMap;

public class loginCheckUtils {
	
	public static HashMap<String, String> map() {
		HashMap<String, String> hmap = new HashMap<String, String>();
		hmap.put("emailId", "tester@perpule.com");
		hmap.put("password", "admin");
		
		return hmap;
	}

}

package com.perpule.api.utils;

import java.util.HashMap;

public class CompletePaymentUtils {
	
	public static HashMap<String, String> map() {
		HashMap<String, String> hmap = new HashMap<String, String>();
		hmap.put("paymentId", "1574300001");
		hmap.put("orderId", "1568890049");
		hmap.put("paymentGatewayRequestId", "");
		hmap.put("paymentGatewayConfirmationId", "");
		hmap.put("flowType", "1");
		hmap.put("isDummyPayment", "1");
		hmap.put("isAutoVerificationAllowed", "true");
			
		return hmap;
	}

}

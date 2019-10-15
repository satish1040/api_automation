package com.perpule.ultraPos.common;
import java.util.HashMap;

import org.json.JSONObject;
import org.testng.Assert;

import com.perpule.api.utils.CompletePaymentUtils;
import com.perpule.api.utils.appUpdateUtils;
import com.perpule.api.utils.loginCheckUtils;

import io.restassured.response.Response;

public class GetDetails extends UltraPOSCommonUtil{
	
	
	

	public void getData(String mobileNo) throws Exception{
		String baseurl = "prodbaseurl";
		String propertyUrl = "CustomerDetailsUrl";
		String url = urlBuilderWithDataProvider(baseurl, propertyUrl, mobileNo);
		Response response = getRequest(url);
		assertSuccessStatus(response);
	    JSONObject data = new JSONObject(response.body().asString());
	    String customerId = String.valueOf(data.get("customerId"));
	    Assert.assertTrue(!customerId.isEmpty(),"Customer Id is empty for mobile number "+mobileNo);
	    
	}
	
	
	
	public void checkShopConfig() {
		String baseurl = "shopConfigBaseUrl";
		String propertyUrl = "shopConfigUrl";
		String url = urlBuilder(baseurl, propertyUrl);
		Response response = postRequest(url,"shop_config.json");
		assertSuccessStatus(response);
		System.out.println(response.body().asString());
		
	}
	
	
	
	public void checkLogin() {
		HashMap<String, String> hsMap = loginCheckUtils.map();
		String baseurl = "loginBaseUrl";
		String propertyUrl = "loginUrl";
		String url = urlBuilder(baseurl, propertyUrl);
		Response response = postRequestWithFormData(url, hsMap);
		assertSuccessStatus(response);
		System.out.println(response.body().asString());
		
	}
	
	
	
	public void checkAppUpdate() {
		HashMap<String, String> hsMap = appUpdateUtils.map();
		String baseurl = "prodbaseurl";
		String propertyUrl = "appUpdateUrl"; 
		String url = urlBuilder(baseurl, propertyUrl);
		Response response = postRequestWithFormData(url, hsMap);
		assertSuccessStatus(response);
		System.out.println(response.body().asString());
		
	}
	
	
	
	public void checkCompletePayment() {
		HashMap<String, String> hsMap = CompletePaymentUtils.map();
		String baseurl = "prodbaseurl";
		String propertyUrl = "paymentComplete";
		String url = urlBuilder(baseurl, propertyUrl);
		Response response = postRequestWithFormData(url, hsMap);
		assertSuccessStatus(response);
		System.out.println(response.body().asString());
		
	}

	
}	



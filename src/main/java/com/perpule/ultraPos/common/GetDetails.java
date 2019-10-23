package com.perpule.ultraPos.common;
import java.util.HashMap;
import org.json.JSONObject;
import org.testng.Assert;

import com.perpule.api.utils.CompletePaymentUtils;
import com.perpule.api.utils.appUpdateUtils;
import com.perpule.api.utils.loginUtils;

import io.restassured.response.Response;

public class GetDetails extends UltraPOSCommonUtil{


	public void customerData() throws Exception{
		String mobileNo = getProp("mobile","common");
		String baseurl = "prodUrl";
		String propertyUrl = "CustomerDetailsUrl";
		String url = urlBuilderWithParam(baseurl, propertyUrl, mobileNo);
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



	public void checkLogin() throws Exception {
		HashMap<String, String> hsMap = loginUtils.map();
		String baseurl = getBaseUrl();
		String propertyUrl = "loginUrl";	
		String url = urlBuilder(baseurl, propertyUrl);
		System.out.println(url);
		Response response = postRequestWithFormData(url, hsMap);
		assertSuccessStatus(response);
		System.out.println(response.body().asString());	

	}
	
	
	
	public void createStaff() throws Exception {
		HashMap<String, String> hsMap = loginUtils.createStaffMap();
		String baseurl = getBaseUrl();
		String propertyUrl = "createStaff";	
		String url = urlBuilder(baseurl, propertyUrl);
		System.out.println(url);
		Response response = postRequestWithAuthToken(url, hsMap);
		assertSuccessStatus(response);
		System.out.println(response.body().asString());	

	}
	
	
	
	public void sendOTP() throws Exception {
		JSONObject Object = loginUtils.getJson();
		String baseurl = getBaseUrl();
		String propertyUrl = "sendOTP";
		String url = urlBuilder(baseurl, propertyUrl);
		System.out.println(url);
		Response response = postRequestWithJsonData(url, Object);
		assertSuccessStatus(response);
		System.out.println(response.body().asString());	

	}
	
	
	
	public void validateOTP() throws Exception {
		String mobileNo = getProp("mobile","common");
		String baseurl = getBaseUrl();
		String getUrl = urlBuilderWithParam(baseurl, "getOTP", mobileNo);
		String validateUrl = urlBuilder(baseurl, "validateOTP");
		Response getResponse = getRequest(getUrl);
		assertSuccessStatus(getResponse);
		System.out.println(getResponse.body().asString());
		JSONObject data = new JSONObject(getResponse.body().asString());
		String otp = String.valueOf(data.get("oneTimePassword"));
		System.out.println(otp);
		JSONObject Object = loginUtils.getJson();
		Object.accumulate("otp", otp);
		System.out.println(Object.toString());
		Response response = postRequestWithJsonData(validateUrl, Object);
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



	public String getBaseUrl() throws Exception{
		String baseurl = null;
		String env = getProp("env","common");
		if(env.contentEquals("QA")) {
			baseurl = "QaBaseUrl";
		}else if(env.contentEquals("PROD")) {
			baseurl = "ProdBaseUrl";
		}else {
			System.out.println("Check Environment");
		}
		return baseurl;

	}


}	



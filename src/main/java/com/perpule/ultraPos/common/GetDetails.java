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
		String url = urlBuilderWithParam(baseurl, propertyUrl, getProp("shopConfigId","common"));
		Response response = postRequest(url,"shop_config.json");
		assertSuccessStatus(response);
		//System.out.println(response.body().asString());

	}



	public void checkLogin() throws Exception {
		HashMap<String, String> hsMap = loginUtils.map();
		String baseurl = getBaseUrl();
		String propertyUrl = "loginUrl";	
		String url = urlBuilder(baseurl, propertyUrl);
		System.out.println(url);
		Response response = postRequestWithFormData(url, hsMap);
		assertSuccessStatus(response);
		System.out.println("Logged in");	
		JSONObject data = new JSONObject(response.body().asString());
		JSONObject authObj = data.getJSONObject("authenticationDetails");
		String token = String.valueOf(authObj.get("authToken"));
		writeProp("authToken", "Bearer " + token);
		System.out.println("Authorization token stored successfully");

	}
	
	
	
	public void createStaff() throws Exception {
		HashMap<String, String> hsMap = loginUtils.createStaffMap();
		String baseurl = getBaseUrl();
		String propertyUrl = "createStaff";	
		String token = getProp("authToken","token");
		String url = urlBuilder(baseurl, propertyUrl);
		Response response = postRequestWithAuthToken(url, token, hsMap);
		assertSuccessStatus(response);
		System.out.println("Staff created successfully");	

	}
	
	
	public void staff() throws Exception {
		String baseurl = getBaseUrl();
		String propertyUrl = "staffDetails";
		String token = getProp("authToken","token");
		String url = urlBuilderWithParam(baseurl, propertyUrl, getProp("staffMobile","common"));
		Response response = getRequestWithAuthToken(url, token);
		assertSuccessStatus(response);
		System.out.println("Staff details retrived successfully");	

	}
	
	
	
	public void staffUpdate() throws Exception {
		JSONObject Object = new JSONObject(getProp("staffUpdateJson","common"));
		String baseurl = getBaseUrl();
		String propertyUrl = "staffUpdate";
		String url = urlBuilder(baseurl, propertyUrl);
		String token = getProp("authToken","token");
		Response response = postRequestWithJsonData(url, token, Object);
		assertSuccessStatus(response);
		System.out.println("Staff role updated successfully");	

	}
	
	
	public void sendOTP() throws Exception {
		JSONObject Object = loginUtils.getJson();
		String baseurl = getBaseUrl();
		String propertyUrl = "sendOTP";
		String url = urlBuilder(baseurl, propertyUrl);
		String token = null;
		Response response = postRequestWithJsonData(url, token, Object);
		assertSuccessStatus(response);
		System.out.println("OTP sent successfully. Please wait for five seconds...");	
		Thread.sleep(5000);

	}
	
	
	public void validateOTP() throws Exception {
		String mobileNo = getProp("mobile","common");
		String baseurl = getBaseUrl();
		String getUrl = urlBuilderWithParam(baseurl, "getOTP", mobileNo);
		String validateUrl = urlBuilder(baseurl, "validateOTP");
		Response getResponse = getRequest(getUrl);
		assertSuccessStatus(getResponse);
		//System.out.println(getResponse.body().asString());
		JSONObject data = new JSONObject(getResponse.body().asString());
		String otp = String.valueOf(data.get("oneTimePassword"));
		System.out.println("Recived OTP : "+ otp);
		JSONObject Object = loginUtils.getJson();
		Object.accumulate("otp", otp);
		String token = null;
		Response response = postRequestWithJsonData(validateUrl, token, Object);
		assertSuccessStatus(response);
		System.out.println("OTP submitted and validated successfully");
		 	

	}
	
	
	public void addItem() throws Exception {
		JSONObject Object = loginUtils.addItemJson();
		String baseurl = getProp("preProdDbaseurl","url");		// Dashboard Base Url
		String cartUrl = getProp("cart","url");
		String cartId = getProp("cartId","token");
		String propertyUrl = getProp("addItem","url");
		String url = baseurl+cartUrl+cartId+propertyUrl;
		System.out.println(url);
		String token = getProp("authTokenCart","token");
		Response response = postRequestWithJsonData(url, token, Object);
		assertSuccessStatus201(response);
		System.out.println("Item added to cart successfully");
		JSONObject data = new JSONObject(response.body().asString());
		String itemId = String.valueOf(data.get("id"));
		writeProp("itemId", itemId);
		System.out.println("Item id " +itemId+ ", saved in properties");

	}
	
	
	public void verifyToken() throws Exception {
		String baseurl = "preProdDbaseurl";
		String propertyUrl = "verifyToken";
		String token = getProp("authTokenCart","token");
		String url = urlBuilder(baseurl, propertyUrl);
		Response response = getRequestWithAuthToken(url, token);
		assertSuccessStatus(response);
		System.out.println("Token validated successfully");	

	}
	
	
	public void changeQuantity() throws Exception {
		JSONObject Object = loginUtils.quantity();
		String baseurl = getProp("preProdDbaseurl","url");		// Dashboard Base Url
		String cartUrl = getProp("cart","url");
		String cartId = getProp("cartId","token");
		String propertyUrl = getProp("addItem","url");
		String url = baseurl+cartUrl+cartId+propertyUrl;
		String token = getProp("authTokenCart","token");
		Response response = postRequestWithJsonData(url, token, Object);
		assertSuccessStatus201(response);
		System.out.println("Item quantity changed successfully");

	}
	
	
	public void cartHold() throws Exception {
		String baseurl = getProp("preProdDbaseurl","url");		// Dashboard Base Url
		String cartUrl = getProp("cart","url");
		String cartId = getProp("cartId","token");
		String propertyUrl = getProp("holdCart","url");
		String url = baseurl+cartUrl+cartId+propertyUrl;
		String token = getProp("authTokenCart","token");
		Response response = authPostRequest(url, token);
		assertSuccessStatus(response);
		System.out.println("Cart id " +getProp("cartId","token")+ " holded successfully");

	}
	
	
	public void clearCart() throws Exception {
		JSONObject Object = loginUtils.addItemJson();
		String baseurl = getProp("preProdDbaseurl","url");		// Dashboard Base Url
		String cartUrl = getProp("cart","url");
		String cartId = getProp("cartId","token");
		String propertyUrl = getProp("deleteCart","url");
		String url = baseurl+cartUrl+cartId+propertyUrl;
		String token = getProp("authTokenCart","token");
		Response response = postRequestWithJsonData(url, token, Object);
		assertSuccessStatus204(response);
		System.out.println("Cart cleared successfully");

	}
	
	
	public void getVoid() throws Exception {
		JSONObject Object = loginUtils.voidLine();
		String baseurl = getProp("preProdDbaseurl","url");		// Dashboard Base Url
		String cartUrl = getProp("cart","url");
		String cartId = getProp("cartId","token");
		String propertyUrl = getProp("addItem","url");
		String itemId = getProp("itemId","token");
		String url = baseurl+cartUrl+cartId+propertyUrl+"/";
		String token = getProp("authTokenCart","token");
		Response response = patchRequestWithJsonData(url, token, Object);
		assertSuccessStatus(response);
		System.out.println("Item id " +itemId+ " voided successfully");

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
		//System.out.println(response.body().asString());

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



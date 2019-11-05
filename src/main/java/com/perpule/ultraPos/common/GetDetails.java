package com.perpule.ultraPos.common;
import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import com.perpule.api.utils.CompletePaymentUtils;
import com.perpule.api.utils.appUpdateUtils;
import com.perpule.api.utils.loginUtils;

import io.restassured.http.ContentType;
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


	public void login() throws Exception {
		JSONObject Object = loginUtils.loginJson();
		String baseurl = "preProdDbaseurl";
		String propertyUrl = "login";
		String token = null;
		String url = urlBuilder(baseurl, propertyUrl);
		Response response = postRequestWithJsonData(url, token, Object);
		assertSuccessStatus(response);
		JSONObject data = new JSONObject(response.body().asString());
		token = "Token " + String.valueOf(data.get("token"));
		writeProp("authTokenCart", token);
		JSONObject staff = data.getJSONObject("staff");
		int shopId = staff.getInt("shop_id");
		writeProp("shopId", String.valueOf(shopId));
		System.out.println("Login success for shopId " + shopId + " with authorization token " +String.valueOf(data.get("token")));

	}


	public void logout() throws Exception {
		String baseurl = "preProdDbaseurl";
		String propertyUrl = "logout";
		String token = getProp("authTokenCart","token");
		String url = urlBuilder(baseurl, propertyUrl);
		Response response = getRequestWithAuthToken(url, token);
		assertSuccessStatus(response);
		System.out.println("Logout success");	

	}


	public void cartCreate() throws Exception {
		JSONObject Object = loginUtils.createCartJson();
		String baseurl = getProp("preProdDbaseurl","url");		// Dashboard Base Url
		String propertyUrl = getProp("createCart","url");
		String url = baseurl+propertyUrl;
		String token = getProp("authTokenCart","token");
		Response response = postRequestWithJsonData(url, token, Object);
		assertSuccessStatus201(response);
		JSONObject data = new JSONObject(response.body().asString());
		String cartId = String.valueOf(data.get("id"));
		writeProp("cartId", cartId);
		System.out.println("Cart id " +cartId+ " is ready for customer " + getProp("mobile","common"));

	}


	public void addItem() throws Exception {
		JSONObject Object = loginUtils.addItemJson();
		Object.put("scanned_barcode", "100000375");
		String baseurl = getProp("preProdDbaseurl","url");		// Dashboard Base Url
		String cartUrl = getProp("cart","url");
		String cartId = getProp("cartId","token");
		String propertyUrl = getProp("addItem","url");
		String url = baseurl+cartUrl+cartId+propertyUrl;
		String token = getProp("authTokenCart","token");
		Response response = postRequestWithJsonData(url, token, Object);
		assertSuccessStatus201(response);
		JSONObject data = new JSONObject(response.body().asString());
		String itemId = String.valueOf(data.get("id"));
		writeProp("itemId", itemId);
		System.out.println("Item id " +itemId+ " added to cart successfully");

	}


	public void addMultipleItems() throws Exception {
		JSONObject searchObj = new JSONObject(searchByName(getProp("searchByName","token"), getProp("shopId","token")));
		JSONArray resultArray = searchObj.getJSONArray("result");
		for(int i = 0; i <= resultArray.length()-1; i++) {
			JSONObject obj = resultArray.getJSONObject(i);
			JSONArray barcodeArray = obj.getJSONArray("barcodes");
			String barcode = barcodeArray.getString(0);
			JSONObject Object = loginUtils.addItemJson();
			Object.put("scanned_barcode", barcode);
			int quantity = ThreadLocalRandom.current().nextInt(1, 5 + 1);
			Object.put("quantity", quantity);
			String baseurl = getProp("preProdDbaseurl","url");		// Dashboard Base Url
			String cartUrl = getProp("cart","url");
			String cartId = getProp("cartId","token");
			String propertyUrl = getProp("addItem","url");
			String url = baseurl+cartUrl+cartId+propertyUrl;
			String token = getProp("authTokenCart","token");
			Response response = given().header("Authorization", token).contentType(ContentType.JSON).body(Object.toString()).post(url);//postRequestWithJsonData(url, token, Object);
			if(response.getStatusCode() == 201) {
			assertSuccessStatus201(response);
			JSONObject data = new JSONObject(response.body().asString());
			String itemId = String.valueOf(data.get("id"));
			System.out.println("Item id : " +itemId+ ", Quantity : " +quantity+ " added to cart successfully");
			}else if(response.getStatusCode() == 404) {
				System.out.println("Barcode id : " + barcode + " is not available for the shopId : " + getProp("shopId","token"));
			}else {
				System.out.println("Invalid Response");
			}
			
		}

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
		System.out.println("Cart id " + cartId + " holded successfully");

	}


	public void cartRecall() throws Exception {
		String baseurl = getProp("preProdDbaseurl","url");		// Dashboard Base Url
		String cartId = getProp("cartId","token");
		String propertyUrl = getProp("recallCart","url").replace("#", cartId);
		String url = baseurl+propertyUrl;
		String token = getProp("authTokenCart","token");
		Response response = authPostRequest(url, token);
		assertSuccessStatus(response);
		System.out.println("Cart id " + cartId + " recalled successfully");

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
		System.out.println("Cart id " +getProp("cartId","token")+ " cleared successfully");


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


	public void checkout() throws Exception {
		JSONObject Object = loginUtils.checkout();
		String baseurl = getProp("preProdDbaseurl","url");		// Dashboard Base Url
		String propertyUrl = getProp("checkout","url");
		String url = baseurl+propertyUrl;
		String token = getProp("authTokenCart","token");
		Response response = postRequestWithJsonData(url, token, Object);
		assertSuccessStatus201(response);
		JSONObject data = new JSONObject(response.body().asString());
		String amount = String.valueOf(data.get("total"));
		String orderId = String.valueOf(data.get("id"));
		writeProp("orderId", orderId);
		writeProp("amount", String.valueOf(Math.round(Float.parseFloat((amount)))));
		System.out.println("Total payable amount for orderID: " + orderId + " is Rs. " + amount + "/-");

	}


	public void payment() throws Exception {
		JSONObject Object = loginUtils.payment();
		String baseurl = getProp("preProdDbaseurl","url");		// Dashboard Base Url
		String bulk = getProp("bulkCreate","url").replace("#", getProp("orderId","token"));
		String propertyUrl = getProp("payment","url").replace("#", getProp("orderId","token"));
		String bulkUrl = baseurl+bulk;
		String paymentUrl = baseurl+propertyUrl;
		String token = getProp("authTokenCart","token");
		Response bulkResponse = paymentRequest(bulkUrl, token, Object);
		assertSuccessStatus201(bulkResponse);
		System.out.println("Bulk created successfully for the order id :" + getProp("orderId","token"));
		Response paymentResponse = paymentRequest(paymentUrl, token, Object);
		assertSuccessStatus(paymentResponse);
		JSONObject data = new JSONObject(paymentResponse.body().asString());
		String status = String.valueOf(data.get("status"));
		System.out.println("Order status : " + status);

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


	public String searchByName(String name, String shopId) {
		String baseurl = getProp("preProdDbaseurl","url");		// Dashboard Base Url
		String searchUrl = getProp("searchName","url").replace("$", name).replace("#", shopId);
		String url = baseurl+searchUrl;
		String token = getProp("authTokenCart","token");
		Response response = getRequestWithAuthToken(url, token);
		assertSuccessStatus(response);
		String data = response.asString();
		System.out.println("Searched items with name "+ name +" for shop id "+shopId);
		return data;

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



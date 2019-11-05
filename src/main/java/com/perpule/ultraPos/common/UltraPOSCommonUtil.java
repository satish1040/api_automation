package com.perpule.ultraPos.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import org.json.JSONObject;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class UltraPOSCommonUtil {

	static InputStream inputStream;

	//  ###   Building URL from properties   ###

	public String urlBuilder(String baseurl, String urlProperty) {
		String baseUrl = getProp(baseurl, "url");
		String extendUrl = getProp(urlProperty, "url");
		String url = baseUrl + extendUrl;
		return url;
	}


	//  ###  Building URL from properties and with extra param  ###

	public String urlBuilderWithParam(String baseurl, String urlProperty, String partUrl) {
		String baseUrl = getProp(baseurl, "url");
		String extendUrl = getProp(urlProperty, "url");
		String url = baseUrl + extendUrl + partUrl;
		return url;
	}



	//		### 	Properties file reader 		###

	public static String getProp(String propName, String propFile) {
		String propStr = null;
		try {
			String path = null;
			if(propFile.contentEquals("url")){
				path = "resources/url.properties";
			}else if (propFile.contentEquals("common")){
				path = "resources/common.properties";
			}else if (propFile.contentEquals("token")){
				path = "resources/token.properties";
			} else {
				System.out.println("Wrong properties file name");
			}
			Properties prop = new Properties();
			File configFile = new File(path);
			inputStream = new FileInputStream(configFile);
			prop.load(inputStream);
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("properties file not found in the classpath");
			}
			propStr = prop.getProperty(propName);
		} catch (Exception e) {
			System.out.println("Exception : " + e);
		}
		return propStr;
	}
	
	
	//	### 	Properties file writer 		###
	
	public void writeProp(String key, String token) throws IOException {
		Properties prop = new Properties();
		InputStream in = new FileInputStream("resources/token.properties");
		prop.load(in);
		prop.setProperty(key, token);
		prop.store(new FileOutputStream("resources/token.properties"), null);
	}
	

	//  ####  Get request   ###

	public Response getRequest(String url) {
		Response response = given().when().get(url);
		return response;
	}	

	
	
	//  ####  Get request with authorization token  ###

	public Response getRequestWithAuthToken(String url, String token) {
		Response response = given().header("Authorization", token).when().get(url);
		return response;
	}	


	//  ####  Post request   ###

	public Response postRequest(String url, String fileName) {
		Response response = null;
		try {
			response = given().header("Content-Type", "application/json")
					.body(new File(System.getProperty("user.dir") + "//src//test//resources//inputJSON//" + fileName).getCanonicalFile())
					.post(url);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;

	}



	//    ####  Post request with parsing data using form   ###

	public Response postRequestWithFormData(String url, HashMap<String, String> hmap) {
		Response response = null;
		try {
			response = given().contentType(ContentType.URLENC.withCharset("UTF-8")).formParams(hmap).post(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;

	}


	//  ####  Post request with authorization token   ###

	public Response postRequestWithAuthToken(String url, String token, HashMap<String, String> hmap) {
		Response response = null;
		try {
			response = given().header("Authorization", token).contentType(ContentType.URLENC.withCharset("UTF-8")).formParams(hmap).post(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;

	}
	
	
	//  ####  Post request with only authorization token   ###

	public Response authPostRequest(String url, String token) {
		Response response = null;
		try {
			response = given().header("Authorization", token).contentType(ContentType.URLENC.withCharset("UTF-8")).post(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;

	}



	//  ####  Post request with parsing json data inside body   ###

	public Response postRequestWithJsonData(String url, String token, JSONObject jObj) {
		Response response = null;
		try {
			if(token == null) {
			response = given().contentType(ContentType.JSON).body(jObj.toString()).post(url);
			//.then() .statusCode(200) .extract() .response();
			}else {
				response = given().header("Authorization", token).contentType(ContentType.JSON).body(jObj.toString()).post(url);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;

	}
	
	public Response paymentRequest(String url, String token, JSONObject jObj) {
		Response response = null;
		try {
			String json = ("["+jObj.toString()+"]");
			response = given().header("Authorization", token).contentType(ContentType.JSON).when().body(json).post(url);	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;

	}
	
	
	//  ####  Patch request with parsing json data inside body   ###

	public Response patchRequestWithJsonData(String url, String token, JSONObject jObj) {
		Response response = null;
		try {
			if(token == null) {
			response = given().contentType(ContentType.JSON).body(jObj.toString()).patch(url);
			//.then() .statusCode(200) .extract() .response();
			}else {
				response = given().header("Authorization", token).contentType(ContentType.JSON).body(jObj.toString()).patch(url);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;

	}



	//    ###   Verify Status Code 200     ###

	public void assertSuccessStatus(Response response) {
		response.then().assertThat().statusCode(200);
	}
	
	
	
	//  ###   Verify Status Code 201     ###

	public void assertSuccessStatus201(Response response) {
		response.then().assertThat().statusCode(201);
	}
	
	
	//  ###   Verify Status Code 204     ###

	public void assertSuccessStatus204(Response response) {
		response.then().assertThat().statusCode(204);
	}
	
	
}

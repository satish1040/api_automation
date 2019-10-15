package com.perpule.ultraPos.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class UltraPOSCommonUtil {

	InputStream inputStream;
	
	//  ###  Building URL from properties file and dataProvider data  ###
	
	public String urlBuilderWithDataProvider(String baseurl, String urlProperty, String partUrl) {
		String baseUrl = "", extendUrl = "";
		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";
			File configFile = new File("resources/data/ultrapos.properties");
			inputStream = new FileInputStream(configFile);
			prop.load(inputStream);
			if (inputStream != null) {
				prop.load(inputStream);
				} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
				}
			baseUrl = prop.getProperty(baseurl);
			extendUrl = prop.getProperty(urlProperty);
			} catch (Exception e) {
				System.out.println("Exception : " + e);
			}
		String url = baseUrl + extendUrl + partUrl;
		return url;
	}
	
	
	
	//     ###   Building URL from properties file   ###
	
	public String urlBuilder(String baseurl, String urlProperty) {
		String baseUrl = "", extendUrl = "";
		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";
			File configFile = new File("resources/data/ultrapos.properties");
			inputStream = new FileInputStream(configFile);
			prop.load(inputStream);
			if (inputStream != null) {
				prop.load(inputStream);
				} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
				}
			baseUrl = prop.getProperty(baseurl);
			extendUrl = prop.getProperty(urlProperty);
			} catch (Exception e) {
				System.out.println("Exception: " + e);
			}
		String url = baseUrl + extendUrl;
		return url;
	}
	
	
	
	//  ####  Get request   ###
	
	public Response getRequest(String url) {
		Response response = given().when().get(url);
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

	
	
	//    ###   Verify Status Code 200     ###
	
	public void assertSuccessStatus(Response response) {
		response.then().assertThat().statusCode(200);
	}
}

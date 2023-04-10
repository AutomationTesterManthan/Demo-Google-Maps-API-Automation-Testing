package utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utilities {
	
	public static RequestSpecification Req;
	public static RequestSpecification AddPlaceReq;
	public static Response PlaceReq;
	public static Response PlaceResponse;
	public static Response UpdatePlaceResponse;
	
	public static String actual_status;
	public static String placeID;
	
	RequestSpecification reqSpecific;
	ResponseSpecification resSpecific;
	
	public RequestSpecification RequestSpec() throws Exception {
		
		reqSpecific = new RequestSpecBuilder().
				setBaseUri(getProp("baseuri")).
				addQueryParam("key", "qaclick123").
				build();
		
		return reqSpecific;
		
	}
	
	public ResponseSpecification ResponseSpec(int status_code) {
		
		resSpecific = new ResponseSpecBuilder().
				expectStatusCode(status_code).
				build();
		
		return resSpecific;
		
	}
	
	public String getProp(String key) throws Exception {
		
		Properties prop = new Properties();
		FileInputStream fileInput = new FileInputStream(new File(System.getProperty("user.dir") + "/src/test/java/config/config.properties"));
		prop.load(fileInput);
		
		return prop.getProperty(key);
		
	}
	
	public String getResponseValue(Response res, String value) {
		
		String response = res.asString();
		JsonPath jsonResponse = new JsonPath(response);
		String actualValue = jsonResponse.getString(value);
		return actualValue;
		
	}

}

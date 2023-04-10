package stepDef;

import static io.restassured.RestAssured.given;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import resource.APIResource;
import testdata.TestData;
import utils.Utilities;

public class Maps_Tests extends Utilities{
	
	TestData data = new TestData();
	Response AddPlaceResponse;
	Response UpdatedPlaceResponse;
	Response DeleteResponseBody;

	@Given("User calls the baseURI")
	public void user_calls_the_base_uri() throws Exception {
		
		Req = given().
				spec(RequestSpec());
		
	}
	
	@Given("Also provides the add place payload in the body")
	public void also_provides_the_add_place_payload_in_the_body() {
		
		AddPlaceReq = Req.
				log().all().
				body(data.AddPlace());
		
	}
	
	@When("User calls the {string} using {string} http request")
	public void user_calls_the_using_http_request(String api_resource, String http_method) {
		
		APIResource res = APIResource.valueOf(api_resource);
		
		if(http_method.equalsIgnoreCase("Post")) {
			
			PlaceReq = AddPlaceReq.
					when().
						post(res.getResource());
			
		}else if(http_method.equalsIgnoreCase("Get")) {
			
			PlaceReq = Req.
						queryParam("place_id", placeID).
					when().
						get(res.getResource());
			
		}else if(http_method.equalsIgnoreCase("Put")) {
			
			PlaceReq = Req.
						body(data.UpdatePlaceData(placeID)).
					when().
						put(res.getResource());
			
		}
		
	}
	
	@Then("Validate the {string} response with status {int}")
	public void validate_the_response_with_status(String responseBody, Integer status) {
		
		if(responseBody.equalsIgnoreCase("addPlaceResponse")){
			
			AddPlaceResponse = PlaceReq.
					then().
						spec(ResponseSpec(200)).
						extract().
						response();
			
		}else if(responseBody.equalsIgnoreCase("getPlaceResponse")) {
			
			PlaceResponse = PlaceReq.
					then().
						spec(ResponseSpec(200)).
						extract().
						response();
		
		}else if(responseBody.equalsIgnoreCase("updatePlaceResponse")) {
			
			UpdatePlaceResponse = PlaceReq.
					then().
					spec(ResponseSpec(200)).
					extract().
					response();
			
		}else if(responseBody.equalsIgnoreCase("UpdatedgetPlaceResponse")) {
			
			UpdatedPlaceResponse = PlaceReq.
					then().
						spec(ResponseSpec(200)).
						extract().
						response();
			
		}else if(responseBody.equalsIgnoreCase("DeleteResponseBody")) {
			
			DeleteResponseBody = PlaceReq.
					then().
						spec(ResponseSpec(200)).
						extract().
						response();
			
		}
		
	}
	
	@Then("Also Validate the response {string} as {string}")
	public void also_validate_the_response_as(String responses, String expected_status) {
		
		if(responses.equalsIgnoreCase("addPlaceResponse")) {
		
			actual_status = getResponseValue(AddPlaceResponse, "status");
			Assert.assertEquals(expected_status, actual_status);
		
		}else if(responses.equalsIgnoreCase("DeletePlaceResponse")) {
			
			actual_status = getResponseValue(DeleteResponseBody, "status");
			Assert.assertEquals(expected_status, actual_status);
			
		}
		
	}
	
	@Then("Extract place ID from the response")
	public void extract_place_id_from_the_response() {
		
		placeID = getResponseValue(AddPlaceResponse, "place_id");
		System.out.println("Place ID: " + placeID);
		
	}

	@Then("Also Validate the response for {string} {string} {string}")
	public void also_validate_the_response_for(String expectedName, String expectedAddress, String expectedWebsite) {
		
		String actualName = getResponseValue(PlaceResponse, "name");
		String actualAddress = getResponseValue(PlaceResponse, "address");
		String actualWebsite = getResponseValue(PlaceResponse, "website");
		
		Assert.assertEquals(expectedName, actualName);
		Assert.assertEquals(expectedAddress, actualAddress);
		Assert.assertEquals(expectedWebsite, actualWebsite);
		
	}
	
	@Then("Also validate the response for {string}")
	public void also_validate_the_response_for(String expectedMessage) {
		
		String actualMessage = getResponseValue(UpdatePlaceResponse, "msg");
		
		Assert.assertEquals(expectedMessage, actualMessage);
		
	}
	
	@Then("Also Validate the response for {string} is updated")
	public void also_validate_the_response_for_is_updated(String previousAddress) {
		
		String newAddress = getResponseValue(UpdatedPlaceResponse, "address");
		
		Assert.assertNotSame(previousAddress,newAddress);
		
	}

}

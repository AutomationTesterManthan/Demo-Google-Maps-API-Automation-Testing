package testdata;

import java.util.ArrayList;

import updateplacepojo.*;
import addplacepojo.*;

public class TestData {
	
	public AddPlace AddPlace() {
		
		AddPlace addplace = new AddPlace();
		Location location = new Location();
		location.setLat(-38.344363);
		location.setLng(34.526234);
		addplace.setLocation(location);
		addplace.setAccuracy(50);
		addplace.setName("Stark Tower");
		addplace.setPhone_number("(+91) 5856389739");
		addplace.setAddress("Times Square, New York");
		ArrayList<String> types = new ArrayList<String>();
		types.add("I.T");
		types.add("Consultation");
		addplace.setTypes(types);
		addplace.setWebsite("www.google.com");
		addplace.setLanguage("English-En");
		
		return addplace;
		
	}
	
	public UpdatePlace UpdatePlaceData(String Place_ID) {
		
		UpdatePlace updateplace = new UpdatePlace();
		updateplace.setPlace_id(Place_ID);
		updateplace.setAddress("Stark Tower, NY");
		updateplace.setKey("qaclick123");
		
		return updateplace;
		
	}

}


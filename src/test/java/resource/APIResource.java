package resource;

public enum APIResource {
	
	AddPlace("/maps/api/place/add/json"),
	GetPlace("/maps/api/place/get/json"),
	UpdatePlace("/maps/api/place/update/json"),
	DeletePlace("/maps/api/place/delete/json");
	
	public String resource;
	
	APIResource(String resource){
		
		this.resource = resource;
		
	}
	
	public String getResource() {
		
		return resource;
		
	}

}

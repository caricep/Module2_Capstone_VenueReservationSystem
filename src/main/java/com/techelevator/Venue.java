package com.techelevator;

public class Venue {

	private int venueId;
	private String venueName;
	private String venueDescription;
	private String categoryName;
	private String cityName;
	private String state;
	
	


	public int getVenueId() {
		return venueId;
	}
	
	public void setVenueId(int venueId) {
		this.venueId = venueId;
	}
	
	public String getVenueName() {
		return venueName;
	}
	
	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}
	
	public String getVenueDescription() {
		return venueDescription;
	}
	
	public void setVenueDescription(String venueDescription) {
		this.venueDescription = venueDescription;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Venue [venueId=" + venueId + ", venueName=" + venueName + ", venueDescription=" + venueDescription
				+ ", categoryName=" + categoryName + ", cityName=" + cityName + ", state=" + state + "]";
	}

	

	
	
	
	
}

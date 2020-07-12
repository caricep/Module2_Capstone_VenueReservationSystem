package com.techelevator.dao;

import java.util.List;

import com.techelevator.Venue;

public interface VenueDAO {

	public List<Venue> alphabeticalVenueList();
	public Venue getVenueInformationById(int venueId);
	
}

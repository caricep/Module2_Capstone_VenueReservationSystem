package com.techelevator.dao;

import java.util.List;

import com.techelevator.Space;

public interface SpaceDAO {
	
	public List<Space> getListOfAllSpacesByVenueId(int id);
	public String returnAMonth(int date);

}

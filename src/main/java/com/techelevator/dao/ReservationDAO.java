package com.techelevator.dao;

import java.time.LocalDate;
import java.util.List;

import com.techelevator.Reservation;
import com.techelevator.Space;

public interface ReservationDAO {

	public List<Space> listOfAvailableSpaces(int dayCount, int venueId , int numberOfAttendees);
	public Reservation makeReservation(int spaceId , int numberOfAttendees, LocalDate startDate , LocalDate endDate, String reservedFor );	
}

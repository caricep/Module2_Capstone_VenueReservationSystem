package com.techelevator;

import java.time.LocalDate;

public class Reservation {

	private int reservationId;
	private LocalDate startDate;
	private LocalDate endDate;
	private String reservedFor;
	private int numberOfAttendees;
	private String spaceName;
	
	
	
	public String getSpaceName() {
		return spaceName;
	}

	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
	}

	public int getReservationId() {
		return reservationId;
	}
	
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	
	public LocalDate getStartDate() {
		return startDate;
	}
	
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	
	public LocalDate getEndDate() {
		return endDate;
	}
	
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	public String getReservedFor() {
		return reservedFor;
	}
	
	public void setReservedFor(String reservedFor) {
		this.reservedFor = reservedFor;
	}

	public int getNumberOfAttendees() {
		return numberOfAttendees;
	}

	public void setNumberOfAttendees(int numberOfAttendees) {
		this.numberOfAttendees = numberOfAttendees;
	}

}

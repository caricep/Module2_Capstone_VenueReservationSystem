package com.techelevator.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.techelevator.Reservation;
import com.techelevator.Space;
import com.techelevator.Venue;

public class Menu {

	private Scanner userInput = new Scanner(System.in);

	public String getMainMenuChoice() {
		System.out.println("What would you like to do? \n" + 
							"1) List Venues \n" + 
							"Q) Quit");
		String userMainMenuChoice = userInput.nextLine();
		return userMainMenuChoice;

	}

	public void displayAlphabeticalVenueList(List<Venue> venue) {
		System.out.println();
		System.out.println("Which venue would you like to view? Select an ID number or (R) to return to previous screen.");

		for (Venue venues : venue) {
			System.out.print("   ID " + venues.getVenueId() + ") " + venues.getVenueName() + "\n");
		}
		System.out.println("   R) Return to Previous Screen");
	}

	public String getVenueChoice() {
		String userVenueChoice = userInput.nextLine();
		return userVenueChoice;
	}

	public void displayVenueInformation(Venue userVenueChoice) {
		System.out.println();
		System.out.println(userVenueChoice.getVenueName());
		System.out.println("Location: " + userVenueChoice.getCityName() + ", " + userVenueChoice.getState());
		System.out.println("Categories: " + getCategories(userVenueChoice.getVenueId()));
		System.out.println();
		System.out.println(userVenueChoice.getVenueDescription());
	}
	


	public String getUserChoiceInVenueDetails() {
		System.out.println();
		System.out.println("What would you like to do next? \n" + 
							"1) View Spaces \n" + 
							"2) Search for Reservation \n" + 
							"R) Return to Previous Screen");
		String userChoiceInVenueDetails = userInput.nextLine();
		return userChoiceInVenueDetails;
	}

	public void displaySpacesByVenueIdList(List<Space> space) {
		System.out.println();
		System.out.println((String.format("%-7s%-35s%-15s%-15s%-15s%s", "ID", "Name", "Open", "Close", "Daily Rate", "Max.Occupancy")));
		System.out.println("======================================================================================================");

		for (Space spaces : space) {
			System.out.printf("%-7s%-35s%-15s%-15s%-15s%s", spaces.getSpaceId(), spaces.getSpaceName(),
					spaces.getOpenFrom(), spaces.getOpenTo(), "$" + spaces.getDailyRate(),
					spaces.getMaxOccupancy() + "\n");
		}

	}

	public String getReservationChoice() {
		System.out.println();
		System.out.println("What would you like to do next? \n" + "1) Reserve a Space \n" + "R) Return to Previous Screen");
		String userReservationChoice = userInput.nextLine();
		return userReservationChoice;
	}

	public LocalDate getReservationStartDate() {
		System.out.println("When do you need the space? (Enter Date as: yyyy-MM-dd)");
		String dateToParse = userInput.nextLine();
		LocalDate reservationStartDate = LocalDate.parse(dateToParse, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		return reservationStartDate;
	}

	public int getDayCount() {
		System.out.println("How many days will you need the space?");
		int dayCount = userInput.nextInt();
		userInput.nextLine();
		return dayCount;
	}

	public int getNumberOfAttendees() {
		System.out.println("How many people will be in attendance?");
		int numberOfAttendees = userInput.nextInt();
		userInput.nextLine();
		return numberOfAttendees;
	}

	public void displayAvailableSpaces(List<Space> space) {
		System.out.println();
		System.out.println((String.format("%-15s%-35s%-15s%-15s%-15s%s", "Space # ", "Name", "Daily Rate", "Max Occup", "Accessible?", "Total Cost")));
		System.out.println("===================================================================================================================");
		
		for (Space spaces : space) {
			System.out.printf("%-15s%-35s%-15s%-15s%-15s%s", spaces.getSpaceId(), spaces.getSpaceName(),
					"$" + spaces.getDailyRate(), spaces.getMaxOccupancy(), spaces.isAccessible(),
					"$" + spaces.getTotalCost() + "\n");
		}
	}

	public int getSpaceIdChoiceForReservation() {
		System.out.println();
		System.out.println("Which space would you like to reserve (enter 0 to cancel)?");
		int userSpaceChoice = userInput.nextInt();
		userInput.nextLine();
		return userSpaceChoice;
	}

	public String getReservedFor() {
		System.out.println("Who is this reservation for?");
		String reservedFor = userInput.nextLine();
		return reservedFor;
	}

	public void displayConfirmationInformation(Reservation reservation, Venue venue) {
		System.out.println();
		System.out.println("Confirmation #: " + reservation.getReservationId());
		System.out.println("Venue: " + venue.getVenueName());
		System.out.println("Space: " + reservation.getSpaceName());
		System.out.println("Reserved for: " + reservation.getReservedFor());
		System.out.println("Attendees: " + reservation.getNumberOfAttendees());
		System.out.println("Arrival date: " + reservation.getStartDate());
		System.out.println("Depart date: " + reservation.getEndDate());
		

	}

	public void getTotalCost(List<Space> spaceList, int spaceId) {
		for (Space spaces : spaceList) {
			if (spaces.getSpaceId() == spaceId) {
			System.out.println( "Total cost: "+ "$"+ spaces.getTotalCost());
			}
		}
		System.out.println();
	}

	public void displayUserMessage(String message) {
		System.out.println(message);
		System.out.flush();
	}
	
	private String getCategories(int venueId) {
		if (venueId == 1) {
			return "Family Friendly , Modern";
		}

		if (venueId == 3) {
			return "Family Friendly , Outdoors , Rustic";
		}
		if (venueId == 4 || venueId == 14) {
			return "Modern";
		}
		if (venueId == 5 || venueId == 15) {
			return "Family Friendly , Outdoors";
		}
		if (venueId == 6 || venueId == 12 || venueId == 13) {
			return "Family Friendly , Historic";
		}
		if (venueId == 7) {
			return "Luxury , Modern";
		}
		if (venueId == 8) {
			return "Luxury";
		}
		if (venueId == 9) {
			return "Historic";
		}
		if (venueId == 10) {
			return "Family Friendly";
		}
		return "No categories at this time.";

	}
	
	
}

package com.techelevator;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.dao.ReservationDAO;
import com.techelevator.dao.SpaceDAO;
import com.techelevator.dao.VenueDAO;
import com.techelevator.jdbc.JDBCReservationDAO;
import com.techelevator.jdbc.JDBCSpaceDAO;
import com.techelevator.jdbc.JDBCVenueDAO;
import com.techelevator.view.Menu;

public class ExcelsiorCLI {

	private Menu menu;
	private VenueDAO venueDAO;
	private SpaceDAO spaceDAO;
	private ReservationDAO reservationDAO;

	public static void main(String[] args) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/excelsior-venues");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");

		ExcelsiorCLI application = new ExcelsiorCLI(dataSource);
		application.run();
	}

	public ExcelsiorCLI(DataSource datasource) {
		this.menu = new Menu();
		venueDAO = new JDBCVenueDAO(datasource);
		spaceDAO = new JDBCSpaceDAO(datasource);
		reservationDAO = new JDBCReservationDAO(datasource);
	}

	public void run() {

		boolean continueRunningApp = true;
		while (continueRunningApp) {

			String userMainMenuChoice = menu.getMainMenuChoice();

			if (userMainMenuChoice.equals("1")) {
				boolean continueMenus = true;
				while(continueMenus) {

					List<Venue> venueList = new LinkedList<Venue>();
					venueList = venueDAO.alphabeticalVenueList();
					menu.displayAlphabeticalVenueList(venueList);
					
					String userVenueChoiceString = menu.getVenueChoice();
					if (userVenueChoiceString.equalsIgnoreCase("R")) {
						break;
					}
					
					{
						int userVenueChoice = Integer.parseInt(userVenueChoiceString);
						Venue venue = venueDAO.getVenueInformationById(userVenueChoice);
						menu.displayVenueInformation(venue);

						String userChoiceInVenueDetails = menu.getUserChoiceInVenueDetails();

						if (userChoiceInVenueDetails.equals("1")) {
							
							List<Space> spacesByVenueId = new LinkedList<Space>();
							spacesByVenueId = spaceDAO.getListOfAllSpacesByVenueId(userVenueChoice);
							menu.displaySpacesByVenueIdList(spacesByVenueId);

							String userReservationChoice = menu.getReservationChoice();
							
							if (userReservationChoice.equals("1")) {

								LocalDate startDate = menu.getReservationStartDate();
								int dayCount = menu.getDayCount();
								int numberOfAttendees = menu.getNumberOfAttendees();

								List<Space> availableSpaces = new LinkedList<Space>();
								availableSpaces = reservationDAO.listOfAvailableSpaces(dayCount, numberOfAttendees, userVenueChoice);
								menu.displayAvailableSpaces(availableSpaces);

								int spaceIdForReservation = menu.getSpaceIdChoiceForReservation();
								String reservedFor = menu.getReservedFor();
								LocalDate endDate = startDate.plusDays(dayCount);

								Reservation completedReservation = reservationDAO.makeReservation(spaceIdForReservation, numberOfAttendees, startDate, endDate, reservedFor);
								menu.displayUserMessage("Thanks for submitting your reservation! The details for your event are listed below:");
								menu.displayConfirmationInformation(completedReservation, venue);
								menu.getTotalCost(availableSpaces, spaceIdForReservation);
								continueMenus = false;
							}
						}
					}
				}

			} else if (userMainMenuChoice.equalsIgnoreCase("Q")) {
				menu.displayUserMessage("Thank you, goodbye!");
				System.exit(0);
			} else {
				menu.displayUserMessage("Invalid Entry. Please try again.");
			}
		}
	}
}

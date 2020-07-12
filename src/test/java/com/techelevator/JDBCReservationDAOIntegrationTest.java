package com.techelevator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.dao.ReservationDAO;
import com.techelevator.jdbc.JDBCReservationDAO;

public class JDBCReservationDAOIntegrationTest extends DAOIntegrationTest {

	private ReservationDAO dao;
	private JdbcTemplate jdbcTemplate;

	@Before
	public void setupTest() {
		dao = new JDBCReservationDAO(getDataSource());
		jdbcTemplate = new JdbcTemplate(getDataSource());
		
	}
	
//	@Test
//	public void is_reservation_available_is_true() {
//		Reservation reservation = createTestReservationWhenDataDoesNotExists();
//	
//		boolean result = dao.reservationIsAvailable(1, reservation);
//		
//		Assert.assertTrue(result);
//	}
	
	@Test
	public void list_of_available_spaces() {
		int daycount = 5;
		int id = 6;
		int number = 7;
		
		List<Space> result = dao.listOfAvailableSpaces(daycount, id , number);
		int resultSize = result.size();
		
		Assert.assertEquals(1, resultSize);
		
		
	}
	@Test
	public void make_reservation() {
		Reservation reservation = createTestReservationWhenDataDoesNotExists();
		int spaceId = 6;
		int numberOfAttendees = 10;
		LocalDate startDate = reservation.getStartDate();
		LocalDate endDate = reservation.getEndDate();
		String reservedFor = "test family";
		String selectSql = "SELECT reservation_id, space_id, number_of_attendees, start_date, end_date, reserved_for FROM reservation";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(selectSql);
		List<SqlRowSet> test = new ArrayList<SqlRowSet>();
		while(rows.next()) {
			test.add(rows);
		}
		int originalSize = test.size();
		
		dao.makeReservation(spaceId, numberOfAttendees, startDate, endDate, reservedFor);
		String addedOne = "SELECT reservation_id, space_id, number_of_attendees, start_date, end_date, reserved_for FROM reservation";
		SqlRowSet newRows = jdbcTemplate.queryForRowSet(addedOne);
		List<SqlRowSet> newTestList = new ArrayList<SqlRowSet>();
		while(newRows.next()) {
			newTestList.add(newRows);
		}
		int newListSize = newTestList.size();
		
		Assert.assertEquals(originalSize+1, newListSize);
	}
 	
	private Reservation createTestReservationWhenDataExists() {
		Reservation reservation = new Reservation();
		reservation.setStartDate(LocalDate.parse("2020-06-15", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		reservation.setEndDate(LocalDate.parse("2020-06-19", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		
		return reservation;
	}
	
	private Reservation createTestReservationWhenDataDoesNotExists() {
		Reservation reservation = new Reservation();
		reservation.setStartDate(LocalDate.parse("1500-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		reservation.setEndDate(LocalDate.parse("1501-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		
		return reservation;
	}
	
	
	
	
}
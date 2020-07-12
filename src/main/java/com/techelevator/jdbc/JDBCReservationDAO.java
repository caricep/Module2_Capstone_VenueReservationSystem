package com.techelevator.jdbc;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.Reservation;
import com.techelevator.Space;
import com.techelevator.dao.ReservationDAO;

public class JDBCReservationDAO implements ReservationDAO {

	private JdbcTemplate jdbcTemplate;
	

	public JDBCReservationDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

//	@Override
//	public boolean reservationIsAvailable(int spaceId, Reservation reservation) {
//		String selectSql = "SELECT reservation_id, space_id, number_of_attendees, start_date, end_date, reserved_for "
//				+ "FROM reservation WHERE space_id = ? AND start_date >= ? AND end_date <= ?";
//		SqlRowSet rows = jdbcTemplate.queryForRowSet(selectSql, spaceId, reservation.getStartDate(), reservation.getEndDate());
//
//		if (rows.next()) {
//			return false;
//		}
//		return true;
//	}

	@Override
	public List<Space> listOfAvailableSpaces(int dayCount, int numberOfAttendees ,int venueId  ) {

		List<Space> availableSpaces = new LinkedList<Space>();
		String selectSql = "SELECT space.id,  space.name, space.is_accessible,  cast(space.daily_rate as decimal), space.max_occupancy, cast(space.daily_rate * ? as decimal) AS total_cost "
				+ "FROM space LEFT JOIN reservation ON reservation.space_id = space.id WHERE space.max_occupancy >= ? AND space.venue_id = ? AND reservation.start_date IS NULL AND reservation.end_date IS NULL LIMIT 5";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(selectSql, dayCount, numberOfAttendees, venueId);
		
		while(rows.next()) {
			Space space = new Space();
			space.setSpaceId(rows.getInt("id"));
			space.setSpaceName(rows.getString("name"));
			space.setAccessible(rows.getBoolean("is_accessible"));
			space.setDailyRate(rows.getDouble("daily_rate"));
			space.setMaxOccupancy(rows.getInt("max_occupancy"));
			space.setTotalCost(rows.getDouble("total_cost"));
		
			availableSpaces.add(space);
		}

		return availableSpaces;
		
	}

	@Override
	public Reservation makeReservation(int spaceId , int numberOfAttendees, LocalDate startDate , LocalDate endDate, String reservedFor ) {
		String insertReservationSql = "INSERT INTO reservation (reservation_id, space_id, number_of_attendees, start_date, end_date, reserved_for) "
				+ "VALUES (DEFAULT, ?, ?, ?, ?, ?) RETURNING reservation_id";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(insertReservationSql, spaceId, numberOfAttendees, startDate, endDate, reservedFor);	
		rows.next();
		int reservationId = rows.getInt("reservation_id");
		
		String selectSql = "SELECT reservation_id, space.name, number_of_attendees, start_date, end_date, reserved_for "
				+ "FROM reservation JOIN space ON space.id = reservation.space_id WHERE reservation_id = ?";
		SqlRowSet selectedRows = jdbcTemplate.queryForRowSet(selectSql , reservationId);
		Reservation reservation = new Reservation();
		while(selectedRows.next()) {
			
			reservation.setReservationId(reservationId);
			reservation.setSpaceName(selectedRows.getString("name"));
			reservation.setNumberOfAttendees(selectedRows.getInt("number_of_attendees"));
			reservation.setStartDate(selectedRows.getDate("start_date").toLocalDate());
			reservation.setEndDate(selectedRows.getDate("end_date").toLocalDate());
			reservation.setReservedFor(selectedRows.getString("reserved_for"));	
		}
		return reservation;
		 
	}

}

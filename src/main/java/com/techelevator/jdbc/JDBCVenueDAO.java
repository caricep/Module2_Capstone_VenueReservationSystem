package com.techelevator.jdbc;

import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.Venue;
import com.techelevator.dao.VenueDAO;

public class JDBCVenueDAO implements VenueDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCVenueDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	@Override
	public List<Venue> alphabeticalVenueList() {

		List<Venue> alphabeticalVenues = new LinkedList<Venue>();

		String selectSql = "SELECT venue.id , venue.name , venue.description , city.name as city_name , city.state_abbreviation "
				+ "FROM venue JOIN city ON venue.city_id = city.id ORDER BY venue.name";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(selectSql);

		while (rows.next()) {
			Venue venue = makeVenueFromRow(rows);
			alphabeticalVenues.add(venue);
		}

		return alphabeticalVenues;
	}
	
	@Override
	public Venue getVenueInformationById(int venueId) {
		String selectSql = "SELECT venue.name, venue.description, city.name, city.state_abbreviation "
				+ "FROM venue JOIN city ON venue.city_id = city.id WHERE venue.id = ?";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(selectSql, venueId);
		
		Venue venue = new Venue();
		while(rows.next()) {
			venue.setVenueId(venueId);
			venue.setVenueName(rows.getString("name"));
			venue.setVenueDescription(rows.getString("description"));
			venue.setCityName(rows.getString("name"));
			venue.setState(rows.getString("state_abbreviation"));
		}
		
		return venue;
	}
	

	private Venue makeVenueFromRow(SqlRowSet rows) {
		Venue venue = new Venue();

		venue.setVenueId(rows.getInt("id"));
		venue.setVenueName(rows.getString("name"));
		venue.setCityName(rows.getString("city_name"));
		venue.setState(rows.getString("state_abbreviation"));

		return venue;
	}



}

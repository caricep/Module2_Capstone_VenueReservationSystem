package com.techelevator;

import java.util.List;

import org.junit.*;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.dao.VenueDAO;
import com.techelevator.jdbc.JDBCVenueDAO;


public class JDBCVenueDAOIntegrationTest extends DAOIntegrationTest {
	
	private VenueDAO dao;
	private JdbcTemplate jdbcTemplate;
	
	@Before
	public void setupTest() {
		dao = new JDBCVenueDAO(getDataSource());
		jdbcTemplate = new JdbcTemplate(getDataSource());
		
	}
	
	//To use test, add 1 to the id value on line 31
	@Test
	public void get_alphabetical_venue_list() {
		Venue venue = new Venue();
		venue.setVenueName("Aaaa");
		venue.setVenueDescription("This is a test venue.");
		
		String addVenueSql = "INSERT INTO venue (id, name, city_id, description) VALUES (23, ?, 1, ?)";
		jdbcTemplate.update(addVenueSql, venue.getVenueName(), venue.getVenueDescription());
		
		List<Venue> testList = dao.alphabeticalVenueList();
		
		
		Assert.assertEquals("Aaaa", testList.get(0).getVenueName());
		
	}
	
	
}

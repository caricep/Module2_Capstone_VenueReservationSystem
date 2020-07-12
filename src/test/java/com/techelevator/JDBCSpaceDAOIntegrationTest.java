package com.techelevator;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.dao.SpaceDAO;
import com.techelevator.jdbc.JDBCSpaceDAO;

public class JDBCSpaceDAOIntegrationTest extends DAOIntegrationTest {
	private SpaceDAO dao;
	private JdbcTemplate jdbcTemplate;

	@Before
	public void setupTest() {
		dao = new JDBCSpaceDAO(getDataSource());
		jdbcTemplate = new JdbcTemplate(getDataSource());

	}

	@Test
	public void get_month_test() {
		int date = 5;
		String result = dao.returnAMonth(date);

		Assert.assertEquals("May", result);

	}

	@Test
	public void list_of_all_spaces_by_venue_id() {
		int id = 1;
		String selectSql = "SELECT id, name, is_accessible, open_from, open_to, cast (daily_rate as decimal), max_occupancy "
				+ "FROM space WHERE venue_id = 1";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(selectSql);
		List<SqlRowSet> testList = new LinkedList<SqlRowSet>();
		while (rows.next()) {
			testList.add(rows);
		}
		int actual = testList.size();

		List<Space> resultList = dao.getListOfAllSpacesByVenueId(id);

		int result = resultList.size();

		Assert.assertEquals(actual, result);

	}

}

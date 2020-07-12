package com.techelevator.jdbc;

import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.Space;
import com.techelevator.dao.SpaceDAO;

public class JDBCSpaceDAO implements SpaceDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCSpaceDAO(DataSource datasource) {

		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	@Override
	public List<Space> getListOfAllSpacesByVenueId(int id) {

		List<Space> spacesWithVenueId = new LinkedList<Space>();
		String selectSql = "SELECT id, name, is_accessible, open_from, open_to, cast (daily_rate as decimal), max_occupancy "
				+ "FROM space WHERE venue_id = ?";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(selectSql, id);
		while (rows.next()) {
			Space space = makeSpaceFromRow(rows);
			spacesWithVenueId.add(space);
		}
		return spacesWithVenueId;

	}

	private Space makeSpaceFromRow(SqlRowSet rows) {
		Space space = new Space();
		space.setSpaceId(rows.getInt("id"));
		space.setSpaceName(rows.getString("name"));
		int fromDate = rows.getInt("open_from");
		space.setOpenFrom(returnAMonth(fromDate));
		int toDate = rows.getInt("open_to");
		space.setOpenTo(returnAMonth(toDate));
		space.setAccessible(rows.getBoolean("is_accessible"));
		space.setDailyRate(rows.getDouble("daily_rate"));
		space.setMaxOccupancy(rows.getInt("max_occupancy"));

		return space;
	}

	public String returnAMonth(int date) {

		if (date == 1) {
			return "Jan";
		}
		if (date == 2) {
			return "Feb";
		}
		if (date == 3) {
			return "Mar";
		}
		if (date == 4) {
			return "Apr";
		}
		if (date == 5) {
			return "May";
		}
		if (date == 6) {
			return "Jun";
		}
		if (date == 7) {
			return "Jul";
		}
		if (date == 8) {
			return "Aug";
		}
		if (date == 9) {
			return "Sep";
		}
		if (date == 10) {
			return "Oct";
		}
		if (date == 11) {
			return "Nov";
		}
		if (date == 12) {
			return "Dec";
		}
		return "";
	}

}

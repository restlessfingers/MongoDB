package com.example.demo;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class BookMapper implements RowMapper<Book>	{

	@Override
	public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
	// TODO Auto-generated method stub
	return new
	
	Book(rs.getString("title"),rs.getString("ISBN"),rs.getInt("pages"));
		}
}
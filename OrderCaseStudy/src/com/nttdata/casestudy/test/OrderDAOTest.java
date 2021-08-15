package com.nttdata.casestudy.test;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.nttdata.casestudy.util.ConnectionUtil;

public class OrderDAOTest {
	final static ConnectionUtil con=new ConnectionUtil();
    final Connection con1=con.estConnection();
	@Before
	public void setUp() throws Exception {
		
		try
		{
			truncateOrdertbl();
			
			//remove this later.
			throw new SQLException();
		}
		catch(SQLException ex)
		{
			System.out.println(ex);
		}
	}

	public void truncateOrdertbl()
	{
		String sql1="drop table line_items";
		String sql2="truncate table orders";
		try
		{
			Statement statement=con1.createStatement();
			
			con1.setAutoCommit(false);
			
			statement.addBatch(sql1);
			statement.addBatch(sql2);
			
			statement.executeBatch();
			
			con1.commit();
		}
		catch(SQLException ex)
		{
			System.out.println(ex);
		}
	}
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}

package com.nttdata.casestudy.test;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.nttdata.casestudy.dao.UserDAO;
import com.nttdata.casestudy.model.User;
import com.nttdata.casestudy.util.ConnectionUtil;

public class UserDAOTest {

	final static ConnectionUtil con=new ConnectionUtil();
	Connection con1 = null;
	
	@Before
	public void setUp() throws Exception {
		con1=con.estConnection();
		
		try
		{
		    truncateUserTbl();
		    String sql1="insert into user values(\"tombrady12\" ,"
		    		+ " \"tombrady12\" ,"
		    		+ " \"Tom Brady\" , "
		    		+ "  \"tb12@gmail.com\" , "
		    		+ " '1977-08-03' , "
		    		+ " \"5051234563\", "
		    		+ " \"Tampa Bay\" , "
		    		+ " 33603 , "
		    		+ " \"admin\" )";
		    
		    String sql2="insert into user values(\"arod12\" ,"
		    		+ " \"arod12\" ,"
		    		+ " \"Aaron Rodgers\" , "
		    		+ "  \"arod12@gmail.com\" , "
		    		+ " '1983-12-02' , "
		    		+ " \"6051234729\", "
		    		+ " \"Green Bay\" , "
		    		+ " 54229 , "
		    		+ " \"customer\" )";
		    
		    String sql3="insert into user values(\"drewbrees9\" ,"
		    		+ " \"drewbrees9\" ,"
		    		+ " \"Drew Brees\" , "
		    		+ "  \"drewbrees9@gmail.com\" , "
		    		+ " '1979-01-15' , "
		    		+ " \"3051237654\", "
		    		+ " \"New Orleans\" , "
		    		+ " 70032 , "
		    		+ " \"customer\" )";
		    
		    String sql4="insert into user values(\"pmahomes15\" ,"
		    		+ " \"pmahomes15\" ,"
		    		+ " \"Patrick Mahomes\" , "
		    		+ "  \"pmahomes15@gmail.com\" , "
		    		+ " '1995-09-17' , "
		    		+ " \"8151234739\", "
		    		+ " \"Kansas\" , "
		    		+ " 64016 , "
		    		+ " \"customer\" )";
		    
		    String sql5="insert into user values(\"rwilson3\" ,"
		    		+ " \"rwilson3\" ,"
		    		+ " \"Russell Wilson\" , "
		    		+ "  \"rwilson3@gmail.com\" , "
		    		+ " '1988-11-29' , "
		    		+ " \"2841238367\" , "
		    		+ " \"Seattle\" , "
		    		+ " 98101 , "
		    		+ " \"customer\" )";
		    
		    PreparedStatement stmt1 = con1.prepareStatement(sql1);
		    PreparedStatement stmt2 = con1.prepareStatement(sql2);
		    PreparedStatement stmt3 = con1.prepareStatement(sql3);
		    PreparedStatement stmt4 = con1.prepareStatement(sql4);
		    PreparedStatement stmt5 = con1.prepareStatement(sql5);

			// Set auto-commit to false
			con1.setAutoCommit(false);

			stmt1.executeUpdate(sql1);
			stmt2.executeUpdate(sql2);
			stmt3.executeUpdate(sql3);
			stmt4.executeUpdate(sql4);
			stmt5.executeUpdate(sql5);

			// Create an int[] to hold returned values
			//int[] count = stmt.executeBatch();

			//Explicitly commit statements to apply changes
			con1.commit();
		    
		}
		catch(SQLException ex) {
			System.out.println(ex);
		}
	}
	
	private void truncateUserTbl() {
		String sql1 = "drop table orders";
		String sql2 = "truncate table user";

		try {
			
			// Set auto-commit to false
			con1.setAutoCommit(false);
		
			Statement stmt = con1.createStatement();
			
			stmt.addBatch(sql1);
			stmt.addBatch(sql2);
			
			stmt.executeBatch();
	
			con1.commit();
		}
		catch (SQLException e) {
			System.out.println(e);
			try {
				con1.rollback();
			}catch(SQLException ex) {
				System.out.println(ex);
			}
		}	
	}

	@After
	public void tearDown() throws Exception {
		con1.close();
	}
	
	@Test
	public void checkAdminFalseTest()
	{
		String uName="arod12";
		String res=UserDAO.checkAdmin(uName);
		assertTrue(res.equals("CUSTOMER"));
	}

	@Test
	public void checkAdminTrueTest()
	{
		String uName="tombrady12";
		String res=UserDAO.checkAdmin(uName);
		assertTrue(res.equals("ADMIN"));
	}
	
	@Test
	public void authenticateSuccessfulTest()
	{
		String orderID="tombrady12";
		String password="tombrady12";
		User user=UserDAO.authenticate(orderID,password);
		boolean flag1=password.equals(user.getPassword());
		boolean flag2= orderID.equals(user.getUserId());
		boolean finalFlag=(flag1&&flag2);
		assertTrue(finalFlag);
		
	}
	
	@Test
	public void authenticateFailedTest()
	{
		String orderID="pmahomes15";
		String password="pmahomes5";
		User user=UserDAO.authenticate(orderID, password);
		
//		boolean flag1=password.equals(user.getPassword());
//		boolean flag2= orderID.equals(user.getUserId());
//      boolean finalFlag=(!user.isAuthSuccessful());
		assertTrue(!user.isAuthSuccessful());
		
	}

}

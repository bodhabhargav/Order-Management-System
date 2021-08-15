package com.nttdata.casestudy.test;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.nttdata.casestudy.dao.LineItemsDAO;
import com.nttdata.casestudy.model.LineItem;
import com.nttdata.casestudy.util.ConnectionUtil;

public class LineItemsDAOTest {

	final static ConnectionUtil con=new ConnectionUtil();
	final static Connection con1 = con.estConnection();
	
	@Before
	public void setUp() throws Exception {
		
		//Connection con1=con.estConnection();
		
		try
		{
			truncateLineItemsTbl();
			String sql1="insert into line_items value(1, 1 , 1 , 1)";
			String sql2="insert into line_items value(2, 2 , 2 , 2)";
			String sql3="insert into line_items value(3, 3 , 3 , 3)";
			String sql4="insert into line_items value(4, 4 , 4 , 4)";
			String sql5="insert into line_items value(5, 5 , 5 , 5)";
			
			Statement statement=con1.createStatement();
			
			con1.setAutoCommit(false);
			
			statement.addBatch(sql1);
			statement.addBatch(sql2);
			statement.addBatch(sql3);
			statement.addBatch(sql4);
			statement.addBatch(sql5);
			
		    statement.executeBatch();

			con1.commit();
			
			//inserting into users table
			 String query1="insert into user values(\"tombrady12\" ,"
			    		+ " \"tombrady12\" ,"
			    		+ " \"Tom Brady\" , "
			    		+ "  \"tb12@gmail.com\" , "
			    		+ " '1977-08-03' , "
			    		+ " \"5051234563\", "
			    		+ " \"Tampa Bay\" , "
			    		+ " 33603 , "
			    		+ " \"admin\" )";
			    
			    String query2="insert into user values(\"arod12\" ,"
			    		+ " \"arod12\" ,"
			    		+ " \"Aaron Rodgers\" , "
			    		+ "  \"arod12@gmail.com\" , "
			    		+ " '1983-12-02' , "
			    		+ " \"6051234729\", "
			    		+ " \"Green Bay\" , "
			    		+ " 54229 , "
			    		+ " \"customer\" )";
			    
			    String query3="insert into user values(\"drewbrees9\" ,"
			    		+ " \"drewbrees9\" ,"
			    		+ " \"Drew Brees\" , "
			    		+ "  \"drewbrees9@gmail.com\" , "
			    		+ " '1979-01-15' , "
			    		+ " \"3051237654\", "
			    		+ " \"New Orleans\" , "
			    		+ " 70032 , "
			    		+ " \"customer\" )";
			    
			    
			    PreparedStatement stmt1 = con1.prepareStatement(query1);
			    PreparedStatement stmt2 = con1.prepareStatement(query2);
			    PreparedStatement stmt3 = con1.prepareStatement(query3);

				// Set auto-commit to false
				con1.setAutoCommit(false);

				stmt1.executeUpdate(sql1);
				stmt2.executeUpdate(sql2);
				stmt3.executeUpdate(sql3);


				//Explicitly commit statements to apply changes
				con1.commit();
			
			//inserting values into orders table
			String ql1="insert into orders value(1,'tombrady12',30, '1999-03-12')";
			String ql2="insert into orders value(2,'tombrady12',300, '1999-03-09')";
			String ql3="insert into orders value(100,'tombrady12',1020, '2008-09-05')";
			
            Statement st=con1.createStatement();
			
			con1.setAutoCommit(false);
			
			st.addBatch(ql1);
			st.addBatch(ql2);
			st.addBatch(ql3);
			
			statement.executeBatch();

			con1.commit();
		}
		catch(SQLException ex)
		{
			System.out.println(ex);
		}
	}

	private void truncateLineItemsTbl() {
		//make sure that your order table is empty or you'll have to add those sql queries
		String sql1="drop table line_items";
		String sql2="drop table user";
		String sql3="create table user(user_id varchar(20) not null "
				                      +" , user_password varchar(20) not null"
				                      +" ,  name varchar(25) not null"
				                      +" , email_id varchar(50) not null"
				                      +" , dob date not null"
				                      +" , contact_number varchar(15) not null"
				                      +" ,  address varchar(100) not null"
				                      +" , pincode int not null"
				                      +" , role varchar(10) not null"
				                      +" , primary key (user_id))";
				
		String sql4="create table line_items (lineitems_id int auto_increment "
				+ "   , product_id int not null"
				+ "   , quantity int not null"
				+ "  , order_id int not null"
				+ "  , primary key (lineitems_id) "
				+ "   , foreign key (product_id) references product(product_id)";
	//			+ "   , foreign key (order_id) references orders(order_id))";
		String sql5 = "truncate table orders";
		try {
				
			// Set auto-commit to false
			con1.setAutoCommit(false);
		
			Statement stmt = con1.createStatement();
			
			stmt.addBatch(sql1);
			stmt.addBatch(sql2);
			stmt.addBatch(sql3);
			stmt.addBatch(sql4);
			stmt.addBatch(sql5);
			stmt.executeBatch();
	
			con1.commit();
		}
		catch (SQLException e) {
			System.out.println(e);
			try {
				con1.rollback();
			}catch(SQLException ex) {}
			
		}		
	}
	
	@After
	public void tearDown() throws Exception {
		con1.close();
	}

	@Test
	public void lineItemCreateTest()
	{
		LineItem item=new LineItem(2,2,2,2);
		String userID="tombrady12";
		boolean flag=LineItemsDAO.createLineItem(item, userID);
		assertTrue(flag);
	}

}

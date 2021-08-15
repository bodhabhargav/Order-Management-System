package com.nttdata.casestudy.test;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.nttdata.casestudy.dao.ProductDAO;
import com.nttdata.casestudy.model.Product;
import com.nttdata.casestudy.util.ConnectionUtil;

public class ProductDAOTest {

	final static ConnectionUtil con=new ConnectionUtil();
	Connection con1 = null;
	


	@Before
	public void setUp() throws Exception {
		
		con1=con.estConnection();

		try{			
			truncateProductTbl();			
			String sql1="insert into product values (1, \"Sanitizer\", 250, 1)";			
			String sql2="insert into product values (2, \"Soap\", 50, 1)";		
			String sql3="insert into product values (3, \"Hand Lotion\", 150, 1)";			
			String sql4="insert into product values (4, \"Shampoo\", 300, 1)";			
			String sql5="insert into product values (5, \"Granola Bar\", 25, 2)";			
			String sql6="insert into product values (6, \"Boost\", 500, 2)";			
			Statement stmt = con1.createStatement();

			// Set auto-commit to false
			con1.setAutoCommit(false);

			stmt.addBatch(sql1);
			stmt.addBatch(sql2);
			stmt.addBatch(sql3);
			stmt.addBatch(sql4);
			stmt.addBatch(sql5);
			stmt.addBatch(sql6);

			// Create an int[] to hold returned values
			int[] count = stmt.executeBatch();

			//Explicitly commit statements to apply changes
			con1.commit();
		}
		catch (SQLException e) {
			System.out.println(e);
		}
				
	}

	private void truncateProductTbl() {
//		String sql1 = "drop table line_items";
		String sql2 = "truncate table Product";

		try {
				
			// Set auto-commit to false
			con1.setAutoCommit(false);
		
			Statement stmt = con1.createStatement();
			
		//	stmt.addBatch(sql1);
			stmt.addBatch(sql2);
			
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
	public void testGetPriceForExistingProduct(){
		
		int prodId = 1;
		
		int price= ProductDAO.getPrice(prodId);
		System.out.println("Price for Prod-1: " + price);
		assertTrue(price == 250);		
	}

	@Test
	public void testGetPriceForNonExistingProduct(){
		
		int prodId = 100;
		
		int price= ProductDAO.getPrice(prodId);
		System.out.println("Price for Prod-1: " + price);
		assertTrue(price == 0);		
	}

	
	@Test
	public void testDisplayAll(){
		
		int categoryVar = 1;
		
		List<Product> li= ProductDAO.displayAll(categoryVar);
		System.out.println("Prods for cat-1: " + li);
		assertTrue(li.size() == 4);		
	}

	@Test
	public void testGetAllProducts(){
		
		List<Product> li= ProductDAO.getAllProducts();
		System.out.println("Prods: " + li);
		assertTrue(li.size() == 6);		
	}

}

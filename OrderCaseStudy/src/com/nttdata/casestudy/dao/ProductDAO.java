package com.nttdata.casestudy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nttdata.casestudy.model.Product;
import com.nttdata.casestudy.util.ConnectionUtil;

public class ProductDAO {
	
	final static ConnectionUtil con=new ConnectionUtil();

	public static List<Product> displayAll(int categoryVar)
	{
		
		List<Product> li=new ArrayList<Product>();
		try(Connection con1=con.estConnection();)
		{
			String sql="select * from product where category_id=?";
			PreparedStatement statement=con1.prepareStatement(sql);
            statement.setInt(1, categoryVar);
			ResultSet resultSet=statement.executeQuery();
			Product product=null;
			while(resultSet.next())
			{
			    product = new Product();
			    product.setId(resultSet.getInt(1));
			    product.setName(resultSet.getString(2));
			    product.setPrice(resultSet.getInt(3));
			    product.setCategoryId(resultSet.getInt(4));
			    li.add(product);
			    
			}
			
		}
		catch (SQLException e) {
			System.out.println(e);
		}
		
		return li;
	}
	
	public static int getPrice(int productID)
	{
		int result=0;
		try(Connection con1=con.estConnection();)
		{
			String sql="select product_price from product where product_id=?";
			PreparedStatement statement=con1.prepareStatement(sql);
			
			statement.setInt(1, productID);
			
			ResultSet rs=statement.executeQuery();
			if (rs.next()) {
				result=rs.getInt(1);
			}else {
				System.out.println("Pls enter valid product ID....");
			}
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
		return result;
	}
	public static List<Product> getAllProducts()
	{
		
		List<Product> li=new ArrayList<Product>();
		try(Connection con1=con.estConnection();)
		{
			String sql="select * from product";
			PreparedStatement statement=con1.prepareStatement(sql);
			ResultSet resultSet=statement.executeQuery();
			Product product=null;
			while(resultSet.next())
			{
			    product = new Product();
			    product.setId(resultSet.getInt(1));
			    product.setName(resultSet.getString(2));
			    product.setPrice(resultSet.getInt(3));
			    product.setCategoryId(resultSet.getInt(4));
			    li.add(product);
			    
			}
			
		}
		catch(SQLException ex)
		{
			System.out.println(ex);
		}
		return li;
      }
}

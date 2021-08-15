package com.nttdata.casestudy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nttdata.casestudy.model.LineItem;
import com.nttdata.casestudy.util.ConnectionUtil;

public class LineItemsDAO {

	final static ConnectionUtil con= new ConnectionUtil();
	
	public static boolean createLineItem(LineItem item, String userId)
	{
		boolean flag=false;
		try(Connection con1=con.estConnection();)
		{
			String sqlquery="select max(order_id) as order_id from orders where user_id=?";
			PreparedStatement st=con1.prepareStatement(sqlquery);
			st.setString(1, userId );
			ResultSet rs=st.executeQuery();
			int orderID=0;
			if(rs.next()){
				orderID=rs.getInt(1);
			}
			else
				System.out.println("Invalid User ID entered...");
			
			String sql="insert into line_items (product_id,quantity,order_id) values(?, ?, ?)";
			PreparedStatement statement=con1.prepareStatement(sql);
		     
			statement.setInt(1, item.getProductID());
			statement.setInt(2, item.getQuantity());
			statement.setInt(3, orderID);
			
			int rows=statement.executeUpdate();
			
			if(rows>0)
				flag=true;
			//System.out.println(rows);
		}
		catch(SQLException ex)
		{
			System.out.println(ex);
		}
		return flag;
	}
}

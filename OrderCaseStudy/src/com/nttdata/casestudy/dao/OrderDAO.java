package com.nttdata.casestudy.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.nttdata.casestudy.model.Order;
import com.nttdata.casestudy.model.OrderView;
import com.nttdata.casestudy.util.ConnectionUtil;

public class OrderDAO {

	final static ConnectionUtil con=new ConnectionUtil();

	//create new order for customer
	public static void createOrder(Order order)
	{
		
		try(Connection con1=con.estConnection();)
		{
			String sql="insert into orders (user_id, order_price, order_date) values(?, ?, ?)";
			PreparedStatement statement=con1.prepareStatement(sql);
			statement.setString(1, order.getUser_id());
			statement.setInt(2, order.getOrder_price());
			statement.setDate(3, order.getOrder_date());

			int rows=statement.executeUpdate();
			if(rows>0)
			{
				System.out.println("Your Order has been successfully placed!");
				
			}
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
	}

	//display orders for a particular customer by passing the customers user ID
	public static List<OrderView> fetchOrdersForCustomer(String userId)
	{
		List<OrderView> order = new ArrayList<OrderView>();

		try(Connection con1=con.estConnection();)
		{
			String sql="select orders.order_id, user.email_id, user.name, user.address,"
					+ " line_items.quantity,"
					+ " product.product_name"
					+ " from orders"
					+ " inner join user on orders.user_id=user.user_id"
					+ " inner join line_items on orders.order_id = line_items.order_id"
					+ " inner join product on  product.product_id =  line_items.product_id"
					+ " where orders.user_id=?";
			PreparedStatement statement=con1.prepareStatement(sql);

			statement.setString(1,userId);
			ResultSet rs = statement.executeQuery();

			OrderView obj = null;
			while (rs.next()) {

				obj=new OrderView();
				obj.setOrderID(rs.getInt(1));
				obj.setEmailID(rs.getString(2));
				obj.setUserName(rs.getString(3));
				obj.setAddress(rs.getString(4));
				obj.setQuantity(rs.getInt(5));
				obj.setProductName(rs.getString(6));
				order.add(obj);
			}
			

		}
		catch(SQLException e)
		{
			System.out.println(e);
		}		
		return order;

	}

	/*
	 * Delete order by passing the userID as an argument and then select the order
	 *   so that you can delete the order for the particular OrderId passed.
	 */
	public static void removeCustomerOrder(String userID)
	{
		Scanner inp=new Scanner(System.in);
		try(Connection con1=con.estConnection();)
		{
			String sql="select * from orders where user_id=?";  

			PreparedStatement statement=con1.prepareStatement(sql);
			statement.setString(1,userID);
			ResultSet rs=statement.executeQuery();

			List<Order> li=new ArrayList<Order>();
			while(rs.next())
			{
				Order order=new Order();
				order.setOrder_id(rs.getInt(1));
                order.setUser_id(rs.getString(2)); 
                order.setOrder_price(rs.getInt(3));
                order.setOrder_date(rs.getDate(4));
                li.add(order);
			}
			System.out.println("These are the orders you have placed: \n"+ li);
			System.out.println("Enter the order id to delete the order");
			int delOrderID=inp.nextInt();
			
			//deleting all the orders for a particular order id
			String delsql="delete from orders where order_id=?";
			PreparedStatement st=con1.prepareStatement(delsql);
			st.setInt(1,delOrderID);

			int row = st.executeUpdate();

			if(row>0)
				System.out.println("Successfully deleted the order: "+delOrderID);
			else
				System.out.println("Order hasn't been deleted.");
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
	}
	
	public static List<OrderView> displayAllOrders()
	{
		List<OrderView> li=new ArrayList<OrderView>();
		try(Connection con1=con.estConnection();)
		{
			String sql="select orders.order_id, user.email_id, user.name, user.address,"
					+ " line_items.quantity,"
					+ " product.product_name"
					+ " from orders"
					+ " inner join user on orders.user_id=user.user_id"
					+ " inner join line_items on orders.order_id = line_items.order_id"
					+ " inner join product on  product.product_id =  line_items.product_id";
			PreparedStatement statement=con1.prepareStatement(sql);
			OrderView obj=null;
			ResultSet rs=statement.executeQuery();
			while(rs.next())
			{
				obj=new OrderView();
				obj.setOrderID(rs.getInt(1));
				obj.setEmailID(rs.getString(2));
				obj.setUserName(rs.getString(3));
				obj.setAddress(rs.getString(4));
				obj.setQuantity(rs.getInt(5));
				obj.setProductName(rs.getString(6));
				li.add(obj);
			}
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
		return li;
	}
	
	public static void removeAdminOrder(int removeOrder)
	{
		try(Connection con1=con.estConnection();)
		{
			String ql="delete from line_items where order_id=?";
			PreparedStatement st=con1.prepareStatement(ql);
			st.setInt(1, removeOrder);
			st.executeUpdate();
			
			String sql="delete from orders where order_id=?";
			PreparedStatement statement=con1.prepareStatement(sql);
			statement.setInt(1, removeOrder);
			
			
			
			int row = statement.executeUpdate();
			
			
			//ResultSet resultSet=statement.executeUpdate();

			if(row>0)
				System.out.println("Successfully deleted the order");
			else
				System.out.println("Order hasn't been deleted.");
			
		}
		catch(SQLException ex)
		{
			System.out.println(ex);
		}
	}
	
	public static void modifyOrder(int orderID, int productId, int quantity)
	{
		try(Connection con1=con.estConnection();)
		{
			String sql="update line_items"
					+ " set product_id=? , quantity=?  where order_id=?";
			PreparedStatement statement=con1.prepareStatement(sql);
			
			statement.setInt(1, productId);
			statement.setInt(2, quantity);
			statement.setInt(3, orderID);
			
			statement.executeUpdate();
			
			String productSql="select product_price from product"
					+ " where product_id=?";
			PreparedStatement productSt=con1.prepareStatement(productSql);
			productSt.setInt(1, productId);
			
			ResultSet rs=productSt.executeQuery();
			
			int productPrice=0;
			if(rs.next())
			{
				productPrice=rs.getInt(1);
			}
			
			String orderSql="update orders"
					         + " set order_price=?"
					         + " where order_id=?";
			PreparedStatement st=con1.prepareStatement(orderSql);
			
			st.setInt(1, quantity*productPrice );
			st.setInt(2, orderID);
			
			st.executeUpdate();
			
		}
		catch(SQLException ex)
		{
			System.out.println(ex);
		}
	}
}

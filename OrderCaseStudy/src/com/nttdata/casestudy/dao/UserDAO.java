package com.nttdata.casestudy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nttdata.casestudy.model.User;
import com.nttdata.casestudy.util.ConnectionUtil;
public class UserDAO {

	//Register a user to the site incase the user is new.
	public static boolean registerUser(User user)
	{
		ConnectionUtil con=new ConnectionUtil();

		boolean flag=false;

		try(Connection con1=con.estConnection();)
		{
			String sql="insert into user values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement=con1.prepareStatement(sql);
			statement.setString(1, user.getUserId());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getName());
			statement.setString(4, user.getEmail());
			statement.setDate(5, user.getDob());
			statement.setString(6, user.getPhoneNum());
			statement.setString(7, user.getAddress());
			statement.setInt(8, user.getPinCode());
			statement.setString(9, user.getRole());
			int rows=statement.executeUpdate();
			if(rows>0)
			{
				flag=true;
				return flag;
			}
		}
		catch(SQLException ex)
		{
			System.out.println(ex);
		}
		return flag;
	}



	//display all the users present in the user table
	public static void displayUser()
	{
		ConnectionUtil con=new ConnectionUtil();

		try(Connection con1=con.estConnection();)
		{
			String sql="select * from user";
			PreparedStatement statement = con1.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery(); // for select oper. // 100 
			// we need to collect the data / records those are fetched from oracle / mysql

			while(resultSet.next()) { //   100			
				System.out.println("User Id: "+ resultSet.getString(1)
				+ " Name: "+ resultSet.getString(3) 
				+" Email Id: "+ resultSet.getString(4)
				+" Date of Birth: "+resultSet.getDate(5)
				+" Contact Number: "+resultSet.getString(6)
				+" Address: "+resultSet.getString(7)
				+" Pincode: "+resultSet.getInt(8)
				+" Role: "+resultSet.getString(9));
			}
		}
		catch(SQLException ex)
		{
			System.out.println(ex);
		}
	}

	//display the values for a particular user by giving his user id as an argument
	public static void displayUser(User user)
	{
		ConnectionUtil con=new ConnectionUtil();

		try(Connection con1=con.estConnection();)
		{
			String sql="select * from user where user_id=?";
			PreparedStatement statement = con1.prepareStatement(sql);
			statement.setString(1,user.getUserId());
			ResultSet resultSet = statement.executeQuery(); 

			System.out.println("User Id: "+ resultSet.getString(1)
			+ " Name: "+ resultSet.getString("name") 
			+" Email Id: "+ resultSet.getString(4)
			+" Date of Birth: "+resultSet.getDate(5)
			+" Contact Number: "+resultSet.getString(6)
			+" Address: "+resultSet.getString(7)
			+" Pincode: "+resultSet.getInt(8)
			+" Role: "+resultSet.getString(9));
		}
		catch(SQLException ex)
		{
			System.out.println(ex);
		}
	}


	//check whether the entered User Id and Password are legit credentials
	public static User authenticate(String userId,String password)
	{
		ConnectionUtil con=new ConnectionUtil();
		User user = new User();
		
		try(Connection con1=con.estConnection();)
		{
			String sql="select * from user where user_id=? and user_password = ?";
			PreparedStatement statement = con1.prepareStatement(sql);
			statement.setString(1,userId);
			statement.setString(2,password);
			ResultSet rs = statement.executeQuery(); 
			
			user.setUserId(userId);
			user.setPassword(password);
			
			if (rs.next()) {
				user.setAddress(rs.getString("address"));
				user.setDob(rs.getDate("dob"));
				user.setEmail(rs.getString("email_id"));
				user.setPhoneNum(rs.getString("contact_number"));
				user.setPinCode(rs.getInt("pincode"));
				user.setRole(rs.getString("role"));
				user.setAuthSuccessful(true);
			}else {
				user.setAuthSuccessful(false);
			}
		}				
		catch(SQLException ex)
		{
			System.out.println(ex);
		}
		return user;
	}


	//This method will return the role of the user logged in.
	public static String checkAdmin(String userid)
	{
		String role="";

		ConnectionUtil con=new ConnectionUtil();
		try(Connection con1=con.estConnection();)
		{
			String sql="Select role from user where user_id=?";
			PreparedStatement statement=con1.prepareStatement(sql);
			statement.setString(1, userid);
			ResultSet resultSet=statement.executeQuery();
            
			if(resultSet.next())
			{
			    role=resultSet.getString("role");
			}
			
		}
		catch(SQLException ex)
		{
			System.out.println(ex);
		}
		return role.toUpperCase();
	}

}

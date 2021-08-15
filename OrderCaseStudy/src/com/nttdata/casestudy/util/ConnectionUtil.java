package com.nttdata.casestudy.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	public Connection estConnection() {

		DBProperties props = new DBProperties();

		String dbURL=  props.getdbURL();//"jdbc:mysql://localhost:3306/case_study";
		Connection con=null;

		try
		{
			Class.forName(props.getDriverName());
			con=DriverManager.getConnection(dbURL, props.getUserId(), props.getPassword());
		}
		catch(ClassNotFoundException ex)
		{
			System.out.println("Class Not found exception "+ ex.getStackTrace());
		}
		catch (SQLException e) {
			System.out.println("SQL exception was found "+e.getStackTrace());

		}
		return con;
	}
}

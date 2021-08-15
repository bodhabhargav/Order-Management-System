package com.nttdata.casestudy.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.nttdata.casestudy.dao.LineItemsDAO;
import com.nttdata.casestudy.dao.OrderDAO;
import com.nttdata.casestudy.dao.ProductDAO;
import com.nttdata.casestudy.dao.UserDAO;
import com.nttdata.casestudy.model.LineItem;
import com.nttdata.casestudy.model.Order;
import com.nttdata.casestudy.model.User;

public class GenerateObj {
       
	public static void registerUser() throws IOException {
		
		User user=new User();
		
		String input="";
		
		Scanner inp=new Scanner(System.in);
		
		BufferedReader sc=new BufferedReader(new InputStreamReader(System.in));
		
		//setting user id 
		System.out.println("Enter the User Id which you want to set:");
	    input=inp.next();
		user.setUserId(input);
		
		//setting user password
		System.out.println("Enter the password which you want to set:");	
		input=inp.next();
		user.setPassword(input);
		
		//setting user Name
		System.out.println("Enter your Name:");
		input=sc.readLine();
		user.setName(input);
		
		//setting email id
		System.out.println("Enter the email id at which you want to get updates at:");		
		input=inp.next();		
		user.setEmail(input);
		
		//setting date of birth
		System.out.println("Enter your Date of birth:");
		input=inp.next();
		java.sql.Date dob = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			dob = new java.sql.Date(sdf.parse(input).getTime());
		}catch(Exception e) {
			System.out.println("Exception while parsing date:" + input);
		}
		user.setDob(dob);
		
		//setting contact number
		System.out.println("Enter your phone number:");
		input=inp.next();
		user.setPhoneNum(input);
		
		//setting address
		System.out.println("Enter your address:");
		input=sc.readLine();
		user.setAddress(input);
		
		//setting pincode
		int tempx=0;
		System.out.println("Enter your pincode:");			
		tempx=inp.nextInt();		
		user.setPinCode(tempx);
		
		//setting User Role
		System.out.println("What type of role are you registering for: Admin or Customer?");		
		input=inp.next();
		
		if("ADMIN".equals(input.toUpperCase()))
			user.setRole("admin");
		else if("CUSTOMER".equals(input.toUpperCase()))
			user.setRole("customer");
		
		
		System.out.println("You have been successfully registered for the website.");
		
		UserDAO.registerUser(user);
	}
	
	public static void registerOrder(User user)
	{
		Order order=new Order();
		LineItem item = new LineItem();
		
		
		int userInput=0;
		Scanner inp=new Scanner(System.in);
		
		order.setUser_id(user.getUserId());
		
		//Setting the product id to the product id present inside the Line Items object.
		System.out.println("Enter the Id for the product which you want to order: ");
		userInput=inp.nextInt();
		item.setProductID(userInput);
		
		
		//Asking the user to enter the number of products of one kind
		System.out.println("Enter the quantity of the products you want to order: ");
		int quant=inp.nextInt();
		item.setQuantity(quant);
		
		
		//setting order price
		int price = quant*ProductDAO.getPrice(userInput);
		order.setOrder_price(price);
		
		//setting current date as Orderdate
		long millis=System.currentTimeMillis();
		order.setOrder_date(new java.sql.Date(millis));

		OrderDAO.createOrder(order);	
		LineItemsDAO.createLineItem(item, user.getUserId());
		
	}
	
	
}

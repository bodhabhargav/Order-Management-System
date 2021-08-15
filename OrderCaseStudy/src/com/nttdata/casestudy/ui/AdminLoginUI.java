package com.nttdata.casestudy.ui;

import java.util.List;
import java.util.Scanner;

import com.nttdata.casestudy.dao.OrderDAO;
import com.nttdata.casestudy.dao.UserDAO;
import com.nttdata.casestudy.model.OrderView;
import com.nttdata.casestudy.model.User;

public class AdminLoginUI {

	public static void adminUI()
	{
		Scanner inp=new Scanner(System.in);
		
		System.out.println("  Menu");
		System.out.println("____________________");
		System.out.println("1. View All Orders: ");
		System.out.println("2. View a specific customers order details: ");
		System.out.println("3. Place an order.");
		System.out.println("Enter either 1, 2 or 3 to perform an action");
		int adminInp=inp.nextInt();
		if(adminInp==1)
		{
			System.out.println(OrderDAO.displayAllOrders());
			System.out.println("Do you want to delete any order? Press Y or N: ");
			String decision=inp.next();
			
			if("Y".equals(decision.toUpperCase()))
			{
				System.out.println("Enter the order id to delete the order: ");
				int orderID=inp.nextInt();
				
				//Have to make a change here
				
				OrderDAO.removeAdminOrder(orderID);
			}
		}
		else if(adminInp==2)
		{
			System.out.println("Enter the User ID for the customer: ");
			String str=inp.next();
			
			//Setting all the order details to a list by passing the user id as an argument.
			List<OrderView> orderList=OrderDAO.fetchOrdersForCustomer(str);
			System.out.println(orderList);
		}
		else if(adminInp==3)
		{
			//place an order for a customer or admin
			System.out.println("Enter the username: ");
			String userName=inp.next();
			System.out.println("Enter the password: ");
			String password=inp.next();
			User authUser = UserDAO.authenticate(userName,password);
			if (authUser.isAuthSuccessful())
			{
			     OrderUI.placeOrder(authUser);
			}
			else
			{
				System.out.println("Invalid credentials entered. Login again.");
				adminUI();
			}
		}
		else 
		{
			System.out.println("The number you entered isn't b/w 1 to 3. Please enter a value b/w 1-3: ");
			adminUI();
		}
		
	}
	
}

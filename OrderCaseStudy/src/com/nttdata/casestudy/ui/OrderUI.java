package com.nttdata.casestudy.ui;

import java.util.Scanner;

import com.nttdata.casestudy.dao.OrderDAO;
import com.nttdata.casestudy.dao.ProductDAO;
import com.nttdata.casestudy.model.User;

public class OrderUI {
	public static void placeOrder(User user) {
		
		Scanner inp=new Scanner(System.in);
		System.out.println("Press 1 to Order something!");
		System.out.println("Press 2 to cancel your Existing Order");
		System.out.println("Press 3 to change your order.");
		int orderInput=inp.nextInt();

		//Create Order flow
		if (orderInput == 1) {
			System.out.println("1. Personal Care");
			System.out.println("2. Nutrition");
			System.out.println("3. Health Care");
			System.out.println("Enter a number from 1, 2 and 3 to browse the category of products");
			
			orderInput=inp.nextInt();
			while (orderInput > 3){
				
				System.out.println("Invlid Cateegory Id. Please enter correct Category ID");
				orderInput=inp.nextInt();				
			}			

			System.out.println(ProductDAO.displayAll(orderInput));
			
			GenerateObj.registerOrder(user);
			
		}
		else if(orderInput==2)
		{
			OrderDAO.removeCustomerOrder(user.getUserId());
		}
		else if(orderInput==3)
		{
			System.out.println("Please enter your User ID: ");
			String userId=inp.next();
			System.out.println("\nThese are the orders you have placed: \n\n");
			
			 System.out.println(OrderDAO.fetchOrdersForCustomer(userId));
			
			//asking the user to enter the order id to modify that order.
			System.out.println("Enter the id for the order which you want to change: ");
			int orderId=inp.nextInt();
			
			System.out.println("This is the products list: ");
			System.out.println(ProductDAO.getAllProducts());
			
			//asking the user to enter the product id to add that product to his buy list.
			System.out.println("Enter the Product Id for which you want to buy: ");
			int productId=inp.nextInt();
			
			//asking the user to enter the quantity of products he wants to buy
			System.out.println("Enter the quantity of products you want: ");
			int quantity=inp.nextInt();
			
			OrderDAO.modifyOrder(orderId,productId,quantity);
		}
		else
		{
			System.out.println("Invalid Option. Please enter 1 or 2");
			placeOrder(user);
		}
		
	}
}

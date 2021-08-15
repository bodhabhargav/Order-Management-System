package com.nttdata.casestudy.ui;

import java.util.List;
import java.util.Scanner;

import com.nttdata.casestudy.dao.OrderDAO;
import com.nttdata.casestudy.dao.UserDAO;
import com.nttdata.casestudy.model.OrderView;
import com.nttdata.casestudy.model.User;

public class LoginUser {

	public static void login()
	{
		Scanner inp=new Scanner(System.in);
		System.out.println("Enter your user id to login: ");
		String userId=inp.next();
		System.out.println("Enter your password to login: ");
		String password=inp.next();
		User authUser = UserDAO.authenticate(userId,password);
		if (!authUser.isAuthSuccessful()) {
			System.out.println("Invalid credentials .. Please enter try again");
			login();
		}else {
			System.out.println("User Profile:" + authUser);
			if (authUser.isAdminUser()) {
				List<OrderView> orders = OrderDAO.fetchOrdersForCustomer(userId);
				AdminLoginUI.adminUI();
			}
			else if(authUser.isCustomerUser())
			{
				OrderUI.placeOrder(authUser);
			}
		}
		
	}

}

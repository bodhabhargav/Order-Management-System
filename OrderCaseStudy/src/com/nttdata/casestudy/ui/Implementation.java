package com.nttdata.casestudy.ui;

import java.io.IOException;
import java.util.Scanner;

public class Implementation {

	public static void main(String[] args) throws IOException {
		
		Scanner inp=new Scanner(System.in);
		
		System.out.println("Main Menu");
		System.out.println("________________");
	    System.out.println("• Register");
	    System.out.println("• Login");
	    System.out.println("• Exit");
	    System.out.println("Enter your choice (1 for Registration, 2 for Login, 3 for Exit)");
	    int options=inp.nextInt();
	    if(options==1)
	    {
	    	GenerateObj.registerUser();
	    }
	    else if(options==2)
	    {	    	
	    	LoginUser.login();
	    }
	    
	}
}

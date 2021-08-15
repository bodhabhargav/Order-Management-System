package com.nttdata.casestudy.model;

public class OrderView {

	private int orderID;
	private String emailID;
	private String userName;
	private String address;
	private int quantity;
	private String productName;
	
	public OrderView(int orderID, String emailID, String userName, String address, int quantity, String productName) {
		super();
		this.orderID = orderID;
		this.emailID = emailID;
		this.userName = userName;
		this.address = address;
		this.quantity = quantity;
		this.productName = productName;
	}
	
	public OrderView()
	{
	}

	//setters and getters
	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public String toString() {
		return "OrderView [orderID=" + orderID + ", emailID=" + emailID + ", userName=" + userName + ", address="
				+ address + ", quantity=" + quantity + ", productName=" + productName + "] \n";
	}
	
	
	
}

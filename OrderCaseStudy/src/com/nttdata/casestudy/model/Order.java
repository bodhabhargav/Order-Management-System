package com.nttdata.casestudy.model;

import java.sql.Date;

public class Order {

	private int order_id;
	private String user_id;
	private int order_price;
	private Date order_date;
	public Order(int order_id, String user_id, int order_price, Date order_date) {
		super();
		this.order_id = order_id;
		this.user_id = user_id;
		this.order_price = order_price;
		this.order_date = order_date;
	}

	//empty constructor
	public Order()
	{
		super();
	}

	@Override
	public String toString() {
		return "Order [order_id=" + order_id + ", user_id=" + user_id + ", order_price=" + order_price + ", order_date="
				+ order_date + "] \n";
	}

	//setter and getter methods
	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getOrder_price() {
		return order_price;
	}

	public void setOrder_price(int order_price) {
		this.order_price = order_price;
	}

	public Date getOrder_date() {
		return order_date;
	}

	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}

}

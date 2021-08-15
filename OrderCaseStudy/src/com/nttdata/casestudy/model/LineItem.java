package com.nttdata.casestudy.model;

public class LineItem {

	private int lineItemID;
	private int productID;
	private int quantity;
	private int orderID;
	public LineItem(int lineItemID, int productID, int quantity, int orderID) {
		super();
		this.lineItemID = lineItemID;
		this.productID = productID;
		this.quantity = quantity;
		this.orderID = orderID;
	}

	public LineItem()
	{
	}

	@Override
	public String toString() {
		return "LineItems [lineItemID=" + lineItemID + ", productID=" + productID + ", quantity=" + quantity
				+ ", orderID=" + orderID + "] \n";
	}


	//Getters and setters
	public int getLineItemID() {
		return lineItemID;
	}

	public void setLineItemID(int lineItemID) {
		this.lineItemID = lineItemID;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}


}

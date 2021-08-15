package com.nttdata.casestudy.model;

public class Product {
	private int id;
	private String name;
	private int price;
	private int categoryId;
	public Product(int id, String name, int price, int categoryId) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.categoryId = categoryId;
	}

	public Product()
	{	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public String toString() {
		return "[Product id=" + id + ", Product name=" + name + ", Product price=" + price + ", Product categoryId=" + categoryId + "] \n";
	}



}

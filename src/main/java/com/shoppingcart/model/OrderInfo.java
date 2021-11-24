package com.shoppingcart.model;

import java.util.Date;
import java.util.List;

public class OrderInfo {
	
	private String id;
	
	private Date orderDate;
	
	private int orderNum;
	
	private double amount;
	
	private String CustomerName;
	
	private String customerAddress;
	
	private String customerEmail;
	
	private String customerPhone;
	
	private List<OrderDetailInfo> orderDetailInfos;

	public OrderInfo() {
		super();
	}
	// Using for Hibernate Query
	public OrderInfo(String id, Date orderDate, int orderNum, double amount, String customerName, String customerAddress,
			String customerEmail, String customerPhone) {
		super();
		this.id = id;
		this.orderDate = orderDate;
		this.orderNum = orderNum;
		this.amount = amount;
		this.CustomerName = customerName;
		this.customerAddress = customerAddress;
		this.customerEmail = customerEmail;
		this.customerPhone = customerPhone;
		
	}

	public OrderInfo(String id, Date orderDate, int orderNum, double amount, String customerName, String customerAddress,
			String customerEmail, String customerPhone, List<OrderDetailInfo> orderDetailInfos) {
		super();
		this.id = id;
		this.orderDate = orderDate;
		this.orderNum = orderNum;
		this.amount = amount;
		CustomerName = customerName;
		this.customerAddress = customerAddress;
		this.customerEmail = customerEmail;
		this.customerPhone = customerPhone;
		this.orderDetailInfos = orderDetailInfos;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getCustomerName() {
		return CustomerName;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAdress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public List<OrderDetailInfo> getOrderDetailInfos() {
		return orderDetailInfos;
	}

	public void setOrderDetailInfos(List<OrderDetailInfo> orderDetailInfos) {
		this.orderDetailInfos = orderDetailInfos;
	}
	
	
	
	
	
	
	
}

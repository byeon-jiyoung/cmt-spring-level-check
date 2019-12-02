package kr.co.domain;

public class Total {
	private int orderNumber;
	private int customerNumber;
	private String customerName;
	private int productNumber;
	private String productName;
	
	public Total(int orderNumber, int customerNumber, String customerName, int productNumber, String productName) {
		this.orderNumber = orderNumber;
		this.customerNumber = customerNumber;
		this.customerName = customerName;
		this.productNumber = productNumber;
		this.productName = productName;
	}
	
	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public int getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(int productNumber) {
		this.productNumber = productNumber;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public String toString() {
		return "Total [orderNumber=" + orderNumber + ", customerNumber=" + customerNumber + ", customerName="
				+ customerName + ", productNumber=" + productNumber + ", productName=" + productName + "]";
	}

	
}

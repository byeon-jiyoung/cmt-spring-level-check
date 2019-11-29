package kr.co.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Collect {
	private static Collect collect = null;
    private List<Order> orderList;
    private Map<Integer, Customer> customerMap;
    private Map<Integer, Product> productMap;
    private List<Total> totalList;
    
    private Collect() {}
    
    public static Collect getInstance() {
    	if(collect == null) {
    		collect = new Collect();
    	}
    	return collect;
    }

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	public Map<Integer, Customer> getCustomerMap() {
		return customerMap;
	}

	public void setCustomerMap(Map<Integer, Customer> customerMap) {
		this.customerMap = customerMap;
	}

	public Map<Integer, Product> getProductMap() {
		return productMap;
	}

	public void setProductMap(Map<Integer, Product> productMap) {
		this.productMap = productMap;
	}

	public List<Total> getTotalList() {
		return totalList;
	}

	public void setTotalList(List<Total> totalList) {
		this.totalList = totalList;
	}

	public List<Total> getCollect(Map<Integer, Customer> customerMap, Map<Integer, Product> productMap, List<Order> orderList) {
		List<Total> list = new ArrayList<Total>();
		
		for(int i=0; i<orderList.size(); i++) {
			int orderNumber = orderList.get(i).getOrderNumber();
			int customerNumber = orderList.get(i).getCustomerNumber();
			String customerName = customerMap.get(customerNumber).getCustomerName();
			int productNumber = orderList.get(i).getProductNumber();
			String productName = productMap.get(productNumber).getProductName();
			
			Total total = new Total(orderNumber, customerNumber, customerName, productNumber, productName);
			list.add(total);
		}
		setTotalList(list);
		
		return totalList;
	}
}

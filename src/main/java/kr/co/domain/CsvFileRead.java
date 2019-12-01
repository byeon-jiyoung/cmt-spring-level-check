package kr.co.domain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvFileRead {
	public void getCsvData(List<String> csvFileList) { 
		Map<Integer, Customer> customerMap = new HashMap<Integer, Customer>();
        Map<Integer, Product> productMap = new HashMap<Integer, Product>();
        List<Order> orderList = new ArrayList<Order>();

        BufferedReader br = null;
        String line;

        Collect collect = Collect.getInstance();
        
        try {
            for (int i = 0; i < csvFileList.size(); i++) {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(csvFileList.get(i))));
                while ((line = br.readLine()) != null) {
                    if (line.indexOf("식별번호") < 0) {
                        String[] field = line.split(",");
                        String fileName = csvFileList.get(i).substring(csvFileList.get(i).lastIndexOf(File.separator)+1, csvFileList.get(i).lastIndexOf("."));
                        if (fileName.equals("customer")) {
                            Customer customer = new Customer();
                            for (int j = 0; j < field.length; j++) {
                            	if (j == 0) {
                                    customer.setCustomerNumber(Integer.parseInt(field[j]));
                                } else if (j == 1) {
                                    customer.setCustomerName(field[j]);
                                    customerMap.put(Integer.parseInt(field[i]), customer);
                                    collect.setCustomerMap(customerMap);
                                }
                            }
                        } else if (fileName.equals("product")) {
                            Product product = new Product();
                            for (int j = 0; j < field.length; j++) {
                                if (j == 0) {
                                    product.setProductNumber(Integer.parseInt(field[j]));
                                } else if (j == 1) {
                                    product.setProductName(field[j]);
                                    productMap.put(Integer.parseInt(field[i]), product);
                                    collect.setProductMap(productMap);
                                }
                            }
                        } else if (fileName.equals("order")) {
                            Order order = new Order();
                            for (int j = 0; j < field.length; j++) {
                                if (j == 0) {
                                    order.setOrderNumber(Integer.parseInt(field[j]));
                                } else if (j == 1) {
                                	order.setCustomerNumber(Integer.parseInt(field[j]));
                                } else if (j == 2) {
                                	order.setProductNumber(Integer.parseInt(field[j]));
                                	orderList.add(order);
                                	collect.setOrderList(orderList);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

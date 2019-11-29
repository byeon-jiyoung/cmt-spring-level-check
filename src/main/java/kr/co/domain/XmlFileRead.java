package kr.co.domain;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlFileRead {
    public void getXmlData(List<String> xmlFileList) {
    	Map<Integer, Customer> customerMap = new HashMap<Integer, Customer>();
        Map<Integer, Product> productMap = new HashMap<Integer, Product>();
        List<Order> orderList = new ArrayList<Order>();
        
        Collect collect = Collect.getInstance();
        
        try {
            for (int i = 0; i < xmlFileList.size(); i++) {
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance(); // 1.문서를 읽기위한 공장을 만들어야 한다.
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder(); // 2.빌더 생성
                Document doc = dBuilder.parse(new File(xmlFileList.get(i))); // 3.생성된 빌더를 통해서 xml문서를 Document객체로 파싱해서 가져온다
                doc.getDocumentElement().normalize();// 문서 구조 안정화
                Element root = doc.getDocumentElement();
                if(root.getNodeName().equals("customer")) {
                    NodeList nodeList = root.getElementsByTagName("customerInfo");
                    
                    for (int j = 0; j < nodeList.getLength(); j++) {
                        Node node = nodeList.item(j);
                        Customer customer = new Customer();
                        if (node.getNodeType() == Node.ELEMENT_NODE) {
                            Element element = (Element) node;
                            customer.setCustomerNumber(Integer.parseInt(getTagValue("customerId", element)));
                            customer.setCustomerName(getTagValue("customerName", element));
                            customerMap.put(Integer.parseInt(getTagValue("customerId", element)), customer);
                            collect.setCustomerMap(customerMap);
                        }
                    }
                } else if(root.getNodeName().equals("product")) {
                    NodeList nodeList = root.getElementsByTagName("productInfo");
                    
                    for (int j = 0; j < nodeList.getLength(); j++) {
                        Node node = nodeList.item(j);
                        Product product = new Product();
                        if (node.getNodeType() == Node.ELEMENT_NODE) {
                            Element element = (Element) node;
                            product.setProductNumber(Integer.parseInt(getTagValue("productId", element)));
                            product.setProductName(getTagValue("productName", element));
                            productMap.put(Integer.parseInt(getTagValue("productId", element)), product);
                            collect.setProductMap(productMap);
                        }
                    }
                } else if(root.getNodeName().equals("order")) {
                    NodeList nodeList = root.getElementsByTagName("orderInfo");
                    
                    for (int j = 0; j < nodeList.getLength(); j++) {
                        Node node = nodeList.item(j);
                        Order order = new Order();
                        if (node.getNodeType() == Node.ELEMENT_NODE) {
                            Element element = (Element) node;
                            order.setOrderNumber(Integer.parseInt(getTagValue("orderId", element)));
                            order.setCustomerNumber(Integer.parseInt(getTagValue("customerId", element)));
                            order.setProductNumber(Integer.parseInt(getTagValue("productId", element)));
                            orderList.add(order);
                            collect.setOrderList(orderList);
                        }
                    }
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getTagValue(String sTag, Element element) {
        NodeList subNodeList = element.getElementsByTagName(sTag).item(0).getChildNodes();
        Node nodeValue = (Node) subNodeList.item(0);
        
        return nodeValue.getNodeValue();
    }
}

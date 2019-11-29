package kr.co.controller;

import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.domain.CsvFileRead;
import kr.co.domain.Customer;
import kr.co.domain.Order;
import kr.co.domain.Product;
import kr.co.domain.Total;
import kr.co.domain.Collect;
import kr.co.domain.XmlFileRead;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ShopController {
	
	private static final Logger logger = LoggerFactory.getLogger(ShopController.class);
	
	String path = System.getProperty("user.dir") + File.separator + "spring_level_check" + File.separator;
	String[] files = {"customer.xml","product.csv","order.xml"};
	
	@RequestMapping(value = "/shop", method = RequestMethod.GET)
	public String shop(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		List<String> csvFileList = new ArrayList<String>();
        List<String> xmlFileList = new ArrayList<String>();
        
        for(int i=0; i<files.length; i++) {
            if(files[i].substring(files[i].lastIndexOf(".")+1).equals("csv")) {
                csvFileList.add(path+files[i]);
                
            }else if(files[i].substring(files[i].lastIndexOf(".")+1).equals("xml")) {
                xmlFileList.add(path+files[i]);
            }
        }
        
        Collect collect = Collect.getInstance();
        
        CsvFileRead csvFileRead = new CsvFileRead();
        csvFileRead.getCsvData(csvFileList);
        XmlFileRead xmlFileRead = new XmlFileRead();
        xmlFileRead.getXmlData(xmlFileList);
        
        List<Total> totalList = collect.getCollect(collect.getCustomerMap(), collect.getProductMap(), collect.getOrderList());
        
        model.addAttribute("totalList", totalList);
        
		return "shop/orders";
	}

}

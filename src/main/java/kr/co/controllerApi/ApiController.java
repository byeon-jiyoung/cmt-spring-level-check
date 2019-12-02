package kr.co.controllerApi;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import kr.co.domain.Collect;
import kr.co.domain.CsvFileRead;
import kr.co.domain.Total;
import kr.co.domain.XmlFileRead;

@Controller
public class ApiController {
	private static final Logger logger = LoggerFactory.getLogger(ApiController.class);
	
	String path = System.getProperty("user.dir") + File.separator + "spring_level_check" + File.separator;
	String[] files = {"customer.xml","product.csv","order.xml"};
	
	@RequestMapping(value = "orders", method = RequestMethod.GET)
	public String api(Model model) {
		logger.info("api controller");
		
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

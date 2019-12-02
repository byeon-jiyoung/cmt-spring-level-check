package kr.co.controllerShop;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import kr.co.domain.Authority;

@Controller
public class ShopController {
	private static final Logger logger = LoggerFactory.getLogger(ShopController.class);
	
	@RequestMapping(value = "orders", method = RequestMethod.GET)
	public String shop(HttpSession session) {
		logger.info("shop controller");
		
		Authority authority = new Authority();
		authority.setAuthorityId("id");
		
		if(authority.getAuthorityId() != null) {
			logger.info("권한있음");
			session.setAttribute("Auth", authority);
		}
		
		return "forward:/api/orders";
	}
	
}
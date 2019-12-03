package kr.co.controllerShop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import kr.co.domain.Authority;

@Controller
public class ShopController {
	private static final Logger logger = LoggerFactory.getLogger(ShopController.class);
	
	@RequestMapping(value = "orders", method = RequestMethod.GET)
	public String shop(Model model) {
		logger.info("shop controller");
		
		//권한이 있는 경우에만 Authority객체 생성
		Authority authority = new Authority();
		authority.setAuthorityId("id");
		authority.setAuthorityName("name");
		
		model.addAttribute("Authority", authority);
		
		return "forward:/api/orders";
	}
	
}
package kr.co.forearlybird.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "admin/dashboard", method = { RequestMethod.GET, RequestMethod.POST })
public class DashBoardController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@RequestMapping(value="/", method = { RequestMethod.GET, RequestMethod.POST})
	public String DashBoard() {
		logger.info("대쉬보드");
		return "admin/A_dashboard/A_dashBoard";
	}
}
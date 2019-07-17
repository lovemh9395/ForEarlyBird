package kr.co.forearlybird.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.forearlybird.MainController;

public class ReplyController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	// 댓글 등록
	@RequestMapping(value = "/R_make", method = RequestMethod.GET)
	public String R_make(Model model) {
		logger.info("댓글 등록 페이지");

		return "R_make";
	}
	
	// 댓글 수정
	@RequestMapping(value = "/R_update", method = RequestMethod.GET)
	public String R_update(Model model) {
		logger.info("댓글 수정 페이지");

		return "R_update";
	}
	
	// 댓글 삭제
	@RequestMapping(value = "/R_delete", method = RequestMethod.GET)
	public String R_delete(Model model) {
		logger.info("댓글 삭제 페이지");

		return "R_delete";
	}
}

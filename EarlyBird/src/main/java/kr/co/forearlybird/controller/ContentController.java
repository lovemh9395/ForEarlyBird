package kr.co.forearlybird.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.forearlybird.MainController;

@Controller
@RequestMapping(value = "controller", method = {RequestMethod.GET,RequestMethod.POST})
public class ContentController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	//게시글 목록 보기
	@RequestMapping(value = "/C_list", method = RequestMethod.GET)
	public String C_list(Model model) {
		logger.info("게시글 목록 보기 페이지");
		
		return "C_list";
	}
	
	//게시글 등록
	@RequestMapping(value = "/C_make", method = RequestMethod.GET)
	public String C_make(Model model) {
		logger.info("게시글 등록 페이지");
		
		return "C_make";
	}
	
	//게시글 수정
	@RequestMapping(value = "/C_update", method = RequestMethod.GET)
	public String C_update(Model model) {
		logger.info("게시글 수정 페이지");
		
		return "C_update";
	}
	
	//게시글 삭제
	@RequestMapping(value = "/C_delete", method = RequestMethod.GET)
	public String C_delete(Model model) {
		logger.info("게시글 삭제 페이지");
		
		return "C_delete";
	}
	
	//게시글 검색
	@RequestMapping(value = "/C_search", method = RequestMethod.GET)
	public String C_search(Model model) {
		logger.info("게시글 검색 페이지");
		
		return "C_search";
	}
	
	//게시글 상세보기
	@RequestMapping(value = "/C_detail", method = RequestMethod.GET)
	public String C_detail(Model model) {
		logger.info("게시글 상세보기 페이지");
		
		return "C_detail";
	}
	
	//게시글 추천하기
	@RequestMapping(value = "/C_recommend", method = RequestMethod.GET)
	public String C_recommend(Model model) {
		logger.info("게시글 추천하기 페이지");
		
		return "C_recommend";
	}
}

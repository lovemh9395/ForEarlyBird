package kr.co.forearlybird.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.co.forearlybird.MainController;
import kr.co.forearlybird.domain.Post;
import kr.co.forearlybird.service.PostService;

@Controller
@RequestMapping(value = "post")
public class PostController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	private PostService service;
	
	
	// 게시글 목록 보기
	@RequestMapping(value = "/P_list", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView P_list() {
		ModelAndView mav = new ModelAndView();
		logger.info("게시글 목록 보기 페이지");
		List<Post> P_list=new ArrayList<>();
		P_list = service.P_list();
		System.out.println(P_list);
		mav.setViewName("post/P_list");
		logger.info("1");
		mav.addObject("list",P_list);
		logger.info("2");
		return mav;
	}

	// 게시글 등록
	@RequestMapping(value = "/P_make", method = RequestMethod.GET)
	public String P_make(Model model) {
		logger.info("게시글 등록 페이지");

		return "P_make";
	}

	// 게시글 수정
	@RequestMapping(value = "/P_update", method = RequestMethod.GET)
	public String P_update(Model model) {
		logger.info("게시글 수정 페이지");

		return "P_update";
	}

	// 게시글 삭제
	@RequestMapping(value = "/P_delete", method = RequestMethod.GET)
	public String P_delete(Model model) {
		logger.info("게시글 삭제 페이지");

		return "P_delete";
	}

	// 게시글 검색
	@RequestMapping(value = "/P_search", method = RequestMethod.GET)
	public String P_search(Model model) {
		logger.info("게시글 검색 페이지");

		return "P_search";
	}

	// 게시글 상세보기
	@RequestMapping(value = "/P_detail", method = RequestMethod.GET)
	public String P_detail(Model model) {
		logger.info("게시글 상세보기 페이지");

		return "post/P_detail";
	}

	// 게시글 추천하기
	@RequestMapping(value = "/P_recommend", method = RequestMethod.GET)
	public String P_recommend(Model model) {
		logger.info("게시글 추천하기 페이지");

		return "P_recommend";
	}
}

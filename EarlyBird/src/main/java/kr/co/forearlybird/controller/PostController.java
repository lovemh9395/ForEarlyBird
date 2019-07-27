package kr.co.forearlybird.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.forearlybird.MainController;
import kr.co.forearlybird.domain.Post;
import kr.co.forearlybird.paging.Criteria;
import kr.co.forearlybird.paging.PageMaker;
import kr.co.forearlybird.service.PostService;

@Controller
@RequestMapping(value = "post")
public class PostController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	private PostService service;

	// 게시글 목록 보기
	@RequestMapping(value = "/P_detail", method = { RequestMethod.GET, RequestMethod.POST })
	public String P_detail(Model model, HttpServletRequest request) {
		logger.info("게시글 목록 보기 페이지");
		int post_id = Integer.parseInt(request.getParameter("post_id"));
		logger.info("P_detail controller:" + post_id);
		Post P_detail = service.P_detail(post_id);
		System.out.println(P_detail);
		model.addAttribute("P_detail", P_detail);
		return "post/P_detail";
	}

	// 게시글 등록
	@RequestMapping(value = "/P_make", method = RequestMethod.POST)
	public String P_make(HttpServletRequest request, HttpSession session, Map map) {
		logger.info("게시글 등록 페이지");
		map.put("post_title", request.getParameter("newPost_title"));
		map.put("post_content", request.getParameter("newPost_content"));
		map.put("mem_userid", session.getAttribute("FindId"));
		service.P_make(map);
		return "post/P_make";
	}

	@RequestMapping(value = "/P_make", method = RequestMethod.GET)
	public String P_make() {
		logger.info("게시글 등록 후~~~");
		return "post/P_list";
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

//	// 게시글 상세보기
//	@RequestMapping(value = "/P_detail", method = RequestMethod.GET)
//	public String P_detail(Model model) {
//		logger.info("게시글 상세보기 페이지");
//
//		return "post/P_detail";
//	}

	// 게시글 추천하기
	@RequestMapping(value = "/P_recommend", method = RequestMethod.GET)
	public String P_recommend(Model model) {
		logger.info("게시글 추천하기 페이지");

		return "P_recommend";
	}

	// 페이징 처리해보장
	@RequestMapping(value = "/P_list", method = RequestMethod.GET)
	public String listPage(@ModelAttribute("cri") Criteria cri, Model model) throws Exception {
		logger.info("페이징 처리 페이지");
		logger.info(cri.toString());
		model.addAttribute("list", service.listCriteria(cri)); // 게시판의 글 리스트
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listCountCriteria(cri));
		model.addAttribute("pageMaker", pageMaker); // 게시판 하단의 페이징 관련, 이전페이지, 페이지 링크 , 다음 페이지

		return "post/P_list";
	}
}

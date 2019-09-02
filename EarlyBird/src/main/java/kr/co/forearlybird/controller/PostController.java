package kr.co.forearlybird.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.forearlybird.domain.Post;
import kr.co.forearlybird.paging.Criteria;
import kr.co.forearlybird.paging.PageMaker;
import kr.co.forearlybird.service.PostService;

@Controller
@RequestMapping(value = "post")
public class PostController {
	private static final Logger logger = LoggerFactory.getLogger(PostController.class);

	@Autowired
	private PostService service;

	// 게시글 등록
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/P_make", method = RequestMethod.POST)
	public String P_make(HttpServletRequest request, HttpSession session, Map map) {
		logger.info("게시글 등록 Controller");
		map.put("brd_id", request.getParameter("brd_id"));
		map.put("post_title", request.getParameter("post_title"));
		map.put("post_content", request.getParameter("post_content"));
		map.put("mem_userid", session.getAttribute("useridd"));
		service.P_make(map);
		
		return "post/P_list";
	}

	//게시글 등록 후
	@RequestMapping(value = "/P_make", method = RequestMethod.GET)
	public String P_make() {
		logger.info("게시글 등록 후 Controller");
		return "post/P_make";
	}

	//게시글 수정 하기
	@RequestMapping(value = "/P_update", method = RequestMethod.GET)
	public String P_update(@RequestParam("post_id") int post_id, Post updatePost, Model model) {
		logger.info("게시글 수정 Controller");
		updatePost = service.P_detail(post_id);
		model.addAttribute("updatePost", updatePost);
		return "post/P_update";
	}

	// 게시글 수정
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/P_update", method = RequestMethod.POST)
	public String P_update(HttpServletRequest request, Map map) {
		logger.info("게시글 수정 Controller");
		map.put("post_id", request.getParameter("post_id"));
		map.put("post_title", request.getParameter("post_title"));
		map.put("post_content", request.getParameter("post_content"));
		service.P_update(map);
		return "post/P_list";
	}

	// 게시글 삭제
	@RequestMapping(value = "/P_delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String P_delete(HttpServletRequest request) {
		logger.info("게시글 삭제 Controller");
		int post_id = Integer.parseInt(request.getParameter("post_id"));
		service.P_delete(post_id);
		return "post/P_list";
	}

	// 게시글 검색
	@RequestMapping(value = "/P_search", method = RequestMethod.GET)
	public String P_search(Model model) {
		logger.info("게시글 검색 Controller");
		return "P_search";
	}

	// 게시글 추천하기
	@ResponseBody
	@RequestMapping(value = "/P_recommand", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView P_recommend(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam int post_id) {
		logger.info("게시글 추천하기 Controller");
		ModelAndView view = new ModelAndView();
		Cookie[] cookies = request.getCookies();

		// 비교하기 위해 새로운 쿠키
		Cookie viewCookies = null;
		// 쿠키가 있을 경우
		if (cookies != null && cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				// Cookie의 name이 cookie + p_listNo와 일치하는 쿠키를 viewCookie에 넣어줌
				if (cookies[i].getName().equals("cookies" + post_id)) {
					//logger.info("게시물 추천 ----- 처음 쿠키가 생성한 뒤 들어옴.");
					viewCookies = cookies[i];
				}
			}
		}
		// 만일 viewCookie가 null일 경우 쿠키를 생성해서 조회수 증가 로직을 처리함.
		if (viewCookies == null) {
			//logger.info("게시물 추천 ----- cookie 없음");
			// 쿠키 생성(이름, 값)
			Cookie newCookies = new Cookie("cookies" + post_id, "|" + post_id + "|");
			//logger.info("게시물 추천 ----- 쿠키 생성 이름,값 " + newCookies);
			// 쿠키 추가
			response.addCookie(newCookies);
			// 쿠키를 추가 시키고 조회수 증가시킴
			int result = service.P_recommand(post_id);
			if (result > 0) {
				logger.info("게시물 추천 ----- 추천수 증가");
			} else {
				logger.info("게시물 추천 ----- 추천수 증가 에러");
			}
		}
		// viewCookie가 null이 아닐경우 쿠키가 있으므로 조회수 증가 로직을 처리하지 않음.
		else {
			//logger.info("게시물 추천 ----- cookie 있음");
			// 쿠키 값 받아옴.
			viewCookies.getValue();
			//logger.info("게시물 추천 ----- cookie 값 : " + value);
		}
		view.setViewName("post/P_detail");
		return view;
	}

	// 페이징 처리해보장
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/P_list", method = RequestMethod.GET)
	public String listPage(@ModelAttribute("cri") Criteria cri, Model model, @RequestParam("brd_id") int brd_id,
			Map map) throws Exception {
		logger.info("페이징 처리 Controller");
		map.put("cri", cri);
		map.put("brd_id", brd_id);
		model.addAttribute("list", service.listCriteria(map)); // 게시판의 글 리스트
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listCountCriteria(map));
		model.addAttribute("pageMaker", pageMaker); // 게시판 하단의 페이징 관련, 이전페이지, 페이지 링크 , 다음 페이지
		logger.info(model.toString());
		return "post/P_list";
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ResponseBody
	@RequestMapping(value = "/P_detail")
	public ModelAndView p_listDetail(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int post_id, @ModelAttribute("cri") Criteria cri, Map map) throws Exception {
		logger.info("게시물 상세보기 및 조회수 증가 방지 Controller");
		ModelAndView view = new ModelAndView();
		// 해당 게시판 번호를 받아 리뷰 상세페이지로 넘겨줌
		map.put("cri", cri);
		map.put("post_id", post_id);
		PageMaker pageMaker = makePageMaker(map);
		view.addObject("R_list", service.replyListCriteria(map));
		logger.info(view.toString());
		view.addObject("pageMaker", pageMaker); // 게시판 하단의 페이징 관련, 이전페이지, 페이지 링크 , 다음 페이지
		Cookie[] cookie = request.getCookies();
		// 비교하기 위해 새로운 쿠키
		Cookie viewCookie = null;
		// 쿠키가 있을 경우
		if (cookie != null && cookie.length > 0) {
			for (int i = 0; i < cookie.length; i++) {
				// Cookie의 name이 cookie + p_listNo와 일치하는 쿠키를 viewCookie에 넣어줌
				if (cookie[i].getName().equals("cookie" + post_id)) {
					//logger.info("게시물 목록보기 및 조회수 증가 방지 --------처음 쿠키가 생성한 뒤 들어옴.");
					viewCookie = cookie[i];
				}
			}
		}
		if (cookie != null) {
			//logger.info("게시물 목록보기 및 조회수 증가 방지 -------- 해당 상세 리뷰페이지로 넘어감");
			// 만일 viewCookie가 null일 경우 쿠키를 생성해서 조회수 증가 로직을 처리함.
			if (viewCookie == null) {
				//logger.info("게시물 목록보기 및 조회수 증가 방지 --------cookie 없음");
				// 쿠키 생성(이름, 값)
				Cookie newCookie = new Cookie("cookie" + post_id, "|" + post_id + "|");
				// 쿠키 추가
				response.addCookie(newCookie);
				// 쿠키를 추가 시키고 조회수 증가시킴
				int result = service.updateHit(post_id);
				if (result > 0) {
					logger.info("게시물 목록보기 및 조회수 증가 방지 --------조회수 증가");
				} else {
					logger.info("게시물 목록보기 및 조회수 증가 방지 --------조회수 증가 에러");
				}
			}
			// viewCookie가 null이 아닐경우 쿠키가 있으므로 조회수 증가 로직을 처리하지 않음.
			else {
				//logger.info("게시물 목록보기 및 조회수 증가 방지 --------cookie 있음");
				// 쿠키 값 받아옴.
				viewCookie.getValue();
				//logger.info("게시물 목록보기 및 조회수 증가 방지 --------cookie 값 : " + value);
			}
			view.setViewName("post/P_detail");
			Post p_list = service.P_detail(post_id);
			view.addObject("P_detail", p_list);
			return view;
		}
		return view;
	}

	@SuppressWarnings("rawtypes")
	private PageMaker makePageMaker(Map map) throws Exception {
		logger.info("페이징 처리 메소드");
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri((Criteria) map.get("cri"));
		pageMaker.setTotalCount(service.replyListCountCriteria(map));

		return pageMaker;
	}
}



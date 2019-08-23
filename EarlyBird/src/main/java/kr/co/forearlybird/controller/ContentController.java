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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.forearlybird.service.ContentService;

@Controller
@RequestMapping(value = "content", method = { RequestMethod.GET, RequestMethod.POST })
public class ContentController {
	private static final Logger logger = LoggerFactory.getLogger(ContentController.class);

	@Autowired
	ContentService service;

	// 게시글 목록 보기
	@RequestMapping(value = "/C_list_M", method = RequestMethod.GET)
	public String C_list(HttpServletRequest request, Model model) {
		logger.info("게시글 목록 보기 페이지");
		int idx = Integer.parseInt(request.getParameter("brd_id"));
		model.addAttribute("list", service.C_list_M(idx));
		return "content/C_list_M";
	}

	// 게시글 등록
	@RequestMapping(value = "/C_make", method = RequestMethod.GET)
	public String C_make(Model model) {
		logger.info("게시글 등록 페이지");

		return "content/C_make";
	}

	// 게시글 수정
	@RequestMapping(value = "/C_update", method = RequestMethod.GET)
	public String C_update(Model model) {
		logger.info("게시글 수정 페이지");

		return "content/C_update";
	}

	// 게시글 삭제
	@RequestMapping(value = "/C_delete", method = RequestMethod.GET)
	public String C_delete(Model model) {
		logger.info("게시글 삭제 페이지");

		return "content/C_delete";
	}

	// 게시글 검색
	@RequestMapping(value = "/C_search", method = RequestMethod.GET)
	public String C_search(Model model) {
		logger.info("게시글 검색 페이지");

		return "content/C_search";
	}

	// 게시글 상세보기
	@RequestMapping(value = "/C_detail", method = RequestMethod.GET)
	public String C_detail(Model model) {
		logger.info("게시글 상세보기 페이지");

		return "content/C_detail";
	}

	// 게시글 추천하기
	@RequestMapping(value = "/C_recommand", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView C_recommend(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam int cnt_id) {
		logger.info("컨텐츠 게시글 추천하기 페이지");
//		int result = service.P_recommand(post_id);
		ModelAndView view = new ModelAndView();
		int brd_id = 0;

		if (request.getParameter("brd_id") != null) {
			brd_id = Integer.parseInt(request.getParameter("brd_id"));
		}

		if (brd_id != 0) {
			logger.info("123412341234123412341234");
			view.addObject("list", service.C_list_M(brd_id));
		} else {
			logger.info("567856785678567856785678");
			view.addObject("list", service.Main_C_list());
		}

		Cookie[] cookies = request.getCookies();
		Cookie viewCookies = null; // 비교하기 위해 새로운 쿠키
		logger.info("cookies --------------------------------------" + cookies);

		if (cookies != null && cookies.length > 0) {// 쿠키가 있을 경우
			for (int i = 0; i < cookies.length; i++) {// Cookie의 name이 cookie + p_listNo와 일치하는 쿠키를 viewCookie에 넣어줌
				if (cookies[i].getName().equals("cookies" + cnt_id)) {
					logger.info("게시물 추천 ----- 처음 쿠키가 생성한 뒤 들어옴.");
					viewCookies = cookies[i];
				}
			}
		}
//		if (result != 0) {
//			System.out.println("게시물 추천 ----- System - 해당 상세 리뷰페이지로 넘어감");
//
//			view.addObject("P_recommand",result);
//			
//			logger.info(view.toString());
		if (viewCookies == null) { // 만일 viewCookie가 null일 경우 쿠키를 생성해서 조회수 증가 로직을 처리함.
			logger.info("게시물 추천 ----- cookie 없음");
			Cookie newCookies = new Cookie("cookies" + cnt_id, "|" + cnt_id + "|"); // 쿠키 생성(이름, 값)
			logger.info("게시물 추천 ----- 쿠키 생성 이름,값 " + newCookies);
			response.addCookie(newCookies);// 쿠키 추가
			int result = service.C_recommand(cnt_id);// 쿠키를 추가 시키고 조회수 증가시킴
			if (result > 0) {
				logger.info("게시물 추천 ----- 조회수 증가");
			} else {
				logger.info("게시물 추천 ----- 조회수 증가 에러");
			}
		} else {// viewCookie가 null이 아닐경우 쿠키가 있으므로 조회수 증가 로직을 처리하지 않음.
			logger.info("게시물 추천 ----- cookie 있음");
			String value = viewCookies.getValue();// 쿠키 값 받아옴.
			logger.info("게시물 추천 ----- cookie 값 : " + value);
		}
		if (brd_id != 0) {
			logger.info("123412341234123412341234");
			view.setViewName("content/C_list_M");
		} else {
			logger.info("567856785678567856785678");
			view.setViewName("/");
		}
		return view;
	}

	// 게시글 추천하기
	@RequestMapping(value = "/C_view", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String C_view(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam int cnt_id, Model model) {
		logger.info("컨텐츠 게시글 조회하기 페이지");
//		int result = service.P_recommand(post_id);

		ModelAndView view = new ModelAndView();
		int brd_id = 0;

		if (request.getParameter("brd_id") != null) {
			logger.info("123412341234123412341234");
			brd_id = Integer.parseInt(request.getParameter("brd_id"));
			model.addAttribute("list", service.C_list_M(Integer.parseInt(request.getParameter("brd_id"))));
		}

		if (brd_id == 0) {
			logger.info("567856785678567856785678");
			view.addObject("list", service.Main_C_list());
		}

		Cookie[] cookie = request.getCookies();
		// 비교하기 위해 새로운 쿠키
		Cookie viewCookie = null;
		logger.info("cookies --------------------------------------" + cookie);
		// 쿠키가 있을 경우
		if (cookie != null && cookie.length > 0) {
			for (int i = 0; i < cookie.length; i++) {
				// Cookie의 name이 cookie + p_listNo와 일치하는 쿠키를 viewCookie에 넣어줌
				if (cookie[i].getName().equals("cookie" + cnt_id)) {
					logger.info("게시물 추천 ----- 처음 쿠키가 생성한 뒤 들어옴.");
					viewCookie = cookie[i];
				}
			}
		}
//		if (result != 0) {
//			System.out.println("게시물 추천 ----- System - 해당 상세 리뷰페이지로 넘어감");
//
//			view.addObject("P_recommand",result);
//			
//			logger.info(view.toString());
		// 만일 viewCookie가 null일 경우 쿠키를 생성해서 조회수 증가 로직을 처리함.
		if (viewCookie == null) {
			logger.info("게시물 추천 ----- cookie 없음");
			// 쿠키 생성(이름, 값)
			Cookie newCookie = new Cookie("cookie" + cnt_id, "|" + cnt_id + "|");
			logger.info("게시물 추천 ----- 쿠키 생성 이름,값 " + newCookie);
			// 쿠키 추가
			response.addCookie(newCookie);
			// 쿠키를 추가 시키고 조회수 증가시킴
			int result = service.C_view(cnt_id);
			if (result > 0) {
				logger.info("게시물 추천 ----- 조회수 증가");
			} else {
				logger.info("게시물 추천 ----- 조회수 증가 에러");
			}
		}
		// viewCookie가 null이 아닐경우 쿠키가 있으므로 조회수 증가 로직을 처리하지 않음.
		else {
			logger.info("게시물 추천 ----- cookie 있음");
			// 쿠키 값 받아옴.
			String value = viewCookie.getValue();
			logger.info("게시물 추천 ----- cookie 값 : " + value);
		}
		if (request.getParameter("brd_id") != null) {
			String connectlink = service.C_connectlink(cnt_id);
			model.addAttribute("connectlink", connectlink);
			return "content/C_list_M";
		} else {
			return "/";
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "menu_btn", method = { RequestMethod.GET, RequestMethod.POST })
	public String menu_btn(HttpServletRequest request, Model model, Map map) {
		logger.info("메뉴 버튼 클릭 시 값 가져오는 Controller");
		int brd_num = Integer.parseInt(request.getParameter("brd_num"));
		int brd_id = Integer.parseInt(request.getParameter("brd_id"));
		map.put("brd_num", brd_num);
		map.put("brd_id", brd_id);
		model.addAttribute("list", service.menu_btn(map));
		return "content/C_list_M";
	}
}

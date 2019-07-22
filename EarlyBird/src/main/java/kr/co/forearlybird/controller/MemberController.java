package kr.co.forearlybird.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.cj.xdevapi.Result;

import kr.co.forearlybird.MainController;
import kr.co.forearlybird.domain.Member;
import kr.co.forearlybird.service.MemberService;
import kr.co.forearlybird.service.MemberServiceImpl;

@Controller
@RequestMapping(value = "member")
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	MemberService service;

	// 로그인으로 이동 (사용자,관리자 레벨로 이동)
	@RequestMapping(value = "/M_login", method = RequestMethod.GET)
	public String M_login(Model model) {
		logger.info("로그인 페이지");
		return "member/M_login";
	}
	// 로그인 처리하기
	@RequestMapping(value = "/M_login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws Exception {
		logger.info("login 처리");
		logger.info(request.toString());
		logger.info(request.getParameter("email"));
		logger.info(request.getParameter("password"));
		logger.info(request.getParameter("name"));
		Map map = service.login(request);
		if(map == null) {
			
			return "redirect:/";
		}
		if (map.toString().equals("{}")) { // 관리자급이 아닙니다.
			logger.info(map.toString() + "1");
		} else {
			// 세션 부여
			logger.info(map.toString() + "2");
			session.setAttribute("user", map);
		}
		return "Mainpage";
	}

	// 회원가입으로 이동
	@RequestMapping(value = "/M_make", method = RequestMethod.POST)
	public String M_make(HttpServletRequest request) {
		logger.info("회원가입 페이지");
		service.make(request);
		return "Mainpage";
	}

	// 아이디 찾기
	@RequestMapping(value = "/M_searchID", method = RequestMethod.GET)
	public String M_searchID(Model model) {
		logger.info("아이디 찾기 페이지");

		return "member/M_searchID";
	}

	// 비밀번호 찾기
	@RequestMapping(value = "/M_searchPWD", method = RequestMethod.GET)
	public String M_searchPWD(Model model) {
		logger.info("비밀번호 찾기 페이지");

		return "member/M_searchID";
	}

	// 비밀번호 찾기 상세페이지
	@RequestMapping(value = "/M_detailPWD", method = RequestMethod.GET)
	public String M_detailPWD(Model model) {
		logger.info("비밀번호 찾기 상세 페이지");

		return "member/M_searchID";
	}

	// 내 정보 보기
	@RequestMapping(value = "/M_info", method = RequestMethod.GET)
	public ModelAndView info(@RequestParam("id") String id) throws Exception {
		logger.info("내 정보 페이지");
		logger.info(id);
		ModelAndView mav = new ModelAndView();
		Member info = service.detail(id);
		System.out.println(info);
		mav.addObject("info",info);
		mav.setViewName("member/M_info");
		
		return mav;
	}

	// 개인정보수정
	@RequestMapping(value = "/M_update", method = {RequestMethod.GET,RequestMethod.POST})
	public String M_update(Member member) {
		logger.info("정보수정 페이지");
		String nick = member.getMem_nickname();
		String phone = member.getMem_phone();
		String id = member.getMem_userid();
		logger.info(id);
		logger.info(phone);
		logger.info(nick);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("nick", nick);
		map.put("phone", phone);
		
		service.update(map);
		
		return "redirect:../";
	}

	// 내 글 보기
	@RequestMapping(value = "/M_list", method = RequestMethod.GET)
	public String M_list(Model model) {
		logger.info("내 글 보기 페이지");

		return "member/M_list";
	}

	// 로그아웃
	@RequestMapping(value = "/M_logout", method = {RequestMethod.GET,RequestMethod.POST})
	public String M_logout(HttpSession session) {
		logger.info("로그아웃 페이지");
		session.invalidate();
		return "redirect:../";
	}

	// 회원탈퇴
	@RequestMapping(value = "/M_delete", method = RequestMethod.GET)
	public String M_delete(@RequestParam("id") String id) {
		logger.info("회원탈퇴 페이지");
		service.delete(id);
		
		
		return "Mainpage";
	}
}

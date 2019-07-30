package kr.co.forearlybird.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.forearlybird.MainController;
import kr.co.forearlybird.service.MemberService;

@Controller
@RequestMapping(value = "member")
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	MemberService service;

	// 로그인으로 이동 (사용자,관리자 레벨로 이동)
	@RequestMapping(value = "/M_login", method = RequestMethod.GET)
	public String M_login() {
		logger.info("로그인 페이지");
		return "member/M_login";
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/M_login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpSession session) throws Exception {
		logger.info("login 처리");
		Map map = service.M_login(request);

		if (!map.toString().equals("{}")) { // 로그인실패
			// 세션 부여
			session.setAttribute("user", map);
			return "A_mainpage";
		}
		return "redirect:/";
	}

	// 회원가입으로 이동
	@RequestMapping(value = "/M_make", method = RequestMethod.GET)
	public String M_make(Model model) {
		logger.info("회원가입 페이지");

		return "member/M_make";
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
	public String info(Model model) {
		logger.info("내 정보 페이지");

		return "member/M_info";
	}

	// 개인정보수정
	@RequestMapping(value = "/M_update", method = RequestMethod.GET)
	public String M_update(Model model) {
		logger.info("정보수정 페이지");

		return "member/M_update";
	}

	// 내 글 보기
	@RequestMapping(value = "/M_list", method = RequestMethod.GET)
	public String M_list(Model model) {
		logger.info("내 글 보기 페이지");

		return "member/M_list";
	}

	// 로그아웃
	@RequestMapping(value = "/M_logout", method = RequestMethod.GET)
	public String M_logout(HttpSession session, Model model) {
		logger.info("로그아웃 페이지");
		session.invalidate();
		return "redirect:/";
	}

	// 회원탈퇴
	@RequestMapping(value = "/M_delete", method = RequestMethod.GET)
	public String M_delete(Model model) {
		logger.info("회원탈퇴 페이지");

		return "member/M_delete";
	}
}

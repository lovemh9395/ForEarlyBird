package kr.co.forearlybird.controller;

import java.util.HashMap;
import java.util.Map;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.forearlybird.MainController;
import kr.co.forearlybird.domain.Member;
import kr.co.forearlybird.service.MemberService;

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
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/M_login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws Exception {
		logger.info("login 처리");
		logger.info(request.toString());
		logger.info(request.getParameter("email"));
		logger.info(request.getParameter("password"));
		Map map = service.login(request);
		System.out.println("------------+++++++++++++-----------------------" + map);

		if (map == null) { // map 가져오기 오류
			return "redirect:/";
		}

		if (map.get("level").toString().equals("1")) { // 차단회원인 경우
			return "Mainpage";
		} else if (map.get("level").toString().equals("4")) { // 탈퇴회원인 경우
			return "Mainpage";
		} else if (map.get("level").toString().equals("9")) { // 관리자인 경우
			// 세션 부여
			logger.info(map.toString() + "2");
			session.setAttribute("user", map);
			session.setAttribute("profilephoto", map.get("profilephoto"));
			session.setAttribute("useridd", map.get("id"));
			if (session.getAttribute("FindId") != null) {
				session.removeAttribute("FindId");
			}
			return "A_mainpage";
		} else {
			if (map.toString().equals("{}")) { // 가입정보가 없는 경우
				logger.info("1");
			} else {
				// 세션 부여
				logger.info(map.toString() + "2");
				session.setAttribute("user", map);
				session.setAttribute("profilephoto", map.get("profilephoto"));
				session.setAttribute("useridd", map.get("id"));
				if (session.getAttribute("FindId") != null) {
					session.removeAttribute("FindId");
				}
			}
		}
		return "Mainpage";
	}

//	// 회원가입으로 이동
//	@RequestMapping(value = "/M_make", method = RequestMethod.POST)
//	public String M_make(HttpServletRequest request) {
//		logger.info("회원가입 페이지");
//		service.make(request);
//		return "Mainpage";
//	}

	// 아이디 찾기
	@RequestMapping(value = "/M_searchID", method = { RequestMethod.GET, RequestMethod.POST })
	public String M_searchID(HttpServletRequest request, HttpSession session, Member member) {
		logger.info("아이디 찾기 페이지");

		String id = service.searchID(request);
		System.out.println("++++++++++++-------------------------------------------" + id);

		session.setAttribute("FindId", id);
		logger.info(session.getAttribute("FindId").toString());
		return "member/M_detailSearchID";
	}

	// 내 정보 보기
	@RequestMapping(value = "/M_info", method = RequestMethod.GET)
	public ModelAndView info(HttpSession session) throws Exception {
		logger.info("내 정보 페이지");
		String id = (String) session.getAttribute("useridd");
		ModelAndView mav = new ModelAndView();
		Member info = service.detail(id);
		System.out.println("fdsaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + info);
		mav.addObject("info", info);
		session.setAttribute("mem_profilephoto", info.getMem_photo());
		mav.setViewName("member/M_info");

		return mav;
	}

	// 개인정보수정
	@RequestMapping(value = "/M_update", method = { RequestMethod.GET, RequestMethod.POST })
	public String M_update(Member member, HttpSession session) {
		logger.info("정보수정 페이지");
		String nick = member.getMem_nickname();
		String phone = member.getMem_phone();
		String id = member.getMem_userid();
		logger.info(id);
		logger.info(phone);
		logger.info(nick);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", session.getAttribute("useridd"));
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
	@RequestMapping(value = "/M_logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String M_logout(HttpSession session) {
		logger.info("로그아웃 페이지");
		session.invalidate();
		return "redirect:../";
	}

	// 회원탈퇴
	@RequestMapping(value = "/M_delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String M_delete(HttpSession session) {
		logger.info("회원탈퇴 페이지");

		String id = (String) session.getAttribute("useridd");
		int result = service.delete(id);
		if (result == 0) {
			return "Mainpage";
		} else {
			session.invalidate();
			return "Mainpage";
		}
	}

	// 프로필 변경하장
	@RequestMapping(value = "/M_profileUpload", method = RequestMethod.GET)
	public String upload() {
		logger.info("프로필 변경");

		return "member/M_profileUpload";
	}

	// 프로필 업로드 입니당
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/upload")
	public String upload(HttpSession session, Model model, @RequestParam("file1") MultipartFile file) throws Exception {

		Map<Object, Object> postmap = new HashMap<>();
		postmap.put("user", session.getAttribute("useridd"));
		postmap.put("file", file);
		System.out.println(file);
		Map map = service.restore(postmap, session);
		session.removeAttribute("profilephoto");
		session.setAttribute("profilephoto", map.get("ProfileName"));
		model.addAllAttributes(map);
		String path = session.getServletContext().getRealPath("/");
		System.out.println("path::::::::::::::::::" + path);
		return "redirect:M_info";
	}

	// 이메일 인증
	@RequestMapping(value = "/M_make", method = RequestMethod.POST)
	public String RegisterPost(@ModelAttribute Member user, RedirectAttributes rttr) throws Exception {
		logger.info("회원가입...");
		logger.info(user.toString());
		service.create(user);
		rttr.addFlashAttribute("authmsg", "가입시 사용한 이메일로 인증해주 3");
		return "redirect:/";
	}

	// 이메일 인증~
	@RequestMapping(value = "/M_newJoin", method = RequestMethod.GET)
	public String emailConfirm(String user_email, Model model) throws Exception { // 이메일인증
		service.userAuth(user_email);
		model.addAttribute("user_email", user_email);

		return "member/M_newJoin";
	}

	// 비밀번호 찾기
	@RequestMapping(value = "/M_searchPWD", method = RequestMethod.POST)
	public String M_searchPWD(@ModelAttribute Member user, RedirectAttributes rttr) throws Exception {
		logger.info("비밀번호 찾기 페이지");
		logger.info(user.toString());
		service.serachPWD(user);
		rttr.addFlashAttribute("authmsg", "가입시 사용한 이메일로 인증해주 3");

		return "redirect:/";
	}

	// 비밀번호 찾기 상세페이지
	@RequestMapping(value = "/M_detailPWD", method = RequestMethod.GET)
	public String M_detailPWD(Model model) {
		logger.info("비밀번호 찾기 상세 페이지");

		return "member/M_searchID";
	}

}

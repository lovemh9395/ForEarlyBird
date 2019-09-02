package kr.co.forearlybird.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.forearlybird.MainController;
import kr.co.forearlybird.domain.Member;
import kr.co.forearlybird.paging.Criteria;
import kr.co.forearlybird.paging.PageMaker;
import kr.co.forearlybird.paging.replyCriteria;
import kr.co.forearlybird.paging.replyPageMaker;
import kr.co.forearlybird.service.ContentService;
import kr.co.forearlybird.service.MemberService;

@SuppressWarnings({ "unchecked", "rawtypes" })
@Controller
@RequestMapping(value = "member")
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Inject
	PasswordEncoder passwordEncoder;

	@Autowired
	MemberService service;

	@Autowired
	ContentService cntService;

	// 로그인으로 이동 (사용자,관리자 레벨로 이동)
	@RequestMapping(value = "/M_login", method = RequestMethod.GET)
	public String M_login(Model model) {
		logger.info("로그인 Controller");
		return "member/M_login";
	}

	// 로그인 처리하기
	@RequestMapping(value = "/M_login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
			throws Exception {
		logger.info("로그인 처리 Controller");
		Map map = service.login(request);
		if (map == null) { // map 가져오기 오류
			return "redirect:/";
		}
		if (map.get("level").toString().equals("1")) { // 차단회원인 경우
			return "Mainpage";
		} else if (map.get("level").toString().equals("4")) { // 탈퇴회원인 경우
			return "Mainpage";
		} else if (map.get("level").toString().equals("9")) { // 관리자인 경우
			// 세션 부여
			session.setAttribute("user", map);
			session.setAttribute("mem_level", map.get("level"));
			session.setAttribute("profilephoto", map.get("profilephoto"));
			session.setAttribute("useridd", map.get("id"));
			if (session.getAttribute("FindId") != null) {
				session.removeAttribute("FindId");
			}
			return "A_mainpage";
		} else {
			if (map.toString().equals("{}")) { // 가입정보가 없는 경우
			} else {
				// 세션 부여
				session.setAttribute("user", map);
				session.setAttribute("profilephoto", map.get("profilephoto"));
				session.setAttribute("useridd", map.get("id"));
				if (session.getAttribute("FindId") != null) {
					session.removeAttribute("FindId");
				}
			}
		}
		model.addAttribute("list", cntService.Main_C_list());

		return "Mainpage";
	}

	// 아이디 찾기
	@RequestMapping(value = "/M_searchID", method = { RequestMethod.GET, RequestMethod.POST })
	public String M_searchID(HttpServletRequest request, HttpSession session, Member member) {
		logger.info("아이디 찾기 Controller");
		String id = service.searchID(request);
		session.setAttribute("FindId", id);

		return "member/M_detailSearchID";
	}

	// 내 정보 보기
	@RequestMapping(value = "/M_info", method = RequestMethod.GET)
	public ModelAndView info(HttpSession session) throws Exception {
		logger.info("내 정보 Controller");
		String id = (String) session.getAttribute("useridd");
		ModelAndView mav = new ModelAndView();
		Member info = service.detail(id);
		mav.addObject("info", info);
		session.setAttribute("mem_profilephoto", info.getMem_photo());
		mav.setViewName("member/M_info");

		return mav;
	}

	// 개인정보수정
	@RequestMapping(value = "/M_update", method = { RequestMethod.GET, RequestMethod.POST })
	public String M_update(HttpServletRequest request, Member member, HttpSession session) {
		logger.info("정보수정 Controller");
		String mem_nickname = request.getParameter("update_mem_nickname");
		String mem_password = request.getParameter("update_mem_password");
		String encPassword = passwordEncoder.encode(mem_password);
		String mem_phone = request.getParameter("update_mem_phone");
		int mem_gender = 0;
		if (request.getParameter("update_mem_gender").equals("남자")) {
			mem_gender = 0;
		} else if (request.getParameter("update_mem_gender").equals("여자")) {
			mem_gender = 1;
		}
		String mem_zipcode = request.getParameter("update_mem_zipcode");
		String mem_address1 = request.getParameter("update_mem_address1");
		String mem_address2 = request.getParameter("update_mem_address2");
		String mem_profile_content = request.getParameter("update_mem_profile_content");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sessionid", session.getAttribute("useridd"));
		map.put("mem_nickname", mem_nickname);
		map.put("mem_password", encPassword);
		map.put("mem_phone", mem_phone);
		map.put("mem_gender", mem_gender);
		map.put("mem_zipcode", mem_zipcode);
		map.put("mem_address1", mem_address1);
		map.put("mem_address2", mem_address2);
		map.put("mem_profile_content", mem_profile_content);

		service.update(map);

		return "redirect:../";
	}

	@RequestMapping(value = "/M_address", method = RequestMethod.GET)
	public String M_address() {

		return "member/M_address";
	}

	// 내 글 보기
	@RequestMapping(value = "/M_list", method = RequestMethod.GET)
	public String M_list(HttpServletRequest request, @ModelAttribute("cri") Criteria cri, Model model, Map map,
			HttpSession session) throws Exception {
		logger.info("내 글 보기 Controller");

		replyCriteria replycri = new replyCriteria();
		replycri.setreplyPage(Integer.parseInt(request.getParameter("replypage")));
		replycri.setReplyperPageNum(Integer.parseInt(request.getParameter("replyperPageNum")));

		map.put("replycri", replycri);
		map.put("cri", cri);
		map.put("sessionId", session.getAttribute("useridd"));
		model.addAttribute("list", service.listCriteria(map)); // 게시판의 글 리스트

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listCountCriteria(map));
		model.addAttribute("pageMaker", pageMaker); // 게시판 하단의 페이징 관련, 이전페이지, 페이지 링크 , 다음 페이지

		model.addAttribute("R_list", service.myreplylistCriteria(map));
		replyPageMaker replypageMaker = new replyPageMaker();
		replypageMaker.setReplycri(replycri);
		replypageMaker.setreplyTotalCount(service.myreplylistCountCriteria(map));
		model.addAttribute("replypageMaker", replypageMaker); // 게시판 하단의 페이징 관련, 이전페이지, 페이지 링크 , 다음 페이지

		return "member/M_list";
	}

	// 로그아웃
	@RequestMapping(value = "/M_logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String M_logout(HttpSession session) {
		logger.info("로그아웃 Controller");
		session.invalidate();

		return "redirect:../";
	}

	// 회원탈퇴
	@RequestMapping(value = "/M_delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String M_delete(HttpSession session) {
		logger.info("회원탈퇴 Controller");
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
	@RequestMapping(value = "/upload")
	public String upload(HttpSession session, Model model, @RequestParam("file1") MultipartFile file,
			HttpServletRequest request) throws Exception {
		logger.info("프로필 업로드 Controller");
		Map<Object, Object> postmap = new HashMap<>();
		if (file != null && checkImageType(file)) {
			postmap.put("user", session.getAttribute("useridd"));
			postmap.put("file", file);
			Map map = service.restore(postmap, session);
			session.removeAttribute("profilephoto");
			session.setAttribute("profilephoto", map.get("ProfileName"));
			model.addAllAttributes(map);
			String path = session.getServletContext().getRealPath("/");
			System.out.println("path::::::::::::::::::" + path);
		}
		return "redirect:M_info";
	}

	// 멀티파일 업로드
	public File multipartToFile(MultipartFile file) throws IllegalStateException, IOException {
		logger.info("멀티파일 업로드 Controller");
		File convFile = new File(file.getOriginalFilename());
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

	// 파일의 이미지 타입인지 아닌지 확인하기 위한 메소드
	private boolean checkImageType(MultipartFile file) {
		logger.info("멀티파일 이미지 타입 확인 메소드");
		try {
			String contentType = Files.probeContentType(multipartToFile(file).toPath());
			if (contentType != null) {
				return contentType.startsWith("image");
			} else {
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 이메일 인증1
	@RequestMapping(value = "/M_make", method = RequestMethod.POST)
	public String RegisterPost(@ModelAttribute Member user, RedirectAttributes rttr) throws Exception {
		logger.info("회원가입 Controller");
		service.create(user);
		rttr.addFlashAttribute("authmsg", "가입시 사용한 이메일로 인증해주세요");

		return "redirect:/";
	}

	// 이메일 인증2
	@RequestMapping(value = "/M_newJoin", method = RequestMethod.GET)
	public String emailConfirm(String user_email, Model model) throws Exception {
		logger.info("회원가입 이메일 인증 Controller");
		service.userAuth(user_email);
		model.addAttribute("user_email", user_email);

		return "member/M_newJoin";
	}

	// 비밀번호 찾기
	@RequestMapping(value = "/M_searchPWD", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public int M_searchPWD(@ModelAttribute Member user, HttpServletRequest request, Map map, RedirectAttributes rttr)
			throws Exception {
		logger.info("비밀번호 찾기 Controller");
		String searchMem_userid = request.getParameter("mem_userid");
		String searchMem_username = request.getParameter("mem_username");
		String searchMem_birthday = request.getParameter("mem_birthday");
		String searchMem_phone = request.getParameter("mem_phone");
		map.put("mem_userid", searchMem_userid);
		map.put("mem_username", searchMem_username);
		map.put("mem_birthday", searchMem_birthday);
		map.put("mem_phone", searchMem_phone);
		if (service.searchPWDcheck(map) != 0) {
			service.serachPWD(user);
			rttr.addFlashAttribute("authmsg", "가입시 사용한 이메일로 인증해주세요");
			return 1;
		}
		return 0;
	}

	// 비밀번호 찾기 상세페이지
	@RequestMapping(value = "/M_detailPWD", method = RequestMethod.GET)
	public String M_detailPWD(Model model) {
		logger.info("비밀번호 찾기 Controller");

		return "member/M_searchID";
	}

	// 아이디 중복체크
	@ResponseBody
	@RequestMapping(value = "/M_CheckId", method = { RequestMethod.GET, RequestMethod.POST })
	public int M_CheckId(HttpServletRequest request, Model model) throws Exception {
		logger.info("ID 중복체크 Controller");
		String formId = request.getParameter("formId");
		int result = service.CheckId(formId);
		if (result == 0) {
			return 1;
		}
		return 0;
	}

	@ResponseBody
	@RequestMapping(value = "/login_Check", method = { RequestMethod.GET, RequestMethod.POST })
	public int login_Check(HttpServletRequest request, Map map) throws Exception {
		logger.info("로그인 아이디,비밀빈호 체크 Controller");
		String formId = request.getParameter("login_id");
		String login_pass = request.getParameter("login_pass");
		map.put("id", formId);
		map.put("login_pass", login_pass);
		int result = service.CheckId(formId);
		if (result == 0) {
			return 1;
		} else if (result == 1) {
			return service.CheckPass(map);
		}
		return 0;
	}
}

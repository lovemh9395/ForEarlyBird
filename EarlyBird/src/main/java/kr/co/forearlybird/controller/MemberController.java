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
		logger.info("로그인 페이지");
		return "member/M_login";
	}

	// 로그인 처리하기
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/M_login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response, HttpSession session,Model model)
			throws Exception {
		logger.info("login 처리");
		logger.info(request.toString());
		logger.info(request.getParameter("login_mem_userid"));
		logger.info(request.getParameter("login_mem_password"));
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
			session.setAttribute("mem_level", map.get("level"));
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
		
		model.addAttribute("list", cntService.Main_C_list());
		
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
		logger.info(info.toString());
		session.setAttribute("mem_profilephoto", info.getMem_photo());
		mav.setViewName("member/M_info");

		return mav;
	}

	// 개인정보수정
	@RequestMapping(value = "/M_update", method = { RequestMethod.GET, RequestMethod.POST })
	public String M_update(Member member, HttpSession session) {
		logger.info("정보수정 페이지");
		String mem_nickname = member.getMem_nickname();
		String mem_password = member.getMem_password();
		String encPassword = passwordEncoder.encode(mem_password);
		String mem_phone = member.getMem_phone();
		System.out.println(member.getMem_gender());
		int mem_gender = member.getMem_gender();
		String mem_zipcode = member.getMem_zipcode();
		String mem_address1 = member.getMem_address1();
		String mem_address2 = member.getMem_address2();
		String mem_profile_content = member.getMem_profile_content();

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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/M_list", method = RequestMethod.GET)
	public String M_list(HttpServletRequest request, @ModelAttribute("cri") Criteria cri, Model model, Map map,
			HttpSession session) throws Exception {
		logger.info("내 글 보기 페이지");
		logger.info(cri.toString());

		replyCriteria replycri = new replyCriteria();
		replycri.setreplyPage(Integer.parseInt(request.getParameter("replypage")));
		replycri.setReplyperPageNum(Integer.parseInt(request.getParameter("replyperPageNum")));

		map.put("replycri", replycri);
		map.put("cri", cri);
		map.put("sessionId", session.getAttribute("useridd"));
		logger.info("國國國國國國國國國國國國國國國國國國國國國國國國國國國國國國國國國國國國國國國國國" + map);
		logger.info(replycri.toString());
		model.addAttribute("list", service.listCriteria(map)); // 게시판의 글 리스트
		logger.info("撥撥撥撥撥撥撥撥撥撥撥撥撥撥撥撥撥撥撥撥撥撥撥撥撥撥撥撥撥撥" + service.listCriteria(map).toString());
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listCountCriteria(map));
		model.addAttribute("pageMaker", pageMaker); // 게시판 하단의 페이징 관련, 이전페이지, 페이지 링크 , 다음 페이지
		logger.info("*******************************************************" + pageMaker);

		model.addAttribute("R_list", service.myreplylistCriteria(map));
		replyPageMaker replypageMaker = new replyPageMaker();
		replypageMaker.setReplycri(replycri);
		replypageMaker.setreplyTotalCount(service.myreplylistCountCriteria(map));
		model.addAttribute("replypageMaker", replypageMaker); // 게시판 하단의 페이징 관련, 이전페이지, 페이지 링크 , 다음 페이지
		logger.info("**********************************************************" + replypageMaker);
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
	public String upload(HttpSession session, Model model, @RequestParam("file1") MultipartFile file,
			HttpServletRequest request) throws Exception {
		Map<Object, Object> postmap = new HashMap<>();
		logger.info("1");
		if (file != null && checkImageType(file)) {
			logger.info("2");
			postmap.put("user", session.getAttribute("useridd"));
			postmap.put("file", file);
			System.out.println("國國國國國國國國國國國國國國國國國國" + file);
			Map map = service.restore(postmap, session);
			session.removeAttribute("profilephoto");
			session.setAttribute("profilephoto", map.get("ProfileName"));
			model.addAllAttributes(map);
			String path = session.getServletContext().getRealPath("/");
			System.out.println("path::::::::::::::::::" + path);
		}
		return "redirect:M_info";
	}

	public File multipartToFile(MultipartFile file) throws IllegalStateException, IOException {
		File convFile = new File(file.getOriginalFilename());
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

	// 파일의 이미지 타입인지 아닌지 확인하기 위한 메소드
	private boolean checkImageType(MultipartFile file) {
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
	
	//아이디 중복체크
	@ResponseBody
	@RequestMapping(value="/M_CheckId", method= {RequestMethod.GET,RequestMethod.POST})
	public int M_CheckId(HttpServletRequest request,Model model) throws Exception {
		String formId = request.getParameter("formId");
		logger.info(formId);
		int result = service.CheckId(formId) ;
		if(result == 0) {
			return 1;
		}
		return 0;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ResponseBody
	@RequestMapping(value="/login_Check", method = {RequestMethod.GET,RequestMethod.POST})
	public int login_Check(HttpServletRequest request,Map map) throws Exception {
		String formId = request.getParameter("login_id");
		String login_pass = request.getParameter("login_pass");
		map.put("id", formId);
		map.put("login_pass", login_pass);
		int result = service.CheckId(formId);
		if(result == 0) {
			return 1;
		} else if (result == 1) {
			result = service.CheckPass(map);
			if(result == 2) {
				return 2;
			} else {
				return 3;
			}
		}
		
		return 0;
	}
}

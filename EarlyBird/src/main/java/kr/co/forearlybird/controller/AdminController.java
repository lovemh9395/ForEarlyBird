package kr.co.forearlybird.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.forearlybird.domain.Post;
import kr.co.forearlybird.service.AdminService;

@SuppressWarnings({ "unchecked", "rawtypes" })
@Controller
@RequestMapping(value = "admin")
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Inject
	PasswordEncoder passwordEncoder;

	@Autowired
	AdminService service;

	// Admin Main page 이동
	@RequestMapping(value = "/A_mainpage", method = RequestMethod.GET)
	public String Adminhome(Model model, HttpSession session) {
		logger.info("AdminPage");
		return "A_mainpage";
	}

	// 관리자 목록 보기
	@RequestMapping(value = "/A_adminList", method = RequestMethod.GET)
	public String A_adminList(HttpSession session, Model model) {
		logger.info("관리자 보기 페이지");
		model.addAttribute("memberList", service.getAdminList());
		return "admin/A_adminList";
	}

	@RequestMapping(value = "/A_AdminCandidateSearch", method = RequestMethod.POST)
	public String A_AdminCandidateSearch(HttpServletRequest request, HttpSession session, Model model) {
		logger.info("회원 검색 페이지");
		Map map = new HashMap();
		map.put("keytype", request.getParameter("keytype"));
		map.put("keyword", request.getParameter("keyword"));
		model.addAttribute("memberList", service.searchAdminCandidateList(map));
		return "admin/A_adminList";
	}

	@RequestMapping(value = "/A_adminUpdate", method = RequestMethod.POST)
	public String A_adminUpdate(String[] checkedList, int[] AuthList, HttpSession session, Model model) {
		logger.info("회원 등급변경 페이지");
		List checklist = new ArrayList<>();
		List memberAuthList = new ArrayList<>();
		for (int i = 0; i < checkedList.length; i++) {
			checklist.add(checkedList[i]);
			memberAuthList.add(AuthList[i]);
		}
		service.memberAuthUpdate(checklist, memberAuthList);
		model.addAttribute("memberList", service.getAdminList());
		return "admin/A_adminList";
	}

	// 게시판 별 관리자 보기
	@RequestMapping(value = "/A_boardAdminList", method = { RequestMethod.GET, RequestMethod.POST })
	public String A_adminList(@RequestParam("brd_id") int brd_id, HttpSession session, Model model) {
		logger.info("게시판 별 관리자 보기 페이지");
		List<String> adminNicknames = service.getAdminNickname(brd_id);
		List<Map> adminList = service.getMemberListForBoardAdmin();
		model.addAttribute("brd_id", brd_id);
		model.addAttribute("adminNicknames", adminNicknames);
		model.addAttribute("adminList", adminList);
		return "admin/A_boardAdminList";
	}

	@RequestMapping(value = "/A_checkAdminId", method = RequestMethod.POST)
	@ResponseBody
	public String A_checkAdminId(@RequestParam("brd_id") int brd_id, @RequestParam("mem_userid") String mem_userid,
			HttpSession session, Model model) {
		logger.info("게시판 관리자 선정 여부 체크");
		int tmp = service.checkAdminId(brd_id, mem_userid);
		String result = tmp + "";
		return result;
	}

	// 게시판 별 관리자 설정
	@RequestMapping(value = "/A_boardAdminUpdate", method = { RequestMethod.GET, RequestMethod.POST })
	public String A_boardAdminUpdate(HttpServletRequest request, HttpSession session, Model model) {
		logger.info("게시판 관리자 변경 페이지");
		int brd_id = Integer.parseInt(request.getParameter("brd_id"));
		String mem_userid = request.getParameter("mem_userid");

		Map map = new HashMap();
		map.put("brd_id", brd_id);
		map.put("mem_userid", mem_userid);

		service.updateAdmin(map);

		List<String> adminNicknames = service.getAdminNickname(brd_id);
		List<Map> adminList = service.getMemberListForBoardAdmin();

		model.addAttribute("brd_id", brd_id);
		model.addAttribute("adminNicknames", adminNicknames);
		model.addAttribute("adminList", adminList);

		return "admin/A_boardAdminList";
	}
	
	@RequestMapping(value = "/A_boardAdminDelete", method = { RequestMethod.GET, RequestMethod.POST })
	public String A_boardAdminDelete(HttpServletRequest request, HttpSession session, Model model) {
		logger.info("게시판 관리자 변경 페이지");
		int brd_id = Integer.parseInt(request.getParameter("brd_id"));
		String mem_userid = request.getParameter("mem_userid");
		
		Map map = new HashMap();
		map.put("brd_id", brd_id);
		map.put("mem_userid", mem_userid);
		
		service.deleteAdmin(map);
		
		List<String> adminNicknames = service.getAdminNickname(brd_id);
		List<Map> adminList = service.getMemberListForBoardAdmin();
		
		model.addAttribute("brd_id", brd_id);
		model.addAttribute("adminNicknames", adminNicknames);
		model.addAttribute("adminList", adminList);
		
		return "admin/A_boardAdminList";
	}

	// 게시판 별 관리자 설정을 위한 후보자 목록 검색
	@RequestMapping(value = "/A_searchMemberForAdmin", method = { RequestMethod.GET, RequestMethod.POST })
	public String A_searchMemberForAdmin(HttpServletRequest request, HttpSession session, Model model) {
		logger.info("게시판 관리자 변경 페이지");
		int brd_id = Integer.parseInt(request.getParameter("brd_id"));
		String searchFilter = request.getParameter("searchFilter");
		String searchkeyword = request.getParameter("searchkeyword");

		Map map = new HashMap();
		map.put("brd_id", brd_id);
		map.put("searchFilter", searchFilter);
		map.put("searchkeyword", searchkeyword);

		List<Map> adminList = service.searchMembersForBoardAdmin(map);

		model.addAttribute("brd_id", brd_id);
		model.addAttribute("adminNicknames", service.getAdminNickname(brd_id));
		model.addAttribute("adminList", adminList);
		return "admin/A_adminList";
	}

	// 회원 검색
	@RequestMapping(value = "/A_memberList", method = RequestMethod.GET)
	public String A_memberList(HttpServletRequest request, HttpSession session, Model model) {
		logger.info("회원 관리 페이지");
		if (request.getParameter("mem_level") != null) {
			model.addAttribute("memberList", service.getBanMemberList());
		} else {
			model.addAttribute("memberList", service.getMemberList());
		}
		return "admin/A_memberList";
	}

	// 회원 검색
	@RequestMapping(value = "/A_memberSearch", method = RequestMethod.POST)
	public String A_memberSearch(HttpServletRequest request, HttpSession session, Model model) {
		logger.info("회원 검색 페이지");
		Map map = new HashMap();
		map.put("keytype", request.getParameter("keytype"));
		map.put("keyword", request.getParameter("keyword"));
		model.addAttribute("memberList", service.searchMemberList(map));
		return "admin/A_memberList";
	}

	@RequestMapping(value = "/A_memberDelete", method = RequestMethod.POST)
	public String A_memberDelete(String[] checkedList, HttpServletRequest request, HttpSession session, Model model) {
		logger.info("회원정보 수정 페이지");
		Map map = new HashMap();
		List<String> checklist = new ArrayList<>();
		for (int i = 0; i < checkedList.length; i++) {
			checklist.add(checkedList[i]);
		}
		map.put("checklist", checklist);
		service.deleteMembers(map);
		model.addAttribute("memberList", service.getMemberList());
		return "admin/A_memberList";
	}

	@RequestMapping(value = "/A_memberBan", method = RequestMethod.POST)
	public String A_memberBan(String[] checkedList, HttpServletRequest request, HttpSession session, Model model) {
		logger.info("회원정보 수정 페이지");
		Map map = new HashMap();
		List<String> checklist = new ArrayList<>();
		for (int i = 0; i < checkedList.length; i++) {
			checklist.add(checkedList[i]);
		}
		map.put("checklist", checklist);
		service.banMembers(map);
		model.addAttribute("memberList", service.getMemberList());
		return "admin/A_memberList";
	}

	@RequestMapping(value = "/A_memberRelease", method = RequestMethod.POST)
	public String A_memberRelease(String[] checkedList, HttpServletRequest request, HttpSession session, Model model)
			throws Exception {
		logger.info("회원정보 수정 페이지");
		Map map = new HashMap();
		List<String> checklist = new ArrayList<>();
		for (int i = 0; i < checkedList.length; i++) {
			checklist.add(checkedList[i]);
		}
		map.put("checklist", checklist);
		service.releaseMembers(map);
		model.addAttribute("memberList", service.getMemberList());
		return "admin/A_memberList";
	}

	// 회원 글 보기
	@RequestMapping(value = "/A_memberDetail", method = RequestMethod.GET)
	public String A_memberDetail(HttpServletRequest request, HttpSession session, Model model) throws Exception {
		logger.info("회원 작성글 열람 페이지");
		String mem_userid = request.getParameter("mem_userid");
		model.addAttribute("mem_userid", mem_userid);
		model.addAttribute("mem_nickname", service.getNickname(mem_userid));
		if (service.getPostNumWritenBy(mem_userid) > 0) {
			model.addAttribute("postList", service.getPostListFromWriter(mem_userid));
		}
		if (service.getReplyNumWritenBy(mem_userid) > 0) {
			model.addAttribute("replyList", service.getRplListFromWriter(mem_userid));
		}
		return "admin/A_memberDetail";
	}
	
	@RequestMapping(value = "/A_memberPostListUpdate", method = RequestMethod.POST)
	public String A_memberPostListUpdate(String[] checkedList, String[] isDelList, HttpServletRequest request, HttpSession session, Model model) throws Exception {
		logger.info("회원 작성글 열람 페이지");
		Map map = new HashMap();
		List<String> checklist = new ArrayList<>();
		List<String> dellist = new ArrayList<>();
		for (int i = 0; i < checkedList.length; i++) {
			checklist.add(checkedList[i]);
			dellist.add(isDelList[i]);
		}
		map.put("checklist", checklist);
		map.put("dellist", dellist);
		service.updatePost(map);
		
		String mem_userid = request.getParameter("mem_userid");
		if (service.getPostNumWritenBy(mem_userid) > 0) {
			model.addAttribute("postList", service.getPostListFromWriter(mem_userid));
		}
		if (service.getReplyNumWritenBy(mem_userid) > 0) {
			model.addAttribute("replyList", service.getRplListFromWriter(mem_userid));
		}
		model.addAttribute("mem_userid", mem_userid);
		model.addAttribute("mem_nickname", service.getNickname(mem_userid));
		return "admin/A_memberDetail";
	}
	
	@RequestMapping(value = "/A_memberReplyListUpdate", method = RequestMethod.POST)
	public String A_memberReplyListUpdate(String[] checkedList, String[] isDelList, HttpServletRequest request, HttpSession session, Model model) throws Exception {
		logger.info("회원 작성글 열람 페이지");
		Map map = new HashMap();
		List<String> checklist = new ArrayList<>();
		List<String> dellist = new ArrayList<>();
		for (int i = 0; i < checkedList.length; i++) {
			checklist.add(checkedList[i]);
			dellist.add(isDelList[i]);
		}
		map.put("checklist", checklist);
		map.put("dellist", dellist);
		service.updateReply(map);
		
		String mem_userid = request.getParameter("mem_userid");
		if (service.getPostNumWritenBy(mem_userid) > 0) {
			model.addAttribute("postList", service.getPostListFromWriter(mem_userid));
		}
		if (service.getReplyNumWritenBy(mem_userid) > 0) {
			model.addAttribute("replyList", service.getRplListFromWriter(mem_userid));
		}
		model.addAttribute("mem_userid", mem_userid);
		model.addAttribute("mem_nickname", service.getNickname(mem_userid));
		return "admin/A_memberDetail";
	}

	// 회원 글 보기
	@RequestMapping(value = "/A_memberPostList", method = RequestMethod.POST)
	public String A_memberPostList(HttpServletRequest request, HttpSession session, Model model) throws Exception {
		logger.info("회원 글 보기 페이지");
		String mem_userid = request.getParameter("mem_userid");
		String post_del = request.getParameter("post_del");
		if (!post_del.equals("none")) {
			Map map = new HashMap();
			map.put("mem_userid", mem_userid);
			map.put("post_del", post_del);
			if (service.getPostNumWritenBy(map) > 0) {
				model.addAttribute("postList", service.getPostListFromWriter(map));
			}
		} else {
			if (service.getPostNumWritenBy(mem_userid) > 0) {
				model.addAttribute("postList", service.getPostListFromWriter(mem_userid));
			}
		}
		return "admin/A_memberDetail";
	}

	// 회원 글 이관
	@RequestMapping(value = "/A_memberPostUpdate", method = RequestMethod.GET)
	public String A_memberPostUpdate(HttpSession session, Model model) {
		logger.info("회원 글 이관 페이지");
		return "admin/A_memberPostUpdate";
	}

	// 회원 댓글 보기
	@RequestMapping(value = "/A_memberReplyList", method = RequestMethod.POST)
	public String A_memberReplyList(HttpServletRequest request, HttpSession session, Model model) throws Exception {
		logger.info("회원 댓글 보기 페이지");
		String mem_userid = request.getParameter("mem_userid");
		String rpl_del = request.getParameter("rpl_del");
		if (!rpl_del.equals("none")) {
			Map map = new HashMap();
			map.put("mem_userid", mem_userid);
			map.put("rpl_del", rpl_del);
			if (service.getReplyNumWritenBy(map) > 0) {
				model.addAttribute("replyList", service.getRplListFromWriter(map));
			}
		} else {
			if (service.getReplyNumWritenBy(mem_userid) > 0) {
				model.addAttribute("replyList", service.getRplListFromWriter(mem_userid));
			}
		}
		return "admin/A_memberDetail";
	}

	// 회원 댓글 이관
	@RequestMapping(value = "/A_memberReplyUpdate", method = RequestMethod.GET)
	public String A_memberReplyUpdate(HttpSession session, Model model) {
		logger.info("회원 댓글 이관 페이지");
		return "admin/A_memberReplyUpdate";
	}

	// 공지사항 보기
	@RequestMapping(value = "/A_noticeList", method = RequestMethod.GET)
	public String A_noticeList(HttpSession session, Model model) throws ParseException {
		logger.info("공지사항 보기 페이지");
		model.addAttribute("noticeList", service.getNoticeListFromBoard());
		return "admin/A_noticeList";
	}

	@RequestMapping(value = "/A_noticeDetail", method = RequestMethod.GET)
	public String A_noticeDetail(HttpServletRequest request, HttpSession session, Model model) throws ParseException {
		logger.info("공지 세부 페이지");
		model.addAttribute("post", service.getPost(Integer.parseInt(request.getParameter("post_id"))));
		return "admin/A_noticeDetail";
	}

	// 공지사항 등록 페이지
	@RequestMapping(value = "/A_noticeMake", method = RequestMethod.GET)
	public String A_noticeMake(HttpSession session, Model model) {
		logger.info("공지사항 등록 페이지");
		return "admin/A_noticeMake";
	}

	// 공지사항 등록 페이지
	@ResponseBody
	@RequestMapping(value = "/A_noticeMake", method = RequestMethod.POST)
	public void A_Mnotice(HttpServletRequest request, HttpSession session, Model model) {
		logger.info("공지사항 등록");
		Post post = makePost(request, session);
		service.makeNotice(post);
	}

	private Post makePost(HttpServletRequest request, HttpSession session) {
		Post post = new Post();
		post.setBrd_id(1);
		post.setPost_id(Integer.parseInt(request.getParameter("post_id")));
		post.setMem_userid((String) session.getAttribute("useridd"));
		post.setPost_title(request.getParameter("notice_title"));
		post.setPost_content(request.getParameter("notice_content"));
		post.setPost_del(0);
		post.setPost_notice(1);
		return post;
	}

	// 공지사항 수정
	@RequestMapping(value = "/A_noticeUpdate", method = RequestMethod.GET)
	public String A_noticeUpdateGET(HttpServletRequest request, HttpSession session, Model model) {
		logger.info("공지사항 수정 페이지");
		int post_id = Integer.parseInt(request.getParameter("post_id"));
		model.addAttribute("post", service.getPost(post_id));
		return "admin/A_noticeUpdate";
	}

	// 공지사항 수정
	@ResponseBody
	@RequestMapping(value = "/A_noticeUpdate", method = RequestMethod.POST)
	public void A_noticeUpdate(HttpServletRequest request, HttpSession session, Model model) {
		logger.info("공지사항 수정 페이지");
		Post post = makePost(request, session);
		service.updateNotice(post);
	}

	// 공지사항 삭제
	@RequestMapping(value = "/A_noticeDelete", method = RequestMethod.POST)
	public String A_noticeDelete(HttpServletRequest request, HttpSession session, Model model, String[] checkedList,
			String[] NoticeOrNot) throws Exception {
		logger.info("공지사항 삭제 페이지");
		Map map = new HashMap();
		List<String> checklist = new ArrayList<>();
		for (int i = 0; i < checkedList.length; i++) {
			checklist.add(checkedList[i]);
		}

		List<String> noticeOrNot = new ArrayList<>();
		for (int i = 0; i < NoticeOrNot.length; i++) {
			noticeOrNot.add(NoticeOrNot[i]);
		}
		map.put("brd_id", request.getParameter("brd_id"));
		map.put("checklist", checklist);
		map.put("NoticeOrNot", noticeOrNot);

		service.updatePostToBoard(map);

		model.addAttribute("noticeList", service.getNoticeListFromBoard());
		return "admin/A_noticeList";
	}

	// 공지사항 삭제2
	@RequestMapping(value = "/A_noticeDrop", method = RequestMethod.POST)
	public String A_noticeDrop(HttpServletRequest request, HttpSession session, Model model, String[] checkedList,
			String[] NoticeOrNot) throws Exception {
		logger.info("공지사항 삭제 페이지");
		Map map = new HashMap();
		List<String> checklist = new ArrayList<>();
		for (int i = 0; i < checkedList.length; i++) {
			checklist.add(checkedList[i]);
		}

		map.put("brd_id", request.getParameter("brd_id"));
		map.put("checklist", checklist);

		service.updatePostToBoard(map);

		model.addAttribute("noticeList", service.getNoticeListFromBoard());
		return "admin/A_noticeList";
	}

	// 카테고리 보기
	@RequestMapping(value = "/A_categoryList", method = { RequestMethod.GET, RequestMethod.POST })
	public String A_categoryList(HttpSession session, Model model) throws Exception {
		logger.info("카테고리 보기 페이지");
		List<Map> largeList = service.largeCategoryList();
		List<Map> smallList = service.CategoryList();
		model.addAttribute("isNull", true);
		if (!largeList.isEmpty()) {
			model.addAttribute("largeList", largeList);
		}
		if (!smallList.isEmpty()) {
			model.addAttribute("smallList", smallList);
		}
		if (!largeList.isEmpty() && !smallList.isEmpty()) {
			model.addAttribute("isNull", false);
		}
		return "admin/A_categoryList";
	}

	// 대분류 카테고리 생성
	@ResponseBody
	@RequestMapping(value = "/A_largeCategoryMake", method = { RequestMethod.GET, RequestMethod.POST })
	public void A_largeCategoryMake(HttpServletRequest request, Model model) throws Exception {
		logger.info("대분류 카테고리 생성");
		request.setCharacterEncoding("UTF-8");
		String large_name = request.getParameter("large_name");

		int result = service.makeLargeCategory(large_name);
		model.addAttribute("result", result);
		logger.info("대분류 카테고리 생성 결과 : " + result);
	}

	// 카테고리 생성
	@ResponseBody
	@RequestMapping(value = "/A_categoryMake", method = { RequestMethod.GET, RequestMethod.POST })
	public void A_categoryMake(HttpServletRequest request, Model model) {
		logger.info("카테고리 생성");
		int large_id = Integer.parseInt(request.getParameter("large_id"));
		String category_name = request.getParameter("category_name");

		Map<String, Object> map = new HashMap<>();
		map.put("large_id", large_id);
		map.put("category_name", category_name);

		int result = service.makeCategory(large_id, category_name);
		model.addAttribute("result", result);
	}

	// 카테고리 삭제
	@ResponseBody
	@RequestMapping(value = "/A_largeCategoryDelete", method = { RequestMethod.GET, RequestMethod.POST })
	public void A_LargeCategoryDelete(@RequestParam("large_id") int large_id, Model model) {
		logger.info("카테고리 삭제 페이지");
		int result = service.leaveLargeCategory(large_id);
		model.addAttribute("result", result);
	}

	// 카테고리 삭제
	@ResponseBody
	@RequestMapping(value = "/A_categoryDelete", method = { RequestMethod.GET, RequestMethod.POST })
	public void A_categoryDelete(@RequestParam("category_id") int category_id, Model model) {
		logger.info("카테고리 삭제 페이지");
		logger.info(category_id + "");
		int result = service.leaveCategory(category_id);
		model.addAttribute("result", result);
	}

	// 게시판 글 보기
	@RequestMapping(value = "/A_postList", method = RequestMethod.GET)
	public String A_postList(@RequestParam("brd_id") int brd_id, HttpSession session, Model model) throws Exception {
		logger.info("게시판 글 보기 페이지");
		Map map = new HashMap();
		map.put("brd_id", brd_id);
		List<Map> boardList = service.getBoardList();
		List<Map> largeList = service.largeCategoryList();
		List<Map> categoryList = service.CategoryList();

		String forlargeList = getStringFromLargeList(largeList);
		String forcategoryList = getStringFromCategoryList(categoryList);
		String forbrdList = getStringFromBoardList(boardList);

		model.addAttribute("forlargeList", forlargeList);
		model.addAttribute("forcategoryList", forcategoryList);
		model.addAttribute("forbrdList", forbrdList);
		model.addAttribute("postList", service.ListPostToBoard(map));
		return "admin/A_postSearch";
	}

	// 게시판 글 검색
	@RequestMapping(value = "/A_postSearch", method = RequestMethod.POST)
	public String A_postSearch(HttpServletRequest request, HttpSession session, Model model) throws Exception {
		logger.info("게시판 글 검색 페이지");
		Map map = new HashMap();
		List<Map> boardList = service.getBoardList();
		List<Map> largeList = service.largeCategoryList();
		List<Map> categoryList = service.CategoryList();

		System.out.println("dateFrom :" + request.getParameter("dateFrom"));
		System.out.println("dateTo :" + request.getParameter("dateTo"));

		String forlargeList = getStringFromLargeList(largeList);
		String forcategoryList = getStringFromCategoryList(categoryList);
		String forbrdList = getStringFromBoardList(boardList);

		map.put("large_id", Integer.parseInt(request.getParameter("large_id")));
		map.put("category_id", Integer.parseInt(request.getParameter("category_id")));
		map.put("brd_id", Integer.parseInt(request.getParameter("brd_id")));
		map.put("keywordType", (String) request.getParameter("keywordType"));
		map.put("keyword", (String) request.getParameter("keyword"));
		map.put("dateFrom", StringToDate(request.getParameter("dateFrom")));
		map.put("dateTo", StringToDate(request.getParameter("dateTo")));
		map.put("post_notice", Integer.parseInt(request.getParameter("post_notice")));
		map.put("post_del", Integer.parseInt(request.getParameter("post_del")));

		/*
		 * "dateFrom" : dateFrom, "dateTo" : dateTo, "large_id" : large_id,
		 * "category_id" : category_id, "brd_id" : brd_id, "post_notice" : post_notice,
		 * "keywordType" : keywordType, "keyword" : keyword
		 */

		model.addAttribute("forlargeList", forlargeList);
		model.addAttribute("forcategoryList", forcategoryList);
		model.addAttribute("forbrdList", forbrdList);
		model.addAttribute("postList", service.searchPostToBoard(map));
		return "admin/A_postSearch";
	}

	// 게시판 글 수정
	@RequestMapping(value = "/A_postUpdate", method = RequestMethod.POST)
	public String A_postUpdate(HttpServletRequest request, HttpSession session, Model model, String[] checkedList,
			String[] NoticeOrNot) throws Exception {
		logger.info("게시판 글 수정 페이지");
		Map map = new HashMap();
		List<String> checklist = new ArrayList<>();
		for (int i = 0; i < checkedList.length; i++) {
			checklist.add(checkedList[i]);
		}

		List<String> noticeOrNot = new ArrayList<>();
		for (int i = 0; i < NoticeOrNot.length; i++) {
			noticeOrNot.add(NoticeOrNot[i]);
		}
		map.put("brd_id", request.getParameter("brd_id"));
		map.put("checklist", checklist);
		map.put("NoticeOrNot", noticeOrNot);

		service.updatePostToBoard(map);

		List<Map> boardList = service.getBoardList();
		List<Map> largeList = service.largeCategoryList();
		List<Map> categoryList = service.CategoryList();

		String forlargeList = getStringFromLargeList(largeList);
		String forcategoryList = getStringFromCategoryList(categoryList);
		String forbrdList = getStringFromBoardList(boardList);

		model.addAttribute("forlargeList", forlargeList);
		model.addAttribute("forcategoryList", forcategoryList);
		model.addAttribute("forbrdList", forbrdList);
		model.addAttribute("postList", service.ListPostToBoard(map));
		return "admin/A_postSearch";
	}

	// 게시판 글 이관
	@RequestMapping(value = "/A_postMove", method = RequestMethod.GET)
	public String A_postMove(HttpSession session, Model model) {
		logger.info("게시판 글 이관 페이지");
		return "admin/A_postMove";
	}

	// 게시판 글 삭제
	@RequestMapping(value = "/A_postDelete", method = RequestMethod.POST)
	public String A_postDelete(HttpServletRequest request, HttpSession session, Model model, String[] checkedList)
			throws Exception {
		logger.info("게시판 글 삭제 페이지");
		Map map = new HashMap();
		List<String> checklist = new ArrayList<>();
		for (int i = 0; i < checkedList.length; i++) {
			checklist.add(checkedList[i]);
		}
		map.put("brd_id", request.getParameter("brd_id"));
		map.put("checklist", checklist);

		service.deletePostToBoard(map);

		List<Map> boardList = service.getBoardList();
		List<Map> largeList = service.largeCategoryList();
		List<Map> categoryList = service.CategoryList();

		String forlargeList = getStringFromLargeList(largeList);
		String forcategoryList = getStringFromCategoryList(categoryList);
		String forbrdList = getStringFromBoardList(boardList);

		model.addAttribute("forlargeList", forlargeList);
		model.addAttribute("forcategoryList", forcategoryList);
		model.addAttribute("forbrdList", forbrdList);
		model.addAttribute("postList", service.ListPostToBoard(map));
		return "admin/A_postSearch";
	}

	@RequestMapping(value = "/A_postReView", method = RequestMethod.POST)
	public String A_postReView(HttpServletRequest request, HttpSession session, Model model, String[] checkedList)
			throws Exception {
		logger.info("게시판 글 삭제 페이지");
		Map map = new HashMap();
		List<String> checklist = new ArrayList<>();
		for (int i = 0; i < checkedList.length; i++) {
			checklist.add(checkedList[i]);
		}
		map.put("brd_id", request.getParameter("brd_id"));
		map.put("checklist", checklist);

		service.reViewPostToBoard(map);

		List<Map> boardList = service.getBoardList();
		List<Map> largeList = service.largeCategoryList();
		List<Map> categoryList = service.CategoryList();

		String forlargeList = getStringFromLargeList(largeList);
		String forcategoryList = getStringFromCategoryList(categoryList);
		String forbrdList = getStringFromBoardList(boardList);

		model.addAttribute("forlargeList", forlargeList);
		model.addAttribute("forcategoryList", forcategoryList);
		model.addAttribute("forbrdList", forbrdList);
		model.addAttribute("postList", service.ListPostToBoard(map));
		return "admin/A_postSearch";
	}

	// 게시판 관리페이지 보기
	@RequestMapping(value = "/A_boardList", method = RequestMethod.GET)
	public String A_boardList(HttpSession session, Model model) throws Exception {
		logger.info("게시판 목록 페이지");
		List<Map> boardList = service.getBoardList();
		List<Map> largeList = service.largeCategoryList();
		List<Map> categoryList = service.CategoryList();

		String forlargeList = getStringFromLargeList(largeList);
		String forcategoryList = getStringFromCategoryList(categoryList);

		model.addAttribute("boardList", boardList);
		model.addAttribute("largeList", largeList);
		model.addAttribute("forlargeList", forlargeList);
		model.addAttribute("forcategoryList", forcategoryList);
		return "admin/A_boardList";
	}

	// 게시판 추가하기
	@RequestMapping(value = "/A_makeBoard", method = RequestMethod.POST)
	public String A_makeBoard(HttpServletRequest request, HttpSession session, Model model) {
		logger.info("게시판 제작하기");
		Map map = new HashMap();
		map.put("large_id", Integer.parseInt(request.getParameter("large_id")));
		map.put("category_id", Integer.parseInt(request.getParameter("category_id")));
		map.put("brd_readauth", Integer.parseInt(request.getParameter("brd_readauth")));
		map.put("brd_writeauth", Integer.parseInt(request.getParameter("brd_writeauth")));
		map.put("brd_name", (String) request.getParameter("brd_name"));
		service.makeBoard(map);
		return "admin/A_boardList";
	}

	@ResponseBody
	@RequestMapping(value = "/A_leaveBoard", method = RequestMethod.POST)
	public void A_leaveBoard(@RequestParam("brd_id") int brd_id, Model model) {
		logger.info("게시판 삭제하기");
		model.addAttribute("result", service.leaveBoard(brd_id));
	}

	@ResponseBody
	@RequestMapping(value = "/A_changeBoardVisibility", method = RequestMethod.POST)
	public void A_changeBoardVisibility(@RequestParam("brd_id") int brd_id,
			@RequestParam("brd_exposure") int brd_exposure, Model model) {
		logger.info("게시판 표시여부 변경하기");
		model.addAttribute(service.changeBoardVisibility(brd_id, brd_exposure));
	}

	private Date StringToDate(String string) throws ParseException {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date to = transFormat.parse(string);
		return to;
	}

	private String getStringFromBoardList(List<Map> boardList) {
		String forbrdList = "[";
		for (int i = 0; i < boardList.size(); i++) {
			forbrdList += "{large_id:" + boardList.get(i).get("large_id") + ", category_id:"
					+ boardList.get(i).get("category_id") + ", brd_id:" + boardList.get(i).get("brd_id")
					+ ", brd_name:'" + boardList.get(i).get("brd_name") + "'}";
			if (i == (boardList.size() - 1)) {
				forbrdList += "]";
			} else {
				forbrdList += ",";
			}
		}
		return forbrdList;
	}

	private String getStringFromCategoryList(List<Map> categoryList) {
		String forcategoryList = "[";
		for (int i = 0; i < categoryList.size(); i++) {
			forcategoryList += "{large_id:" + categoryList.get(i).get("large_id") + ", category_id:"
					+ Integer.parseInt((String) categoryList.get(i).get("id")) + ", category_name:'"
					+ categoryList.get(i).get("name") + "'}";
			if (i == (categoryList.size() - 1)) {
				forcategoryList += "]";
			} else {
				forcategoryList += ",";
			}
		}
		return forcategoryList;
	}

	private String getStringFromLargeList(List<Map> largeList) {
		String forlargeList = "[";
		for (int i = 0; i < largeList.size(); i++) {
			forlargeList += "{id:" + Integer.parseInt((String) largeList.get(i).get("id")) + ", name:'"
					+ largeList.get(i).get("name") + "'}";
			if (i == (largeList.size() - 1)) {
				forlargeList += "]";
			} else {
				forlargeList += ",";
			}
		}
		return forlargeList;
	}
}

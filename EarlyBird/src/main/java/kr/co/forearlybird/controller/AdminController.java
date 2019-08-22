package kr.co.forearlybird.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.forearlybird.service.AdminService;

@Controller
@RequestMapping(value = "admin", method = { RequestMethod.GET, RequestMethod.POST })
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	AdminService service;

	// 게시판 별 관리자 보기
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/A_adminList", method = RequestMethod.GET)
	public String A_adminList(@RequestParam("brd_id") int brd_id, HttpSession session, Model model) {
		logger.info("게시판 별 관리자 보기 페이지");
		logger.info("게시판 아이디 : " + brd_id);
		List<String> adminNicknames = service.getAdminNickname(brd_id);
		List<Map> adminList = service.getMemberListForBoardAdmin();

		model.addAttribute("brd_id", brd_id);
		model.addAttribute("adminNicknames", adminNicknames);
		model.addAttribute("adminList", adminList);

		return "admin/A_adminList";
	}

	@RequestMapping(value = "/A_checkAdminId", method = RequestMethod.POST)
	@ResponseBody
	public String A_checkAdminId(@RequestParam("brd_id") int brd_id, @RequestParam("mem_userid") String mem_userid,
			HttpSession session, Model model) {
		logger.info("게시판 관리자 선정 여부 체크");
		int tmp = service.checkAdminId(brd_id, mem_userid);
		logger.info("service :" + tmp);
		String result = tmp + "";
		return result;
	}

	// 게시판 별 관리자 설정
	@SuppressWarnings({ "unchecked", "rawtypes" })
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

		return "admin/A_adminList";
	}

	// 게시판 별 관리자 설정을 위한 후보자 목록 검색
	@SuppressWarnings({ "unchecked", "rawtypes" })
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
	@RequestMapping(value = "/A_memberSearch", method = RequestMethod.GET)
	public String A_memberSearch(HttpSession session, Model model) {
		logger.info("회원 검색 페이지");
		return "admin/A_memberSearch";
	}

	// 회원 글 보기
	@RequestMapping(value = "/A_memberPostList", method = RequestMethod.GET)
	public String A_memberPostList(HttpSession session, Model model) {
		logger.info("회원 글 보기 페이지");
		return "admin/A_memberPostList";
	}

	// 회원 글 이관
	@RequestMapping(value = "/A_memberPostUpdate", method = RequestMethod.GET)
	public String A_memberPostUpdate(HttpSession session, Model model) {
		logger.info("회원 글 이관 페이지");
		return "admin/A_memberPostUpdate";
	}

	// 회원 댓글 보기
	@RequestMapping(value = "/A_memberReplyList", method = RequestMethod.GET)
	public String A_memberReplyList(HttpSession session, Model model) {
		logger.info("회원 댓글 보기 페이지");
		return "admin/A_memberReplyList";
	}

	// 회원 댓글 이관
	@RequestMapping(value = "/A_memberReplyUpdate", method = RequestMethod.GET)
	public String A_memberReplyUpdate(HttpSession session, Model model) {
		logger.info("회원 댓글 이관 페이지");
		return "admin/A_memberReplyUpdate";
	}

	// 탈퇴 회원 정보 검색
	@RequestMapping(value = "/A_deleteMemberSearch", method = RequestMethod.GET)
	public String A_deleteMemberSearch(HttpSession session, Model model) {
		logger.info("탈퇴 회원 정보 검색 페이지");
		return "admin/A_deleteMemberSearch";
	}

	// 탈퇴 회원 정보 보기
	@RequestMapping(value = "/A_deleteMemberList", method = RequestMethod.GET)
	public String A_deleteMemberList(HttpSession session, Model model) {
		logger.info("탈퇴 회원 정보 보기 페이지");
		return "admin/A_deleteMemberList";
	}

	// 탈퇴 회원 정보 삭제
	@RequestMapping(value = "/A_deleteMemberLeave", method = RequestMethod.GET)
	public String A_deleteMemberLeave(HttpSession session, Model model) {
		logger.info("탈퇴 회원 정보 삭제 페이지");
		return "admin/A_deleteMemberLeave";
	}

	// 공지사항 보기
	@RequestMapping(value = "/A_noticeList", method = RequestMethod.GET)
	public String A_noticeList(HttpSession session, Model model) {
		logger.info("공지사항 보기 페이지");
		return "admin/A_noticeList";
	}

	// 공지사항 등록
	@RequestMapping(value = "/A_noticeMake", method = RequestMethod.GET)
	public String A_noticeMake(HttpSession session, Model model) {
		logger.info("공지사항 등록 페이지");
		return "admin/A_noticeMake";
	}

	// 공지사항 수정
	@RequestMapping(value = "/A_noticeUpdate", method = RequestMethod.GET)
	public String A_noticeUpdate(HttpSession session, Model model) {
		logger.info("공지사항 수정 페이지");
		return "admin/A_noticeUpdate";
	}

	// 공지사항 삭제
	@RequestMapping(value = "/A_noticeDelete", method = RequestMethod.GET)
	public String A_noticeDelete(HttpSession session, Model model) {
		logger.info("공지사항 삭제 페이지");
		return "admin/A_noticeDelete";
	}

	// 카테고리 보기
	@SuppressWarnings("rawtypes")
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
	@RequestMapping(value = "/A_largeCategoryMake", method = RequestMethod.POST)
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
	@RequestMapping(value = "/A_categoryMake", method = RequestMethod.POST)
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
	@RequestMapping(value = "/A_largeCategoryDelete", method = RequestMethod.GET)
	public void A_LargeCategoryDelete(@RequestParam("large_id") int large_id, Model model) {
		logger.info("카테고리 삭제 페이지");
		int result = service.leaveLargeCategory(large_id);
		model.addAttribute("result", result);
	}

	// 카테고리 삭제
	@ResponseBody
	@RequestMapping(value = "/A_categoryDelete", method = RequestMethod.GET)
	public void A_categoryDelete(@RequestParam("category_id") int category_id, Model model) {
		logger.info("카테고리 삭제 페이지");
		logger.info(category_id + "");
		int result = service.leaveCategory(category_id);
		model.addAttribute("result", result);
	}

	// 게시판 글 보기
	@SuppressWarnings({ "rawtypes", "unchecked" })
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/A_postUpdate", method = RequestMethod.GET)
	public String A_postUpdate(HttpServletRequest request, HttpSession session, Model model, String[] checkedList, String[] NoticeOrNot) throws Exception {
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/A_postDelete", method = RequestMethod.POST)
	public String A_postDelete(HttpServletRequest request, HttpSession session, Model model, String[] checkedList) throws Exception {
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/A_postReView", method = RequestMethod.POST)
	public String A_postReView(HttpServletRequest request, HttpSession session, Model model, String[] checkedList) throws Exception {
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
	@SuppressWarnings("rawtypes")
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/A_makeBoard", method = RequestMethod.GET)
	public String A_makeBoard(HttpServletRequest request, HttpSession session, Model model) {
		logger.info("게시판 제작하기");
		Map map = new HashMap();
		map.put("large_id", Integer.parseInt(request.getParameter("large_id")));
		map.put("category_id", Integer.parseInt(request.getParameter("category_id")));
		map.put("brd_readauth", Integer.parseInt(request.getParameter("brd_readauth")));
		map.put("brd_writeauth", Integer.parseInt(request.getParameter("brd_writeauth")));
		map.put("brd_name", (String) request.getParameter("brd_name"));
		System.out.println("map : " + map.toString());
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

	@SuppressWarnings("rawtypes")
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

	@SuppressWarnings("rawtypes")
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

	@SuppressWarnings("rawtypes")
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

package kr.co.forearlybird.controller;

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
	@RequestMapping(value = "/A_adminList", method = RequestMethod.GET)
	public String A_adminList(HttpSession session, Model model) {
		logger.info("게시판 별 관리자 보기 페이지");
		return "admin/A_adminList";
	}

	// 게시판 별 관리자 설정
	@RequestMapping(value = "/A_adminMake", method = RequestMethod.GET)
	public String A_adminMake(HttpSession session, Model model) {
		logger.info("게시판 별 관리자 설정 페이지");
		return "admin/A_adminMake";
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
	public String A_categoryList1(HttpSession session, Model model) {
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
	@RequestMapping(value = "/A_postList", method = RequestMethod.GET)
	public String A_postList(HttpSession session, Model model) {
		logger.info("게시판 글 보기 페이지");
		return "admin/A_postList";
	}

	// 게시판 글 검색
	@RequestMapping(value = "/A_postSearch", method = RequestMethod.GET)
	public String A_postSearch(HttpSession session, Model model) {
		logger.info("게시판 글 검색 페이지");
		return "admin/A_postSearch";
	}

	// 게시판 글 수정
	@RequestMapping(value = "/A_postUpdate", method = RequestMethod.GET)
	public String A_postUpdate(HttpSession session, Model model) {
		logger.info("게시판 글 수정 페이지");
		return "admin/A_postUpdate";
	}

	// 게시판 글 이관
	@RequestMapping(value = "/A_postMove", method = RequestMethod.GET)
	public String A_postMove(HttpSession session, Model model) {
		logger.info("게시판 글 이관 페이지");
		return "admin/A_postMove";
	}

	// 게시판 글 삭제
	@RequestMapping(value = "/A_postLeave", method = RequestMethod.GET)
	public String A_postLeave(HttpSession session, Model model) {
		logger.info("게시판 글 삭제 페이지");
		return "admin/A_postLeave";
	}

	// 게시판 관리페이지 보기
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/A_boardList", method = RequestMethod.GET)
	public String A_boardList(HttpSession session, Model model) {
		logger.info("게시판 목록 페이지");
		List<Map> boardList = service.getBoardList();
		model.addAttribute("boardList", boardList);
		return "admin/A_boardList";
	}
}

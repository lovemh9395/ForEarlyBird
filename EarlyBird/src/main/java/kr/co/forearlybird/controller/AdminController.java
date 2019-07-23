package kr.co.forearlybird.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.forearlybird.MainController;

@Controller
@RequestMapping(value = "admin", method = { RequestMethod.GET, RequestMethod.POST })
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	// 게시판 별 관리자 보기
	@RequestMapping(value = "/A_adminList", method = RequestMethod.GET)
	public String A_adminList(HttpSession session, Model model) {
		logger.info("게시판 별 관리자 보기 페이지");
		model.addAttribute("user",session.getAttribute("user"));
		return "admin/A_adminList";
	}

	// 게시판 별 관리자 설정
	@RequestMapping(value = "/A_adminMake", method = RequestMethod.GET)
	public String A_adminMake(HttpSession session, Model model) {
		logger.info("게시판 별 관리자 설정 페이지");
		model.addAttribute("user",session.getAttribute("user"));
		return "admin/A_adminMake";
	}

	// 회원 검색
	@RequestMapping(value = "/A_memberSearch", method = RequestMethod.GET)
	public String A_memberSearch(HttpSession session, Model model) {
		logger.info("회원 검색 페이지");
		model.addAttribute("user",session.getAttribute("user"));
		return "admin/A_memberSearch";
	}

	// 회원 글 보기
	@RequestMapping(value = "/A_memberPostList", method = RequestMethod.GET)
	public String A_memberPostList(HttpSession session, Model model) {
		logger.info("회원 글 보기 페이지");
		model.addAttribute("user",session.getAttribute("user"));
		return "admin/A_memberPostList";
	}

	// 회원 글 이관
	@RequestMapping(value = "/A_memberPostUpdate", method = RequestMethod.GET)
	public String A_memberPostUpdate(HttpSession session, Model model) {
		logger.info("회원 글 이관 페이지");
		model.addAttribute("user",session.getAttribute("user"));
		return "admin/A_memberPostUpdate";
	}

	// 회원 댓글 보기
	@RequestMapping(value = "/A_memberReplyList", method = RequestMethod.GET)
	public String A_memberReplyList(HttpSession session, Model model) {
		logger.info("회원 댓글 보기 페이지");
		model.addAttribute("user",session.getAttribute("user"));
		return "admin/A_memberReplyList";
	}

	// 회원 댓글 이관
	@RequestMapping(value = "/A_memberReplyUpdate", method = RequestMethod.GET)
	public String A_memberReplyUpdate(HttpSession session, Model model) {
		logger.info("회원 댓글 이관 페이지");
		model.addAttribute("user",session.getAttribute("user"));
		return "admin/A_memberReplyUpdate";
	}

	// 차단 회원 목록 보기
	@RequestMapping(value = "/A_blackList", method = RequestMethod.GET)
	public String A_blackList(HttpSession session, Model model) {
		logger.info("차단 회원 목록 보기 페이지");
		model.addAttribute("user",session.getAttribute("user"));
		return "admin/A_blackList";
	}

	// 차단 회원 등록
	@RequestMapping(value = "/A_blackListMake", method = RequestMethod.GET)
	public String A_blackListMake(HttpSession session, Model model) {
		logger.info("차단 회원 등록 페이지");
		model.addAttribute("user",session.getAttribute("user"));
		return "admin/A_blackListMake";
	}

	// 차단 회원 등록 해제
	@RequestMapping(value = "/A_blackListDelete", method = RequestMethod.GET)
	public String A_blackListDelete(HttpSession session, Model model) {
		logger.info("차단 회원 등록 해제 페이지");
		model.addAttribute("user",session.getAttribute("user"));
		return "admin/A_blackListDelete";
	}

	// 탈퇴 회원 정보 검색
	@RequestMapping(value = "/A_deleteMemberSearch", method = RequestMethod.GET)
	public String A_deleteMemberSearch(HttpSession session, Model model) {
		logger.info("탈퇴 회원 정보 검색 페이지");
		model.addAttribute("user",session.getAttribute("user"));
		return "admin/A_deleteMemberSearch";
	}

	// 탈퇴 회원 정보 보기
	@RequestMapping(value = "/A_deleteMemberList", method = RequestMethod.GET)
	public String A_deleteMemberList(HttpSession session, Model model) {
		logger.info("탈퇴 회원 정보 보기 페이지");
		model.addAttribute("user",session.getAttribute("user"));
		return "admin/A_deleteMemberList";
	}

	// 탈퇴 회원 정보 삭제
	@RequestMapping(value = "/A_deleteMemberLeave", method = RequestMethod.GET)
	public String A_deleteMemberLeave(HttpSession session, Model model) {
		logger.info("탈퇴 회원 정보 삭제 페이지");
		model.addAttribute("user",session.getAttribute("user"));
		return "admin/A_deleteMemberLeave";
	}

	// 공지사항 보기
	@RequestMapping(value = "/A_noticeList", method = RequestMethod.GET)
	public String A_noticeList(HttpSession session, Model model) {
		logger.info("공지사항 보기 페이지");
		model.addAttribute("user",session.getAttribute("user"));
		return "admin/A_noticeList";
	}

	// 공지사항 등록
	@RequestMapping(value = "/A_noticeMake", method = RequestMethod.GET)
	public String A_noticeMake(HttpSession session, Model model) {
		logger.info("공지사항 등록 페이지");
		model.addAttribute("user",session.getAttribute("user"));
		return "admin/A_noticeMake";
	}

	// 공지사항 수정
	@RequestMapping(value = "/A_noticeUpdate", method = RequestMethod.GET)
	public String A_noticeUpdate(HttpSession session, Model model) {
		logger.info("공지사항 수정 페이지");
		model.addAttribute("user",session.getAttribute("user"));
		return "admin/A_noticeUpdate";
	}

	// 공지사항 삭제
	@RequestMapping(value = "/A_noticeDelete", method = RequestMethod.GET)
	public String A_noticeDelete(HttpSession session, Model model) {
		logger.info("공지사항 삭제 페이지");
		model.addAttribute("user",session.getAttribute("user"));
		return "admin/A_noticeDelete";
	}

	// 카테고리 보기
	@RequestMapping(value = "/A_categoryList", method = RequestMethod.GET)
	public String A_categoryList(HttpSession session, Model model) {
		logger.info("카테고리 보기 페이지");
		model.addAttribute("user",session.getAttribute("user"));
		return "admin/A_categoryList";
	}

	// 카테고리 생성
	@RequestMapping(value = "/A_categoryMake", method = RequestMethod.GET)
	public String A_categoryMake(HttpSession session, Model model) {
		logger.info("카테고리 생성 페이지");
		model.addAttribute("user",session.getAttribute("user"));
		return "admin/A_categoryMake";
	}

	// 카테고리 삭제
	@RequestMapping(value = "/A_categoryDelete", method = RequestMethod.GET)
	public String A_categoryDelete(HttpSession session, Model model) {
		logger.info("카테고리 삭제 페이지");
		model.addAttribute("user",session.getAttribute("user"));
		return "admin/A_categoryDelete";
	}

	// 게시판 글 보기
	@RequestMapping(value = "/A_postList", method = RequestMethod.GET)
	public String A_postList(HttpSession session, Model model) {
		logger.info("게시판 글 보기 페이지");
		model.addAttribute("user",session.getAttribute("user"));
		return "admin/A_postList";
	}

	// 게시판 글 검색
	@RequestMapping(value = "/A_postSearch", method = RequestMethod.GET)
	public String A_postSearch(HttpSession session, Model model) {
		logger.info("게시판 글 검색 페이지");
		model.addAttribute("user",session.getAttribute("user"));
		return "admin/A_postSearch";
	}

	// 게시판 글 수정
	@RequestMapping(value = "/A_postUpdate", method = RequestMethod.GET)
	public String A_postUpdate(HttpSession session, Model model) {
		logger.info("게시판 글 수정 페이지");
		model.addAttribute("user",session.getAttribute("user"));
		return "admin/A_postUpdate";
	}

	// 게시판 글 이관
	@RequestMapping(value = "/A_postMove", method = RequestMethod.GET)
	public String A_postMove(HttpSession session, Model model) {
		logger.info("게시판 글 이관 페이지");
		model.addAttribute("user",session.getAttribute("user"));
		return "admin/A_postMove";
	}

	// 게시판 글 삭제
	@RequestMapping(value = "/A_postLeave", method = RequestMethod.GET)
	public String A_postLeave(HttpSession session, Model model) {
		logger.info("게시판 글 삭제 페이지");
		model.addAttribute("user",session.getAttribute("user"));
		return "admin/A_postLeave";
	}
}

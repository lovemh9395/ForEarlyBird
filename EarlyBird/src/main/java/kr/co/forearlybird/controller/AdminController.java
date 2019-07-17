package kr.co.forearlybird.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.forearlybird.MainController;

public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	// 게시판 별 관리자 보기
	@RequestMapping(value = "/A_postAdminList", method = RequestMethod.GET)
	public String A_postAdminList(Model model) {
		logger.info("게시판 별 관리자 보기 페이지");

		return "A_postAdminList";
	}
	
	// 게시판 별 관리자 설정
	@RequestMapping(value = "/A_adminMake", method = RequestMethod.GET)
	public String A_adminMake(Model model) {
		logger.info("게시판 별 관리자 설정 페이지");

		return "A_adminMake";
	}
	
	// 회원 검색
		@RequestMapping(value = "/A_memberSearch", method = RequestMethod.GET)
		public String A_memberSearch(Model model) {
			logger.info("회원 검색 페이지");

			return "A_memberSearch";
		}
		
	// 회원 글 보기
	@RequestMapping(value = "/A_memberPostList", method = RequestMethod.GET)
	public String A_memberPostList(Model model) {
		logger.info("회원 글 보기 페이지");

		return "A_memberPostList";
	}
	
	// 회원 글 이관
	@RequestMapping(value = "/A_memberPostUpdate", method = RequestMethod.GET)
	public String A_memberPostUpdate(Model model) {
		logger.info("회원 글 이관 페이지");

		return "A_memberPostUpdate";
	}
	
	// 회원 댓글 보기
	@RequestMapping(value = "/A_memberReplyList", method = RequestMethod.GET)
	public String A_memberReplyList(Model model) {
		logger.info("회원 댓글 보기 페이지");

		return "A_memberReplyList";
	}
	
	// 회원 댓글 이관
	@RequestMapping(value = "/A_memberReplyUpdate", method = RequestMethod.GET)
	public String A_memberReplyUpdate(Model model) {
		logger.info("회원 댓글 이관 페이지");

		return "A_memberReplyUpdate";
	}
	
	// 차단 회원 목록 보기
	@RequestMapping(value = "/A_blackList", method = RequestMethod.GET)
	public String A_blackList(Model model) {
		logger.info("차단 회원 목록 보기 페이지");

		return "A_blackList";
	}
	
	// 차단 회원 등록
	@RequestMapping(value = "/A_blackListMake", method = RequestMethod.GET)
	public String A_blackListMake(Model model) {
		logger.info("차단 회원 등록 페이지");

		return "A_blackListMake";
	}
	
	// 차단 회원 등록 해제
	@RequestMapping(value = "/A_blackListDelete", method = RequestMethod.GET)
	public String A_blackListDelete(Model model) {
		logger.info("차단 회원 등록 해제 페이지");

		return "A_blackListDelete";
	}
	
	// 탈퇴 회원 정보 검색
	@RequestMapping(value = "/A_deleteMemberSearch", method = RequestMethod.GET)
	public String A_deleteMemberSearch(Model model) {
		logger.info("탈퇴 회원 정보 검색 페이지");

		return "A_deleteMemberSearch";
	}
	
	// 탈퇴 회원 정보 보기
	@RequestMapping(value = "/A_deleteMemberList", method = RequestMethod.GET)
	public String A_deleteMemberList(Model model) {
		logger.info("탈퇴 회원 정보 보기 페이지");

		return "A_deleteMemberList";
	}
	
	// 탈퇴 회원 정보 삭제
	@RequestMapping(value = "/A_deleteMemberLeave", method = RequestMethod.GET)
	public String A_deleteMemberLeave(Model model) {
		logger.info("탈퇴 회원 정보 삭제 페이지");

		return "A_deleteMemberLeave";
	}
	
	// 공지사항 보기
	@RequestMapping(value = "/A_noticeList", method = RequestMethod.GET)
	public String A_noticeList(Model model) {
		logger.info("공지사항 보기 페이지");

		return "A_noticeList";
	}
	
	// 공지사항 등록
	@RequestMapping(value = "/A_noticeMake", method = RequestMethod.GET)
	public String A_noticeMake(Model model) {
		logger.info("공지사항 등록 페이지");

		return "A_noticeMake";
	}
	
	// 공지사항 수정
	@RequestMapping(value = "/A_noticeUpdate", method = RequestMethod.GET)
	public String A_noticeUpdate(Model model) {
		logger.info("공지사항 수정 페이지");

		return "A_noticeUpdate";
	}
	
	// 공지사항 삭제
	@RequestMapping(value = "/A_noticeDelete", method = RequestMethod.GET)
	public String A_noticeDelete(Model model) {
		logger.info("공지사항 삭제 페이지");

		return "A_noticeDelete";
	}
	
	// 카테고리 보기
	@RequestMapping(value = "/A_categoryList", method = RequestMethod.GET)
	public String A_categoryList(Model model) {
		logger.info("카테고리 보기 페이지");

		return "A_categoryList";
	}
	
	// 카테고리 생성
	@RequestMapping(value = "/A_categoryMake", method = RequestMethod.GET)
	public String A_categoryMake(Model model) {
		logger.info("카테고리 생성 페이지");

		return "A_categoryMake";
	}
	
	// 카테고리 삭제
	@RequestMapping(value = "/A_categoryDelete", method = RequestMethod.GET)
	public String A_categoryDelete(Model model) {
		logger.info("카테고리 삭제 페이지");

		return "A_categoryDelete";
	}
	
	// 게시판 글 보기
	@RequestMapping(value = "/A_postList", method = RequestMethod.GET)
	public String A_postList(Model model) {
		logger.info("게시판 글 보기 페이지");

		return "A_postList";
	}
	
	// 게시판 글 검색
	@RequestMapping(value = "/A_postSearch", method = RequestMethod.GET)
	public String A_postSearch(Model model) {
		logger.info("게시판 글 검색 페이지");

		return "A_postSearch";
	}
	
	// 게시판 글 수정
	@RequestMapping(value = "/A_postUpdate", method = RequestMethod.GET)
	public String A_postUpdate(Model model) {
		logger.info("게시판 글 수정 페이지");

		return "A_postUpdate";
	}
	
	// 게시판 글 이관
	@RequestMapping(value = "/A_postMove", method = RequestMethod.GET)
	public String A_postMove(Model model) {
		logger.info("게시판 글 이관 페이지");

		return "A_postMove";
	}
	
	// 게시판 글 삭제
	@RequestMapping(value = "/A_postLeave", method = RequestMethod.GET)
	public String A_postLeave(Model model) {
		logger.info("게시판 글 삭제 페이지");

		return "A_postLeave";
	}
}

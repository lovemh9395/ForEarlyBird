package kr.co.forearlybird.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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

import kr.co.forearlybird.paging.Criteria;
import kr.co.forearlybird.paging.PageMaker;
import kr.co.forearlybird.service.ReplyService;

@Controller
@RequestMapping("reply")
public class ReplyController {
	private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);

	@Autowired
	ReplyService service;
	
	// 댓글 등록
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/R_make", method = { RequestMethod.GET, RequestMethod.POST })
	public String R_make(HttpSession session, HttpServletRequest request,Map map, Model model, @ModelAttribute("cri") Criteria cri) throws Exception {
		logger.info("댓글 등록 페이지");
		String sessionid = (String) session.getAttribute("useridd");
		String replycontent = request.getParameter("rpl_content");
		String post_id = request.getParameter("post_id");
		map.put("sessionid", sessionid);
		map.put("replycontent", replycontent);
		map.put("post_id", post_id);
		service.R_make(map);
		logger.info(post_id);
		logger.info(sessionid);
		logger.info(replycontent);
		
		map.put("cri", cri);
		map.put("post_id", post_id);
		PageMaker pageMaker = makePageMaker(map);
		model.addAttribute("R_list", service.replyListCriteria(map));
		
		model.addAttribute("pageMaker", pageMaker); // 게시판 하단의 페이징 관련, 이전페이지, 페이지 링크 , 다음 페이지
		//댓글 리스트 불러오기 
		//ex -> model.addAttribute("R_list", service.replyListCriteria(map));
		//현재 R_list에서 c:forEach 구문 돌려서 댓글 불러올때 출력할 List가 없어서 출력이 안됨
		return "reply/R_list";
	}

	// 댓글 수정
	@RequestMapping(value = "/R_update", method = RequestMethod.GET)
	public String R_update(Model model) {
		logger.info("댓글 수정 페이지");

		return "R_update";
	}

	// 댓글 삭제
	@RequestMapping(value = "/R_delete", method = {RequestMethod.GET,RequestMethod.POST})
	public String R_delete(@RequestParam("rpl_id") int rpl_id,HttpServletRequest request,Model model) {
		logger.info("댓글 삭제 페이지");
		service.R_delete(rpl_id);
		logger.info(request.getParameter("post_id"));
		int post_id = Integer.parseInt(request.getParameter("post_id"));
		model.addAttribute("R_list",service.R_list(post_id));
		return "reply/R_list";
	}
	
	@SuppressWarnings("rawtypes")
	private PageMaker makePageMaker(Map map) throws Exception {
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri((Criteria)map.get("cri"));
		pageMaker.setTotalCount(service.replyListCountCriteria(map));
	
	return pageMaker;
	}
}

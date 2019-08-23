package kr.co.forearlybird.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.forearlybird.dao.MemberDAO;
import kr.co.forearlybird.dao.PostDAO;
import kr.co.forearlybird.dao.ReplyDAO;
import kr.co.forearlybird.domain.Post;
import kr.co.forearlybird.domain.Reply;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberDAO.class);

	@Autowired
	ReplyDAO replyDAO;
	
	@Autowired
	PostDAO postDAO;
	
	
	@SuppressWarnings("rawtypes")
	@Override
	public int R_make(Map map) {
		return replyDAO.R_make(map);
	}

	@Override
	public int R_delete(int rpl_id) {
		return replyDAO.R_delete(rpl_id);
	}
	
	@Override
	public List<Reply> replyListCriteria(Map map) throws Exception {
		logger.info("Reply 페이징처리2 List Service");
		return replyDAO.replylistCriteria(map);
	}
	
	
	@Override
	public int replyListCountCriteria(Map map) throws Exception {
		logger.info("Reply 페이징처리3 List Service");
		return replyDAO.replyCountPaging(map);
	}
	
	public List<Reply> R_list(int post_id) {
		return replyDAO.R_list(post_id);
	}

}

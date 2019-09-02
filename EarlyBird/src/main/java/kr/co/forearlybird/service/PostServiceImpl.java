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

@SuppressWarnings("rawtypes")
@Service
public class PostServiceImpl implements PostService {
	private static final Logger logger = LoggerFactory.getLogger(MemberDAO.class);

	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private ReplyDAO replyDAO;

	@Override
	public List<Post> P_list() {
		logger.info("자유게시판 List Service");
		return postDAO.P_list();
	}

	@Override
	public List<Post> listAll() throws Exception {
		logger.info("페이징처리1 List Service");
		return postDAO.listAll();
	}

	@Override
	public List<Post> listCriteria(Map map) throws Exception {
		logger.info("페이징처리2 List Service");
		return postDAO.listCriteria(map);
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
	
	@Override
	public int listCountCriteria(Map map) throws Exception {
		logger.info("페이징처리3 List Service");
		return postDAO.countPaging(map);
	}


	@Override
	public Post P_detail(int post_id) {
		logger.info(post_id+"");
		logger.info("게시글 상세보기 service");
		return postDAO.P_detail(post_id);
	}

	@Override
	public void P_make(Map map) {
		logger.info("글쓰기 service");
		postDAO.P_make(map);
	}

	@Override
	public void P_delete(int post_id) {
		logger.info("글삭제 service");
		postDAO.P_delete(post_id);
	}

	@Override
	public void P_update(Map map) {
		logger.info("글수정 service");
		postDAO.P_update(map);
	}

	@Override
	public int P_recommand(int post_id) {
		logger.info("추천하기 service");
		return postDAO.P_recommand(post_id);
	}

	@Override
	public int updateHit(int post_id) {
		logger.info("조회수 증가 service");
		return postDAO.updateHit(post_id);
	}

	@Override
	public List<Reply> R_list(int post_id) {
		logger.info("댓글 List service");
		return replyDAO.R_list(post_id);
	}	
}

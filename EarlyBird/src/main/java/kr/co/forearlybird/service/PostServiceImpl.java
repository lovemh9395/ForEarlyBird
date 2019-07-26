package kr.co.forearlybird.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.forearlybird.dao.MemberDAO;
import kr.co.forearlybird.dao.PostDAO;
import kr.co.forearlybird.domain.Post;
import kr.co.forearlybird.paging.Criteria;

@Service
public class PostServiceImpl implements PostService {
	private static final Logger logger = LoggerFactory.getLogger(MemberDAO.class);

	@Autowired
	private PostDAO postDAO;

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
	public List<Post> listCriteria(Criteria cri) throws Exception {
		logger.info("페이징처리2 List Service");
		return postDAO.listCriteria(cri);
	}
	
	@Override
	public int listCountCriteria(Criteria cri) throws Exception {
		logger.info("페이징처리3 List Service");
		return postDAO.countPaging(cri);
	}

	@Override
	public Post P_detail(int post_id) {
		logger.info(post_id+"");
		logger.info("게시글 상세보기 service");
		return postDAO.P_detail(post_id);
	}
}

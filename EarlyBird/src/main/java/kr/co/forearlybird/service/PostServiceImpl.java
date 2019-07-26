package kr.co.forearlybird.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.forearlybird.dao.MemberDAO;
import kr.co.forearlybird.dao.PostDAO;
import kr.co.forearlybird.domain.Post;

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
}

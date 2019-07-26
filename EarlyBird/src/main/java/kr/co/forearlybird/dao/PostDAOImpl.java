package kr.co.forearlybird.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.forearlybird.domain.Post;

@Repository
public class PostDAOImpl implements PostDAO {
	private static final Logger logger = LoggerFactory.getLogger(MemberDAO.class);
	@Autowired
	private SqlSession sqlSession;
	
	
	@Override
	public List<Post> P_list() {
		logger.info("자유게시판 List DAO");
		return sqlSession.selectList("post.P_list");
	}

}

package kr.co.forearlybird.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.forearlybird.domain.Post;
import kr.co.forearlybird.paging.Criteria;

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

	@Override
	public List<Post> listAll() throws Exception {
		logger.info("페이징처리1 List DAO");
		return sqlSession.selectList("Post.listAll");
	}

	// 게시판 페이징

	@Override
	public List<Post> listPage(int page) throws Exception {
		logger.info("페이징처리2 List DAO");
		if (page <= 0) {
			page = 1;
		}

		page = (page - 1) * 10;

		return sqlSession.selectList("post.listPage", page);
	}

	// Criteria 를 적용한 게시판 페이징 조회
	@Override
	public List<Post> listCriteria(Criteria cri) throws Exception {
		logger.info("페이징처리3 List DAO");
		return sqlSession.selectList("post.listCriteria", cri);
	}

	@Override
	public int countPaging(Criteria cri) throws Exception {
		logger.info("페이징처리4 List DAO");
		return sqlSession.selectOne("post.countPaging", cri);
	}

	@Override
	public Post P_detail(int post_id) {
		logger.info("게시글 상세보기 DAO");
		return sqlSession.selectOne("post.P_detail",post_id);
	}

	@Override
	public void P_make(Map map) {
		sqlSession.insert("post.P_make",map);
	}

}

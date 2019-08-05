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
		logger.info("글쓰기 DAO");
		sqlSession.insert("post.P_make",map);
	}

	@Override
	public void P_delete(String post_id) {
		logger.info("글삭제 DAO");
		sqlSession.update("post.P_delete",post_id);
	}

	@Override
	public void P_update(Map map) {
		logger.info("글수정 DAO");
		sqlSession.update("post.P_update",map);
	}

	@Override
	public int P_recommand(int post_id) {
		logger.info("추천하기 DAO");
		return sqlSession.update("post.P_recommand",post_id);
	}

	@Override
	public int updateHit(int post_id) {
		logger.info("조회수 증가 DAO");
		return sqlSession.update("post.P_hit",post_id);
	}

}

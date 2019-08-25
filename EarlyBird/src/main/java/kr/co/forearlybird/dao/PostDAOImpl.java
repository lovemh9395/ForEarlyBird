package kr.co.forearlybird.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	@SuppressWarnings("rawtypes")
	@Override
	public List<Post> listCriteria(Map map) throws Exception {
		logger.info("페이징처리3 List DAO");
		return sqlSession.selectList("post.listCriteria", map);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public int countPaging(Map map) throws Exception {
		logger.info("페이징처리4 List DAO");
		return sqlSession.selectOne("post.countPaging", map);
	}


	@Override
	public Post P_detail(int post_id) {
		logger.info("게시글 상세보기 DAO");
		return sqlSession.selectOne("post.P_detail",post_id);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void P_make(Map map) {
		logger.info("글쓰기 DAO");
		sqlSession.insert("post.P_make",map);
	}

	@Override
	public void P_delete(int post_id) {
		logger.info("글삭제 DAO");
		sqlSession.update("post.P_delete",post_id);
	}
	
	@Override
	public void P_reView(int post_id) {
		logger.info("글삭제 DAO");
		sqlSession.update("post.P_reView", post_id);
	}

	@SuppressWarnings("rawtypes")
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

	@SuppressWarnings("rawtypes")
	@Override
	public List<Post> getPostList2(Map tmp) throws Exception {
		logger.info("게시판 글 가져오기1 DAO");
		sqlSession.delete("post.deleteView2");
		sqlSession.insert("post.createView2FromGetPostList2", tmp);
		return sqlSession.selectList("post.lookView2");
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Post> getPostList(Map map) {
		logger.info("게시판 글 가져오기 DAO");
		return sqlSession.selectList("post.getPostList", map);
	}

	@Override
	public int getNewPostNumUnderBoard(int brd_id) {
		logger.info("게시판 최신 글 갯수 가져오기 DAO");
		return sqlSession.selectOne("post.getPostNumUnderBoardInToday", brd_id);
	}

	@Override
	public int getAllPostNumUnderBoard(int brd_id) {
		logger.info("게시판 전체 글 갯수 가져오기 DAO");
		return sqlSession.selectOne("post.getPostNumUnderBoard", brd_id);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void changeParamNotice(int post_id, int post_notice) {
		logger.info("changeParamNoticeDAO");
		int notice = post_notice;
		if (post_notice == 1) {
			notice-=1;
		} else {
			notice+=1;
		}
		Map map = new HashMap();
		map.put("post_id",post_id);
		map.put("post_notice",notice);
		sqlSession.update("post.changeParamNotice", map);
	}
}

package kr.co.forearlybird.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.forearlybird.domain.Reply;

@Repository
public class ReplyDAOImpl implements ReplyDAO {
	private static final Logger logger = LoggerFactory.getLogger(MemberDAO.class);
	
	@Autowired
	SqlSession sqlSession;
	
	@SuppressWarnings("rawtypes")
	@Override
	public int R_make(Map map) {
		logger.info("댓글 쓰기 DAO");
		return sqlSession.insert("reply.R_make",map);
	}
	@Override
	public int R_delete(int rpl_id) {
		logger.info("댓글 삭제 DAO");
		return sqlSession.update("reply.R_delete",rpl_id);
	}

	@Override
	public List<Reply> R_list(int post_id) {
		logger.info("리스트 보여주기 DAO");
		return sqlSession.selectList("reply.R_list",post_id);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<Reply> replylistCriteria(Map map) throws Exception {
		logger.info("페이징처리3 DAO");
		return sqlSession.selectList("reply.replylistCriteria", map);
	}
	
	
	@SuppressWarnings("rawtypes")
	@Override
	public int replyCountPaging(Map map) throws Exception {
		logger.info("reply 페이징처리4 DAO");
		return sqlSession.selectOne("reply.replyCountPaging", map);
	}
}

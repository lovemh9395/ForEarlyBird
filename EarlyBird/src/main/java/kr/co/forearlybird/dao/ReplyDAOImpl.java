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
		return sqlSession.insert("reply.R_make",map);
	}
	@Override
	public int R_delete(int rpl_id) {
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
		logger.info("페이징처리3 List DAO");
		return sqlSession.selectList("reply.replylistCriteria", map);
	}
	
	
	@SuppressWarnings("rawtypes")
	@Override
	public int replyCountPaging(Map map) throws Exception {
		logger.info("reply 페이징처리4 List DAO");
		return sqlSession.selectOne("reply.replyCountPaging", map);
	}
}

package kr.co.forearlybird.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import kr.co.forearlybird.controller.MemberController;

public class MemberDAOImpl {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	@Autowired
	private SqlSession sqlSession;
	
	public Map login(Map<String, Object> map) {
		logger.info("login DAO");
		return sqlSession.selectOne("board.login", map);
	}
}

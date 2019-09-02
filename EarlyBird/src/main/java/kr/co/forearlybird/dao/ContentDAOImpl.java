package kr.co.forearlybird.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.forearlybird.MainController;
import kr.co.forearlybird.domain.Content;

@Repository
public class ContentDAOImpl implements ContentDAO {
	
	@Autowired
	private SqlSession sqlSession;
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Override
	public List<Content> Main_C_list() {
		logger.info("메인페이지 컨텐츠 리스트 DAO");
		return sqlSession.selectList("content.Main_C_list");
	}

	@Override
	public List<Content> C_list_M(int idx) {
		logger.info("컨텐츠 리스트 DAO");
		return sqlSession.selectList("content.C_list_M",idx);
	}
	
	@Override
	public int C_recommand(int cnt_id) {
		logger.info("컨텐츠 추천하기 DAO");
		return sqlSession.update("content.C_recommand",cnt_id);
	}
	
	@Override
	public int C_view(int cnt_id) {
		logger.info("컨텐츠 조회하기 DAO");
		return sqlSession.update("content.C_view",cnt_id);
	}

	@Override
	public String C_connectlink(int connectlink) {
		logger.info("컨텐츠 링크 가져오기 DAO");
		return sqlSession.selectOne("content.C_connectlink",connectlink);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Content> menu_btn(Map map) {
		logger.info("컨텐츠 하나의 메뉴 리스트 DAO");
		return sqlSession.selectList("content.menu_btn",map);
	}
}

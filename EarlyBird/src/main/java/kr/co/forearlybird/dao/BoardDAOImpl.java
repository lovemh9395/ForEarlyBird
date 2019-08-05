package kr.co.forearlybird.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.forearlybird.domain.Board;

@Repository
public class BoardDAOImpl implements BoardDAO{
	private static final Logger logger = LoggerFactory.getLogger(BoardDAOImpl.class);
	
	@Autowired
	SqlSession sqlsession;

	@Override
	public int getNumberOfBoardUnderCategory(int category_id) {
		logger.info("getNumberOfBoardUnderCategoryDAO");
		return sqlsession.selectOne("board.getNumberOfBoardUnderCategory", category_id);
	}

	@Override
	public List<Board> getBoardList() {
		logger.info("getBoardListDAO");
		return sqlsession.selectList("board.getBoardList");
	}

}

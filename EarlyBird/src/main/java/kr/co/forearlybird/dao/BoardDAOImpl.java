package kr.co.forearlybird.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.forearlybird.domain.Board;

@Repository
public class BoardDAOImpl implements BoardDAO {
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

	@SuppressWarnings("rawtypes")
	@Override
	public int getBoardIdNumToName(Map map) {
		logger.info("getBoardIdToNameDAO");
		return sqlsession.selectOne("board.getBoardIdNumToName", map);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void makeBoard(Map newMap) {
		logger.info("makeBoardDAO");
		sqlsession.insert("board.makeBoard", newMap);
	}

	@Override
	public int getBoardMAXID() {
		logger.info("getBoardMAXID_DAO");
		return sqlsession.selectOne("board.getBoardMAXID");
	}

	@Override
	public int leaveBoard(int brd_id) {
		logger.info("leaveBoardDAO");
		return sqlsession.delete("board.leaveBoard",brd_id);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int changeBoardVisibility(Map map) {
		logger.info("changeBoardVisibilityDAO");
		return sqlsession.update("board.changeBoardVisibility", map);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Integer> getBrd_idList(Map tmp) {
		logger.info("getBrd_idListDAO");
		if ((int)tmp.get("category_id")!=0) {
			return sqlsession.selectList("board.getBrd_listByLC", tmp);
		} else if ((int)tmp.get("large_id")!=0) {
			return sqlsession.selectList("board.getBrd_listByL", tmp);
		} else {
			return sqlsession.selectList("board.getBrd_list");
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map getLargeAndCategoryid(int brd_id) {
		logger.info("getLargeAndCategoryidDAO");
		return sqlsession.selectOne("board.getLargeAndCategoryid", brd_id);
	}

	@Override
	public int checkBoardId(int category_id) {
		logger.info("checkBoardIdDAO");
		return sqlsession.selectOne("board.checkBoardId",category_id);
	}

	@Override
	public int checkBoardIdByCategory(int category_id) {
		logger.info("checkBoardIdByCategoryDAO");
		return sqlsession.selectOne("board.checkBoardIdByCategory", category_id);
	}

	@Override
	public int getBoardMAXIDUnderCategory(int category_id) {
		logger.info("getBoardMAXIDUnderCategoryDAO");
		return sqlsession.selectOne("board.getBoardMAXIDUnderCategory",category_id);
	}

}

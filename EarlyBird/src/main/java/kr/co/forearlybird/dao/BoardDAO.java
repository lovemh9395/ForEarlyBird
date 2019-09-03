package kr.co.forearlybird.dao;

import java.util.List;
import java.util.Map;

import kr.co.forearlybird.domain.Board;

@SuppressWarnings("rawtypes")
public interface BoardDAO {

	int getNumberOfBoardUnderCategory(int category_id);

	List<Board> getBoardList();

	void makeBoard(Map newMap);

	int getBoardIdNumToName(Map map);

	int getBoardMAXID();

	int leaveBoard(int brd_id);

	int changeBoardVisibility(Map map);

	List<Integer> getBrd_idList(Map tmp);

	Map getLargeAndCategoryid(int brd_id);

	int checkBoardId(int category_id);

	int checkBoardIdByCategory(int category_id);

	int getBoardMAXIDUnderCategory(int category_id);

	Board getBoardParam(int brd_id);

	void updateBoard(Map newMap);
}

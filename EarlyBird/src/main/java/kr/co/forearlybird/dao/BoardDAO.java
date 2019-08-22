package kr.co.forearlybird.dao;

import java.util.List;
import java.util.Map;

import kr.co.forearlybird.domain.Board;

public interface BoardDAO {

	int getNumberOfBoardUnderCategory(int category_id);

	List<Board> getBoardList();

	@SuppressWarnings("rawtypes")
	void makeBoard(Map newMap);

	@SuppressWarnings("rawtypes")
	int getBoardIdNumToName(Map map);

	int getBoardMAXID();

	int leaveBoard(int brd_id);

	@SuppressWarnings("rawtypes")
	int changeBoardVisibility(Map map);

	@SuppressWarnings("rawtypes")
	List<Integer> getBrd_idList(Map tmp);

	@SuppressWarnings("rawtypes")
	Map getLargeAndCategoryid(int brd_id);
}

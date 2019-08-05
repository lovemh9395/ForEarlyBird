package kr.co.forearlybird.dao;

import java.util.List;

import kr.co.forearlybird.domain.Board;

public interface BoardDAO {

	int getNumberOfBoardUnderCategory(int category_id);

	List<Board> getBoardList();

}

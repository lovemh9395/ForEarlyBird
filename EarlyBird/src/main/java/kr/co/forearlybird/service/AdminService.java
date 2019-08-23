package kr.co.forearlybird.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import kr.co.forearlybird.domain.A_postListDTO;
import kr.co.forearlybird.domain.Post;

public interface AdminService {

	// 대분류 카테고리 리스트 불러오기
	@SuppressWarnings("rawtypes")
	List<Map> largeCategoryList() throws Exception;

	// 소분류 카테고리 리스트 불러오기
	@SuppressWarnings("rawtypes")
	List<Map> CategoryList();

	// 대분류 카테고리 만들기
	int makeLargeCategory(String large_name);

	// 소분류 카테고리 만들기
	int makeCategory(int large_id, String category_name);

	int leaveLargeCategory(int large_id);

	int leaveCategory(int category_id);

	@SuppressWarnings("rawtypes")
	List<Map> getBoardList();

	List<String> getAdminNickname(int brd_id);

	@SuppressWarnings("rawtypes")
	List<Map> getMemberListForBoardAdmin();

	@SuppressWarnings("rawtypes")
	int updateAdmin(Map map);

	int checkAdminId(int brd_id, String mem_userid);

	@SuppressWarnings("rawtypes")
	List<Map> searchMembersForBoardAdmin(Map map);

	@SuppressWarnings("rawtypes")
	void makeBoard(Map map);

	@SuppressWarnings("rawtypes")
	List<Map> CategoryList(int large_id);

	int leaveBoard(int brd_id);

	int changeBoardVisibility(int brd_id, int brd_exposure);

	@SuppressWarnings("rawtypes")
	List<Post> searchPostToBoard(Map map) throws Exception;

	@SuppressWarnings("rawtypes")
	List<A_postListDTO> ListPostToBoard(Map map) throws ParseException;

	@SuppressWarnings("rawtypes")
	void deletePostToBoard(Map map);

	@SuppressWarnings("rawtypes")
	void reViewPostToBoard(Map map);

	@SuppressWarnings("rawtypes")
	void updatePostToBoard(Map map); 
}

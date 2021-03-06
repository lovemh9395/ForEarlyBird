package kr.co.forearlybird.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import kr.co.forearlybird.domain.A_postListDTO;
import kr.co.forearlybird.domain.Content;
import kr.co.forearlybird.domain.Post;

@SuppressWarnings("rawtypes")
public interface AdminService {

	// 대분류 카테고리 리스트 불러오기
	List<Map> largeCategoryList() throws Exception;

	// 소분류 카테고리 리스트 불러오기
	List<Map> CategoryList();

	// 대분류 카테고리 만들기
	int makeLargeCategory(String large_name);

	// 소분류 카테고리 만들기
	int makeCategory(int large_id, String category_name);

	int leaveLargeCategory(int large_id);

	int leaveCategory(int category_id);

	List<Map> getBoardList();

	List<String> getAdminNickname(int brd_id);

	List<Map> getMemberListForBoardAdmin();

	int updateAdmin(Map map);

	int checkAdminId(int brd_id, String mem_userid);

	List<Map> searchMembersForBoardAdmin(Map map);

	void makeBoard(Map map);

	List<Map> CategoryList(int large_id);

	int leaveBoard(int brd_id);

	int changeBoardVisibility(int brd_id, int brd_exposure);

	List<Post> searchPostToBoard(Map map) throws Exception;

	List<A_postListDTO> ListPostToBoard(Map map) throws ParseException;

	void deletePostToBoard(Map map);

	void reViewPostToBoard(Map map);

	void updatePostToBoard(Map map);

	List<A_postListDTO> getNoticeListFromBoard() throws ParseException;

	void makeNotice(Post post);

	Post getPost(int post_id);

	void updateNotice(Post post);

	List<Map> getMemberList();

	List<Map> searchMemberList(Map map);

	List<Map> getAdminList();

	List<Map> searchAdminCandidateList(Map map);

	void deleteMembers(Map map);

	void releaseMembers(Map map) throws Exception;

	void banMembers(Map map);

	List<Map> getBanMemberList();

	String getNickname(String mem_userid);

	List<A_postListDTO> getPostListFromWriter(String mem_userid) throws Exception;

	List<Map> getRplListFromWriter(String mem_userid) throws Exception;

	int getPostNumWritenBy(String mem_userid);

	int getReplyNumWritenBy(String mem_userid);

	void memberAuthUpdate(List checklist, List memberAuthList);

	int getPostNumWritenBy(Map map);

	List<A_postListDTO> getPostListFromWriter(Map map) throws Exception;

	int getReplyNumWritenBy(Map map);

	List<Map> getRplListFromWriter(Map map) throws Exception;

	void updatePost(Map map);

	void updateReply(Map map);

	void deleteAdmin(Map map);

	Map getBoardParam(int brd_id);

	void updateBoard(Map map);

	List<Content> getContentsList();

	void deleteContents(List checklist, List dellist);

	Content getContent(int cnt_id);
}

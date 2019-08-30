package kr.co.forearlybird.dao;

import java.util.List;
import java.util.Map;

import kr.co.forearlybird.domain.Post;

public interface PostDAO {
	public List<Post> P_list();

	public List<Post> listAll() throws Exception;
	/* public List<BoardVO> listAll() throws Exception; */

	// 게시판 페이징
	public List<Post> listPage(int page) throws Exception;
	/* public List<BoardVO> listPage(int page) throws Exception; */

	// 게시판 페이징용 Criteria
	@SuppressWarnings("rawtypes")
	public List<Post> listCriteria(Map map) throws Exception;
	
	// 토탈카운트를 반환?
	@SuppressWarnings("rawtypes")
	public int countPaging(Map map) throws Exception;


	public Post P_detail(int post_id);

	@SuppressWarnings("rawtypes")
	public void P_make(Map map);

	public void P_delete(int i);

	@SuppressWarnings("rawtypes")
	public void P_update(Map map);

	public int P_recommand(int post_id);
	
	public int updateHit(int post_id);
	
	@SuppressWarnings("rawtypes")
	public List<Post> getPostList2(Map tmp) throws Exception;

	@SuppressWarnings("rawtypes")
	public List<Post> getPostList(Map map);

	public int getNewPostNumUnderBoard(int brd_id);

	public int getAllPostNumUnderBoard(int brd_id);

	public void P_reView(int parseInt);

	public void changeParamNotice(int post_id, int post_del);

	public List<Post> getNoticeList();

	public void P_make(Post post);

	public void P_update(Post post);

	public void changeParamDelete(int post_id, int post_delete);

	public void changeParamDrop(int post_id);
}

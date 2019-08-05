package kr.co.forearlybird.dao;

import java.util.List;
import java.util.Map;

import kr.co.forearlybird.domain.Post;
import kr.co.forearlybird.paging.Criteria;

public interface PostDAO {
	public List<Post> P_list();

	public List<Post> listAll() throws Exception;
	/* public List<BoardVO> listAll() throws Exception; */

	// 게시판 페이징
	public List<Post> listPage(int page) throws Exception;
	/* public List<BoardVO> listPage(int page) throws Exception; */

	// 게시판 페이징용 Criteria
	public List<Post> listCriteria(Criteria cri) throws Exception;

	// 토탈카운트를 반환?
	public int countPaging(Criteria cri) throws Exception;

	public Post P_detail(int post_id);

	public void P_make(Map map);

	public void P_delete(String post_id);

	public void P_update(Map map);

	public int P_recommand(int post_id);
	
	public int updateHit(int post_id);
}

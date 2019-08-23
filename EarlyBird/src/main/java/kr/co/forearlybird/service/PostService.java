package kr.co.forearlybird.service;

import java.util.List;
import java.util.Map;

import kr.co.forearlybird.domain.Post;
import kr.co.forearlybird.domain.Reply;
import kr.co.forearlybird.paging.Criteria;

public interface PostService {
	public Post P_detail(int post_id);
	
	public List<Post> P_list();

	public List<Post> listAll() throws Exception;

	public List<Post> listCriteria(Map map) throws Exception;
	
	public List<Reply> replyListCriteria(Map map) throws Exception;
	
	public int listCountCriteria(Map map) throws Exception;
	
	public int replyListCountCriteria(Map map) throws Exception;
	
	@SuppressWarnings("rawtypes")
	public void P_make(Map map);
	
	public void P_delete(String post_id);
	
	@SuppressWarnings("rawtypes")
	public void P_update(Map map);
	
	public int updateHit(int post_id);

	public int P_recommand(int post_id);
	
	public List<Reply> R_list(int post_id);
}

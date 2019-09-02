package kr.co.forearlybird.dao;

import java.util.List;
import java.util.Map;

import kr.co.forearlybird.domain.Reply;

public interface ReplyDAO {
	@SuppressWarnings("rawtypes")
	public int R_make(Map map);
	
	public int R_delete(int rpl_id);
	
	public List<Reply> R_list(int post_id);
	
	@SuppressWarnings("rawtypes")
	public List<Reply> replylistCriteria(Map map) throws Exception;
	
	@SuppressWarnings("rawtypes")
	public int replyCountPaging(Map map) throws Exception;
}

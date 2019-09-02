package kr.co.forearlybird.dao;

import java.util.List;
import java.util.Map;

import kr.co.forearlybird.domain.Reply;

@SuppressWarnings("rawtypes")
public interface ReplyDAO {
	public int R_make(Map map);
	
	public int R_delete(int rpl_id);
	
	public List<Reply> R_list(int post_id);
	
	public List<Reply> replylistCriteria(Map map) throws Exception;
	
	public int replyCountPaging(Map map) throws Exception;

	public List<Reply> getReplyList(String mem_userid);

	public int getReplyNumWritenBy(String mem_userid);

	public int getReplyNumWritenBy(Map map);

	public List<Reply> getReplyList(String mem_userid, int rpl_del);

	public void updateReplyDel(Map tmp);
}

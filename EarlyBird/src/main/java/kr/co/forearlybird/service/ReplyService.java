package kr.co.forearlybird.service;

import java.util.List;
import java.util.Map;

import kr.co.forearlybird.domain.Reply;

@SuppressWarnings("rawtypes")
public interface ReplyService {
	
	public int R_make(Map map);
	
	public int R_delete(int rpl_id);

	public List<Reply> replyListCriteria(Map map) throws Exception;

	public int replyListCountCriteria(Map map) throws Exception;
	
	public List<Reply> R_list(int post_id);
}

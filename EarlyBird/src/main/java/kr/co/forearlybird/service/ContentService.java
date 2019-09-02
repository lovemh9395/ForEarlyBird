package kr.co.forearlybird.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import kr.co.forearlybird.domain.Content;

public interface ContentService {

	public List<Content> Main_C_list();
	
	public List<Content> C_list_M(int brd_id);
	
	public int C_recommand(int cnt_id);
	
	public int C_view(int cnt_id);
	
	public String C_connectlink(int connectlink);
	
	@SuppressWarnings("rawtypes")
	public List<Content> menu_btn(Map map);
	
	public Map C_share_make(Map postmap) throws Exception;
	
	public List<Content> C_share_list(int brd_id);
}

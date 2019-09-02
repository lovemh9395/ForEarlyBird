package kr.co.forearlybird.service;

import java.util.List;
import java.util.Map;

import kr.co.forearlybird.domain.Content;

public interface ContentService {

	public List<Content> Main_C_list();
	
	public List<Content> C_list_M(int brd_id);
	
	public int C_recommand(int cnt_id);
	
	public int C_view(int cnt_id);
	
	public String C_connectlink(int connectlink);
	
	@SuppressWarnings("rawtypes")
	public List<Content> menu_btn(Map map);
}

package kr.co.forearlybird.dao;

import java.util.List;
import java.util.Map;

import kr.co.forearlybird.domain.Content;

@SuppressWarnings("rawtypes")
public interface ContentDAO {

	public List<Content> Main_C_list();
	
	public List<Content> C_list_M(int idx);
	
	public int C_recommand(int cnt_id);
	
	public int C_view(int cnt_id);
	
	public String C_connectlink(int connectlink);
	
	public List<Content> menu_btn(Map map);
	
	public int C_share_make(Map middlemap);
	
	public List<Content> C_share_list(int brd_id);

	public void deleteContents(Map tmp);

	public Content getContent(int cnt_id);

	public void makeContent(Map map);
	
}

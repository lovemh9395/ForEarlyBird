package kr.co.forearlybird.dao;

import java.util.List;
import java.util.Map;

import kr.co.forearlybird.domain.Member;
import kr.co.forearlybird.domain.Post;
import kr.co.forearlybird.domain.Reply;

public interface MemberDAO  {
	@SuppressWarnings("rawtypes")
	public Map login(Map<String, Object> map) throws Exception;
	
	public int make(Member member);
	
	public Member detail(String id);
	
	@SuppressWarnings("rawtypes")
	public Map update(Map<String, Object> map);
	
	public int delete(String id);
	
	@SuppressWarnings("rawtypes")
	public int profile(Map middlemap);
	
	public String searchID(Member member);
	
	public void insertUser(Member vo) throws Exception;
	
	public void createAuthKey(String user_email, String user_authcode) throws Exception;
	
	public void userAuth(String user_email) throws Exception;
	
	public void searchPWD(Member vo, String mem_password) throws Exception;

	@SuppressWarnings("rawtypes")
	public String getrawPw(Map map);
	
	//내 글 보기 페이징 및 보여주기
	@SuppressWarnings("rawtypes")
	public List<Post> listCriteria(Map map) throws Exception;
	
	@SuppressWarnings("rawtypes")
	public int countPaging(Map map) throws Exception;
	
	//내 댓글 보기 페이징 및 보여주기
	@SuppressWarnings("rawtypes")
	public List<Reply> myreplylistCriteria(Map map) throws Exception;
	
	@SuppressWarnings("rawtypes")
	public int myreplycountPaging(Map map) throws Exception;
	
	public void mem_logintime(String id);
	
	public int CheckId(String formId) throws Exception;
	
	@SuppressWarnings("rawtypes")
	public int CheckPass(Map map);
}

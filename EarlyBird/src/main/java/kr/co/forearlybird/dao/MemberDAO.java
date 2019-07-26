package kr.co.forearlybird.dao;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import kr.co.forearlybird.domain.Member;

public interface MemberDAO  {
	public Map login(Map<String, Object> map) throws Exception;
	
	public int make(Member member);
	
	public Member detail(String id);
	
	public Map update(Map<String, Object> map);
	
	public int delete(String id);
	
	public int profile(Map middlemap);
	
	public String searchID(Member member);
	
	public void insertUser(Member vo) throws Exception;
	
	public void createAuthKey(String user_email, String user_authcode) throws Exception;
	
	public void userAuth(String user_email) throws Exception;
	
	public void searchPWD(Member vo, String mem_password) throws Exception;
}

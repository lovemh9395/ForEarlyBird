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
}

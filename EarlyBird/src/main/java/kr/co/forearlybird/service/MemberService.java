package kr.co.forearlybird.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface MemberService {
	//로그인
	@SuppressWarnings("rawtypes")
	public Map M_login(HttpServletRequest request) throws Exception;
}

package kr.co.forearlybird.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import kr.co.forearlybird.domain.Member;

public interface MemberService {
	// 로그인을 처리하기 위한 메소드
	public Map login(HttpServletRequest request) throws Exception;

	// 전체 목록을 가져오는 메소드
	public List<Member> list();

	// 회원가입을 위한 메소드
	public int make(HttpServletRequest request);

	// 상세보기를 위한 메소드
	public Member detail(String id);

	// 삭제를 하기 위한 메소드
	public int delete(String id);

	// 수정을 하기 위한 메소드

	public Map update(Map<String, Object> map);
}

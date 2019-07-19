package kr.co.forearlybird.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import kr.co.forearlybird.domain.Member;

public interface MemberService {
	// 로그인을 처리하기 위한 메소드
		public Map login(HttpServletRequest request);

		// 전체 목록을 가져오는 메소드
		public List<Member> list();
		
		// 추가를 위한 메소드
		public int register(HttpServletRequest request);
		
		// 상세보기를 위한 메소드
		public Member detail(int bno);
		
		// 삭제를 하기 위한 메소드
		public int delete(int bno);
		
		// 수정을 하기 위한 메소드
		public int update(int bno);
}

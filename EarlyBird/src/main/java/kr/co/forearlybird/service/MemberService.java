package kr.co.forearlybird.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.co.forearlybird.domain.Member;
import kr.co.forearlybird.domain.Post;
import kr.co.forearlybird.domain.Reply;

@SuppressWarnings("rawtypes")
public interface MemberService {
	// 로그인을 처리하기 위한 메소드
	public Map login(HttpServletRequest request) throws Exception;

	// 전체 목록을 가져오는 메소드
	public List<Member> list();

	// 상세보기를 위한 메소드
	public Member detail(String id);

	// 삭제를 하기 위한 메소드
	public int delete(String id);

	// 수정을 하기 위한 메소드
	public Map update(Map<String, Object> map);

	public Map restore(Map postmap,HttpSession session) throws Exception;
	
	public String searchID(HttpServletRequest request);

	// 회원가입을 위한 메소드
	void create(Member vo) throws Exception;
	
	public void userAuth(String userEmail) throws Exception;
	
	void serachPWD(Member vo) throws Exception;

	//내 글 보기 페이징 처리 및 보여주기 다하자
	public List<Post> listCriteria(Map map) throws Exception;
	
	public int listCountCriteria(Map map) throws Exception;
	
	//내 글 보기 페이징 처리 및 보여주기 다하자
	public List<Reply> myreplylistCriteria(Map map) throws Exception;
	
	public int myreplylistCountCriteria(Map map) throws Exception;
	
	public int CheckId(String formId) throws Exception;
	
	public int CheckPass(Map map);
	
	public int CheckLevel(String formId);
	
	public int searchPWDcheck(Map map);
}

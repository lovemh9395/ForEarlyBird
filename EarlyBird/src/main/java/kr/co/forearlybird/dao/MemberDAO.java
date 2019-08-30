package kr.co.forearlybird.dao;

import java.util.List;
import java.util.Map;

import kr.co.forearlybird.domain.Member;
import kr.co.forearlybird.domain.Post;
import kr.co.forearlybird.domain.Reply;

@SuppressWarnings("rawtypes")
public interface MemberDAO  {
	public Map login(Map<String, Object> map) throws Exception;
	
	public int make(Member member);
	
	public Member detail(String mem_userid);
	
	public Map update(Map<String, Object> map);
	
	public int delete(String mem_userid);
	
	public void release(String mem_userid);
	
	public int profile(Map middlemap);
	
	public String searchID(Member member);
	
	public void insertUser(Member vo) throws Exception;
	
	public void createAuthKey(String user_email, String user_authcode) throws Exception;
	
	public void userAuth(String user_email) throws Exception;
	
	public void searchPWD(Member vo, String mem_password) throws Exception;

	public String getrawPw(Map map);
	
	//내 글 보기 페이징 및 보여주기
	public List<Post> listCriteria(Map map) throws Exception;
	
	public int countPaging(Map map) throws Exception;
	
	//내 댓글 보기 페이징 및 보여주기
	public List<Reply> myreplylistCriteria(Map map) throws Exception;
	
	public int myreplycountPaging(Map map) throws Exception;
	
	public void mem_logintime(String mem_userid);
	
	public int CheckId(String formId) throws Exception;
	
	public int CheckPass(Map map);
	
	public String getMemberNickName(String mem_userid);

	public List<Map> getMemberList(Map map);

	public List<Map> getMemberListByMinLevel(int minlevel);

	public List getMemberListLikesThisName(String mem_nickname);

	public List<Member> getMemberList();

	public List<Member> searchMemberList(Map map);

	public List<Member> getAdminList();

	public List<Member> searchAdminCandidateList(Map map);

	public void ban(String mem_userid);

	public void memberAuthUpdate(Map tmp);

	public List<Member> getBanMemberList();
}

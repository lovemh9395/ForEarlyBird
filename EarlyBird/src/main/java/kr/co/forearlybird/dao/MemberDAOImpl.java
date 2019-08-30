package kr.co.forearlybird.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import kr.co.forearlybird.controller.MemberController;
import kr.co.forearlybird.domain.Member;
import kr.co.forearlybird.domain.Post;
import kr.co.forearlybird.domain.Reply;

@SuppressWarnings("rawtypes")
@Repository
public class MemberDAOImpl implements MemberDAO {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	@Autowired
	private SqlSession sqlSession;
	
	@Inject
	PasswordEncoder passwordEncoder;

	@Override
	public Map login(Map<String, Object> map) throws Exception {
		logger.info("로그인 DAO");
		Map result = sqlSession.selectOne("member.login", map);
		return result;
	}

	@Override
	public int make(Member member) {
		logger.info("회원가입 DAO");
		return sqlSession.insert("member.make", member);
	}

	@Override
	public Member detail(String id) {
		logger.info("내 정보 보기 dao");
		return sqlSession.selectOne("member.detail", id);
	}

	@Override
	public Map update(Map<String, Object> map) {
		logger.info("정보수정 dao");
		return sqlSession.selectOne("member.update", map);
	}

	@Override
	public int delete(String mem_userid) {
		logger.info("회원탈퇴 dao");
		return sqlSession.update("member.delete", mem_userid);
	}

	@Override
	public int profile(Map middlemap) {
		logger.info("프로필업로드 dao");
		logger.info(middlemap.toString());
		System.out.println(middlemap.get("url"));
		return sqlSession.update("member.profileupdate", middlemap);
	}

	@Override
	public String searchID(Member member) {
		logger.info("PW 찾기 dao");
		return sqlSession.selectOne("member.searchID", member);
	}

	@Override
	public void insertUser(Member vo) throws Exception {
		logger.info("dao " + vo);
		System.out.println("DAO 로그 : 회원가입 중");
		sqlSession.insert("member.insertUser", vo);
//			System.out.println(vo.toString());
	}

	@Override
	public void createAuthKey(String user_email, String user_authcode) throws Exception {
		Member vo = new Member();
		vo.setMem_profile_content(user_authcode);
		vo.setMem_adminmemo(user_email);
		sqlSession.selectOne("member.createAuthKey", vo);
	}

	@Override
	public void userAuth(String user_email) throws Exception {
		sqlSession.update("member.userAuth", user_email);
	}

	@Override
	public void searchPWD(Member vo, String mem_password) throws Exception {
		System.out.println("++++++++++++++++++++++++++" + vo + "-------------------" + mem_password);
		String encPassword = passwordEncoder.encode(mem_password);
		vo.setMem_password(encPassword);
		sqlSession.selectOne("member.searchPWD", vo);
	}

	@Override
	public String getrawPw(Map map) {
		return sqlSession.selectOne("member.rawPw", map);
	}

	// Criteria 를 적용한 게시판 페이징 조회
	@Override
	public List<Post> listCriteria(Map map) throws Exception {
		logger.info("내 글 보기 Post DAO");
		logger.info(map.toString());
		return sqlSession.selectList("member.mypostlistCriteria", map);
	}

	@Override
	public int countPaging(Map map) throws Exception {
		logger.info("내 글 보기 Count paging List DAO");
		logger.info(map.toString());
		return sqlSession.selectOne("member.mypostcountPaging", map);
	}

	// Criteria 를 적용한 게시판 페이징 조회
	@Override
	public List<Reply> myreplylistCriteria(Map map) throws Exception {
		logger.info("내 댓글 보기 Post DAO");
		logger.info(map.toString());
		return sqlSession.selectList("member.myreplylistCriteria", map);
	}

	@Override
	public int myreplycountPaging(Map map) throws Exception {
		logger.info("내 댓글 보기 Count paging List DAO");
		logger.info(map.toString());
		return sqlSession.selectOne("member.myreplyCountPaging", map);
	}

	// 마지막 로그인 시간 업데이트
	@Override
	public void mem_logintime(String id) {
		sqlSession.update("member.login_time", id);
	}

	@Override
	public int CheckId(String formId) throws Exception {
		return sqlSession.selectOne("member.CheckId",formId);
	}

	@Override
	public int CheckPass(Map map) {
		return sqlSession.selectOne("member.CheckPass",map);
	}
	
	@Override
	public String getMemberNickName(String mem_userid) {
		logger.info("getMemberNickNameDAO");
							  
		return sqlSession.selectOne("member.getMemberNickName", mem_userid);
	}

	@Override
	public List<Map> getMemberListByMinLevel(int mem_level) {
		logger.info("getMemberListByMinLevelDAO");
							  
		return sqlSession.selectList("member.getMemberListByMinLevel", mem_level);
	}

	@Override
	public List<Member> getMemberList() {
		logger.info("getMemberListDAO");
		return sqlSession.selectList("member.getMemberList");
	}
	
	@Override
	public List<Map> getMemberList(Map map) {
		logger.info("getMemberListDAO");
		if (map.get("index").equals("mem_userid")) {
			return sqlSession.selectList("member.getMemberListById", map);
		} else if (map.get("index").equals("mem_level")) {
			return sqlSession.selectList("member.getMemberListBylevel", map);
		} else {
			return sqlSession.selectList("member.getMemberListByNickname", map);
		}
	}
	
	@Override
	public List getMemberListLikesThisName(String keyword) {
		logger.info("getMemberListLikesThisNameDAO");
		return sqlSession.selectList("member.getMemberListLikesThisName",keyword);
	}

	@Override
	public List<Member> searchMemberList(Map map) {
		logger.info("searchMemberListDAO");
		return sqlSession.selectList("member.searchMemberList",map);
	}

	@Override
	public List<Member> getAdminList() {
		logger.info("getAdminListDAO");
		return sqlSession.selectList("member.getAdminList");
	}

	@Override
	public List<Member> searchAdminCandidateList(Map map) {
		logger.info("searchAdminCandidateListDAO");
		return sqlSession.selectList("member.searchForAdminList",map);
	}


	@Override
	public void release(String mem_userid) {
		logger.info("releaseDAO");
		sqlSession.update("member.release",mem_userid);
	}

	@Override
	public void ban(String mem_userid) {
		logger.info("banDAO");
		sqlSession.update("member.ban",mem_userid);
	}

	@Override
	public void memberAuthUpdate(Map map) {
		logger.info("memberAuthUpdateDAO");
		sqlSession.update("member.memberAuthUpdate",map);
	}

	@Override
	public List<Member> getBanMemberList() {
		logger.info("getBanMemberListDAO");
		return sqlSession.selectList("member.getBanMemberList");
	}
}

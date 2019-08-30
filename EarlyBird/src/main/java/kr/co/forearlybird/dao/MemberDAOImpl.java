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

@Repository
public class MemberDAOImpl implements MemberDAO {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	@Autowired
	private SqlSession sqlSession;
	
	@Inject
	PasswordEncoder passwordEncoder;

	@SuppressWarnings("rawtypes")
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
		logger.info("내 정보 보기 DAO");
		return sqlSession.selectOne("member.detail", id);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map update(Map<String, Object> map) {
		logger.info("정보수정 DAO");
		return sqlSession.selectOne("member.update", map);
	}

	@Override
	public int delete(String id) {
		logger.info("회원탈퇴 DAO");
		return sqlSession.insert("member.delete", id);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int profile(Map middlemap) {
		logger.info("프로필업로드 DAO");
		System.out.println(middlemap.get("url"));
		return sqlSession.update("member.profileupdate", middlemap);
	}

	@Override
	public String searchID(Member member) {
		logger.info("비밀번호 찾기 DAO");
		return sqlSession.selectOne("member.searchID", member);
	}

	@Override
	public void insertUser(Member vo) throws Exception {
		logger.info("회원가입 DAO");
		sqlSession.insert("member.insertUser", vo);
	}

	@Override
	public void createAuthKey(String user_email, String user_authcode) throws Exception {
		logger.info("인증키 생성 DAO");
		Member vo = new Member();
		vo.setMem_profile_content(user_authcode);
		vo.setMem_adminmemo(user_email);
		sqlSession.selectOne("member.createAuthKey", vo);
	}

	@Override
	public void userAuth(String user_email) throws Exception {
		logger.info("인증키 보내기 DAO");
		sqlSession.update("member.userAuth", user_email);
	}

	@Override
	public void searchPWD(Member vo, String mem_password) throws Exception {
		logger.info("비밀번호 찾기 DAO");
		String encPassword = passwordEncoder.encode(mem_password);
		vo.setMem_password(encPassword);
		sqlSession.selectOne("member.searchPWD", vo);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String getrawPw(Map map) {
		logger.info("비밀번호 암호화 비교 DAO");
		return sqlSession.selectOne("member.rawPw", map);
	}

	// Criteria 를 적용한 게시판 페이징 조회
	@SuppressWarnings("rawtypes")
	@Override
	public List<Post> listCriteria(Map map) throws Exception {
		logger.info("내 글 보기 DAO");
		return sqlSession.selectList("member.mypostlistCriteria", map);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int countPaging(Map map) throws Exception {
		logger.info("내 글 보기 페이징 처리 DAO");
		return sqlSession.selectOne("member.mypostcountPaging", map);
	}

	// Criteria 를 적용한 게시판 페이징 조회
	@SuppressWarnings("rawtypes")
	@Override
	public List<Reply> myreplylistCriteria(Map map) throws Exception {
		logger.info("내 댓글 보기 DAO");
		return sqlSession.selectList("member.myreplylistCriteria", map);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int myreplycountPaging(Map map) throws Exception {
		logger.info("내 댓글 보기 페이징  DAO");
		return sqlSession.selectOne("member.myreplyCountPaging", map);
	}

	@Override
	public void mem_logintime(String id) {
		logger.info("마지막 로그인 시간 업데이트 DAO");
		sqlSession.update("member.login_time", id);
	}

	@Override
	public int CheckId(String formId) throws Exception {
		logger.info("로그인 아이디 체크 DAO");
		return sqlSession.selectOne("member.CheckId",formId);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int CheckPass(Map map) {
		logger.info("로그인 비밀번호 체크 DAO");
		return sqlSession.selectOne("member.CheckPass",map);
	}
	
	@Override
	public String getMemberNickName(String mem_userid) {
		logger.info("getMemberNickNameDAO");
							  
		return sqlSession.selectOne("member.getMemberNickName", mem_userid);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> getMemberListByMinLevel(int mem_level) {
		logger.info("getMemberListByMinLevelDAO");
							  
		return sqlSession.selectList("member.getMemberListByMinLevel", mem_level);
	}

													 
	@SuppressWarnings("rawtypes")
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
	
	@SuppressWarnings("rawtypes")
	@Override
	public List getMemberListLikesThisName(String keyword) {
		logger.info("getMemberListLikesThisNameDAO");
		return sqlSession.selectList("member.getMemberListLikesThisName",keyword);
	}
	
	public int CheckLevel(Map map) {
		return sqlSession.selectOne("member.CheckLevel",map);
	}

	@Override
	public int searchPWDcheck(Map map) {
		return sqlSession.selectOne("member.searchPWDcheck",map);
	}

}

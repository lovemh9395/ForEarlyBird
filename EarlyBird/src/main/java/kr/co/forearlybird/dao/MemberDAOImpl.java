package kr.co.forearlybird.dao;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.forearlybird.controller.MemberController;
import kr.co.forearlybird.domain.Member;

@Repository
public class MemberDAOImpl implements MemberDAO {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	@Autowired
	private SqlSession sqlSession;
	@Override
	public Map login(Map<String, Object> map) throws Exception {
		logger.info("로그인 DAO");
		Map result = sqlSession.selectOne("member.login",map);
		return result;
	}
	
	@Override
	public int make(Member member) {
		logger.info("회원가입 DAO");
		// TODO Auto-generated method stub
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
	public int delete(String id) {
		logger.info("회원탈퇴 dao");
		return sqlSession.insert("member.delete", id);
	}
}

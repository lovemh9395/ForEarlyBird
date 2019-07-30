package kr.co.forearlybird.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.forearlybird.domain.Member;

@Repository
public class MemberDAOImpl implements MemberDAO {
	private static final Logger logger = LoggerFactory.getLogger(MemberDAOImpl.class);
	
	@Autowired
	SqlSession sqlsession;

	@SuppressWarnings("rawtypes")
	@Override
	public Member loginMember(Map map) {
		logger.info("makeMemberDAO");
		return sqlsession.selectOne("member.makeMember",map);
	}

}

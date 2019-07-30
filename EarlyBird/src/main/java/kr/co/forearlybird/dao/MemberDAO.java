package kr.co.forearlybird.dao;

import java.util.Map;

import kr.co.forearlybird.domain.Member;

public interface MemberDAO {

	@SuppressWarnings("rawtypes")
	Member loginMember(Map map);

}

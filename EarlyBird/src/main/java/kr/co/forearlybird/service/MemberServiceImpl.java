package kr.co.forearlybird.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import kr.co.forearlybird.dao.MemberDAO;
import kr.co.forearlybird.domain.Member;

@Service(value = "MemberService")
public class MemberServiceImpl implements MemberService {
	private static final Logger logger = LoggerFactory.getLogger(MemberDAO.class);
	@Override
	public Map login(HttpServletRequest request) {
		logger.info("로그인 service");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("pw", pw);
		
		return null;
	}

	@Override
	public List<Member> list() {
		return null;
	}

	@Override
	public int register(HttpServletRequest request) {
		return 0;
	}

	@Override
	public Member detail(int bno) {
		return null;
	}

	@Override
	public int delete(int bno) {
		return 0;
	}

	@Override
	public int update(int bno) {
		return 0;
	}

}

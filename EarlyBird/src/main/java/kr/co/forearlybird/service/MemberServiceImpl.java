package kr.co.forearlybird.service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.forearlybird.dao.MemberDAO;
import kr.co.forearlybird.domain.Member;

@Service
public class MemberServiceImpl implements MemberService {
	private static final Logger logger = LoggerFactory.getLogger(MemberDAO.class);
	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public Map login(HttpServletRequest request) throws Exception {
		logger.info("로그인 service");
		String id = request.getParameter("email");
		String pw = request.getParameter("password");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("pw", pw);
		Map temp = memberDAO.login(map);
		if(temp == null) {
			return null;
		}
		Map result = new HashMap();
		result.put("id", temp.get("mem_userid"));
		result.put("pw", temp.get("mem_password"));
		result.put("name", temp.get("mem_username"));
		
		return result;
	}

	@Override
	public List<Member> list() {
		return null;
	}

	@Override
	public int make(HttpServletRequest request) {
		logger.info("회원가입 service");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Member member = new Member();
		member.setMem_userid(request.getParameter("makeemail"));
		member.setMem_password(request.getParameter("makepassword"));
		member.setMem_nickname(request.getParameter("makenickname"));
		member.setMem_phone(request.getParameter("maketel"));
		member.setMem_birthday(request.getParameter("makebirth"));
		member.setMem_username(request.getParameter("makename"));
		//클라이언트 아이피 주소 받기
		return memberDAO.make(member);
	}

	@Override
	public Member detail(String id) {
		logger.info("내 정보 보기 service");
		
		return memberDAO.detail(id);
	}

	@Override
	public int delete(String id) {
		logger.info("회원탈퇴 service");
		return memberDAO.delete(id);
	}

	@Override
	public Map update(Map<String, Object> map) {
		logger.info("정보수정 service");
		return memberDAO.update(map);
	}
}

package kr.co.forearlybird.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import kr.co.forearlybird.dao.MemberDAO;
import kr.co.forearlybird.domain.Member;

@Service
@Component
public class MemberServiceImpl implements MemberService {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

	@Autowired
	MemberDAO memberDAO;
	
	@SuppressWarnings("rawtypes")
	@Override
	public Map M_login(HttpServletRequest request) throws Exception {
		logger.info("login Service");
		String mem_id = request.getParameter("mem_id");
		String mem_password = request.getParameter("mem_password");
		
		Map<String, Object> tmp = new HashMap<>();
		tmp.put("mem_id",mem_id);
		tmp.put("mem_password",mem_password);
		
		Member member = memberDAO.loginMember(tmp);
		
		Map<String, Object> map = new HashMap<>();
		map.put("mem_userid", member.getMem_userid());
		map.put("mem_nickname", member.getMem_nickname());
		map.put("mem_username", member.getMem_username());
		map.put("mem_level", member.getMem_level());
		map.put("mem_phone", member.getMem_phone());
		map.put("mem_birthday", member.getMem_birthday());
		map.put("mem_photo", member.getMem_photo());
		map.put("mem_profile_content", member.getMem_profile_content());
		return map;
	}

}

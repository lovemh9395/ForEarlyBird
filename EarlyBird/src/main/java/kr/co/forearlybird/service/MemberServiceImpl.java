package kr.co.forearlybird.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class MemberServiceImpl implements MemberService {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

	@Override
	public Map M_login(HttpServletRequest request) throws Exception {
		logger.info("login Service");
		String id = request.getParameter("email");
		String pw = request.getParameter("password");

		Map<String, Object> map = new HashMap<>();
		
		if (!id.equals("admin")) {
			return map;
		}
		if (!pw.equals("123")) {
			return map;
		}
		map.put("id", id);
		map.put("pw", pw);
		logger.info(map.toString());
		return map;
	}

}

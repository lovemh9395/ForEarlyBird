package kr.co.forearlybird.service;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

	@Override
	public int M_login(Map<String, Object> map) throws Exception {
		String id = (String) map.get("id");
		String pw = (String) map.get("pw");
		
		if (!id.equals("admin")) {
			return -1;
		}
		if (!pw.equals("admin")) {
			return 0;
		}
		return 1;
	}

}

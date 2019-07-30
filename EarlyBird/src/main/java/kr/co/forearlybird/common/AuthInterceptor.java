package kr.co.forearlybird.common;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AuthInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

	// controller에 요청을 보내기 전에 호출되는 메소드
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 클라이언트에 부여한 세션을 가지고 온다
		HttpSession session = request.getSession();
		Map map = (Map) session.getAttribute("user");
		if ((Integer) map.get("mem_level") <= 8) {// 관리자가 아니라면
			response.sendRedirect("/"); // <-- 07.29 오후 2시
			return false;
		}
		logger.info("관리자명 :" + map.get("mem_userid").toString());
		return true;
	}

	// 예외나 오류 없이 controller가 작업을 마친 뒤 view를 부르기 전에 호출되는 메소드
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
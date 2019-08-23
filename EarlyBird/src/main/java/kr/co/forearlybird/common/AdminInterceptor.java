package kr.co.forearlybird.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AdminInterceptor implements HandlerInterceptor {

	//private static final Logger logger = LoggerFactory.getLogger(AdminInterceptor.class);

	// controller에 요청을 보내기 전에 호출되는 메소드
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean masterFlag = false;

		System.out.println("Login Interceptor");
		if (request.getSession().getAttribute("useridd") != null
				&& (Integer) request.getSession().getAttribute("mem_level") > 8) {
			// 세션 ID (SID)가 존재하고, 등급(SGEADE)이 3 이라면

			System.out.println("admin인증!!!"); // admin 인증
			masterFlag = true;

		} else { // 세션 ID가 존재치 않거나, 등급이 3이 아니라면

			System.out.println("admin 미인증!"); // admin 미인증
			response.sendRedirect(request.getContextPath() + "/");
			// 해당 페이지로 보내기
			masterFlag = false;
		}

		return masterFlag;
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
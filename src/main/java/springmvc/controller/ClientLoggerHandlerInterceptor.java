package springmvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;



public class ClientLoggerHandlerInterceptor extends HandlerInterceptorAdapter {

	private final Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//		String client = request.getHeader("User-Agent");
//		String clientLanguage = request.getHeader("Accept-Language");
//		logger.debug("--->  Client: {}", client);
//		logger.debug("--->  Request lanuage: {}", clientLanguage);
		return true;
	}
	

}

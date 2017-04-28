package com.aidansu.demo.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aidansu.demo.common.utils.SessionUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @className VisitorInterceptors.java
 * @classDescription 前端访问拦截器
 * @author AIDAN SU
 * @createTime 2017-3-3
 *
 */

public class VisitorInterceptor  implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e)throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView ) throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		if( SessionUtil.getUserFromSession(request) != null ){
			return true ;
		}else{
			//重定向到登录界面
			response.sendRedirect(request.getContextPath()+"/login");
			return false;
		}
		
	}

}

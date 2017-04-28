package com.aidansu.demo.common.utils;

import com.aidansu.demo.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/**
 * 
 * @className SessionUtil.java
 * @classDescription 用户会话工具
 * @author AIDAN SU
 * @createTime 2015-05-12
 *
 */
public class SessionUtil {
	
	public static final String USER = "user";

	/**
	 * 从会话中读取用户信息
	 * 
	 * @param request
	 * @return 用户对象
	 */
	public static User getUserFromSession(HttpServletRequest request ){
		HttpSession session = request.getSession();
		Object obj = session.getAttribute(USER);
		if( obj != null && obj instanceof User ){
			return (User)obj ;
		}
		return null;
	}
	
	
	/**
	 * 检查是否已登录
	 *
	 * @param request
	 * @return 返回true表示已登录，false表示未登录
	 */
	public static boolean isLogin( HttpServletRequest request ){
		if( getUserFromSession( request ) != null  ){
			return true ;
		}
		return false;
	}
	
	/**
	 * 用户登录
	 * 
	 * @param user 用户对象
	 * @param request
	 */
	public static void login( User user ,HttpServletRequest request){
		HttpSession session  = request.getSession();
		session.setAttribute(USER, user) ;
	}
	
	/**
	 * 用户登出
	 *
	 * @param request
	 */
	public static void logout(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.removeAttribute( USER );
		session.invalidate();
	}
	
}

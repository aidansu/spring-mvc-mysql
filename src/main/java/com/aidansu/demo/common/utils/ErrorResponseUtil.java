package com.aidansu.demo.common.utils;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

/**
 * @className ErrorResponseUtil.java
 * @classDescription 返回错误信息
 * @author AIDAN SU
 * @createTime 2017-3-2
 *
 */

public class ErrorResponseUtil {
	
	/**
	 * errcode:
	 * 
	 * -1：系统繁忙，请稍后再试
	 * 0：请求成功
	 * 40001：缺少参数
	 *
	 */

	/**
	 * 获取响应Map对象
	 *
	 * @param errcode
	 * @param errmsg
	 * @return String
	 */
	public static String setResponse(String errcode ,String errmsg){

		Map<String,String> response = new HashMap<String,String>();
		response.put("errcode", errcode) ;
		response.put("errmsg", errmsg) ;
		Gson gson = new Gson();
		return gson.toJson(response) ;
	}
}

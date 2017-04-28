package com.aidansu.demo.common.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @className EncryptUtil.java
 * @classDescription 加密工具
 * @author AIDAN SU
 * @createTime 2017-3-2
 *
 */

public class EncryptUtil {
	
	 /**
	  * SHA加密
	  * 
	  * @author AIDAN SU
	  * @param targetString 需加密的目标字符串
	  * @return sha加密后的字符串
	  * @throws NoSuchAlgorithmException
	  */
	 public static String encryptSHA( String targetString ) throws NoSuchAlgorithmException{
	        MessageDigest sha = MessageDigest.getInstance("SHA-1");  
	        sha.update( targetString.getBytes() );  
	        BigInteger result = new BigInteger(sha.digest());  
	        return result.toString();
	 }
	 
	 /**
	  * 获取字符串MD5
	  * 
	  * @author AIDAN SU
	  * @param source
	  * @return
	  */
	public static String getMD5(String source) {

		String s = null;
		// 用来将字节转换成 16 进制表示的字符
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] b = source.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(b);
			byte tmp[] = md.digest();
			char str[] = new char[16 * 2];
			int k = 0;
			for (int i = 0; i < 16; i++) {

				byte byte0 = tmp[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];

				str[k++] = hexDigits[byte0 & 0xf];
			}
			s = new String(str);

		} catch (Exception e) {
			//e.printStackTrace();
		}
		return s;
	}
	

}

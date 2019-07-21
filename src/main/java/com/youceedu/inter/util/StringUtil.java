package com.youceedu.inter.util;

public class StringUtil {
	public static String replaceStr(String sourceStr,String matcherStr,String replaceStr){
		
		try {
			
			//得到matcher左侧的字符串
			int index = sourceStr.indexOf(matcherStr);
			String beginStr = sourceStr.substring(0, index);
			
			//得到matcher右侧的字符串
			int matchLen = matcherStr.length();
			int souceLen = sourceStr.length();
			String endStr = sourceStr.substring(index+matchLen, souceLen);
			
			//重新进行拼接
			sourceStr = beginStr+replaceStr+endStr;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return sourceStr;
		
	}
}

package com.youceedu.inter.util;

public class StringUtil {
	public static String replaceStr(String sourceStr,String matcherStr,String replaceStr){
		
		try {
			
			//�õ�matcher�����ַ���
			int index = sourceStr.indexOf(matcherStr);
			String beginStr = sourceStr.substring(0, index);
			
			//�õ�matcher�Ҳ���ַ���
			int matchLen = matcherStr.length();
			int souceLen = sourceStr.length();
			String endStr = sourceStr.substring(index+matchLen, souceLen);
			
			//���½���ƴ��
			sourceStr = beginStr+replaceStr+endStr;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return sourceStr;
		
	}
}

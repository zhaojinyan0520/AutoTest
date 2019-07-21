package com.youceedu.inter.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSONPath;

import junit.framework.Assert;

public class PatternUtil {
	/**
	 * 创建正则表达式
	 */
	private static String compareResultRegex="([//$//.a-zA-Z0-9]+)=(//w)+";
	private static String depKeyRegex="([//A-Za-z0-9]+):([//$//.a-zA-Z0-9]+)";
	private static String reqDataRegex="([//A-Za-z0-9]+:[//$//.a-zA-Z0-9]+)";
	private static  Map<String,String> map = new HashMap<String, String>();
	/**
	 * 建立正则规范
	 */
	
	public static Matcher getmatcher(String regex,String data){
		Matcher matcher = null;
		try {
			Pattern pattern= Pattern.compile(regex);
			matcher = pattern.matcher(data);
		} catch (Exception e) {
		}
		return matcher;
		
	}
	
	/**
	 * 实际值和预期值做对比，打印测试报告
	 */
	
	public static void compareResultToReport(String expResult,String actResult){
		try {
			Matcher matcher = getmatcher(compareResultRegex, expResult);
			while (matcher.find()) {
				Assert.assertEquals(expResult, JSONPath.read(actResult, matcher.group(1).toString()),matcher.group(2));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	/**
	 * 实际值和预期值做对比测试结果入库
	 */
	public static int compareResultToDb(String expResult,String actResult){
		List<Integer> list = new ArrayList<Integer>();
		int flag = 0;
		try {
			Matcher matcher = getmatcher(compareResultRegex, expResult);
			
			while (matcher.find()) {
				int status = JSONPath.read(actResult, matcher.group(1).toString()).equals(matcher.group(2))?1:0;
				list.add(status);
				if (!list.contains(0)) {
					flag = 1;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return flag;
		
	}
	
	
	/**
	 * 存储结果
	 */
	
	public static void storeResultValue(String depKey,String actResult){
		try {
			Matcher matcher = getmatcher(depKeyRegex, depKey);
			while (matcher.find()) {
				String jsonPath = matcher.group(2);
				String value = JSONPath.read(actResult, jsonPath).toString();
				map.put(matcher.group(), value);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * 处理依赖
	 */
	
	public static void reqResultValue(String reqData){
		try {
			Matcher matcher = getmatcher(reqDataRegex, reqData);
			while (matcher.find()) {
			    String value = map.get(matcher.group());
				StringUtil.replaceStr(reqData, matcher.group(2), value);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	
	
	
	
}

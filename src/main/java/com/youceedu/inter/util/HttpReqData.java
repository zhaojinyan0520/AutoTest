package com.youceedu.inter.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpReqData {
	//实例化cookie对象
	private static BasicCookieStore cookieStore = new BasicCookieStore();
	
	//设置header和超时配置
	public static void getReqConfigValue(HttpRequestBase httpRequestBase){
		httpRequestBase.setHeader("", "");
		
		
		//设置请求超时配置
		RequestConfig requestConfig = RequestConfig.custom()
		.setConnectionRequestTimeout(2000)
		.build();
	}
	
	/**
	 * 创建get请求
	 */
	public static String sendGet(String url,String param){
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		String entityUtils = null;
		String reqData = url+"?"+param;
		try {
			//得到httpclients和get对象，并进行自动管理
			httpClient = HttpClients.custom()
					.setDefaultCookieStore(cookieStore)
					.build();
			HttpGet httpget = new HttpGet(reqData);
			
			//得到服务器返回值
			response = httpClient.execute(httpget);
			if (response.getStatusLine().getStatusCode()==HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				entityUtils = EntityUtils.toString(entity);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return entityUtils;
		
	}
	
	/**
	 * 创建post请求
	 */
	
	public static String senPost(String url,String param){
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		String result = null;
		try {
			httpClient = HttpClients.custom()
					.setDefaultCookieStore(cookieStore)
					.build();
			HttpPost httpPost = new HttpPost(url);
			
			//设置消息体
			StringEntity stringEntity = new StringEntity(param);
			 httpPost.setEntity(stringEntity);
			
			response = httpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode()==HttpStatus.SC_OK) {
				HttpEntity httpEntity = response.getEntity();
				result = EntityUtils.toString(httpEntity);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
		
	}
	
	

	 
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

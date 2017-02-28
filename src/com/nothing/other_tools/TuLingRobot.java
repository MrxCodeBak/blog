package com.nothing.other_tools;

import java.io.*;
import java.net.*;

public class TuLingRobot {
	
	/**
	 * 机器人类
	 * @param question
	 * @return
	 * @throws Exception
	 */
	public static String tuLingRobot(String question){
		StringBuffer sb = new StringBuffer(); 
		try {
			
			String APIKEY = "********"; 
			String INFO = URLEncoder.encode(question, "utf-8"); 
			String getURL = "http://www.tuling123.com/openapi/api?key=" + APIKEY + "&info=" + INFO; 
			URL getUrl = new URL(getURL); 
			HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection(); 
			connection.connect(); 
			
			// 取得输入流，并使用Reader读取 
			BufferedReader reader = new BufferedReader(new InputStreamReader( connection.getInputStream(), "utf-8"));
			String line = ""; 
			while ((line = reader.readLine()) != null) { 
				sb.append(line); 
			} 
			reader.close(); 
			// 断开连接 
			connection.disconnect(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	/**
	 * test
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception{
//		System.out.println(TuLingRobot.tuLingRobot("日！"));
	}
}

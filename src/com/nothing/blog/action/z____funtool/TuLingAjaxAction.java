package com.nothing.blog.action.z____funtool;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.nothing.other_tools.TuLingRobot;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 图灵机器人
 * @author: Nothing
 * @email: 761328790@qq.com
 * @company: Sichuan Agricultural University
 * @date 2015年8月15日
 */
public class TuLingAjaxAction extends ActionSupport{
	
	private static final long serialVersionUID = 785559563849863867L;

	/**
	 * 通用的json打印方法
	 * @param json
	 * @return 
	 * @throws Exception
	 */
	public String toPageJson(String json){
		try {
			//输出json数据到页面上
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().println(json);
			//打印测试json值
			System.out.println("json:  "+json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	/** 参数 */
	private String question;
	
	/**
	 * 图灵机器人方法
	 * @return
	 */
	public String tuLingRobot(){
		JSONObject json = new JSONObject();
		String answer = TuLingRobot.tuLingRobot(question);
		json.put("answer", answer);
		return toPageJson(json.toString());
	}

	/** setter and getter method */
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
}

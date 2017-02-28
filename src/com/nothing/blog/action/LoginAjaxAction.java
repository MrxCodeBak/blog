package com.nothing.blog.action;

import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.nothing.blog.service.BlogService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 用户异步登陆类
 * @author: Nothing
 * @email: 761328790@qq.com
 * @company: Sichuan Agricultural University
 * @date 2015年8月3日
 */
public class LoginAjaxAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	/** 业务层注入 */
	private BlogService blogService;
	public void setBlogService(BlogService blogService) {
		this.blogService = blogService;
	}
	
	/** 参数 */
	private String username;
	private String password;

	/**
	 * 异步登陆方法
	 */
	public String execute() throws Exception{
		//执行getJson()方法
		String json = blogService.login(username, password);
		//输出json数据到页面上
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().println(json);
		//打印测试json值
		System.out.println("json:  "+json);
		return NONE;
	}
	
	/** setter and getter method */
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}

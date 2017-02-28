package com.nothing.blog.action.homepage;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nothing.blog.service.BlogService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 处理【hompage】首页模块的所有请求（同步和异步）
 * @author: Nothing
 * @email: 761328790@qq.com
 * @company: Sichuan Agricultural University
 * @date 2015年8月9日
 */
public class AdminHomepageAjaxAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	
	/** 业务层注入 */
	private BlogService blogService;
	public void setBlogService(BlogService blogService) {
		this.blogService = blogService;
	}
	
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
	private Map<String, Integer> count;
	
	/**
	 * 后台登陆成功后，同步加载后台需要的6个数量
	 * @return
	 */
	public String readyData(){
		count = blogService.readyData();
		return SUCCESS;
	}
	
	/**
	 * \\\异步请求\\\ 后台主页- 后台页面点击左边【首页】菜单按钮异步加载所需数据 Method
	 */
	public String ajaxLoadMenu_SY(){
		return toPageJson(blogService.ajaxLoadMenu_SY());
	}

	/** setter and getter method */
	public Map<String, Integer> getCount() {
		return count;
	}
	public void setCount(Map<String, Integer> count) {
		this.count = count;
	}
}

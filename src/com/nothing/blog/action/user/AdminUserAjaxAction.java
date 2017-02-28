package com.nothing.blog.action.user;

import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.nothing.blog.domain.pt_domain.User;
import com.nothing.blog.service.BlogService;
import com.nothing.blog_tools.page.PageModel;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 处理【user】用户模块的所有异步请求
 * @author: Nothing
 * @email: 761328790@qq.com
 * @company: Sichuan Agricultural University
 * @date 2015年8月9日
 */
public class AdminUserAjaxAction extends ActionSupport {
	
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
	private PageModel pageModel = new PageModel();
	private String userId;
	private User user;
	private String username;

	/**
	 *  \\\异步请求\\\ 后台主页- 后台页面点击左边【用户管理】菜单按钮异步加载所需数据 Method
	 * @return
	 */
	public String ajaxLoadMenu_YH(){
		pageModel.setPageSize(4);
		return toPageJson(blogService.ajaxLoadMenu_YH(pageModel));
	}
	
	/**
	 * \\\异步请求\\\ 后台主页- 用户模块：跳到修改用户页面，准备个人信息 Method
	 * @return
	 */
	public String ajaxLoadUpdateUserInfo_YH(){
		return toPageJson(blogService.ajaxLoadUpdateUserInfo_YH(userId));
	}
	
	
	/**
	 * \\\异步请求\\\ 后台主页- 用户模块：在修改用户页面异步修改个人信息  Method
	 * @return
	 */
	public String ajaxUpdateUserInfo_YH(){
		return toPageJson(blogService.ajaxUpdateUserInfo_YH(user));
	}
	
	/**
	 * \\\异步请求\\\ 后台主页- 用户模块：异步删除用户  Method
	 * @return
	 */
	public String ajaxDeleteUserInfo_YH(){
		return toPageJson(blogService.ajaxDeleteUserInfo_YH(userId));
	}
	
	/**
	 * \\\异步请求\\\ 后台主页- 用户模块：异步添加用户  Method
	 * @return
	 */
	public String ajaxAddUserInfo_YH(){
		return toPageJson(blogService.ajaxAddUserInfo_YH(user));
	}
	
	/**
	 * \\\异步请求\\\ 后台主页- 用户模块：校验username是否存在  Method
	 * @return
	 */
	public String ajaxJyUsernameInfo_YH(){
		return toPageJson(blogService.ajaxJyUsernameInfo_YH(username));
	}
	
	/** setter and getter method */
	public PageModel getPageModel() {
		return pageModel;
	}
	public void setPageModel(PageModel pageModel) {
		this.pageModel = pageModel;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}

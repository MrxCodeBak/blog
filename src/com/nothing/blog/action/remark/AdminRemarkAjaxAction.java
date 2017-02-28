package com.nothing.blog.action.remark;

import java.io.File;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nothing.blog.domain.pt_domain.Article;
import com.nothing.blog.service.BlogService;
import com.nothing.blog_tools.page.PageModel;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 处理【remark】评论模块的所有异步请求
 * @author: Nothing
 * @email: 761328790@qq.com
 * @company: Sichuan Agricultural University
 * @date 2015年8月9日
 */
public class AdminRemarkAjaxAction extends ActionSupport{

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
	private String remarkIds_Str;
	
	/**
	 *  后台主页- 点击【评论管理】异步所有评论数据
	 * @return
	 */
	public String ajaxLoadMenuRemark_PL(){
		pageModel.setPageSize(19);
		return toPageJson(blogService.ajaxLoadMenuRemark_PL(pageModel));
	}
	
	/**
	 * 后台主页 - 删除评论（支持批量删除）
	 * @return
	 */
	public String ajaxDeleteplRemarkInfo_PL(){
		return toPageJson(blogService.ajaxDeleteplRemarkInfo_PL(remarkIds_Str));
	}
	
	/** setter and getter method */
	public PageModel getPageModel() {
		return pageModel;
	}
	public void setPageModel(PageModel pageModel) {
		this.pageModel = pageModel;
	}
	public String getRemarkIds_Str() {
		return remarkIds_Str;
	}
	public void setRemarkIds_Str(String remarkIds_Str) {
		this.remarkIds_Str = remarkIds_Str;
	}
}

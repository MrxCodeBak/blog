package com.nothing.blog.action.link;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nothing.blog.domain.pt_domain.Link;
import com.nothing.blog.domain.pt_domain.Menu;
import com.nothing.blog.service.BlogService;
import com.nothing.blog_tools.page.PageModel;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 处理【link】链接模块的所有异步请求
 * @author: Nothing
 * @email: 761328790@qq.com
 * @company: Sichuan Agricultural University
 * @date 2015年8月9日
 */
public class AdminLinkAjaxAction extends ActionSupport{

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
	private String link_name;
	private String link_url;
	private String linkId;
	private Link link;
	
	/**
	 * 后台主页 - 点击【链接管理】异步加载所有链接数据
	 * @return
	 */
	public String ajaxLoadLinkInfo_LJ(){
		pageModel.setPageSize(19);
		return toPageJson(blogService.ajaxLoadLinkInfo_LJ(pageModel));
	}

	/**
	 * 失去焦点便修改链接名称
	 * @return
	 */
	public String ajaxUpdateLinkNameInfo_LJ(){
		return toPageJson(blogService.ajaxUpdateLinkNameInfo_LJ(link_name, linkId));
	}

	/**
	 * 后台主页 - 失去焦点便修改链接URL 
	 * @return
	 */
	public String ajaxUpdateLinkUrlInfo_LJ(){
		return toPageJson(blogService.ajaxUpdateLinkUrlInfo_LJ(linkId, link_url));
	}
	
	/**
	 * 后台主页 - 添加Link
	 * @return
	 */
	public String ajaxAddLinkInfo_LJ(){
		return toPageJson(blogService.ajaxAddLinkInfo_LJ(link));
	}
	
	/**
	 * 后台主页 - 删除Link
	 * @return
	 */
	public String ajaxDeleteLinkInfo_LJ(){
		return toPageJson(blogService.ajaxDeleteLinkInfo_LJ(linkId)); 
	}
	
	/** setter and getter method */
	public PageModel getPageModel() {
		return pageModel;
	}
	public void setPageModel(PageModel pageModel) {
		this.pageModel = pageModel;
	}
	public String getLink_name() {
		return link_name;
	}
	public void setLink_name(String link_name) {
		this.link_name = link_name;
	}
	public String getLinkId() {
		return linkId;
	}
	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}
	public String getLink_url() {
		return link_url;
	}
	public void setLink_url(String link_url) {
		this.link_url = link_url;
	}
	public Link getLink() {
		return link;
	}
	public void setLink(Link link) {
		this.link = link;
	}
}

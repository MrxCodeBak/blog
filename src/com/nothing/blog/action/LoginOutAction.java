package com.nothing.blog.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.nothing.blog.domain.pt_domain.Article;
import com.nothing.blog.domain.pt_domain.Link;
import com.nothing.blog.domain.pt_domain.Menu;
import com.nothing.blog.domain.pt_domain.User;
import com.nothing.blog.service.BlogService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 注销登录
 * @author: Nothing
 * @email: 761328790@qq.com
 * @company: Sichuan Agricultural University
 * @date 2015年8月18日
 */
public class LoginOutAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	/** 业务层注入 */
	private BlogService blogService;
	public void setBlogService(BlogService blogService) {
		this.blogService = blogService;
	}

	/** 参数 */
	private List<Article> articles_five;
	private List<Menu> menus_five;
	private List<Menu> menus;
	private List<Link> links;
	private User user;
	
	
	@Override
	public String execute() throws Exception{
		Thread.sleep(3000);
		ServletActionContext.getRequest().getSession().invalidate();
		//index.jsp获取前5条数据
		articles_five = blogService.qt_getFiveArticles();
		//index.jsp获取前5个菜单
		menus_five = blogService.qt_getFiveMenus();
		//index.jsp获取所有的菜单
		menus = blogService.qt_getAllMenus();
		//index.jsp获取所有的链接
		links = blogService.qt_getAllLinks();
		//index.jsp查询博主信息
		user = blogService.qt_getBlogMasterMess();
		return SUCCESS;
	}

	/** setter and getter emthod */
	public List<Article> getArticles_five() {
		return articles_five;
	}
	public void setArticles_five(List<Article> articles_five) {
		this.articles_five = articles_five;
	}
	public List<Menu> getMenus_five() {
		return menus_five;
	}
	public void setMenus_five(List<Menu> menus_five) {
		this.menus_five = menus_five;
	}
	public List<Menu> getMenus() {
		return menus;
	}
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	public List<Link> getLinks() {
		return links;
	}
	public void setLinks(List<Link> links) {
		this.links = links;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}

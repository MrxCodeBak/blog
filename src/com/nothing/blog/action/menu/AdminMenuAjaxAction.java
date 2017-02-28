package com.nothing.blog.action.menu;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nothing.blog.domain.pt_domain.Menu;
import com.nothing.blog.service.BlogService;
import com.nothing.blog_tools.page.PageModel;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 处理【menu】菜单模块的所有异步请求
 * @author: Nothing
 * @email: 761328790@qq.com
 * @company: Sichuan Agricultural University
 * @date 2015年8月9日
 */
public class AdminMenuAjaxAction extends ActionSupport{

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
	private String newNemuName;
	private String menuId;
	private Menu menu;
	
	/**
	 * 后台主页 - 点击【菜单管理】异步加载所有菜单数据
	 * @return
	 */
	public String ajaxLoadMenu_CD(){
		pageModel.setPageSize(19);
		return toPageJson(blogService.ajaxLoadMenu_CD(pageModel));
	}

	/**
	 * 后台主页 - 失去焦点就修改菜单名
	 * @return
	 */
	public String ajaxUpdateMenuNameInfo_CD(){
		return toPageJson(blogService.ajaxUpdateMenuNameInfo_CD(newNemuName, menuId));
	}

	/**
	 * 后台主页 - 点击添加按钮 异步添加Menu
	 * @return
	 */
	public String ajaxAddMenuInfo_CD(){
		return toPageJson(blogService.ajaxAddMenuInfo_CD(menu));
	}
	
	/** setter and getter method */
	public PageModel getPageModel() {
		return pageModel;
	}
	public void setPageModel(PageModel pageModel) {
		this.pageModel = pageModel;
	}
	public String getNewNemuName() {
		return newNemuName;
	}
	public void setNewNemuName(String newNemuName) {
		this.newNemuName = newNemuName;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
}

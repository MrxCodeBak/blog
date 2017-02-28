package com.nothing.blog.action.base_abstr;

import com.nothing.blog_tools.page.PageModel;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 处理后台所有的普通请求
 * @author: Nothing
 * @email: 761328790@qq.com
 * @company: Sichuan Agricultural University
 * @date 2015年8月8日
 */
public abstract class AbstractAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * private修饰的变量，如果提供了公有的方法，那么就可以调用它，反之就不行了！
	 */
	private String tip; /** 页面提示信息 */
	
	/**
	 * protected修饰的变量，只能是继承他的类可以用，不加private是指在子类中可以直接拿变量pageModel来使用！
	 */
	protected PageModel pageModel = new PageModel();

	
	/** setter and getter method */
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public PageModel getPageModel() {
		return pageModel;
	}
	public void setPageModel(PageModel pageModel) {
		this.pageModel = pageModel;
	}
}

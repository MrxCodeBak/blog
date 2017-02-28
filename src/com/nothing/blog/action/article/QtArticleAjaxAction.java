package com.nothing.blog.action.article;

import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.nothing.blog.domain.pt_domain.Remark;
import com.nothing.blog.service.BlogService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 处理前台【Article】的所有异步请求
 * @author: Nothing
 * @email: 761328790@qq.com
 * @company: Sichuan Agricultural University
 * @date 2015年8月9日
 */
public class QtArticleAjaxAction extends ActionSupport{

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
	private Remark remark;
	private String articleId;
	
	/**
	 * 前台异步添加评论
	 * @return
	 */
	public String qtAjaxAddRemarkInfo(){
		return toPageJson(blogService.qt_ajaxAddRemarkInfo(remark));
	}
	
	/**
	 * 喜欢+1
	 * @return
	 */
	public String qtAjaxAddLikeInfo(){
		return toPageJson(blogService.qt_ajaxAddLikeInfo(articleId));
	}
	
	/** setter ang getter method */
	public Remark getRemark() {
		return remark;
	}
	public void setRemark(Remark remark) {
		this.remark = remark;
	}
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
}	
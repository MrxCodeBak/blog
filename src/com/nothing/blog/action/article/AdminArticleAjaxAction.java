package com.nothing.blog.action.article;

import java.io.File;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nothing.blog.domain.pt_domain.Article;
import com.nothing.blog.service.BlogService;
import com.nothing.blog_tools.page.PageModel;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 处理【article】文章模块的所有异步请求
 * @author: Nothing
 * @email: 761328790@qq.com
 * @company: Sichuan Agricultural University
 * @date 2015年8月9日
 */
public class AdminArticleAjaxAction extends ActionSupport{

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
	private String articleIds_Str;//评论删除文章ids
	private String articleId;//预览文章id
	
	private File article_faceimg;//封面img
	private String article_faceimgFileName;//封面名字
	
	private Article article;
	
	
	
	
	/**
	 * 后台主页- 点击左边菜单【文章管理】异步加载数据 Method
	 */
	public String ajaxLoadMenu_WZ(){
		pageModel.setPageSize(19);
		return toPageJson(blogService.ajaxLoadMenu_WZ(pageModel));
	}
	
	/**
	 * 后台主页- 批量删除文章（也可以单个文章删除）Method
	 * @return
	 */
	public String ajaxDeleteplArticleInfo_WZ(){
		return toPageJson(blogService.ajaxDeleteplArticleInfo_WZ(articleIds_Str));
	}

	/**
	 * 后台主页- 点击预览按钮时，根据articleId把该文章加载出来 Method
	 * @return
	 */
	public String ajaxLoadArticleInfo_WZ(){
		return toPageJson(blogService.ajaxLoadArticleInfo_WZ(articleId));
	}
	
	/**
	 * 后台主页- 点击添加文章按钮时，异步加载所有menu分类 Method
	 * @return
	 */
	public String ajaxLoadArticleMenuIds_WZ(){
		return toPageJson(blogService.ajaxLoadArticleMenuIds_WZ());
	}
	
	/**
	 * 后台主页- 异步上传文章的封面 Method
	 * @return
	 */
	public String ajaxUploadArticleFaceImg_WZ(){
		return toPageJson(blogService.ajaxUploadArticleFaceImg_WZ(article_faceimg, article_faceimgFileName));
	}
	
	/**
	 * 后台主页- 异步添加文章 Method
	 * @return
	 */
	public String ajaxAddArticleInfo_WZ(){
		return toPageJson(blogService.ajaxAddArticleInfo_WZ(article));
	}
	
	/**
	 * 后台主页- 根据articleId异步修改文章 Method
	 * @return
	 */
	public String ajaxUpdateArticleInfo_WZ(){
		return toPageJson(blogService.ajaxUpdateArticleInfo_WZ(article));
	}
	
	/** setter and getter method */
	public PageModel getPageModel() {
		return pageModel;
	}
	public void setPageModel(PageModel pageModel) {
		this.pageModel = pageModel;
	}
	public String getArticleIds_Str() {
		return articleIds_Str;
	}
	public void setArticleIds_Str(String articleIds_Str) {
		this.articleIds_Str = articleIds_Str;
	}
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public File getArticle_faceimg() {
		return article_faceimg;
	}
	public void setArticle_faceimg(File article_faceimg) {
		this.article_faceimg = article_faceimg;
	}
	public String getArticle_faceimgFileName() {
		return article_faceimgFileName;
	}
	public void setArticle_faceimgFileName(String article_faceimgFileName) {
		this.article_faceimgFileName = article_faceimgFileName;
	}
}

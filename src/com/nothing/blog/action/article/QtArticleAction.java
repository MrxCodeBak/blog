package com.nothing.blog.action.article;

import java.util.List;

import com.nothing.blog.action.base_abstr.AbstractAction;
import com.nothing.blog.domain.index_domain.ArticleSo;
import com.nothing.blog.domain.pt_domain.Article;
import com.nothing.blog.domain.pt_domain.Link;
import com.nothing.blog.domain.pt_domain.Menu;
import com.nothing.blog.domain.pt_domain.Remark;
import com.nothing.blog.domain.pt_domain.User;
import com.nothing.blog.service.BlogService;

/**
 * 处理博客项目前台的同步Action
 * @author: Nothing
 * @email: 761328790@qq.com
 * @company: Sichuan Agricultural University
 * @date 2015年8月13日
 */
public class QtArticleAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	/** 业务层注入 */
	private BlogService blogService;
	public void setBlogService(BlogService blogService) {
		this.blogService = blogService;
	}
	
	/** 参数 */
	private List<Article> articles_five;//去前5条数据
	private List<Article> articles;
	private Article article;
	private Article article_prev;
	private Article article_next;
	private String articleId;
	private User user;
	private List<Menu> menus_five;
	private List<Menu> menus;
	private String menuId;
	private String menu_fl_name;
	private List<Link> links;
	private List<Remark> remarks;
	private List<ArticleSo> articleSos; 
	private String searchContent; 
	
	/**
	 * 准备index.jsp页面所需要的数据
	 * @return
	 */
	public String qtIndex(){
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
	
	/**
	 * 根据menuId展示相应的文章
	 * @return
	 */
	public String qtGetArticlesByMenuId(){
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
		/**
		 * 根据menuId分页展示相应的文章
		 */
		menuId = getMenuId();
		pageModel.setPageSize(10);
		articles = blogService.qt_getArticlesByMenuId(menuId, pageModel);
		System.out.println("--------1"+articles.size());
		if (articles.size() > 0) {
			System.out.println("-----2-");
			menu_fl_name = articles.get(0).getMenu().getMenu_name();
		}
		return SUCCESS;
	}
	
	/**
	 * 根据articleId展示相应的文章内容
	 * @return
	 */
	public String qtGetArticleContentByArticleId(){
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
		/**
		 * 执行查询
		 */
		article = blogService.qt_getArticleContentByArticleId(articleId);//Article-User-Menu
		remarks = blogService.qt_getArticleRemarksByArticleId(articleId);//Remark
		article_prev = blogService.qt_getPrevArticleByArticleId(articleId);//上一篇文章
		article_next = blogService.qt_getNextArticleByArticleId(articleId);//下一篇文章
		return SUCCESS;
	}
	
	/**
	 * 回车搜索文章
	 * @return
	 */
	public String qtGetSearchArticles(){
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
		
		/**
		 * 正式查询
		 */
		articleSos = blogService.qt_getSearchArticles(searchContent);
		if (articleSos.size()<1) {
			setTip("articleSos__NULL");
		}
		return SUCCESS;
	}
	
	
	/** setter and getter method */
	public List<Article> getArticles() {
		return articles;
	}
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	public List<Remark> getRemarks() {
		return remarks;
	}
	public void setRemarks(List<Remark> remarks) {
		this.remarks = remarks;
	}
	public Article getArticle_prev() {
		return article_prev;
	}
	public void setArticle_prev(Article article_prev) {
		this.article_prev = article_prev;
	}
	public Article getArticle_next() {
		return article_next;
	}
	public void setArticle_next(Article article_next) {
		this.article_next = article_next;
	}
	public String getSearchContent() {
		return searchContent;
	}
	public void setSearchContent(String searchContent) {
		this.searchContent = searchContent;
	}
	public List<ArticleSo> getArticleSos() {
		return articleSos;
	}
	public void setArticleSos(List<ArticleSo> articleSos) {
		this.articleSos = articleSos;
	}
	public String getMenu_fl_name() {
		return menu_fl_name;
	}
	public void setMenu_fl_name(String menu_fl_name) {
		this.menu_fl_name = menu_fl_name;
	}
}

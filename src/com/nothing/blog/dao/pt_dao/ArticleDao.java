package com.nothing.blog.dao.pt_dao;

import java.sql.SQLException;
import java.util.List;

import com.nothing.blog.domain.pt_domain.Article;
import com.nothing.blog_tools.page.PageModel;

public interface ArticleDao extends BaseDao {

	/**
	 * 后台主页- 点击左边菜单【文章管理】异步加载数据方法
	 * @param pageModel
	 * @return
	 * @throws SQLException
	 */
	List<Article> ajaxLoadMenu_WZ(PageModel pageModel)throws SQLException;

	/**
	 * 后台主页- 评批量删除文章（也可以单个文章删除）Method
	 * @param arictleIds
	 * @throws SQLException
	 */
	void deleteArticle(Integer[] arictleIds)throws SQLException;

	/** ######################################前台操作####################################################### */
	/**
	 * index.jsp获取前5条数据
	 * @return
	 * @throws SQLException
	 */
	List<Article> qt_getFiveArticles()throws SQLException;

	/**
	 * 根据menuId分页展示相应的文章
	 * @param menuId
	 * @param pageModel
	 * @return
	 */
	List<Article> getArticlesByMenuId(String menuId, PageModel pageModel);

	/**
	 * 根据articleId展示相应的文章内容 Article-User-Menu
	 * @param articleId
	 * @return
	 * @throws SQLException
	 */
	Article getArticleContentByArticleId(String articleId)throws SQLException;

	/**
	 * 查询article的最大Id
	 * @return
	 */
	int getMaxId();


}

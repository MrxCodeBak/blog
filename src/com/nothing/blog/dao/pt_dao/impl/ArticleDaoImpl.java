package com.nothing.blog.dao.pt_dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nothing.blog.dao.pt_dao.ArticleDao;
import com.nothing.blog.domain.pt_domain.Article;
import com.nothing.blog_tools.page.PageModel;

public class ArticleDaoImpl extends BaseDaoImpl implements ArticleDao {

	/**
	 * 后台主页- 点击左边菜单【文章管理】异步加载数据方法
	 * @param pageModel
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<Article> ajaxLoadMenu_WZ(PageModel pageModel)
			throws SQLException {
		String hql = "select a from Article a join a.user u join a.menu order by a.article_fbtime desc";
		return this.findByPage(hql, pageModel, null);
	}

	/**
	 * 后台主页- 评批量删除文章（也可以单个文章删除）Method
	 * @param arictleIds
	 * @throws SQLException
	 */
	@Override
	public void deleteArticle(Integer[] arictleIds) throws SQLException {
		for (Integer r : arictleIds) {
			System.out.println("------------->>>>"+r);
		}
		StringBuilder hql = new StringBuilder();
		hql.append("delete from Article where id in(");
		for (int i = 0; i < arictleIds.length; i++)
		{
			hql.append(i == 0 ? "?" : ",?");
		}
		hql.append(")");
		this.bulkUpdate(hql.toString(), arictleIds);
	}
	
	/** ######################################前台操作####################################################### */
	/**
	 * index.jsp获取前5条数据
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<Article> qt_getFiveArticles() throws SQLException {
		String hql = "select a from Article a join a.user u join a.menu order by a.article_fbtime desc";
		return this.findByPage(hql, 0, 5, null);
	}

	/**
	 * 根据menuId分页展示相应的文章
	 * @param menuId
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<Article> getArticlesByMenuId(String menuId, PageModel pageModel) {
		String hql = "select a from Article a join a.user u join a.menu m where m.id = ? order by a.article_fbtime desc";
		List<Object> params = new ArrayList<>();
		//必须转成Menu id --> Integer 相统一
		params.add(Integer.valueOf(menuId));
		return this.findByPage(hql, pageModel, params);
	}

	/**
	 * 根据articleId展示相应的文章内容 Article-User-Menu
	 * @param articleId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public Article getArticleContentByArticleId(String articleId)
			throws SQLException {
		String hql = "select a from Article a join a.user u join a.menu m where a.id = ?";
		return this.findUniqueEntity(hql, new Object[]{Integer.valueOf(articleId)});
	}

	/**
	 * 查询article的最大Id
	 * @return
	 */
	@Override
	public int getMaxId() {
		String hql = "select max(id) from Article";
		return this.findUniqueEntity(hql, null);
	}


}

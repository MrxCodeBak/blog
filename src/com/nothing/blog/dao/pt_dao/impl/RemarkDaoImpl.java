package com.nothing.blog.dao.pt_dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nothing.blog.dao.pt_dao.RemarkDao;
import com.nothing.blog.domain.pt_domain.Remark;
import com.nothing.blog_tools.page.PageModel;

public class RemarkDaoImpl extends BaseDaoImpl implements RemarkDao {

	/**
	 * 根据articleId查询该篇文章的所有评论数量
	 * @return
	 * @throws SQLException
	 */
	@Override
	public int getRemarkCountByArticleId(Integer articleId) throws SQLException {
		String hql = "select count(*) from Remark r where r.article.id=?";
		return this.count(hql, new Object[]{articleId});
	}

	/**
	 * 根据articleId展示相应的文章内容 Remark
	 * @param articleId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<Remark> getArticleRemarksByArticleId(String articleId)
			throws SQLException {
		String hql = "select r from Remark r join r.article a where a.id = ?";
		return (List<Remark>) this.find(hql, new Object[]{Integer.valueOf(articleId)});
	}

	/**
	 * 后台主页- 点击【评论管理】异步所有评论数据
	 * @param pageModel
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<Remark> ajaxLoadMenuRemark_PL(PageModel pageModel)
			throws SQLException {
		String hql = "select r from Remark r join r.article a order by a.id desc";
		return this.findByPage(hql, pageModel, null);
	}

	/**
	 * 后台主页 - 删除评论（支持批量删除）
	 * @param remarkIds
	 * @throws SQLException
	 */
	@Override
	public void deleteRemark(Integer[] remarkIds) throws SQLException {
		StringBuilder hql = new StringBuilder();
		hql.append("delete from Remark where id in(");
		for (int i = 0; i < remarkIds.length; i++)
		{
			hql.append(i == 0 ? "?" : ",?");
		}
		hql.append(")");
		this.bulkUpdate(hql.toString(), remarkIds);
	}

}

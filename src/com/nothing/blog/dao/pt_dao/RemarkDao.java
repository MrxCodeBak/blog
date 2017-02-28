package com.nothing.blog.dao.pt_dao;

import java.sql.SQLException;
import java.util.List;

import com.nothing.blog.domain.pt_domain.Remark;
import com.nothing.blog_tools.page.PageModel;

public interface RemarkDao extends BaseDao {

	/**
	 * 根据articleId查询该篇文章的所有评论数量
	 * @param articleId 
	 * @return
	 * @throws SQLException
	 */
	int getRemarkCountByArticleId(Integer articleId)throws SQLException;

	/**
	 * 根据articleId展示相应的文章内容 Remark
	 * @param articleId
	 * @return
	 * @throws SQLException
	 */
	List<Remark> getArticleRemarksByArticleId(String articleId)throws SQLException;

	/**
	 * 后台主页- 点击【评论管理】异步所有评论数据
	 * @param pageModel
	 * @return
	 * @throws SQLException
	 */
	List<Remark> ajaxLoadMenuRemark_PL(PageModel pageModel)throws SQLException;

	/**
	 * 后台主页 - 删除评论（支持批量删除）
	 * @param remarkIds
	 * @throws SQLException
	 */
	void deleteRemark(Integer[] remarkIds)throws SQLException;

}

package com.nothing.blog.dao.pt_dao;

import java.sql.SQLException;
import java.util.List;

import com.nothing.blog.domain.pt_domain.Menu;
import com.nothing.blog_tools.page.PageModel;

public interface MenuDao extends BaseDao {

	/**
	 * index.jsp获取前5个菜单
	 * @return
	 * @throws SQLException
	 */
	List<Menu> qt_getFiveMenus()throws SQLException;
	
	/**
	 * 异步分页查询所有的Menu
	 * @param pageModel
	 * @return
	 * @throws SQLException
	 */
	List<Menu> ajaxLoadMenu_CD(PageModel pageModel)throws SQLException;

	/**
	 * 获取该菜单下面的文章总数
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	Integer getArticleCount(Integer menuId)throws SQLException;

	

}

package com.nothing.blog.dao.pt_dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.nothing.blog.dao.pt_dao.MenuDao;
import com.nothing.blog.domain.pt_domain.Menu;
import com.nothing.blog_tools.page.PageModel;

public class MenuDaoImpl extends BaseDaoImpl implements MenuDao {

	/**
	 * index.jsp获取前5个菜单
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<Menu> qt_getFiveMenus() throws SQLException {
		String hql = "from Menu order by id asc";
		return this.findByPage(hql, 0, 5, null);
	}

	/**
	 * 异步分页查询所有的Menu
	 * @param pageModel
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<Menu> ajaxLoadMenu_CD(PageModel pageModel) throws SQLException {
		String hql = "from Menu";
		return this.findByPage(hql, pageModel, null);
	}
	
	/**
	 * 获取该菜单下面的文章总数
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	@Override
	public Integer getArticleCount(Integer menuId) throws SQLException {
		String hql = "select count(*) from Article a where a.menu.id = ?";
		return this.count(hql, new Object[]{menuId});
	}

	

}

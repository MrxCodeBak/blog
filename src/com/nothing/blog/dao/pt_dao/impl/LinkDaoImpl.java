package com.nothing.blog.dao.pt_dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.nothing.blog.dao.pt_dao.LinkDao;
import com.nothing.blog.domain.pt_domain.Link;
import com.nothing.blog_tools.page.PageModel;

public class LinkDaoImpl extends BaseDaoImpl implements LinkDao {

	/**
	 * 异步加载【链接】菜单所需数据
	 * @param pageModel
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<Link> ajaxLoadLinkInfo_LJ(PageModel pageModel)
			throws SQLException {
		String hql = "from Link";
		return this.findByPage(hql, pageModel, null);
	}

}

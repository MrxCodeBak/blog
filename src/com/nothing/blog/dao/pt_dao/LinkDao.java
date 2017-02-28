package com.nothing.blog.dao.pt_dao;

import java.sql.SQLException;
import java.util.List;

import com.nothing.blog.domain.pt_domain.Link;
import com.nothing.blog_tools.page.PageModel;

public interface LinkDao extends BaseDao {

	/**
	 * 异步加载【链接】菜单所需数据
	 * @param pageModel
	 * @return
	 * @throws SQLException
	 */
	List<Link> ajaxLoadLinkInfo_LJ(PageModel pageModel)throws SQLException;

}

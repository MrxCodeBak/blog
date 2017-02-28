package com.nothing.blog.dao.pt_dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.nothing.blog.dao.pt_dao.UserDao;
import com.nothing.blog.domain.pt_domain.User;
import com.nothing.blog_tools.page.PageModel;

public class UserDaoImpl extends BaseDaoImpl implements UserDao {

	/**
	 * 异步登陆 Mthod
	 * @param username
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	@Override
	public User login(String username, String password) throws SQLException {
		String hql = "from User where username = ? and password = ?";
		return this.findUniqueEntity(hql, new Object[]{username, password});
	}

	/**
	 * 根据username验证该用户是否存在 Mthod
	 * @param username
	 * @return
	 * @throws SQLException
	 */
	@Override
	public User verify_exist(String username) throws SQLException {
		String hql = "from User where username = ?";
		return this.findUniqueEntity(hql, new Object[]{username});
	}

	/**
	 * 后台主页-异步加载【用户管理】所需数据 Mthod
	 * @param pageModel
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<User> ajaxLoadMenu_YH(PageModel pageModel) throws SQLException {
		String hql = "from User";
		return this.findByPage(hql, pageModel, null);
	}

	/**
	 * 后台主页- 异步删除用户 (支持批量删除) Mthod
	 * @param userIdToArr
	 * @return
	 * @throws SQLException
	 */
	@Override
	public void deleteUser(Integer[] userIds) throws SQLException {
		StringBuilder hql = new StringBuilder();
		hql.append("delete from User where id in(");
		for (int i = 0; i < userIds.length; i++)
		{
			hql.append(i == 0 ? "?" : ",?");
		}
		hql.append(")");
		this.bulkUpdate(hql.toString(), userIds);
	}

	/**
	 * 后台主页 - 异步校验用户名是否已经存在
	 * @param username
	 * @return
	 * @throws SQLException
	 */
	@Override
	public User ajaxJyUsernameInfo_YH(String username) throws SQLException {
		String hql = "from User where username = ?";
		return this.findUniqueEntity(hql, new Object[]{username});
	}

}

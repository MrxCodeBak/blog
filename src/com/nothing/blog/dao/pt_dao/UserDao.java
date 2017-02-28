package com.nothing.blog.dao.pt_dao;

import java.sql.SQLException;
import java.util.List;

import com.nothing.blog.domain.pt_domain.User;
import com.nothing.blog_tools.page.PageModel;

public interface UserDao extends BaseDao {

	/**
	 * 异步登陆 Mthod
	 * @param username
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	User login(String username, String password)throws SQLException;

	/**
	 * 根据username验证该用户是否存在 Mthod
	 * @param username
	 * @return
	 * @throws SQLException
	 */
	User verify_exist(String username)throws SQLException;

	/**
	 * 后台主页-异步加载【用户管理】所需数据 Mthod
	 * @param pageModel
	 * @return
	 * @throws SQLException
	 */
	List<User> ajaxLoadMenu_YH(PageModel pageModel)throws SQLException;

	/**
	 * 后台主页- 异步删除用户 (支持批量删除) Mthod
	 * @param userIdToArr
	 * @return
	 * @throws SQLException
	 */
	void deleteUser(Integer[] userIds)throws SQLException;

	/**
	 * 后台主页 - 异步校验用户名是否已经存在
	 * @param username
	 * @return
	 * @throws SQLException
	 */
	User ajaxJyUsernameInfo_YH(String username)throws SQLException;

}

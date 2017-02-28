package com.nothing.blog_tools.interceptor;

import com.nothing.blog.domain.pt_domain.User;
import com.nothing.blog_tools.cont.WebConstant;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 配置默认的登陆拦截器
 * @author: Nothing
 * @email: 761328790@qq.com
 * @company: Sichuan Agricultural University
 * @date 2015年8月4日
 */
public class LoginInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	/**
	 * 继承AbstractInterceptor类，重写intercept方法
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		User user = (User) invocation.getInvocationContext().getSession().get(WebConstant.SESSION_USER);
		if (user != null) {
			return invocation.invoke();
		}else{
			return WebConstant.USER_MUST_LOGIN;
		}
	}

}

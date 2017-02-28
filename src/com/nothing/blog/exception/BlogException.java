package com.nothing.blog.exception;

/**
 * 博客项目的自定义异常处理
 * @author: Nothing
 * @email: 761328790@qq.com
 * @company: Sichuan Agricultural University
 * @date 2015年7月29日
 */
public class BlogException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BlogException() {
		// TODO Auto-generated constructor stub
	}

	public BlogException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public BlogException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public BlogException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BlogException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}

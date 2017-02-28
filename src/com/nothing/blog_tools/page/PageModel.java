package com.nothing.blog_tools.page;

/**
 * 分页实体
 * @author: Nothing
 * @email: 761328790@qq.com
 * @company: Sichuan Agricultural University
 * @date 2015年7月29日
 */
public class PageModel 
{
	// 定义默认一页显示的数量
	private final static int PAGE_SIZE = 5;
	// 当前页码
	private int pageIndex;
	// 一页显示的显数
	private int pageSize;
	// 总记录条数
	private int recordCount;
	
	/** setter and getter method */
	public int getPageIndex() 
	{
		return pageIndex <= 1 ? 1 : pageIndex;
	}
	public void setPageIndex(int pageIndex) 
	{
		this.pageIndex = pageIndex;
	}
	public int getPageSize() 
	{
		return pageSize <= 0 ? PAGE_SIZE : pageSize;
	}
	public void setPageSize(int pageSize) 
	{
		this.pageSize = pageSize;
	}
	public int getRecordCount() 
	{
		return recordCount;
	}
	public void setRecordCount(int recordCount) 
	{
		this.recordCount = recordCount;
	}
	// 分页查询时要用到这个方法来获取起始行数
	public int getStartRow() 
	{
		// limit 0,2  : 第一个参数：(pageIndex - 1) * pageSize
		return (this.getPageIndex() - 1) * this.getPageSize();
	}
	
	/** 补充：总页数 */
	public int getTotalSize() 
	{
		return (this.recordCount % getPageSize() == 0) 	? (recordCount / getPageSize()) :  (recordCount / getPageSize()) + 1;
	}
}

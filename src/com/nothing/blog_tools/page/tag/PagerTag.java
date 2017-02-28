package com.nothing.blog_tools.page.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * 分页标签库
 * @author: Nothing
 * @email: 761328790@qq.com
 * @company: Sichuan Agricultural University
 * @date 2015年4月26日
 */
public class PagerTag extends SimpleTagSupport {
	// 定义URL中的一个占位符
	private static final String TAG = "{0}";
	// 当前的页码
	private int pageIndex;
	// 每页显示的数量
	private int pageSize;
	// 总记录条数
	private int recordCount;
	// 提交的URL (pageServlet?pageIndex={0})
	private String submitUrl;
	// 定义总页数
	private int totalSize;
	@Override
	public void doTag() throws JspException, IOException {
		// 拼接分页信息
		StringBuilder res = new StringBuilder();
		// 拼接中间页码信息
		StringBuilder str = new StringBuilder();
		/**-------------只需要将index.html中的第一个页面中的第一个<div>的copy过来即可！！！--------------------*/
		str.append("<span class='digg'>");
		// 首先判断总记录条数
		if (recordCount > 0){
			// 计算出总页数
			totalSize = (recordCount % pageSize == 0) 
					    ? recordCount / pageSize 
						: (recordCount / pageSize) + 1;
			// 判断当前的页码处于哪个位置
			if (pageIndex == 1){ // 在首页
				str.append("<span class='disabled'> <  Prev</span>");
				// 计算中间的页码
				calcPage(str);
				if (pageIndex == totalSize){ // 如果只有一页
					str.append("<span class='disabled'>Next  > </span>");
				}else{
					String tempUrl = submitUrl.replace(TAG, String.valueOf(pageIndex + 1));
					// pageServlet?pageIndex=2
					str.append("<a href='"+tempUrl+"'>Next  > </a>");
				}
				
			}else if (pageIndex == totalSize){ // 在尾页
				//  <  Prev
				String tempUrl = submitUrl.replace(TAG, String.valueOf(pageIndex - 1));
				str.append("<a href='"+tempUrl+"'> <  Prev</a>");
				// 计算中间的页码
				calcPage(str);
				// Next  > 
				str.append("<span class='disabled'>Next  > </span>");
			}else{ // 在中间
				//  <  Prev
				String tempUrl = submitUrl.replace(TAG, String.valueOf(pageIndex - 1));
				str.append("<a href='"+tempUrl+"'> <  Prev</a>");
				// 计算中间的页码
				calcPage(str);
				tempUrl = submitUrl.replace(TAG, String.valueOf(pageIndex + 1));
				str.append("<a href='"+tempUrl+"'>Next  > </a>");
			}
			// 开始条数
			int startNum = (this.pageIndex - 1) * this.pageSize + 1;
			// 结束条数
			int endNum = (this.pageIndex == totalSize) ? this.recordCount : this.pageIndex * this.pageSize;
			res.append("<table align='center' style='font-size:14px;'>")
			    .append("<tr><td>" + str.toString())
			    .append("跳转到<input type='text' id='jump_page_num' size='2' style='margin-left:2px;outline:none;'/>页")
			    .append("<input type='button' onclick='jump_page_fn();' value='确 定' style='width:40px;height:22px;border:0 none;background:#bda47e;color:#fff;font-size:12px;border-radius:3px;margin-left:5px;font-family:微软雅黑;line-height:15px;outline:none;'/>")
			    .append("</td></tr>")
			    .append("<tr align='center' style='color:#8B745C'><td>总共<span class='current'>"+recordCount)
			    .append("</font>条记录，当前显示"+ startNum +"-"+ endNum +"条记录</td></tr>")
			    .append("</table>");
			res.append("<script type='text/javascript'>");
			res.append("function jump_page_fn(){");
			res.append("var page_num = document.getElementById('jump_page_num').value;");
			res.append("var submitUrl = '" + submitUrl +"';");
			res.append("if (isNaN(page_num) || page_num < 1 || page_num > "+ totalSize +"){");
			res.append("   alert('请输入1-"+ totalSize +"之间的页码！');");
			res.append("}else{");
			res.append("   document.location = submitUrl.replace('"+ TAG +"', page_num)");
			res.append("}");
			res.append("}");
			res.append("</script>");
		}else{ // 总记录条数小于等于0
			res.append("<table align='center' style='font-size:14px;'><tr>")
			   .append("<td>总共<span class='current'>0</font>条记录，当前显示0-0条记录。</td>")
			   .append("</tr></table>");
		}
		res.append("</span>");
		this.getJspContext().getOut().println(res.toString());
	}

	/**
	 * 计算中间的页码
	 * @param str
	 */
	private void calcPage(StringBuilder str) {
		// 判断总页数
		if (totalSize <= 10){ // 页码全部显示出来
			for (int i = 1; i <= totalSize; i++){
				if (i == pageIndex){ // 当前选中的页码
					str.append("<span class='current'>"+ i +"</span>");
				}else{
					String tempUrl = submitUrl.replace(TAG, String.valueOf(i));
					str.append("<a href='"+ tempUrl +"'>"+ i +"</a>");
				}
			}
		}else{
			// 12345678...100
			if (pageIndex - 8 < 0){ // 靠近首页
				for (int i = 1; i <= 8; i++){
					if (i == pageIndex){ // 当前选中的页码
						str.append("<span class='current'>"+ i +"</span>");
					}else{
						String tempUrl = submitUrl.replace(TAG, String.valueOf(i));
						str.append("<a href='"+ tempUrl +"'>"+ i +"</a>");
					}
				}
				String tempUrl = submitUrl.replace(TAG, String.valueOf(totalSize));
				str.append("...")
				   .append("<a href='"+tempUrl+"'>"+totalSize+"</a>");
			}
			//1...93949596979899100
			else if (pageIndex + 8 > totalSize){ // 靠近尾页
				String tempUrl = submitUrl.replace(TAG, String.valueOf(1));
				str.append("<a href='"+ tempUrl +"'>1</a>").append("...");
				for (int i = totalSize - 8; i <= totalSize; i++){
					if (i == pageIndex){ // 当前选中的页码
						str.append("<span class='current'>"+ i +"</span>");
					}else{
						tempUrl = submitUrl.replace(TAG, String.valueOf(i));
						str.append("<a href='"+ tempUrl +"'>"+ i +"</a>");
					}
				}
			}
			//1...789101112131415...100
			else{ // 中间
				String tempUrl = submitUrl.replace(TAG, String.valueOf(1));
				str.append("<a href='"+ tempUrl +"'>1</a>").append("...");
				
				for (int i = pageIndex - 4; i <= pageIndex + 4; i++){
					if (i == pageIndex){ // 当前选中的页码
						str.append("<span class='current'>"+ i +"</span>");
					}else{
						tempUrl = submitUrl.replace(TAG, String.valueOf(i));
						str.append("<a href='"+ tempUrl +"'>"+ i +"</a>");
					}
				}
				
				tempUrl = submitUrl.replace(TAG, String.valueOf(totalSize));
				str.append("...").append("<a href='"+tempUrl+"'>"+totalSize+"</a>");
			}
		}
	}


	/** setter and getter method */
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public String getSubmitUrl() {
		return submitUrl;
	}

	public void setSubmitUrl(String submitUrl) {
		this.submitUrl = submitUrl;
	}
}

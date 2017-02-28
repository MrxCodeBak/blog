package com.nothing.blog.domain.index_domain;

import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableId;
import org.compass.annotations.SearchableProperty;
import org.compass.annotations.Store;
import org.compass.annotations.TermVector;

/**
 * 列出需要检索的字段
 * @author: Nothing
 * @email: 761328790@qq.com
 * @company: Sichuan Agricultural University
 * @date 2015年7月30日
 */
@Searchable
public class ArticleSo {
	@SearchableId
	private Integer id;
	@SearchableProperty(termVector=TermVector.YES , store=Store.YES)
	private String article_title;
	@SearchableProperty(termVector=TermVector.YES , store=Store.YES)
	private String article_content;
	
	/** setter and getter method */
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getArticle_title() {
		return article_title;
	}
	public void setArticle_title(String article_title) {
		this.article_title = article_title;
	}
	public String getArticle_content() {
		return article_content;
	}
	public void setArticle_content(String article_content) {
		this.article_content = article_content;
	}
}

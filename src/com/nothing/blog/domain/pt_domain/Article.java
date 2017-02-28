package com.nothing.blog.domain.pt_domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="blog_article")
public class Article {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String article_title;
	private String article_content;
	private Date article_fbtime;
	private String article_faceimg;
	private String article_like;
	private String article_timeaxis;
	private String article_remarknum;
	
	@ManyToOne(targetEntity=User.class)
	@JoinColumn(name="userId", referencedColumnName="id")
	private User user;
	
	@ManyToOne(targetEntity=Menu.class)
	@JoinColumn(name="menuId", referencedColumnName="id")
	private Menu menu;
	
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
	public String getArticle_like() {
		return article_like;
	}
	public void setArticle_like(String article_like) {
		this.article_like = article_like;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	public String getArticle_faceimg() {
		return article_faceimg;
	}
	public void setArticle_faceimg(String article_faceimg) {
		this.article_faceimg = article_faceimg;
	}
	public Date getArticle_fbtime() {
		return article_fbtime;
	}
	public void setArticle_fbtime(Date article_fbtime) {
		this.article_fbtime = article_fbtime;
	}
	public String getArticle_timeaxis() {
		return article_timeaxis;
	}
	public void setArticle_timeaxis(String article_timeaxis) {
		this.article_timeaxis = article_timeaxis;
	}
	public String getArticle_remarknum() {
		return article_remarknum;
	}
	public void setArticle_remarknum(String article_remarknum) {
		this.article_remarknum = article_remarknum;
	}
}

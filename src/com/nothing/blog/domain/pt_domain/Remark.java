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
@Table(name="blog_remark")
public class Remark {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String remark_content;
	private String remark_username;
	private String remark_useremail;
	private Date remark_time;
	private String remark_timeaxis;
	private String remark_timejson;

	@ManyToOne(targetEntity=Article.class)
	@JoinColumn(name="articleId", referencedColumnName="id")
	private Article article;

	
	/** setter and getter method */
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRemark_content() {
		return remark_content;
	}
	public void setRemark_content(String remark_content) {
		this.remark_content = remark_content;
	}
	public String getRemark_username() {
		return remark_username;
	}
	public void setRemark_username(String remark_username) {
		this.remark_username = remark_username;
	}
	public String getRemark_useremail() {
		return remark_useremail;
	}
	public void setRemark_useremail(String remark_useremail) {
		this.remark_useremail = remark_useremail;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public Date getRemark_time() {
		return remark_time;
	}
	public void setRemark_time(Date remark_time) {
		this.remark_time = remark_time;
	}
	public String getRemark_timeaxis() {
		return remark_timeaxis;
	}
	public void setRemark_timeaxis(String remark_timeaxis) {
		this.remark_timeaxis = remark_timeaxis;
	}
	public String getRemark_timejson() {
		return remark_timejson;
	}
	public void setRemark_timejson(String remark_timejson) {
		this.remark_timejson = remark_timejson;
	}
}

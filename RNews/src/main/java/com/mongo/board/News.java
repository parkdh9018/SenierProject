package com.mongo.board;

import org.springframework.data.annotation.Id;

public class News {
	
	@Id
	private String id;
	
	private String title;
	private String company;
	private String site;
	
	public News(String title, String company, String site)
	{
		this.title = title;
		this.company = company;
		this.site = site;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", company="+company+", site="+site+"]<br>";
	}
	
}

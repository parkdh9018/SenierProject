package com.mongo.controller;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mongo.board.MongoDAO;
import com.mongo.board.News;

/**
 * Handles requests for the application home page.
 */
@Controller


public class insertController {
	@RequestMapping("insert")
	public String Insert(HttpServletRequest request) throws Exception {
		String page = "home";
		
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title");
		String company = request.getParameter("company");
		String site = request.getParameter("site");
		
		System.out.println("title : "+title+", company :  "+company+", site : "+site);
		News news = new News(title, company, site);
		MongoDAO mongoDAO = new MongoDAO();
		mongoDAO.insert(news);
		
		return page;
	}

	
}

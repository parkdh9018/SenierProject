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


public class removeController {
	@RequestMapping("remove")
	public String remove(HttpServletRequest request) throws Exception {
		String page = "home";
		MongoDAO mongoDAO = new MongoDAO();
		
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		
		mongoDAO.findAndRemove(id);
		
		return page;
	}

	
}

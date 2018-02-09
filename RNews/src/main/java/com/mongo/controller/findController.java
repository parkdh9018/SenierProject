package com.mongo.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mongo.board.MongoDAO;
import com.mongo.board.News;

/**
 * Handles requests for the application home page.
 */
@Controller
//MVC 중 Controller에 해당

public class findController {
	
	//collection내의 모든 document를 출력
	@RequestMapping("findAll")
	public ModelAndView FindAll(){
		MongoDAO mongoDAO = new MongoDAO();
		List<News> list = mongoDAO.findAll();
	
		//ModelAnView 메서드를 이용하여 Model에 list를 넘겨준다.
		ModelAndView view = new ModelAndView("list");
		view.getModelMap().addAttribute("list",list);
		
		return view;
	}
	
	//조건에 맞는 모든 document를 출력
	@RequestMapping("find")
	public ModelAndView Find(HttpServletRequest request) throws Exception {
		MongoDAO mongoDAO = new MongoDAO();
		
		request.setCharacterEncoding("UTF-8");
		String where = request.getParameter("where");
		String is = request.getParameter("is");
		
		List<News> list = mongoDAO.find(where,is);
		ModelAndView view = new ModelAndView("list");
		view.getModelMap().addAttribute("list",list);
		
		return view;

	}
	
}

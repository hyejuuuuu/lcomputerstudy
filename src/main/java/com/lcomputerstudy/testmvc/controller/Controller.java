package com.lcomputerstudy.testmvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lcomputerstudy.testmvc.service.UserService;
import com.lcomputerstudy.testmvc.vo.Pagination;
import com.lcomputerstudy.testmvc.vo.User;


@WebServlet("*.do")
public class Controller extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html; charset=utf-8");
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		String view = null;
		String pw = null;
		String idx = null;
		HttpSession session = null;
		command = chcekSession(request,response,command);	
		int usercount =0;
		int page=1;
		UserService userService = null;
		
		
		switch (command) {
		    case "/user-list.do":
		    	String rePage = request.getParameter("page");
		    	if(rePage != null) {
		    		page = Integer.parseInt(rePage);
		
		    	}
		    	userService = UserService.getInstance();
		    	usercount = userService.getUsersCount();
		    	Pagination pagination = new Pagination();
		    	pagination.setCount(usercount);
		    	pagination.setPage(page);
		    	pagination.init();
		    	ArrayList<User> list = userService.getUsers(pagination);
		    	
		    	request.setAttribute("list", list);
		    	request.setAttribute("pagination", pagination);	// pagination 추가
		    	
		    	view = "user/list";
		    	break;
		    	
		    case "/user-insert.do":
				view = "user/insert";
				break;
		case "/user-insert-process.do":
				User user = new User();
				user.setU_id(request.getParameter("id"));
				user.setU_pw(request.getParameter("password"));
				user.setU_name(request.getParameter("name"));
				user.setU_tel(request.getParameter("tel1") + "-" + request.getParameter("tel2") + "-" + request.getParameter("tel3"));
				user.setU_age(request.getParameter("age"));
				
				userService = UserService.getInstance();
				userService.insertUser(user);
						
				view = "user/insert-result";
				break;   
				
		case "/user-login.do":
			view ="user/login";
			break;
		case "/user-login-process.do":
			idx = request.getParameter("login_id");
			pw = request.getParameter("login_password");
			
			userService = UserService.getInstance();
			user = userService.loginUser(idx,pw);
			
			if(user != null) {
				session = request.getSession();
				session.setAttribute("user", user);
				
				view = "user/login-result";
			}else {
				view ="user/login-fail";
			}
			break;
		case "/logout.do":
			session= request.getSession();
			session.invalidate();
			view = "user/login";
			break;
		case "/access-denied.do":
			view = "user/access-denied";
			break;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(view+".jsp");
		rd.forward(request, response);
	}
	
	
	String chcekSession(HttpServletRequest request,HttpServletResponse response,String command) {
		HttpSession session = request.getSession();
		
		String[] authList= {
				"/user-list.do"
				,"/user-insert.do"
				,"/user-insert-process.do"
				,"/user-detail.do"
				,"/user-edit.do"
				,"/user-edit-process.do"
				,"/user-logout.do"
		};
		
	for(String item : authList) {
		if(item.equals(command)) {
			if(session.getAttribute("user")== null) {
				command = "/access-denied.do";
			}
		}
	}
	return command;
 }	
}

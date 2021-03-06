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

import com.lcomputerstudy.testmvc.service.BService;
import com.lcomputerstudy.testmvc.service.C_service;
import com.lcomputerstudy.testmvc.service.UserService;
import com.lcomputerstudy.testmvc.vo.Board;
import com.lcomputerstudy.testmvc.vo.Comment;
import com.lcomputerstudy.testmvc.vo.Pagination;
import com.lcomputerstudy.testmvc.vo.Search;
import com.lcomputerstudy.testmvc.vo.User;


@WebServlet("*.do")
public class Controller extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		String view = null;
		String pw = null;
		String idx = null;
		HttpSession session = null;
		//command = chcekSession(request,response,command);	
		int usercount =0;
		int page=1;
		UserService userService = null;
		BService bService = null;
		Board board = null;
		int bcount =0;
		int page1=1;
		C_service Cservice =null;
		Comment comment = null;
		Search search = null;
		
		boolean isRedirected = false;
		
		
		switch (command) {
		    case "/user-list.do":
		    	String rePage = request.getParameter("page");
		    	if(rePage != null) {
		    		page = Integer.parseInt(rePage);
		
		    	}
		    ;
				
		    	userService = UserService.getInstance();
		    	usercount = userService.getUsersCount();
		    	Pagination pagination = new Pagination();
		    	pagination.setCount(usercount);
		    	pagination.setPage(page);
		    	pagination.init();
		    	ArrayList<User> list = userService.getUsers(pagination);
		    	
		    	
				
		    	request.setAttribute("list", list);
		    	request.setAttribute("pagination", pagination);	// pagination ??????
		    	
		    	view = "user/list";
		    	
		    	break;
		    	
		    case "/user-update.do":
		    	User user1 = new User();  
					user1.setU_idx(Integer.parseInt(request.getParameter("u_idx")));
					user1.setU_role(request.getParameter("u_role"));
					userService = UserService.getInstance();
					userService.updateUser(user1);
					
					view = "user-list.do";
					isRedirected = true;
			    	
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
				
			case "/board-write.do":
				board = new Board();
				view = "Board/B-rg";
				break;
			case "/board-write-process.do":
				session = request.getSession();
				user = (User)session.getAttribute("user");
				board = new Board();  //????????????
				board.setU_idx(user.getU_idx());
				board.setB_tt(request.getParameter("title")); 
				board.setB_con(request.getParameter("content"));
				bService = BService.getInstance();
				bService.writeBoard(board);	
				
				view = "Board/Rg-result";
				break;
			
			case "/board-list.do":/*???????????? list????????? ???????????? ?????? ???????????? ???*/
		    	String rePage1 = request.getParameter("page");
		    	if(rePage1 != null) {
		    		page = Integer.parseInt(rePage1);
		
		    	}
		    	
		    	search = new Search();
		    	search.setType(request.getParameter("type"));
		    	search.setKeyword(request.getParameter("keyword"));
		    	
		    	bService = BService.getInstance();
		    	bcount = bService.getBoardsCount();
		    	Pagination pagination1 = new Pagination();
		    	pagination1.setCount(bcount);
		    	pagination1.setPage(page);
		    	pagination1.setSearch(search);
		    	pagination1.init();
		    	ArrayList<Board> list1 = bService.getBoards(pagination1);
		    	
		    	board = new Board();
		    	board.setB_con(request.getParameter("b_con"));
				board.setU_name(request.getParameter("u_name"));
				board.setB_tt(request.getParameter("b_tt")); 
				
		    	
		    	
		    	
		    	request.setAttribute("list", list1);
		    	request.setAttribute("pagination", pagination1);	
		    /*	System.out.println(search.getType());
		    	System.out.println(search.getKeyword());*/
		    	view = "Board/B-list";
		    	
		    	break;
		    	
			
			case "/board-detail.do":
				session = request.getSession();
				user = (User)session.getAttribute("user");
				
				board = new Board();
				board.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
				bService = BService.getInstance();
				board = bService.getDetail(board);
				bService.viewCnt(board.getB_idx());
				
				Pagination pagination2 = new Pagination();
		    	pagination2.setCount(bcount);
		    	pagination2.setPage(page);
		    	pagination2.init();
		    	Cservice = C_service.getInstance();
				ArrayList<Comment> list2 = Cservice.getComments(pagination2, board);
				
				
				
				
				request.setAttribute("board", board);
				request.setAttribute("commentList", list2);
				request.setAttribute("pagination", pagination2);
				
				/*comment list ??? service ??? ????????? ????????? ??? request ??? attribute ???????????? jsp?????? ??????*/
					
				view = "Board/Bdetail";
				break;
				
		
			case "/comment-Reply-Process.do":
				session = request.getSession();
				user = (User)session.getAttribute("user"); 
				
				comment = new Comment(); 
				
				comment.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
				comment.setC_con(request.getParameter("content"));
				comment.setU_name(request.getParameter("u_name"));
				comment.setC_gr(Integer.parseInt(request.getParameter("c_gr")));
				comment.setC_or(Integer.parseInt(request.getParameter("c_or")) + 1);
				comment.setC_de(Integer.parseInt(request.getParameter("c_de")) + 1);
				Cservice = C_service.getInstance();
				comment.setU_idx(user.getU_idx());
				Cservice.commentReply(comment);
				
				view = "board-detail.do?b_idx=" + comment.getB_idx();
				isRedirected = true;
				break;
				
			case "/aj-comment-reply-process.do":
				session = request.getSession();
				user = (User)session.getAttribute("user");
				
				board = new Board();
				board.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
				
				comment = new Comment(); 
				
				comment.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
				comment.setC_con(request.getParameter("c_con"));
				comment.setC_gr(Integer.parseInt(request.getParameter("c_gr")));
				comment.setC_or(Integer.parseInt(request.getParameter("c_or")) + 1);
				comment.setC_de(Integer.parseInt(request.getParameter("c_de")) + 1);
				Cservice = C_service.getInstance();
				comment.setU_idx(user.getU_idx());
				Cservice.commentReply(comment);
				
				ArrayList<Comment> list5 = Cservice.getComments(board);
				request.setAttribute("commentList", list5);  /*??????????????????????????????*/
				request.setAttribute("board", board); /*???????????????????????? ??????????????? ??????????????? ?????? */
				
				
				view = "ajax/comment-list";
				break;
				
			case "/comment-delete.do": /*????????????*/
				comment = new Comment();
				comment.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
				comment.setC_idx(Integer.parseInt(request.getParameter("c_idx")));
				Cservice = C_service.getInstance();
				Cservice.commentDelete(comment);
				
				view = "board-detail.do?b_idx=" + comment.getB_idx();
				isRedirected = true;
				break;
				
			case "/aj-comment-delete.do": 
				board = new Board();
				board.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
				
				comment = new Comment();
				comment.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
				comment.setC_idx(Integer.parseInt(request.getParameter("c_idx")));
				Cservice = C_service.getInstance();
				Cservice.commentDelete(comment);
				
				ArrayList<Comment> list7 = Cservice.getComments(board);
				request.setAttribute("commentList", list7);
				request.setAttribute("board", board);
				request.setAttribute("comment", comment);
				
				view = "ajax/comment-list";
				
				break;
				
				
				
			case "/comment-edit.do": /*????????????*/
				comment = new Comment();  
				comment.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
				comment.setC_idx(Integer.parseInt(request.getParameter("c_idx")));
				comment.setC_con(request.getParameter("content"));
				Cservice = C_service.getInstance();
				Cservice.commentEdit(comment);
				
				view = "board-detail.do?b_idx=" + comment.getB_idx();
				isRedirected = true;
				break;
				
			case "/aj-comment-edit.do": 
				board = new Board();
				board.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
				
				comment = new Comment();  
				comment.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
				comment.setC_idx(Integer.parseInt(request.getParameter("c_idx")));
				comment.setC_con(request.getParameter("c_con"));
				Cservice = C_service.getInstance();
				Cservice.commentEdit(comment);
				
				ArrayList<Comment> list6 = Cservice.getComments(board);
				request.setAttribute("commentList", list6);
				request.setAttribute("comment", comment);
				request.setAttribute("board", board);
				view="ajax/comment-list";
				
				
				break;
				
			case "/aj-comment-write.do": 
				session = request.getSession();
				user = (User)session.getAttribute("user");
				/*??????*/
				board = new Board();
				board.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
				
				comment = new Comment();
				comment.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
				comment.setC_con(request.getParameter("c_con"));
				comment.setU_idx(user.getU_idx());
				Cservice = C_service.getInstance();
				Cservice.writereply(comment);
				
		    	Cservice = C_service.getInstance();
				ArrayList<Comment> list4 = Cservice.getComments(board);
				request.setAttribute("commentList", list4);
				request.setAttribute("board", board);
				
				view = "ajax/comment-list";
				break;
				
			case "/board-edit.do":
				board = new Board();
				board.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
				bService = BService.getInstance();
				board = bService.getEdit(board);
				request.setAttribute("board", board);
				view = "Board/Bedit";
				break;
				
			case "/board-edit-process.do":
				session = request.getSession();
				user = (User)session.getAttribute("user");
				board = new Board();  //????????????
				board.setU_idx(user.getU_idx());
				board.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
				board.setB_tt(request.getParameter("title")); 
				board.setB_con(request.getParameter("content"));
				bService = BService.getInstance();
				bService.updateBoard(board);
				
				view = "Board/Edit-Result";
				break;
				
			case "/board-delete.do":
				session = request.getSession();
				user = (User)session.getAttribute("user");
				board = new Board();  //????????????
				board.setU_idx(user.getU_idx());
				board.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
				bService = BService.getInstance();
				bService.Delete(board);
				
				
				view = "Board/Bdelete";
				break;
				
			
				
			case "/board-Reply.do":
				board = new Board();
				board.setB_gr(Integer.parseInt(request.getParameter("b_gr")));
				board.setB_or(Integer.parseInt(request.getParameter("b_or")));
				board.setB_de(Integer.parseInt(request.getParameter("b_de")));
				request.setAttribute("board", board);  /*set????????? jsp????????? ?????????*/
				view = "Board/B-reply";
				break;
				
			case "/board-Reply-Process.do":
				session = request.getSession();
				user = (User)session.getAttribute("user");
				board = new Board();  
				board.setU_idx(user.getU_idx());
				board.setB_con(request.getParameter("content"));
				board.setB_tt(request.getParameter("title"));
				board.setB_gr(Integer.parseInt(request.getParameter("b_gr")));
				board.setB_or(Integer.parseInt(request.getParameter("b_or")) + 1);
				board.setB_de(Integer.parseInt(request.getParameter("b_de")) + 1);
				bService = BService.getInstance();
				bService.replyBoard(board);
				
				view = "Board/Rg-result";
				
				break;
			
			
			
				
						
		}
		
		if (isRedirected) {
			response.sendRedirect(view);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher(view+".jsp");
			rd.forward(request, response);
		}
	}
	
	
	String chcekSession(HttpServletRequest request,HttpServletResponse response,String command) {
		HttpSession session = request.getSession();
		
		String[] authList= {
				"/user-list.do"
				,"/user-insert.do"
				,"/user-update.do"
				,"/user-insert-process.do"
				,"/user-detail.do"
				,"/user-edit.do"
				,"/user-edit-process.do"
				,"/user-logout.do"
				,"/board-list.do"
				,"/board-write.do"
				,"/board-detail.do"
				,"/board-delete.do"
				,"/board-edit.do"
				,"/comment-edit.do"
				,"/board-reply.do"
				,"/comment-delete.do"
				,"/comment-reply-process.do"
				
		};/*do?????? ???????????? case??? ????????????*/
		
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

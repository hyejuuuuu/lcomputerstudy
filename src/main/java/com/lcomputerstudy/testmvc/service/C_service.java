package com.lcomputerstudy.testmvc.service;

import java.util.ArrayList;

import com.lcomputerstudy.testmvc.dao.BDAO;
import com.lcomputerstudy.testmvc.dao.C_DAO;
import com.lcomputerstudy.testmvc.vo.Board;
import com.lcomputerstudy.testmvc.vo.Comment;
import com.lcomputerstudy.testmvc.vo.Pagination;

public class C_service {
	
	private static C_service service = null;
	private static C_DAO dao = null;
	
	private C_service() {

	}
	public static C_service getInstance() {
		if(service == null) {
			service = new C_service();
			dao = C_DAO.getInstance();
		}
		return service;
	}
	public void writereply(Comment comment) {
		dao.writereply(comment);
	}
	public ArrayList<Comment> getComments(Pagination pagination){
		return dao.getComments(pagination);
	}

	public int getCommentsCount() {
		// TODO Auto-generated method stub
		return dao.getCommentsCount();
	}
}
package com.lcomputerstudy.testmvc.service;

import java.util.ArrayList;

import com.lcomputerstudy.testmvc.dao.BDAO;
import com.lcomputerstudy.testmvc.vo.Board;
import com.lcomputerstudy.testmvc.vo.Pagination;

public class BService {
	
	private static BService service = null;
	private static BDAO dao = null;
	
	private BService() {
		
	}
	
	public static BService getInstance() {
		if(service == null) {
			service = new BService();
			dao = BDAO.getInstance();
		}
		return service;
	}
	
	public ArrayList<Board> getBoards(){
		return dao.getBoards();
	}
	
	public void writeBoard(Board board) {
		dao.writeBoard(board);
	}
	
	
	public ArrayList<Board> getBoards(Pagination pagination){
		return dao.getBoards();
	}

	public int getBoardsCount() {
		// TODO Auto-generated method stub
		return dao.getBoardsCount();
	}
	

}

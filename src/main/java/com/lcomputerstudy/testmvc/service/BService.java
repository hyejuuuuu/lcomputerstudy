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
	
	public void writeBoard(Board board) {
		dao.writeBoard(board);
	}
	
	
	public ArrayList<Board> getBoards(Pagination pagination){
		return dao.getBoards(pagination);
	}

	public int getBoardsCount() {
		// TODO Auto-generated method stub
		return dao.getBoardsCount();
	}

	public Board getDetail(Board board) {
		// TODO Auto-generated method stub
		return dao.getDetail(board);
	}

	public Board getEdit(Board board) {
		// TODO Auto-generated method stub
		return dao.getEdit(board);
	}

	public void Delete(Board board) {
		// TODO Auto-generated method stub
		dao.Delete(board);
	}
	
	public void updateBoard(Board board) {
		dao.updateBoard(board);
	}

	public void replyBoard(Board board) {
		dao.replyBoard(board);
	}
	
	public void viewCnt(int b_idx ) {
		dao.viewCnt(b_idx);
	}
	


}
	



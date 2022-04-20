package com.lcomputerstudy.testmvc.vo;

public class Comment {
	
	private int c_idx;
	private int b_idx;	
	private int c_gr;
	private int c_de;
	private int c_or;
	private String c_con;
	private String c_date;
	private int  rownum;
	private Board  board;
	private String  u_name;
	private int u_idx;
	private User user;
	


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getU_idx() {
		return u_idx;
	}

	public void setU_idx(int u_idx) {
		this.u_idx = u_idx;
	}

	public String getU_name() {
		return u_name;
	}

	public void setU_name(String u_name) {
		this.u_name = u_name;
	}

	public Board getBoard() {
		return board;
	}
	
	public void setBoard(Board board) {
		this.board = board;
	}
	public int getB_idx() {
		return b_idx;
	}
	public void setB_idx(int b_idx) {
		this.b_idx = b_idx;
	}
	
	
	public int getC_idx() {
		return c_idx;
	}
	public void setC_idx(int c_idx) {
		this.c_idx = c_idx;
	}

	public int getC_gr() {
		return c_gr;
	}
	public void setC_gr(int c_gr) {
		this.c_gr = c_gr;
	}
	public int getC_de() {
		return c_de;
	}
	public void setC_de(int c_de) {
		this.c_de = c_de;
	}
	public int getC_or() {
		return c_or;
	}
	public void setC_or(int c_or) {
		this.c_or = c_or;
	}
	
	public String getC_con() {
		return c_con;
	}
	public void setC_con(String c_con) {
		this.c_con = c_con;
	}
	public String getC_date() {
		return c_date;
	}
	public void setC_date(String c_date) {
		this.c_date = c_date;
	}
	public void setRownum(int rownum) {
		this.rownum = rownum;
		
	}
	public int getRownum() {
		return rownum;
	}

	
}

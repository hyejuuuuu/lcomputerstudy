package com.lcomputerstudy.testmvc.vo;

public class Board {
	
	private int b_idx;
	private int u_idx;
	private String b_tt;
	private String b_con;
	private int b_ct;
	private String b_date;
	private int rownum;
	private String u_name;
	
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name= u_name;
	}
	
	public int getRownum() {
		return rownum;
	}

	public void setRownum(int rownum) {
		this.rownum = rownum;
	}

	public int getU_idx() {
		return u_idx;
	}

	public void setU_idx(int u_idx) {
		this.u_idx = u_idx;
	}

	
	public String getB_tt() {
		return b_tt;
	}
	
	public void setB_tt(String b_tt) {
		this.b_tt = b_tt;
	}
	
	public String getB_con() {
		return b_con;
	}
	
	public void setB_con(String b_con) {
		this.b_con = b_con;
	}
	
	public int getB_ct() {
		return b_ct;
	}
	
	public void setB_ct(int b_ct) {
		this.b_ct = b_ct;
	}
	
	public String getB_date() {
		return b_date;
	}
	
	public void setB_date(String b_date) {
		this.b_date = b_date;
	}

	public void setB_idx(int b_idx) {
		// TODO Auto-generated method stub
		this.b_idx = b_idx;
	}
	
	public int getB_idx() {
		return b_idx;
	}
}

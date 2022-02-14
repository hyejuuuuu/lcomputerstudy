package com.lcomputerstudy.testmvc.vo;

public class Board {
	
	private int b_idx;
	private String b_id;
	private int u_idx;
	private String b_tt;
	private String b_con;
	private int b_ct;
	private int b_date;
	
	public int getU_idx() {
		return u_idx;
	}

	public void setU_idx(int u_idx) {
		this.u_idx = u_idx;
	}

	public String getB_id() {
		return b_id;
	}
	
	public void setB_id(String b_id) {
		this.b_id = b_id;
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
	
	public int getB_date() {
		return b_date;
	}
	
	public void setB_date(int b_date) {
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

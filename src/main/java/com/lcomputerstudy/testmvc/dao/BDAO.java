package com.lcomputerstudy.testmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.lcomputerstudy.testmvc.database.DBConnection;
import com.lcomputerstudy.testmvc.vo.Pagination;
import com.lcomputerstudy.testmvc.vo.User;
import com.lcomputerstudy.testmvc.vo.Board;

public class BDAO {
	
	private static BDAO dao = null;
	
	private BDAO() {
		
	}
	
	public static BDAO getInstance() {
		if(dao == null) {
			dao = new BDAO();
		}
		return dao;
	}
	
	public ArrayList<Board> getBoards(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Board>list = null;
		
		
		try {
			conn = DBConnection.getConnection();
			String query = "select * from board";
			rs = pstmt.executeQuery();
			list = new ArrayList<Board>();
			
			while(rs.next()) {
				Board board = new Board();
				board.setB_idx(rs.getInt("b_idx"));
				board.setB_id(rs.getString("b_id"));
				board.setB_tt(rs.getString("b_tt"));
				board.setB_con(rs.getString("b_con"));
				board.setB_ct(rs.getInt("b_ct"));
				board.setB_date(rs.getInt("b_date"));
				list.add(board);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public int getBoardsCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			String query = "SELECT COUNT(*) count FROM board ";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				count = rs.getInt("count");
			}
		}catch(Exception e) {
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
	public ArrayList<Board> getBoards(Pagination pagination){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Board> list = null;
		int pageNum = (pagination.getPage() -1) * Pagination.perPage;
		
		try {
			conn = DBConnection.getConnection();
			String query = new StringBuilder()
					.append("SELECT 		@ROWNUM := @ROWNUM - 1 AS ROWNUM,")
					.append("				ta.*")
					.append("FROM 			board ta,")
					.append("INNER JOIN	    (SELECT @rownum := (SELECT	COUNT(*)-3+1 FROM user ta)) tb ON 1+1")
					.append("LIMIT			3, 3")
					.toString();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, pageNum);
			pstmt.setInt(2, pageNum);
			rs= pstmt.executeQuery();
			list = new ArrayList<Board>();
			
			while(rs.next()) {
				Board board = new Board();
				board.setB_idx(rs.getInt("b_idx"));
				board.setB_id(rs.getString("b_id"));
				board.setB_tt(rs.getString("b_tt"));
				board.setB_con(rs.getString("b_con"));
				board.setB_ct(rs.getInt("b_ct"));
				board.setB_date(rs.getInt("b_date"));
				list.add(board);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public void writeBoard(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn =  DBConnection.getConnection();
			String sql = "insert into board(u_idx,b_tt,b_con,b_ct) value(?,?,?,0)";
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1,board.getU_idx());
			pstmt.setString(2,board.getB_tt());
			pstmt.setString(3,board.getB_con());
			pstmt.executeUpdate();
		}catch(Exception ex) {
			System.out.println("SQLException : "+ex.getMessage());
		}finally {
			try {
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}	
	}

}

	
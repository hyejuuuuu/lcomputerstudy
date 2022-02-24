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
					.append("SELECT 		@ROWNUM := @ROWNUM - 1 AS ROWNUM,\n")	
					.append("ta.*,\n")	
					.append("tb.u_name\n")	
					.append("FROM 			board ta\n")	
					.append("LEFT JOIN	user tb ON ta.u_idx = tb.u_idx\n")	
					.append("INNER JOIN	(SELECT @rownum := (SELECT	COUNT(*)-?+1 FROM board ta)) tc ON 1=1\n")	
 			        .append("Limit ?,3\n")	
					.toString();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, pageNum);
			pstmt.setInt(2, pageNum);
			rs= pstmt.executeQuery();
			list = new ArrayList<Board>();
			
			while(rs.next()) {
				Board board = new Board();
				board.setRownum(rs.getInt("ROWNUM"));
				board.setU_name(rs.getString("u_name"));
				board.setB_idx(rs.getInt("b_idx"));
				board.setB_tt(rs.getString("b_tt"));
				board.setB_con(rs.getString("b_con"));
				board.setB_ct(rs.getInt("b_ct"));
				board.setB_date(rs.getString("b_date"));
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
			pstmt.setInt(1, board.getU_idx());
			pstmt.setString(2, board.getB_tt());
			pstmt.setString(3, board.getB_con());
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

	public Board getDetail(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.getConnection();
			String query = new StringBuilder()
					.append("SELECT		*\n")
					.append("FROM			board ta\n")
					.append("LEFT JOIN	user tb ON ta.u_idx = tb.u_idx\n")
					.append("WHERE 		b_idx = ?\n")
					.toString();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, board.getB_idx());
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				board.setB_idx(rs.getInt("b_idx"));
				board.setU_name(rs.getString("u_name"));
				board.setB_tt(rs.getString("b_tt"));
				board.setB_con(rs.getString("b_con"));
				board.setB_ct(rs.getInt("b_ct"));
				board.setB_date(rs.getString("b_date"));
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
		return board;
	}

	public Board getEdit(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String title = board.getB_tt();
		String content = board.getB_con();
		int idx = board.getB_idx();
		
		try {
			String sql = "UPDATE board SET b_date =now(),b_tt =?,b_con =? where b_idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, idx);
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			
			conn = DBConnection.getConnection();
			String sb = new StringBuilder()
					.append("UPDATE  board")
					.append("SET b_tt = '제목수정중'")
					.append("b_idx=1;")
					.toString();
			
				
			if (rs.next()) {
				board.setB_idx(rs.getInt("b_idx"));
				board.setU_name(rs.getString("u_name"));
				board.setB_tt(rs.getString("b_tt"));
				board.setB_con(rs.getString("b_con"));
				board.setB_date(rs.getString("b_date"));
				
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
		return board;
	}
	
	

	public Board getDelete(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.getConnection();
			String query = new StringBuilder()
					.append("SELECT		*\n")
					.append("FROM			board ta\n")
					.append("LEFT JOIN	user tb ON ta.u_idx = tb.u_idx\n")
					.append("WHERE 		b_idx = ?\n")
					.toString();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, board.getB_idx());
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				board.setU_name(rs.getString("u_name"));
				board.setB_tt(rs.getString("b_tt"));
				board.setB_con(rs.getString("b_con"));
				board.setB_ct(rs.getInt("b_ct"));
				board.setB_date(rs.getString("b_date"));
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
		return board;
		
	}

}

	
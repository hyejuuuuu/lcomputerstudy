package com.lcomputerstudy.testmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lcomputerstudy.testmvc.database.DBConnection;
import com.lcomputerstudy.testmvc.vo.Board;
import com.lcomputerstudy.testmvc.vo.Comment;
import com.lcomputerstudy.testmvc.vo.Pagination;
import com.lcomputerstudy.testmvc.vo.User;

public class C_DAO {

	private static C_DAO dao = null;
	
	private C_DAO() {
		
	}
	
	public static C_DAO getInstance() {
		if(dao == null) {
			dao = new C_DAO();
		}
		return dao;
	}
	public int getCommentsCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			String query = "SELECT COUNT(*) count FROM comment ";
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
	
	public ArrayList<Comment> getComments(Pagination pagination, Board board){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Comment> list = null;
		int pageNum = (pagination.getPage() -1) * Pagination.perPage;
		
		try {
			conn = DBConnection.getConnection();
			String query = new StringBuilder()
					.append("SELECT 		@ROWNUM := @ROWNUM - 1 AS ROWNUM,\n")	
					.append("ta.*,\n")	
					.append("tb.b_idx,tb.u_idx,td.u_name\n")	
					.append("FROM 			comment ta\n")	
					.append("LEFT JOIN	board tb ON ta.b_idx = tb.b_idx\n")	
					.append("LEFT JOIN	user td ON ta.u_idx = td.u_idx\n")	
					.append("INNER JOIN	(SELECT @rownum := (SELECT	COUNT(*)-?+1 FROM comment ta)) tc ON 1=1\n")
					.append("where ta.b_idx = ?\n")
					.append("order by c_gr desc, c_or asc\n")
 			        .append("Limit ?,?\n")	
					.toString();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, pageNum);
			pstmt.setInt(2, board.getB_idx());
			pstmt.setInt(3, pageNum);
			pstmt.setInt(4, Pagination.perPage);
			rs= pstmt.executeQuery();
			list = new ArrayList<Comment>();
			
			while(rs.next()) {
				Comment comment = new Comment();
				comment.setRownum(rs.getInt("ROWNUM"));
				comment.setC_idx(rs.getInt("c_idx"));
				comment.setC_date(rs.getString("c_date"));
				comment.setC_de(rs.getInt("c_de"));
				comment.setC_or(rs.getInt("c_or"));
				comment.setC_gr(rs.getInt("c_gr"));
				comment.setC_con(rs.getString("c_con"));
				comment.setB_idx(rs.getInt("b_idx"));
				User user = new User();
				user.setU_name(rs.getString("u_name"));
				comment.setUser(user);
				list.add(comment);
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
	
	public ArrayList<Comment> getComments(Board board){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Comment> list = null;
		
		try {
			conn = DBConnection.getConnection();
			String query = new StringBuilder()
					.append("SELECT 		@ROWNUM := @ROWNUM - 1 AS ROWNUM,\n")	
					.append("ta.*,\n")	
					.append("tb.b_idx,tb.u_idx,td.u_name\n")	
					.append("FROM 			comment ta\n")	
					.append("LEFT JOIN	board tb ON ta.b_idx = tb.b_idx\n")	
					.append("LEFT JOIN	user td ON ta.u_idx = td.u_idx\n")	
					.append("INNER JOIN	(SELECT @rownum := (SELECT	COUNT(*)+1 FROM comment ta)) tc ON 1=1\n")
					.append("where ta.b_idx = ?\n")
					.append("order by c_gr desc, c_or asc\n")	
					.toString();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, board.getB_idx());
			rs= pstmt.executeQuery();
			list = new ArrayList<Comment>();
			
			while(rs.next()) {
				Comment comment = new Comment();
				comment.setRownum(rs.getInt("ROWNUM"));
				comment.setC_idx(rs.getInt("c_idx"));
				comment.setC_date(rs.getString("c_date"));
				comment.setC_de(rs.getInt("c_de"));
				comment.setC_or(rs.getInt("c_or"));
				comment.setC_gr(rs.getInt("c_gr"));
				comment.setC_con(rs.getString("c_con"));
				comment.setB_idx(rs.getInt("b_idx"));
				User user = new User();
				user.setU_name(rs.getString("u_name"));
				comment.setUser(user);
				list.add(comment);
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

	public void writereply(Comment comment) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn= DBConnection.getConnection();
			String sql = "insert into comment(b_idx,c_gr,c_or,c_de,c_con,u_idx) value(?,0,1,0,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comment.getB_idx());
			pstmt.setString(2,comment.getC_con());
			pstmt.setInt(3, comment.getU_idx());
			pstmt.executeUpdate();
			pstmt.close();
			
			sql = "update comment set c_gr = last_insert_id() where c_idx=last_insert_id()";
			pstmt = conn.prepareStatement(sql);
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

	public void commentReply(Comment comment) {
		// TODO Auto-generated method stub
		Connection conn =null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnection.getConnection();
			String sql = "insert into comment(b_idx,c_con,c_gr,c_or,c_de,u_idx) value(?,?,?,?,?,?)";
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, comment.getB_idx());
			pstmt.setString(2, comment.getC_con());
			pstmt.setInt(3, comment.getC_gr());
			pstmt.setInt(4, comment.getC_or());
			pstmt.setInt(5, comment.getC_de());
			pstmt.setInt(6,comment.getU_idx());
			pstmt.executeUpdate();
			pstmt.close();
			
			sql = "update comment set c_or = c_or+1 where c_gr = ? and c_or >= ? and c_idx != last_insert_id()";
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, comment.getC_gr());
			pstmt.setInt(2, comment.getC_or());
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

	public void commentDelete(Comment comment) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnection.getConnection();
			String sql = "delete from comment where c_idx= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,comment.getC_idx());
			pstmt.executeUpdate();
		}catch(Exception ex) {
			System.out.println("SQLException "+ex.getMessage());
		}finally {
			try {
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void commentEdit(Comment comment) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		 try {
			 conn = DBConnection.getConnection();
			 String sql = "update comment set "
						+ "c_con =?, "
						+ "c_date = now() "
						+ "where c_idx =?";
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1,comment.getC_con());
			 pstmt.setInt(2, comment.getC_idx());
			 pstmt.executeUpdate();
		 }catch(Exception ex) {
			 System.out.println("SQLException :"+ex.getMessage());
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




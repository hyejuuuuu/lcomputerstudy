<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정 값 넘겨받기</title>
</head>
<body>
<%@ include file="DBconnection_B.jsp" %>

<%
	request.setCharacterEncoding("UTF-8");

	String idx = request.getParameter("b_idx");
	String title = request.getParameter("b_tt");
	String content = request.getParameter("b_con");

	PreparedStatement pstmt = null;
	
	try{
		String sql= "UPDATE board SET b_title = ? ,b_content = ? where b_idx=?";
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, title);
		pstmt.setString(2, content);
		pstmt.setString(3, idx);
		pstmt.executeUpdate();
		System.out.println(pstmt.toString());
%>
		<h3>수정 완료</h3>
<% 
		}catch(SQLException ex) {
			System.out.println("SQLException : " +ex.getMessage());
		}finally{
			if(pstmt != null){
				pstmt.close();
			}
			if(conn != null){
				conn.close();
			}
		}
%>	
<a href = "B-list.jsp">돌아가기</a>

</body>
</html>
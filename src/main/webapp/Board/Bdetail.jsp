
<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 상세</title>
</head>
<style>
	table{
		border-collapse:collapse;
	}
	
	table tr th{
		font-weight:700;
	}
	
	table tr td,table tr th{
		border:1px solid #818181;
		width:200px;
		text-align:center;
	}
	
	a{
		text-decoration:none;
		color:#000;
		font-weight:700;
		border:none;
		cursor:pointer;
		padding:10px;
		display:inline-block;
	}
</style>

<body>
<%@ include file="DBconnection_B.jsp" %>
	<h1>회원 상세페이지</h1>
	<table>
	<%
		String idx = request.getParameter("b_idx");
	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select * from board where b_idx=?";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, idx);
		
		rs = pstmt.executeQuery();
		
		while(rs.next()){
			int b_idx     =rs.getInt("b_idx");
			String b_tt   =rs.getString("b_tt");
			String u_name =rs.getString("u_name");
			String b_con  =rs.getString("b_con");
			
			
			
	%>
	<tr>
		<td>NO.</td>
		<td><%=b_idx %></td>
	</tr>
	<tr>
		<td>게시글 제목</td>
		<td><%=b_tt %></td>
	</tr>
	<tr>
		<td>작성자</td>
		<td><%=u_name %></td>
	</tr>
	<tr>
		<td>내용</td>
		<td><%=b_con %></td>
	</tr>

	<tr style="height:50px;">
		<td style="border:none;">
			<a href="Bedit.jsp?b_idx=<%=b_idx%>" style="width:70%;font-weight:700;background-color: #818181;color:#fff;" >수정</a>
		</td>
		<td style="border:none;">
			<a href="Bdelete.jsp?b_idx=<%=b_idx%>" style="width:70%;font-weight:700;background-color:red;color:#fff;">삭제</a>
		</td>
	</tr>
	
	
	
	<%
		}
	%>					
	</table>
<body>

</body>
</html>
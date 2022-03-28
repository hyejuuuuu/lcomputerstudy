
<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세보기</title>
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
<h1>회원 목록</h1>
	<table>
	
		<tr>
			<td>NO.</td>
			<td>${board.b_idx}</td>
		</tr>
		<tr>	
			<td>작성자</td>
			<td>${board.u_name}</td>
		</tr>
		<tr>	
			<td>제목</td>
			<td>${board.b_tt}</td>
		</tr>
		<tr>	
			<td>내용</td>
			<td>${board.b_con}</td>
		</tr>	
			<td>조회수</td>
			<td>${board.b_ct}</td>
		<tr>	
			<td>작성일시</td>
			<td>${board.b_date}</td>
		</tr>
		
	</table>
<style>
	a {
		text-decoration:none;
		color:#000;
		font-weight:700;
		border:none;
		cursor:pointer;
		padding:10px;
		display:inline-block;
		text-align: center;
		margin: 10px auto;
	}
</style>
		
		<td>
		<a href="board-edit.do?b_idx=${board.b_idx }" style="width:5%;font-weight:700;background-color:skyblue;color:#fff;" >수정</a>
		</td>
		<td >
		<a href="board-delete.do?b_idx=${board.b_idx }" style="width:5%;font-weight:700;background-color:red;color:#fff;">삭제</a>
		</td>
		<td>
		<a href="board-Reply.do?b_idx=${board.b_idx }&b_gr=${board.b_gr}&b_or=${board.b_or}&b_de=${board.b_de}" style="whidth:5%;font-weight:800;background-color:grey;color:#fff;">답글작성</a>
		</td>
	</tr>
	
/***댓글****///
<div class="comment">

		<form action="comment-write.do" method ="POST" name ="comment">
			<div class="w3-border w3-padding">댓글 작성</div>	
			
				<textarea name="content" rows="5" cols="50" >댓글 작성하기</textarea>	
				<input type="hidden" name="b_idx" value="${comment.b_idx}">
				<input type="hidden" name="b_gr" value="${comment.c_gr}">
				<input type="hidden" name="b_or" value="${comment.c_or}">
				<input type="hidden" name="b_de" value="${comment.c_de}">
					
					<button type="submit" value="등록" >등록하기</button>
			</div>
		</form>	
			


</body>
</html>
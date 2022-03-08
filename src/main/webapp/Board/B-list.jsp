<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix ="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
</head>
<style> 
	h1{
		text-align: center;
	}
	table{
		border-collapse: collapse;  
		margin: 40px auto	/*제목과 테이블의 띄우는*/
	}
	table tr th {
		font-weight:700; /*테이블 둘째 칸 글 굵기*/
	}
	table tr td, table tr th{
		border:1px solid #818181; /*칸 테두리 굵기*/
		width:200px; /*전체 칸넓이*/
		text-align:center;
	}
	a{				/*페이지버튼 텍스트*/
		text-decoration: none;
		color:#000;
		font-weight:700;
	}
	ul{				
		width:500px;
		height: 50px;
		margin: 100px auto;	/*테이블과 페이지버튼  사이 띄움*/
	}
	li{
		list-style: none;
		width: 50px;
		line-height: 50px;
		border: 1px solid #ededed;
		float: left;
		text-align: center;
		margin: 0 5px;
		border-radius: 5px; /*페이지버튼 모양 둥글게*/
	}
</style>
<body>

<h1>회원 목록</h1>

<div style="margin: 0 auto; width: 100px;">
	<a href="board-write.do">글등록</a>
</div>

	<table>
		<tr>
			<td colspan="6">전체 게시글 수: ${pagination.count } </td>
		</tr>
		
		<tr>
			<th>NO.</th>
			<th>제목</th>
			<th>내용</th>
			<th>조회수</th>
			<th>작성자</th>
			<th>작성일시</th>
		</tr>
		<c:forEach items="${list }" var="item" varStatus="status">
			<tr>
				<td><a href="board-detail.do?b_idx=${item.b_idx}">${item.rownum} </a></td>
				<td>${item.b_tt}</td>
				<td>${item.b_con}</td>
				<td>${item.b_ct}</td>
				<td>${item.u_name}</td>
				<td>${item.b_date}</td>
			<tr>	
		</c:forEach>	
</table>

	<div>
		<ul>
			<c:choose>
				<c:when test = "${ pagination.page > pagination.pageUnit }"> 
					<li>
						<a href="board-list.do?page=${pagination.prevPage}">◀</a>
					</li>
				</c:when>
			</c:choose> 
			<c:forEach var="i" begin="${pagination.startPage}" end="${pagination.endPage}" step="1">
				<c:choose>
					<c:when test="${ pagination.page eq i }">
						
						<li style="background-color:#ededed;">
							<span>${i}</span>
						</li>
					</c:when>
					<c:when test="${ pagination.page ne i }">
						<li>
							<a href="board-list.do?page=${i}">${i}</a>
						</li>
					</c:when>
				</c:choose>
					
			</c:forEach>
			 <c:choose>
				<c:when test="${ pagination.nextPage <= pagination.lastPage }">
					<li style="">
						<a href="board-list.do?page=${pagination.nextPage}">▶</a>
					</li>
				</c:when>
			</c:choose> 
		</ul>			
	</div>
</body>	
</html>
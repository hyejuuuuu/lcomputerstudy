
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
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
		<tr>
			<td>조회수</td>
			<td>${board.b_ct}</td>
		</tr>
		<tr>	
			<td>작성일시</td>
			<td>${board.b_date}</td>
		</tr>
		</table>
		
	
		
		<c:if test="${board.u_idx eq sessionScope.user.u_idx}"></c:if></p>
			
		<div>
		
				<c:if test="${board.u_idx eq sessionScope.user.u_idx}">
					<a href="board-edit.do?b_idx=${board.b_idx }"  style="font-weight:700;background-color:blue;color:#fff;">수정</a>
				</c:if>
				
				<c:if test="${(sessionScope.user.u_role eq 'admin') or (board.u_idx eq sessionScope.user.u_idx) }">
				<a href="board-delete.do?b_idx=${board.b_idx }" style="background-color:red;color:#fff;">삭제</a>
				</c:if>
			
				<a href="board-Reply.do?b_idx=${board.b_idx }&b_gr=${board.b_gr}&b_or=${board.b_or}&b_de=${board.b_de}" style="whidth:5%;font-weight:800;background-color:grey;color:#fff;">답글작성</a>
			
			
	
		</div>
	
		
	
	
	
	
<!-- 댓글 -->

	<div class="comment">
			
		<div class="w3-border w3-padding">댓글 작성</div>
		<form action="comment-write.do" method ="POST" name ="comment">	
		
			<textarea name="content" id="c_contents" rows="5" cols="50" placeholder="댓글 작성하기"></textarea>	
			<input type="hidden" name="b_idx" value="${board.b_idx}">
			<button type="button" id="btnComment" value="등록"  >등록하기</button>
		</form>
	</div>
	
	<!-- 댓글없는 원글은 안뜨게 해야함(수정중) -->
	<div id="commentList">
	
	 
		<c:forEach items="${commentList }" var="comment" varStatus="status">
		<div>
		
			<!-- 작성자,작성날짜 -->
			<div style = width:150px>
			
				<div>
				 <div style="text-align: left;"><c:forEach var="j" begin="1" end="${comment.c_de}"> > </c:forEach>${comment.user.u_name}</div>
				 <font size ="2" color ="lightgray">${comment.c_date }</font>
				 </div>
			</div>
			<!--내용-->
			<div style="width:550px;" >
					${comment.c_con }
			</div>
			<!-- 버튼 --><!-- 댓글작성자만 수정,삭제 가능하게 해야함 -->
			<div style=width:200px>
				<div style ="text-align: left;">
					<button type="button" class="commentReplyBtn">답변</button>
					
					<button type="button" class="commentEditBtn">수정</button>
					<button type="button" class="commentDeleteBtn" c_idx="${comment.c_idx}"  b_idx="${board.b_idx}">삭제</button>			
				</div>
			</div>
			<hr>
		</div>	
		<div style="display: none;" class="commentReplyDiv">
		<!--  <form action="comment-Reply-Process.do" method ="POST" name ="commentReply">-->	
				<textarea rows="3" cols="50"  class="cc_contnets" placeholder="댓글 답글작성"></textarea>
				<button type="button" value="등록" class="crpBTN" c_gr="${comment.c_gr}" c_or="${comment.c_or}" c_de="${comment.c_de}">등록</button>
				<button type="button"  class="replyCancel" value="cancel" >취소</button>
				
				<input type="hidden" name="b_idx" value="${board.b_idx}">
				
				
		
		</div>
		<!-- 수정버튼 (수정중) -->
		<div style="display: none;" class="commentEditDiv">
			
				<textarea rows="2" cols="80" name="content" class ="ed_contnets">${comment.c_con }</textarea>
				<button type="button" value="수정완료" class="edBTN" b_idx="${board.b_idx }" c_idx="${comment.c_idx }">수정</button>
				<button type="button"  class="editCancel" value="cancel2" >취소</button>
			
				
			
		</div>	
	</c:forEach>
</div>
<script>

	$(document).on('click', '.commentReplyBtn', function () {
		$(this).parent().parent().parent().next().css('display', '');
	});
	$(document).on('click', '.replyCancel', function () {
		$(this).parent().css('display', 'none');
	});
	$(document).on('click', '.commentEditBtn', function () {
		$(this).parent().parent().parent().next().next().css('display', '');
	});
	$(document).on('click', '.editCancel', function () {
		$(this).parent().css('display', 'none');
	});
	
	$(document).on('click', '#btnComment', function () {
		//let contents = $(this).prev().prev().val();
		let contents = $('#c_contents').val();
		
		$.ajax({
		  method: "POST",
		  url: "aj-comment-write.do",
		  data: { b_idx: '${board.b_idx}',c_con: contents }
		})
		.done(function( html ) {
		  console.log(html);
		  $('#commentList').html(html);
		  $('#c_contents').val("");
		});
	});
	//답변등록버튼 ajax//	
	$(document).on('click', '.crpBTN', function () {
		let contents = $(this).prev().val();
		let c_gr = $(this).attr('c_gr');
		let c_or = $(this).attr('c_or');
		let c_de = $(this).attr('c_de');
		
		$.ajax({
		  method: "POST",
		  url: "aj-comment-reply-process.do",
		  data: { 
			  b_idx: '${board.b_idx}',
			  c_con:contents,
			  c_gr: c_gr,
			  c_or: c_or,
			  c_de:c_de
		  }
		})
		.done(function( html ) {
		  console.log(html);
		  $('#commentList').html(html).val("");
		  $('.cc_contents').val("");
		});
	});
	
	//수정버튼 ajax//
	$(document).on('click', '.edBTN', function () {
		let contents = $(this).prev().val();
		let cIdx = $(this).attr('c_idx');
		
		$.ajax({
			  method: "POST",
			  url: "aj-comment-edit.do",
			  data: {
			  		 b_idx: '${board.b_idx}',
					 c_con: contents,
					 c_idx: cIdx
				}
			})
			.done(function( html ) {
			  console.log(html);
			  $('#commentList').html(html);
			  $('.ed_contents').val("");
			});
		});
	//ajax 삭제//
	$(document).on('click', '.commentDeleteBtn', function () {
		let cIdx = $(this).attr('c_idx');
		
		$.ajax({
			  method: "POST",
			  url: "aj-comment-delete.do",
			  data: {
			  		 b_idx: '${board.b_idx}',
					 c_idx:cIdx
				}
			})
			.done(function( html ) {
			  console.log(html);
			  $('#commentList').html(html);
			  
			});
		});
	
</script>

</body>
</html>
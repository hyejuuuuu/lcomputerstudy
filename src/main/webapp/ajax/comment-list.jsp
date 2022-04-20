<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		
		  <textarea rows="3" cols="50" name="content" placeholder="댓글 답글작성" class="cc_contnets"></textarea>
			<button type="button" value="등록" class="crpBTN" c_gr="${comment.c_gr}" c_or="${comment.c_or}" c_de="${comment.c_de}" >등록</button>
			<button type="button"  class="replyCancel" value="cancel" >취소</button>
			<input type= "hidden" name="b_idx" value=" ${board.b_idx}">
			
			

	</div>
	<!-- 수정버튼 (수정중) -->
	<div style="display: none;" class="commentEditDiv">
			<textarea rows="2" cols="80" name="content" class ="ed_contnets">${comment.c_con }</textarea>
			<button type="button" value="수정완료" class="edBTN" b_idx="${board.b_idx }" c_idx="${comment.c_idx }">수정</button>
			<button type="button"  class="editCancel" value="cancel2" >취소</button>
			
			
		
	</div>	
</c:forEach>
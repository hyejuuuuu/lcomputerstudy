<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>edit Page</title>
</head>
<body>
	<h1> 게시글 수정</h1>

		 	<form action="board-edit-process.do" name="Bedit" method="post">
		 		<div></div><input type="hidden" name="b_idx" value="${board.b_idx }"></div>
		 		<div> 작성자 : ${board.user.u_name}</div>
		 		<div> 제목 :</div>
		 		 <div><input type ="text" name = "title" value="${board.b_tt }"></div>
		 		<div> 내용 :</div>
		 		<div> <input type ="text" name = "content" value="${board.b_con }"></div>
		 		
		 		
		 		<div> 
		 			  <button type ="submit"> 완료 </button>
		 			  <button type ="submit"> 삭제 </button>
		 		</div>
		 		
		 	</form>
		 	

</body>
</html>
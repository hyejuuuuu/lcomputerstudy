<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 등록페이지</title>
</head>
<body>
<h1>글 등록</h1>
<form action="board-write-process.do" name = "board" method="post">
	<input type="hidden" name="b_idx" value="${sessionScope.board.b_idx}">
	<p> 제목 : <input type="text" name="title"></p>
	<p> 내용 : <input type="text" name="content"></p>
	<p> <input type="submit" value="등록"></p>
</form>
</body>
</html>
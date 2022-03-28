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
<form action="board-Reply-Process.do" name = "board" method="post">
	<input type="hidden" name="b_gr" value="${board.b_gr}">
	<input type="hidden" name="b_or" value="${board.b_or}">
	<input type="hidden" name="b_de" value="${board.b_de}">
	<p> 제목 : <input type="text" name="title"></p>
	<p> 내용 : 
		<textarea name="content" rows="10" cols="100"></textarea>
	</p>
	<p> <input type="submit" value="등록"></p>
	<p> <button type="button" onclick="moveList()">취소</button></p>
</form>
<script>
function moveList () {
	location.href = 'board-list.do';
}
</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>reply</title>
</head>
<body>
<h1>답글 작성</h1>
<form action="board-reply.do" name = "board" method="post">
	<input type="hidden" name="b_idx" value="${sessionScope.board.b_idx}">
	<input type="hidden" name="u_name" value="{board.u_name}"/>
	<input type="hidden" name="b_gr" value="{board.b_gr}"/>
	<input type="hidden" name="b_or" value="{board.b_or}"/>
	<input type="hidden" name="b_de" value="{board.b_de}"/>
	
	<table width="700" border="3" bordercolor="lightgrey" align="center">
	
		<tr>	
			<td>작성자</td>
			<td>${board.u_name}</td>
		</tr>
		<tr>	
			<td>내용</td>
			<td>${board.b_con}</td>
		</tr>
		<tr>	
			<td>작성일시</td>
			<td>${board.b_date}</td>
		</tr>
		<tr>	
		
		<tr align="center" valign="middle">
			<td colspan="5">
				<input type="reset" value="작성취소" >
				<input type="submit" value="등록" >
				<input type="button" value="목록"  onclick="javascript:history.go(-1)">
			</td>
		</tr>											
	</table>
</form>

</body>
</html>
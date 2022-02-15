<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>선택</title>
</head>
<style>
	body{
		margin:0;
		padding:0;
	}
	div:nth-child(1){
		background-color:rgba(75,189,217,0.1);
		padding:10px 30px;
		font-size:50px;
		font-weight:700;
		text-align: center;
	}
	div:nth-child(2){
		width:200px;
		padding:20px 850px;
		text-align: center;
	}
	div ul{
		width:100%;
		text-align: center;
		list-style: none;
		padding:0;
	}
	div ul li{
		padding:10px;
		font-size: 1rem;
		background-color: rgba(75,189,217,0.1);
		border-radius: 10px;
		margin: 10px;
		font-weight: 700;
		box-shadow: 2px 3px 3px #bbbbbb;
		
	}
	div ul li a{
		text-decoration: none;
		color: #333333;
	}
</style>
<body>
<div>
${sessionScope.board.u_name } 회원 님 
</div>
<div>
	<ul>
		<li><a href="board-list.do">게시글 목록</a></li>
	</ul>
</div>
<body>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>include 지시어</h1>
	
	<div style="border:1px solid black">
		<%@ include file = "01_ScriptingElement.jsp"%>
	</div>
	
	* 포함(include)한 jsp상에 선언되어 있는 변수를 가져다가 사용 가능
	1부터 100까지의 총합계 : <%= sum %>

</body>
</html>
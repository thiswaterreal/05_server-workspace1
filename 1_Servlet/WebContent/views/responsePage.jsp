<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	h2 {color:red;}
	#name{color:orange;}
	#age{color:blue;}
	#city{color:yellowgreen;}
	#height{color:purple;}
	#gender{color:green;}
</style>

</head>
<body>
	
	<%
		// 스크립틀릿 (scriptlet) == html문서 내에 자바코드를 쓸 수 있는 영역
		// int a = 10;

		// request.getAttribute("키") : Object (그에 해당하는 벨류값)
		// 반환형이 Object. 최상위(모든것의 부모)라서 다 담담,뺌뺌 가능
		
		String name = (String)request.getAttribute("name");
		// '작은박스 = 큰박스'  이렇게 담는다는게 말이 안됨..
		// 즉, getAttribute 반환형이 Object라서 String 자료형으로 담고싶다면 (다운캐스팅==강제형변환) 해줘야함
		int age = (int)request.getAttribute("age");
		String city = (String)request.getAttribute("city");
		double height = (double)request.getAttribute("height");
		String gender = (String)request.getAttribute("gender");
		String[] foods = (String[])request.getAttribute("foods");
		// attribute에 있는거 뽑아와서 변수에 셋팅 끝.
	%>

	
	<h2>개인정보 응답화면 - POST</h2>
	
	<span id="name"><%= name %></span>님은
	<span id="age"><%= age %></span>살이며,
	<span id="city"><%= city %></span>에 사는,
	키는 <span id="height"><%= height %></span>cm 이고,
	
	성별은
	<% if(gender == null) { %>
		선택하지 않았습니다. <br> <!-- case1 -->
	<% }else { %>
		<% if(gender.equals("M")) { %>
			<span id="gender">남자</span>입니다. <br> <!-- case2_1 -->
		<% }else { %>
			<span id="gender">여자</span>입니다. <br> <!-- case2_2 -->	
		<% } %>
	<% } %>
	
	좋아하는 음식은
	<% if(foods == null) { %>
		없습니다.
	<% }else { %>
	
		<ul>
			<% for(int i=0; i<foods.length; i++) { %>
				<li><%= foods[i] %></li>
			<% } %>
		</ul>
		
		<!-- (없애고 본 것)
		<ul>
			for(int i=0; i<foods.length; i++) {
				<li>foods[i]</li>
			}
		</ul>
		<ul>
			for(String f : foods) {
				<li>f</li>
			}
		</ul>
		 -->
		
	<% } %>
	
	
	
	
	
	
	
	
	
</body>
</html>
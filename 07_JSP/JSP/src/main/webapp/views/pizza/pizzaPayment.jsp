<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String name = (String)request.getAttribute("name");
	String phone = (String)request.getAttribute("phone");
	String address = (String)request.getAttribute("address");
	String message = (String)request.getAttribute("message");
	
	String pizza = (String)request.getAttribute("pizza");
	String[] toppingList = (String[])request.getAttribute("toppingList");
	String[] sideList = (String[])request.getAttribute("sideList");
	String payment = (String)request.getAttribute("payment");
	int price = (int)request.getAttribute("price");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>피자 결제 페이지</h1>
	<h2>주문내역</h2>
	<h4>[ 주문자 정보 ]</h4>

	<ul>
		<li>성함 : <%=name%></li>
		<li>전화번호 : <%=phone%></li>
		<li>주소 : <%=address%></li>
		<li>요청사항 : <%=message%></li>
	</ul>
</body>
</html>
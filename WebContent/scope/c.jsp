<%@page import="shop.Member"%>
<%@page import="shop.Cart"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
Cart cart=(Cart)session.getAttribute("cart");
%>
<%
Member mem=(Member)session.getAttribute("mem");
%>
<h2><%=mem.getUname() %>님 환영합니다</h2>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="#888888">
	장바구니에 담겨진 상품은 [<%=cart.getName() %>] 입니다<br>
	장바구니에 담겨진 갯수은 [<%=cart.getEa() %>] 입니다<br>
	장바구니에 담겨진 가격은 [<%=cart.getPrice() %>] 입니다<br>
	
</body>
</html>
<%@page import="shop.Member"%>
<%@page import="shop.Cart"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
String name=request.getParameter("name");
String ea=request.getParameter("ea");
String price=request.getParameter("price");
//낱개로 된 데이터를 하나의 인스턴스에 몰아넣자
Cart ct=new Cart();
ct.setName(name);
ct.setEa(Integer.parseInt(ea));
ct.setPrice(Integer.parseInt(price));

//현재 클라이언트가 사용가능한세션에 cart를 담는다.
session.setAttribute("cart", ct);

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
<body bgcolor="#cccccc">
당신이 입력한 상품은 "<%=ct.getName() %>"입니다.<br>
당신이 입력한 갯수는 "<%=ct.getEa() %>"입니다.<br>
당신이 입력한 가격은 "<%=ct.getPrice() %>"입니다.
<p>
<a href="c.jsp">장바구니 담기</a>
</body>
</html>
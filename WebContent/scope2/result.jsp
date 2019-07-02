<%@page import="domain.User"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
User dto=(User)request.getAttribute("dto");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="pink">
최종적인 결과 출력<p>
유저:<%=dto.getUser() %>
<br>
연락처:<%=dto.getPhone() %>
</body>
</html>
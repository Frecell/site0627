<%@page import="shop.Member"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
//정보를 담고 유지할수 있는 내장객체는?
//3개 : request < session < application
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
<script>
function send(){
	form1.action="b.jsp";
	form1.method="post";
	form1.submit();
}
</script>
</head>
<body>
	<form name="form1">
		<input type="text" placeholder="제품명.." name="name"/>
		<input type="text" placeholder="갯수.." name="ea"/>
		<input type="text" placeholder="가격.." name="price"/>
		<button onclick="send()">전송</button>
	</form>
</body>
</html>
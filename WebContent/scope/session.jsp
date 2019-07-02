<%@ page contentType="text/html; charset=UTF-8"%>
<%
//클라이언트가 최초의 접속이라면 Session 객체가 생성되고 
//고유한 아이디가 발급된다.
	String id=session.getId();//tomcat이 클라이언트에 발급해준 고유세션ID
	
	//클라이언트의 브라우저에 쿠키로 기록됨..
	out.print("발급받은 ID:"+id);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>
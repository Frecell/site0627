<%@ page contentType="text/html; charset=UTF-8"%>
<%
String color=request.getParameter("color");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
function send(){
	form1.action="test.jsp";
	form1.submit();
}
</script>
</head>
<body bgcolor=<%=color%>>
	<form name="form1">
		<select name="color">
			<option value="red">빨간색</option>
			<option value="blue">파란색</option>
			<option value="green">초록색</option>
		</select>
	</form>
	<button onclick="send()">색상변경</button>
</body>
</html>
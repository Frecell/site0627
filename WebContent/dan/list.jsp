<%@ page contentType="text/html; charset=UTF-8"%>
<%
int idan=2;
String dan=request.getParameter("dan");
if(dan!=null){
idan=Integer.parseInt(dan);
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
/*window.addEventListener("load",function(){
	var sel=document.querySelector("select");
	
	sel.addEventListener("change",function(){
		if(this.value!=0){
			
		}
	})
})*/
function change(){
	form1.action="list.jsp";
	form1.submit();
}
</script>
</head>
<body>
<form name="form1">
	<select name="dan" onchange="change()">
		<option value="0">단 선택</option>
		<%
			for(int a=2; a<=9; a++){
		%>
				<option <%if(a==idan){%>selected<%} %> value="<%=a%>"><%=a %>단</option>
		<%
			}
		%>
	</select>
</form>
<%
	for(int i=1; i<10; i++){
		out.print("<br>"+idan+" * "+i+" = "+(idan*i));
	}
%>
</body>
</html>
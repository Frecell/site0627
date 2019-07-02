<%@ page contentType="text/html; charset=UTF-8"%>
<%
// jsp 는 실행직전에 서블릿으로 변경된다
// 왜 jsp 는 왜 제공되는것일까
// -순수한 서블릿만을 이용해 문서의 태그와 컨텐츠내용을 
// out.print()안에 넣어야한다. 따라서 디자인 영역의 효율성이 떨어진다.

// 개발자는 jsp에서 지원하는 내장객체라 불리는 객체들이 서블릿으로 변경될때 어떤 자료형인지 알고 있어야한다
// request객체의 자료형 : HttpServletRequest
// response 객체 : 응답정보를 보유한 객체 HttpServletResponse
// session 객체 : 클라이언트와 연결을 지속한것처럼 효과를 내기위한 객체 HttpSession
// application 객체 : 웹 어플리케이션 전역적 정보를 보유한 객체 ServletContext

// 내장객체중 request < session < application 의 생명력을 이해..
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
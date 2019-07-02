<%@page import="shop.Member"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%!
	String user="scott";
	String pw="1234";
%>
<%
	//클라이언트가 전송한 데이터를 이용하여 로그인 처리
	request.setCharacterEncoding("utf-8");
	String uname=request.getParameter("uname");
	String psw=request.getParameter("psw");
	
	if(uname.equals(user) && psw.equals(pw)){
		out.print("반갑습니다.");

		Member mem=new Member();
		mem.setUname(uname);
		mem.setPsw(psw);
		session.setAttribute("mem", mem);
		//브라우저로 하여금 지정한 url로 재접속 유도
		response.sendRedirect("/scope/a.jsp");
		
	}else{
		out.print("존재하지 않는 ID, 비밀번호 입니다.");
	}
%>
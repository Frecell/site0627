package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.User;

public class Temp extends HttpServlet{
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		type(req,resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		type(req,resp);
	}
	
	public void type(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String user=req.getParameter("user");
		String phone=req.getParameter("phone");
		
		User dto=new User();
		dto.setUser(user);
		dto.setPhone(phone);
		
		//현재 이요청에 대헤 응답을 실시하지 말고, 서버상에 있는 result.jsp로 포워딩(전달)시키자.
		RequestDispatcher dis=null;
		dis=req.getRequestDispatcher("/scope2/result.jsp");
		//요청 객체에 원하는 데이터 심어보기.
		//request객체가 죽지않는 동안에는 데이터를 꺼내쓸수있다.
		req.setAttribute("dto", dto);
		//포워딩 하기
		dis.forward(req, resp);
	}
}

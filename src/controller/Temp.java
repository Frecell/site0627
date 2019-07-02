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
		
		//���� �̿�û�� ���� ������ �ǽ����� ����, ������ �ִ� result.jsp�� ������(����)��Ű��.
		RequestDispatcher dis=null;
		dis=req.getRequestDispatcher("/scope2/result.jsp");
		//��û ��ü�� ���ϴ� ������ �ɾ��.
		//request��ü�� �����ʴ� ���ȿ��� �����͸� ���������ִ�.
		req.setAttribute("dto", dto);
		//������ �ϱ�
		dis.forward(req, resp);
	}
}

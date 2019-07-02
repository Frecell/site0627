<%@page import="util.FileManager"%>
<%@page import="java.io.File"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%
	// 파일 업로드란? 클라이언트가 전송한 파일 데이터를 서버의 특정위치에 저장하는것
	// 네트워크 + 스트림 등 많은기술이 필요..

	//우리가 선택한 라이브러리는 oreilly사에서 제작한 책의 예제였던 cos.jar 를 이용할것.

	/* request.setCharacterEncoding("utf-8");
	String title=request.getParameter("title");
	
	out.print("전송한 제목_"+title); */

	//아래의 코드는 윈도우에서만 돌아감. 즉 환경에 지배를 받는다.
	//해결책) 모든플랫폼에서 실행하려면 경로를 수정할 필요가 있다
	//방법) 개발자가 경로를 하드코딩하지말자.
	//String saveDir="D:/final_workspace/site0627/WebContent/data";

	//jsp의 내장객체중 application 내장객체를 이용하면 웹 어플리케이션의
	//전역적 정보를 가지고 있으므로, 특정 디렉토리의 하드디스크상 풀경로로 구해준다.
	String saveDir = application.getRealPath("/data");
	long time=System.currentTimeMillis();
	out.print(saveDir);
	
	int maxSize=5*1024*1024;
	String encoding="UTF-8";
	MultipartRequest multi = new MultipartRequest(request, saveDir, maxSize, encoding);
	File file=multi.getFile("myfile");
	
	String ext = FileManager.getExt(file.getName());
	File destFile=new File(saveDir+"/"+time+"."+ext);
	file.renameTo(destFile);
	
	//방금 올려진 이미지명을 현재날짜와 조합하여 이름바꾸기	
	out.print(destFile.getName());
	
	//제목 받기
	String title=multi.getParameter("title");
	out.print("<br>제목은\""+title+"\"입니다.");
	
%>
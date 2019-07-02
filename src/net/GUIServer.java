package net;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUIServer extends JFrame{
	JPanel p;
	JTextField tf;
	JButton bt;
	JScrollPane scroll;
	JTextArea area;
	ServerSocket server; // 접속 청취용 서버소켓
	Thread thread;//지연, 대기, 루프등에 사용하기 위함.
	//메인쓰레드를 보호하기 위해서.
	
	
	
	public GUIServer() {
		p = new JPanel();
		tf = new JTextField("7777");
		bt = new JButton("start");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		thread = new Thread() {
			//개발자는 독립수행할 코드를 run에 넣어두면 
			//JVM이 알아서 실행해준다(runnable 에 넣어주자..)
			public void run() {
				StartServer();
				
			}
		};
				
		p.add(tf);
		p.add(bt);
		add(p,BorderLayout.NORTH);
		add(scroll);
		area.setEditable(false);
		bt.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				thread.start();
			}
		});
		
		setVisible(true);
		setSize(300,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void StartServer() {
		int port = Integer.parseInt(tf.getText());
		try {
			server = new ServerSocket(port);
			bt.setEnabled(false);
			area.append("서버 가동 시작!\n");
			area.append("클라이언트 접속 대기중...\n");
			Socket client=server.accept();//클라이언트 접속 청취(대기...)
			//자바는 쓰레드 기반이기때문에, 우리가 실행분이라 불렀던 그 실행주체가 바로 
			//main thread다 메인쓰레드의 역할은 프로그램을 운영하는 역할을한다.
			//특히 GUI 프로그램에서는 사용자의 이벤트감지, 그래픽 렌더링등등...
			//따라서 메인쓰레드를 대기, 루프, 지연 상태로 빠트리면 프로그램자체가 불통.
			//안드로이드에서는 컴파일 에러대상이기도 하다.
			String ip = client.getInetAddress().getHostAddress();
			area.append(ip+"님 접속\n");
			
			//접속자가 감지되면 스트림을 뽑아서 대화나누기!
			BufferedReader buffr=new BufferedReader(new InputStreamReader(client.getInputStream()));
			BufferedWriter buffw=new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			
			while(true) {
				//대화시작
				String msg=buffr.readLine();//듣기
				//보내기
				buffw.write(msg+"\n");//줄바꿈을 해줘야 보내줌.. 
				buffw.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new GUIServer();
	}
}

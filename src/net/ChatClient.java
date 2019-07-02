package net;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class ChatClient extends JFrame{
	JTextField txt;
	JTextArea area;
	JButton bt_con;
	JScrollPane scroll;
	JPanel p_s, p_n;
	Choice ch;
	JTextField t_port;
	String[] ip = {"55","51","67","15","60","143","79","41"};
	Socket client;
	BufferedReader buffr;
	BufferedWriter buffw;
	
	
	public ChatClient() {
		txt = new JTextField(15);
		area = new JTextArea();
		scroll = new JScrollPane(area);
		p_s = new JPanel();
		ch = new Choice();
		p_n = new JPanel();
		bt_con = new JButton("접속");
		t_port = new JTextField("7777");
		//아이피 설정
		for(int i=0; i<ip.length;i++){
			ch.add("192.168.0."+ip[i]);
		}
		
		//버튼조합
		p_n.add(ch);
		p_n.add(t_port);
		p_n.add(bt_con);
		add(p_n,BorderLayout.NORTH);
		p_s.add(txt);
		add(scroll,BorderLayout.CENTER);
		add(p_s,BorderLayout.SOUTH);
		bt_con.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect();
			}
		});
		txt.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					send(txt.getText());
				}
			}
		});
		area.setEditable(false);
		setVisible(true);
		setSize(300,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void connect() {
		String ip=ch.getSelectedItem();
		int port=Integer.parseInt(t_port.getText());
		try {
			client = new Socket(ip, port);
			//소켓으로부터 스트림 얻기
			buffr=new BufferedReader(new InputStreamReader(client.getInputStream()));
			buffw=new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void listen(String msg) {
		
		try {
			msg=buffr.readLine();
			area.append(msg+"\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void send(String msg) {
		//서버측에 데이터 전송
		try {
			buffw.write("192.168.0.55>>"+msg+"\n");
			//문자열의 끝에 줄바꿈이 있어야 한줄의 끝을 이해함.
			//서버측에서는 문자열의 끝이 없었기때문에 계속 대기함..
			buffw.flush();
			//버퍼처리된 출력 스트림계열에서 이 메서드를 호출하면 
			//스트림에 현재 쌓여있는 데이터를 모두방출
			listen(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		txt.setText("");
	}
	
	public static void main(String[] args) {
		new ChatClient();
	}
}

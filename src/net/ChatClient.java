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
		bt_con = new JButton("����");
		t_port = new JTextField("7777");
		//������ ����
		for(int i=0; i<ip.length;i++){
			ch.add("192.168.0."+ip[i]);
		}
		
		//��ư����
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
			//�������κ��� ��Ʈ�� ���
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
		//�������� ������ ����
		try {
			buffw.write("192.168.0.55>>"+msg+"\n");
			//���ڿ��� ���� �ٹٲ��� �־�� ������ ���� ������.
			//������������ ���ڿ��� ���� �����⶧���� ��� �����..
			buffw.flush();
			//����ó���� ��� ��Ʈ���迭���� �� �޼��带 ȣ���ϸ� 
			//��Ʈ���� ���� �׿��ִ� �����͸� ��ι���
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

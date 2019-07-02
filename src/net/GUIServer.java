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
	ServerSocket server; // ���� û��� ��������
	Thread thread;//����, ���, ����� ����ϱ� ����.
	//���ξ����带 ��ȣ�ϱ� ���ؼ�.
	
	
	
	public GUIServer() {
		p = new JPanel();
		tf = new JTextField("7777");
		bt = new JButton("start");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		thread = new Thread() {
			//�����ڴ� ���������� �ڵ带 run�� �־�θ� 
			//JVM�� �˾Ƽ� �������ش�(runnable �� �־�����..)
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
			area.append("���� ���� ����!\n");
			area.append("Ŭ���̾�Ʈ ���� �����...\n");
			Socket client=server.accept();//Ŭ���̾�Ʈ ���� û��(���...)
			//�ڹٴ� ������ ����̱⶧����, �츮�� ������̶� �ҷ��� �� ������ü�� �ٷ� 
			//main thread�� ���ξ������� ������ ���α׷��� ��ϴ� �������Ѵ�.
			//Ư�� GUI ���α׷������� ������� �̺�Ʈ����, �׷��� ���������...
			//���� ���ξ����带 ���, ����, ���� ���·� ��Ʈ���� ���α׷���ü�� ����.
			//�ȵ���̵忡���� ������ ��������̱⵵ �ϴ�.
			String ip = client.getInetAddress().getHostAddress();
			area.append(ip+"�� ����\n");
			
			//�����ڰ� �����Ǹ� ��Ʈ���� �̾Ƽ� ��ȭ������!
			BufferedReader buffr=new BufferedReader(new InputStreamReader(client.getInputStream()));
			BufferedWriter buffw=new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			
			while(true) {
				//��ȭ����
				String msg=buffr.readLine();//���
				//������
				buffw.write(msg+"\n");//�ٹٲ��� ����� ������.. 
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

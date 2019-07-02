package homework;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatA extends JFrame{
	JTextField tfA;
	JTextArea taA;
	JButton btA;
	JLabel laA;
	JScrollPane spA;
	public ChatA() {
		tfA = new JTextField();
		taA = new JTextArea();
		btA = new JButton("채팅2열기");
		laA = new JLabel();
		spA = new JScrollPane(taA);
		
		laA.setLayout(new GridLayout(1,2));
		laA.add(tfA);
		laA.add(btA);
		
		setLayout(new FlowLayout());
		add(tfA);
		add(laA);
		
		setVisible(true);
		setSize(400,500);
	}
	
	public static void main(String[] args) {
		new ChatA();
	}
}

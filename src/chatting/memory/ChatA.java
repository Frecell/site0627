package chatting.memory;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class ChatA extends JFrame{
	JTextField txt;
	JTextArea area;
	JButton bt;
	JScrollPane scroll;
	JPanel p;
	ChatB chatB;
	
	public ChatA() {
		txt = new JTextField(15);
		area = new JTextArea();
		bt = new JButton("ChatB");
		scroll = new JScrollPane(area);
		p = new JPanel();
		
		
		p.add(txt);
		p.add(bt);
		add(scroll,BorderLayout.CENTER);
		add(p,BorderLayout.SOUTH);
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openB();
			}
		});
		txt.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					String msg=txt.getText();
					area.append("ChatA>"+msg+"\n");
					chatB.area.append("ChatA>"+msg+"\n");
					txt.setText("");
				}
			}
		});
		area.setEditable(false);
		setVisible(true);
		setSize(300,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void openB() {
		chatB = new ChatB(this);
	}
	
	public static void main(String[] args) {
		new ChatA();
	}
}

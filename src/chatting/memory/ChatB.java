package chatting.memory;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatB extends Frame{
	JTextField txt;
	JTextArea area;
	JScrollPane scroll;
	JPanel p;
	ChatA chatA;
	
	public ChatB(ChatA chatA) {
		txt = new JTextField(15);
		area = new JTextArea();
		scroll = new JScrollPane(area);
		p = new JPanel();
		this.chatA=chatA;
		
		p.add(txt);
		add(scroll,BorderLayout.CENTER);
		add(p,BorderLayout.SOUTH);
		txt.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					String msg=txt.getText();
					area.append("ChatB>"+msg+"\n");
					chatA.area.append("ChatB>"+msg+"\n");
					txt.setText("");
				}
			}
		});
		area.setEditable(false);
		setVisible(true);
		setSize(300,400);
		
	}
}

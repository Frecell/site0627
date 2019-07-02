package homework;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatB extends JFrame{
	JTextField tfB;
	JTextArea taB;
	JLabel laB;
	JScrollPane spB;
	public ChatB() {
		tfB = new JTextField();
		taB = new JTextArea();
		laB = new JLabel();
		
		setSize(400,500);
	}
}

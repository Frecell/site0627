package site0627;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MemoApp extends JFrame{
	//��� ������ : �ǹ� �ο���. ������ ����..
	public static final int MY_FONT_SIZE=18;
	JMenuBar bar;
	JMenu[] menu;
	JMenuItem[] item;
	String[] menuTitle={"����","����","����","����","����"};
	String[] itemTitle={"���� �����","����","����","�ٸ��̸����� ����","������ ����","�μ�","������"};
	JTextArea area;
	JScrollPane scroll;
	JFileChooser chooser;
	//FileInputStream�� ����Ʈ ��� ��Ʈ���̹Ƿ� 1byte�� �����Ѵ�.
	//���� �ѱ۰� ���õ�, �� ���̱� ��� �ƴѰ��� ������ ���δ�.
	//2byte�� ��� �����Ҽ��ִ� ��Ʈ���� ���ڱ�� ��Ʈ���� ���.
	//���� ��� ��Ʈ���� �ַ� ~Reader(�Է�), ~Writer(���)
	FileInputStream fis;
	FileReader reader;
	//���۱�� ��Ʈ�� : ������ ������ ���پ� ��Ƽ�, �ٸ������ �׶� �ѹ� �Է��� ����Ű�� ȿ������ ��Ʈ��
	//API�� ���� ���ξ Buffered~..
	BufferedReader buffr;
	
	public MemoApp() {
		bar = new JMenuBar();
		menu = new JMenu[menuTitle.length];
		item = new JMenuItem[itemTitle.length];
		area = new JTextArea();
		scroll = new JScrollPane(area);
		chooser = new JFileChooser("D:/final_workspace");
		
		for(int i=0; i<menuTitle.length; i++) {
			menu[i] = new JMenu(menuTitle[i]);
			bar.add(menu[i]);
		}//for
		
		for(int i=0; i<itemTitle.length; i++) {
			item[i] = new JMenuItem(itemTitle[i]);
			if(i==4 || i==6) {
				menu[0].addSeparator();
			}
			menu[0].add(item[i]);
			
			//�̺�Ʈ ����
			item[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//�̺�Ʈ �ҽ��� �������� �˾� ����
					JMenuItem obj=(JMenuItem)e.getSource();
		
					if(obj.getText().equals("����")) {
						openFile();
					}//if
				}//actonPerformed
			});
		}//for
		
		//������ �̺�Ʈ ����
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//�����ִ� DB or ��Ʈ���� ����.
				//�޸� ����.. �޸� ������ PC�ٿ��.
				if(reader!=null) {
					try {
						reader.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}//catch
				}//if
				if(buffr!=null) {
					try {
						buffr.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}//catch
				}//if
				
				System.exit(0);//���μ��� ����
				
			}//windowClosing
		});
		
		//������ ��ü �����ϱ�.
		add(scroll);
		setJMenuBar(bar);
		area.setFont(new Font("consolas",Font.BOLD,MemoApp.MY_FONT_SIZE));
		setSize(800,650);
		setVisible(true);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}//cons
	
	
	public void openFile() {
		int result=chooser.showOpenDialog(this);
		if(result != JFileChooser.CANCEL_OPTION) {
			File file=chooser.getSelectedFile();
			String path=file.getAbsolutePath();		
			//�������� ���α׷����� ������ �о������.
			try {
				reader = new FileReader(path);
				buffr = new BufferedReader(reader);
				//������ ��Ʈ�� �����̿��Ͽ� ����Ʈ�� �о������
				String data=null;
				while(true) {
					data=buffr.readLine();
					if(data==null)break;
					area.append(data+"\n");
				}//while
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}//if
	}//openFile
	
	public static void main(String[] args) {
		new MemoApp();
	}//main

}//class

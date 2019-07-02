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
	//상수 사용목적 : 의미 부여함. 직관성 증가..
	public static final int MY_FONT_SIZE=18;
	JMenuBar bar;
	JMenu[] menu;
	JMenuItem[] item;
	String[] menuTitle={"파일","편집","서식","보기","도움말"};
	String[] itemTitle={"새로 만들기","열기","저장","다른이름으로 저장","페이지 설정","인쇄","끝내기"};
	JTextArea area;
	JScrollPane scroll;
	JFileChooser chooser;
	//FileInputStream은 바이트 기반 스트림이므로 1byte씩 이해한다.
	//따라서 한글과 관련된, 즉 영미권 영어가 아닌경우는 깨져서 보인다.
	//2byte씩 묶어서 이해할수있는 스트림인 문자기반 스트림을 사용.
	//문자 기반 스트림은 주로 ~Reader(입력), ~Writer(출력)
	FileInputStream fis;
	FileReader reader;
	//버퍼기반 스트림 : 데이터 단위를 한줄씩 모아서, 다모아지면 그때 한번 입력을 일으키는 효율적인 스트림
	//API에 거의 접두어가 Buffered~..
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
			
			//이벤트 구현
			item[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//이벤트 소스가 누구인지 알아 내기
					JMenuItem obj=(JMenuItem)e.getSource();
		
					if(obj.getText().equals("열기")) {
						openFile();
					}//if
				}//actonPerformed
			});
		}//for
		
		//윈도우 이벤트 구현
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//열려있는 DB or 스트림을 닫자.
				//메모리 누수.. 메모리 꽉차면 PC다운됨.
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
				
				System.exit(0);//프로세스 종료
				
			}//windowClosing
		});
		
		//생성된 객체 조합하기.
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
			//실행중인 프로그램에서 파일을 읽어들이자.
			try {
				reader = new FileReader(path);
				buffr = new BufferedReader(reader);
				//생성된 스트림 관을이용하여 바이트를 읽어들이자
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

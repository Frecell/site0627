package site0627;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class KeyboardInput {

	public static void main(String[] args) {
		//모든 입력스트림의 최상위 클래스
		//따라서 모든 입력도구는 이 클래스로 처리가 가능하다.
		//키보드와 관련 스트림은 이미 생성됨. 시스템적으로 생성됨.
		//따라서System클래스부터 얻을수있다.
		InputStream is=System.in;
		InputStreamReader reader=new InputStreamReader(is);
		
		try {
			int data=0;
			while(true) {
				data=reader.read();
				System.out.print((char)data);
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}

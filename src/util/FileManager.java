package util;

public class FileManager {
	//확장자 구하기
	public static String getExt(String path) {
		int index=path.lastIndexOf(".");
		System.out.println(index);
		
		return path.substring(index+1, path.length());
	}
	
//	public static void main(String[] args) {
//		System.out.println(getExt("...d/.a.sdf.asd.fa.sd.asasdf.gif"));
//	}
}


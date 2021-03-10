package kr.co.sist.javamemo.run;

import java.awt.Font;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import kr.co.sist.javamemo.JavaMemo;

/**
 * 메모장을 실행하기 위한 클래스
 * @author user
 */
public class RunJavaMemo {
	
	public RunJavaMemo() throws FileNotFoundException, IOException, ClassNotFoundException {
		//글꼴 정보를 저장한 파일에서 글꼴을 읽어들여 디자인객체(JavaMemo)에 전달
		Font font = null;
		ObjectInputStream ois = null;
		
		try {
			//파일에 스트림 연결
			ois = new ObjectInputStream(new FileInputStream("c:/dev/memo/memo.dat"));
			//파일에 기록된 객체 읽기
			font = (Font)ois.readObject();
		} finally {
			//스트림 연결 끊기
			if(ois != null) {ois.close();} //end if -> 혹시라도 글꼴파일이 없어도 실행되게 함
			//스트림에서 읽어들이기 실패해도 연결은 반드시 끊고, 사용자에게 프로그램은 제공되어야됨
			new JavaMemo( font );
		}// end finally
		
		
	}//RunJavaMemo

	public static void main(String[] args) {
		try {
			new RunJavaMemo();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}//main

}//class

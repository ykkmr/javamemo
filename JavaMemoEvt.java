package kr.co.sist.javamemo.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import kr.co.sist.javamemo.run.JavaMemo;
import kr.co.sist.javamemo.run.MemoFont;

/**
 *	JavaMemo 디자인의 이벤트처리 클래스
 * @author user
 */
public class JavaMemoEvt extends WindowAdapter implements ActionListener {

	private JavaMemo jm; //has a 관계
	
	public JavaMemoEvt(JavaMemo jm) {
		this.jm = jm;
	}
	@Override
	public void windowClosing(WindowEvent we) {
		jm.dispose();
	}//windowClosing

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == jm.getJmiFont() ) {//r글꼴에서 이벤트 발생
			memoFont();
		}//end if
	}//actionPerformed

	public void memoFont() {
		new MemoFont( jm ); //글꼴설정  JDialog 생성
	}//memoFont
	
}//class

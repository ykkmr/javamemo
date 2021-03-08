package kr.co.sist.javamemo.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import kr.co.sist.javamemo.run.JavaMemo;
import kr.co.sist.javamemo.run.MemoFont;

/**
 *	JavaMemo �������� �̺�Ʈó�� Ŭ����
 * @author user
 */
public class JavaMemoEvt extends WindowAdapter implements ActionListener {

	private JavaMemo jm; //has a ����
	
	public JavaMemoEvt(JavaMemo jm) {
		this.jm = jm;
	}
	@Override
	public void windowClosing(WindowEvent we) {
		jm.dispose();
	}//windowClosing

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == jm.getJmiFont() ) {//r�۲ÿ��� �̺�Ʈ �߻�
			memoFont();
		}//end if
	}//actionPerformed

	public void memoFont() {
		new MemoFont( jm ); //�۲ü���  JDialog ����
	}//memoFont
	
}//class

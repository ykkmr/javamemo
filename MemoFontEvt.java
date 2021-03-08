package kr.co.sist.javamemo.evt;

import java.awt.Choice;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import kr.co.sist.javamemo.run.JavaMemo;
import kr.co.sist.javamemo.run.MemoFont;

/**
 *	글꼴 디자인의 이벤트 처리 클래스
 * @author user
 */
public class MemoFontEvt extends WindowAdapter 
implements ActionListener, ListSelectionListener, ItemListener{

	private MemoFont mf;
	
	private String[] formatArr; //필요 ㄴ
	private String[] styleArr; //필요 ㄴ

	private JList<String> jlFormat, jlStyle, jlSize;
	
	private Font memoFont;//JTextArea의 폰트 정보를 저장하는 폰트객체. (미리보기(JLabel)와 같음)  //필요 ㄴ
	
	private Object jbtnConfirm, jbtnCancel;
	private JTextArea jtaNote;
	
	/**
	 * 	지원 글꼴 : "Dialog", "DialogInput", "Monospaced", "Serif", "SansSerif", "맑은 고딕", "궁서체" <br>
	 * 	지원 스타일 : "보통", "굵게", "기울임꼴", "굵은 기울임꼴"  <br>
	 * @param mf
	 */
	public MemoFontEvt (MemoFont mf) {
		this.mf= mf;
		 formatArr = new String[] { "Dialog", "DialogInput", "Monospaced", "Serif", "SansSerif", "맑은 고딕", "궁서체" };
		 styleArr = new String[] { "보통", "굵게", "기울임꼴", "굵은 기울임꼴" };
		 
		 DefaultListModel<String> dlmFormat = mf.getDlmFormat();
		 DefaultListModel<String> dlmStyle = mf.getDlmStyle();
		 DefaultListModel<String> dlmSize = mf.getDlmSize();
		 
		 //글꼴 설정
			for (int i = 0; i < formatArr.length; i++) {
				dlmFormat.addElement(formatArr[i]);
			}//end for
			
			//스타일 설정
			for (int i = 0; i < styleArr.length; i++) {
				dlmStyle.addElement(styleArr[i]);
			}//end for
			
			//크기 설정
			dlmSize.addElement(String.valueOf("7"));
			dlmSize.addElement(String.valueOf("8"));
			dlmSize.addElement(String.valueOf("9"));
			for (int i = 10; i < 81; i+=2) {
					dlmSize.addElement(String.valueOf(i));
				}//end for
			
			//미리보기
			mf.getChPreview().add("한글");
			mf.getChPreview().add("영어");
			
			jlFormat = mf.getJlFormat(); //값을 설정한 JList를 얻는다
			jlStyle = mf.getJlStyle(); //값을 설정한 JList를 얻는다
			jlSize = mf.getJlSize(); //값을 설정한 JList를 얻는다
			
			
			//부모창의 JTextArea폰트정보를 얻는다
			//JlPreview 객체 (미리보기의) Font정보에 설정
			mf.getJlbPreview().setFont( mf.getJm().getJtaNote().getFont() );
			
			memoFont = mf.getJlbPreview().getFont();
			
			//JTextField에 읽어들인 글꼴 정보를 설정한다
			mf.getJtfFormat().setText(memoFont.getFamily());
			mf.getJtfStyle().setText( styleArr [memoFont.getStyle()] );
			mf.getJtfSize().setText(String.valueOf(memoFont.getSize()));
			
	}//MemoFontEvt
	
	@Override
	public void itemStateChanged(ItemEvent ie) {
		
	}//itemStateChanged

	@Override
	public void valueChanged(ListSelectionEvent lse) {//이벤트를 발생시킨 컴포넌트가 생성한 이벤트 객체
		
	if( lse.getValueIsAdjusting() ) {
		
		//JList에서 이벤트가 발생하였다면 "미리보기"폰트의 정보를 변수에 저장
		Font previewFont = mf.getJlbPreview().getFont();
		String fontFamily = mf.getJlbPreview().getFont().getFamily();
		int fontStyle = mf.getJlbPreview().getFont().getStyle();
		int fontSize = mf.getJlbPreview().getFont().getSize();
		
		if( lse.getSource() == jlFormat ) {//포맷이 클릭 되었을 떄
				mf.getJtfFormat().setText(jlFormat.getSelectedValue());
				fontFamily = jlFormat.getSelectedValue(); //선택한 폰트의 정보를 설정
		}//end if
		
		if( lse.getSource() == jlStyle ) {//스타일이 클릭 되었을 떄
				mf.getJtfStyle().setText(jlStyle.getSelectedValue());
				fontStyle = jlStyle.getSelectedIndex();
		}//end if
		
		if( lse.getSource() == jlSize ) {//크기가 클릭 되었을 떄
				mf.getJtfSize().setText( jlSize.getSelectedValue() );
				//일반, 진하게 가 아닌 0,1,2 숫자로 스타일이 사용되기 때문에 선택한 인덱스를 얻는다
				fontSize = Integer.parseInt( jlSize.getSelectedValue() ); 
		}//end if
		
		//위에서 설정된 정보를 가지고 JlPreview를 변경하여 사용자가 폰트정보를 사용할 것인지
		//미리 보여준다
		Font tempFont = new Font(fontFamily, fontStyle, fontSize);
		mf.getJlbPreview().setFont(tempFont);
		
	}//end if
}//valueChanged
	@Override
	public void actionPerformed(ActionEvent ae) {
		jbtnConfirm = mf.getJbtnConfirm(); //값을 설정한 JList를 얻는다
		jbtnCancel = mf.getJbtnCancel(); //값을 설정한 JList를 얻는다
		if (ae.getSource() == jbtnConfirm ) {
			String fontFamily = mf.getJlbPreview().getFont().getFamily();
			int fontStyle = mf.getJlbPreview().getFont().getStyle();
			int fontSize = mf.getJlbPreview().getFont().getSize();

			Font tempFont = new Font(fontFamily, fontStyle, fontSize);
			JavaMemo pMemo = (JavaMemo) mf.getParent();
			pMemo.getJtaNote().setFont(tempFont);
			mf.dispose();
		}
		if (ae.getSource() ==  jbtnCancel ) {
			mf.dispose();
		}
	}//actionPerformed
	
}//class

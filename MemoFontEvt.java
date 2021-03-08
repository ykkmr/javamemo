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
 *	�۲� �������� �̺�Ʈ ó�� Ŭ����
 * @author user
 */
public class MemoFontEvt extends WindowAdapter 
implements ActionListener, ListSelectionListener, ItemListener{

	private MemoFont mf;
	
	private String[] formatArr; //�ʿ� ��
	private String[] styleArr; //�ʿ� ��

	private JList<String> jlFormat, jlStyle, jlSize;
	
	private Font memoFont;//JTextArea�� ��Ʈ ������ �����ϴ� ��Ʈ��ü. (�̸�����(JLabel)�� ����)  //�ʿ� ��
	
	private Object jbtnConfirm, jbtnCancel;
	private JTextArea jtaNote;
	
	/**
	 * 	���� �۲� : "Dialog", "DialogInput", "Monospaced", "Serif", "SansSerif", "���� ���", "�ü�ü" <br>
	 * 	���� ��Ÿ�� : "����", "����", "����Ӳ�", "���� ����Ӳ�"  <br>
	 * @param mf
	 */
	public MemoFontEvt (MemoFont mf) {
		this.mf= mf;
		 formatArr = new String[] { "Dialog", "DialogInput", "Monospaced", "Serif", "SansSerif", "���� ���", "�ü�ü" };
		 styleArr = new String[] { "����", "����", "����Ӳ�", "���� ����Ӳ�" };
		 
		 DefaultListModel<String> dlmFormat = mf.getDlmFormat();
		 DefaultListModel<String> dlmStyle = mf.getDlmStyle();
		 DefaultListModel<String> dlmSize = mf.getDlmSize();
		 
		 //�۲� ����
			for (int i = 0; i < formatArr.length; i++) {
				dlmFormat.addElement(formatArr[i]);
			}//end for
			
			//��Ÿ�� ����
			for (int i = 0; i < styleArr.length; i++) {
				dlmStyle.addElement(styleArr[i]);
			}//end for
			
			//ũ�� ����
			dlmSize.addElement(String.valueOf("7"));
			dlmSize.addElement(String.valueOf("8"));
			dlmSize.addElement(String.valueOf("9"));
			for (int i = 10; i < 81; i+=2) {
					dlmSize.addElement(String.valueOf(i));
				}//end for
			
			//�̸�����
			mf.getChPreview().add("�ѱ�");
			mf.getChPreview().add("����");
			
			jlFormat = mf.getJlFormat(); //���� ������ JList�� ��´�
			jlStyle = mf.getJlStyle(); //���� ������ JList�� ��´�
			jlSize = mf.getJlSize(); //���� ������ JList�� ��´�
			
			
			//�θ�â�� JTextArea��Ʈ������ ��´�
			//JlPreview ��ü (�̸�������) Font������ ����
			mf.getJlbPreview().setFont( mf.getJm().getJtaNote().getFont() );
			
			memoFont = mf.getJlbPreview().getFont();
			
			//JTextField�� �о���� �۲� ������ �����Ѵ�
			mf.getJtfFormat().setText(memoFont.getFamily());
			mf.getJtfStyle().setText( styleArr [memoFont.getStyle()] );
			mf.getJtfSize().setText(String.valueOf(memoFont.getSize()));
			
	}//MemoFontEvt
	
	@Override
	public void itemStateChanged(ItemEvent ie) {
		
	}//itemStateChanged

	@Override
	public void valueChanged(ListSelectionEvent lse) {//�̺�Ʈ�� �߻���Ų ������Ʈ�� ������ �̺�Ʈ ��ü
		
	if( lse.getValueIsAdjusting() ) {
		
		//JList���� �̺�Ʈ�� �߻��Ͽ��ٸ� "�̸�����"��Ʈ�� ������ ������ ����
		Font previewFont = mf.getJlbPreview().getFont();
		String fontFamily = mf.getJlbPreview().getFont().getFamily();
		int fontStyle = mf.getJlbPreview().getFont().getStyle();
		int fontSize = mf.getJlbPreview().getFont().getSize();
		
		if( lse.getSource() == jlFormat ) {//������ Ŭ�� �Ǿ��� ��
				mf.getJtfFormat().setText(jlFormat.getSelectedValue());
				fontFamily = jlFormat.getSelectedValue(); //������ ��Ʈ�� ������ ����
		}//end if
		
		if( lse.getSource() == jlStyle ) {//��Ÿ���� Ŭ�� �Ǿ��� ��
				mf.getJtfStyle().setText(jlStyle.getSelectedValue());
				fontStyle = jlStyle.getSelectedIndex();
		}//end if
		
		if( lse.getSource() == jlSize ) {//ũ�Ⱑ Ŭ�� �Ǿ��� ��
				mf.getJtfSize().setText( jlSize.getSelectedValue() );
				//�Ϲ�, ���ϰ� �� �ƴ� 0,1,2 ���ڷ� ��Ÿ���� ���Ǳ� ������ ������ �ε����� ��´�
				fontSize = Integer.parseInt( jlSize.getSelectedValue() ); 
		}//end if
		
		//������ ������ ������ ������ JlPreview�� �����Ͽ� ����ڰ� ��Ʈ������ ����� ������
		//�̸� �����ش�
		Font tempFont = new Font(fontFamily, fontStyle, fontSize);
		mf.getJlbPreview().setFont(tempFont);
		
	}//end if
}//valueChanged
	@Override
	public void actionPerformed(ActionEvent ae) {
		jbtnConfirm = mf.getJbtnConfirm(); //���� ������ JList�� ��´�
		jbtnCancel = mf.getJbtnCancel(); //���� ������ JList�� ��´�
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

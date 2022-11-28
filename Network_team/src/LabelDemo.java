import java.util.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class LabelDemo extends JFrame{
   public static final int WIDTH = 373;
   public static final int HEIGHT = 595;
   private JButton loginBtn, signupBtn, findIdBtn,doSignupBtn,backtoLoginBtn[],doFindIdBtn;
   Operator o=null;
   
   JLabel kakaoLogo,kakaoLogo2,kakaoLogo3;
   JTextField loginIdText;
   
   JTextField signupIdText,nicknameText,nameText,emailText,birthText,sayingText,phoneText,urlText;
   
   JTextField findNameText,findEmailText;
   JPasswordField loginPwText,signupPwText;
   ButtonListener bl = new ButtonListener();
   Container contentPane;
   JPanel content,content2,content3;
   public LabelDemo (Operator _o) {
      o=_o;
      setTitle ("Kakaotalk");
      setSize (WIDTH, HEIGHT);
      setLocationRelativeTo(null);   //실행했을 때 frame창 화면을 컴퓨터 가운데에 배치 : 참고 <https://kkh0977.tistory.com/595>
      setResizable(false);   //frame창 크기 고정 : 참고 <https://kkh0977.tistory.com/595>
      Color backgroundColor = new Color(250, 225, 0);
      backtoLoginBtn=new JButton[2];
      backtoLoginBtn[0]=new JButton("로그인 화면으로 돌아가기");
      backtoLoginBtn[1]=new JButton("로그인 화면으로 돌아가기");
      backtoLoginBtn[0].setBounds(20,500,333,30);
      backtoLoginBtn[1].setBounds(20,500,333,30);
      backtoLoginBtn[0].addActionListener(bl);
      backtoLoginBtn[1].addActionListener(bl);
      findNameText=new JTextField();
      findEmailText=new JTextField();
      doFindIdBtn=new JButton("ID 찾기");
      doFindIdBtn.addActionListener(bl);
                
      contentPane=getContentPane();
      content = new JPanel(null);
      content2= new JPanel(null);
      content3= new JPanel(null);
      
      content.setBounds(0,0,WIDTH,HEIGHT);
      content2.setBounds(0,0,WIDTH,HEIGHT);
      content3.setBounds(0,0,WIDTH,HEIGHT);
      
      setVisible(true);
      contentPane.add(content3);
      contentPane.add(content2);
      contentPane.add(content);
      
      content.setVisible(false);
      content2.setVisible(false);
      content3.setVisible(false);
      
    
      content.setBackground(backgroundColor);
      content2.setBackground(backgroundColor);
      content3.setBackground(backgroundColor);
      //login content
      ImageIcon icon = new ImageIcon("src/image/LOGO-05.png");
      Image img = icon.getImage();
      Image changeImg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
      ImageIcon changeIcon = new ImageIcon(changeImg);
      kakaoLogo = new JLabel(changeIcon);
      kakaoLogo.setBounds((WIDTH-150)/2,0,150,150);
      kakaoLogo2 = new JLabel(changeIcon);
      kakaoLogo2.setBounds((WIDTH-150)/2,0,150,150);
      kakaoLogo3 = new JLabel(changeIcon);
      kakaoLogo3.setBounds((WIDTH-150)/2,0,150,150);
      content.add (kakaoLogo); 
      content2.add(kakaoLogo2);
      content3.add(kakaoLogo3);
      
      loginIdText = new JTextField(); 
      loginPwText = new JPasswordField();
      loginBtn = new JButton ("로그인");
      loginBtn.addActionListener(bl);//버튼 리스너
      JLabel l1 = new JLabel("---------또는---------");
      signupBtn = new JButton ("회원가입");
      signupBtn.addActionListener (bl);//버튼 리스너
      
      loginIdText.setBounds(20,150,333,30);
      loginPwText.setBounds(20,180,333,30);
      loginBtn.setBounds(20,210,333,30);
      l1.setBounds(20,240,333,30);
      l1.setHorizontalAlignment(JLabel.CENTER);
      signupBtn.setBounds(20,270,333,30);
      
      content.add(loginIdText);
      content.add(loginPwText);
      content.add(loginBtn);
      content.add(l1);
      content.add(signupBtn);
      
      findIdBtn = new JButton ("카카오계정 찾기");
      findIdBtn.addActionListener (bl); //버튼 리스너
      findIdBtn.setBounds(20,500,333,30);
      content.add(findIdBtn);
      
      content.setVisible(true);
      //singup content2
	  signupIdText=new JTextField();
	  nicknameText=new JTextField();
	  nameText=new JTextField();
	  emailText=new JTextField();
	  birthText=new JTextField();
	  sayingText=new JTextField();
	  phoneText=new JTextField();//optional
	  urlText=new JTextField();//optional
	  
	  signupPwText=new JPasswordField();
	  doSignupBtn=new JButton("계정 생성하기");
	  
	  //버튼 기능 추가
      doSignupBtn.addActionListener(bl);
	  signupIdText.setBounds(20,150,333,30);
	  signupPwText.setBounds(20,150+30*1,333,30);
	  nicknameText.setBounds(20,150+30*2,333,30);
	  nameText.setBounds(20,150+30*3,333,30);
	  emailText.setBounds(20,150+30*4,333,30);
	  birthText.setBounds(20,150+30*5,333,30);
	  sayingText.setBounds(20,150+30*6,333,30);
	  phoneText.setBounds(20,150+30*7,333,30);//optional
	  urlText.setBounds(20,150+30*8,333,30);//optional

	  doSignupBtn.setBounds(20,150+30*10,333,30);
	  
	  content2.add(signupIdText);
	  content2.add(signupPwText);
	  content2.add(nicknameText);
	  content2.add(nameText);
	  content2.add(emailText);
	  content2.add(birthText);
	  content2.add(sayingText);
	  content2.add(phoneText);
	  content2.add(urlText);
      content2.add(doSignupBtn);
      content2.add(backtoLoginBtn[0]);
      
      
	  
      //find account content3
      findNameText.setBounds(20,150,333,30);
      findEmailText.setBounds(20,180,333,30);
      doFindIdBtn.setBounds(20,450,333,30);
      
      content3.add(backtoLoginBtn[1]);
      content3.add(findNameText);
      content3.add(findEmailText);
      content3.add(doFindIdBtn);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   class ButtonListener implements ActionListener{
       @Override
       public void actionPerformed(ActionEvent e) {
          JButton b = (JButton)e.getSource();
          
          /* TextField에 입력된 아이디와 비밀번호를 변수에 초기화 */
          //PW decoding
          String uid = loginIdText.getText();
          String upass = "";
          for(int i=0; i<loginPwText.getPassword().length; i++) {
             upass = upass + loginPwText.getPassword()[i];
          }
          upass=encoding.encode(upass, upass.length());
          //press login Button
          if(b.getText().equals("로그인")) {
        	  content.setVisible(true);
        	  content2.setVisible(false);
        	  content3.setVisible(false);
        	  
             if(uid.equals("") || upass.equals("")) {
            	 //login fail
                JOptionPane.showMessageDialog(null, "Please enter both your ID and PW", "login fail", JOptionPane.ERROR_MESSAGE);
                System.out.println("login fail > Login information not entered");
             }else if(uid != null && upass != null) {
                try {
                	if(o.request_login(uid, upass)) {   //이 부분이 데이터베이스에 접속해 로그인 정보를 확인하는 부분이다.
                		//id & pw correct
	                  System.out.println("login success");
	                  JOptionPane.showMessageDialog(null, "login success");
	                  o.mainFrame=new MyFrame(o, uid);
	                  //로그인 후에 보이는 창화면 설정
	                  dispose();
	                  //새로운 패널 클래스로 넘어간다.
	                  //데이터베이스 클래스를 넘겨준다 .
                	} else {
                		//id & pw not correct
	                  System.out.println("login fail > Login Information Mismatch");
	                  JOptionPane.showMessageDialog(null, "login fail");
                	}
	            } catch (Exception e1) {
	               e1.printStackTrace();
	            }
             }
          }else if(b.getText().equals("회원가입")) {
        	  //complete, no more code need
        	  content.setVisible(false);
        	  content2.setVisible(true);
        	  content3.setVisible(false);
          }else if(b.getText().equals("카카오계정 찾기")) {
        	  //complete, no more code need
        	  content.setVisible(false);
        	  content2.setVisible(false);
        	  content3.setVisible(true);
          }else if(b.getText().equals("계정 생성하기")) {
        	  if(signupIdText.getText().equals("") || signupPwText.getText().equals("") || nicknameText.getText().equals("")
        			  || nameText.getText().equals("") || emailText.getText().equals("") || birthText.getText().equals("")
        			  || sayingText.getText().equals("")) {//if not null is null,,,
        		  JOptionPane.showMessageDialog(null, "Fill essential informations");
        	  }else {
            	  //jdbc if there isn't ID, then make account
        		  System.out.println(1);
        		  try {
        			  String s=signupIdText.getText();
					if(!o.check_id(s)) {
						  JOptionPane.showMessageDialog(null, "ID \'"+signupIdText.getText()+"\' is already used");
					 }else {
						  //계정 추가
						  o.sign_up(signupIdText.getText(), signupIdText.getText(),nicknameText.getText(),nameText.getText(),emailText.getText(),
								  birthText.getText(),sayingText.getText(),phoneText.getText(),urlText.getText());
						  JOptionPane.showMessageDialog(null, "회원가입 성공");
						  content.setVisible(true);
						  content2.setVisible(false);
						  content3.setVisible(false);
					  }
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	  }
          }else if(b.getText().equals("로그인 화면으로 돌아가기")) {
        	  //complete, no more code need
        	  content.setVisible(true);
        	  content2.setVisible(false);
        	  content3.setVisible(false);
          }else if(b.getText().equals("ID 찾기")) {
        	  //jdbc if there are equal name,email find ID ,then give ID
        	  content.setVisible(true);
        	  content2.setVisible(false);
        	  content3.setVisible(false);
          }
       }
    }

}
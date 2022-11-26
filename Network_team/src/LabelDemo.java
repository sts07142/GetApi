import java.util.*;
import javax.swing.*;


import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class LabelDemo extends JFrame{
   public static final int WIDTH = 373;
   public static final int HEIGHT = 595;
   private JButton b1, b2, b3, b4;
   private JCheckBox rb1;
   Operator o=null;
   
   JTextField t1=null;
   JPasswordField password=null;
   ButtonListener bl = new ButtonListener();
   public LabelDemo (Operator _o) {
	   o=_o;
      setTitle ("Kakaotalk");
      setSize (WIDTH, HEIGHT);
      setLocationRelativeTo(null);   //실행했을 때 frame창 화면을 컴퓨터 가운데에 배치 : 참고 <https://kkh0977.tistory.com/595>
      setResizable(false);   //frame창 크기 고정 : 참고 <https://kkh0977.tistory.com/595>
      Color backgroundColor = new Color(250, 225, 0);
      
      Container content = getContentPane();
      content.setLayout (new GridLayout (3, 1));
      
      //1)
      JPanel panel1 = new JPanel ();
      panel1.setLayout (new BorderLayout ());
      panel1.setBackground (backgroundColor);
         JPanel panel1_1 = new JPanel();
         panel1_1.setBackground (backgroundColor);
         
         ImageIcon icon = new ImageIcon("src/image/LOGO-05.png");
         Image img = icon.getImage();
         Image changeImg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
         ImageIcon changeIcon = new ImageIcon(changeImg);
         JLabel 카카오톡logo = new JLabel(changeIcon);
         panel1_1.add(카카오톡logo);
         
         panel1.add(panel1_1, BorderLayout.CENTER);
      content.add (panel1); 
      

      
      //2)
      JPanel panel2 = new JPanel ();
      panel2.setLayout (new BorderLayout ());
      panel2.setBackground (backgroundColor);
         JPanel panel2_1 = new JPanel(new GridLayout (5, 1));
         panel2_1.setBackground (backgroundColor);
         t1 = new JTextField("카카오계정 (이메일 또는 전화번호)"); 
         panel2_1.add(t1);
         password = new JPasswordField();
         panel2_1.add(password);
         b1 = new JButton ("로그인");
         b1.addActionListener(bl);//버튼 리스너
         panel2_1.add(b1);
         JLabel l1 = new JLabel("---------또는---------");
         l1.setHorizontalAlignment(JLabel.CENTER); 
         panel2_1.add(l1);
         b2 = new JButton ("QR코드 로그인");
         b2.addActionListener (bl);//버튼 리스너
         panel2_1.add(b2);
         panel2.add(panel2_1, BorderLayout.CENTER);
         
         JPanel panel2_2 = new JPanel();
         panel2_2.setBackground(backgroundColor);
         panel2.add(panel2_2, BorderLayout.WEST);
         
         JPanel panel2_3 = new JPanel();
         panel2_3.setBackground(backgroundColor);
         panel2.add(panel2_3, BorderLayout.EAST);
         
      content.add (panel2);
      
      //3
      JPanel panel3 = new JPanel();
      panel3.setLayout (new BorderLayout ());
      panel3.setBackground (backgroundColor);
         rb1 = new JCheckBox("자동로그인", false);
         rb1.setBackground(backgroundColor);
         panel3.add(rb1, BorderLayout.NORTH);
         
      
         JPanel panel3_1 = new JPanel();
         panel3_1.setLayout (new FlowLayout ());
         panel3_1.setBackground (backgroundColor);
         b3 = new JButton ("카카오계정 찾기");
         b3.addActionListener (bl); //버튼 리스너
         panel3_1.add (b3);
         b4 = new JButton ("비밀번호 재설정");
         b4.addActionListener (bl); // 버튼 리스너 
         panel3_1.add (b4);
         panel3.add(panel3_1, BorderLayout.SOUTH);
      content.add (panel3);
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

   }

   class ButtonListener implements ActionListener{
       @Override
       public void actionPerformed(ActionEvent e) {
          JButton b = (JButton)e.getSource();
          
          /* TextField에 입력된 아이디와 비밀번호를 변수에 초기화 */
          String uid = t1.getText();
          String upass = "";
          for(int i=0; i<password.getPassword().length; i++) {
             upass = upass + password.getPassword()[i];
          }
          upass=encoding.encode(upass, upass.length());
          if(b.getText().equals("로그인")) {
             if(uid.equals("") || upass.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter both your ID and password", "login fail", JOptionPane.ERROR_MESSAGE);
                System.out.println("login fail > Login information not entered");
             }
             
             else if(uid != null && upass != null) {
                try {
					if(o.request_login(uid, upass)) {   //이 부분이 데이터베이스에 접속해 로그인 정보를 확인하는 부분이다.
					   System.out.println("login success");
					   JOptionPane.showMessageDialog(null, "login success");
					   //로그인 후에 보이는 창화면 설정
					   dispose();
					   //새로운 패널 클래스로 넘어간다.
					   //데이터베이스 클래스르르 넘겨준다 .
					} else {
					   System.out.println("login fail > Login Information Mismatch");
					   JOptionPane.showMessageDialog(null, "login fail");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
             }
          }
       }
    }
}
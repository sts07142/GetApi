import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class LabelDemo extends JFrame{
   public static final int WIDTH = 385;
   public static final int HEIGHT = 595;
   private JButton loginBtn, signupBtn, findIdBtn,doSignupBtn,backtoLoginBtn[],doFindIdBtn;
   ImageIcon createID;
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
      setLocationRelativeTo(null);   //frame screen in center of computer
      setResizable(false);  //frameFix window size
      Color backgroundColor = new Color(250, 225, 0);
      backtoLoginBtn=new JButton[2];
      backtoLoginBtn[0]=new JButton("Back to login Frame");
      backtoLoginBtn[1]=new JButton("Back to login Frame");
      backtoLoginBtn[0].setBounds(20,500,333,30);
      backtoLoginBtn[1].setBounds(20,500,333,30);
      backtoLoginBtn[0].addActionListener(bl);
      backtoLoginBtn[1].addActionListener(bl);
      backtoLoginBtn[0].addActionListener (bl);    //Button Listener
      backtoLoginBtn[0].setBorderPainted(false);    //Remove the button border
      backtoLoginBtn[0].setContentAreaFilled(false);
      backtoLoginBtn[0].setFont(new Font("Arial", Font.PLAIN, 11));
      backtoLoginBtn[0].setBounds(60,480,250,33);
      backtoLoginBtn[1].addActionListener (bl);    //Button Listener
      backtoLoginBtn[1].setBorderPainted(false);    //Remove the button border
      backtoLoginBtn[1].setContentAreaFilled(false);
      backtoLoginBtn[1].setFont(new Font("Arial", Font.PLAIN, 11));
      backtoLoginBtn[1].setBounds(60,480,250,33);
     

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
      ImageIcon icon = new ImageIcon("C:\\Users\\82105\\Desktop\\LOGO-05.png");
      Image img = icon.getImage();
      Image changeImg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
      ImageIcon changeIcon = new ImageIcon(changeImg);
      kakaoLogo = new JLabel(changeIcon);
      kakaoLogo.setBounds((373-150)/2,40,150,150);
      kakaoLogo2 = new JLabel(changeIcon);
      kakaoLogo2.setBounds((373-150)/2,40,150,150);
      kakaoLogo3 = new JLabel(changeIcon);
      kakaoLogo3.setBounds((373-150)/2,40,150,150);
      content.add (kakaoLogo); 
      content2.add(kakaoLogo2);
      content3.add(kakaoLogo3);
      
      loginIdText = new JTextField("Enter your ID") {
         @Override
          public void setBorder(Border border) {} //Remove the textfield border 
      }; 
      loginIdText.addMouseListener(new MouseAdapter(){
          @Override
          public void mouseClicked(MouseEvent e){
             ResetAllTextField();
             if(loginIdText.getText().equals("Enter your ID")) {
                loginIdText.setText("");                
                loginIdText.setForeground(Color.BLACK);
             }   
          }
      });
      loginIdText.setForeground(Color.lightGray);
      
      loginPwText = new JPasswordField("Password") {
         @Override
          public void setBorder(Border border) {} //Remove the textfield border
      };
      loginPwText.addMouseListener(new MouseAdapter(){
          @Override
          public void mouseClicked(MouseEvent e){
             ResetAllTextField();
             if(loginPwText.getText().equals("Password")) {
                loginPwText.setText("");
                loginPwText.setForeground(Color.BLACK);
             }        
          }
      });
      loginPwText.setForeground(Color.lightGray);
      
      loginBtn = new JButton ("Login");
      loginBtn.addActionListener(bl);    //Button Listener
      loginBtn.setBorderPainted(false);    //Remove the button border
      loginBtn.setFont(new Font("Arial", Font.PLAIN, 14));
      JLabel l1 = new JLabel("--------- OR ---------");
      l1.setFont(new Font("Arial", Font.PLAIN, 14));
      signupBtn = new JButton ("Sign Up");
      signupBtn.addActionListener (bl);    //Button Listener
      signupBtn.setBorderPainted(false);    //Remove the button border
      signupBtn.setFont(new Font("Arial", Font.PLAIN, 14));
      
      loginIdText.setBounds(60,190,250,35);
      loginPwText.setBounds(60,226,250,35);
      loginBtn.setBounds(60,265,250,35);
      l1.setBounds(60,300,250,35);
      l1.setHorizontalAlignment(JLabel.CENTER);
      signupBtn.setBounds(60,335,250,35);
      
      content.add(loginIdText);
      content.add(loginPwText);
      content.add(loginBtn);
      content.add(l1);
      content.add(signupBtn);
      
      content.addMouseListener(new MouseAdapter(){
          @Override
          public void mouseClicked(MouseEvent e){
             ResetAllTextField();     
          }
      });
      
      findIdBtn = new JButton ("Find Kakao Account");
      findIdBtn.addActionListener (bl);    //Button Listener
      findIdBtn.setBorderPainted(false);    //Remove the button border
      findIdBtn.setContentAreaFilled(false);
      findIdBtn.setFont(new Font("Arial", Font.PLAIN, 11));
      findIdBtn.setBounds(60,480,250,33);
      content.add(findIdBtn);
      content.setVisible(true);
      
      
      
      //singup content2
     signupIdText=new JTextField("ID") {
         @Override
          public void setBorder(Border border) {} //Remove the textfield border 
      }; 
      signupIdText.addMouseListener(new MouseAdapter(){
          @Override
          public void mouseClicked(MouseEvent e){
             ResetAllTextField();
             if(signupIdText.getText().equals("ID")) {
                signupIdText.setText("");   
                signupIdText.setForeground(Color.BLACK);
             }        
          }
      });
      signupIdText.setForeground(Color.lightGray);
      
      signupPwText=new JPasswordField("Password"){
         @Override
          public void setBorder(Border border) {} //Remove the textfield border 
      };   
      signupPwText.addMouseListener(new MouseAdapter(){
      @Override
          public void mouseClicked(MouseEvent e){
             ResetAllTextField();
             if(signupPwText.getText().equals("Password")) {
                signupPwText.setText("");
                signupPwText.setForeground(Color.BLACK);
             }        
          }
      });
      signupPwText.setForeground(Color.lightGray);
      
     nicknameText=new JTextField("Nickname"){
         @Override
          public void setBorder(Border border) {} //Remove the textfield border 
      }; 
      nicknameText.addMouseListener(new MouseAdapter(){
          @Override
          public void mouseClicked(MouseEvent e){
             ResetAllTextField();
             if(nicknameText.getText().equals("Nickname")) {
                nicknameText.setText(""); 
                nicknameText.setForeground(Color.BLACK);
             }         
          }
      });
      nicknameText.setForeground(Color.lightGray);
      
     nameText=new JTextField("Name"){
         @Override
          public void setBorder(Border border) {} //Remove the textfield border 
      }; 
      nameText.addMouseListener(new MouseAdapter(){
          @Override
          public void mouseClicked(MouseEvent e){
             ResetAllTextField();
             if(nameText.getText().equals("Name")) {
                nameText.setText("");    
                nameText.setForeground(Color.BLACK);
             }         
          }
      });
      nameText.setForeground(Color.lightGray);
      
     emailText=new JTextField("hong@gachon.ac.kr"){
         @Override
          public void setBorder(Border border) {} //Remove the textfield border 
      }; 
      emailText.addMouseListener(new MouseAdapter(){
          @Override
          public void mouseClicked(MouseEvent e){
             ResetAllTextField();
             if(emailText.getText().equals("hong@gachon.ac.kr")) {
                emailText.setText("");  
                emailText.setForeground(Color.BLACK);
             }     
          }
      });
      emailText.setForeground(Color.lightGray);
      
     birthText=new JTextField("YYYY-MM-DD"){
         @Override
          public void setBorder(Border border) {} //Remove the textfield border 
      };
      birthText.addMouseListener(new MouseAdapter(){
          @Override
          public void mouseClicked(MouseEvent e){
             ResetAllTextField();
             if(birthText.getText().equals("YYYY-MM-DD")) {
                birthText.setText(""); 
                birthText.setForeground(Color.BLACK);
             }        
          }
      });
      birthText.setForeground(Color.lightGray);
      
     sayingText=new JTextField("Hello world"){
         @Override
          public void setBorder(Border border) {} //Remove the textfield border 
      }; 
      sayingText.addMouseListener(new MouseAdapter(){
          @Override
          public void mouseClicked(MouseEvent e){
             ResetAllTextField();
             if(sayingText.getText().equals("Hello world")) {
                sayingText.setText("");  
                sayingText.setForeground(Color.BLACK);
             }         
          }
      });
      sayingText.setForeground(Color.lightGray);
          
     phoneText=new JTextField("010-0000-0000"){ //optional
         @Override
          public void setBorder(Border border) {} //Remove the textfield border 
      }; 
      phoneText.addMouseListener(new MouseAdapter(){
          @Override
          public void mouseClicked(MouseEvent e){
             ResetAllTextField();
             if(phoneText.getText().equals("010-0000-0000")) {
                phoneText.setText("");  
                phoneText.setForeground(Color.BLACK);
             }     
          }
      });
      phoneText.setForeground(Color.lightGray);
          
     urlText=new JTextField("https://sw.gachon.ac.kr/cms/"){ //optional
         @Override
          public void setBorder(Border border) {} //Remove the textfield border 
      }; 
      urlText.addMouseListener(new MouseAdapter(){
          @Override
          public void mouseClicked(MouseEvent e){
             ResetAllTextField();
             if(urlText.getText().equals("https://sw.gachon.ac.kr/cms/")) {
                urlText.setText(""); 
                urlText.setForeground(Color.BLACK);
             }
          }
      });
      urlText.setForeground(Color.lightGray);

     ImageIcon icon1 = new ImageIcon("C:\\Users\\82105\\Desktop\\add.png");
     Image img1 = icon1.getImage();
     Image changeImg1 = img1.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
     createID = new ImageIcon(changeImg1);
     doSignupBtn = new JButton(createID);
     doSignupBtn.setBorderPainted(false); //button Border
     doSignupBtn.setContentAreaFilled(false); //button Background
     doSignupBtn.setFocusPainted(false); //Img border in button
     
     
     //Add button functionality
     doSignupBtn.addActionListener(bl);
     nameText.setBounds(60,182,124,35);
     nicknameText.setBounds(186,182,124,35);
     signupIdText.setBounds(60,218,124,35);
     signupPwText.setBounds(186,218,124,35);
     emailText.setBounds(60,254,250,35);
     birthText.setBounds(60,290,250,35);
     sayingText.setBounds(60,326,250,35);
     phoneText.setBounds(60,362,250,35);   //optional
     urlText.setBounds(60,398,250,35);   //optional

     doSignupBtn.setBounds(60,445,250,30);
     
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
      
      content2.addMouseListener(new MouseAdapter(){
          @Override
          public void mouseClicked(MouseEvent e){
             ResetAllTextField();     
          }
      });
      
      
     
      //find account content3
      findNameText=new JTextField("Enter your Name"){ //optional
         @Override
          public void setBorder(Border border) {} //Remove the textfield border 
      };
      findNameText.addMouseListener(new MouseAdapter(){
          @Override
          public void mouseClicked(MouseEvent e){
             ResetAllTextField();
             if(findNameText.getText().equals("Enter your Name")) {
                findNameText.setText("");
                findNameText.setForeground(Color.BLACK);
             }
          }
      });
      findNameText.setForeground(Color.lightGray);
      
      findEmailText=new JTextField("Enter your Email"){ //optional
         @Override
          public void setBorder(Border border) {} //Remove the textfield border 
      };
      findEmailText.addMouseListener(new MouseAdapter(){
          @Override
          public void mouseClicked(MouseEvent e){
             ResetAllTextField();
             if(findEmailText.getText().equals("Enter your Email")) {
                findEmailText.setText("");
                findEmailText.setForeground(Color.BLACK);
             }
          }
      });
      findEmailText.setForeground(Color.lightGray);
      
      doFindIdBtn=new JButton("Search ID");
      doFindIdBtn.addActionListener(bl);
      
      findNameText.setBounds(60,190,250,35);
      findEmailText.setBounds(60,226,250,35);
      doFindIdBtn.setBounds(60,265,250,35);
      doFindIdBtn.setBorderPainted(false);    //Remove the button border
      doFindIdBtn.setFont(new Font("Arial", Font.PLAIN, 14));
      
      content3.add(backtoLoginBtn[1]);
      content3.add(findNameText);
      content3.add(findEmailText);
      content3.add(doFindIdBtn);
      
      content3.addMouseListener(new MouseAdapter(){
          @Override
          public void mouseClicked(MouseEvent e){
             ResetAllTextField();     
          }
      });
      

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   
   class ButtonListener implements ActionListener{
       @Override
       public void actionPerformed(ActionEvent e) {
          JButton b = (JButton)e.getSource();
          
          /* Initialize ID and password entered in TextField to variable */
          //PW decoding
          String uid = loginIdText.getText();
          String upass = "";
          for(int i=0; i<loginPwText.getPassword().length; i++) {
             upass = upass + loginPwText.getPassword()[i];
          }
          upass=encoding.encode(upass, upass.length());
          //press login Button
          if(b.getText().equals("Login")) {
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
                     //Window screen settings visible after login
                     dispose();
                     //Move on to the new panel class & Hand over the database class.
                   } else {
                      //id & pw not correct
                     System.out.println("login fail > Login Information Mismatch");
                     JOptionPane.showMessageDialog(null, "login fail");
                   }
               } catch (Exception e1) {
                  e1.printStackTrace();
               }
             }
          }else if(b.getText().equals("Sign Up")) {
             //complete, no more code need
             content.setVisible(false);
             content2.setVisible(true);
             content3.setVisible(false);
          }else if(b.getText().equals("Find Kakao Account")) {
             //complete, no more code need
             content.setVisible(false);
             content2.setVisible(false);
             content3.setVisible(true);
             
          }else if(b.getText().equals("Back to login Frame")) {
             //complete, no more code need
             content.setVisible(true);
             content2.setVisible(false);
             content3.setVisible(false);
             
          }else if(b.getText().equals("Search ID")) {
             //jdbc if there are equal name,email find ID ,then give ID
             content.setVisible(true);
             content2.setVisible(false);
             content3.setVisible(false);
           //jdbc if there are equal name,email find ID ,then give ID
       	  String find_name=findNameText.getText();
             String find_email=findEmailText.getText();
             String ID=new String();
       	  try {
				ID=o.find_ID(find_name, find_email);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
       	  if(ID.equals(null) || ID.equals("")) {
       		  JOptionPane.showMessageDialog(null, "not find");
       	  }else {
       		  JOptionPane.showMessageDialog(null, "your ID id that : "+ID);
       	  }
             
          }
          else if(b.getIcon().equals(createID)) {
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
                    //add Account
                    o.sign_up(signupIdText.getText(), signupIdText.getText(),nicknameText.getText(),nameText.getText(),emailText.getText(),
                          birthText.getText(),sayingText.getText(),phoneText.getText(),urlText.getText());
                    JOptionPane.showMessageDialog(null, "Success Sign Up");
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
          }
       }
    }
<<<<<<< HEAD
   /*
   public static void main (String [] args) {
      LabelDemo w = new LabelDemo();
      w.setVisible (true);
   }
   */
   public void ResetAllTextField() {
      //content3
      if(signupIdText.getText().equals("")) {
         signupIdText.setText("ID");
         signupIdText.setForeground(Color.lightGray);
      }

      
      if(signupPwText.getText().equals("")) {
         //signupPwText.setText("Passward");
         //signupPwText.setForeground(Color.lightGray);
      }
     
      if(nicknameText.getText().equals("")) {
         nicknameText.setText("Nickname");
         nicknameText.setForeground(Color.lightGray);
      }
      
      if(nameText.getText().equals("")) {
         nameText.setText("Name");
         nameText.setForeground(Color.lightGray);
      }
      
      if(emailText.getText().equals("")) {
         emailText.setText("hong@gachon.ac.kr");
         emailText.setForeground(Color.lightGray);
      }
      
      if(birthText.getText().equals("")) {
         birthText.setText("YYYY-MM-DD");
         birthText.setForeground(Color.lightGray);
      }
      
      if(sayingText.getText().equals("")) {
         sayingText.setText("Hello world");
         sayingText.setForeground(Color.lightGray);
      }
      
      if(phoneText.getText().equals("")) {
         phoneText.setText("010-0000-0000");
         phoneText.setForeground(Color.lightGray);
      }
      
      if(urlText.getText().equals("")) {
         urlText.setText("https://sw.gachon.ac.kr/cms/");
         urlText.setForeground(Color.lightGray);
      }   
      
      //content2
      if(findEmailText.getText().equals("")) {
         findEmailText.setText("Enter your Email");
         findEmailText.setForeground(Color.lightGray);
      }   
      
      if(findNameText.getText().equals("")) {
         findNameText.setText("Enter your Name");
         findNameText.setForeground(Color.lightGray);
      }   
     
      //content
      if(loginIdText.getText().equals("")) {
         loginIdText.setText("Enter your ID");
         loginIdText.setForeground(Color.lightGray);
      }
      
      if(loginPwText.getText().equals("")) {
         //loginPwText.setText("Password");
         //loginPwText.setForeground(Color.lightGray);
      }  
         

   }
   
=======
>>>>>>> 2f46633de42055e9c3f85afd461fa33beaca99ed
   
}
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class MyFrame extends JFrame{
   public static final int WIDTH = 405;
   public static final int HEIGHT = 595;
   JPanel panel, left, center, bottom;
   //친구, 채팅목록, 검색, 내정보수정
   JButton btnFriend, btnChat, btnSearch, btnUpdateMyData;
   JPanel pnlFriend, pnlChat, pnlSearch, pnlUpdateMyData;
   
   JLabel panelName;
   private JScrollPane scrollFriend;
   ImageIcon icon1, icon2, icon3, icon4, friends, chat, search, setting;
   Image img1, img2, img3, img4, changeImg1, changeImg2, changeImg3, changeImg4;
   
   // ButtonListener
   ButtonListener bl = new ButtonListener();
   get_Button getB=new get_Button();
   Operator o=null;
   String user_id=new String();
   
   JTextField txtFieldSearch;
   JPanel scrollSearchPanel;
   MyFrame(Operator _o, String user_id) {
	   this.o=_o;
	   this.user_id=user_id;
      setTitle ("Kakaotalk");
      setSize (WIDTH, HEIGHT);
      setLocationRelativeTo(null);
      setResizable(false);
      
      Container contentPane = getContentPane();
      
      //0) 전체 panel - 왼쪽, 중앙, 아래를 합친
      panel = new JPanel(null);
      panel.setLayout(null);
      
      
      //1) 왼쪽 panel - 친구, 채팅목록, 검색, 내정보수정
      left = new JPanel(null);
      left.setBounds(0,0,80,460);
      Color leftColor = new Color(236,236,237);
      left.setBackground (leftColor);
         //친구, 채팅목록, 검색, 내정보수정 버튼을 추가한다
            //1-1)친구
      
            icon1 = new ImageIcon("src/image/friend.png");
            img1 = icon1.getImage();
            changeImg1 = img1.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            friends = new ImageIcon(changeImg1);
            btnFriend = new JButton(friends);
            btnFriend.setBounds(20, 20, 40, 40);
            btnFriend.setBorderPainted(false); //button 테두리
            btnFriend.setContentAreaFilled(false); //button 배경
            btnFriend.setFocusPainted(false); //button 안에 들어있는 이미지 테두리
            
            //1-2)채팅목록
            icon2 = new ImageIcon("src/image/not_chat.png");
            img2 = icon2.getImage();
            changeImg2 = img2.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            chat = new ImageIcon(changeImg2);
            btnChat = new JButton(chat);
            btnChat.setBounds(20, 80, 40, 40);
            btnChat.setBorderPainted(false); //button 테두리
            btnChat.setContentAreaFilled(false); //button 배경
            btnChat.setFocusPainted(false); //button 안에 들어있는 이미지 테두리
            
            //1-3)검색
            icon3 = new ImageIcon("src/image/not_search.png");
            img3 = icon3.getImage();
            changeImg3 = img3.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            search = new ImageIcon(changeImg3);
            btnSearch = new JButton(search);
            btnSearch.setBounds(20, 140, 40, 40);
            btnSearch.setBorderPainted(false); //button 테두리
            btnSearch.setContentAreaFilled(false); //button 배경
            btnSearch.setFocusPainted(false); //button 안에 들어있는 이미지 테두리
            
            //1-4)설정
            icon4 = new ImageIcon("src/image/not_setting.png");
            img4 = icon4.getImage();
            changeImg4 = img4.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            setting = new ImageIcon(changeImg4);
            btnUpdateMyData = new JButton(setting);
            btnUpdateMyData.setBounds(20, 410, 40, 40);
            btnUpdateMyData.setBorderPainted(false); //button 테두리
            btnUpdateMyData.setContentAreaFilled(false); //button 배경
            btnUpdateMyData.setFocusPainted(false); //button 안에 들어있는 이미지 테두리 
            
         left.add(btnFriend);
         left.add(btnChat);
         left.add(btnSearch);
         left.add(btnUpdateMyData);
         
            
         btnFriend.addActionListener(bl);
         btnChat.addActionListener(bl);
         btnSearch.addActionListener(bl);
         btnUpdateMyData.addActionListener(bl);
      panel.add(left);
      
      
      //2) 중앙 panel - 친구list
      int listCount = 0; //<---------------------------------DB에서 친구가 몇명인지 읽어온 후 넣음
      center = new JPanel(null);
      center.setBounds(80,0,325,460);
      center.setBackground (Color.white);
         //2-1)친구 목록 화면 - scollPane 안에 넣을 panel
         pnlFriend = new JPanel(null);
         pnlFriend.setPreferredSize(new Dimension(313, 1000));
         pnlFriend.setBackground(Color.black);
            //title - 친구
            JPanel titleProfile = new JPanel(null);
            titleProfile.setBackground (Color.white);
            JLabel title = new JLabel("친구");
            title.setFont(new Font("맑은 고딕", Font.BOLD , 18));
            title.setBounds(10,7,100,20);
            titleProfile.add(title);
            titleProfile.setBounds(0,0,295, 35);
            pnlFriend.add(titleProfile);
            
            //내 프로필
            JPanel profile = new JPanel(null);
            profile.setBackground (Color.white);
            profile.setBounds(0,35,310, 80);
                  //프로필사진
                  ImageIcon icon = new ImageIcon("src/image/profile.png"); //<----------------------- DB : 로그인 한 사람의 프로필사진
                  Image img = icon.getImage();
                  Image changeImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                  ImageIcon changeIcon = new ImageIcon(changeImg);
                  JLabel profileImg = new JLabel(changeIcon);
                  profileImg.setBounds(10,15,50,50);
                  profile.add(profileImg);
                  //별명
                  JLabel nickname = new JLabel("프로필별명"); //<----------------------- DB : 로그인 한 사람의 별명
                  nickname.setBounds(70,27,100,10);
                  nickname.setFont(new Font("맑은 고딕", Font.BOLD , 10));
                  profile.add(nickname);
                  //오늘의 한마디
                  JLabel message = new JLabel("오늘의한마디오늘의한마디오늘의한마디오늘의한마디"); //<----------------------- DB : 로그인 한 사람의 "오늘의 한마디"
                  message.setBounds(70,45,180,10);
                  message.setFont(new Font("맑은 고딕", Font.PLAIN , 10));
                  profile.add(message);
                  //온오프라인 상태
                  DrawPanel d = new DrawPanel();
                  d.setBounds(270, 35, 10, 10);
                  profile.add(d);
               pnlFriend.add(profile);
               
            //친구프로필 띄우기
            JPanel profile1 = new JPanel();
            profile1.setBackground (Color.pink);
            profile1.setBounds(0,160, 310, 80);
            pnlFriend.add(profile1);
               
         //스크롤바 포함시켜야 함
         scrollFriend = new JScrollPane(pnlFriend, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
         scrollFriend.setBounds(0, 0, 313, 460);
         center.add(scrollFriend);
         
            //내 프로필 띄우기
            //JPanel profile = new JPanel();
            //profile.setBackground (Color.BLUE);
            //center.add(profile);
            //for (int i=0; i<listCount; i++) { //친구수만큼 프로필 보이게 하기
               
            //}
               
         
         //2-2)채팅
         pnlChat = new JPanel(null);
         pnlChat.setPreferredSize(new Dimension(313, 1000));
         pnlChat.setBackground(Color.yellow);
            //title - 채팅
            JPanel titleChat = new JPanel(null);
            titleChat.setBackground (Color.white);
            JLabel chat = new JLabel("채팅");
            chat.setFont(new Font("맑은 고딕", Font.BOLD , 18));
            chat.setBounds(10,7,100,20);
            titleChat.add(chat);
            titleChat.setBounds(0,0,310, 35);
            pnlChat.add(titleChat);
         
         
         //2-3)검색
         pnlSearch = new JPanel(null);
         pnlSearch.setPreferredSize(new Dimension(313, 450));
         pnlSearch.setBackground(Color.orange);
            //title - 검색
            JPanel titleSearch = new JPanel(null);
            titleSearch.setBackground (Color.white);
            JLabel search = new JLabel("친구 추가");
            search.setFont(new Font("맑은 고딕", Font.BOLD , 18));
            search.setBounds(10,7,100,20);
            titleSearch.add(search);
            titleSearch.setBounds(0,0,310, 35);
            pnlSearch.add(titleSearch);
            
            //label - ID로 추가
            JLabel labelPlus = new JLabel("ID로 추가");
            labelPlus.setFont(new Font("맑은 고딕", Font.PLAIN , 15));
            labelPlus.setBounds(10, 60, 100, 15);
            pnlSearch.add(labelPlus);
                  
            //입력창 - 검색
            txtFieldSearch = new JTextField();
            txtFieldSearch.setBounds(10, 85, 230, 25);
            pnlSearch.add(txtFieldSearch);
            
            //버튼 - 검색
            JButton btnsearchList = new JButton("검색");
            btnsearchList.setFont(new Font("맑은 고딕", Font.PLAIN , 5));
            btnsearchList.setBounds(243, 85, 50, 25);
            pnlSearch.add(btnsearchList);
            btnsearchList.addActionListener(getB);
            
            //출력창 - 검색 결과
            scrollSearchPanel = new JPanel(null);
            scrollSearchPanel.setBackground(Color.cyan);
            scrollSearchPanel.setPreferredSize(new Dimension(313, 1000));  //<---------검색했을 때 나오는 프로필 개수에 따라서 길이 조절
            JScrollPane scrollSearch = new JScrollPane(scrollSearchPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollSearch.setBounds(0, 120, 313, 340);
            pnlSearch.add(scrollSearch);

      
         //2-4)설정
         pnlUpdateMyData = new JPanel(null);
         pnlUpdateMyData.setPreferredSize(new Dimension(313, 450));
         pnlUpdateMyData.setBackground(Color.white);
            //title - 설정
            JPanel titleUpdateMyData = new JPanel(null);
            titleUpdateMyData.setBackground (Color.WHITE);
            JLabel updateMyData = new JLabel("설정");
            updateMyData.setFont(new Font("맑은 고딕", Font.BOLD , 18));
            updateMyData.setBounds(10,7,100,20);
            titleUpdateMyData.add(updateMyData);
            titleUpdateMyData.setBounds(0,0,310, 35);
            pnlUpdateMyData.add(titleUpdateMyData);
            
            //비밀번호 변경
               //label - 비밀번호 변경
               JLabel labelUpdatePassword = new JLabel("비밀번호 변경");
               labelUpdatePassword.setFont(new Font("맑은 고딕", Font.PLAIN , 12));
               labelUpdatePassword.setBounds(10, 60, 100, 15);
               pnlUpdateMyData.add(labelUpdatePassword);
                     
               //입력창 - 비밀번호
               JPasswordField passwordFieldUpdate = new JPasswordField();
               passwordFieldUpdate.setBounds(10, 85, 230, 25);
               pnlUpdateMyData.add(passwordFieldUpdate);
               
               //버튼 - 변경
               JButton btnUpdatePassword = new JButton("변경");
               btnUpdatePassword.setFont(new Font("맑은 고딕", Font.PLAIN , 8));
               btnUpdatePassword.setBounds(243, 85, 50, 25);
               btnUpdatePassword.addActionListener(bl);
               pnlUpdateMyData.add(btnUpdatePassword);
            
            //별명 변경
               //label - 별명 변경
               JLabel labelNicknameUpdate = new JLabel("별명 변경");
               labelNicknameUpdate.setFont(new Font("맑은 고딕", Font.PLAIN , 12));
               labelNicknameUpdate.setBounds(10, 130, 100, 15);
               pnlUpdateMyData.add(labelNicknameUpdate);
                     
               //입력창 - 별명
               JTextField txtFieldNicknameUpdate = new JTextField();
               txtFieldNicknameUpdate.setBounds(10, 155, 230, 25);
               pnlUpdateMyData.add(txtFieldNicknameUpdate);
               
               //버튼 - 변경
               JButton btnUpdateNickname = new JButton("변경");
               btnUpdateNickname.setFont(new Font("맑은 고딕", Font.PLAIN , 8));
               btnUpdateNickname.setBounds(243, 155, 50, 25);
               btnUpdateNickname.addActionListener(bl);
               pnlUpdateMyData.add(btnUpdateNickname);
               
            //오늘의 한마디 변경
               //label - 오늘의 한마디 변경
               JLabel labelSentenceUpdate = new JLabel("오늘의 한마디 변경");
               labelSentenceUpdate.setFont(new Font("맑은 고딕", Font.PLAIN , 12));
               labelSentenceUpdate.setBounds(10, 202, 150, 15);
               pnlUpdateMyData.add(labelSentenceUpdate);
                     
               //입력창 - 별명
               JTextField txtFieldSentenceUpdate = new JTextField();
               txtFieldSentenceUpdate.setBounds(10, 225, 230, 25);
               pnlUpdateMyData.add(txtFieldSentenceUpdate);
               
               //버튼 - 변경
               JButton btnUpdateSentence = new JButton("변경");
               btnUpdateSentence.setFont(new Font("맑은 고딕", Font.PLAIN , 8));
               btnUpdateSentence.setBounds(243, 225, 50, 25);
               btnUpdateSentence.addActionListener(bl);
               pnlUpdateMyData.add(btnUpdateSentence);
               
            //탈퇴 버튼
            JButton btnWithdrawal = new JButton("탈퇴");
            btnWithdrawal.setFont(new Font("맑은 고딕", Font.PLAIN , 8));
            btnWithdrawal.setBounds(10, 270, 50, 25);
            btnWithdrawal.addActionListener(bl);
            pnlUpdateMyData.add(btnWithdrawal);
            
      panel.add(center);
      
      

      
      
      
      
      
      
      //3) 아래 panel - 공공데이터 사용
      bottom = new JPanel(null);
      bottom.setBounds(0,460,405,135);
      Color bottomColor = new Color(248,248,248);
      bottom.setBackground (bottomColor);
      panel.add(bottom);
      
      
      
      contentPane.add(panel, BorderLayout.CENTER);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
   }
   class get_Button implements ActionListener{
	   public void actionPerformed (ActionEvent e) {
		   JButton b = (JButton)e.getSource();
		   if(b.getText().equals("검색")) {
			   String s=txtFieldSearch.getText();
			  
			   if(s.equals("") || s.equals(null)) {
				   
			   }else {
				   
				   String[] sample=null;
				   try {
				   sample = o.find_friends(s);
				   } catch (Exception e1) {
				   // TODO Auto-generated catch block
				   e1.printStackTrace();
				   }
				   
				   scrollSearchPanel.updateUI();
				   scrollSearchPanel.removeAll();
				   scrollSearchPanel.revalidate();
				   scrollSearchPanel.repaint();
				   //scrollSearchPanel=new JPanel(null);
				   
				   scrollSearchPanel.setPreferredSize(new Dimension(313, 1000)); 
				   scrollSearchPanel.setBackground(Color.black);
			      int Psize=Integer.parseInt(sample[0]);
			      int Pnum=1;
			      
			      while(Psize>Pnum) {
			    	  
			    	  JPanel profile = new JPanel(null);
			            profile.setBackground (Color.white);
			            profile.setBounds(0,(Pnum-1)*80,310,80);
			                  //프로필사진
			                  ImageIcon icon = new ImageIcon("src/image/profile.png"); //<----------------------- DB : 로그인 한 사람의 프로필사진
			                  Image img = icon.getImage();
			                  Image changeImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			                  ImageIcon changeIcon = new ImageIcon(changeImg);
			                  JLabel profileImg = new JLabel(changeIcon);
			                  profileImg.setBounds(10,15,50,50);
			                  profile.add(profileImg);
			                  //별명
			                  JLabel nickname = new JLabel("프로필별명"); //<----------------------- DB : 로그인 한 사람의 별명
			                  nickname.setBounds(70,27,100,10);
			                  nickname.setFont(new Font("맑은 고딕", Font.BOLD , 10));
			                  profile.add(nickname);
			                  //오늘의 한마디
			                  JLabel message = new JLabel("오늘의한마디오늘의한마디오늘의한마디오늘의한마디"); //<----------------------- DB : 로그인 한 사람의 "오늘의 한마디"
			                  message.setBounds(70,45,180,10);
			                  message.setFont(new Font("맑은 고딕", Font.PLAIN , 10));
			                  profile.add(message);
			                  //온오프라인 상태
			                  DrawPanel d = new DrawPanel();
			                  d.setBounds(270, 35, 10, 10);
			                  profile.add(d);
			         
			         scrollSearchPanel.add(profile);
			         Pnum++;
			      }
			   }
			   txtFieldSearch.setText("");
		      //스크롤 pane은 스크롤바가 포함된 object를 생성하는 부분이다. 그럼 이 부분에서 scrollSearchPanel패널을 스크롤바가 존재하는 object에 삽입해
		      //스크롤 효과를 추가
	           
		   }
	   }
   }
   
   class ButtonListener implements ActionListener{
      @Override
      public void actionPerformed (ActionEvent e) {
         JButton b = (JButton)e.getSource();
         
          if(b.getIcon().equals(friends)) {       
             btnFriend.setVisible(false);
             btnChat.setVisible(false);
             btnSearch.setVisible(false);
             btnUpdateMyData.setVisible(false);
             icon1 = new ImageIcon("src/image/friend.png");
             icon2 = new ImageIcon("src/image/not_chat.png");
             icon3 = new ImageIcon("src/image/not_search.png");
             icon4 = new ImageIcon("src/image/not_setting.png");
             pnlFriend.setVisible(true);
             pnlChat.setVisible(false);
             pnlSearch.setVisible(false);
             pnlUpdateMyData.setVisible(false);
             scrollFriend.setViewportView(pnlFriend);
            }
          else if(b.getIcon().equals(chat)) {
             btnFriend.setVisible(false);
             btnChat.setVisible(false);
             btnSearch.setVisible(false);
             btnUpdateMyData.setVisible(false);
             icon1 = new ImageIcon("src/image/not_friend.png");
             icon2 = new ImageIcon("src/image/chat.png");
             icon3 = new ImageIcon("src/image/not_search.png");
             icon4 = new ImageIcon("src/image/not_setting.png");
             pnlFriend.setVisible(false);
             pnlChat.setVisible(true);
             pnlSearch.setVisible(false);
             pnlUpdateMyData.setVisible(false);
             scrollFriend.setViewportView(pnlChat);
            }
          else if(b.getIcon().equals(search)) {
             btnFriend.setVisible(false);
             btnChat.setVisible(false);
             btnSearch.setVisible(false);
             btnUpdateMyData.setVisible(false);
             icon1 = new ImageIcon("src/image/not_friend.png");
             icon2 = new ImageIcon("src/image/not_chat.png");
             icon3 = new ImageIcon("src/image/search.png");
             icon4 = new ImageIcon("src/image/not_setting.png");
             pnlFriend.setVisible(false);
             pnlChat.setVisible(false);
             pnlSearch.setVisible(true);
             pnlUpdateMyData.setVisible(false);
             scrollFriend.setViewportView(pnlSearch);
            }
          else if(b.getIcon().equals(setting)) {
             btnFriend.setVisible(false);
             btnChat.setVisible(false);
             btnSearch.setVisible(false);
             btnUpdateMyData.setVisible(false);
             icon1 = new ImageIcon("src/image/not_friend.png");
             icon2 = new ImageIcon("src/image/not_chat.png");
             icon3 = new ImageIcon("src/image/not_search.png");
             icon4 = new ImageIcon("src/image/setting.png");
             pnlFriend.setVisible(false);
             pnlChat.setVisible(false);
             pnlSearch.setVisible(false);
             pnlUpdateMyData.setVisible(true);
             scrollFriend.setViewportView(pnlUpdateMyData);
            }
          
             img1 = icon1.getImage();
            changeImg1 = img1.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            friends = new ImageIcon(changeImg1);
            btnFriend = new JButton(friends);
            btnFriend.setBounds(20, 20, 40, 40);
            btnFriend.setBorderPainted(false); //button 테두리
            btnFriend.setContentAreaFilled(false); //button 배경
            btnFriend.setFocusPainted(false); //button 안에 들어있는 이미지 테두리
            img2 = icon2.getImage();
            changeImg2 = img2.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            chat = new ImageIcon(changeImg2);
            btnChat = new JButton(chat);
            btnChat.setBounds(20, 80, 40, 40);
            btnChat.setBorderPainted(false); //button 테두리
            btnChat.setContentAreaFilled(false); //button 배경
            btnChat.setFocusPainted(false); //button 안에 들어있는 이미지 테두리
            img3 = icon3.getImage();
            changeImg3 = img3.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            search = new ImageIcon(changeImg3);
            btnSearch = new JButton(search);
            btnSearch.setBounds(20, 140, 40, 40);
            btnSearch.setBorderPainted(false); //button 테두리
            btnSearch.setContentAreaFilled(false); //button 배경
            btnSearch.setFocusPainted(false); //button 안에 들어있는 이미지 테두리
            img4 = icon4.getImage();
            changeImg4 = img4.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            setting = new ImageIcon(changeImg4);
            btnUpdateMyData = new JButton(setting);
            btnUpdateMyData.setBounds(20, 410, 40, 40);
            btnUpdateMyData.setBorderPainted(false); //button 테두리
            btnUpdateMyData.setContentAreaFilled(false); //button 배경
            btnUpdateMyData.setFocusPainted(false); //button 안에 들어있는 이미지 테두리 
            
            left.add(btnFriend);
            left.add(btnChat);
            left.add(btnSearch);
            left.add(btnUpdateMyData);
            
            // ButtonListener
            ButtonListener bl = new ButtonListener();
            btnFriend.addActionListener(bl);
            btnChat.addActionListener(bl);
            btnSearch.addActionListener(bl);
            btnUpdateMyData.addActionListener(bl);
            
            panel.add(left);   
      }
   }
   public class DrawPanel extends JPanel {  //<----------온오프라인 나타내는 동그라미 관련 함수
      String state = ""; //<--------------------- DB에서 상태를 읽어서 state라는 string 변수에 저장
      
      @Override
      public void paint(Graphics g) {
         super.paint(g);
         g.setColor(Color.RED); //상태 default값으로 "off"로 해놓는다 
         if (state.equalsIgnoreCase("on") == true) {   //<------------ DB에서 읽었을 때 on 상태일 경우
            g.setColor(Color.GREEN);
         }
         g.fillOval(0, 0, 10, 10);
         
      }
   }
   
}
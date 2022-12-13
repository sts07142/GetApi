import java.awt.*;
import java.io.IOException;
import java.net.UnknownHostException;
import java.time.LocalTime;

import javax.swing.*;
import java.awt.event.*;
import org.json.simple.parser.ParseException;

class MyFrame extends JFrame implements Runnable, ActionListener, WindowListener{//main frame after login success
   public static final int WIDTH = 405;
   public static final int HEIGHT = 595;
   JPanel panel, left, center, bottom;
   JButton btnFriend, btnChat, btnSearch, btnUpdateMyData, btnEnding;
   JPanel pnlFriend, pnlChat, pnlSearch, pnlUpdateMyData, pnlending;
   ApiExplorer a=new ApiExplorer();
   JLabel panelName, apiLabel[],apiImgLabel[];
   public JScrollPane scrollFriend;
   ImageIcon icon1, icon2, icon3, icon4, friends, chat, search, setting, ending;
   Image img1, img2, img3, img4, img5, changeImg1, changeImg2, changeImg3, changeImg4, changeImg5;
   
   ImageIcon cold,hot,wind,snow,rain,sun,humidity,calender,snowrain,location;
   // ButtonListener
   ButtonListener bl = new ButtonListener();
   get_Button getB=new get_Button();
   Operator o=null;
   String user_id=new String();

   JTextField txtFieldSearch;
   JPanel scrollSearchPanel;
   //for reloading user_information
   JTextField passwordFieldUpdate,txtFieldNicknameUpdate,txtFieldSentenceUpdate;
   JButton btnUpdatePassword,btnUpdateNickname,btnUpdateSentence,btnWithdrawal;

   int minute=-1;
   
   MyFrame(Operator _o, String user_id) throws IOException, ParseException {
      this.o=_o;
      this.user_id=user_id;
      setTitle ("Kakaotalk");
      setSize (WIDTH, HEIGHT);
      setLocationRelativeTo(null);
      setResizable(false);
      
      ImageIcon frameIcon = new ImageIcon("src/image/LOGO.png");
      setIconImage(frameIcon.getImage());
      
      Container contentPane = getContentPane();
      minute=LocalTime.now().getMinute();
      //0) All panel - left, mid, bottom
      panel = new JPanel(null);
      panel.setLayout(null);
      
      
      //1) left panel - friend list, chatting list, search, exit, setting info
      left = new JPanel(null);
      left.setBounds(0,0,80,460);
      Color leftColor = new Color(236,236,237);
      left.setBackground (leftColor);
            //1-1) friend list
      
            icon1 = new ImageIcon("src/image/friend.png");
            img1 = icon1.getImage();
            changeImg1 = img1.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            friends = new ImageIcon(changeImg1);
            btnFriend = new JButton(friends);
            btnFriend.setBounds(20, 20, 40, 40);
            btnFriend.setBorderPainted(false);
            btnFriend.setContentAreaFilled(false); 
            btnFriend.setFocusPainted(false); 
            
            //1-2)chatting list
            icon2 = new ImageIcon("src/image/not_chat.png");
            img2 = icon2.getImage();
            changeImg2 = img2.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            chat = new ImageIcon(changeImg2);
            btnChat = new JButton(chat);
            btnChat.setBounds(20, 80, 40, 40);
            btnChat.setBorderPainted(false);
            btnChat.setContentAreaFilled(false);
            btnChat.setFocusPainted(false); 
            
            //1-3)search
            icon3 = new ImageIcon("src/image/not_search.png");
            img3 = icon3.getImage();
            changeImg3 = img3.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            search = new ImageIcon(changeImg3);
            btnSearch = new JButton(search);
            btnSearch.setBounds(20, 140, 40, 40);
            btnSearch.setBorderPainted(false); 
            btnSearch.setContentAreaFilled(false); 
            btnSearch.setFocusPainted(false); 
            
            //1-4)setting info
            icon4 = new ImageIcon("src/image/not_setting.png");
            img4 = icon4.getImage();
            changeImg4 = img4.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            setting = new ImageIcon(changeImg4);
            btnUpdateMyData = new JButton(setting);
            btnUpdateMyData.setBounds(20, 410, 40, 40);
            btnUpdateMyData.setBorderPainted(false); 
            btnUpdateMyData.setContentAreaFilled(false);
            btnUpdateMyData.setFocusPainted(false); 
            
            //1-5)exit
            ending = new ImageIcon("src/image/end.png");
            img5 = ending.getImage();
            changeImg5 = img5.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            ending = new ImageIcon(changeImg5);
            btnEnding = new JButton(ending);
            btnEnding.setBounds(20, 360, 40, 40);
            btnEnding.setBorderPainted(false); 
            btnEnding.setContentAreaFilled(false); 
            btnEnding.setFocusPainted(false); 
            
         left.add(btnFriend);
         left.add(btnChat);
         left.add(btnSearch);
         left.add(btnUpdateMyData);
         left.add(btnEnding);
            
         btnFriend.addActionListener(bl);
         btnChat.addActionListener(bl);
         btnSearch.addActionListener(bl);
         btnUpdateMyData.addActionListener(bl);
         btnEnding.addActionListener(bl);
      panel.add(left);
      
      center = new JPanel(null);
      center.setBounds(80,0,325,460);
      center.setBackground (Color.white);
      //2) mid panel - friend list
         pnlFriend=new JPanel(null);
         profile_reset();
         scrollFriend = new JScrollPane(pnlFriend, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
         scrollFriend.setBounds(0, 0, 313, 460);
         center.add(scrollFriend);
    
         //2-2)chatting
         chat_reset();
         
         //2-3)search
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
                  
            //textfield - 검색
            txtFieldSearch = new JTextField();
            txtFieldSearch.setBounds(10, 85, 230, 25);
            pnlSearch.add(txtFieldSearch);
            
            //button - 검색
            JButton btnsearchList = new JButton("검색");
            btnsearchList.setFont(new Font("맑은 고딕", Font.PLAIN , 5));
            btnsearchList.setBounds(243, 85, 50, 25);
            pnlSearch.add(btnsearchList);
            btnsearchList.addActionListener(getB);
            
            //print - 검색 결과
            scrollSearchPanel = new JPanel(null);
            scrollSearchPanel.setBackground(Color.cyan);
            scrollSearchPanel.setPreferredSize(new Dimension(313, 1000)); 
            JScrollPane scrollSearch = new JScrollPane(scrollSearchPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollSearch.setBounds(0, 120, 313, 340);
            pnlSearch.add(scrollSearch);

      
         //2-4)setting info
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
            
            //change password
               //label - 비밀번호 변경
               JLabel labelUpdatePassword = new JLabel("비밀번호 변경");
               labelUpdatePassword.setFont(new Font("맑은 고딕", Font.PLAIN , 12));
               labelUpdatePassword.setBounds(10, 60, 100, 15);
               pnlUpdateMyData.add(labelUpdatePassword);
                     
               //textfield - 비밀번호
               passwordFieldUpdate = new JPasswordField();
               passwordFieldUpdate.setBounds(10, 85, 230, 25);
               pnlUpdateMyData.add(passwordFieldUpdate);
               
               //button - 변경
               btnUpdatePassword = new JButton("변경");
               btnUpdatePassword.setFont(new Font("맑은 고딕", Font.PLAIN , 8));
               btnUpdatePassword.setBounds(243, 85, 50, 25);
               pnlUpdateMyData.add(btnUpdatePassword);
            
            //change nickname
               //label - nickname 변경
               JLabel labelNicknameUpdate = new JLabel("nickname 변경");
               labelNicknameUpdate.setFont(new Font("맑은 고딕", Font.PLAIN , 12));
               labelNicknameUpdate.setBounds(10, 130, 100, 15);
               pnlUpdateMyData.add(labelNicknameUpdate);
                     
               //textfield - nickname
               txtFieldNicknameUpdate = new JTextField();
               txtFieldNicknameUpdate.setBounds(10, 155, 230, 25);
               pnlUpdateMyData.add(txtFieldNicknameUpdate);
               
               //button - 변경
               btnUpdateNickname = new JButton("변경");
               btnUpdateNickname.setFont(new Font("맑은 고딕", Font.PLAIN , 8));
               btnUpdateNickname.setBounds(243, 155, 50, 25);
               pnlUpdateMyData.add(btnUpdateNickname);
               
            //change today's saying
               //label - today's saying 변경
               JLabel labelSentenceUpdate = new JLabel("today's saying 변경");
               labelSentenceUpdate.setFont(new Font("맑은 고딕", Font.PLAIN , 12));
               labelSentenceUpdate.setBounds(10, 202, 150, 15);
               pnlUpdateMyData.add(labelSentenceUpdate);
                     
               //textfield - nickname
               txtFieldSentenceUpdate = new JTextField();
               txtFieldSentenceUpdate.setBounds(10, 225, 230, 25);
               pnlUpdateMyData.add(txtFieldSentenceUpdate);
               
               //button - 변경
               btnUpdateSentence = new JButton("변경");
               btnUpdateSentence.setFont(new Font("맑은 고딕", Font.PLAIN , 8));
               btnUpdateSentence.setBounds(243, 225, 50, 25);
               pnlUpdateMyData.add(btnUpdateSentence);
               
            //탈퇴 button
            btnWithdrawal = new JButton("탈퇴");
            btnWithdrawal.setFont(new Font("맑은 고딕", Font.PLAIN , 8));
            btnWithdrawal.setBounds(10, 270, 50, 25);
            pnlUpdateMyData.add(btnWithdrawal);
            
      panel.add(center);
      
      btnUpdatePassword.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e) {//change password
            String a=passwordFieldUpdate.getText();
               passwordFieldUpdate.setText("");
               o.updateInfo(user_id,"0",a);
               JOptionPane.showMessageDialog(null,"비밀번호가 변경되었습니다","설정 - 변경",JOptionPane.PLAIN_MESSAGE);
            profile_reset();
         } 
         
      });
      
      btnUpdateNickname.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e) {//change nickname
            String a=txtFieldNicknameUpdate.getText();
               txtFieldNicknameUpdate.setText("");
               o.updateInfo(user_id,"1",a);
               JOptionPane.showMessageDialog(null,"nickname이 변경되었습니다","설정 - 변경",JOptionPane.PLAIN_MESSAGE);
            profile_reset();
            } 
      });
      
      btnUpdateSentence.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e) {//change today's saying
            String a=txtFieldSentenceUpdate.getText();
               txtFieldSentenceUpdate.setText("");
               o.updateInfo(user_id,"2",a);
               JOptionPane.showMessageDialog(null,"today's saying가 변경되었습니다","설정 - 변경",JOptionPane.PLAIN_MESSAGE);
            profile_reset();
            
         } 
      });
      
      btnWithdrawal.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e) {//withdraw account
            o.updateInfo(user_id,"3","");
               JOptionPane.showMessageDialog(null,"계정이 탈퇴되었습니다","설정 - 변경",JOptionPane.PLAIN_MESSAGE);
               dispose();
               try {
            new Operator();
         } catch (UnknownHostException e1) {
            e1.printStackTrace();
         } catch (IOException e1) {
            e1.printStackTrace();
         }
         } 
      });
        
        
    //3) API panel
      apiLabel=new JLabel[6];
      apiImgLabel=new JLabel[6];
      for(int i=0; i<6; i++)
      {
         apiLabel[i]=new JLabel();
         apiImgLabel[i]=new JLabel();
      }
      bottom = new JPanel(null);
      bottom.setBounds(0,460,405,135);
      bottom.setBackground (Color.white);
      panel.add(bottom);
      
      hot=new ImageIcon(new ImageIcon("src/image/hot.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
      cold=new ImageIcon(new ImageIcon("src/image/cold.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
      wind=new ImageIcon(new ImageIcon("src/image/wind.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
      snow=new ImageIcon(new ImageIcon("src/image/snow.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
      rain=new ImageIcon(new ImageIcon("src/image/rain.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
      sun=new ImageIcon(new ImageIcon("src/image/sun.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
      humidity=new ImageIcon(new ImageIcon("src/image/humidity.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
      calender=new ImageIcon(new ImageIcon("src/image/calender.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
      snowrain=new ImageIcon(new ImageIcon("src/image/snowrain.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
      location=new ImageIcon(new ImageIcon("src/image/location.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
            
      String[] readApi=a.getApi().split(",");
      
      if(Double.parseDouble(readApi[7])>0) {
         apiImgLabel[0].setIcon(hot);
      }else
         apiImgLabel[0].setIcon(cold);
      
      apiImgLabel[1].setIcon(calender);
      apiImgLabel[2].setIcon(humidity);
      apiImgLabel[3].setIcon(wind);
      apiImgLabel[5].setIcon(location);
      //readApi.length will be 16+2
      //temperature
      apiLabel[0].setText("온도 "+readApi[7]+"℃");
      //used date,time
      apiLabel[1].setText("측정 "+readApi[16]+" "+readApi[17]);
      //humidity
      apiLabel[2].setText("습도 "+readApi[3]+"%");
      //wind speed
      apiLabel[3].setText("풍속 "+readApi[15]+"m/s");
      //rain type, rain drop
      apiLabel[4].setText(readApi[1]+readApi[5]);
      //location
      apiLabel[5].setText("위치 Gachon Univ.");
      
      //width 405 height 135
      apiLabel[5].setBounds(50,0,130,30);
      apiLabel[1].setBounds(230,0,150,30);
      apiLabel[0].setBounds(50,30,130,30);
      apiLabel[2].setBounds(230,30,130,30);
      apiLabel[3].setBounds(50,60,130,30);
      apiLabel[4].setBounds(230,60,130,30);
      
      apiImgLabel[5].setBounds(0,0,30,30);
      apiImgLabel[1].setBounds(180,0,30,30);
      apiImgLabel[0].setBounds(0,30,30,30);
      apiImgLabel[2].setBounds(180,30,30,30);
      apiImgLabel[3].setBounds(0,60,30,30);
      apiImgLabel[4].setBounds(180,60,30,30);
      
      for(int i=0; i<6;i++) {
         bottom.add(apiLabel[i]);
         bottom.add(apiImgLabel[i]);
      }
      
      int chk=1;
      switch(Integer.parseInt(readApi[1])) {
      //NONE(0), RAIN(1), RAIN+SNOW(2), SNOW(3)
      //RAIN DROP(5), RAIN/SNOW DROP(6), SNOW DROP(7)
      case 0:
         chk=0;
          apiLabel[4].setText("눈/비 없음 "+readApi[5]+"mm");
          apiImgLabel[4].setIcon(sun);
         break;
      case 1:
         apiLabel[4].setText("비 "+readApi[5]+"mm");
          apiImgLabel[4].setIcon(rain);
         break;
      case 2:
         apiLabel[4].setText("눈/비 "+readApi[5]+"mm");
          apiImgLabel[4].setIcon(snowrain);
         break;
      case 3:
         apiLabel[4].setText("눈 "+readApi[5]+"mm");
          apiImgLabel[4].setIcon(snow);
         break;
      case 5:
         apiLabel[4].setText("빗방울 "+readApi[5]+"mm");
          apiImgLabel[4].setIcon(rain);
         break;
      case 6:
         apiLabel[4].setText("빗방울/눈날림 "+readApi[5]+"mm");
          apiImgLabel[4].setIcon(snowrain);
         break;
      case 7:
         apiLabel[4].setText("눈날림 "+readApi[5]+"mm");
          apiImgLabel[4].setIcon(snow);
         break;     
      }
      
      new Thread(this).start();
      
      contentPane.add(panel, BorderLayout.CENTER);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
      

   }
   public void run(){
      while(true) {
         
         
         try {
         if(o.thread_count==0) {
        	 System.out.println("myframe_reset");
        	 chat_reset();
             Thread.sleep(500);
         }
         
         //profile_reset();
         //Thread.sleep(2000);
      } catch (InterruptedException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      }
   }

   synchronized void chat_reset() {
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
          
          //set friend list
          String inform[]=null;
          try {
             inform=o.find_chatting(user_id);
          } catch (Exception e1) {
             // TODO Auto-generated catch block
             e1.printStackTrace();
          }
          
         int num= Integer.parseInt(inform[0]);
         int i=1;
         if(inform[0].equals("1") || inform[0].equals(null)) {
            num=0;
         }
         
         while(i<num) {          
             
            String inform11[]=null;
             try {
                inform11=o.get_ChatInformation(inform[i], user_id);
             } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
             }
             //profile
             JPanel profile1 = new JPanel(null);
             profile1.setBackground (Color.pink);
             profile1.setBounds(0,30+((i+1)/2-1)*80, 310, 80);
            ImageIcon icon1 = new ImageIcon("src/image/profile.png"); 
             Image img1 = icon1.getImage();
             Image changeImg1 = img1.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
             ImageIcon changeIcon1 = new ImageIcon(changeImg1);
             JLabel profileImg1 = new JLabel(changeIcon1);
             profileImg1.setBounds(10,15,50,50);
             profile1.add(profileImg1);
             //nickname
             int temp=Integer.parseInt(inform11[0]);
             String s="";
             if(temp==2) {
            	 s=inform11[1];
             }else {
            	 for(int j=1; j<temp; j++) {
            		 if(j==1) {
            			 s=inform11[j];
            		 }else {
            			 s=s+", "+inform11[j];
            		 }
                     
                  }
             }
             
             
             JLabel nickname1 = new JLabel(s); 
             nickname1.setBounds(70,27,100,10);
             nickname1.setFont(new Font("맑은 고딕", Font.BOLD , 10));
             profile1.add(nickname1);
             //today's saying
             JLabel message1 = new JLabel(o.get_lastchat(inform[i])); 
             message1.setBounds(70,45,180,10);
             message1.setFont(new Font("맑은 고딕", Font.PLAIN , 10));
             profile1.add(message1);
             //on/offline state
           
             int result = 0;
             if(inform[i+1].equals("0")) {
                result = JOptionPane.showConfirmDialog(null, "chatting("+s+") allow?.");
                System.out.println("chat:"+result);
                if(result==0) {
                   o.allow_chat(user_id, inform[i], "1");
                }else {
                   o.allow_chat(user_id, inform[i], "0");
                }
             }else if(inform[i+1].equals("-1")) {
                o.allow_chat(user_id, inform[i], "-1");
                JOptionPane.showMessageDialog(null, "chatting fail : "+s);
             }
             pnlChat.add(profile1);
            i++;
            i++;
         }
         pnlChat.setVisible(true);   
   }
   
   synchronized void profile_reset() {//reset profile(refresh)
      o.update_time(user_id);
            //2-1)freind list
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
               JPanel profile = new JPanel(null);
               //profile
               profile.setBackground (Color.white);
               profile.setBounds(0,35,310, 80);
               
      String inform1[]=null;
       try {
          inform1=o.get_information(user_id);
       } catch (Exception e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
       }
             
       
             //profile
             ImageIcon icon = new ImageIcon("src/image/profile.png");
             Image img = icon.getImage();
             Image changeImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
             ImageIcon changeIcon = new ImageIcon(changeImg);
             JLabel profileImg = new JLabel(changeIcon);
             profileImg.setBounds(10,15,50,50);
             profile.add(profileImg);
             //nickname
             JLabel nickname = new JLabel(inform1[0]); 
             nickname.setBounds(70,27,100,10);
             nickname.setFont(new Font("맑은 고딕", Font.BOLD , 10));
             profile.add(nickname);
             //today's saying
             JLabel message = new JLabel(inform1[1]); 
             message.setBounds(70,45,180,10);
             message.setFont(new Font("맑은 고딕", Font.PLAIN , 10));
             profile.add(message);
             //on/offline state
             String ddddd=o.stating(user_id);
             DrawPanel d = new DrawPanel(ddddd);
             d.setBounds(270, 35, 10, 10);
             profile.add(d);
          pnlFriend.add(profile);
       
          
       //friends profile       
       String inform2[]=null;
       try {
          inform2=o.find_follow(user_id);
       } catch (Exception e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
       }
      int num= Integer.parseInt(inform2[0]);
      int i=1;
      if(inform2[0].equals("1") || inform2[0].equals(null)) {
         num=0;
      }
      
      while(i<num) {          
          //profile
         String inform11[]=null;
          try {
             inform11=o.get_information(inform2[i]);
          } catch (Exception e1) {
             // TODO Auto-generated catch block
             e1.printStackTrace();
          }
          
          JPanel profile1 = new JPanel(null);
          profile1.setBackground (Color.pink);
          profile1.setBounds(0,30+i*80, 310, 80);
         ImageIcon icon1 = new ImageIcon("src/image/profile.png"); 
          Image img1 = icon1.getImage();
          Image changeImg1 = img1.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
          ImageIcon changeIcon1 = new ImageIcon(changeImg1);
          JLabel profileImg1 = new JLabel(changeIcon1);
          profileImg1.setBounds(10,15,50,50);
          profile1.add(profileImg1);
          //nickname
          JLabel nickname1 = new JLabel(inform11[0]);
          nickname1.setBounds(70,27,100,10);
          nickname1.setFont(new Font("맑은 고딕", Font.BOLD , 10));
          profile1.add(nickname1);
          //today's saying
          JLabel message1 = new JLabel(inform11[1]); 
          message1.setBounds(70,45,180,10);
          message1.setFont(new Font("맑은 고딕", Font.PLAIN , 10));
          profile1.add(message1);
          //on/offline state
          String ssss=o.stating(inform2[i]);
          DrawPanel d1 = new DrawPanel(ssss);
          d1.setBounds(270, 35, 10, 10);
          profile1.add(d1);
          Exam18_sub sub=new Exam18_sub(profile1, inform2[i], o, user_id);
                    
          pnlFriend.add(profile1);
         i++;
      }
      pnlFriend.setVisible(true);
   }

   //add friend
   ImageIcon icon=new ImageIcon("src/image/plus.png");
   Image img=icon.getImage();
   Image changeImg=img.getScaledInstance(40,40,Image.SCALE_SMOOTH);
   ImageIcon plusIcon=new ImageIcon(changeImg);
   
   Buttonplus b_plus = new Buttonplus(); 
   class Buttonplus implements ActionListener{
      @Override
      
      public void actionPerformed(ActionEvent e) {
         o.update_time(user_id);
         JButton b = (JButton)e.getSource();
         try {
          updateApi();
       } catch (IOException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
       } catch (ParseException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
       }
         String id=b.getText();
         String temp = null;
         try {
            temp = o.follow(user_id, id);
         } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
         }
         if(temp.equals("true")) {
            JOptionPane.showMessageDialog(null, "success plus");
         }else {
            JOptionPane.showMessageDialog(null, "fail plus");
         }
         scrollSearchPanel.updateUI();
         scrollSearchPanel.removeAll();
         scrollSearchPanel.revalidate();
         scrollSearchPanel.repaint();
   }
}
   
   class get_Button implements ActionListener{
      public void actionPerformed (ActionEvent e) {
         o.update_time(user_id);
            
         JButton b = (JButton)e.getSource();
         try {
          updateApi();
       } catch (IOException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
       } catch (ParseException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
       }
         if(b.getText().equals("검색")) {//if search button clicked, search IDs info
            String samll_id=txtFieldSearch.getText();
           samll_id=samll_id.trim();
            if(samll_id.equals("") || samll_id.equals(null)) {
               samll_id="abcdefghlasjdkflklsdjfljsakldfjklsadf";
            }
            String[] sample=null;
            try {
            sample = o.find_friends(samll_id, user_id);
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
                  String inform[]=null;
                  try {
                     inform=o.get_information(sample[Pnum]);
                  } catch (Exception e1) {
                     // TODO Auto-generated catch block
                     e1.printStackTrace();
                  }
                        //profile
                        ImageIcon icon = new ImageIcon("src/image/profile.png");
                        Image img = icon.getImage();
                        Image changeImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                        ImageIcon changeIcon = new ImageIcon(changeImg);
                        JLabel profileImg = new JLabel(changeIcon);
                        profileImg.setBounds(10,15,50,50);
                        profile.add(profileImg);
                        //nickname
                        JLabel nickname = new JLabel(inform[0]); 
                        nickname.setBounds(70,27,100,10);
                        nickname.setFont(new Font("맑은 고딕", Font.BOLD , 10));
                        profile.add(nickname);
                        //today's saying
                        JLabel message = new JLabel(inform[1]); 
                        message.setBounds(70,45,180,10);
                        message.setFont(new Font("맑은 고딕", Font.PLAIN , 10));
                        profile.add(message);
                        
                        JButton ac_plus = new JButton(sample[Pnum],plusIcon);
                        
                        ac_plus.setBorderPainted(false);
                        ac_plus.setBackground(Color.white);
                      
                        ac_plus.addActionListener(b_plus);
                        ac_plus.setBounds(235, 20, 40, 40);
                        profile.add(ac_plus);
               
               scrollSearchPanel.add(profile);
               Pnum++;
            }
            txtFieldSearch.setText("");
         }
      }
   }
     
   class ButtonListener implements ActionListener{
      @Override
      public void actionPerformed (ActionEvent e) {
         profile_reset();
         JButton b = (JButton)e.getSource();
         o.update_time(user_id);
         try {
         updateApi();
      } catch (IOException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      } catch (ParseException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      }
          if(b.getIcon().equals(friends)) {  //show friend list
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
          else if(b.getIcon().equals(chat)) {//show chatting list
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
          else if(b.getIcon().equals(search)) {//show search panel
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
          else if(b.getIcon().equals(setting)) {//show setting info panel
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
          else if(b.getIcon().equals(ending)) {//end program and update info
                o.end_program(user_id);
                dispose();
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

@Override
public void actionPerformed(ActionEvent e) {}

void updateApi() throws IOException, ParseException {//function to update API info per clicked(once in 1minute)
	if(LocalTime.now().getMinute()!=minute) {//check last API's time and current time
      //API RELOAD
		minute=LocalTime.now().getMinute();
         String[] readApi=a.getApi().split(",");
         
            if(Double.parseDouble(readApi[7])>0) {
               apiImgLabel[0].setIcon(hot);
            }else
               apiImgLabel[0].setIcon(cold);
            
            apiImgLabel[1].setIcon(calender);
            apiImgLabel[2].setIcon(humidity);
            apiImgLabel[3].setIcon(wind);
            apiImgLabel[5].setIcon(location);
            //readApi.length will be 16+2
            //temperature
            apiLabel[0].setText("온도 "+readApi[7]+"℃");
            //used date,time
            apiLabel[1].setText("측정 "+readApi[16]+" "+readApi[17]);
            //humidity
            apiLabel[2].setText("습도 "+readApi[3]+"%");
            //wind speed
            apiLabel[3].setText("풍속 "+readApi[15]+"m/s");
            //rain type, rain drop
            apiLabel[4].setText(readApi[1]+readApi[5]);
            //location
            apiLabel[5].setText("위치 Gachon Univ.");
            
            int chk=1;
            switch(Integer.parseInt(readApi[1])) {
            //NONE(0), RAIN(1), RAIN+SNOW(2), SNOW(3)
            //RAIN DROP(5), RAIN/SNOW DROP(6), SNOW DROP(7)
            case 0:
               chk=0;
                apiLabel[4].setText("눈/비 없음 "+readApi[5]+"mm");
                apiImgLabel[4].setIcon(sun);
               break;
            case 1:
               apiLabel[4].setText("비 "+readApi[5]+"mm");
                apiImgLabel[4].setIcon(rain);
               break;
            case 2:
               apiLabel[4].setText("눈/비 "+readApi[5]+"mm");
                apiImgLabel[4].setIcon(snowrain);
               break;
            case 3:
               apiLabel[4].setText("눈 "+readApi[5]+"mm");
                apiImgLabel[4].setIcon(snow);
               break;
            case 5:
               apiLabel[4].setText("빗방울 "+readApi[5]+"mm");
                apiImgLabel[4].setIcon(rain);
               break;
            case 6:
               apiLabel[4].setText("빗방울/눈날림 "+readApi[5]+"mm");
                apiImgLabel[4].setIcon(snowrain);
               break;
            case 7:
               apiLabel[4].setText("눈날림 "+readApi[5]+"mm");
                apiImgLabel[4].setIcon(snow);
               break;     
            }
         //API RELOAD*
	}
   }
@Override
public void windowOpened(WindowEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void windowClosing(WindowEvent e) {
	// TODO Auto-generated method stub
	o.end_program(user_id);
	o.mainFrame=null;
}
@Override
public void windowClosed(WindowEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void windowIconified(WindowEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void windowDeiconified(WindowEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void windowActivated(WindowEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void windowDeactivated(WindowEvent e) {
	// TODO Auto-generated method stub
	
}
   
}

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class MyFrame extends JFrame{
   public static final int WIDTH = 405;
   public static final int HEIGHT = 595;
   JPanel panel, left, center, bottom;
   //ģ��, ä�ø��, �˻�, ����������
   JButton btnFriend, btnChat, btnSearch, btnUpdateMyData;
   JPanel pnlFriend, pnlChat, pnlSearch, pnlUpdateMyData;
   
   JLabel panelName;
   private JScrollPane scrollFriend;
   ImageIcon icon1, icon2, icon3, icon4, friends, chat, search, setting;
   Image img1, img2, img3, img4, changeImg1, changeImg2, changeImg3, changeImg4;
   
   // ButtonListener
   ButtonListener bl = new ButtonListener();
   
   MyFrame() {
      setTitle ("Kakaotalk");
      setSize (WIDTH, HEIGHT);
      setLocationRelativeTo(null);
      setResizable(false);
      
      Container contentPane = getContentPane();
      
      //0) ��ü panel - ����, �߾�, �Ʒ��� ��ģ
      panel = new JPanel(null);
      panel.setLayout(null);
      
      
      //1) ���� panel - ģ��, ä�ø��, �˻�, ����������
      left = new JPanel(null);
      left.setBounds(0,0,80,460);
      Color leftColor = new Color(236,236,237);
      left.setBackground (leftColor);
         //ģ��, ä�ø��, �˻�, ���������� ��ư�� �߰��Ѵ�
            //1-1)ģ��
            icon1 = new ImageIcon("src/image/friend.png");
            img1 = icon1.getImage();
            changeImg1 = img1.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            friends = new ImageIcon(changeImg1);
            btnFriend = new JButton(friends);
            btnFriend.setBounds(20, 20, 40, 40);
            btnFriend.setBorderPainted(false); //button �׵θ�
            btnFriend.setContentAreaFilled(false); //button ���
            btnFriend.setFocusPainted(false); //button �ȿ� ����ִ� �̹��� �׵θ�
            
            //1-2)ä�ø��
            icon2 = new ImageIcon("src/image/not_chat.png");
            img2 = icon2.getImage();
            changeImg2 = img2.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            chat = new ImageIcon(changeImg2);
            btnChat = new JButton(chat);
            btnChat.setBounds(20, 80, 40, 40);
            btnChat.setBorderPainted(false); //button �׵θ�
            btnChat.setContentAreaFilled(false); //button ���
            btnChat.setFocusPainted(false); //button �ȿ� ����ִ� �̹��� �׵θ�
            
            //1-3)�˻�
            icon3 = new ImageIcon("src/image/not_search.png");
            img3 = icon3.getImage();
            changeImg3 = img3.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            search = new ImageIcon(changeImg3);
            btnSearch = new JButton(search);
            btnSearch.setBounds(20, 140, 40, 40);
            btnSearch.setBorderPainted(false); //button �׵θ�
            btnSearch.setContentAreaFilled(false); //button ���
            btnSearch.setFocusPainted(false); //button �ȿ� ����ִ� �̹��� �׵θ�
            
            //1-4)����
            icon4 = new ImageIcon("src/image/not_setting.png");
            img4 = icon4.getImage();
            changeImg4 = img4.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            setting = new ImageIcon(changeImg4);
            btnUpdateMyData = new JButton(setting);
            btnUpdateMyData.setBounds(20, 410, 40, 40);
            btnUpdateMyData.setBorderPainted(false); //button �׵θ�
            btnUpdateMyData.setContentAreaFilled(false); //button ���
            btnUpdateMyData.setFocusPainted(false); //button �ȿ� ����ִ� �̹��� �׵θ� 
            
         left.add(btnFriend);
         left.add(btnChat);
         left.add(btnSearch);
         left.add(btnUpdateMyData);
         
            
         btnFriend.addActionListener(bl);
         btnChat.addActionListener(bl);
         btnSearch.addActionListener(bl);
         btnUpdateMyData.addActionListener(bl);
      panel.add(left);
      
      
      //2) �߾� panel - ģ��list
      int listCount = 0; //<---------------------------------DB���� ģ���� ������� �о�� �� ����
      center = new JPanel(null);
      center.setBounds(80,0,325,460);
      center.setBackground (Color.white);
         //2-1)ģ�� ��� ȭ�� - scollPane �ȿ� ���� panel
         pnlFriend = new JPanel(null);
         pnlFriend.setPreferredSize(new Dimension(313, 1000));
         pnlFriend.setBackground(Color.black);
            //title - ģ��
            JPanel titleProfile = new JPanel(null);
            titleProfile.setBackground (Color.white);
            JLabel title = new JLabel("ģ��");
            title.setFont(new Font("���� ���", Font.BOLD , 18));
            title.setBounds(10,7,100,20);
            titleProfile.add(title);
            titleProfile.setBounds(0,0,295, 35);
            pnlFriend.add(titleProfile);
            
            //�� ������
            JPanel profile = new JPanel(null);
            profile.setBackground (Color.white);
            profile.setBounds(0,35,310, 80);
                  //�����ʻ���
                  ImageIcon icon = new ImageIcon("src/image/profile.png"); //<----------------------- DB : �α��� �� ����� �����ʻ���
                  Image img = icon.getImage();
                  Image changeImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                  ImageIcon changeIcon = new ImageIcon(changeImg);
                  JLabel profileImg = new JLabel(changeIcon);
                  profileImg.setBounds(10,15,50,50);
                  profile.add(profileImg);
                  //����
                  JLabel nickname = new JLabel("�����ʺ���"); //<----------------------- DB : �α��� �� ����� ����
                  nickname.setBounds(70,27,100,10);
                  nickname.setFont(new Font("���� ���", Font.BOLD , 10));
                  profile.add(nickname);
                  //������ �Ѹ���
                  JLabel message = new JLabel("�������Ѹ���������Ѹ���������Ѹ���������Ѹ���"); //<----------------------- DB : �α��� �� ����� "������ �Ѹ���"
                  message.setBounds(70,45,180,10);
                  message.setFont(new Font("���� ���", Font.PLAIN , 10));
                  profile.add(message);
                  //�¿������� ����
                  DrawPanel d = new DrawPanel();
                  d.setBounds(270, 35, 10, 10);
                  profile.add(d);
               pnlFriend.add(profile);
               
            //ģ�������� ����
            JPanel profile1 = new JPanel();
            profile1.setBackground (Color.pink);
            profile1.setBounds(0,160, 310, 80);
            pnlFriend.add(profile1);
               
         //��ũ�ѹ� ���Խ��Ѿ� ��
         scrollFriend = new JScrollPane(pnlFriend, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
         scrollFriend.setBounds(0, 0, 313, 460);
         center.add(scrollFriend);
         
            //�� ������ ����
            //JPanel profile = new JPanel();
            //profile.setBackground (Color.BLUE);
            //center.add(profile);
            //for (int i=0; i<listCount; i++) { //ģ������ŭ ������ ���̰� �ϱ�
               
            //}
               
         
         //2-2)ä��
         pnlChat = new JPanel(null);
         pnlChat.setPreferredSize(new Dimension(313, 1000));
         pnlChat.setBackground(Color.yellow);
            //title - ä��
            JPanel titleChat = new JPanel(null);
            titleChat.setBackground (Color.white);
            JLabel chat = new JLabel("ä��");
            chat.setFont(new Font("���� ���", Font.BOLD , 18));
            chat.setBounds(10,7,100,20);
            titleChat.add(chat);
            titleChat.setBounds(0,0,310, 35);
            pnlChat.add(titleChat);
         
         
         //2-3)�˻�
         pnlSearch = new JPanel(null);
         pnlSearch.setPreferredSize(new Dimension(313, 450));
         pnlSearch.setBackground(Color.orange);
            //title - �˻�
            JPanel titleSearch = new JPanel(null);
            titleSearch.setBackground (Color.white);
            JLabel search = new JLabel("ģ�� �߰�");
            search.setFont(new Font("���� ���", Font.BOLD , 18));
            search.setBounds(10,7,100,20);
            titleSearch.add(search);
            titleSearch.setBounds(0,0,310, 35);
            pnlSearch.add(titleSearch);
            
            //label - ID�� �߰�
            JLabel labelPlus = new JLabel("ID�� �߰�");
            labelPlus.setFont(new Font("���� ���", Font.PLAIN , 15));
            labelPlus.setBounds(10, 60, 100, 15);
            pnlSearch.add(labelPlus);
                  
            //�Է�â - �˻�
            JTextField txtFieldSearch = new JTextField();
            txtFieldSearch.setBounds(10, 85, 230, 25);
            pnlSearch.add(txtFieldSearch);
            
            //��ư - �˻�
            JButton btnsearchList = new JButton("�˻�");
            btnsearchList.setFont(new Font("���� ���", Font.PLAIN , 5));
            btnsearchList.setBounds(243, 85, 50, 25);
            pnlSearch.add(btnsearchList);
            btnsearchList.addActionListener(bl);
            
            //���â - �˻� ���
            JPanel scrollSearchPanel = new JPanel(null);
            scrollSearchPanel.setBackground(Color.cyan);
            scrollSearchPanel.setPreferredSize(new Dimension(313, 1000));  //<---------�˻����� �� ������ ������ ������ ���� ���� ����
            JScrollPane scrollSearch = new JScrollPane(scrollSearchPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollSearch.setBounds(0, 120, 313, 340);
            pnlSearch.add(scrollSearch);

      
         //2-4)����
         pnlUpdateMyData = new JPanel(null);
         pnlUpdateMyData.setPreferredSize(new Dimension(313, 450));
         pnlUpdateMyData.setBackground(Color.white);
            //title - ����
            JPanel titleUpdateMyData = new JPanel(null);
            titleUpdateMyData.setBackground (Color.WHITE);
            JLabel updateMyData = new JLabel("����");
            updateMyData.setFont(new Font("���� ���", Font.BOLD , 18));
            updateMyData.setBounds(10,7,100,20);
            titleUpdateMyData.add(updateMyData);
            titleUpdateMyData.setBounds(0,0,310, 35);
            pnlUpdateMyData.add(titleUpdateMyData);
            
            //��й�ȣ ����
               //label - ��й�ȣ ����
               JLabel labelUpdatePassword = new JLabel("��й�ȣ ����");
               labelUpdatePassword.setFont(new Font("���� ���", Font.PLAIN , 12));
               labelUpdatePassword.setBounds(10, 60, 100, 15);
               pnlUpdateMyData.add(labelUpdatePassword);
                     
               //�Է�â - ��й�ȣ
               JPasswordField passwordFieldUpdate = new JPasswordField();
               passwordFieldUpdate.setBounds(10, 85, 230, 25);
               pnlUpdateMyData.add(passwordFieldUpdate);
               
               //��ư - ����
               JButton btnUpdatePassword = new JButton("����");
               btnUpdatePassword.setFont(new Font("���� ���", Font.PLAIN , 8));
               btnUpdatePassword.setBounds(243, 85, 50, 25);
               btnUpdatePassword.addActionListener(bl);
               pnlUpdateMyData.add(btnUpdatePassword);
            
            //���� ����
               //label - ���� ����
               JLabel labelNicknameUpdate = new JLabel("���� ����");
               labelNicknameUpdate.setFont(new Font("���� ���", Font.PLAIN , 12));
               labelNicknameUpdate.setBounds(10, 130, 100, 15);
               pnlUpdateMyData.add(labelNicknameUpdate);
                     
               //�Է�â - ����
               JTextField txtFieldNicknameUpdate = new JTextField();
               txtFieldNicknameUpdate.setBounds(10, 155, 230, 25);
               pnlUpdateMyData.add(txtFieldNicknameUpdate);
               
               //��ư - ����
               JButton btnUpdateNickname = new JButton("����");
               btnUpdateNickname.setFont(new Font("���� ���", Font.PLAIN , 8));
               btnUpdateNickname.setBounds(243, 155, 50, 25);
               btnUpdateNickname.addActionListener(bl);
               pnlUpdateMyData.add(btnUpdateNickname);
               
            //������ �Ѹ��� ����
               //label - ������ �Ѹ��� ����
               JLabel labelSentenceUpdate = new JLabel("������ �Ѹ��� ����");
               labelSentenceUpdate.setFont(new Font("���� ���", Font.PLAIN , 12));
               labelSentenceUpdate.setBounds(10, 202, 150, 15);
               pnlUpdateMyData.add(labelSentenceUpdate);
                     
               //�Է�â - ����
               JTextField txtFieldSentenceUpdate = new JTextField();
               txtFieldSentenceUpdate.setBounds(10, 225, 230, 25);
               pnlUpdateMyData.add(txtFieldSentenceUpdate);
               
               //��ư - ����
               JButton btnUpdateSentence = new JButton("����");
               btnUpdateSentence.setFont(new Font("���� ���", Font.PLAIN , 8));
               btnUpdateSentence.setBounds(243, 225, 50, 25);
               btnUpdateSentence.addActionListener(bl);
               pnlUpdateMyData.add(btnUpdateSentence);
               
            //Ż�� ��ư
            JButton btnWithdrawal = new JButton("Ż��");
            btnWithdrawal.setFont(new Font("���� ���", Font.PLAIN , 8));
            btnWithdrawal.setBounds(10, 270, 50, 25);
            btnWithdrawal.addActionListener(bl);
            pnlUpdateMyData.add(btnWithdrawal);
            
      panel.add(center);
      
      

      
      
      
      
      
      
      //3) �Ʒ� panel - ���������� ���
      bottom = new JPanel(null);
      bottom.setBounds(0,460,405,135);
      Color bottomColor = new Color(248,248,248);
      bottom.setBackground (bottomColor);
      panel.add(bottom);
      
      
      
      contentPane.add(panel, BorderLayout.CENTER);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
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
            btnFriend.setBorderPainted(false); //button �׵θ�
            btnFriend.setContentAreaFilled(false); //button ���
            btnFriend.setFocusPainted(false); //button �ȿ� ����ִ� �̹��� �׵θ�
            img2 = icon2.getImage();
            changeImg2 = img2.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            chat = new ImageIcon(changeImg2);
            btnChat = new JButton(chat);
            btnChat.setBounds(20, 80, 40, 40);
            btnChat.setBorderPainted(false); //button �׵θ�
            btnChat.setContentAreaFilled(false); //button ���
            btnChat.setFocusPainted(false); //button �ȿ� ����ִ� �̹��� �׵θ�
            img3 = icon3.getImage();
            changeImg3 = img3.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            search = new ImageIcon(changeImg3);
            btnSearch = new JButton(search);
            btnSearch.setBounds(20, 140, 40, 40);
            btnSearch.setBorderPainted(false); //button �׵θ�
            btnSearch.setContentAreaFilled(false); //button ���
            btnSearch.setFocusPainted(false); //button �ȿ� ����ִ� �̹��� �׵θ�
            img4 = icon4.getImage();
            changeImg4 = img4.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            setting = new ImageIcon(changeImg4);
            btnUpdateMyData = new JButton(setting);
            btnUpdateMyData.setBounds(20, 410, 40, 40);
            btnUpdateMyData.setBorderPainted(false); //button �׵θ�
            btnUpdateMyData.setContentAreaFilled(false); //button ���
            btnUpdateMyData.setFocusPainted(false); //button �ȿ� ����ִ� �̹��� �׵θ� 
            
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
   
   public static void main(String[] args) {
      MyFrame m = new MyFrame();
   }
   
   class DrawPanel extends JPanel {  //<----------�¿������� ��Ÿ���� ���׶�� ���� �Լ�
      String state = ""; //<--------------------- DB���� ���¸� �о state��� string ������ ����
      
      @Override
      public void paint(Graphics g) {
         super.paint(g);
         g.setColor(Color.RED); //���� default������ "off"�� �س��´� 
         if (state.equalsIgnoreCase("on") == true) {   //<------------ DB���� �о��� �� on ������ ���
            g.setColor(Color.GREEN);
         }
         g.fillOval(0, 0, 10, 10);
         
      }
   }
   
}
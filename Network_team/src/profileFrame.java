import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;


class profileFrame extends JFrame {
   public static final int WIDTH = 305;
   public static final int HEIGHT = 460;
   
   profileFrame(String id, Operator _o) {
	   String temp[]=_o.profile(id);
	   for(int i=0; i<10; i++) {
		   System.out.println(temp[i]);
	   }
	   
      setTitle("profile");
      setSize(WIDTH,HEIGHT);
      setLocationRelativeTo(null);
      setResizable(false);
      
      Container contentPane = getContentPane();
      
         //0) the entire panel
         JPanel panelAll = new JPanel(null);
         panelAll.setLayout(null);
         Color backColor = new Color(132,139,145);
         panelAll.setBackground(backColor);
         contentPane.add(panelAll, BorderLayout.CENTER);
         
         //1) Profile photo
         ImageIcon icon = new ImageIcon("src/image/profile2.png"); 
         Image img = icon.getImage();
         Image changeImg = img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
         ImageIcon changeIcon = new ImageIcon(changeImg);
      
         JLabel profileImg = new JLabel(changeIcon);
         profileImg.setBounds(105, 100, 80, 80);
         panelAll.add(profileImg);
         
         //2) Login status - On/Off status
         String ddddd=_o.stating(id);
         DrawPanel d = new DrawPanel(ddddd);
         d.setBackground(backColor);
         d.setBounds(187, 100, 10, 10);
         panelAll.add(d);
         
         
         
         //3) nick_name + name
         //String name;
         //String nick;  //<---------------------------- DB :  Read the nickname and change it to new JLabel(nick + "(" + name + ")") below
         JLabel userNickname = new JLabel(temp[0] + " (" + temp[1] + ")");
         userNickname.setFont(new Font("맑은 고딕", Font.BOLD, 22));
         userNickname.setForeground(Color.WHITE); //폰트 색상
         userNickname.setBounds(15, 185, 260, 25);
         userNickname.setHorizontalAlignment(JLabel.CENTER); //Center the text in the label
         panelAll.add(userNickname);
         
         //4) Today's words
         //String saying;  //<---------------------------- DB : Read the status message and change it to new JLabel (saying) below 
         JLabel userSentence = new JLabel("<html>"+temp[2]+"</html>");
         userSentence.setFont(new Font("맑은 고딕", Font.BOLD, 14));
         userSentence.setForeground(Color.WHITE);
         userSentence.setBounds(15, 210, 260, 40);
         userSentence.setHorizontalAlignment(JLabel.CENTER); 
         userSentence.setVerticalAlignment(JLabel.NORTH);
         panelAll.add(userSentence);
         
         //5) phone_number
         //String num;  //<---------------------------- DB : Read the status message and change it to new JLabel (site) below
         JLabel userNum = new JLabel("전화번호 : "+temp[3]);
         userNum.setFont(new Font("맑은 고딕", Font.BOLD, 12));
         userNum.setForeground(Color.WHITE);
         userNum.setBounds(15, 265, 260, 20);
         userNum.setHorizontalAlignment(JLabel.CENTER); 
         panelAll.add(userNum);
         
         //6) birth date
         //String birth;  //<---------------------------- DB : Read the status message and change it to new JLabel (birth) below
         JLabel userbirth = new JLabel("생년월일 : "+temp[4]);
         userbirth.setFont(new Font("맑은 고딕", Font.BOLD, 12));
         userbirth.setForeground(Color.WHITE);
         userbirth.setBounds(15, 285, 260, 20);
         userbirth.setHorizontalAlignment(JLabel.CENTER); 
         panelAll.add(userbirth);
         
         //7) ID
         //String id;  //<---------------------------- DB : Read the status message and change it to new JLabel(id) below
         JLabel userId = new JLabel("ID : "+temp[5]);
         userId.setFont(new Font("맑은 고딕", Font.BOLD, 12));
         userId.setForeground(Color.WHITE);
         userId.setBounds(15, 305, 260, 20);
         userId.setHorizontalAlignment(JLabel.CENTER);
         panelAll.add(userId);
         
         //8) email
         //String email;  //<---------------------------- DB : Read the status message and change it to new JLabel (email) below
         JLabel userEmail = new JLabel("이메일 : "+temp[6]);
         userEmail.setFont(new Font("맑은 고딕", Font.BOLD, 12));
         userEmail.setForeground(Color.WHITE);
         userEmail.setBounds(15, 325, 260, 20); 
         userEmail.setHorizontalAlignment(JLabel.CENTER); 
         panelAll.add(userEmail);
         
         //9) github
         //String site;  //<---------------------------- DB : 상태메세지를 읽어서 아래 new JLabel(site)으로 변경 
         JLabel userSite = new JLabel("사이트 : "+temp[7]);
         userSite.setFont(new Font("맑은 고딕", Font.BOLD, 12));
         userSite.setForeground(Color.WHITE);
         userSite.setBounds(15, 345, 260, 20);
         userSite.setHorizontalAlignment(JLabel.CENTER); //label 안에서 text를 가운데 배치
         //userSite.setBorder(new LineBorder(Color.black, 1, true)); //label 사이즈 확인용 테두리
         panelAll.add(userSite);
         
         //10)last access_time
         //String lastTime;  //<---------------------------- DB : 상태메세지를 읽어서 아래 new JLabel(lastTime)으로 변경 
         JLabel userlastTime = new JLabel("최종접속시간: " + temp[8]);
         userlastTime.setFont(new Font("맑은 고딕", Font.BOLD, 11));
         userlastTime.setForeground(Color.WHITE);
         userlastTime.setBounds(15, 403, 260, 20);
         userlastTime.setHorizontalAlignment(JLabel.CENTER);
         panelAll.add(userlastTime);
         setVisible(true);
   }
   
}

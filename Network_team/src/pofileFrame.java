import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;


class profileFrame extends JFrame {
   public static final int WIDTH = 305;
   public static final int HEIGHT = 460;
   
   profileFrame() {
      setTitle("profile");
      setSize(WIDTH,HEIGHT);
      setLocationRelativeTo(null);
      setResizable(false);
      
      Container contentPane = getContentPane();
      
         //0) 전체 panel
         JPanel panelAll = new JPanel(null);
         panelAll.setLayout(null);
         Color backColor = new Color(132,139,145);
         panelAll.setBackground(backColor);
         contentPane.add(panelAll, BorderLayout.CENTER);
         
         //1) 프로필 사진
         ImageIcon icon = new ImageIcon("src/image/profile2.png"); //<----------------------- DB : 로그인 한 사람의 프로필사진
         Image img = icon.getImage();
         Image changeImg = img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
         ImageIcon changeIcon = new ImageIcon(changeImg);
      
         JLabel profileImg = new JLabel(changeIcon);
         profileImg.setBounds(105, 100, 80, 80);
         panelAll.add(profileImg);
         
         //2) 로그인 여부 - 온오프라인 상태
         DrawPanel d = new DrawPanel();
         d.setBackground(backColor);
         d.setBounds(187, 100, 10, 10);
         panelAll.add(d);
         
         //3) 별명 + 이름
         //String name;
         //String nick;  //<---------------------------- DB : 별명을 읽어서 아래 new JLabel(nick + " (" + name + ")")으로 변경 
         JLabel userNickname = new JLabel("오일러 강" + " (" + "강태훈" + ")");
         userNickname.setFont(new Font("맑은 고딕", Font.BOLD, 22));
         userNickname.setForeground(Color.WHITE); //폰트 색상
         userNickname.setBounds(15, 185, 260, 25);
         userNickname.setHorizontalAlignment(JLabel.CENTER); //label 안에서 text를 가운데 배치
         //userNickname.setBorder(new LineBorder(Color.black, 1, true)); //label 사이즈 확인용 테두리
         panelAll.add(userNickname);
         
         //4) 오늘의 한마디
         //String saying;  //<---------------------------- DB : 상태메세지를 읽어서 아래 new JLabel(saying)으로 변경 
         JLabel userSentence = new JLabel("<html>종강하고싶다종강하고싶다종강하<br>고싶다종강하고싶다종강하고싶다</html>");
         userSentence.setFont(new Font("맑은 고딕", Font.BOLD, 14));
         userSentence.setForeground(Color.WHITE);
         userSentence.setBounds(15, 210, 260, 40);
         userSentence.setHorizontalAlignment(JLabel.CENTER); //label 안에서 text를 가운데 배치
         userSentence.setVerticalAlignment(JLabel.NORTH);
         //userSentence.setBorder(new LineBorder(Color.black, 1, true)); //label 사이즈 확인용 테두리
         panelAll.add(userSentence);
         
         //5) 전화번호
         //String num;  //<---------------------------- DB : 상태메세지를 읽어서 아래 new JLabel(site)으로 변경 
         JLabel userNum = new JLabel("전화번호 : "+"010-9215-9227");
         userNum.setFont(new Font("맑은 고딕", Font.BOLD, 12));
         userNum.setForeground(Color.WHITE);
         userNum.setBounds(15, 265, 260, 20);
         userNum.setHorizontalAlignment(JLabel.CENTER); //label 안에서 text를 가운데 배치
         //userNum.setBorder(new LineBorder(Color.black, 1, true)); //label 사이즈 확인용 테두리
         panelAll.add(userNum);
         
         //6) 생년월일
         //String birth;  //<---------------------------- DB : 상태메세지를 읽어서 아래 new JLabel(birth)으로 변경 
         JLabel userbirth = new JLabel("생년월일 : "+"2000-03-16");
         userbirth.setFont(new Font("맑은 고딕", Font.BOLD, 12));
         userbirth.setForeground(Color.WHITE);
         userbirth.setBounds(15, 285, 260, 20);
         userbirth.setHorizontalAlignment(JLabel.CENTER); //label 안에서 text를 가운데 배치
         //userbirth.setBorder(new LineBorder(Color.black, 1, true)); //label 사이즈 확인용 테두리
         panelAll.add(userbirth);
         
         //7) ID
         //String id;  //<---------------------------- DB : 상태메세지를 읽어서 아래 new JLabel(id)으로 변경 
         JLabel userId = new JLabel("ID : "+"aaaaaaaaaaasubin9227");
         userId.setFont(new Font("맑은 고딕", Font.BOLD, 12));
         userId.setForeground(Color.WHITE);
         userId.setBounds(15, 305, 260, 20);
         userId.setHorizontalAlignment(JLabel.CENTER); //label 안에서 text를 가운데 배치
         //userId.setBorder(new LineBorder(Color.black, 1, true)); //label 사이즈 확인용 테두리
         panelAll.add(userId);
         
         //8) email
         //String email;  //<---------------------------- DB : 상태메세지를 읽어서 아래 new JLabel(email)으로 변경 
         JLabel userEmail = new JLabel("이메일 : "+"aaaaaaaaaaasubin9227@naver.com");
         userEmail.setFont(new Font("맑은 고딕", Font.BOLD, 12));
         userEmail.setForeground(Color.WHITE);
         userEmail.setBounds(15, 325, 260, 20); 
         userEmail.setHorizontalAlignment(JLabel.CENTER); //label 안에서 text를 가운데 배치
         //userEmail.setBorder(new LineBorder(Color.black, 1, true)); //label 사이즈 확인용 테두리
         panelAll.add(userEmail);
         
         //9) github/홈페이지 주소 등
         //String site;  //<---------------------------- DB : 상태메세지를 읽어서 아래 new JLabel(site)으로 변경 
         JLabel userSite = new JLabel("사이트 : "+"m.blog.naver.com/subin9227");
         userSite.setFont(new Font("맑은 고딕", Font.BOLD, 12));
         userSite.setForeground(Color.WHITE);
         userSite.setBounds(15, 345, 260, 20);
         userSite.setHorizontalAlignment(JLabel.CENTER); //label 안에서 text를 가운데 배치
         //userSite.setBorder(new LineBorder(Color.black, 1, true)); //label 사이즈 확인용 테두리
         panelAll.add(userSite);
         
         //10) 최종접속시간
         //String lastTime;  //<---------------------------- DB : 상태메세지를 읽어서 아래 new JLabel(lastTime)으로 변경 
         JLabel userlastTime = new JLabel("최종접속시간: " + "2022-11-26 20:22:27.144347700");
         userlastTime.setFont(new Font("맑은 고딕", Font.BOLD, 11));
         userlastTime.setForeground(Color.WHITE);
         userlastTime.setBounds(15, 403, 260, 20);
         userlastTime.setHorizontalAlignment(JLabel.CENTER); //label 안에서 text를 가운데 배치
         //userlastTime.setBorder(new LineBorder(Color.black, 1, true)); //label 사이즈 확인용 테두리
         panelAll.add(userlastTime);
   }
   
   public static void main(String[] args) {
      profileFrame p = new profileFrame();
      p.setVisible(true);
   }
}
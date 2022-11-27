import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class Exam18_sub extends JFrame implements MouseListener {
     private Container con;
     private JLabel lb = new JLabel("메모를 하십시요 "); // 라벨 생성
     private JTextArea ta = new JTextArea();  // 텍스트 아레아 생성
     private JScrollPane jsp = new JScrollPane(ta); // 텍스트 아레아에 스크롤 팬 생성
     private JPopupMenu jpm = new JPopupMenu(); // 팝업 메뉴 생성
     private JMenuItem jmi = new JMenuItem("1"); // 팝업 아이템 생성
     private JMenuItem jmi1 = new JMenuItem("2"); //팝업 아이템 생성
     private JMenuItem jmi2 = new JMenuItem("3"); // 팝업 아이템 생성
 

public Exam18_sub(){
      super("Exam18");
      this.init();
      this.start();
      this.setSize(300,300);
      this.setVisible(true);
 }
 public void init(){
	 menuButton bl =new menuButton();
	 jmi.addActionListener(bl);
	 jmi1.addActionListener(bl);
	 jmi2.addActionListener(bl);
      jpm.add(jmi);
      jpm.add(jmi1);
      jpm.add(jmi2);
  
  con = this.getContentPane();
  con.setLayout(new BorderLayout());
  con.add("North",lb);
  con.add("Center",jsp);
  
 }
 public void start(){
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      ta.addMouseListener(this);
 }
String s;
// 마우스 리스너 모두 재정의 해야 함
 public void mouseClicked(MouseEvent e){}
 public void mousePressed(MouseEvent e){}
 public void mouseReleased(MouseEvent e){
  if(e.getSource() == ta && e.isPopupTrigger() == true){   //마우스 우측 버튼 여부 검사
   //팝업
   jpm.show(ta,e.getX(),e.getY());
   s=ta.getText();
  }
 }
 public void mouseEntered(MouseEvent e){}
 public void mouseExited(MouseEvent e){}
 public static void main(String[] args) {
	 Exam18_sub temp=new Exam18_sub();
}
 class menuButton implements ActionListener{
     @Override
     public void actionPerformed(ActionEvent e) {
    	 JMenuItem b = (JMenuItem)e.getSource();
    	 System.out.println(b.getText()+s);
    	 
        //위의 코드는 게시물을 올리는 부분이다. 그럼 새롭게 update한 게시물을 바로 화면에 나타나게 하기 위해 p_reset함수를 사용(위에서 설명) 
     }
  }
 
}
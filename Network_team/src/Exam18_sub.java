import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.filechooser.FileSystemView;


public class Exam18_sub {
   private JPopupMenu jpm = new JPopupMenu(); //make popup menu when mouse right button clicked
   //list of popup menu
      private JMenuItem jmi = new JMenuItem("profile"); 
      private JMenuItem jmi1 = new JMenuItem("chatting"); 
      private JMenuItem jmi2 = new JMenuItem("delete"); 
      private JMenuItem jmi3 = new JMenuItem("sending file");
      private JMenuItem jmi4 = new JMenuItem("game"); 
      
      public JPanel temp;
      String id;
      Operator o;
      String user_id;
      public Exam18_sub(JPanel temp, String id, Operator _o, String user_id) {
         this.id=id;
         this.temp=temp;
         this.user_id=user_id;
         o=_o;
         init();
         start();
   }
      public void init(){//init buttons
          menuButton bl =new menuButton();
          jmi.addActionListener(bl);
          jmi1.addActionListener(bl);
          jmi2.addActionListener(bl);
          jmi3.addActionListener(bl);
          jmi4.addActionListener(bl);
            jpm.add(jmi);
            jpm.add(jmi1);
            jpm.add(jmi2);
            jpm.add(jmi3);
            jpm.add(jmi4);
       }
       public void start(){
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            MyMouseListener listener = new MyMouseListener();
            temp.addMouseListener(listener);
       }
       private void setDefaultCloseOperation(int exitOnClose) {}
      class MyMouseListener implements MouseListener{
         //use mouseListener
          public void mouseClicked(MouseEvent e){}
          public void mousePressed(MouseEvent e){}
          public void mouseReleased(MouseEvent e){
           if(e.getSource() == temp && e.isPopupTrigger() == true){   //if mouse right button clicked
            //show popup menu
            jpm.show(temp,e.getX(),e.getY());
            
           }
          }
          public void mouseEntered(MouseEvent e){}
          public void mouseExited(MouseEvent e){}
       }       
       class menuButton implements ActionListener{
           private static final char[] File = null;
         @Override
           public void actionPerformed(ActionEvent e) {//if popup menu is clicked
              JMenuItem b = (JMenuItem)e.getSource();
              String s=b.getText();
              if(s.equals("profile")) {//profile button clicked
            	  //show profile
                 o.pF=new profileFrame(id, o);
              }else if(s.equals("delete")) {//delete button clicked
            	  //delete in friend list
                 o.delete(user_id, id);
                 o.mainFrame.profile_reset();
                 o.mainFrame.pnlFriend.setVisible(true);
                 o.mainFrame.scrollFriend.setViewportView(o.mainFrame.pnlFriend);
              }else if(s.equals("sending file")) {//sending file button clicked
            	  //send file
                 JFileChooser jfc;
                 jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                 int returnValue = jfc.showOpenDialog(null);
                   File route = jfc.getSelectedFile();
                   try {
                  o.sending_file(user_id, id, route);
               } catch (IOException e1) {
                  e1.printStackTrace();
               }
              }else if(s.equals("chatting")) {//chatting button clicked
            	  //chatting
                 try {
                	o.make_chat(user_id, id);
                       o.cF=new chatFrame(user_id,id,o);
                       
                       o.thread_count=1;
                    } catch (Exception e1) {
                       e1.printStackTrace();
                    }
              }else if(s.equals("game")) {//game button clicked
                 //game
              }
           }

        }

}




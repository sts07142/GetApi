import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;


public class chatFrame extends JFrame {//chatting frame
   public static final int WIDTH = 390;
   public static final int HEIGHT = 630;
   ImageIcon Imgclip, changeIcon3, changeIcon4, changeIcon5,chatReload;
   JFileChooser jfc;
   JTextArea txtField;
   JPanel panelAll, chatName, screen;
   int startX = 200, startY = 10, widthX = 150, heightY = 0; //chat bubble & file's space in screen (x,y, width, height)
   int screenHeight=30;
   ButtonListener bl = new ButtonListener();
   String id="",chatid="";
   Operator o;
   chatFrame(){}
   chatFrame(String id, String chatid,Operator _o) throws Exception {
      this.id=id;
      this.chatid=chatid;
      o=_o;
      setTitle("chat");
      setSize(WIDTH,HEIGHT);
      setLocationRelativeTo(null);
      setResizable(false);
      setVisible(true);
      Container contentPane = getContentPane();
      
      ImageIcon frameIcon = new ImageIcon("src/image/LOGO.png");
      setIconImage(frameIcon.getImage());
      //0) All panel
      panelAll = new JPanel(null);
      contentPane.add(panelAll);
      
      //1) chat name panel
      chatName = new JPanel(null);
      chatName.setBackground(new Color(236, 236, 237));
      chatName.setBounds(0,0,390,60);
         //1-1) profile img
         ImageIcon icon1 = new ImageIcon("src/image/profile2.png");
          Image img1 = icon1.getImage();
          Image changeImg1 = img1.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
          ImageIcon changeIcon = new ImageIcon(changeImg1);
          JLabel profileImg = new JLabel(changeIcon);
          profileImg.setBounds(10,8,45,45);
          chatName.add(profileImg);
         //1-2) chat user name
          String[] a=o.get_information(chatid);
          JLabel chatUserName = new JLabel(a[0]);
          chatUserName.setFont(new Font("Arial", Font.PLAIN , 16));
          chatUserName.setBounds(70, 15, 200, 30);
          chatName.add(chatUserName);
          //1-3) user on/offline state
          DrawPanel d = new DrawPanel(chatid);
         d.setBounds(350, 25, 10, 10);
         chatName.add(d);
      panelAll.add(chatName);
      
      //2) chat screen panel
      screen = new JPanel(null); 
      screen.setPreferredSize(new Dimension(370, screenHeight));
      Color backColor = new Color(186, 206, 224);
      screen.setBackground(backColor);
      JScrollPane screenScrollbar = new JScrollPane(screen, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      screenScrollbar.setBounds(0, 60, 378, 410);
      panelAll.add(screenScrollbar);
      
      
      
      //3) chat textArea & button panel
      JPanel chatting = new JPanel(null);
      chatting.setBackground(Color.WHITE);
      chatting.setBounds(0,470, 390, 125);
         //3-1) chat textArea
            txtField = new JTextArea();
            txtField.setLineWrap(true);
            txtField.setBounds(8, 8, 370, 85);
            txtField.setBorder(new TitledBorder(new LineBorder(Color.GRAY, 1)));
            JScrollPane txtScrollbar = new JScrollPane(txtField, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            txtScrollbar.setBounds(3, 3, 370, 87);
            chatting.add(txtScrollbar);

         //3-2) chat button
            //button1 - send file      
            ImageIcon icon2 = new ImageIcon("src/image/clip.png");
            Image img2 = icon2.getImage();
            Image changeImg2 = img2.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            Imgclip = new ImageIcon(changeImg2);
            JButton btnClip = new JButton(Imgclip);
            btnClip.setBounds(4, 90, 30, 30);
            btnClip.setBorderPainted(false); //button Border
            btnClip.setContentAreaFilled(false); //button Background
            btnClip.setFocusPainted(false); //image border in button
            btnClip.addActionListener(bl);
            chatting.add(btnClip);
            
            jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            
            //button2 - send message
            JButton btnSend = new JButton("전송");
            btnSend.setFont(new Font("맑은 고딕", Font.PLAIN , 10));
            btnSend.setBounds(312, 92, 60, 25);
            btnSend.setBackground(new Color(250, 225, 0));
            btnSend.setFocusPainted(false);
            btnSend.addActionListener(bl);
            chatting.add(btnSend);
            
            ImageIcon icon4 = new ImageIcon("src/image/reload.png");
            Image img4 = icon4.getImage();
            Image changeImg4 = img4.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            chatReload = new ImageIcon(changeImg4);
            JButton btnReload = new JButton(chatReload);
            btnReload.setBounds(280, 90, 30, 30);
            btnReload.setBorderPainted(false); //button Border
            btnReload.setContentAreaFilled(false); //button Background
            btnReload.setFocusPainted(false); //image border in button
            btnReload.addActionListener(bl);
            chatting.add(btnReload);
            
      panelAll.add(chatting);
   }
   
   class ButtonListener implements ActionListener{
      @Override
      public void actionPerformed (ActionEvent e) {
         JButton b = (JButton)e.getSource();
         
         if(b.getText().equals("전송")) {   //if send button is clicked
            String txt = txtField.getText();
            
            if(txt.equals("")||txt.equals(null))
               return;
            
            o.chat(id, chatid, txt);
            
            StringBuffer t = new StringBuffer(txt);
            
            int cnt = 0; //count the speechBubble length
            startX = 200; widthX = 150; //set the speechBubble x, width
            
            char[] txtArr=txt.toCharArray();
            int Length=txt.length();//txt length
            int[] lenArr=new int[Length];//char's byte length
            int[] charLen=new int[Length];//char's length in gui
            
            for(int i=0; i<Length; i++) { //set text ballon's size
               int code=(int)txtArr[i];
               // 3bytes //12
                if ((txtArr[i] < '0' || txtArr[i] > '9') && (txtArr[i] < 'A' || txtArr[i] > 'Z') 
                   && (txtArr[i] < 'a' || txtArr[i] > 'z') &&code > 255) lenArr[i]= 3;
                // 1bytes //8
                else lenArr[i]=1;
                
                if(txtArr[i]=='`' || txtArr[i]=='!' || txtArr[i]=='^' || txtArr[i]=='*' || txtArr[i]=='(' || txtArr[i]==')' || txtArr[i]=='-'|| txtArr[i]=='/'|| txtArr[i]=='['|| txtArr[i]=='{'|| txtArr[i]==']'|| txtArr[i]=='}'
                   || txtArr[i]=='\\'|| txtArr[i]=='"') 
                   charLen[i]=6;
                else if(txtArr[i]=='j'|| txtArr[i]==';'
                      || txtArr[i]==':'|| txtArr[i]=='\''|| txtArr[i]==','|| txtArr[i]=='.')
                   charLen[i]=4;
                else if(txtArr[i]=='i'|| txtArr[i]=='I' || txtArr[i]=='l' || txtArr[i]=='|')
                   charLen[i]=3;
                else if( txtArr[i]=='f' || txtArr[i]=='r'|| txtArr[i]=='t' )
                   charLen[i]=5;
                else if(txtArr[i]=='m'|| txtArr[i]=='%')
                   charLen[i]=10;
                else if(txtArr[i]=='@'|| txtArr[i]=='W')
                   charLen[i]=12;
                else if(txtArr[i]=='G'|| txtArr[i]=='M'|| txtArr[i]=='O'|| txtArr[i]=='Q')
                   charLen[i]=9;
                else if(txtArr[i]=='_'||txtArr[i]=='w')
                   charLen[i]=8;
                else if(lenArr[i]==1 ||txtArr[i]>='0' && txtArr[i]<='9')
                   charLen[i]=7;
                else//Korean,Chinese character
                   charLen[i]=12;
            }
            
            int tmp=0;
            for(int i=0; i<Length; i++) {
               if(txtArr[i]=='\n') {
                  cnt++;
               }
               tmp+=charLen[i];
               if(tmp>156) {
                  t.insert(i,'\n');
                  txt=t.toString();
                  tmp=0;
                  cnt++;
               }
            }
            
             int all=0,count=0;
             for(int i=0; i<Length; i++) {
                all+=charLen[i];
                if(charLen[i]!=12)
                   count+=1;
             }
             if(all < 156) { 
                widthX = all; 
                startX = 350-(widthX);
                if(all<5)
                   widthX+=3;
            }
             heightY = 18+(cnt*17)+cnt;
             
             JTextArea speechText3 = new JTextArea(txt);
             speechText3.setBounds(startX, startY, widthX, heightY);
             speechText3.setBackground(new Color(255, 235, 51)); 
             speechText3.setEditable(false);
             screen.add(speechText3);
             
            txtField.setText("");

            startY = startY + heightY + 10;
            screenHeight+=heightY+10;
            screen.setPreferredSize(new Dimension(370,screenHeight));
            
           }
         else if(b.getIcon().equals(Imgclip)) {   //if imgclip(button to send file) is clicked
            int returnValue = jfc.showOpenDialog(null);
            File selectedFile = jfc.getSelectedFile();
            
            //file panel
            JPanel sendFile = new JPanel(null); 
            sendFile.setBounds(200, startY, 150, 70);
            sendFile.setBackground(Color.WHITE);
            
            String txt = selectedFile.getName();
            StringBuffer t = new StringBuffer(txt);
            

            char[] txtArr=txt.toCharArray();
            int Length=txt.length();
            int[] lenArr=new int[Length];
            int[] charLen=new int[Length];
            
            for(int i=0; i<Length; i++) {//set file ballon's size
               int code=(int)txtArr[i];
               // 3bytes //12
                if ((txtArr[i] < '0' || txtArr[i] > '9') && (txtArr[i] < 'A' || txtArr[i] > 'Z') 
                   && (txtArr[i] < 'a' || txtArr[i] > 'z') &&code > 255) lenArr[i]= 3;
                // 1bytes //8
                else lenArr[i]=1;
                
                if(txtArr[i]=='`' || txtArr[i]=='!' || txtArr[i]=='^' || txtArr[i]=='*' || txtArr[i]=='(' || txtArr[i]==')' || txtArr[i]=='-'|| txtArr[i]=='/'|| txtArr[i]=='['|| txtArr[i]=='{'|| txtArr[i]==']'|| txtArr[i]=='}'
                   || txtArr[i]=='\\'|| txtArr[i]=='"') 
                   charLen[i]=6;
                else if(txtArr[i]=='i'|| txtArr[i]=='I' || txtArr[i]=='l' || txtArr[i]=='j'|| txtArr[i]=='|'|| txtArr[i]==';'
                      || txtArr[i]==':'|| txtArr[i]=='\''|| txtArr[i]==','|| txtArr[i]=='.')
                   charLen[i]=4;
                else if( txtArr[i]=='f' || txtArr[i]=='r'|| txtArr[i]=='t' )
                   charLen[i]=5;
                else if(txtArr[i]=='m'|| txtArr[i]=='%')
                   charLen[i]=10;
                else if(txtArr[i]=='@'|| txtArr[i]=='W')
                   charLen[i]=12;
                else if(txtArr[i]=='G'|| txtArr[i]=='M'|| txtArr[i]=='O'|| txtArr[i]=='Q')
                   charLen[i]=9;
                else if(txtArr[i]=='_'||txtArr[i]=='w')
                   charLen[i]=8;
                else if(lenArr[i]==1 ||txtArr[i]>='0' && txtArr[i]<='9')
                   charLen[i]=7;
                else
                   charLen[i]=12;
            }
            
            int cnt = 0;
            int tmp=0;
            for (int i = 0; i < txt.length(); i++) { //line change
               tmp+=charLen[i];
               if(tmp> 107) { 
                  t.insert(i, '\n'); 
                  cnt++; 
                  break;
               }
             }
            int h = 18+(cnt*17);
            txt = t.toString();
            
            if(returnValue != JFileChooser.APPROVE_OPTION) {
                   return;
              }
            
               //file panel 1) file Name
               JTextArea fileName = new JTextArea(txt);
               fileName.setFont(new Font("safari", Font.BOLD, 11));
               fileName.setBounds(7, 7, 107, h);
               fileName.setEditable(false);
               sendFile.add(fileName);
               //file panel 2) file loaction
               JTextArea fileLocation = new JTextArea("file lacation : \n" + selectedFile.getAbsolutePath());
               fileLocation.setFont(new Font("safari", Font.PLAIN, 6));
               fileLocation.setForeground(Color.gray);
               fileLocation.setBounds(7, 45, 130, 18);
               fileLocation.setEditable(false);
               sendFile.add(fileLocation);
               //file panel 3) file download
               ImageIcon icon3 = new ImageIcon("src/image/down.png");
                Image img3 = icon3.getImage();
                Image changeImg3 = img3.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                changeIcon3 = new ImageIcon(changeImg3);
                JButton downImg = new JButton(changeIcon3);
                downImg.setBounds(115,10,30,30);
                downImg.setBorderPainted(false); //button Border
                downImg.setContentAreaFilled(false); //button Background
                downImg.setFocusPainted(false); //image border in button
                downImg.addActionListener(bl);
                sendFile.add(downImg);
            screen.add(sendFile);
            
            startY = startY + 70 + 10;
            screenHeight+=80;
            screen.setPreferredSize(new Dimension(370,screenHeight));
           }
         else if(b.getIcon().equals(chatReload)) {  //if button to get history of chatting is clicked
             screen.removeAll(); 
             startX = 200; startY = 10; widthX = 150; heightY = 0;
            screenHeight = 30;
            
            String myChat = id; //myid
            String yourChat = chatid; //(2) oppid
            
            String[] ans=o.readChat(myChat, yourChat);
            int numberOfchat = ans.length; //DB how many chat logs in tableCHatId
            
            for (int j=0; j<numberOfchat; j++) {
            	String[] a=ans[j].split(",");
            	String whoIsChatting = a[0]; //DB chatting id
            	String txt = a[1]; //DB get txt(or fileName)
                boolean isFile=true;//DB get file or not
                if(a[2].equals(null)||a[2].equals("null"))
                	isFile=false;
                
               if (whoIsChatting.equals(myChat)) { //if chatting is my chatting
                  
                  
                  if(!isFile) {//if txt
                     if(txt.equals("")||txt.equals(null))
                        return;
                     StringBuffer t = new StringBuffer(txt);
                     
                     int cnt = 0; //count the speechBubble length
                     startX = 200; widthX = 150; //set the speechBubble x, width
                     
                     char[] txtArr=txt.toCharArray();
                     int Length=txt.length();
                     int[] lenArr=new int[Length];
                     int[] charLen=new int[Length];
                     
                     for(int i=0; i<Length; i++) { 
                        int code=(int)txtArr[i];
                        // 3bytes //12
                         if ((txtArr[i] < '0' || txtArr[i] > '9') && (txtArr[i] < 'A' || txtArr[i] > 'Z') 
                            && (txtArr[i] < 'a' || txtArr[i] > 'z') &&code > 255) lenArr[i]= 3;
                         // 1bytes //8
                         else lenArr[i]=1;
                         
                         if(txtArr[i]=='`' || txtArr[i]=='!' || txtArr[i]=='^' || txtArr[i]=='*' || txtArr[i]=='(' || txtArr[i]==')' || txtArr[i]=='-'|| txtArr[i]=='/'|| txtArr[i]=='['|| txtArr[i]=='{'|| txtArr[i]==']'|| txtArr[i]=='}'
                            || txtArr[i]=='\\'|| txtArr[i]=='"') 
                            charLen[i]=6;
                         else if(txtArr[i]=='j'|| txtArr[i]==';'
                               || txtArr[i]==':'|| txtArr[i]=='\''|| txtArr[i]==','|| txtArr[i]=='.')
                            charLen[i]=4;
                         else if(txtArr[i]=='i'|| txtArr[i]=='I' || txtArr[i]=='l' || txtArr[i]=='|')
                            charLen[i]=3;
                         else if( txtArr[i]=='f' || txtArr[i]=='r'|| txtArr[i]=='t' )
                            charLen[i]=5;
                         else if(txtArr[i]=='m'|| txtArr[i]=='%')
                            charLen[i]=10;
                         else if(txtArr[i]=='@'|| txtArr[i]=='W')
                            charLen[i]=12;
                         else if(txtArr[i]=='G'|| txtArr[i]=='M'|| txtArr[i]=='O'|| txtArr[i]=='Q')
                            charLen[i]=9;
                         else if(txtArr[i]=='_'||txtArr[i]=='w')
                            charLen[i]=8;
                         else if(lenArr[i]==1 ||txtArr[i]>='0' && txtArr[i]<='9')
                            charLen[i]=7;
                         else
                            charLen[i]=12;
                     }
                     
                     int tmp=0;
                     for(int i=0; i<Length; i++) {
                        if(txtArr[i]=='\n') {
                           cnt++;
                        }
                        tmp+=charLen[i];
                        if(tmp>156) {
                           t.insert(i,'\n');
                           txt=t.toString();
                           tmp=0;
                           cnt++;
                        }
                     }
                     
                      int all=0,count=0;
                      for(int i=0; i<Length; i++) {
                         all+=charLen[i];
                         if(charLen[i]!=12)
                            count+=1;
                      }
                      if(all < 156) { 
                         widthX = all; 
                         startX = 350-(widthX);
                         if(all<5)
                            widthX+=3;
                     }
                      heightY = 18+(cnt*17)+cnt;
                      
                     JTextArea speechText3 = new JTextArea(txt);
                     speechText3.setBounds(startX, startY, widthX, heightY);
                     speechText3.setBackground(new Color(255, 235, 51));
                     speechText3.setEditable(false);
                     screen.add(speechText3);

                     startY = startY + heightY + 10;
                     screenHeight+=heightY+10;
                     screen.setPreferredSize(new Dimension(370,screenHeight));
                     
                  }else {//if file
                	   
                      //file panel
                      JPanel sendFile = new JPanel(null); 
                      sendFile.setBounds(200, startY, 150, 70);
                      sendFile.setBackground(Color.WHITE);
                      
                     
                      StringBuffer t = new StringBuffer(txt);
                      

                      char[] txtArr=txt.toCharArray();
                      int Length=txt.length();
                      int[] lenArr=new int[Length];
                      int[] charLen=new int[Length];
                      
                      for(int i=0; i<Length; i++) { 
                         int code=(int)txtArr[i];
                         // 3bytes //12
                          if ((txtArr[i] < '0' || txtArr[i] > '9') && (txtArr[i] < 'A' || txtArr[i] > 'Z') 
                             && (txtArr[i] < 'a' || txtArr[i] > 'z') &&code > 255) lenArr[i]= 3;
                          // 1bytes //8
                          else lenArr[i]=1;
                          
                          if(txtArr[i]=='`' || txtArr[i]=='!' || txtArr[i]=='^' || txtArr[i]=='*' || txtArr[i]=='(' || txtArr[i]==')' || txtArr[i]=='-'|| txtArr[i]=='/'|| txtArr[i]=='['|| txtArr[i]=='{'|| txtArr[i]==']'|| txtArr[i]=='}'
                             || txtArr[i]=='\\'|| txtArr[i]=='"') 
                             charLen[i]=6;
                          else if(txtArr[i]=='i'|| txtArr[i]=='I' || txtArr[i]=='l' || txtArr[i]=='j'|| txtArr[i]=='|'|| txtArr[i]==';'
                                || txtArr[i]==':'|| txtArr[i]=='\''|| txtArr[i]==','|| txtArr[i]=='.')
                             charLen[i]=4;
                          else if( txtArr[i]=='f' || txtArr[i]=='r'|| txtArr[i]=='t' )
                             charLen[i]=5;
                          else if(txtArr[i]=='m'|| txtArr[i]=='%')
                             charLen[i]=10;
                          else if(txtArr[i]=='@'|| txtArr[i]=='W')
                             charLen[i]=12;
                          else if(txtArr[i]=='G'|| txtArr[i]=='M'|| txtArr[i]=='O'|| txtArr[i]=='Q')
                             charLen[i]=9;
                          else if(txtArr[i]=='_'||txtArr[i]=='w')
                             charLen[i]=8;
                          else if(lenArr[i]==1 ||txtArr[i]>='0' && txtArr[i]<='9')
                             charLen[i]=7;
                          else
                             charLen[i]=12;
                      }
                      
                      int cnt = 0;
                      int tmp=0;
                      for (int i = 0; i < txt.length(); i++) { //line change
                         tmp+=charLen[i];
                         if(tmp> 107) { 
                            t.insert(i, '\n'); 
                            cnt++; 
                            break;
                         }
                       }
                      int h = 18+(cnt*17);
                      txt = t.toString();
                      
                         //file panel 1) file Name
                         JTextArea fileName = new JTextArea(txt);
                         fileName.setFont(new Font("safari", Font.BOLD, 11));
                         fileName.setBounds(7, 7, 107, h);
                         fileName.setEditable(false);
                         sendFile.add(fileName);
                         //file panel 2) file loaction
                         JTextArea fileLocation = new JTextArea("file lacation : \n" /*+ selectedFile.getAbsolutePath()*/);
                         fileLocation.setFont(new Font("safari", Font.PLAIN, 6));
                         fileLocation.setForeground(Color.gray);
                         fileLocation.setBounds(7, 45, 130, 18);
                         fileLocation.setEditable(false);
                         sendFile.add(fileLocation);
                         //file panel 3) file download
                         ImageIcon icon3 = new ImageIcon("src/image/down.png");
                          Image img3 = icon3.getImage();
                          Image changeImg3 = img3.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                          changeIcon4 = new ImageIcon(changeImg3);
                          JButton downImg = new JButton(changeIcon4);
                          downImg.setBounds(115,10,30,30);
                          downImg.setBorderPainted(false); //button Border
                          downImg.setContentAreaFilled(false); //button Background
                          downImg.setFocusPainted(false); //image border in button  
                          downImg.addActionListener(bl);
                          sendFile.add(downImg);
                      screen.add(sendFile);
                      
                      startY = startY + 70 + 10;
                      screenHeight+=80;
                      screen.setPreferredSize(new Dimension(370,screenHeight));
                      
                  }
               }else if (whoIsChatting.equals(yourChat)) { //if chatting is opponent's chatting
                  
                  if(!isFile) {
	                  if(txt.equals("")||txt.equals(null))
	                         return;
	                      StringBuffer txtBuffer = new StringBuffer(txt);
	                      
	                      int cnt = 0; //count the speechBubble length
	                      startX = 200; widthX = 150; //set the speechBubble x, width
	                      
	                      char[] txtArr=txt.toCharArray();
	                      int Length=txt.length();
	                      int[] lenArr=new int[Length];
	                      int[] charLen=new int[Length];
	                      
	                      for(int i=0; i<Length; i++) { 
	                         int code=(int)txtArr[i];
	                         // 3bytes //12
	                          if ((txtArr[i] < '0' || txtArr[i] > '9') && (txtArr[i] < 'A' || txtArr[i] > 'Z') 
	                             && (txtArr[i] < 'a' || txtArr[i] > 'z') &&code > 255) lenArr[i]= 3;
	                          // 1bytes //8
	                          else lenArr[i]=1;
	                          
	                          if(txtArr[i]=='`' || txtArr[i]=='!' || txtArr[i]=='^' || txtArr[i]=='*' || txtArr[i]=='(' || txtArr[i]==')' || txtArr[i]=='-'|| txtArr[i]=='/'|| txtArr[i]=='['|| txtArr[i]=='{'|| txtArr[i]==']'|| txtArr[i]=='}'
	                             || txtArr[i]=='\\'|| txtArr[i]=='"') 
	                             charLen[i]=6;
	                          else if(txtArr[i]=='j'|| txtArr[i]==';'
	                                || txtArr[i]==':'|| txtArr[i]=='\''|| txtArr[i]==','|| txtArr[i]=='.')
	                             charLen[i]=4;
	                          else if(txtArr[i]=='i'|| txtArr[i]=='I' || txtArr[i]=='l' || txtArr[i]=='|')
	                             charLen[i]=3;
	                          else if( txtArr[i]=='f' || txtArr[i]=='r'|| txtArr[i]=='t' )
	                             charLen[i]=5;
	                          else if(txtArr[i]=='m'|| txtArr[i]=='%')
	                             charLen[i]=10;
	                          else if(txtArr[i]=='@'|| txtArr[i]=='W')
	                             charLen[i]=12;
	                          else if(txtArr[i]=='G'|| txtArr[i]=='M'|| txtArr[i]=='O'|| txtArr[i]=='Q')
	                             charLen[i]=9;
	                          else if(txtArr[i]=='_'||txtArr[i]=='w')
	                             charLen[i]=8;
	                          else if(lenArr[i]==1 ||txtArr[i]>='0' && txtArr[i]<='9')
	                             charLen[i]=7;
	                          else
	                             charLen[i]=12;
	                      }
	                      
	                      int tmp=0;
	                      for(int i=0; i<Length; i++) {
	                         if(txtArr[i]=='\n') {
	                            cnt++;
	                         }
	                         tmp+=charLen[i];
	                         if(tmp>156) {
	                           txtBuffer.insert(i,'\n');
	                            txt=txtBuffer.toString();
	                            tmp=0;
	                            cnt++;
	                         }
	                      }
	                      
	                       int all=0,count=0;
	                       for(int i=0; i<Length; i++) {
	                          all+=charLen[i];
	                          if(charLen[i]!=12)
	                             count+=1;
	                       }
	                       if(all < 156) { 
	                          widthX = all; 
	                          startX = 390-(widthX)-40;
	                          if(all<5)
	                             widthX+=3;
	                      }
	                       heightY = 18+(cnt*17)+cnt;
	                  
	                  JTextArea speechText4 = new JTextArea(txt);
	                  speechText4.setBounds(7, startY, widthX, heightY);
	                  speechText4.setBackground(Color.WHITE);
	                  speechText4.setEditable(false);
	                  screen.add(speechText4);
	                  
	                  startY = startY + heightY + 10;
	                  screenHeight = screenHeight + heightY + 10;
	                  screen.setPreferredSize(new Dimension(370, screenHeight)); 
	                  
                  }else {//if file
                      
                      //file panel
                      JPanel sendFile = new JPanel(null); 
                      sendFile.setBounds(7, startY, 150, 70);
                      sendFile.setBackground(Color.WHITE);
                      
                     
                      StringBuffer t = new StringBuffer(txt);
                      

                      char[] txtArr=txt.toCharArray();
                      int Length=txt.length();
                      int[] lenArr=new int[Length];
                      int[] charLen=new int[Length];
                      
                      for(int i=0; i<Length; i++) {
                         int code=(int)txtArr[i];
                         // 3bytes //12
                          if ((txtArr[i] < '0' || txtArr[i] > '9') && (txtArr[i] < 'A' || txtArr[i] > 'Z') 
                             && (txtArr[i] < 'a' || txtArr[i] > 'z') &&code > 255) lenArr[i]= 3;
                          // 1bytes //8
                          else lenArr[i]=1;
                          
                          if(txtArr[i]=='`' || txtArr[i]=='!' || txtArr[i]=='^' || txtArr[i]=='*' || txtArr[i]=='(' || txtArr[i]==')' || txtArr[i]=='-'|| txtArr[i]=='/'|| txtArr[i]=='['|| txtArr[i]=='{'|| txtArr[i]==']'|| txtArr[i]=='}'
                             || txtArr[i]=='\\'|| txtArr[i]=='"') 
                             charLen[i]=6;
                          else if(txtArr[i]=='i'|| txtArr[i]=='I' || txtArr[i]=='l' || txtArr[i]=='j'|| txtArr[i]=='|'|| txtArr[i]==';'
                                || txtArr[i]==':'|| txtArr[i]=='\''|| txtArr[i]==','|| txtArr[i]=='.')
                             charLen[i]=4;
                          else if( txtArr[i]=='f' || txtArr[i]=='r'|| txtArr[i]=='t' )
                             charLen[i]=5;
                          else if(txtArr[i]=='m'|| txtArr[i]=='%')
                             charLen[i]=10;
                          else if(txtArr[i]=='@'|| txtArr[i]=='W')
                             charLen[i]=12;
                          else if(txtArr[i]=='G'|| txtArr[i]=='M'|| txtArr[i]=='O'|| txtArr[i]=='Q')
                             charLen[i]=9;
                          else if(txtArr[i]=='_'||txtArr[i]=='w')
                             charLen[i]=8;
                          else if(lenArr[i]==1 ||txtArr[i]>='0' && txtArr[i]<='9')
                             charLen[i]=7;
                          else
                             charLen[i]=12;
                      }
                      
                      int cnt = 0;
                      int tmp=0;
                      for (int i = 0; i < txt.length(); i++) { //line change
                         tmp+=charLen[i];
                         if(tmp> 107) { 
                            t.insert(i, '\n'); 
                            cnt++; 
                            break;
                         }
                       }
                      int h = 18+(cnt*17);
                      txt = t.toString();
                      
                         //file panel 1) file Name
                         JTextArea fileName = new JTextArea(txt);
                         fileName.setFont(new Font("safari", Font.BOLD, 11));
                         fileName.setBounds(7, 7, 107, h);
                         fileName.setEditable(false);
                         sendFile.add(fileName);
                         //file panel 2) file loaction
                         JTextArea fileLocation = new JTextArea("file lacation : \n" /*+ selectedFile.getAbsolutePath()*/);
                         fileLocation.setFont(new Font("safari", Font.PLAIN, 6));
                         fileLocation.setForeground(Color.gray);
                         fileLocation.setBounds(7, 45, 130, 18);
                         fileLocation.setEditable(false);
                         sendFile.add(fileLocation);
                         //file panel 3) file download
                         ImageIcon icon3 = new ImageIcon("src/image/down.png");
                          Image img3 = icon3.getImage();
                          Image changeImg3 = img3.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                          changeIcon5 = new ImageIcon(changeImg3);
                          JButton downImg = new JButton(changeIcon5);
                          downImg.setBounds(115,10,30,30);
                          downImg.setBorderPainted(false); //button Border
                          downImg.setContentAreaFilled(false); //button Background
                          downImg.setFocusPainted(false); //image border in button ]
                          downImg.addActionListener(bl);
                          sendFile.add(downImg);
                      screen.add(sendFile);
                      
                      startY = startY + 70 + 10;
                      screenHeight+=80;
                      screen.setPreferredSize(new Dimension(370,screenHeight));
                  }
               }
               
            }
            
            screen.setVisible(true);
        }
         
         
         
      }
      
   }
  
}

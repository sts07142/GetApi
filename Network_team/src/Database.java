

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;


public class Database {
   Connection con = null;
   Statement stmt = null;
 //Server maintains continuous connection to DB
   String url = "jdbc:mysql://localhost/net";   
   String user = "root";
   String passwd = "cbxd2525";
   

 //Start connection to DB in generator part
   Database() {   
      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         con = DriverManager.getConnection(url, user, passwd);
         stmt = con.createStatement();
         System.out.println("MySQL suceess");
      } catch(Exception e) {
         System.out.println("MySQL fail > " + e.toString());
      }
   }
   
   String plus_f(String user, String other) {
      boolean flag = true;
      //Get 2 user_id from server and register as friends
      String sql = "insert into FOLLOW values (?,?)";
      try {
         

         PreparedStatement pstmt = con.prepareStatement(sql);

         pstmt.setString(1, other);
         pstmt.setString(2, user);


         int r = pstmt.executeUpdate();
      } catch (SQLException e1) {
         System.out.println("SQL error" + e1.getMessage());
         return "false";
      }
      return "true";
   }
   
   
   boolean logincheck(String _i, String _p) {
      boolean flag = false;
      
      String id = _i;
      String pw = decoding.decode(_p);
      //The above calculate.decode converts the password through the RSA algorithm: for security
      try {
         String checkingStr = "SELECT password FROM user WHERE user_id=\'" + id + "\'";
         ResultSet result = stmt.executeQuery(checkingStr);
         
         int count = 0;
         while(result.next()) {
            if(pw.equals(result.getString("password"))) {
               flag = true;
               System.out.println("Login Successful");
            }
            
            else {
               flag = false;
               System.out.println("Login failed");
            }
            count++;
         }
         
         
      } catch(Exception e) {
         flag = false;
         System.out.println("Login failed > " + e.toString());
      }
      second_UPstate(id);
      return flag;
   }
   
   boolean check_id(String _i) {
      boolean flag=true;
      String id = _i;
      try {
         String checkingStr = "SELECT user_id FROM USER WHERE user_id=\'" + id + "\'";
         ResultSet result = stmt.executeQuery(checkingStr);
         
         while(result.next()) {
            String s= result.getString(1);
            if (result.equals(id)) return false;
         }
      } catch(Exception e) {
      }
      return flag;
   }
   //Bring the image registered by the user.
   String get_profile(String _i) {
      String user_id = _i;
      String s=new String();
      try {
         String checkingStr = "select profile from state where user_id = \'" + user_id + "\'";
         ResultSet result = stmt.executeQuery(checkingStr);
         while(result.next()) {
            s= result.getString(1);
            //If the value does not exist, initialize the string to 1 to notify you that there is no image
            if (result.wasNull()) s = "1";
         }
      } catch(Exception e) {
      }
      return s;
   }
   
   
   //Store personal information in a database for membership
   void signUP(String _i, String _p,String _n, String nick, String _y,String email, String saying ,String phoneNumber, String git) {
      boolean flag = false;
      String id = _i;
      String pw = _p;
      String name = _n;
      String year = _y;
      String sql = "insert into USER values (?,?,?,?,?,?,?,?)";
      try {
         

         PreparedStatement pstmt = con.prepareStatement(sql);

         pstmt.setString(1, id);
         pstmt.setString(2, pw);
         pstmt.setString(3, name);
         pstmt.setString(4, nick);
         pstmt.setString(5, email);
         pstmt.setString(6, year);
         pstmt.setString(7, phoneNumber);
         pstmt.setString(8, git);

         int r = pstmt.executeUpdate();
      } catch (SQLException e1) {
         System.out.println("SQL error" + e1.getMessage());
      }
      
      first_UPstate(id, saying);
   }
   void first_UPstate(String _i, String saying) {
	   //Immediately after signing up, save your status information
      String id = _i;
      
      String sql = "insert into STATE values (?,?,?,?,?)";
      try {
         Date date = new Date();
         
         SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         
         PreparedStatement pstmt = con.prepareStatement(sql);
         System.out.println(df.format(date));
         pstmt.setString(1, id); // user_id
         pstmt.setString(2, saying);
         pstmt.setString(3, null);//profile
         pstmt.setString(4, df.format(date));//Last connection time
         pstmt.setInt(5, 0);//Login Status
         
         int r = pstmt.executeUpdate();
         
      } catch (SQLException e1) {
         System.out.println("SQL error" + e1.getMessage());
      
      }
   }
   void second_UPstate(String _i) {
      String id = _i;
      int count=0;
      Date date = new Date();
      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      try {
         stmt = con.createStatement();
         String update = "update STATE set access_time =\'"+df.format(date)+"\', login="+1+" where user_id=\'"+id+"\'";
         count = stmt.executeUpdate(update);
      } catch(Exception e) {
      }
      //Update status information upon login
   }
   void end_UPstate(String _i) {
	   //Update user status information at the end of the program
      String id = _i;
      int count=0;
      Date date = new Date();
      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      try {
         stmt = con.createStatement();
         //login information is exchaged to "0"
         String update = "update STATE set access_time =\'"+df.format(date)+"\', login="+0+" where user_id=\'"+id+"\'";
         count = stmt.executeUpdate(update);
      } catch(Exception e) {
      }
   }
   
   String[] find_friend(String other,String user_id) {
      String[] p=new String[30]; 
      int count = 1;
      //Search for users in the database through other strings, 
      //outputting users with other strings while not making friends with them
      try {
         String checkingStr = "SELECT A.user_id FROM USER as A where A.user_id LIKE \'%"+other+"%\' and A.user_id !=\'"
               +user_id+"\' and A.user_id not in(select f_id from FOLLOW WHERE user_id=\'"+user_id+"\')";
         ResultSet result = stmt.executeQuery(checkingStr);
         while(result.next()) {
            p[count] = result.getString(1);
            if (result.wasNull()) p[count] = null;
            count++;
         }
      } catch(Exception e) {
         
      }
      //Put the number of data into the 0th of the array.
      p[0]=Integer.toString(count);
      return p;
   }
   String[] get_information(String user_id) {
      String[] p=new String[3]; 
      int count = 0;
      try {
         String checkingStr = "SELECT nick_name, saying FROM USER natural join STATE where user_id = \'"+user_id+"\'";
         ResultSet result = stmt.executeQuery(checkingStr);
         
         while(result.next()) {
            p[count] = result.getString(1);
            if (result.wasNull()) p[count] = null;
            count++;
            p[count] = result.getString(2);
            if (result.wasNull()) p[count] = null;
            count++;
         }
      } catch(Exception e) {
         
      }
      //Put the number of data into the 0th of the array.
      return p;
   }

   String[] get_ChatInformation(String chat_id, String user_id) {
      String[] p=new String[30]; 
      int count = 1;
      try {
         String checkingStr ="SELECT nick_name FROM USER natural join PARTICIPATE where chat_id = \'"+chat_id+"\' and user_id <> \'"+user_id+"\'";
         ResultSet result = stmt.executeQuery(checkingStr);
        
         while(result.next()) {
            p[count] = result.getString(1);
            if (result.wasNull()) p[count] = null;
            count++;
         }
      } catch(Exception e) {
         
      }
      p[0]=Integer.toString(count);
      return p;
   }
   String get_state(String user_id) {
      String p=new String(); 
      try {
         String checkingStr = "SELECT login FROM STATE where user_id = \'"+user_id+"\'";
         ResultSet result = stmt.executeQuery(checkingStr);
         while(result.next()) {
            p= result.getString(1);
            if (result.wasNull()) p = "0";
         }
      } catch(Exception e) {
         
      }
      //Verify that the user is connected based on the user's ID entered
      return p;
   }
   void deleting(String user_id, String other) {
       try {
          stmt = con.createStatement();
            String sql="delete from FOLLOW where user_id=\'"+user_id+"\' and f_id=\'"+other+"\';";
            int count = stmt.executeUpdate(sql);
       }catch(Exception e) {
            
         }
       // Delete friends you want to delete from the database using your ID
   }
   
   String[] profile(String user_id) {
      String[] p=new String[10]; 
      int count = 0;
      //Gets the information to use in chatFrame
      //nick_name, name, saying, phone_number, birth, user_id, email, git, access_time, login
      try {
         String checkingStr = "SELECT nick_name, name, saying, phone_number, birth, user_id, email, git, access_time, login  FROM USER natural join STATE where user_id = \'"+user_id+"\'";
         ResultSet result = stmt.executeQuery(checkingStr);
         
         
         while(result.next()) {
            p[count] = result.getString(1);
            if (result.wasNull()) p[count] = null;
            count++;
            p[count] = result.getString(2);
            if (result.wasNull()) p[count] = null;
            count++;
            p[count] = result.getString(3);
            if (result.wasNull()) p[count] = null;
            count++;
            p[count] = result.getString(4);
            if (result.wasNull()) p[count] = null;
            count++;
            p[count] = result.getString(5);
            if (result.wasNull()) p[count] = null;
            count++;
            p[count] = result.getString(6);
            if (result.wasNull()) p[count] = null;
            count++;
            p[count] = result.getString(7);
            if (result.wasNull()) p[count] = null;
            count++;
            p[count] = result.getString(8);
            if (result.wasNull()) p[count] = null;
            count++;
            p[count] = result.getString(9);
            if (result.wasNull()) p[count] = null;
            count++;
            p[count] = result.getString(10);
            if (result.wasNull()) p[count] = null;
            count++;
         }
      } catch(Exception e) {
         
      }
    
      return p;
   }
   
   String[] find_follow(String u_id) {
      String[] p=new String[30]; 
      int count = 1;
      try {
         String checkingStr = "SELECT f_id FROM FOLLOW where user_id = \'"+u_id+"\'";
         ResultSet result = stmt.executeQuery(checkingStr);
         while(result.next()) {
            p[count] = result.getString(1);
            if (result.wasNull()) p[count] = null;
            count++;

         }
      } catch(Exception e) {
         
      }
      p[0]=Integer.toString(count);
      return p;
   }
   
   String find_id(String name, String email) {
	   //If the user forgets the id, it is used to find it. At this time, 
	   //the user's name and email information are received
      String s=new String();
      try {
         String checkingStr = "select user_id from USER where name = \'" + name + "\' and email=\'"+email+"\'";
         ResultSet result = stmt.executeQuery(checkingStr);
         while(result.next()) {
            s= result.getString(1);
            if (result.wasNull()) s = "1";
         }
      } catch(Exception e) {
      }
      return s;
   }
   
   String[] find_chat(String user_id) {
      String[] p=new String[30]; 
      int count = 1;
      //Get the ID of the chat room and information about permission.
      try {
         String checkingStr = "SELECT chat_id, checking FROM PARTICIPATE where user_id = \'"+user_id+"\'";
         ResultSet result = stmt.executeQuery(checkingStr);

         while(result.next()) {
            p[count] = result.getString(1);
            if (result.wasNull()) p[count] = null;
            count++;
            p[count] = result.getString(2);
            if (result.wasNull()) p[count] = null;
            count++;
            
         }
      } catch(Exception e) {
         
      }

      p[0]=Integer.toString(count);
      return p;
   }
   String get_lastchat(String chat_id) {
      String s=new String();
      try {
         String checkingStr = "select chatting, file from CHAT where chat_id = \'" + chat_id + "\' order by up_load DESC";
         ResultSet result = stmt.executeQuery(checkingStr);
         while(result.next()) {
            s= result.getString(1);
            if (result.wasNull()) s = null;
            
            FileInputStream fis;
            fis=(FileInputStream) result.getBinaryStream(2);
            if(!fis.equals(null)) {
               return "file : "+s;
            }
               
            
         }
      } catch(Exception e) {
      }
      return s;
   }
   
   void updateInfo(String user_id,String what,String update) throws SQLException {
         stmt = con.createStatement();
         String sql = "";
         switch (what) {
            case "0": //pw
               sql="update USER set password = \'"+update+"\'where user_id = \'"+user_id+"\'";
               break;
            case "1": //nick
               sql="update USER set nick_name = \'"+update+"\'where user_id = \'"+user_id+"\'";
               break;
            case "2": //saying
               sql="update STATE set saying = \'"+update+"\'where user_id = \'"+user_id+"\'";
               break;
            case "3": //withdraw
               sql="delete from USER where user_id = \'"+user_id+"\'";
               break;
         }
         stmt.executeUpdate(sql);
            
      }
   
   String[] getIdProfile(String id) throws SQLException {
       
         //user_id, password,name,nick_name,email,birth,phone_number,git,saying,profile,access_time,login
         stmt=con.createStatement();
         String sql="";
         String a[]=new String[10];
         
         sql="select * from user natural join state where user_id=\'"+id+"\';";
         ResultSet rs=stmt.executeQuery(sql);
         for(int i=0; i<10; i++) {
            if(i==1 || i==9)
               continue;
            else if(i==5)
               //birth
               a[i]=""+rs.getDate(i+1);
            else if(i==10)
               //access_time
               a[i]=""+rs.getTime(i+1);
            else if(i==11)
               //login
               a[i]=""+rs.getInt(i+1);
            else
               a[i]=rs.getString(i+1);
         }
         return a;
      }
   void chat_allow(String user_id, String chat_id, String allow) {
	   //Request message to opponent for chatting
      if(allow.equals("1")) {
    	  //1 means that the other person allowed it
         int count=0;
         try {
            stmt = con.createStatement();
            String update = "update PARTICIPATE set checking =\'"+"1"+"\' where chat_id=\'"+chat_id+"\' and user_id = \'"+user_id+"\';";
            count = stmt.executeUpdate(update);
         } catch(Exception e) {
         }
      }else if(allow.equals("0")) {
    	  //0 is when the other person requests permission for the chat
         int count=0;
         try {
            stmt = con.createStatement();
            String update = "update PARTICIPATE set checking =\'"+"-1"+"\' where chat_id=\'"+chat_id+"\' and user_id != \'"+user_id+"\';";
            count = stmt.executeUpdate(update);
            update = "update PARTICIPATE set checking =\'"+"1"+"\' where chat_id=\'"+chat_id+"\' and user_id = \'"+user_id+"\';";
            count = stmt.executeUpdate(update);
         } catch(Exception e) {
         }
      }else if(allow.equals("-1")) {
    	  //-1 mean that rejects the request for chat by the other party
          try {
             stmt = con.createStatement();
               String sql="delete from PARTICIPATE where chat_id=\'"+chat_id+"\'";
               int count = stmt.executeUpdate(sql);
          }catch(Exception e) {
               
            }
      }
      System.out.println(allow);
      
   }
   void updateFile(String user_id, String other, String route) throws FileNotFoundException {
      String s=new String();
      //Uploading files to the Database
      try {
    	  //Search for ID for chat
         String checkingStr = "select A.chat_id from PARTICIPATE as A join PARTICIPATE as B where A.user_id=\'"+user_id+"\' and B.user_id=\'"+other+"\' and A.chat_id=B.chat_id";
         ResultSet result = stmt.executeQuery(checkingStr);
         while(result.next()) {
            s= result.getString(1);
            if (result.wasNull()) s = null;
         }
      } catch(Exception e) {
      }
      //But if the chat room hasn't opened yet, it's a new one
      if(s.equals(null) ||s.equals("")) {
         int chat_count=0;
         try {
            String checkingStr = "select count(chat_id) from PARTICIPATE group by chat_id";
            ResultSet result = stmt.executeQuery(checkingStr);
            while(result.next()) {
               chat_count= result.getInt(1);
               if (result.wasNull()) chat_count = 0;
            }
         } catch(Exception e) {
         }
         
         // new one 
         String sql = "insert into PARTICIPATE values (?,?,?)";
         try {
            s=Integer.toString(chat_count+1);
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, user_id);
            pstmt.setString(2, s);
            pstmt.setString(3, "1");
            int r = pstmt.executeUpdate();
            
            
         } catch (SQLException e1) {
            System.out.println("SQL error" + e1.getMessage());
         }
         try {
            s=Integer.toString(chat_count+1);
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, other);
            pstmt.setString(2, s);
            pstmt.setString(3, "0");
            int r = pstmt.executeUpdate();
         } catch (SQLException e1) {
            System.out.println("SQL error" + e1.getMessage());
         }
      }
      
      
      //chatting : file_name, file:file 
      String sql = "insert into CHAT(chat_id, user_id, chatting, file, up_load) values (?,?,?,?,?)";
      try {
         Date date = new Date();
         //update the file
         SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

         PreparedStatement pstmt = con.prepareStatement(sql);
         System.out.println(df.format(date));
         FileInputStream fis = new FileInputStream(route);
         pstmt.setString(1, s);
         pstmt.setString(2, user_id);
         pstmt.setString(3, route);
         pstmt.setBinaryStream(4, fis);
         pstmt.setString(5, df.format(date));
         
         int r = pstmt.executeUpdate();
         
      } catch (SQLException e1) {
         System.out.println("SQL error" + e1.getMessage());
      
      }
      

   }
   

   void chat(String user_id,String chatid, String txt) throws SQLException {
      String s=new String();
      //Get the information in the chat
      try {
         String checkingStr = "select A.chat_id from PARTICIPATE as A join PARTICIPATE as B where A.user_id=\'"+user_id+"\' and B.user_id=\'"+chatid+"\' and A.chat_id=B.chat_id";
         ResultSet result = stmt.executeQuery(checkingStr);
         while(result.next()) {
            s= result.getString(1);
            if (result.wasNull()) s = null;
         }
      } catch(Exception e) {
      }
    //But if the chat room hasn't opened yet, it's a new one
      if(s.equals(null) ||s.equals("")) {
         int chat_count=0;
         try {
            String checkingStr = "select count(chat_id) from PARTICIPATE group by chat_id";
            ResultSet result = stmt.executeQuery(checkingStr);
            while(result.next()) {
               chat_count= result.getInt(1);
               if (result.wasNull()) chat_count = 0;
            }
         } catch(Exception e) {
         }
         
         
         String sql = "insert into PARTICIPATE values (?,?,?)";
         try {
            s=Integer.toString(chat_count+1);
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, user_id);
            pstmt.setString(2, s);
            pstmt.setString(3, "1");
            int r = pstmt.executeUpdate();
            
            
         } catch (SQLException e1) {
            System.out.println("SQL error" + e1.getMessage());
         }
         try {
            s=Integer.toString(chat_count+1);
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, chatid);
            pstmt.setString(2, s);
            pstmt.setString(3, "0");
            int r = pstmt.executeUpdate();
         } catch (SQLException e1) {
            System.out.println("SQL error" + e1.getMessage());
         }
      }
      
      System.out.println("database"+ s);
         Date date = new Date();
         SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         String sql="insert into chat values (?,?,?,?,?)";
         PreparedStatement pstmt = con.prepareStatement(sql);
         pstmt.setString(1, s);
         pstmt.setString(2, user_id);
         pstmt.setString(3, txt);
         pstmt.setBinaryStream(4, null);
         pstmt.setString(5, df.format(date));
          int r = pstmt.executeUpdate();
      }

      
   String[] readChat(String id1,String id2) throws SQLException {
	      
	     String checkingStr = "select A.chat_id from PARTICIPATE as A join PARTICIPATE as B where A.user_id=\'"+id1+"\' and B.user_id=\'"+id2+"\' and A.chat_id=B.chat_id";
	      ResultSet result = stmt.executeQuery(checkingStr);
	      String tableUId="";
	      while(result.next())
	         tableUId=result.getString(1);
	                 
	      System.out.println(tableUId);
	      
	       String sql2="select count(chat_id) from chat where chat_id=\'"+tableUId+"\' group by chat_id";
	       ResultSet rs2=stmt.executeQuery(sql2);
	       int numOfChat=0;
	       while(rs2.next())
	          numOfChat=(rs2.getInt(1));
	       
	       System.out.println(numOfChat);
	       
	       //whoisChat         getTxt         isFile         fName=getTxt
	       //chat_id user_id chatting file up_load
	        
	        
	       String[] ans=new String[numOfChat+1];
	       String sql3="select user_id, chatting, file from chat where chat_id=\'"+tableUId+"\' order by up_load asc";
	       ResultSet rs3=stmt.executeQuery(sql3);
	       
	       int cnt=1;
	       ans[0]=""+numOfChat;
	       
	       while(rs3.next()) {
	          ans[cnt]=rs3.getString(1)+","+rs3.getString(2)+","+rs3.getString(3);
	          System.out.println(ans[cnt]);
	          cnt++;
	       }
	       
	       
	       return ans;
	    }

}




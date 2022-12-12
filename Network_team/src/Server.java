import java.util.*;
import java.io.*;
import java.util.concurrent.*;



import java.net.*;
public class Server {
   static InputStream inFile = null;                       
   static FileOutputStream outFile = null; 
   static DataInputStream din=null;
   public static void main(String[] args)throws Exception {

      ServerSocket listenner=new ServerSocket(7777);
      System.out.println("the capitalization server is running.....");
     
      ExecutorService pool=Executors.newFixedThreadPool(20);
      
      while(true) {
         Socket sock=listenner.accept();
          inFile = sock.getInputStream();  
          din = new DataInputStream(inFile);
        //request client access, accept server
        //Associate one thread with the client
         pool.execute(new Capitalizer(sock));
      }
   }
   static Database db = new Database();
   private static class Capitalizer implements Runnable{
      
      private Socket socket;
      Capitalizer(Socket socket){
         this.socket=socket;
      }
      @Override
      public void run() {
         System.out.println("Connected: "+socket);
         try {
            var in=new Scanner(socket.getInputStream());
            // client send data to serve
            var out=new PrintWriter(socket.getOutputStream(), true);
            //Server Send data to client
            while(in.hasNextLine()) {
               //Return a non-message field value from the requirement
               try {
               String request=in.nextLine();
               if(request.equals("login")) {
                  //
                  String id=in.nextLine();
                  String pass=in.nextLine();
                  System.out.println("Sid : "+id+"\nSpass : "+pass);
                  boolean check=db.logincheck(id, pass);
                  out.println(check);
                  //login check
               }else if(request.equals("find_friends")) {
                  
                  //
                  String other=in.nextLine();
                  String id=in.nextLine();
                  String s[]=db.find_friend(other, id);
                  
                  int num=Integer.parseInt(s[0]);
                  
                  if(s[0].equals("")){
                     num=0;
                  }
                  
                  String s1=s[0];
                  int i=1;
                  while(i<num) {
                     s1=s1+'/'+s[i];
                     i++;
                  }
                  out.println(s1);
               }else if(request.equals("get_information")) {
            	   //get the information(nick_name, saying)
                  String id=in.nextLine();
                  String s[]=db.get_information(id);
                  
                  String s1=s[0];
                  int i=1;
                  while(i<2) {
                     s1=s1+'/'+s[i];
                     i++;
                  }
                  System.out.println(s1);
                  out.println(s1);
               }else if(request.equals("get_ChatInformation")) {
            	   //get the information(chat_id, participate, message)
                  String id=in.nextLine();
                  String user=in.nextLine();
                  String s[]=db.get_ChatInformation(id, user);
                  int num=Integer.parseInt(s[0]);
                  
                  if(s[0].equals("")){
                     num=0;
                  }
                  
                  String s1=s[0];
                  int i=1;
                  while(i<num) {
                     s1=s1+'/'+s[i];
                     i++;
                  }
                  out.println(s1);
               }else if(request.equals("check_id")) {
                  //클라이언트 측에서 더 이상의 요청이 없음 예외처리(505)
                  String id=in.nextLine();
                  boolean check=db.check_id(id);
                  System.out.println("server_check"+check);
                  out.println(check);
               }else if(request.equals("get_lastchat")) {
                  //last message for chatting
                  String id=in.nextLine();
                  String check=db.get_lastchat(id);
                  //this function is used to make chatFrame 
                  
                  out.println(check);
               }else if(request.equals("sign_up")) {
            	   //this is sign up
            	   //Hand over information to the database.
                  String id=in.nextLine();
                  String pass=in.nextLine();
                  String name=in.nextLine();
                  String nick=in.nextLine();
                  String email=in.nextLine();
                  String birth=in.nextLine();
                  String saying=in.nextLine();
                  String phone=in.nextLine();
                  String url=in.nextLine();
                  db.signUP(id, pass ,name, nick, birth, email, saying, phone, url);
                  
               }else if(request.equals("follow")) {
            	   //plus friends using the other(other user_id)
                  String id=in.nextLine();
                  String other=in.nextLine();
                  String a =db.plus_f(id, other);
                  out.println(a);
               }else if(request.equals("find_id")) {
            	   //the function that found the ID you forgot using email, name
                  String name=in.nextLine();
                  String email=in.nextLine();
                  String a =db.find_id(name, email);
                  out.println(a);
               }else if(request.equals("find_follow")) {
                  //Bring your friend's ID using user_id at follow table
                  String id=in.nextLine();
                  String s[]=db.find_follow(id);
                  int num=Integer.parseInt(s[0]);
                  
                  if(s[0].equals("")){
                     num=0;
                  }
                  System.out.println(1);
                  String s1=s[0];
                  int i=1;
                  while(i<num) {
                     s1=s1+'/'+s[i];
                     i++;
                  }
                  out.println(s1);
               }else if(request.equals("updateInfo")) {
            	   //updating the information(saying, nick_name, ) for user
                        String id=in.nextLine();
                        String what=in.nextLine();
                        String update=in.nextLine();
                        db.updateInfo(id,what,update);
                     }else if(request.equals("profile")) {
                    	 //update that profileFrame using user_information 
                        String id=in.nextLine();
                        String[] temp=db.profile(id);

                        for(int i=0; i<10; i++) {
                           out.println(temp[i]);
                        }
                     }else if(request.equals("stating")) {
                    	 //confirm login_state of other user
                        String id=in.nextLine();
                        String temp=db.get_state(id);
             
                           out.println(temp);
                        
                     }else if(request.equals("delete")) {
                    	 //function that delete friend
                        String id=in.nextLine();
                        String other=in.nextLine();
                        db.deleting(id, other);
                        
                     }else if(request.equals("chat")) {
                    	 //update chatting message
                        String id=in.nextLine();
                        String chat_id=in.nextLine();
                        String chatting=in.nextLine();
                        db.chat(id, chat_id, chatting);
                        
                     }else if(request.equals("time")) {
                        String id=in.nextLine();
                        db.second_UPstate(id);
                     }else if(request.equals("end")) {
                    	 //close the pogram
                    	 //and update state(access_time, login)
                        String id=in.nextLine();
                        db.end_UPstate(id);
                        socket.close();
                     }else if(request.equals("find_chat")) {
                    	 //find the chat using user_id
                        String id=in.nextLine();
                        String s[]=db.find_chat(id);

                     int num=Integer.parseInt(s[0]);
                     
                     if(s[0].equals("")){
                        num=0;
                     }
                     
                     String s1=s[0];
                     int i=1;
                     while(i<num) {
                        s1=s1+'/'+s[i];
                        i++;
                     }
                     out.println(s1);
                        
                     }else if(request.equals("sending_file")) {
                        String id=in.nextLine();
                        String other=in.nextLine();
                        
                        int data = din.readInt();           //Receive Int-type data.
                         String filename = din.readUTF();            
                         File file = new File(filename);             //Copy to the name of the file you entered and create it.
                         try {
                     outFile = new FileOutputStream(file);
                  } catch (Exception e) {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
                  } 
                         
                         int datas = data;                            //Variables that measure the number of transmissions, capacity.
                         byte[] buffer = new byte[1024];        //Creates a buffer that temporarily stores in bytes.
                         int len;
                         for(;data>0;data--){                   //The file is completed using FileOutputStream by receiving the number of transmitted data times.
                             len = inFile.read(buffer);
                             outFile.write(buffer,0,len);
                         }
                         outFile.close();
                         db.updateFile(id, other, filename);
                         if( file.exists() ){
                           if(file.delete()){
                              System.out.println("파일삭제 성공");
                           }else{
                              System.out.println("파일삭제 실패");
                           }
                        }else{
                           System.out.println("파일이 존재하지 않습니다.");
                        }
                      
                     }else if(request.equals("allow?")) {
                        String id=in.nextLine();
                        String chat_id=in.nextLine();
                        String allow=in.nextLine();
                        db.chat_allow(id, chat_id, allow);
                     }else if(request.equals("readChat")) {
                         String id1=in.nextLine();
                         String id2=in.nextLine();
                         System.out.println("readchat");
                         String ans[]=(db.readChat(id1, id2));
                         System.out.println("readchat");
                         for(int i=0; i<=ans.length; i++) {
                            out.println(ans[i]);
                            System.out.println(i+" "+ans[i]);
                         }
                      }
               

               
               
               
               }catch(Exception e) {
                  //Unconfirmed exception handling (???)
                  out.println("???");
               }
            }
         }catch(Exception e) {
            //Handle exception in socket connection
            System.out.println("Error:"+socket);
         }finally {
            try {
               socket.close();
            }catch(IOException e) {
               System.out.println("Closed: "+socket);
            }
         }
      }
   }
}

class customException extends Exception{
    customException(String msg){
       super(msg);
    }
}

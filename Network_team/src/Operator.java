import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
//The constructor is used as a parameter to connect to the server
public class Operator{
   LabelDemo LD=null;
   MyFrame mainFrame=null;
   Socket socket=null;
   Scanner in;
   PrintWriter out;
   int find_count=0;
   profileFrame pF=null;
   OutputStream outFile;
   FileInputStream fin;
   DataOutputStream dout;
   InputStream inFile = null;                       
   DataInputStream din=null;
   
   
   chatFrame cF;
   int thread_count=0;
   Operator() throws UnknownHostException, IOException{
      int port_num=7777;
      String host="localhost";
      //Read the ip address in the file and access the server.
      /*
      try {
         
         FileInputStream fis=new FileInputStream("server_info.dat");
         BufferedInputStream bis=new BufferedInputStream(fis);
         DataInputStream ois=new DataInputStream(bis);
         
         host=ois.readUTF();
         port_num=ois.readInt();
         ois.close();
         bis.close();
         fis.close();
      }catch(IOException e) {
         System.out.println(e.getMessage());
      }
   */
      socket=new Socket(host,port_num);
      in=new Scanner(socket.getInputStream());
      out=new PrintWriter(socket.getOutputStream(), true);
      
      outFile =socket.getOutputStream();    
      inFile = socket.getInputStream(); 
      outFile =socket.getOutputStream(); 
      din = new DataInputStream(inFile);
      in=new Scanner(socket.getInputStream());
      out=new PrintWriter(socket.getOutputStream(), true);

      //Opens a stream that sends data to the server in bytes.
    dout = new DataOutputStream(outFile); 
      
      
      //Opens a stream that sends data to the server in bytes.
    dout = new DataOutputStream(outFile); //Open streams sent in data units using OutputStream.
   }
  
   public static void main(String[] args) throws Exception{
      
      Operator opt = new Operator();
      opt.LD = new LabelDemo(opt);
   }
   //The functions below access the database through the server and update or select or delete the date
   boolean request_login(String user_id, String pass) throws Exception {
      out.println("login");
      out.println(user_id);
      out.println(pass);
      boolean num=in.nextBoolean();
      return num;
   }

   String[] find_friends(String search_id, String user_id)  throws Exception{
      out.println("find_friends");
      out.println(search_id);
      out.println(user_id);
      String temp1=new String();
      if(find_count==0) {
         in.nextLine();
         temp1=in.nextLine();
         find_count++;
      }else {
         temp1=in.nextLine();
         find_count++;
      }
      String[] s1=temp1.split("/");
      return s1;
   }
   int chat_count=0;
   String[] find_chatting(String user_id)  throws Exception{
         out.println("find_chat");
         out.println(user_id);
         String temp1=new String();
         temp1=in.nextLine();
         System.out.println(temp1);
         String[] s1=temp1.split("/");
         return s1;
      }
   int find_friends=0;
   
   String[] find_follow(String user_id)  throws Exception{
      out.println("find_follow");
      out.println(user_id);
      String temp1=new String();

         temp1=in.nextLine();
         find_friends++;

      temp1=temp1.trim();
      String[] s1=temp1.split("/");
      return s1;
   }
   
   String follow(String user_id, String other_id) throws Exception {
      out.println("follow");
      out.println(user_id);
      out.println(other_id);
      String num=in.nextLine();
      return num;
   }
   String find_ID(String name, String email) throws Exception {
      out.println("find_id");
      out.println(name);
      out.println(email);
      String num=in.nextLine();
      return num;
   }
   boolean check_id(String user_id) throws Exception {
      out.println("check_id");
      out.println(user_id);
      boolean num=in.nextBoolean();
      return num;
   }
   String[] get_information(String user_id)  throws Exception{
      out.println("get_information");
      out.println(user_id);
      String temp1=new String();
      if(find_count==0) {
         in.nextLine();
         temp1=in.nextLine();
         find_count++;
      }else {
         temp1=in.nextLine();
         find_count++;
      }


      String[] s1=temp1.split("/");

      return s1;
   }
   String[] get_ChatInformation(String chat_id, String user_id)  throws Exception{
         out.println("get_ChatInformation");
         out.println(chat_id);
         out.println(user_id);
         String temp1=new String();
         temp1=in.nextLine();
         find_count++;
         String[] s1=temp1.split("/");
         return s1;
      }
   String get_lastchat(String chat_id) {
      out.println("get_lastchat");
      out.println(chat_id);
      String s1=new String();
      s1=in.nextLine();
      return s1;
   }
   void sign_up(String id, String pw, String nick, String name, String email, String birth, String saying, String iphone, String url) throws Exception {
      out.println("sign_up");
      out.println(id);
      out.println(pw);
      out.println(name);
      out.println(nick);
      out.println(email);
      out.println(birth);
      out.println(saying);
      out.println(iphone);
      out.println(url);
   }
   
   String[] profile(String id) {
      out.println("profile");
      out.println(id);
      String[] temp=new String[10];
      for(int i=0; i<10; i++) {
         temp[i]=in.nextLine();
      }
      return temp;
   }
   
   void updateInfo(String user_id,String what,String update) {
         out.println("updateInfo");
         out.println(user_id);
         out.println(what);
         out.println(update);
      }
   void allow_chat(String user_id,String chat_id,String allow) {
       out.println("allow?");
       out.println(user_id);
       out.println(chat_id);
       out.println(allow);
    }
   void delete(String user_id, String other_id) {
         out.println("delete");
         out.println(user_id);
         out.println(other_id);
      }
   String stating(String user_id) {
         out.println("stating");
         out.println(user_id);
         String s=in.nextLine();
         return s;
      }
   void chat(String user_id, String chat_id, String chatting) {
      out.println("chat");
       out.println(user_id);
       out.println(chat_id);
       out.println(chatting);
   }
   void make_chat(String user_id, String chat_id) {
	      out.println("make_chat");
	       out.println(user_id);
	       out.println(chat_id);
	   }
   void update_time(String user_id) {
      out.println("time");
       out.println(user_id);
   }void end_program(String user_id) {
      out.println("end");
       out.println(user_id);
   }
   void sending_file(String user_id, String other, File temp) throws IOException {
         out.println("sending_file");
         out.println(user_id);
         out.println(other);
         
         
         System.out.println("operator start");
         try{
               fin = new FileInputStream(temp); //FileInputStream
               
           byte[] buffer = new byte[1024];        //Creates a buffer that temporarily stores in bytes.
           int len;                               //Variable that measures the length of data to be transferred.
           int data=0;                            //Variables that measure the number of transmissions, capacity.
           
           while((len = fin.read(buffer))>0){    
               data++;                        //Measures the amount of data.
           }
           
           int datas = data;                      //Because the data becomes zero through the for statement below, it is temporarily saved.
    
           fin.close();
           fin = new FileInputStream(temp);   //new FileInputStream
           dout.writeInt(data);                   //It sends the number of data transfers to the server
           dout.writeUTF(temp.getName());               //Sends the name of the file to the server.
           
            len = 0;
            System.out.println("operator start");
           for(;data>0;data--){             
               len = fin.read(buffer);        
               outFile.write(buffer,0,len);    
           }
           
           }catch(Exception e){
           }
         System.out.println("operator end");
         
}
   String[] readChat(String id1, String id2) {
	   //reading all message for chat room 
	      out.println("readChat");
	      out.println(id1);
	      out.println(id2);
	      int numOfChat=0;
	      if(in.hasNextLine()) {
	         String a=in.nextLine();
	         numOfChat=Integer.parseInt(a);
	      }
	   
	      String[] ans=new String[numOfChat];
	      for(int i=0; i<numOfChat; i++)
	         ans[i]=in.nextLine();
	         
	      return ans;
	   }
   void read_file(String id, String other, String path) throws IOException {
	   out.println("read_file");
	   out.println(id);
	   out.println(other);
	   out.println(path);
	   System.out.println("asdfasdfasfdasdf");
	   
       int data = 0;
	try {
		data = din.readInt();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}           //Receive Int-type data.
        String filename = null;
		try {
			filename = din.readUTF();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		System.out.println("filename "+filename);
		
        //filename=filename.replace("sending/", "");
		filename="client/"+filename;
		System.out.println("filename "+filename);
        File file = new File(filename);             //Copy to the name of the file you entered and create it.
        try {
    outFile = new FileOutputStream(file);
 } catch (Exception e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
 } 
        System.out.println("asdfasdfasfdasdf");
        int datas = data;                            //Variables that measure the number of transmissions, capacity.
        byte[] buffer = new byte[1024];        //Creates a buffer that temporarily stores in bytes.
        int len;
        for(;data>0;data--){                   //The file is completed using FileOutputStream by receiving the number of transmitted data times.
            len = inFile.read(buffer);
            outFile.write(buffer,0,len);
        }
        outFile.flush();
        outFile.close();
        
     
   }


   
}
//

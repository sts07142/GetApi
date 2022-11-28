import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Operator{
	LabelDemo LD=null;
	MyFrame mainFrame=null;
	//main구문에서 각 클래스에 대한 생성자를 null로 초기화하고 이를 매개변수로 사용한다.
	//
	Socket socket=null;
	Scanner in;
	PrintWriter out;
	int find_count=0;
	Operator() throws UnknownHostException, IOException{
		int port_num=7777;
		String host="localhost";
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

		socket=new Socket(host,port_num);
		in=new Scanner(socket.getInputStream());
		out=new PrintWriter(socket.getOutputStream(), true);
	}
	public static void main(String[] args) throws Exception{
		
		Operator opt = new Operator();
		opt.LD = new LabelDemo(opt);
	}
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
		System.out.println(temp1);
		String[] s1=temp1.split("/");
		return s1;
	}
	boolean follow(String user_id, String other_id) throws Exception {
		out.println("follow");
		out.println(user_id);
		out.println(other_id);
		boolean num=in.nextBoolean();
		return num;
	}
	boolean check_id(String user_id) throws Exception {
		out.println("check_id");
		out.println(user_id);
		boolean num=in.nextBoolean();
		System.out.println("check "+num);
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
		
		System.out.println(temp1);
		String[] s1=temp1.split("/");
		for(int i=0; i<2; i++) {
			System.out.println(s1[i]);
		}
		return s1;
	}
	void sign_up(String id, String pw, String nick, String name, String email, String birth, String saying, String phone, String url) throws Exception {
		out.println("sign_up");
		out.println(id);
		out.println(pw);
		out.println(name);
		out.println(nick);
		out.println(email);
		out.println(birth);
		out.println(saying);
		out.println(phone);
		out.println(url);
	}
}
//
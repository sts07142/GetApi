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

	String[] find_friends(String user_id)  throws Exception{
		out.println("find_friends");
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
}
//
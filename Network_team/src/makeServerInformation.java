import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class makeServerInformation {
	public static void main(String[] args) {
		int port_num=7777;
		try {
			//서버의 ip address와 port number에 대한 정보를 가진 파일을 만들기
			
			String message="127.0.0.1";
			FileOutputStream temp=new FileOutputStream("server_info.dat");
			BufferedOutputStream temp1=new BufferedOutputStream(temp);
			DataOutputStream temp2=new DataOutputStream(temp1);
			temp2.flush();
			temp2.writeUTF(message);
			temp2.writeInt(port_num);
			
			temp2.close();
			temp1.close();
			temp.close();
			
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
}

import java.util.*;
import java.io.*;
import java.util.concurrent.*;



import java.net.*;
public class Server {
	public static void main(String[] args)throws Exception {
		//서버 소켓을 7777 포트넘버로 활성화
		ServerSocket listenner=new ServerSocket(7777);
		System.out.println("the capitalization server is running.....");
		//쓰레드 20개를 놀게한다.
		ExecutorService pool=Executors.newFixedThreadPool(20);
		
		while(true) {
			Socket sock=listenner.accept();
			//클라이언트 접속요청, 서버 승낙
			//하나의 쓰레드를 클라이언트와 연결
			pool.execute(new Capitalizer(sock));
		}
	}
	static Database db = new Database();
	private static class Capitalizer implements Runnable{
		//static Database db = new Database();
		private Socket socket;
		Capitalizer(Socket socket){
			this.socket=socket;
		}
		@Override
		public void run() {
			System.out.println("Connected: "+socket);
			try {
				var in=new Scanner(socket.getInputStream());
				//클라이언트->서버로 데이터 전송
				var out=new PrintWriter(socket.getOutputStream(), true);
				//서버->클라이언트로 데이터 전송
				while(in.hasNextLine()) {
					//요구조건에서 메세지 형태가 아닌 필드값을 반환
					try {
					String request=in.nextLine();
					if(request.equals("login")) {
						//클라이언트 측에서 더 이상의 요청이 없음 예외처리(505)
						String id=in.nextLine();
						String pass=in.nextLine();
						System.out.println("Sid : "+id+"\nSpass : "+pass);
						boolean check=db.logincheck(id, pass);
						out.println(check);
					}else if(request.equals("find_friends")) {
						
						//클라이언트 측에서 더 이상의 요청이 없음 예외처리(505)
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
					}else if(request.equals("check_id")) {
						//클라이언트 측에서 더 이상의 요청이 없음 예외처리(505)
						String id=in.nextLine();
						boolean check=db.check_id(id);
						System.out.println("server_check"+check);
						out.println(check);
					}else if(request.equals("sign_up")) {
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
						
					}
					}catch(Exception e) {
						//확인되지 않은 예외처리(???)
						out.println("???");
					}
				}
			}catch(Exception e) {
				//소켓 연결에서 예외 발생시 처리
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

/*}catch(customException e) {
						//메세지 형태로 에러와 수행을 알리는 것이 아닌 특정 정수를 지정해서 반환
						//서버 측이 아닌 클라이언트 측에서 반환받은 정수를 통해 에러의 종류를 확인한다.
						out.println(e.getMessage());
					}
 */
//모든 예외를 처리할 수 없어 커스텀 형태의 예외처리 클래스 선언
class customException extends Exception{
	 customException(String msg){
		 super(msg);
	 }
}

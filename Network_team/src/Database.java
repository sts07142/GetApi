

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Database {
	Connection con = null;
	Statement stmt = null;
	//한명의 특정 유저가 사용을 종료하기 전까지 DB에 지속적인 연결을 유지
	String url = "jdbc:mysql://localhost/net";	//dbstudy 스키마
	String user = "root";
	String passwd = "cbxd2525";
	//본인이 설정한 root 계정의 비밀번호를 입력하면 된다.

	//생성자 부분에서 DB에 연결을 시작
	Database() {	//데이터베이스에 연결한다.
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, passwd);
			stmt = con.createStatement();
			System.out.println("MySQL suceess");
		} catch(Exception e) {
			System.out.println("MySQL fail > " + e.toString());
		}
	}
	
	
	
	boolean logincheck(String _i, String _p) {
		boolean flag = false;
		
		String id = _i;
		String pw = decoding.decode(_p);
		//위의 calcutate.decode는 비밀번호를 RSA알고리즘을 통해서 변환 : for 보안
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
		
		return flag;
	}
	
	//유저가 등록한 이미지를 가지고 온다.
	String get_profile(String _i) {
		String user_id = _i;
		String s=new String();
		try {
			String checkingStr = "select profile from state where user_id = \'" + user_id + "\'";
			ResultSet result = stmt.executeQuery(checkingStr);
			while(result.next()) {
				s= result.getString(1);
				//만약 값이 존재하지 않으면 문자열을 1로 초기화해 이미지가 없다고 알림
				if (result.wasNull()) s = "1";
			}
		} catch(Exception e) {
		}
		return s;
	}
	
	
	
	boolean signUP(String _i, String _p,String _n,String _y) {
		boolean flag = false;
		String id = _i;
		String pw = _p;
		String name = _n;
		int year = Integer.parseInt(_y);
		String sql = "insert into USER(user_id, password, name, age) values (?,?,?,?)";
		try {
			

			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			pstmt.setInt(4, year);
			

			int r = pstmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "joining completed!", "joined", 1);
		} catch (SQLException e1) {
			System.out.println("SQL error" + e1.getMessage());
			if (e1.getMessage().contains("PRIMARY")) {
				JOptionPane.showMessageDialog(null, "Duplicate ID!", "Duplicate ID error", 1);
				return false;
			}else
				JOptionPane.showMessageDialog(null, "Registration error!", "error", 1);
		}
		
		flag=true;
		
		return flag;
	}
}






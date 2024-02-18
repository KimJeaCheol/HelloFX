package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBConnection {
	public Connection con;
	public Statement stmt;
	public PreparedStatement psmt;
	public ResultSet rs;
	
	public JDBConnection() {
		
		try {
//			
			try {
				//con = DriverManager.getConnection("jdbc:mariadb://192.168.0.3:3307/noticeboard?user=root&password=password1!");
				Class.forName("org.mariadb.jdbc.Driver");
				String url = "jdbc:mariadb://192.168.0.3:3307/noticeboard";
				String id = "root";
				String pw = "password1!";
				con = DriverManager.getConnection(url, id, pw);
				System.out.println("DB 연결 성공");
			} catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("DB 연결 실패");
			}
			
			
		} finally {
			
			// TODO: handle finally clause
		}
	}
}

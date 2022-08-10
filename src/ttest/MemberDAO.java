package ttest;

import java.sql.*;
import java.util.ArrayList;

public class MemberDAO {

	private static MemberDAO dao = new MemberDAO();
	private MemberDAO(){}

	public static MemberDAO getInstance() {
		return dao;
	}

	public Connection connect() {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.78:1521:xe", "test", "1111");
			if(conn!=null) System.out.println("db성공 ");
			else System.out.println("db실패 ");
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return conn;
	}

	public void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception ex) {
				System.out.println(ex);				
			}
		}
		close(conn, ps);
	} // close

	public void close(Connection conn, PreparedStatement ps) {
		if (ps != null) {
			try {
				ps.close();
			} catch (Exception ex) {
				System.out.println(ex);				
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (Exception ex) {
				System.out.println(ex);				
			}
		}
	} // close

	//table create 
	public void insert(ClassInfo ci) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = connect();
			pstmt = conn.prepareStatement("insert into ClassInfo(id,name,kor,eng,math) values(?,?,?,?,?)");
			pstmt.setString(1, ci.getId());
			pstmt.setString(2,ci.getName());
			pstmt.setInt(3, ci.getKor());
			pstmt.setInt(4, ci.getEng());
			pstmt.setInt(5, ci.getMath());
			pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println(ex);				
		} finally {
			close(conn, pstmt);
		}
	}

	/*
	 * public ClassInfo findOne(String id) {
	 * 
	 * }
	 * 
	 * public void update(ClassInfo ci) {
	 * 
	 * 
	 * }
	 * 
	 * public void delete(String id) {
	 * 
	 * }
	 * 
	 * public ArrayList<ClassInfo> findAll() {
	 * 
	 * 
	 * }
	 */

}

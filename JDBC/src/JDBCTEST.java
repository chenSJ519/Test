import java.sql.*;
public class JDBCTEST {

	public JDBCTEST() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url="jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		Connection conn=DriverManager.getConnection(url, "scott", "123");
		String sql="update dept set loc=? where deptno=?";
		PreparedStatement pstm=conn.prepareStatement(sql);
		
		pstm.setString(1, "ÍòÄê1");
		pstm.setString(2, "12");
//		pstm.setString(2, "kl");
//		pstm.setString(3, "jk");
		pstm.executeUpdate();
//		System.out.println(rs);
//		while(rs.next())
//		{
//			System.out.println(rs.getString("ename"));
//		}
//		rs.close();
		pstm.close();
		conn.close();
		
		
		
		
	}
}

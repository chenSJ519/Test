import java.io.*;
import java.sql.*;

import org.junit.Test;
public class TestXiaosuo {

	public TestXiaosuo() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			conn=getConnection();
			
			String sql="insert into 傲世九重天  values(?,?,?)";
			ps=conn.prepareStatement(sql);
			BufferedReader bur=new BufferedReader(new InputStreamReader(new FileInputStream("e:\\2.txt")));
			String s="";
			boolean flag=false;
			StringBuilder sb=new StringBuilder();
			String last="前言";
			int count=0;
			while((s=bur.readLine())!=null)
			{
				if(s.matches(".*\u7B2C.{1,7}\u7AE0.*"))
				{
					count++;
					ps.setObject(1, count);
					ps.setObject(2, last);
					last=s;
					ps.setObject(3, sb.toString());
					ps.executeUpdate();
					sb=new StringBuilder();
				}
				
				sb.append(s);
				
			}
			ps.setObject(1, count+1);
			ps.setObject(2, last);
			ps.setObject(3, sb.toString());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				conn.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private static Connection getConnection() {
	    Connection conn = null;
	    try {
	        Class.forName("oracle.jdbc.driver.OracleDriver");
	        conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:ORCL", "jgs", "123");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return conn;
	}
	@Test
	public void t1()
	{
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			String sql="create table DKK(myindex number,chapter varchar2(100),content clob)";
			conn =getConnection();
			ps=conn.prepareStatement(sql);
//			ps.setString(1, "天下1");
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				conn.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}

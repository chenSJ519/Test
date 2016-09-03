import java.sql.*;

import org.apache.commons.dbcp2.BasicDataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3p0 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
			String driver="oracle.jdbc.driver.OracleDriver";
			String url="jdbc:oracle:thin:@127.0.0.1:1521:orcl";
			String user="scott";
			String password="123";
			
//			ComboPooledDataSource cpds=new ComboPooledDataSource();
//			cpds.setDriverClass(driver);
//			cpds.setJdbcUrl(url);
//			cpds.setUser(user);
//			cpds.setPassword(password);
//			cpds.setInitialPoolSize(3);
//			cpds.setMaxPoolSize(1000);
			
			BasicDataSource bds=new BasicDataSource();
			bds.setDriverClassName(driver);
			bds.setUrl(url);
			bds.setUsername(user);
			bds.setPassword(password);
			
			Connection conn=null;
			boolean b=true;
			while(b)
			{
				conn=bds.getConnection();
				System.out.println(conn);
			}
			PreparedStatement ps =conn.prepareStatement("select * from dept");
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				System.out.println(rs.getString(3));
			}
			
			
	}

}

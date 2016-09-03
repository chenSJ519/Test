import java.sql.*;
import java.util.Scanner;
public class FenYie {

	public FenYie() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) throws Exception {
		String url="jdbc:oracle:thin:@127.0.0.1:1521:ORCL";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn=DriverManager.getConnection(url, "scott", "123");
		Statement stm= conn.createStatement();
		
		boolean a=true;
		
		while(a)
		{
			System.out.println("请输入你要哪一页");
			Scanner input=new Scanner(System.in);
			int i=input.nextInt();
			String sql=pageFen(2,i);//默认为一页两条数据
			ResultSet rs= stm.executeQuery(sql);
			System.out.println(rs);
			while(rs.next())
			{
				System.out.println(rs.getString("ename"));
			}
			rs.close();
		}
		
		
		conn.close();
		
	}
		public static String pageFen(int pageSize,int  pageIndex)
		{
			String sql="select * from (select ename,rownum rowindex from emp ) where  rowindex between ("+pageIndex+"-1)*"+pageSize+"+1 and "+pageSize+"*+"+pageIndex;
			return sql;
			
		}
}

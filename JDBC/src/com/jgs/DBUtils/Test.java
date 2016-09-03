package com.jgs.DBUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import com.jgs.DBUtils.Dept;
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			String url="jdbc:oracle:thin:@127.0.0.1:1521:ORCL";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection(url, "scott", "123");
			QueryRunner qr=new QueryRunner();
			List<Dept>  deptList=qr.query(conn, "select * from Dept", new BeanListHandler<Dept>(Dept.class));
			Iterator<Dept> it=deptList.iterator();
			while(it.hasNext())
			{
				
				System.out.println(it.next());
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

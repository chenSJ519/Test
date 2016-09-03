
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

public class test {
	private int dka=3;
	private static int dd=89;
	public static void main(String[] args) throws Exception {
			int var=3;
			Date dat=new Date();
			 Connection conn=null;
		     QueryRunner qr=new QueryRunner();
		     dd++;

			conn=getConnection();
			String sql="select * from emp";
			List<Map<String, Object>> mapList=qr.query(conn, sql, new MapListHandler());
//			for(Map<String,Object> map:mapList)
//			{
//				Date d=(Date)map.get("HIREDATE");
//				map.put("HIREDATE", d.toString());
//			}
			Dept d=new Dept();
			Gson json=new Gson();
			
//			JSONObject jsonObject=new JSONObject(mapList);
			
			  List list = new ArrayList();
		        list.add( "first" );
		        list.add( "second" );
//			jsonObject.put("rows", jsonArray);
//			jsonObject.put("total", 100);
//		        JSONArray jsonArray=new JSONArray(list);
		        JSONObject jsonObject=new JSONObject(list);
//			System.out.println(jsonObject);
			System.out.println(jsonObject);
			
//			String sss=json.getJSONObject(1).getString("HIREDATE");
//			System.out.println(sss);
//			JSONObject jsonz=new JSONObject();
//			jsonz.put("rows", json);
//			jsonz.put("total", 100);
//			System.out.println(json);
			
			
//			Iterator it=json.iterator();
//			
//			while(it.hasNext())
//			{
////				JSONArray js=(JSONArray) it.next();
////				Iterator it2=js.iterator();
//				System.out.println(it.next());
//				Class c=it.next().getClass();
////				c.getName();
//				System.out.println(c.getName());
////				while(it2.hasNext())
////				{
////					System.out.println(it2.next());
////				}
//			}
////			String ss=json.getJSONObject(0).getString("HIREDATE");
////			System.out.println(ss);
////			String[] a=ss.split(",");
////			String day=a[0].split(":")[1];
////			String month=a[4].split(":")[1];
////			String year=a[9].split(":")[1].replaceAll("}", "");
////			String date=year+"年"+month+"月"+day+"日";
////			
////			System.out.println(date);
////			System.out.println();
//			String s="{\"total\": 100,\"rows\":"+json.toString()+"}";
////			System.out.println(json);
//			System.out.println(s);
//		 Map<String, String> map1 = new HashMap<String, String>();
//	        map1.put("name", "Alexia");
//	        map1.put("sex", "female");
//	        map1.put("age", "23");
//	        List list = new ArrayList();
//	        list.add( "first" );
//	        list.add( "second" );
//		JSONObject jb=JSONObject.fromObject(list);
//		System.out.println(jb);
	}
	private static Connection getConnection() {
	    Connection conn = null;
	    try {
	        Class.forName("oracle.jdbc.driver.OracleDriver");
	        conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:ORCL", "scott", "123");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return conn;
	}
}

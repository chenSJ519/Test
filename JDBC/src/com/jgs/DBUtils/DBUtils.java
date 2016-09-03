package com.jgs.DBUtils;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Date;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
public class DBUtils {
	public static void main(String[] args) {
		
		//新增
		Visitor v=new Visitor("陈小明", "1264830070@qq.com");
		v.setCreateTime(new Date());
		v.setStatus(1);
		insertVisitor(v);
		//删除
//		deleteVisitor(38);
		//
//		Visitor v=retrieveVisitor(37);
		//
//		getVisitorList();
//		getVisitWithMap(37);
		getVisitWithMapLs();
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
	
	/*
	 * 新增Visitor, ScalarHandler的demo
	 */
	public static void insertVisitor(Visitor visitor) {
	    Connection conn = getConnection();
	    QueryRunner qr = new QueryRunner();
	    String sql = "insert into visitor (Id,Name, Email, Status, CreateTime) values (?,?, ?, ?, ?)";
	    try {
	    	BigDecimal newId = (BigDecimal) qr.query(conn, "select name_id.nextval from dual", new ScalarHandler<BigDecimal>(1));
	         visitor.setId(Integer.valueOf(String.valueOf(newId)));
	         SimpleDateFormat sdf=new SimpleDateFormat("dd-MM月-yy");
	         String time=sdf.format(visitor.getCreateTime());
	         String newtime=time.replaceFirst("-0", "-");
	        int count = qr.update(conn, sql, visitor.getId(),visitor.getName(), visitor.getEmail(), 1, new java.sql.Date(new Date().getTime()));
	        System.out.println("新增" + count + "条数据=>Id：" + newId);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static void deleteVisitor(int id) {
	    Connection conn = getConnection();
	    QueryRunner qr = new QueryRunner();
	    String sql = "delete from visitor where status>0 and id=?";
	    try {
	        int count = qr.update(conn, sql, id);
	        System.out.println("删除" + count + "条数据。");
	    } catch (SQLException e) {
	        // TODO: handle exception
	        e.printStackTrace();
	    }
	}
	
//	查询方法 

	public static Visitor retrieveVisitor(int id) {
	    Connection conn = getConnection();
	    Visitor visitor = null;
	    QueryRunner qr = new QueryRunner();
	    String sql = "select * from visitor where status>0 and id=?";        
	    try {
	        visitor = (Visitor) qr.query(conn, sql, new BeanHandler<Visitor>(Visitor.class), id);
	        System.out.println(visitor);
	        return visitor;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return visitor;
	}
//	更新操作


	public static void updateVisitor(int id) {
	    Visitor visitor = retrieveVisitor(id);
	    System.out.println("更新前：" + visitor);
	    Connection conn = getConnection();
	    String updateFieldStr = visitor.getName();
	    QueryRunner qr = new QueryRunner();
	    String sql = "update visitor set Name = ?, Email = ?, Status = ?, CreateTime = ? where status>0 and Id = ?";
	    if (updateFieldStr.contains("updated")) {
	        updateFieldStr = updateFieldStr.substring(0, updateFieldStr.indexOf("updated"));
	    } else {
	        updateFieldStr = updateFieldStr + "updated";
	    }
	    visitor.setName(updateFieldStr);
	    try {
	        int count = qr.update(conn, sql, new Object[] { visitor.getName(), visitor.getName(), visitor.getStatus(),
	                visitor.getCreateTime(), visitor.getId() });
	        System.out.println("更新了" + count + "条数据");
	        System.out.println("更新后：" + visitor);
	    } catch (SQLException e) {
	        // TODO: handle exception
	        e.printStackTrace();
	    }
	}
//	BeanListHandler方法


	public static void getVisitorList() {
	    Connection conn = getConnection();
	    QueryRunner qr = new QueryRunner();
	    String sql = "select * from visitor where status>0";
	    try {
	        List<Visitor> ls = qr.query(conn, sql, new BeanListHandler<Visitor>(Visitor.class));
	        for (Visitor visitor : ls) {
	            System.out.println(visitor);
	        }
	    } catch (SQLException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	}
//	MapHandler操作


	public static void getVisitWithMap(int id) {
	    Connection conn = getConnection();
	    QueryRunner qr = new QueryRunner();
	    String sql = "select * from visitor where status>0 and id=?";
	    try {
	        Map<String, Object> map = qr.query(conn, sql, new MapHandler(), id);
	        Integer visitorId = Integer.valueOf(map.get("Id").toString());
	        String visitorName = map.get("Name").toString();
	        String visitorEmail = map.get("Email").toString();
	        Integer visitorStatus = Integer.valueOf(map.get("Status").toString());
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        java.util.Date visitorCreateTime = sdf.parse(map.get("CreateTime").toString());
	        Visitor visitor = new Visitor(visitorName, visitorEmail);
	        visitor.setId(visitorId);
	        visitor.setStatus(visitorStatus);
	        visitor.setCreateTime(visitorCreateTime);
	        System.out.println(visitor);
	    } catch (Exception e) {
	        // TODO: handle exception
	        e.printStackTrace();
	    }
	}
//	MapListHandler方法


	public static void getVisitWithMapLs() {
	        Connection conn = getConnection();
	        QueryRunner qr = new QueryRunner();
	        String sql = "select * from visitor where status>0";
	        try {
	            List<Map<String, Object>> mapLs = qr.query(conn, sql, new MapListHandler());
	            for (Map<String, Object> map : mapLs) {
	                Integer visitorId = Integer.valueOf(map.get("Id").toString());
	                String visitorName = map.get("Name").toString();
	                String visitorEmail = map.get("Email").toString();
	                Integer visitorStatus = Integer.valueOf(map.get("Status").toString());
	                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	                java.util.Date visitorCreateTime = sdf.parse(map.get("CreateTime").toString());
	                Visitor visitor = new Visitor(visitorName, visitorEmail);
	                visitor.setId(visitorId);
	                visitor.setStatus(visitorStatus);
	                visitor.setCreateTime(visitorCreateTime);
	                System.out.println(visitor);
	            }
	        } catch (Exception e) {
	            // TODO: handle exception
	            e.printStackTrace();
	        }
	    }
}

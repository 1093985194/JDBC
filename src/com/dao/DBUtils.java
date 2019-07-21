package com.dao;


import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
//工具类
public class DBUtils {
    private static String sDriver;
    private static String sUrl;
    private static String sUserName;
    private static String sPassWord;
    private static Connection sConn;

    static {
        InputStream in = DBUtils.class.getClassLoader().getResourceAsStream("db.properties");
        Properties pro = new Properties();
        try {
            pro.load(in);
            sDriver = pro.getProperty("driver");
            sUrl =pro.getProperty("url");
            sUserName =pro.getProperty("username");
            sPassWord = pro.getProperty("password");
//            System.out.println("连接数据库...");
            //加载驱动
            Class.forName(sDriver);
//            System.out.println("加载驱动...");
//            sConn = DriverManager.getConnection(sUrl, sUserName, sPassWord);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        Connection conn = null;
           try {
               conn = DriverManager.getConnection(sUrl, sUserName, sPassWord);
//               conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students", "root", "oyzy");
//               System.out.println("获取连接成功");
               System.out.println("");
           } catch (SQLException e) {
               e.printStackTrace();
               System.out.println("获取连接失败");
           }
               return conn;
    }

    /**
     * 释放资源的方法
     */
    public static void close(ResultSet rs,PreparedStatement stat,Connection conn){
        try {
            if(rs!=null)rs.close();
            if(stat!=null)stat.close();
            if(conn!=null)conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
     }

}

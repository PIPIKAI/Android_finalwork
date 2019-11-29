package com.jdbcmysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.data.USER;

public class jdbcc {
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://10.0.2.2:3306/vote";
    private static final String user = "root";
    private static final String pwd = "1234";
    private static Connection conn=null;
    private static PreparedStatement stmt=null;
    public static List<String[]> getwords(int num) {
        List<String[]> res = new ArrayList<>();
        try {
            Class.forName(driver).newInstance();
            System.out.println("驱动加载成功！！！！！");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        try{
            conn = DriverManager.getConnection(url,user,pwd);
            System.out.println("连接数据库成功！！！！！！");
            String sql="SELECT * FROM enwords  ORDER BY RAND() LIMIT "+Integer.toString(num);
            stmt = conn.prepareStatement(sql);
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String word=rs.getString("word");
                String trans=rs.getString("translation");
                String map1[]={word, trans};
                res.add(map1);
                System.out.println("word: "+word+"   trans： "+trans);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if(conn!=null){
                try {
                    conn.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return res;
    }

    public static boolean linkMysql(String us,String pa) {
        boolean res=false;
        try {
            Class.forName(driver).newInstance();
            System.out.println("驱动加载成功！！！！！");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        try{
            conn = DriverManager.getConnection(url,user,pwd);
            System.out.println("连接数据库成功！！！！！！");
            stmt = conn.prepareStatement("SELECT * FROM USER");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String id=rs.getString("username");
                String pass=rs.getString("password");
                if(pa.equals(pass)&&us.equals(id)){
                    res=true;
                    return true;
                }
                System.out.println(":"+id+":"+":"+pass+":");
            }
            return res;

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if(conn!=null){
                try {
                    conn.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return  res;
    }




}


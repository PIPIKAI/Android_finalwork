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
    public static USER data  =null;
    /**
     * 连接数据库
     * @return
     */
    public static Connection connection() {
        Connection conn = null;
        try {
            Class.forName(driver);  //加载数据库驱动
            try {
                conn = DriverManager.getConnection(url, user, pwd);  //连接数据库
                System.out.println("数据库连接成功(*￣︶￣)");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }
    /**
     * 关闭数据库链接
     * @return
     */
    public static void close() {
        if(conn != null) {
            try {
                conn.close();  //关闭数据库链接
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 从数据库获得单词
     * @return
     */
    public static List<String[]> getwords(int num) {
        List<String[]> res = new ArrayList<>();
        conn = connection();
        String sql="SELECT * FROM enwords  ORDER BY RAND() LIMIT "+Integer.toString(num);
        try {
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
            close();
        }
        return res;
    }
    /**
     * 登录
     * @return
     */
    public static boolean login(String us,String pa) {
        conn = connection();
        boolean res = false;

        try {

            stmt = conn.prepareStatement("SELECT * FROM USER WHERE username = ? AND PASSWORD = ?");
            stmt.setString(1,us);
            stmt.setString(2,pa);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String id = rs.getString("username");
                String pass = rs.getString("password");
                int gola = rs.getInt("goaldays");
                int daily = rs.getInt("daily");
                int had=rs.getInt("had");
                res = true;
                System.out.println("登陆成功！~");
                data=new USER(gola,daily,us,pass,had);
                return true;
            }
        }
        catch(Exception e){
                e.printStackTrace();
        }
        finally{
                close();
        }
        return res;

    }
    /**
     * 添加用户到数据库（注册）
     * @return
     */
    public static boolean user_add(String us,String pa,String gola,String daily) {
        boolean res=false;
        try{
            conn = connection();

            stmt = conn.prepareStatement("SELECT * FROM USER WHERE username = ? ");
            stmt.setString(1,us);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return false;// 用户名已存在时
            }

            String sql="INSERT INTO USER (username,PASSWORD,goaldays,daily,had) VALUES (?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,us);
            stmt.setString(2,pa);
            stmt.setInt(3,Integer.valueOf(gola));
            stmt.setInt(4,Integer.valueOf(daily));
            stmt.setInt(5,0);
            stmt.executeUpdate();
            System.out.println("注册成功(*￣︶￣)");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
           close();
        }
        return  res;
    }
    /**
     * 返回用户数据
     * @return
     */
    public USER getData(){
        return this.data;
    }


}


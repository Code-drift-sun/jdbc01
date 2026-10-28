package com.aaa.demo1.dao;

import com.aaa.demo1.entity.Dept;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeptDao {

    public int insert(String name){
        Connection con=null;
        PreparedStatement ps=null;
        try {
            //1. 获取连接对象
            // url: 数据库服务的地址。 jdbc:mysql://IP:Port/数据库名
            // user:数据库账号  password:密码
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/qy_196", "root", "root");
            //2. 获取执行sql语句的对象. ?:表示占位符。
            String sql = "insert into dept values(null,?)";
            ps = con.prepareStatement(sql);
            //2.2 为占位符赋值: 1表示第一个占位符。 name表示占位符的值
            ps.setString(1, name);
            //3. 开始执行sql语句.
            int row = ps.executeUpdate();//如果执行增删改的sql 直接调用该方法。返回值表示影响的行数。
            return row;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(ps!=null){
                    ps.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if(con!=null){
                    con.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    //删除部门
    public int delete(int id){
        Connection con = null;
        PreparedStatement ps = null;
        try {
            //1. 获取连接对象
            // url: 数据库服务的地址。 jdbc:mysql://IP:Port/数据库名
            // user:数据库账号  password:密码
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/qy_196", "root", "root");
            //2. 获取执行sql语句的对象. ?:表示占位符。
            String sql = "delete from dept where id=?";
            ps = con.prepareStatement(sql);
            //2.2 为占位符赋值: 1表示第一个占位符。 name表示占位符的值
            ps.setInt(1, id);
            //3. 开始执行sql语句.
            int row = ps.executeUpdate();//如果执行增删改的sql 直接调用该方法。返回值表示影响的行数。
            return row;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(ps!=null){
                    ps.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if(con!=null){
                    con.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    //修改部门
    public int update(int id,String name){
        Connection con = null;
        PreparedStatement ps = null;
        try {
            //1. 获取连接对象
            // url: 数据库服务的地址。 jdbc:mysql://IP:Port/数据库名
            // user:数据库账号  password:密码
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/qy_196", "root", "root");
            //2. 获取执行sql语句的对象. ?:表示占位符。
            String sql = "update dept set name=? where id=?";
            ps = con.prepareStatement(sql);
            //2.2 为占位符赋值: 1表示第一个占位符。 name表示占位符的值
            ps.setString(1,name);
            ps.setInt(2, id);
            //3. 开始执行sql语句.
            int row = ps.executeUpdate();//如果执行增删改的sql 直接调用该方法。返回值表示影响的行数。
            return row;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(ps!=null){
                    ps.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if(con!=null){
                    con.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //查询部门信息
    public List<Dept> findAll(){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Dept> depts=new ArrayList<>();//创建一个集合容器
        try {
            //1. 获取连接对象
            // url: 数据库服务的地址。 jdbc:mysql://IP:Port/数据库名
            // user:数据库账号  password:密码
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/qy_196", "root", "root");
            //2. 获取执行sql语句的对象. ?:表示占位符。
            String sql = "select * from dept";
            ps = con.prepareStatement(sql);
            //3. 开始执行sql语句.
            rs = ps.executeQuery();
            //4.next：把游标移动到下一行，并判断当前行是否有记录。
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Dept d=new Dept();//创建部门对象
                d.setId(id);
                d.setName(name);
                depts.add(d);
            }
            return depts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(rs!=null){
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if(ps!=null){
                    ps.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if(con!=null){
                    con.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


}


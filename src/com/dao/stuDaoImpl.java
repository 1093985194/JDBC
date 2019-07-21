package com.dao;

import com.bean.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//实现类
public class stuDaoImpl implements stuDao {
    @Override
    public void add(Student student) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql ="insert into student(id,name,sex,age)values(?,?,?,?)";
        try {
            //获取连接对象
            conn = DBUtils.getConnection();
            //获得sql的预编译对象
            ps = conn.prepareStatement(sql);
            ps.setInt(1,student.getId());
            ps.setString(2,student.getName());
            ps.setString(3,student.getSex());
            ps.setInt(4,student.getAge());
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
            throw new SQLException("添加错误");
        }finally {
            DBUtils.close(null,ps,conn);
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "delete from student where id=?";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
            throw new SQLException("删除错误");
        }finally {
            DBUtils.close(null,ps,conn);
        }
    }

    @Override
    public void update(Student student) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "update student set name=?,age=?,sex=? where id=?";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(4,student.getId());
            ps.setString(1,student.getName());
            ps.setString(3,student.getSex());
            ps.setInt(2,student.getAge());
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
            throw new SQLException("更新错误");
        }finally {
            DBUtils.close(null,ps,conn);
        }

    }

    @Override
    public Student queryOne(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Student stu = null;
        String sql = "select name,sex,age from student where id = ?";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            if(rs.next()){
                stu = new Student();
                stu.setId(id);
                stu.setName(rs.getString(1));
                stu.setSex(rs.getString(2));
                stu.setAge(rs.getInt(3));
            }

        }catch (Exception e){
            e.printStackTrace();
            throw new SQLException("根据ID查询数据失败");

        }finally {

            DBUtils.close(rs,ps,conn);
        }
        return stu;
    }

    @Override
    public List<Student> queryAll() throws SQLException  {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Student stu = null;
        List<Student> studentList = new ArrayList<>();
        String sql = "select id,name,sex,age from student";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                stu = new Student();
                stu.setId(rs.getInt(1));
                stu.setName(rs.getString(2));
                stu.setSex(rs.getString(3));
                stu.setAge(rs.getInt(4));
                studentList.add(stu);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new SQLException("根据ID查询数据失败");
        }finally {
            DBUtils.close(rs,ps,conn);
        }
        return studentList;
    }
}

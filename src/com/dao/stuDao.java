package com.dao;

import com.bean.Student;

import java.sql.SQLException;
import java.util.List;
//接口类
public interface stuDao {
    public void add(Student student) throws SQLException;
    public void delete(int id) throws SQLException;
    public void update(Student student) throws SQLException;
    public Student queryOne(int id) throws SQLException;
    List<Student> queryAll() throws SQLException;
}

package com.dao;

import com.bean.Student;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class stuTest {
    public static void main(String[] args) {
        int op = 0;
        //接口的实例化
        stuDao sdi = new stuDaoImpl();
        Scanner scanner = new Scanner(System.in);
        while (op != -1){
            System.out.println("=====学生系统=====");
            System.out.println("=====1.添加");
            System.out.println("=====2.删除");
            System.out.println("=====3.更改");
            System.out.println("=====4.查看单个学生");
            System.out.println("=====5.查看全部");
            System.out.println("====-1.退出");
            op = scanner.nextInt();
            switch (op){
                case 1:
                    System.out.println("输入要添加学生ID");
                    int id = scanner.nextInt();
                    System.out.println("输入要添加学生姓名");
                    String name = scanner.next();
                    System.out.println("输入要添加学生年龄");
                    int age = scanner.nextInt();
                    System.out.println("输入要添加学生性别");
                    String sex = scanner.next();
                    Student student1 = new Student();
                    student1.setId(id);
                    student1.setName(name);
                    student1.setSex(sex);
                    student1.setAge(age);
                    try {
                        sdi.add(student1);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    break;
                case 2:
                    System.out.println("输入要删除的学生ID");
                    int id2 = scanner.nextInt();
                    try {
                        sdi.delete(id2);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    Student student3 =new Student();
                    System.out.println("输入要修改的学生ID");
                    int id3 = scanner.nextInt();
                    System.out.println("输入修改后的姓名");
                    String name3 = scanner.next();
                    System.out.println("输入修改后的性别");
                    String sex3 = scanner.next();
                    System.out.println("输入修改后的年龄");
                    int age3 = scanner.nextInt();
                    student3.setId(id3);
                    student3.setName(name3);
                    student3.setSex(sex3);
                    student3.setAge(age3);
                    try {
                        sdi.update(student3);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    int i = 0;
                    System.out.println("输入学生ID，查看信息");
                    i = scanner.nextInt();
                    try {
                        Student student4 = sdi.queryOne(i);
                        System.out.println(student4);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    try {
                        List<Student> students = sdi.queryAll();
                        for (Student student : students){
                            System.out.println(student);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case -1:
                    System.out.println("程序结束");
                    break;
                default:
                    System.out.println("输入错误,程序结束");
                    op = -1;
            }
        }






    }
}

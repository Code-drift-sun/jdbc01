package com.aaa;

import com.aaa.demo2.dao.ClazzDao;
import com.aaa.demo2.entity.Clazz;
import com.aaa.demo2.entity.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.Reader;

public class classTest {
    @Test
    public void test1() throws Exception {
        //1.读取配置文件---解析配置文件. mybatis知道连接的数据库和账号密码。 知道映射文件中内。
        Reader reader = Resources.getResourceAsReader("config.xml");
        //2.获取SqlSessionFactory对象
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //1. 获取SqlSession对象----理解为: Connection对象。类中包含对表的各种操作方法。
        SqlSession session = sessionFactory.openSession();
        //2.获取UsersDao的代理实现类对象。
        ClazzDao mapper = session.getMapper(ClazzDao.class);
        Clazz clazz = mapper.getById(1);
        System.out.println("班级名:" + clazz.getCname() + ";对应的老师:" + clazz.getTeacher().getTname());
    }

    @Test
    public void test2() throws Exception {
//1.读取配置文件---解析配置文件. mybatis知道连接的数据库和账号密码。 知道映射文件中内。
        Reader reader = Resources.getResourceAsReader("config.xml");
//2.获取SqlSessionFactory对象
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
//1. 获取SqlSession对象----理解为: Connection对象。类中包含对表的各种操作方法。
        SqlSession session = sessionFactory.openSession();
//2.获取UsersDao的代理实现类对象。
        ClazzDao mapper = session.getMapper(ClazzDao.class);
        Clazz clazz = mapper.getById1(2);
        System.out.println("班级:" + clazz.getCname() + "下的所有学生如下:");
        for (Student s : clazz.getStudents()) {
            System.out.println("学号:" + s.getSid() + ";名字:" + s.getSname());
        }
    }
}

package com.aaa;

import com.aaa.demo2.dao.EmployeeDao;
import com.aaa.demo2.entity.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeTest {
    @Test
    public void test() throws Exception {
        //1.读取配置文件---解析配置文件. mybatis知道连接的数据库和账号密码。 知道映射文件中内。
        Reader reader = Resources.getResourceAsReader("config.xml");
        //2.获取SqlSessionFactory对象
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //1. 获取SqlSession对象----理解为: Connection对象。类中包含对表的各种操作方法。
        SqlSession session = sessionFactory.openSession();
        //2.获取UsersDao的代理实现类对象。
        EmployeeDao mapper = session.getMapper(EmployeeDao.class);
        //构造查询条件对象，可根据需要设置筛选字段（不设置则查出全部）
        Employee condition = new Employee();
        condition.setEmpName("张伟"); // 按员工名筛选
        condition.setDept("研发部");   // 按部门筛选

        //接口返回 List<Employee>，需要传条件对象
        List<Employee> list = mapper.selectByCondition(condition);
        for (Employee e : list) {
            System.out.println("员工名:" + e.getEmpName() + ", 部门:" + e.getDept());
        }
    }

    @Test
    public void test1() throws Exception {
        //1.读取配置文件---解析配置文件. mybatis知道连接的数据库和账号密码。 知道映射文件中内。
        Reader reader = Resources.getResourceAsReader("config.xml");
        //2.获取SqlSessionFactory对象
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //1. 获取SqlSession对象----理解为: Connection对象。类中包含对表的各种操作方法。
        SqlSession session = sessionFactory.openSession();
        //2.获取UsersDao的代理实现类对象。
        EmployeeDao mapper = session.getMapper(EmployeeDao.class);
        //构造查询条件对象，可根据需要设置筛选字段（不设置则查出全部）
        Employee condition = new Employee();
//        condition.setEmpName("张伟"); // 按员工名筛选
        condition.setDept("研发部");   // 按部门筛选

        //接口返回 List<Employee>，需要传条件对象
        List<Employee> list = mapper.selectByCondition1(condition);
        for (Employee e : list) {
            System.out.println("员工名:" + e.getEmpName() + ", 部门:" + e.getDept());
        }
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
        EmployeeDao mapper = session.getMapper(EmployeeDao.class);
        //构造查询条件对象，可根据需要设置筛选字段（不设置则查出全部）
        Employee emp = new Employee();
        emp.setEmpId(1);          // 定位到哪一行
        emp.setSalary(8000.0);    // 要更新的字段
        emp.setPhone("13800000001");
        int rows = mapper.update(emp);
        System.out.println("影响行数: " + rows);
        session.commit();
    }

    @Test
    public void test3() throws Exception {
        //1.读取配置文件---解析配置文件. mybatis知道连接的数据库和账号密码。 知道映射文件中内。
        Reader reader = Resources.getResourceAsReader("config.xml");
        //2.获取SqlSessionFactory对象
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //1. 获取SqlSession对象----理解为: Connection对象。类中包含对表的各种操作方法。
        SqlSession session = sessionFactory.openSession();
        //2.获取UsersDao的代理实现类对象。
        EmployeeDao mapper = session.getMapper(EmployeeDao.class);
        //构造查询条件对象，可根据需要设置筛选字段（不设置则查出全部）
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(1);
        ids.add(6);
        ids.add(9);
        int rows = mapper.batchDelete(ids);
        System.out.println("影响行数: " + rows);
        session.commit();
    }

    @Test
    public void test4() throws Exception {
        //1.读取配置文件---解析配置文件. mybatis知道连接的数据库和账号密码。 知道映射文件中内。
        Reader reader = Resources.getResourceAsReader("config.xml");
        //2.获取SqlSessionFactory对象
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //1. 获取SqlSession对象----理解为: Connection对象。类中包含对表的各种操作方法。
        SqlSession session = sessionFactory.openSession();
        //2.获取UsersDao的代理实现类对象。
        EmployeeDao mapper = session.getMapper(EmployeeDao.class);
        //构造查询条件对象，可根据需要设置筛选字段（不设置则查出全部）
        List<Employee> emps = new ArrayList<Employee>();
        emps.add(new Employee(null,"张小跑", "男", 13, "研发部", "前端开发", 8020.0, new Date(), "13800000001", "zhangsan@example.com"));
//        emps.add(new Employee(null,"李四", "女", 25, "销售部", "销售代表", 6000.0, new Date(), "13800000002", "lisi@example.com"));
        int rows = mapper.batchAdd(emps);
        System.out.println("影响行数: " + rows);
        session.commit();
    }


}

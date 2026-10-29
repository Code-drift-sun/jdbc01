package com.aaa;

import com.aaa.demo2.Util.SqlSessionFactoryUtil;
import com.aaa.demo2.dao.DeptDao;
import com.aaa.demo2.dao.EmployeeDao;
import com.aaa.demo2.entity.Dept;
import com.aaa.demo2.entity.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.Reader;

public class DeptTest {

    private SqlSession sqlSession;

    @Test
    public void testOneLevelCache() {
        SqlSessionFactory sqlSessionFactory =SqlSessionFactoryUtil.getSqlSessionFactory();
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        System.out.println("=================第1次查询===========================");
        long start1 = System.currentTimeMillis();
        DeptDao deptDao1 = sqlSession1.getMapper(DeptDao.class);
        Dept dept1 = deptDao1.queryById(1);
        if(dept1 != null) {
            System.out.println(dept1);
        }
        long end1 = System.currentTimeMillis();
        System.out.println("查询1次的时间:"+(end1-start1)+"ms");
        System.out.println("=================第2次查询，从日志可以看到，不再执行语句，说明从缓存中获取的===========================");
        long start2 = System.currentTimeMillis();
        DeptDao deptDao2 = sqlSession1.getMapper(DeptDao.class);
        Dept dept2 = deptDao2.queryById(1);
        if(dept2 != null) {
            System.out.println(dept2);
        }
        long end2 = System.currentTimeMillis();
        System.out.println("查询2次的时间:"+(end2-start2)+"ms");
        System.out.println("=================第3次查询，从日志可以看到，不再执行语句，说明从缓存中获取的===========================");
        DeptDao deptDao3 = sqlSession1.getMapper(DeptDao.class);
        Dept dept3 = deptDao3.queryById(1);
        if(dept3 != null) {
            System.out.println(dept3);
        }
        sqlSession1.close();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        System.out.println("=================第4次查询======使用新的sqlSession，缓存没有了，又会从数据库获取数据，会执行语句=====================");
        DeptDao deptDao4 = sqlSession2.getMapper(DeptDao.class);
        Dept dept4 = deptDao4.queryById(1);
        if(dept4 != null) {
            System.out.println(dept4);
        }
        System.out.println("=================第5次查询====== 重复使用了sqlSession2，缓存又有了，不会执行语句====================");
        DeptDao deptDao5 = sqlSession2.getMapper(DeptDao.class);
        Dept dept5 = deptDao5.queryById(1);
        if(dept5 != null) {
            System.out.println(dept5);
        }
        System.out.println("=================在第5次和第6次查询中执行一个更新==========================");
        deptDao5.update("测试部门", 1);
        sqlSession2.commit();
        System.out.println("=================第6次查询====== 上面执行了更新，会清空缓存，再次执行语句====================");
        DeptDao deptDao6 = sqlSession2.getMapper(DeptDao.class);
        Dept dept6 = deptDao6.queryById(1);
        if(dept6 != null) {
            System.out.println(dept6);
        }
        System.out.println("=================第7次查询====== 不会执行语句====================");
        DeptDao deptDao7 = sqlSession2.getMapper(DeptDao.class);
        Dept dept7 = deptDao7.queryById(1);
        if(dept7 != null) {
            System.out.println(dept7);
        }
        System.out.println("=================第7次和第8次查询中，执行清空缓存===================");
        sqlSession2.clearCache();
        System.out.println("=================第8次查询====== 因为缓存被清空，会执行语句====================");
        DeptDao deptDao8 = sqlSession2.getMapper(DeptDao.class);
        Dept dept8 = deptDao8.queryById(1);
        if(dept8 != null) {
            System.out.println(dept8);
        }
        System.out.println("=================第9次查询======不会执行语句====================");
        DeptDao deptDao9 = sqlSession2.getMapper(DeptDao.class);
        Dept dept9 = deptDao9.queryById(1);
        if(dept9 != null) {
            System.out.println(dept9);
        }
        sqlSession2.close();
    }

    @Test
    public void test2() throws Exception {
        //1.读取配置文件---解析配置文件. mybatis知道连接的数据库和账号密码。 知道映射文件中内。
        Reader reader = Resources.getResourceAsReader("config.xml");
        //2.获取SqlSessionFactory对象
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //1. 获取SqlSession对象----理解为: Connection对象。类中包含对表的各种操作方法。
        SqlSession session = sessionFactory.openSession();
        System.out.println("第一次查询数据库 id=2的数据内容----执行sql语句。");
        EmployeeDao mapper1 = session.getMapper(EmployeeDao.class);
        Employee employee1 = mapper1.selectById(2);
        System.out.println(employee1);

        System.out.println("第二次查询数据库，id=2的数据内容----没有执行sql语句。");
        EmployeeDao mapper2 = session.getMapper(EmployeeDao.class);
        Employee employee2 = mapper2.selectById(2);
        System.out.println(employee2);

        session.close();//关闭session 一级缓存就失效了
        SqlSession session2 = sessionFactory.openSession();
        System.out.println("第三次查询数据库，id=2的数据内容----没有执行sql---从二级缓存获取。");
        EmployeeDao mapper3 = session2.getMapper(EmployeeDao.class);
        Employee employee3 = mapper3.selectById(2);
        System.out.println(employee3);


        System.out.println("第四次查询数据库，id=2的数据内容----没有执行sql---从二级缓存。");
        EmployeeDao mapper4 = session2.getMapper(EmployeeDao.class);
        Employee employee4 = mapper4.selectById(2);
        System.out.println(employee4);

        System.out.println("第五次查询数据库，id=2的数据内容----没有执行sql---从二级缓存。");
        EmployeeDao mapper5 = session2.getMapper(EmployeeDao.class);
        Employee employee5 = mapper5.selectById(2);
        System.out.println(employee5);

    }


}

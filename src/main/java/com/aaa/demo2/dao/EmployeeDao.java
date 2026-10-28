package com.aaa.demo2.dao;

import com.aaa.demo2.entity.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeDao {
    public List<Employee> selectByCondition(Employee employee);
    public List<Employee> selectByCondition1(Employee employee);
    public List<Employee> selectByCondition2(Employee employee);
    public int update(Employee emp);

    public int batchDelete(@Param("ids")List<Integer> ids);

    public int batchAdd(@Param("emps")List<Employee> emps);
}

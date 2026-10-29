package com.aaa.demo2.dao;

import com.aaa.demo2.entity.Dept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeptDao {

    //根据部门号查询部门信息
    public Dept queryById(Integer id);
    public List<Dept> getAll();
    public int insert(String name);
    public int update(@Param("name") String name, @Param("id") int id);
    public int delete(int id);
}

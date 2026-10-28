package com.aaa.demo2.dao;

import com.aaa.demo2.entity.Clazz;

public interface ClazzDao {
    //根据班级号查询班级信息以及对应的老师信息
    public Clazz getById(Integer cid);

    public Clazz getById1(Integer cid);


}

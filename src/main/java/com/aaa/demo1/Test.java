package com.aaa.demo1;

import com.aaa.demo1.dao.DeptDao;
import com.aaa.demo1.entity.Dept;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        DeptDao ddao=new DeptDao();
//        int  d1=ddao.insert("张文隽");
        List<Dept> list=ddao.findAll();
        //打印部门信息
        for(Dept d:list){
            System.out.println(d.getId()+"\t"+d.getName());
        }
    }
}

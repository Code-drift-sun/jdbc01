package com.aaa.demo2.dao;

import com.aaa.demo2.entity.Orders;

import java.util.List;

public interface OrdersDao {

    public Orders getByNo(String no);

    public List<Orders> getAll();
}

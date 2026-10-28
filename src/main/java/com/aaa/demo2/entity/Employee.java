package com.aaa.demo2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private Integer empId;
    private String empName;
    private String gender;
    private Integer age;
    private String dept;
    private String job;
    private Double salary;
    private Date hiredate;
    private String phone;
    private String email;
}

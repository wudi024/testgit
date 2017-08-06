package com.ck.aopTest.dao.impl;

import org.springframework.stereotype.Repository;

import com.ck.aopTest.dao.MyAopDao;

@Repository
public class MyAopDaoImpl implements MyAopDao
{

	public void getUser() {
		// TODO Auto-generated method stub
		System.out.println("这是我的aop测试dao方法一");
	}

	public void getName(String user) {
		// TODO Auto-generated method stub
		System.out.println("这是我的aop测试dao方法二");
	}

	public void addUser() {
		// TODO Auto-generated method stub
		System.out.println("这是我的aop测试dao方法三");
	}


}

package com.ck.transactionTest.service;

import com.ck.transactionTest.bean.book.Book;

public interface BaseSevice
{

	public void insert(Book record,String author , boolean flag) throws Exception;
	
	public int selectMaxKey() throws Exception;
}

package com.ck.transactionTest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.ck.transactionTest.bean.book.Book;
import com.ck.transactionTest.dao.book.BookMapper;
import com.ck.transactionTest.exception.UserAccountException;
import com.ck.transactionTest.service.BaseSevice;

@Service("baseSevice")
public class BaseSeviceImpl implements BaseSevice
{
	@Autowired
	private BookMapper bookMapper;

	@Transactional(isolation=Isolation.READ_COMMITTED,rollbackFor=Exception.class,noRollbackFor=UserAccountException.class)
	public void insert(Book record, String author ,boolean flag) throws Exception {
		bookMapper.insert(record);
		// 如果flag 为 true ，抛出异常
		if (flag)
		{
			throw new UserAccountException("has exception!!!");
		}
		
		int max = bookMapper.selectMaxKey();
		Book newbook = bookMapper.selectByPrimaryKey(max);
		newbook.setAuthor(author);//更新作者
		bookMapper.updateByPrimaryKey(newbook);
		record = newbook;
	}

	public int selectMaxKey() throws Exception {
		int num = bookMapper.selectMaxKey();
		return num;
	}

}

package com.ck.transactionTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.ck.transactionTest.bean.book.Book;
import com.ck.transactionTest.service.BaseSevice;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mvc.xml","classpath:spring-mybatis.xml"})
public class TransTest extends TestCase
{
	@Autowired
	private BaseSevice baseSevice;
	
	@Autowired
	TransactionTemplate transactionTemplate;
	
//	@Test
	public void test() throws Exception{
        
        int maxId = baseSevice.selectMaxKey();
        System.out.println("before transaction maxId: "+maxId);
        System.out.println("transaction....");
        Book record = new Book();
        record.setAuthor("mert Caliskan");
        record.setInventory(new Random().nextInt(100));
        record.setPrice(59.80F);
        record.setPublisher("清华大学出版社");
        record.setTitle("Spring 入门经典"+new Random().nextInt(10));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");     
        record.setCreatetime(formatter.format(new Date()));
        baseSevice.insert(record, "锅铲",true);
        int maxId2 = baseSevice.selectMaxKey();
        System.out.println("after transaction maxId: "+maxId2);
	}
	
	
	@Test
	public void test2() throws Exception{
        
        int maxId = baseSevice.selectMaxKey();
        System.out.println("before transaction maxId: "+maxId);
        System.out.println("transaction....");
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                try{
                	Book record = new Book();
                    record.setAuthor("mert Caliskan");
                    record.setInventory(new Random().nextInt(100));
                    record.setPrice(59.80F);
                    record.setPublisher("清华大学出版社");
                    record.setTitle("Spring 入门经典"+new Random().nextInt(10));
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");     
                    record.setCreatetime(formatter.format(new Date()));
                    baseSevice.insert(record, "锅铲",true);
                } catch (Exception e){
                    //对于抛出Exception类型的异常且需要回滚时,需要捕获异常并通过调用status对象的setRollbackOnly()方法告知事务管理器当前事务需要回滚
                    status.setRollbackOnly();
                    e.printStackTrace();
                }
           }
        });
        
        int maxId2 = baseSevice.selectMaxKey();
        System.out.println("after transaction maxId: "+maxId2);
	}
	
	
}

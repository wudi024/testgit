package com.ck.transactionDemo;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ck.transactionDemo.service.AccountService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext3.xml"})
public class SpringTest {
    
    @Resource(name="accountService")
    private AccountService accountService;

    @Test
    public void demo1() throws Exception{
        accountService.transferA("aaa", "bbb", 200d,true);
    }
}

package com.ck.transactionDemo.impl;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import com.ck.transactionDemo.dao.AccountDao;
import com.ck.transactionDemo.dao.AccountDao_h;
import com.ck.transactionDemo.service.AccountService;
import com.ck.transactionDemo.service.AccountServiceP;

public class AccountServiceImplP implements AccountServiceP {
    // 注入DAO类
    private AccountDao accountDao;
    
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }
    
//    private AccountDao_h accountDao;
//
//    public void setAccountDao(AccountDao_h accountDao) {
//        this.accountDao = accountDao;
//    }

    @Override
    public void transfer(String out, String in, Double money, boolean flag) throws Exception {
        accountDao.outMoney(out, money);
        if (flag) {
            System.out.println(1 / 0);
//            throw new Exception();
        }
        accountDao.inMoney(in, money);
    }

    TransactionTemplate transactionTemplate;

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

//    @Override
////    @Transactional(propagation=Propagation.)
//    public void transfer(final String out, final String in, final Double money, final boolean flag) throws Exception {
//        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
//
//            @Override
//            protected void doInTransactionWithoutResult(TransactionStatus status) {
//
//                accountDao.outMoney(out, money);
////                 try {
////                 Thread.sleep(2000);
////                 }
////                 catch (InterruptedException e) {
////                 e.printStackTrace();
////                 }
//                    if (flag) {
////                        System.out.println(1 / 0);
//                        // throw new NullPointerException("has NullPointerException!!! has rollback test!!!");
//                        // throw new ClassCastException("has ClassCastException!!! has rollback test!!!");
////                        throw new RuntimeException("has RuntimeException!!! has rollback test!!!");// 如果不指定rollbackexception，是不会捕捉回滚的
//                        throw new Exception();
//                }
//
//                accountDao.inMoney(in, money);
//
//            }
//        });
//    }

}

package com.ck.transactionDemo.service;

public interface AccountService {

    /**
     * 
     * @param out   :转出账号
     * @param in    ：转入账号
     * @param money ：转账金额
     * @throws  
     */
    public void transfer(String out,String in,Double money,boolean flag) throws Exception;
    
    public void transferA(String out,String in,Double money,boolean flag) throws Exception;
    
}

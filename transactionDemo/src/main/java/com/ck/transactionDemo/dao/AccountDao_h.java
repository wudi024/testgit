package com.ck.transactionDemo.dao;

public interface AccountDao_h {
    /**
     * @param out   :转出账号
     * @param money ：转出金额
     */
    public Object outMoney(String out,Double money);
    /**
     * @param in    :转入账号
     * @param money :转入金额
     */
    public Object inMoney(String in,Double money);

}

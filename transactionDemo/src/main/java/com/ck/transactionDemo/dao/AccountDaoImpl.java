package com.ck.transactionDemo.dao;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class AccountDaoImpl extends JdbcDaoSupport implements AccountDao {

    public void outMoney(String out, Double money) {
        String sql = "update accountdemo set money = money - ? where name = ?";
        this.getJdbcTemplate().update(sql, money, out);
    }

    public void inMoney(String in, Double money) {
        String sql = "update accountdemo set money = money + ? where name = ?";
        this.getJdbcTemplate().update(sql, money, in);

    }

    @Override
    public void getMoney() {
        String sql = "select money from accountdemo where name = 'aaa' ";
        this.getJdbcTemplate().queryForMap(sql);

    }

}

package com.ck.transactionDemo.dao;

import java.io.Serializable;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


public class AccountDaoImpl_h extends HibernateDaoSupport implements AccountDao_h {

    public Object outMoney(final String out, final Double money) {
        final String sql = "update accountdemo set money = money - ? where name = ?";
//        this.getHibernateTemplate().bulkUpdate(sql, new Object[]{money, out}); 
        return getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                SQLQuery query = session.createSQLQuery(sql);
                query.setDouble(0, money);
                query.setString(1, out);

                return query.executeUpdate();
            }
        });
    }

    public Object inMoney(final String in, final Double money) {
        final String sql = "update accountdemo set money = money + ? where name = ?";
        return getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                SQLQuery query = session.createSQLQuery(sql);
                query.setDouble(0, money);
                query.setString(1, in);

                return query.executeUpdate();
            }
        });

        
    }

}

package com.ck.transactionDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class test {

    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            String url="jdbc:oracle:thin:@192.168.5.100:1530:billdb";
            String username="ddp";
            String password="ddp";
            conn = DriverManager.getConnection(url, username, password);
            conn.setAutoCommit(false);  //将自动提交设置为false   
//          conn.begin();                 
//          执行CRUD操作  //业务处理
            conn.commit();      //当CRUD操作成功后手动提交  
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            conn.rollback();
        }
        finally {
            conn.close();
        }
        
    }
}

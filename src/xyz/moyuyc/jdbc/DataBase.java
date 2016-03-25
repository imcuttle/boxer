package xyz.moyuyc.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Yc on 2016/3/24 for boxer.
 */
public class DataBase {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/boxer";
    private static final String USER = "root";
    private static final String PWD = "110114";
    protected static final String TABLE_USER = "users";
    protected static final String TABLE_POINT = "usersPoint";
    protected static final String TABLE_MSG = "msgs";

    protected Connection conn;
    protected Connection getConnection(){
        try {
            if(conn==null||conn.isClosed()){
                try {
                    Class.forName(DRIVER);
                    conn = DriverManager.getConnection(URL,USER,PWD);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    protected void close(){
        if(conn!=null)
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}

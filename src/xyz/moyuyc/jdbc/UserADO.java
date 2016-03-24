package xyz.moyuyc.jdbc;

import xyz.moyuyc.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Yc on 2016/3/24 for boxer.
 */
public class UserADO extends DataBase{
    public boolean addUser(User user){
        Connection conn = getConnection();
        PreparedStatement ps = null;
        boolean rlt = false;
        try {
            ps = conn.prepareStatement("insert into " + TABLE_USER + " values (?,?,CURRENT_DATE());");
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            rlt = ps.executeUpdate()!=0;
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            return rlt;
        }
    }

    public boolean checkUser(User user){
        Connection conn = getConnection();
        PreparedStatement ps = null;
        boolean rlt = false;
        try {
            ps = conn.prepareStatement("SELECT * FROM "+TABLE_USER+" WHERE username=? AND password=?");
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            ResultSet rs = ps.executeQuery();
            rlt = rs.next();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            return rlt;
        }
    }
}

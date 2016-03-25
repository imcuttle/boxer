package xyz.moyuyc.jdbc;

import xyz.moyuyc.entity.UserPoint;
import xyz.moyuyc.entity.UserPoints;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Yc on 2016/3/24 for boxer.
 */
public class UserPointADO extends DataBase {
    public boolean addUserPoint(UserPoint userPoint){
        Connection conn = getConnection();
        PreparedStatement ps = null;
        boolean rlt = false;
        try {
            ps = conn.prepareStatement("insert into " + TABLE_POINT+ " values (?,?,?,now());");
            ps.setString(1,userPoint.getUsername());
            ps.setInt(2, userPoint.getPoint());
            ps.setInt(3,userPoint.getStep());
            rlt = ps.executeUpdate()!=0;
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            return rlt;
        }
    }

    public boolean updateUserPoint(UserPoint userPoint){
        Connection conn = getConnection();
        PreparedStatement ps = null;
        boolean rlt = false;
        try {
            ps = conn.prepareStatement("UPDATE " + TABLE_POINT+ " set point=?,step=?,time=now() WHERE username=? AND " +
                    "(POINT<? OR (POINT=? AND step<?))");
            ps.setString(3,userPoint.getUsername());
            ps.setInt(1,userPoint.getPoint());
            ps.setInt(2,userPoint.getStep());
            ps.setInt(4,userPoint.getPoint());
            ps.setInt(5,userPoint.getPoint());
            ps.setInt(6,userPoint.getStep());
            rlt = ps.executeUpdate()!=0;
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            return rlt;
        }
    }

    public UserPoint getUserPoints(String username){
        Connection conn = getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("SELECT * FROM " + TABLE_POINT+ " WHERE username=?;");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                return new UserPoint(rs.getString(1),rs.getInt(2),rs.getInt(3),rs.getTimestamp(4));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserPoints getTopUserPoints(int num){
        Connection conn = getConnection();
        PreparedStatement ps = null;
        UserPoints userPoints = new UserPoints(new ArrayList<>());
        try {
            ps = conn.prepareStatement("SELECT * FROM " + TABLE_POINT+ " ORDER BY point DESC,step limit ?;");
            ps.setInt(1, num);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                userPoints.add(new UserPoint(rs.getString(1),rs.getInt(2),rs.getInt(3),rs.getTimestamp(4)));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            return userPoints;
        }
    }
}

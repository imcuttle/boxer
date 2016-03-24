package xyz.moyuyc.jdbc;

import xyz.moyuyc.entity.UserMsg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yc on 2016/3/24 for boxer.
 */
public class UserMsgADO extends DataBase{
    public boolean addMsg(UserMsg msg){
        Connection conn = getConnection();
        PreparedStatement ps = null;
        boolean rlt = false;
        try {
            ps = conn.prepareStatement("insert into " + TABLE_MSG + " values (?,?,NOW());");
            ps.setString(1,msg.getUsername());
            ps.setString(2,msg.getContent());
            rlt = ps.executeUpdate()!=0;
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            return rlt;
        }
    }
    public List<UserMsg> getRecentMsgs(int num){
        Connection conn = getConnection();
        PreparedStatement ps = null;
        List<UserMsg> list = new ArrayList<UserMsg>();
        try {
            ps = conn.prepareStatement("SELECT * FROM " + TABLE_MSG + " ORDER BY time DESC limit n");
            ps.setInt(1, num);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                list.add(new UserMsg(rs.getString(1),rs.getString(2),rs.getTimestamp(3)));
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            return list;
        }
    }
    public List<UserMsg> getAllMsgs(){
        Connection conn = getConnection();
        PreparedStatement ps = null;
        List<UserMsg> list = new ArrayList<UserMsg>();
        try {
            ps = conn.prepareStatement("SELECT * FROM " + TABLE_MSG + " ORDER BY time DESC");
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                list.add(new UserMsg(rs.getString(1),rs.getString(2),rs.getTimestamp(3)));
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            return list;
        }
    }

}

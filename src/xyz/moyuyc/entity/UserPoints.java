package xyz.moyuyc.entity;

import java.util.List;

/**
 * Created by Yc on 2016/3/24 for boxer.
 */
public class UserPoints {
    private List<UserPoint> userPoints;

    public UserPoints(List<UserPoint> userPoints) {
        this.userPoints = userPoints;
    }

    public UserPoints add(UserPoint userPoint){
        userPoints.add(userPoint);
        return this;
    }
    public int size(){return userPoints.size();}
    public UserPoint get(int i){return userPoints.get(i);}
}

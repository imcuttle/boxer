package xyz.moyuyc.entity;

import java.util.Date;

/**
 * Created by Yc on 2016/3/24 for boxer.
 */
public class UserPoint {
    private String username;
    private int point;
    private int step;
    private Date date;

    public void setStep(int step) {
        this.step = step;
    }

    public int getStep() {
        return step;
    }

    public UserPoint(String username, int point,int step) {
        this.username = username;
        this.point = point;
        this.step = step;
    }
    public UserPoint(String username, int point,int step, Date date) {
        this(username,point,step);
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserPoint)) return false;

        UserPoint userPoint = (UserPoint) o;

        if (username != null ? !username.equals(userPoint.username) : userPoint.username != null) return false;
        return !(date != null ? !date.equals(userPoint.date) : userPoint.date != null);

    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

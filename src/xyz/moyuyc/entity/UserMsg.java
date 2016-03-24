package xyz.moyuyc.entity;

import java.util.Date;

/**
 * Created by Yc on 2016/3/24 for boxer.
 */
public class UserMsg {
    private String username;
    private String content;
    private Date time;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public UserMsg(String username, String content, Date time) {

        this.username = username;
        this.content = content;
        this.time = time;
    }

    public UserMsg(String username, String content) {

        this.username = username;
        this.content = content;
    }
}

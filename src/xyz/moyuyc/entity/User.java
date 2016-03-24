package xyz.moyuyc.entity;

import java.util.Date;

/**
 * Created by Yc on 2016/3/24 for boxer.
 */
public class User {
    private String username;
    private String password;
    private Date registerDate;

    public User(String username, String password,Date date) {
        this.username = username;
        this.password = password;
        this.registerDate = date;
    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return username.equals(user.username);

    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }
}

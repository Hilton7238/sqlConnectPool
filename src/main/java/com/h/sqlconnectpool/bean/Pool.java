package com.h.sqlconnectpool.bean;

public class Pool {
    private int num;
    private String url;
    private String userName;
    private String password;

    public Pool() {
        this.num = 10;
    }

    public int getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = Integer.parseInt(num);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Pool{" +
                "num=" + num +
                ", url='" + url + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

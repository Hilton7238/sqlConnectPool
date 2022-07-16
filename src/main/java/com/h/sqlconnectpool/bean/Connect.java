package com.h.sqlconnectpool.bean;

import java.sql.Connection;

public class Connect {
    private Connection connection;
    private int num;

    public Connect() {
    }

    public Connect(Connection connection) {
        this.connection = connection;
        this.num = 0;
    }

    public Connection getConnection() {
        this.num++;
        return connection;
    }

    public Connection getConnection(String s) {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
        this.num = 0;
    }

    public int getNum() {
        return num;
    }

}

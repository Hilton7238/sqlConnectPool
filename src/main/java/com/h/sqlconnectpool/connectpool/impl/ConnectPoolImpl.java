package com.h.sqlconnectpool.connectpool.impl;

import com.h.sqlconnectpool.bean.Connect;
import com.h.sqlconnectpool.bean.Pool;
import com.h.sqlconnectpool.connectpool.ConnectPool;
import com.h.sqlconnectpool.load.LoadDisposition;
import com.h.sqlconnectpool.load.impl.LoadDispositionImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectPoolImpl implements ConnectPool {
    private List<Connect> connections;
    private int index = 0;
    private Pool pool;

    public ConnectPoolImpl() {
        this.init();
    }

    private void init() {
        if (connections != null) return;
        LoadDisposition loadDisposition = new LoadDispositionImpl();
        try {
            pool = loadDisposition.load();
            int num = pool.getNum();
            connections = new ArrayList<>(num);
            for (int i = 0; i < num; i++) {
                connections.add(new Connect(DriverManager.getConnection(pool.getUrl(), pool.getUserName(), pool.getPassword())));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    synchronized public void closeAll() {
        for (Connect connect : connections) {
            try {
                connect.getConnection().close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    synchronized public Connect getConnect() {
        Connect connect = connections.get(index);
        index++;
        if (index == connections.size())
            index = 0;
        return connectAgain(connect);
    }

    private Connect connectAgain(Connect connect) {
        try {
            Connection connection = connect.getConnection("test connect");
            if (!connection.isValid(5)) {
                connect.setConnection(DriverManager.getConnection(pool.getUrl(), pool.getUserName(), pool.getPassword()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connect;
    }

}

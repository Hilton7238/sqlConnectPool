package com.h.sqlconnectpool.factory;

import com.h.sqlconnectpool.connectpool.ConnectPool;
import com.h.sqlconnectpool.connectpool.impl.ConnectPoolImpl;

public class ConnectPoolFactory {
    private static ConnectPool connectPool;

    synchronized public static ConnectPool getConnectPool() {
        if (connectPool == null)
            connectPool = new ConnectPoolImpl();
        return connectPool;
    }
}

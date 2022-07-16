package com.h.sqlconnectpool.connectpool;

import com.h.sqlconnectpool.bean.Connect;

public interface ConnectPool {
    //load param init max number of connect


    public void closeAll();

    public Connect getConnect();

}

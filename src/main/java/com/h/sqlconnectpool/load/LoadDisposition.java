package com.h.sqlconnectpool.load;

import com.h.sqlconnectpool.bean.Pool;
import com.h.sqlconnectpool.exception.PoolException;

import java.io.IOException;

public interface LoadDisposition {
    public Pool load() throws PoolException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException;
}

package com.h.sqlconnectpool.load.impl;

import com.h.sqlconnectpool.bean.Pool;
import com.h.sqlconnectpool.load.LoadDisposition;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class LoadDispositionImpl implements LoadDisposition {
    private final String PREFIX = "pool";
    private final String FILENAME = "pool";
    private final String[] KEYS = {"pool.num", "pool.jdbc.url", "pool.jdbc.userName", "pool.jdbc.password"};

    public LoadDispositionImpl() {
    }

    @Override
    public Pool load() throws IOException {
        File file = new File(Thread.currentThread().getContextClassLoader().getResource("").getPath() + FILENAME + ".yml");
        Pool pool = new Pool();
        Class poolClass = pool.getClass();
        if (!file.exists()) {
            file = new File(Thread.currentThread().getContextClassLoader().getResource("").getPath() + FILENAME + ".properties");
            if (file.exists()) {
                Properties properties = new Properties();
                properties.load(new FileInputStream(file));
                for (int i = 0; i < KEYS.length; i++) {
                    StringBuilder s = new StringBuilder(KEYS[i]);
                    if (s == null || s.equals(""))
                        continue;
                    StringBuilder suffix = new StringBuilder(s.substring(s.lastIndexOf(".") + 1, s.length()));
                    try {
                        poolClass.getMethod("set" + suffix.substring(0, 1).toUpperCase() + suffix.substring(1, suffix.length()), String.class).invoke(pool, (String) properties.get(KEYS[i]));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        } else {
            Yaml yaml = new Yaml();
            Map<String, Object> properties = yaml.load(new FileInputStream(file));
            if (properties != null) {
                Map<String, Object> poolProperties = (HashMap<String, Object>) properties.get("pool");
                this.setProperties(poolProperties, pool);
            }
        }
        return pool;
    }

    private void setProperties(Map<String, Object> properties, Object obj) {
        Class<?> aClass = obj.getClass();
        properties.forEach((k, v) -> {
            try {
                if (v instanceof Map)
                    this.setProperties((Map<String, Object>) v, obj);
                else
                    aClass.getMethod("set" + k.substring(0, 1).toUpperCase() + k.substring(1, k.length()), String.class).invoke(obj, v.toString());
            } catch (Exception e) {
                System.out.println(e);
            }
        });
    }
}

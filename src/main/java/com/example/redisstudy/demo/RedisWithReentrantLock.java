package com.example.redisstudy.demo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.Map;

/**
 * @author aaron
 * @since 2021-03-02
 */
public class RedisWithReentrantLock {

    private ThreadLocal<Map<String,Integer>> lockers = new ThreadLocal<>();

    private Jedis jedis;

    public RedisWithReentrantLock(Jedis jedis){
        this.jedis=jedis;
    }

    private boolean _lock(String key){
        SetParams setParams = new SetParams();
        return jedis.set(key,"") != null;
    }
}

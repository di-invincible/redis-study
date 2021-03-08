package com.example.redisstudy.demo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

/**
 * 使用zset 实现简单限流
 * @author aaron
 * @since 2021-03-08
 */
public class SimpleRateLimiter {

    private Jedis jedis;
    public SimpleRateLimiter(Jedis jedis){
        this.jedis=jedis;
    }
    public boolean isActionAllowed(String userId,String actionKey,int period,int maxCount){
        String key = String.format("hist:%s:%s",userId,actionKey);
        System.out.println("============:"+key);
        long nowTs = System.currentTimeMillis();
        Pipeline pipe = jedis.pipelined();
        pipe.multi();
        pipe.zadd(key,nowTs,""+nowTs);
        pipe.zremrangeByScore(key,0,nowTs-period*1000);
        Response<Long> count = pipe.zcard(key);
        pipe.expire(key,period+1);
        pipe.exec();
        pipe.close();
        return count.get() <= maxCount;
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        SimpleRateLimiter limiter = new SimpleRateLimiter(jedis);
        for (int i = 0; i < 20; i++) {
            System.out.println(limiter.isActionAllowed("aaron","reply",60,5));
        }
    }
}

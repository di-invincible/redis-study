package com.example.redisstudy.demo;

import org.redisson.client.RedisClient;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

/**
 * 初识 pipeline
 * @author aaron
 * @since 2021-03-11
 */
public class PipeLineDemo {

    private Jedis jedis;

    public PipeLineDemo(Jedis jedis){
        this.jedis=jedis;
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        PipeLineDemo pipeLineDemo = new PipeLineDemo(jedis);

        long setStart = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            jedis.set("key_" + i, String.valueOf(i));
        }
        long setEnd = System.currentTimeMillis();
        System.out.println("非pipeline操作10000次字符串数据类型set写入，耗时：" + (setEnd - setStart) + "毫秒");

        long pipelineStart = System.currentTimeMillis();
        Pipeline pipeline = jedis.pipelined();
        for (int i = 0; i < 10000; i++) {
            pipeline.set("key_" + i, String.valueOf(i));
        }
        pipeline.sync();
        long pipelineEnd = System.currentTimeMillis();
        System.out.println("pipeline操作10000次字符串数据类型set写入，耗时：" + (pipelineEnd - pipelineStart) + "毫秒");
    }
}

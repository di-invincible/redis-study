package com.example.redisstudy.demo;

import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import redis.clients.jedis.Client;

/**
 * @author aaron
 * @since 2021-03-08
 */
public class BloomTest {
//    public static void main(String[] args) {
//        Client client = new Client();
//        client.del("codehole");
//        for (int i = 0; i < 100000; i++) {
//            client.sadd("codehole","user"+i);
//        }
//    }


//    public static void main(String[] args) {
//        Config config = new Config();
//        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
//
//        //构造Redisson
//        RedissonClient redisson = Redisson.create(config);
//
//
//        RBloomFilter<String> bloomFilter = redisson.getBloomFilter("phoneList");
//        //初始化布隆过滤器:预计元素100000000L,误差率为3%
//        bloomFilter.tryInit(100000000L,0.03);
//        bloomFilter.add("10086");
//
//        //判断下面号码是否在布隆过滤器中
//        System.out.println(bloomFilter.contains("123456"));//false
//        System.out.println(bloomFilter.contains("10086"));//true
//
//    }


//    public static void main(String[] args) {
//        Config config = new Config();
//        config.useSingleServer().setAddress("redis://192.168.1.39:6379");
//        //构造Redisson
//        RedissonClient redisson = Redisson.create(config);
//        RBloomFilter<String> bloomFilter = redisson.getBloomFilter("test");
//        //初始化布隆过滤器:预计元素100000000L,误差率为3%
//        bloomFilter.tryInit(10000L, 0);
//        for (int i = 0; i < 10000; i++) {
//            bloomFilter.add("user" + i);
//            boolean ret = bloomFilter.contains("user" +i);
//            if (!ret) {
//                System.out.println(i);
//            }
//        }
//    }

 //6026 误判了
    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.1.39:6379");
        //构造Redisson
        RedissonClient redisson = Redisson.create(config);
        RBloomFilter<String> bloomFilter = redisson.getBloomFilter("test");
        bloomFilter.delete();
        //初始化布隆过滤器:预计元素100000000L,误差率为3%
        bloomFilter.tryInit(1000L, 0);
        for (int i = 0; i < 10000; i++) {
            bloomFilter.add("user" + i);
            boolean ret = bloomFilter.contains("user" +(i+1));
            if (ret) {
                System.out.println(i);
                break;
            }
        }
    }



//    public static void main(String[] args) {
//        Config config = new Config();
//        config.useSingleServer().setAddress("redis://192.168.1.39:6379");
//
//        //构造Redisson
//        RedissonClient redisson = Redisson.create(config);
//
//        RBloomFilter<String> bloomFilter = redisson.getBloomFilter("test");
//        boolean ret = bloomFilter.contains("user9999");
//        System.out.println(ret);
//    }

}

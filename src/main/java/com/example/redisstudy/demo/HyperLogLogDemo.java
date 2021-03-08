package com.example.redisstudy.demo;

import redis.clients.jedis.Jedis;

/**
 * PV（Page View） 即页面浏览量或点击量，用户每1次对网站中的每个网页访问均被记录1个PV。用户对同一页面的多次访问，访问量累计，用以衡量网站用户访问的网页数量。
 *
 * UV（Unique visitor） 是指通过互联网访问、浏览这个网页的自然人。访问您网站的一台电脑客户端为一个访客。00:00-24:00内相同的客户端只被计算一次。
 * @author aaron
 * @since 2021-03-06
 */
public class HyperLogLogDemo {


    private Jedis jedis;

    private HyperLogLogDemo(Jedis jedis) {
        this.jedis = jedis;
    }


    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        for (int i = 0; i <100000 ; i++) {
            jedis.pfadd("codehole","user"+i);
//            long total = jedis.pfcount("codehole");
//            if(total != i+1){
//                System.out.printf("%d %d\n",total,i+1);
//                break;
//            }
        }
        long total = jedis.pfcount("codehole");
        System.out.printf("%d %d\n",100000,total);
        jedis.close();
    }
}

package com.example.redisstudy.demo;

import redis.clients.jedis.Jedis;

import java.time.LocalDateTime;

/**
 * 位图 实现 签到功能
 *
 * @author aaron
 * @since 2021-03-06
 */
public class BitmapSignIn {

    private Jedis jedis;

    private BitmapSignIn(Jedis jedis) {
        this.jedis = jedis;
    }


    /**
     * 签到
     *
     * @param now 当前日期
     */
    public void signIn(String key, LocalDateTime now) {
        int day = now.getDayOfYear();
        jedis.setbit(key, day, true);
    }

    public void signIn(String key, int day) {
        int yearDay = 365;
        if (day > yearDay) {
            return;
        }
        jedis.setbit(key, day, true);
    }


    /**
     * 模拟签到
     */
    public void signInSimulation(String user) {
        for (int i = 1; i < 366; i++) {
            if (Math.random() > 0.5) {
                signIn(user, i);
            }
        }
    }

    /**
     * 当天是否登录
     *
     * @return int
     */
    public boolean dayIsLoggedIn(String key, LocalDateTime now) {
        int day = now.getDayOfYear();
        return jedis.getbit(key, day);
    }

    /**
     * 获取登录天数
     */
    public Long getSignInDays(String use) {
        return jedis.bitcount(use);
    }


    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        BitmapSignIn bitmapSignIn = new BitmapSignIn(jedis);


        Thread producer = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    bitmapSignIn.signInSimulation("user:" + i);
                }
            }
        };


        Thread consumer = new Thread(){
            @Override
            public void run(){
                for (int i = 0; i < 10; i++) {
                  Long days =   bitmapSignIn.getSignInDays("user:" + i);
                    System.out.println("user"+i+"登录了"+days+"天");
                }
            }
        };



        try{
            producer.start();
            producer.join();
            Thread.sleep(9000);
            consumer.start();
            consumer.interrupt();
            consumer.join();
        }catch (InterruptedException e){}

    }


}

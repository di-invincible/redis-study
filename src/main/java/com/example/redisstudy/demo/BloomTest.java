package com.example.redisstudy.demo;

import redis.clients.jedis.Client;

/**
 * @author aaron
 * @since 2021-03-08
 */
public class BloomTest {
    public static void main(String[] args) {
        Client client = new Client();
        client.del("codehole");
        for (int i = 0; i < 100000; i++) {
            client.sadd("codehole","user"+i);
        }
    }
}

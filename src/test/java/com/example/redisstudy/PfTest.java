package com.example.redisstudy;

import com.example.redisstudy.service.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author aaron
 * @since 2021-02-08
 */
public class PfTest extends RedisStudyApplicationTests {

    @Autowired
    private RedisService redisService;

    @Test
    void test() {
        for (int i = 0; i < 100000; i++) {
            redisService.pfAdd("codehole", "user" + i);
        }
        long total = redisService.pfCount("codehole");
        System.out.printf("%d %d\n",10000, total);
    }
}

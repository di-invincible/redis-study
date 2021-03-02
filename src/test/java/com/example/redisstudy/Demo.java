package com.example.redisstudy;

import com.example.redisstudy.service.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author aaron
 * @since 2021-02-08
 */
public class Demo extends RedisStudyApplicationTests{

    @Autowired
    private RedisService redisService;

    @Test
    void setKey(){
        redisService.set("11","你好时间111sfas");
    }
}

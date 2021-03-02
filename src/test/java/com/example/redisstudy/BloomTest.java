package com.example.redisstudy;

import com.example.redisstudy.util.BloomFilterHelper;
import com.example.redisstudy.service.RedisBloomFilter;
import com.example.redisstudy.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author aaron
 * @since 2021-02-08
 */
public class BloomTest extends RedisStudyApplicationTests{


    @Autowired
    RedisBloomFilter redisBloomFilter;

    @Autowired
    private BloomFilterHelper bloomFilterHelper;


    String addBloomFilter(){

        String orderNum = "";
        try {
            redisBloomFilter.addByBloomFilter(bloomFilterHelper,"bloom",orderNum);
        } catch (Exception e) {
            e.printStackTrace();
            return "添加失败";
        }

        return "添加成功";
    }
}

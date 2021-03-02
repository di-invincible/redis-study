package com.example.redisstudy.service;


import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {

    /**
     * redis中存任务锁的key前缀
     */
    public final static String SCHEDULE_LOCK_KEY = "lock:schedule:";

    @Resource
    private  StringRedisTemplate stringRedisTemplate;




    /**
     * 判断是否有锁。有，返回true；否，返回false,设置一定期效的锁
     *
     * @param lockName 锁名称
     * @param expire   锁的有效时间长，单位：秒
     * @return boolean
     */
    @Override
    public boolean requireLock(String lockName, long expire) {
        String key = SCHEDULE_LOCK_KEY + lockName;
        Boolean isSetSuccess = stringRedisTemplate.opsForValue().setIfAbsent(key, "1");
        if (isSetSuccess != null && isSetSuccess) {
            stringRedisTemplate.expire(key, expire, TimeUnit.SECONDS);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void set(String key, String value, long time) {
        stringRedisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }

    @Override
    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    @Override
    public Object get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public Boolean del(String key) {
        return stringRedisTemplate.delete(key);
    }

    @Override
    public Long del(List<String> keys) {
        return stringRedisTemplate.delete(keys);
    }

    @Override
    public Boolean expire(String key, long time) {
        return stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    @Override
    public Long getExpire(String key) {
        return stringRedisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    @Override
    public Boolean hasKey(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    @Override
    public Long incr(String key, long delta) {
        return stringRedisTemplate.opsForValue().increment(key, delta);
    }

    @Override
    public Long decr(String key, long delta) {
        return stringRedisTemplate.opsForValue().increment(key, -delta);
    }

    @Override
    public Object hGet(String key, String hashKey) {
        return stringRedisTemplate.opsForHash().get(key, hashKey);
    }

    @Override
    public Boolean hSet(String key, String hashKey, Object value, long time) {
        stringRedisTemplate.opsForHash().put(key, hashKey, value);
        return expire(key, time);
    }

    @Override
    public void hSet(String key, String hashKey, Object value) {
        stringRedisTemplate.opsForHash().put(key, hashKey, value);
    }

    @Override
    public Map<Object, Object> hGetAll(String key) {
        return stringRedisTemplate.opsForHash().entries(key);
    }

    @Override
    public Boolean hSetAll(String key, Map<String, Object> map, long time) {
        stringRedisTemplate.opsForHash().putAll(key, map);
        return expire(key, time);
    }

    @Override
    public void hSetAll(String key, Map<String, Object> map) {
        stringRedisTemplate.opsForHash().putAll(key, map);
    }

    @Override
    public void hDel(String key, Object... hashKey) {
        stringRedisTemplate.opsForHash().delete(key, hashKey);
    }

    @Override
    public Boolean hHasKey(String key, String hashKey) {
        return stringRedisTemplate.opsForHash().hasKey(key, hashKey);
    }

    @Override
    public Long hIncr(String key, String hashKey, Long delta) {
        return stringRedisTemplate.opsForHash().increment(key, hashKey, delta);
    }

    @Override
    public Long hDecr(String key, String hashKey, Long delta) {
        return stringRedisTemplate.opsForHash().increment(key, hashKey, -delta);
    }

    @Override
    public Set<String> sMembers(String key) {
        return stringRedisTemplate.opsForSet().members(key);
    }

    @Override
    public Long sAdd(String key, String... values) {
        return stringRedisTemplate.opsForSet().add(key, values);
    }

    @Override
    public Long sAdd(String key, long time, String... values) {
        Long count = stringRedisTemplate.opsForSet().add(key, values);
        expire(key, time);
        return count;
    }

    @Override
    public Boolean sIsMember(String key, String value) {
        return stringRedisTemplate.opsForSet().isMember(key, value);
    }

    @Override
    public Long sSize(String key) {
        return stringRedisTemplate.opsForSet().size(key);
    }

    @Override
    public Long sRemove(String key, Object... values) {
        return stringRedisTemplate.opsForSet().remove(key, values);
    }

    @Override
    public List<String> lRange(String key, long start, long end) {
        return stringRedisTemplate.opsForList().range(key, start, end);
    }

    @Override
    public Long lSize(String key) {
        return stringRedisTemplate.opsForList().size(key);
    }

    @Override
    public Object lIndex(String key, long index) {
        return stringRedisTemplate.opsForList().index(key, index);
    }

    @Override
    public Long lPush(String key, String value) {
        return stringRedisTemplate.opsForList().rightPush(key, value);
    }

    @Override
    public Long lPush(String key, String value, long time) {
        Long index = stringRedisTemplate.opsForList().rightPush(key, value);
        expire(key, time);
        return index;
    }

    @Override
    public Long lPushAll(String key, String... values) {
        return stringRedisTemplate.opsForList().rightPushAll(key, values);
    }

    @Override
    public Long lPushAll(String key, Long time, String... values) {
        Long count = stringRedisTemplate.opsForList().rightPushAll(key, values);
        expire(key, time);
        return count;
    }

    @Override
    public Long lRemove(String key, long count, String value) {
        return stringRedisTemplate.opsForList().remove(key, count, value);
    }

    @Override
    public Long pfAdd(String key,String value) {
        return stringRedisTemplate.opsForHyperLogLog().add(key,value);
    }

    @Override
    public Long pfCount(String key) {
        return stringRedisTemplate.opsForHyperLogLog().size(key);
    }

    @Override
    public Long bfAdd(String key, String value) {
        return null;
    }

    @Override
    public Long bfExists(String key, String value) {
        return null;
    }

    @Override
    public Long bfMAdd(String key, String... value) {
        return null;
    }

    @Override
    public Long bfMExists(String key, String... value) {
        return null;
    }

}

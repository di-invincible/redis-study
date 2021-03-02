package com.example.redisstudy.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author aaron
 */
public interface RedisService {

    /**
     * 判断是否有锁。有，返回true；否，返回false,设置一定期效的锁
     * @param lockName 锁名称
     * @param expire  锁的有效时间长，单位：秒
     * @return boolean
     */
    boolean requireLock(String lockName,long expire);

    /**
     * 保存属性
     */
    void set(String key, String value, long time);

    /**
     * 保存属性
     */
    void set(String key, String value);

    /**
     * 获取属性
     */
    Object get(String key);

    /**
     * 删除属性
     */
    Boolean del(String key);

    /**
     * 批量删除属性
     */
    Long del(List<String> keys);

    /**
     * 设置过期时间
     */
    Boolean expire(String key, long time);

    /**
     * 获取过期时间
     */
    Long getExpire(String key);

    /**
     * 判断是否有该属性
     */
    Boolean hasKey(String key);

    /**
     * 按delta递增
     */
    Long incr(String key, long delta);

    /**
     * 按delta递减
     */
    Long decr(String key, long delta);

    /**
     * 获取Hash结构中的属性
     */
    Object hGet(String key, String hashKey);

    /**
     * 向Hash结构中放入一个属性
     */
    Boolean hSet(String key, String hashKey, Object value, long time);

    /**
     * 向Hash结构中放入一个属性
     */
    void hSet(String key, String hashKey, Object value);

    /**
     * 直接获取整个Hash结构
     */
    Map<Object, Object> hGetAll(String key);

    /**
     * 直接设置整个Hash结构
     */
    Boolean hSetAll(String key, Map<String, Object> map, long time);

    /**
     * 直接设置整个Hash结构
     */
    void hSetAll(String key, Map<String, Object> map);

    /**
     * 删除Hash结构中的属性
     */
    void hDel(String key, Object... hashKey);

    /**
     * 判断Hash结构中是否有该属性
     */
    Boolean hHasKey(String key, String hashKey);

    /**
     * Hash结构中属性递增
     */
    Long hIncr(String key, String hashKey, Long delta);

    /**
     * Hash结构中属性递减
     */
    Long hDecr(String key, String hashKey, Long delta);

    /**
     * 获取Set结构
     */
    Set<String> sMembers(String key);

    /**
     * 向Set结构中添加属性
     */
    Long sAdd(String key, String... values);

    /**
     * 向Set结构中添加属性
     */
    Long sAdd(String key, long time, String... values);

    /**
     * 是否为Set中的属性
     */
    Boolean sIsMember(String key, String value);

    /**
     * 获取Set结构的长度
     */
    Long sSize(String key);

    /**
     * 删除Set结构中的属性
     */
    Long sRemove(String key, Object... values);

    /**
     * 获取List结构中的属性
     */
    List<String> lRange(String key, long start, long end);

    /**
     * 获取List结构的长度
     */
    Long lSize(String key);

    /**
     * 根据索引获取List中的属性
     */
    Object lIndex(String key, long index);

    /**
     * 向List结构中添加属性
     */
    Long lPush(String key, String value);

    /**
     * 向List结构中添加属性
     */
    Long lPush(String key, String value, long time);

    /**
     * 向List结构中批量添加属性
     */
    Long lPushAll(String key, String... values);

    /**
     * 向List结构中批量添加属性
     */
    Long lPushAll(String key, Long time, String... values);

    /**
     * 从List结构中移除属性
     */
    Long lRemove(String key, long count, String value);

    /**
     * 向HyperLogLog结构中添加计数
     */
    Long pfAdd(String key,String value);

    /**
     *向HyperLogLog结构中获取计数
     */
    Long pfCount(String key);

    Long bfAdd(String key,String value);

    Long bfExists(String key,String value);

    Long bfMAdd(String key,String... value);

    Long bfMExists(String key,String... value);
}

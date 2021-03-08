package com.example.redisstudy.demo;


import java.util.Arrays;
import java.util.BitSet;

/**
 * 布隆 过滤器 demo
 * @author aaron
 * @since 2021-03-06
 */
public class MyBloomFilter  {
    //你的布隆过滤器容量
    private static final int DEFAULT_SIZE = 2 << 28;
    //bit数组，用来存放结果
    private static BitSet bitSet = new BitSet(DEFAULT_SIZE);
    //后面hash函数会用到，用来生成不同的hash值，可随意设置，别问我为什么这么多8，图个吉利
    private static final int[] hashInts = {1, 6, 16, 38, 58, 68};

    //add方法，计算出key的hash值，并将对应下标置为true
    public void add(Object key) {
        Arrays.stream(hashInts ).forEach(i -> bitSet.set(hash(key, i)));
    }

    //判断key是否存在，true不一定说明key存在，但是false一定说明不存在
    public boolean isContain(Object key) {
        boolean result = true;
        for (int i : hashInts) {
            //短路与，只要有一个bit位为false，则返回false
            result = result && bitSet.get(hash(key, i));
        }
        return result;
    }

    //hash函数，借鉴了hashmap的扰动算法，强烈建议大家把这个hash算法看懂，这个设计真的牛皮加闪电
    private int hash(Object key, int i) {
        int h;
        return key == null ? 0 : (i * (DEFAULT_SIZE - 1) & ((h = key.hashCode()) ^ (h >>> 16)));
    }

    static class Test{
        public static void main(String[] args) {
            MyBloomFilter myNewBloomFilter = new MyBloomFilter();
            myNewBloomFilter.add("张学友");
            myNewBloomFilter.add("郭德纲");
            myNewBloomFilter.add("蔡徐鸡");
            myNewBloomFilter.add(666);
            System.out.println(myNewBloomFilter.isContain("张学友"));//true
            System.out.println(myNewBloomFilter.isContain("张学友 "));//false
            System.out.println(myNewBloomFilter.isContain("张学友1"));//false
            System.out.println(myNewBloomFilter.isContain("郭德纲"));//true
            System.out.println(myNewBloomFilter.isContain("蔡徐老母鸡"));//false
            System.out.println(myNewBloomFilter.isContain(666));//true
            System.out.println(myNewBloomFilter.isContain(888));//false
        }

    }


}

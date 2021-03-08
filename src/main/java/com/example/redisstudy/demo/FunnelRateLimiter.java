package com.example.redisstudy.demo;

import java.util.HashMap;
import java.util.Map;

/**
 * 漏斗限流
 * @author aaron
 * @since 2021-03-08
 */
public class FunnelRateLimiter {

    static class Funnel{
        /**
         * 容量
         */
        int capacity;
        /**
         * 泄漏率
         */
        float leakingRate;

        /**
         * 剩余配额
         */
        int leftQuota;

        /**
         * 泄漏 时间点
         */
        long leakingTs;

        public Funnel(int capacity,float leakingRate){
            this.capacity=capacity;
            this.leakingRate=leakingRate;
            this.leftQuota = capacity;
            this.leakingRate=System.currentTimeMillis();
        }

        void makeSpace(){
            long nowTs = System.currentTimeMillis();
            long deltaTs = nowTs - leakingTs;
            int deltaQuota = (int) (deltaTs* leakingRate);
            //间隔时间太长,整数数字过大溢出
            if(deltaQuota<0){
                this.leftQuota = capacity;
                this.leakingTs = nowTs;
                return;
            }

            //腾出空间太小,最小单位是1
            if(deltaQuota < 1){
                return;
            }
            this.leftQuota += deltaQuota;
            this.leakingTs = nowTs;
            if(this.leftQuota>this.capacity){
                this.leftQuota = this.capacity;
            }
        }

        boolean watering(int quota){
            makeSpace();
            if(this.leftQuota>=quota){
                this.leftQuota -= quota;
                return true;
            }
            return false;
        }
    }

    private Map<String,Funnel> funnels = new HashMap<>();

    public boolean isActionAllowed(String userId,String actionKey,int capacity,float leakingRate){
        String key = String.format("%s:%s",userId,actionKey);
        Funnel funnel = funnels.get(key);
        if(funnel == null){
            funnel = new Funnel(capacity,leakingRate);
        }
        return funnel.watering(1); //需要1个quota
    }



}
